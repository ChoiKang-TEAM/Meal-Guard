package choikang.MealGuard.global.exception;

import lombok.Getter;

public enum ExceptionCode {

    REGION_NOT_FOUND(404,"Region not found"),

    RECIPE_NOT_FOUND(404,"Recipe not found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
