package com.main.springBatch.controllers;

import com.main.springBatch.repositories.UserRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/batch")
public class BatchController {



    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importUserJob;



    @GetMapping("/start")
    public ResponseEntity<String> startBatch() {
        try {
            jobLauncher.run(importUserJob, new org.springframework.batch.core.JobParameters());
            return ResponseEntity.ok("Batch job started successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Batch job failed: " + e.getMessage());
        }
    }




}
