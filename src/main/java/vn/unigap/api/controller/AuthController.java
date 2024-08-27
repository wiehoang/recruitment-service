package vn.unigap.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.dto.in.AuthSignInDtoIn;
import vn.unigap.api.dto.in.AuthSignUpDtoIn;
import vn.unigap.api.service.AuthService;
import vn.unigap.common.response.ApiResponse;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid AuthSignUpDtoIn authSignUpDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(authService.signup(authSignUpDtoIn)));
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<?> signin(@RequestBody @Valid AuthSignInDtoIn authSignInDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(authService.signin(authSignInDtoIn)));
    }

}
