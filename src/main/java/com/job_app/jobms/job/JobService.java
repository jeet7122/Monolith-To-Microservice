package com.job_app.jobms.job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    List<Job> findAll();
    Optional<Job> findById(Long id);

    void addJob(Job job);

    void updateJob(Job job, Long id);
}
