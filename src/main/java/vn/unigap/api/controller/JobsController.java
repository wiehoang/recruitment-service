package vn.unigap.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.dto.out.JobsDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.common.response.ApiResponse;
import vn.unigap.api.dto.in.JobsDtoIn;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.in.UpdateJobsDtoIn;
import vn.unigap.api.service.JobsService;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/jobs", consumes = {"application/json"}, produces = {"application/json"})
@Tag(name = "Jobs", description = "Jobs API contains operations to perform on jobs that created by employers")
public class JobsController {

    private final JobsService jobsService;

    @Operation(summary = "Create a new job", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Create a job successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = JobsResponse.class)))})
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER')")
    public ResponseEntity<?> createJob(@RequestBody @Valid JobsDtoIn jobsDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(jobsService.createJob(jobsDtoIn)));
    }

    @Operation(summary = "Update a job details by id", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Update a job details successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = JobsResponse.class)))})
    @PatchMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER')")
    public ResponseEntity<?> updateJob(@PathVariable("id") Long id, @RequestBody @Valid UpdateJobsDtoIn updateJobsDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(jobsService.updateJob(id, updateJobsDtoIn)));
    }

    @Operation(summary = "Get a job details by id", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Get a job details successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = JobsResponse.class)))})
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER', 'ROLE_SEEKER')")
    public ResponseEntity<?> getJob(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(jobsService.getJob(id)));
    }

    @Operation(summary = "Get jobs details by page", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Get jobs details successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PageJobsResponse.class)))})
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER', 'ROLE_SEEKER')")
    public ResponseEntity<?> getAllJobs(@RequestParam Long employerId, @RequestBody @Valid PageDtoIn pageDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(jobsService.getAllJobs(employerId, pageDtoIn)));
    }

    @Operation(summary = "Delete a job by id", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Delete a job successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class)))})
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER')")
    public ResponseEntity<?> deleteJob(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(jobsService.deleteJob(id)));
    }

    private static class JobsResponse extends ApiResponse<JobsDtoOut> {
    }

    private static class PageJobsResponse extends ApiResponse<PageDtoOut<JobsDtoOut>> {
    }
}
