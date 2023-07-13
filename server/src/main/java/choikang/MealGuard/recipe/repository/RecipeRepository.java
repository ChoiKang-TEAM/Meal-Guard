package choikang.MealGuard.recipe.repository;

import choikang.MealGuard.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {
}
