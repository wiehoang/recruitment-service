package vn.unigap.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.unigap.api.dto.in.AuthLogInDtoIn;
import vn.unigap.api.dto.in.AuthSignUpDtoIn;
import vn.unigap.api.dto.out.AuthDtoOut;
import vn.unigap.api.entity.User;
import vn.unigap.api.service.AuthService;
import vn.unigap.common.response.ApiResponse;


/**
 * The `AuthController` class handles authentication-related operations.
 * Provides endpoints for creating a new account (signup) and
 * logging into an existing account (login).
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth", consumes = {"application/json"}, produces = {"application/json"})
@Tag(name = "Authentication",
    description = "Authentication API contains operations such as signup, login")
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

  @Operation(summary = "Log in an account", responses = {
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "200",
        description = "Log in successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = LoginResponse.class)))})
  @PostMapping(value = "/login")
  public ResponseEntity<?> login(@RequestBody @Valid AuthLogInDtoIn authLogInDtoIn) {
    return ResponseEntity.ok(ApiResponse.success(authService.login(authLogInDtoIn)));
  }

  private static class LoginResponse extends ApiResponse<AuthDtoOut> {}

}
