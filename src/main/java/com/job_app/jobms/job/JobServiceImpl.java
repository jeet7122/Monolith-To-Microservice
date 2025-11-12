package com.job_app.jobms.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<Job> findAll() {
        List<Job> jobs = jobRepository.findAll();
        return jobs;
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