package com.job_app.jobms.job;

import com.job_app.jobms.job.dto.JobWithCompanyDTO;
import com.job_app.jobms.job.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<JobWithCompanyDTO>> findAllJobs() {
        List<JobWithCompanyDTO> jobs = jobService.findAll();
        if (jobs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Job>> findByJobId(@PathVariable Long id) {
        Optional<Job> job = jobService.findById(id);
        if (job.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addJob(@RequestBody Job job) {
        jobService.addJob(job);
        return new ResponseEntity<>("Job added", HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@RequestBody Job job, @PathVariable Long id) {
        Optional<Job> jobToUpdate = jobService.findById(id);
        if (jobToUpdate.isEmpty()){
            return new ResponseEntity<>("No Job found with id: " + id ,HttpStatus.NOT_FOUND);
        }
        jobService.updateJob(job,id);
        return new ResponseEntity<>("Job updated", HttpStatus.OK);
    }
}
