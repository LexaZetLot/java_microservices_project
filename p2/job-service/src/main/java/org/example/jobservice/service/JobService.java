package org.example.jobservice.service;

import org.example.jobservice.dto.JobToCompanyDTO;
import org.example.jobservice.model.Job;

import java.util.List;

public interface JobService {
    public List<JobToCompanyDTO> getAllJobs();
    public void createJob(Job job);
    public JobToCompanyDTO getJobById(Long id);
    public boolean deleteJob(Long id);
    public boolean updateJob(Long id, Job job);
}
