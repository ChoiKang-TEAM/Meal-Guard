package choikang.MealGuard.global.exception;

import lombok.Getter;

public enum ExceptionCode {

    REGION_NOT_FOUND(404,"Region not found"),

    RECIPE_NOT_FOUND(404,"Recipe not found"),

    INVALID_NOT_TOKEN(403,"Invalid not token"),

    MEMBER_NOT_FOUND(404, "Member not found"),

    LIKE_NOT_TWICE(409,"Like Not twice"),

    LIKE_NOT_CANCEL(204,"Like Not cancel");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
