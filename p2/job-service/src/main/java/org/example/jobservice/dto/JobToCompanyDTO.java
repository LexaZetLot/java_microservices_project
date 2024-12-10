package org.example.jobservice.dto;

import org.example.jobservice.model.Company;
import org.example.jobservice.model.Job;
import org.example.jobservice.model.Reviews;

import java.util.ArrayList;

public class JobToCompanyDTO {
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Long companyId;
    private Company company;
    private ArrayList<Reviews> reviews;

    public JobToCompanyDTO() {}

    public JobToCompanyDTO(Job job, Company company, ArrayList<Reviews> reviews) {
        goJob(job);
        this.company = company;
        this.reviews = reviews;
    }

    public Job downJob() {
        return new Job(id, title, description, minSalary, maxSalary, location, companyId);
    }

    public void goJob(Job job) {
        this.id = job.getId();
        this.title = job.getTitle();
        this.description = job.getDescription();
        this.minSalary = job.getMinSalary();
        this.maxSalary = job.getMaxSalary();
        this.location = job.getLocation();
        this.companyId = job.getCompanyId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ArrayList<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Reviews> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "JobToCompanyDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", minSalary='" + minSalary + '\'' +
                ", maxSalary='" + maxSalary + '\'' +
                ", location='" + location + '\'' +
                ", companyId=" + companyId +
                ", company=" + company +
                ", reviews=" + reviews +
                '}';
    }
}