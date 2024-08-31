package vn.unigap.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.dto.in.EmployerDtoIn;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.common.response.ApiResponse;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.service.EmployerService;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/employer", consumes = {"application/json"}, produces = {"application/json"})
@Tag(name = "Employer", description = "Employer API contains operations to perform on an employer")
public class EmployerController {

    private final EmployerService employerService;

    @Operation(summary = "Get an employer details by id", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Get an employer details successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmployerResponse.class)))})
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER', 'ROLE_SEEKER')")
    public ResponseEntity<?> getEmployerById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(employerService.getEmployerById(id)));
    }

    @Operation(summary = "Creat a new employer", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Create an employer successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmployerResponse.class)))})
    @PostMapping(value = "")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER')")
    public ResponseEntity<?> createEmployer(@RequestBody @Valid EmployerDtoIn employerDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(employerService.createEmployer(employerDtoIn)));
    }

    @Operation(summary = "Update an employer details by id", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Update an employer details successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmployerResponse.class)))})
    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER')")
    public ResponseEntity<?> updateEmployer(@PathVariable(value = "id") Long id, @RequestBody @Valid EmployerDtoIn employerDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(employerService.updateEmployer(id, employerDtoIn)));
    }

    @Operation(summary = "Get employers details by page", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Get employers details successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PageEmployerResponse.class)))})
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER', 'ROLE_SEEKER')")
    public ResponseEntity<?> getAllEmployers(@RequestBody @Valid PageDtoIn pageDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(employerService.getAllEmployers(pageDtoIn)));
    }

    @Operation(summary = "Delete an employer by id", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Delete an employer successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class)))})
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER')")
    public ResponseEntity<?> deleteEmployer(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(employerService.deleteEmployer(id)));
    }

    private static class EmployerResponse extends ApiResponse<EmployerDtoOut> {
    }

    private static class PageEmployerResponse extends ApiResponse<PageDtoOut<EmployerDtoOut>> {
    }
}

