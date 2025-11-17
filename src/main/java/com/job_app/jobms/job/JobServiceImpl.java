package com.job_app.jobms.job;

import com.job_app.jobms.job.dto.JobWithCompanyDTO;
import com.job_app.jobms.job.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOS = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        for (Job job: jobs){
            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            jobWithCompanyDTO.setJob(job);
            Company company = restTemplate.getForObject("http://localhost:8082/api/v1/companies/" + job.getCompanyID(), Company.class);
            if (company != null){
                System.out.println("Company: " + company.getName());
                jobWithCompanyDTO.setCompany(company);
                jobWithCompanyDTOS.add(jobWithCompanyDTO);
            }
            else {
                System.out.println("Error");
            }
        }
        return jobWithCompanyDTOS;
    }

    @Override
    public Optional<Job> findById(Long id) throws RuntimeException {
        return jobRepository.findById(id);
    }

    @Override
    public void addJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public void updateJob(Job jobToUpdate, Long id) {
        Job existingJob = jobRepository.findById(id).orElseThrow(() -> new RuntimeException("job not found"));
        if (existingJob != null) {
            existingJob.setDescription(jobToUpdate.getDescription());
            existingJob.setTitle(jobToUpdate.getTitle());
            existingJob.setMinSalary(jobToUpdate.getMinSalary());
            existingJob.setMaxSalary(jobToUpdate.getMaxSalary());
            existingJob.setLocation(jobToUpdate.getLocation());
            existingJob.setCompanyID(jobToUpdate.getCompanyID());
            jobRepository.save(existingJob);
        }
    }
}