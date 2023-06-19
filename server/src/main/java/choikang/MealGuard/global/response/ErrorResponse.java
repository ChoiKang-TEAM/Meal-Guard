package choikang.MealGuard.global.response;

import choikang.MealGuard.global.exception.ExceptionCode;

import java.util.List;

public class ErrorResponse {

    private int status;

    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorResponse of(ExceptionCode exceptionCode) {
        return new ErrorResponse(exceptionCode.getStatus(), exceptionCode.getMessage());
    }

}
