package com.job_app.jobms.job.dto;

import com.job_app.jobms.job.Job;
import com.job_app.jobms.job.external.Company;
import lombok.Data;

@Data
public class JobWithCompanyDTO {
    private Job job;
    private Company company;
}
