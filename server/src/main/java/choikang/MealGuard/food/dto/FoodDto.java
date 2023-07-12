package choikang.MealGuard.food.dto;

import lombok.Getter;
import lombok.Setter;

public class FoodDto {

    @Getter
    @Setter
    public static class Response{

        private long foodId;
        private String name;

        private int kcal;

        private String taste;
    }

}
