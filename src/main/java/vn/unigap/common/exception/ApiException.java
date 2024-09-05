package vn.unigap.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


/** Define exception handling used throughout the application. */
@Getter
@Setter
public class ApiException extends RuntimeException {

  private Integer statusCode;
  private HttpStatus status;

  /** Constructor for responding exception with custom messages. */
  public ApiException(HttpStatus status, String message) {
    super(message);
    this.statusCode = status.value();
    this.status = status;
  }

}

