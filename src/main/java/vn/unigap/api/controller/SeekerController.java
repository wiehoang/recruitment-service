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
import vn.unigap.api.dto.in.SeekerDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.SeekerDtoOut;
import vn.unigap.api.service.SeekerService;
import vn.unigap.common.response.ApiResponse;


/**
 * The `SeekerController` class handles seeker-related operations.
 * Provides endpoints for creating, editing and deleting a seeker, getting seekers details.
 */
@RestController
@RequestMapping(value = "/seeker")
@RequiredArgsConstructor
@Tag(name = "Seeker", description = "Seeker API contains operations to perform on seekers")
public class SeekerController {

  private final SeekerService seekerService;

  @Operation(summary = "Creat a new seeker", responses = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "201",
          description = "Create a seeker successfully",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = SeekerResponse.class)))})
  @PostMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SEEKER')")
  public ResponseEntity<?> createSeeker(@RequestBody @Valid SeekerDtoIn seekerDtoIn) {
    return ResponseEntity.ok(ApiResponse.success(seekerService.createSeeker(seekerDtoIn)));
  }

  @Operation(summary = "Update a seeker details by id", responses = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "200",
          description = "Update a seeker details successfully",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = SeekerResponse.class)))})
  @PutMapping(value = "/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SEEKER')")
  public ResponseEntity<?> updateSeeker(@PathVariable("id") Long id,
                                        @RequestBody @Valid SeekerDtoIn seekerDtoIn) {
    return ResponseEntity.ok(ApiResponse.success(seekerService.updateSeeker(id, seekerDtoIn)));
  }

  @Operation(summary = "Get a seeker details by id", responses = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "200",
          description = "Get a seeker details successfully",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = SeekerResponse.class)))})
  @GetMapping(value = "/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER', 'ROLE_SEEKER')")
  public ResponseEntity<?> getSeeker(@PathVariable("id") Long id) {
    return ResponseEntity.ok(ApiResponse.success(seekerService.getSeeker(id)));
  }

  @Operation(summary = "Get seekers details by page", responses = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "200",
          description = "Get seekers details successfully",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = PageSeekerResponse.class)))})
  @GetMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER', 'ROLE_SEEKER')")
  public ResponseEntity<?> getAllSeekers(@RequestParam Integer provinceId,
                                         @RequestBody @Valid PageDtoIn pageDtoIn) {
    return ResponseEntity.ok(ApiResponse.success(
                                seekerService.getAllSeekers(provinceId, pageDtoIn)));
  }

  @Operation(summary = "Delete a seeker by id", responses = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "200",
          description = "Delete a seeker successfully",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = Boolean.class)))})
  @DeleteMapping(value = "/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SEEKER')")
  public ResponseEntity<?> deleteSeeker(@PathVariable("id") Long id) {
    return ResponseEntity.ok(ApiResponse.success(seekerService.deleteSeeker(id)));
  }

  private static class SeekerResponse extends ApiResponse<SeekerDtoOut> {
  }

  private static class PageSeekerResponse extends ApiResponse<PageDtoOut<SeekerDtoOut>> {
  }

}
