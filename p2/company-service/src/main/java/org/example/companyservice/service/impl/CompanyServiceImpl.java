package org.example.companyservice.service.impl;

import org.example.companyservice.clients.ReviewsClient;
import org.example.companyservice.dto.ReviewsMessage;
import org.example.companyservice.model.Company;
import org.example.companyservice.repository.CompanyRepository;
import org.example.companyservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    public CompanyRepository companyRepository;
    public ReviewsClient reviewsClient;

    public CompanyServiceImpl(){}

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ReviewsClient reviewsClient) {
        this.companyRepository = companyRepository;
        this.reviewsClient = reviewsClient;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company updateCompany, Long id) {
        Optional<Company> optionalJob = companyRepository.findById(id);
        if (optionalJob.isPresent()) {
            Company company = optionalJob.get();
            company.setName(updateCompany.getName());
            company.setDescription(updateCompany.getDescription());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public void updateCompanyRating(ReviewsMessage reviewsMessage) {
        Company company = companyRepository.findById(reviewsMessage.getCompanyId()).orElseThrow(() -> new RuntimeException("Company not found" + reviewsMessage.getCompanyId()));
        double averageRating = reviewsClient.getAverageRatingForCompany(reviewsMessage.getCompanyId());
        company.setRating(averageRating);
        companyRepository.save(company);
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }


}
