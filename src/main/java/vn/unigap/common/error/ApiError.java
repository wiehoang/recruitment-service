package vn.unigap.common.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    private Integer statusCode;
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.statusCode = status.value();
        this.status = status;
        this.message = message;
        errors = Collections.singletonList(error);
    }

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.statusCode = status.value();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}

