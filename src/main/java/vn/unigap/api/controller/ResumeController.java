package vn.unigap.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.in.ResumeDtoIn;
import vn.unigap.api.dto.in.UpdateResumeDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.ResumeDtoOut;
import vn.unigap.api.service.ResumeService;
import vn.unigap.common.response.ApiResponse;


/**
 * The `ResumeController` class handles resume-related operations that performed by a seeker.
 * Provides endpoints for creating, editing and deleting a resume, getting resumes details.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/resume", consumes = {"application/json"}, produces = {"application/json"})
@Tag(name = "Resume",
    description = "Resume API contains operations to perform on resumes that created by seekers")
public class ResumeController {

  private final ResumeService resumeService;

  @Operation(summary = "Creat a new resume", responses = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "201",
          description = "Create a resume successfully",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ResumeResponse.class)))})
  @PostMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SEEKER')")
  public ResponseEntity<?> createResume(@RequestBody @Valid ResumeDtoIn resumeDtoIn) {
    return ResponseEntity.ok(ApiResponse.success(resumeService.createResume(resumeDtoIn)));
  }

  @Operation(summary = "Update a resume details by id", responses = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "200",
          description = "Update a resume details successfully",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ResumeResponse.class)))})
  @PutMapping(value = "/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SEEKER')")
  public ResponseEntity<?> updateResume(@PathVariable("id") Long id,
                                        @RequestBody @Valid UpdateResumeDtoIn updateResumeDtoIn) {
    return ResponseEntity.ok(ApiResponse.success(
                                resumeService.updateResume(id, updateResumeDtoIn)));
  }

  @Operation(summary = "Get a resume details by id", responses = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "200",
          description = "Get a resume details successfully",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ResumeResponse.class)))})
  @GetMapping(value = "/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER', 'ROLE_SEEKER')")
  public ResponseEntity<?> getResume(@PathVariable("id") Long id) {
    return ResponseEntity.ok(ApiResponse.success(resumeService.getResume(id)));
  }

  @Operation(summary = "Get resumes details by page", responses = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "200",
          description = "Get resumes details successfully",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = PageResumeResponse.class)))})
  @GetMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER', 'ROLE_SEEKER')")
  public ResponseEntity<?> getAllResumes(@RequestParam("seekerId") Long seekerId,
                                         @RequestBody @Valid PageDtoIn pageDtoIn) {
    return ResponseEntity.ok(ApiResponse.success(resumeService.getAllResumes(seekerId, pageDtoIn)));
  }

  @Operation(summary = "Delete a resume by id", responses = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "200",
          description = "Delete a resume successfully",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = Boolean.class)))})
  @DeleteMapping(value = "/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SEEKER')")
  public ResponseEntity<?> deleteResume(@PathVariable("id") Long id) {
    return ResponseEntity.ok(ApiResponse.success(resumeService.deleteResume(id)));
  }

  private static class ResumeResponse extends ApiResponse<ResumeDtoOut> {
  }

  private static class PageResumeResponse extends ApiResponse<PageDtoOut<ResumeDtoOut>> {
  }

}
