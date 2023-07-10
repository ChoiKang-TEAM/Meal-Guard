package choikang.MealGuard.food.dto;

import lombok.Getter;
import lombok.Setter;

public class FoodDto {

    @Getter
    @Setter
    public static class Response{

        private long id;
        private String name;

        private String kcal;
    }

}
