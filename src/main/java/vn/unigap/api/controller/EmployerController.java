package vn.unigap.api.controller;

import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.dto.in.EmployerDtoIn;
import vn.unigap.common.response.ApiResponse;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.service.EmployerService;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/employer")
public class EmployerController {

    private final EmployerService employerService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER', 'ROLE_SEEKER')")
    public ResponseEntity<?> getEmployerById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(employerService.getEmployerById(id)));
    }

    @PostMapping(value = "")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER')")
    public ResponseEntity<?> createEmployer(@RequestBody @Valid EmployerDtoIn employerDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(employerService.createEmployer(employerDtoIn)));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER')")
    public ResponseEntity<?> updateEmployer(@PathVariable(value = "id") Long id, @RequestBody @Valid EmployerDtoIn employerDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(employerService.updateEmployer(id, employerDtoIn)));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER', 'ROLE_SEEKER')")
    public ResponseEntity<?> getAllEmployers(@RequestBody @Valid PageDtoIn pageDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(employerService.getAllEmployers(pageDtoIn)));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYER')")
    public ResponseEntity<?> deleteEmployer(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(employerService.deleteEmployer(id)));
    }
}
