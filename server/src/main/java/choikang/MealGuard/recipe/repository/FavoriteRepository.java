package choikang.MealGuard.recipe.repository;

import choikang.MealGuard.recipe.dto.FavoriteRecipeResponse;
import choikang.MealGuard.recipe.dto.RecipeDto;
import choikang.MealGuard.recipe.entity.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {

    @Query("SELECT new choikang.MealGuard.recipe.dto.FavoriteRecipeResponse(r.recipeId, r.thumbnail, r.name, r.kcal) " +
            "FROM Favorite f JOIN Recipe r ON f.recipe.recipeId = r.recipeId " +
            "WHERE f.user.user_seq = :userSeq")
    Page<FavoriteRecipeResponse> findFavoriteRecipesByUser_seq(String userSeq, Pageable pageable);
}
