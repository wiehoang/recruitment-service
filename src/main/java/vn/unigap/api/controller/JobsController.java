package vn.unigap.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unigap.common.response.ApiResponse;
import vn.unigap.api.dto.in.JobsDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
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
        return ResponseEntity.ok(ApiResponse.success(jobsService.createJob(jobsDtoIn)));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> updateJob(@PathVariable("id") Long id, @RequestBody @Valid UpdateJobsDtoIn updateJobsDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(jobsService.updateJob(id, updateJobsDtoIn)));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getJob(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(jobsService.getJob(id)));
    }

    @GetMapping
    public ResponseEntity<?> getAllJobs(@RequestParam Long employerId, @RequestBody @Valid PageDtoIn pageDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(jobsService.getAllJobs(employerId, pageDtoIn)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(jobsService.deleteJob(id)));
    }

}
