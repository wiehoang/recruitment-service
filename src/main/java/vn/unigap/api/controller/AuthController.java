package vn.unigap.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unigap.api.dto.in.AuthSignInDtoIn;
import vn.unigap.api.dto.in.AuthSignUpDtoIn;
import vn.unigap.api.dto.out.AuthDtoOut;
import vn.unigap.api.entity.User;
import vn.unigap.api.service.AuthService;
import vn.unigap.common.exception.ApiException;
import vn.unigap.common.response.ApiResponse;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth", consumes = {"application/json"}, produces = {"application/json"})
@Tag(name = "Authentication", description = "Authentication API contains operations such as signup, signin")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Create a new account", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "An account is created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)))})
    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid AuthSignUpDtoIn authSignUpDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(authService.signup(authSignUpDtoIn)));
    }

    @Operation(summary = "Sign in an account", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Sign in successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponse.class)))})
    @PostMapping(value = "/signin")
    public ResponseEntity<?> signin(@RequestBody @Valid AuthSignInDtoIn authSignInDtoIn) {
        return ResponseEntity.ok(ApiResponse.success(authService.signin(authSignInDtoIn)));
    }

    private static class LoginResponse extends ApiResponse<AuthDtoOut>{
    }

}
