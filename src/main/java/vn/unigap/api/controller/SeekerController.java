package vn.unigap.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.unigap.common.response.ApiResponse;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.in.SeekerDtoIn;
import vn.unigap.api.service.SeekerService;


@RestController
@RequestMapping(value = "/seeker")
@RequiredArgsConstructor
public class SeekerController {

    private final SeekerService seekerService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('SEEKER')")
    public ResponseEntity<?> createSeeker(@RequestBody @Valid SeekerDtoIn seekerDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(seekerService.createSeeker(seekerDtoIn)));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('SEEKER')")
    public ResponseEntity<?> updateSeeker(@PathVariable("id") Long id, @RequestBody @Valid SeekerDtoIn seekerDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(seekerService.updateSeeker(id, seekerDtoIn)));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('SEEKER') or hasRole('EMPLOYER')")
    public ResponseEntity<?> getSeeker(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(seekerService.getSeeker(id)));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('SEEKER') or hasRole('EMPLOYER')")
    public ResponseEntity<?> getAllSeekers(@RequestParam Integer provinceId, @RequestBody @Valid PageDtoIn pageDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(seekerService.getAllSeekers(provinceId, pageDtoIn)));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('SEEKER')")
    public ResponseEntity<?> deleteSeeker(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(seekerService.deleteSeeker(id)));
    }
}
