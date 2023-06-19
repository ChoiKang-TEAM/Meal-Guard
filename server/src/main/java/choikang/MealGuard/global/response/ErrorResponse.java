package choikang.MealGuard.global.response;

import choikang.MealGuard.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ErrorResponse {

    private int status;

    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorResponse of(HttpStatus httpStatus) {
        return new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public static ErrorResponse of(ExceptionCode exceptionCode) {
        return new ErrorResponse(exceptionCode.getStatus(), exceptionCode.getMessage());
    }

}
