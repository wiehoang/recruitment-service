package vn.unigap.api.common.exception;

import org.springframework.http.HttpStatus;
import vn.unigap.api.common.errorcode.ErrorCode;


public class ApiException extends RuntimeException {
    private Integer errorCode;
    private HttpStatus httpStatus;

    public ApiException(Integer errorCode, HttpStatus httpStatus, String message) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}
