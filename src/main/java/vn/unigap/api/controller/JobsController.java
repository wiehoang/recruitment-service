package vn.unigap.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.common.response.ApiResponse;
import vn.unigap.api.dto.in.JobsDtoIn;
import vn.unigap.api.dto.in.UpdateJobsDtoIn;
import vn.unigap.api.service.JobsService;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/jobs")
public class JobsController {
    @Autowired
    private JobsService jobsService;

    @PostMapping
    public ResponseEntity<?> createJob(@RequestBody @Valid JobsDtoIn jobsDtoIn) {
        return ResponseEntity.ok(ApiResponse.create(jobsService.createJob(jobsDtoIn)));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> updateJob(@PathVariable("id") Long id, @RequestBody @Valid UpdateJobsDtoIn updateJobsDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(jobsService.updateJob(id, updateJobsDtoIn)));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getJob(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(jobsService.getJob(id)));
    }

}
