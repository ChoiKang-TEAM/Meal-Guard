package choikang.MealGuard.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FavoriteRecipeResponse {
    private Long recipeId;
    private String thumbnail;
    private String name;
    private Double kcal;
}
