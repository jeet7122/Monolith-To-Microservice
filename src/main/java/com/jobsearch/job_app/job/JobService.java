package com.jobsearch.job_app.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    Job findById(long id);

    void addJob(Job job);

    void updateJob(Job job, Long id);
}
