package choikang.MealGuard.dessert.dto;

import lombok.Getter;
import lombok.Setter;

public class DessertDto {

    @Getter
    @Setter
    public static class Response{
        private Long dessertId;

        private String name;

        private int kcal;
    }
}
