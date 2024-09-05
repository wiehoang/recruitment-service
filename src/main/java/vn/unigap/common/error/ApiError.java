package vn.unigap.common.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import org.springframework.http.HttpStatus;


/** Define error handling used throughout the application. */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

  private Integer statusCode;
  private HttpStatus status;
  private String message;
  private List<String> errors;

  /** Constructor for handling only 1 error. */
  public ApiError(HttpStatus status, String message, String error) {
    super();
    this.statusCode = status.value();
    this.status = status;
    this.message = message;
    errors = Collections.singletonList(error);
  }

  /** Constructor for handling only over 2 errors. */
  public ApiError(HttpStatus status, String message, List<String> errors) {
    super();
    this.statusCode = status.value();
    this.status = status;
    this.message = message;
    this.errors = errors;
  }
}

