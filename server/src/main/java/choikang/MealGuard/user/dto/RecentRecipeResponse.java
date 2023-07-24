package choikang.MealGuard.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RecentRecipeResponse {
    private Long recipeId;
    private String thumbnail;
    private String name;
    private Double kcal;
}
