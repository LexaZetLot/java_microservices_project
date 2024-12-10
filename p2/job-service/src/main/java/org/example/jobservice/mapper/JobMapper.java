package org.example.jobservice.mapper;

import org.example.jobservice.dto.JobToCompanyDTO;
import org.example.jobservice.model.Company;
import org.example.jobservice.model.Job;
import org.example.jobservice.model.Reviews;

import java.util.ArrayList;

public class JobMapper {
    public static JobToCompanyDTO mapToJobToCompanyDTO(Job job, Company company, ArrayList<Reviews> reviews) {
        return new JobToCompanyDTO(job, company, reviews);
    }
}
