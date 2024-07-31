package vn.unigap.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.common.response.ApiResponse;
import vn.unigap.api.dto.in.ResumeDtoIn;
import vn.unigap.api.dto.in.UpdateResumeDtoIn;
import vn.unigap.api.service.ResumeService;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/resume")
public class ResumeController {

    final private ResumeService resumeService;

    @PostMapping
    public ResponseEntity<?> createResume(@RequestBody @Valid ResumeDtoIn resumeDtoIn) {
        return ResponseEntity.ok(ApiResponse.create(resumeService.createResume(resumeDtoIn)));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateResume(@PathVariable("id") Long id, @RequestBody @Valid UpdateResumeDtoIn updateResumeDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(resumeService.updateResume(id, updateResumeDtoIn)));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getResume(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(resumeService.getResume(id)));
    }


}
