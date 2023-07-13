package choikang.MealGuard.recipe.mapper;

import choikang.MealGuard.recipe.dto.RecipeDto;
import choikang.MealGuard.recipe.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecipeMapper {

    List<RecipeDto.ListResponse> recipeToListResponse(List<Recipe> recipes);

    RecipeDto.Response recipeToResponse(Recipe recipe);

}
