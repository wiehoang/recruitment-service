package vn.unigap.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private int statusCode;
    private HttpStatus status;
    private String message;
    private T object;

    public static <T> ApiResponse<T> success(T object) {
        return new ApiResponse<>(0, HttpStatus.OK, "Success", object);
    }

    public static <T> ApiResponse<T> error(int statusCode, HttpStatus status, String message) {
        return new ApiResponse<>(statusCode, status, message, null);
    }
}

