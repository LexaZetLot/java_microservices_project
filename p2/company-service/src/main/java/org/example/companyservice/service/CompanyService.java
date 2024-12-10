package org.example.companyservice.service;

import org.example.companyservice.dto.ReviewsMessage;
import org.example.companyservice.model.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company updateCompany, Long id);
    void updateCompanyRating(ReviewsMessage reviewsMessage);
    void createCompany(Company company);
    boolean deleteCompany(Long id);
    Company getCompanyById(Long id);
}
