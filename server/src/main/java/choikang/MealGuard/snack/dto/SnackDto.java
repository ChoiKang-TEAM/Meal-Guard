package choikang.MealGuard.snack.dto;

import lombok.Getter;
import lombok.Setter;

public class SnackDto {

    @Getter
    @Setter
    public static class Response{
        private Long snackId;
        private String name;
        private int kcal;
    }
}
