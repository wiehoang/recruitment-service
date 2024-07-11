package vn.unigap.api.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import vn.unigap.api.common.errorcode.ErrorCode;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private int errorCode;
    private int statusCode;
    private String message;
    private T object;

    public static <T> ApiResponse<T> success(T object) {
        return new ApiResponse<>(ErrorCode.SUCCESS, HttpStatus.OK.value(), "Success", object);
    }
}

