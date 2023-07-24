package choikang.MealGuard.user.mapper;

import choikang.MealGuard.user.dto.RecentRecipeResponse;
import choikang.MealGuard.user.entity.RecentRecipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecentRecipeMapper {
    @Mapping(source = "recipe.recipeId", target = "recipeId")
    @Mapping(source = "recipe.thumbnail", target = "thumbnail")
    @Mapping(source = "recipe.name", target = "name")
    @Mapping(source = "recipe.kcal", target = "kcal")
    RecentRecipeResponse recentRecipeToResponse(RecentRecipe recentRecipe);

    List<RecentRecipeResponse> recentRecipesToResponses(List<RecentRecipe> recentRecipes);
}
