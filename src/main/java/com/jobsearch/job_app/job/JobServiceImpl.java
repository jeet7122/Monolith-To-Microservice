package com.jobsearch.job_app.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Job findById(long id) {
        return jobRepository.findById(id).orElseThrow(() -> new RuntimeException("job not found"));
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
            jobRepository.save(existingJob);
        }
    }
}