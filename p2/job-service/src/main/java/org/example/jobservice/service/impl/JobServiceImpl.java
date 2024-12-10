package org.example.jobservice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.example.jobservice.clients.CompanyClient;
import org.example.jobservice.clients.ReviewsClient;
import org.example.jobservice.dto.JobToCompanyDTO;
import org.example.jobservice.model.Company;
import org.example.jobservice.model.Job;
import org.example.jobservice.model.Reviews;
import org.example.jobservice.repository.JobRepository;
import org.example.jobservice.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.example.jobservice.external.ReviewsExternal.convertReviewsToReviewsExternalArray;
import static org.example.jobservice.mapper.JobMapper.mapToJobToCompanyDTO;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final CompanyClient companyClient;
    private final ReviewsClient reviewsClient;
    int attempts = 0;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewsClient reviewsClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewsClient = reviewsClient;
    }

    @Override
    //@CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public List<JobToCompanyDTO> getAllJobs() {
        System.out.println("Attempt " + ++attempts);
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<String> companyBreakerFallback(Exception e) {
        List<String> list = new ArrayList<>();
        list.add(e.getMessage());
        return list;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobToCompanyDTO getJobById(Long id) {
        return convertToDto(Objects.requireNonNull(jobRepository.findById(id).orElse(null)));
    }

    @Override
    public boolean deleteJob(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job job) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job jobBuf = optionalJob.get();
            jobBuf.setTitle(job.getTitle());
            jobBuf.setDescription(job.getDescription());
            jobBuf.setMinSalary(job.getMinSalary());
            jobBuf.setMaxSalary(job.getMaxSalary());
            jobBuf.setLocation(job.getLocation());
            jobBuf.setCompanyId(job.getCompanyId());
            jobRepository.save(jobBuf);
            return true;
        }
        return false;
    }

    private JobToCompanyDTO convertToDto(Job job){
        Company company = companyClient.getCompany(job.getCompanyId());
        ArrayList<Reviews> reviews = convertReviewsToReviewsExternalArray(reviewsClient.getReviews(job.getCompanyId()));
        return mapToJobToCompanyDTO(job, company, reviews);
    }
}