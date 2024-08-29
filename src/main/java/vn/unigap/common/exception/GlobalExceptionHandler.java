package vn.unigap.common.exception;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.unigap.common.error.ApiError;
import vn.unigap.common.response.ApiResponse;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleCustomException(ApiException e) {
        ApiResponse<Object> apiResponse = ApiResponse.error(e.getStatusCode(), e.getStatus(), e.getMessage());
        log.debug("Error occurred: {}", e.getMessage(), e);
        return new ResponseEntity<>(apiResponse, e.getStatus());
    }

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ":" + error.getDefaultMessage());
        }

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        log.debug("Method argument not valid: {}. Request details: {}", ex.getMessage(), request.getDescription(false), ex);

        return new ResponseEntity<>(apiError, status);
    }

    @Override
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    protected ResponseEntity<Object> handleMissingServletRequestPart(
            MissingServletRequestPartException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String error = ex.getRequestPartName() + " part is missing";

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        log.debug("Missing servlet request part: {}. Request details: {}", ex.getMessage(), request.getDescription(false), ex);

        return new ResponseEntity<>(apiError, status);
    }

    @Override
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String error = ex.getParameterName() + " parameter is missing";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        log.debug("Missing servlet request parameter: {}. Request details: {}", ex.getMessage(), request.getDescription(false), ex);

        return new ResponseEntity<>(apiError, status);
    }

    @Override
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    protected ResponseEntity<Object> handleMissingPathVariable(
            MissingPathVariableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String error = ex.getVariableName() + " variable is missing in path";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        log.debug("Missing path variable: {}. Request details: {}", ex.getMessage(), request.getDescription(false), ex);

        return new ResponseEntity<>(apiError, status);
    }

    @Override
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String requiredType = ex.getRequiredType() == null ? null : ex.getRequiredType().getName();
        String error = ex.getPropertyName() + " should be " + requiredType + " type";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        log.debug("Type mismatch: {}. Request details: {}", ex.getMessage(), request.getDescription(false), ex);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @Override
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "HttpMessageNotReadable");
        log.debug("Http message not readable: {}. Request details: {}", ex.getMessage(), request.getDescription(false), ex);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @Override
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
            HttpMessageNotWritableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "HttpMessageNotReadable");
        log.debug("Http message not writable: {}. Request details: {}", ex.getMessage(), request.getDescription(false), ex);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "405", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String supportedMethod = ex.getSupportedMethods() == null ? null : String.join(", ", ex.getSupportedMethods());
        String error = ex.getMethod() + " method is not supported. Supported methods: " + supportedMethod;
        ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), error);
        log.debug("Http request method not supported: {}. Request details: {}", ex.getMessage(), request.getDescription(false), ex);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "415", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String supportedType = ex.getSupportedMediaTypes().toString().replaceAll("[\\[\\]]", "");
        String error = ex.getContentType() + "media type is not supported. Supported media types: " + supportedType;
        ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage(), error);
        log.debug("Http media type not supported: {}. Request details: {}", ex.getMessage(), request.getDescription(false), ex);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String error = "No handler found for " + ex.getHttpMethod() + ": " + ex.getRequestURL();
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        log.debug("No handler found: {}. Request details: {}", ex.getMessage(), request.getDescription(false), ex);


        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage(), "Access denied");
        log.error("Access denied: {}", ex.getMessage(), ex);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownException(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "Something went wrong");
        log.error("Error occurred: {}", ex.getMessage(), ex);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
