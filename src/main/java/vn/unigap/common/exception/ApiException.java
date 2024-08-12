package vn.unigap.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class ApiException extends RuntimeException {
    private Integer statusCode;
    private HttpStatus status;

    public ApiException(HttpStatus status, String message) {
        super(message);
        this.statusCode = status.value();
        this.status = status;
    }
}

