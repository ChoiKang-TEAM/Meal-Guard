package choikang.MealGuard.recipe.repository;

import choikang.MealGuard.recipe.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {

    @Query("SELECT r FROM Recipe r WHERE lower(r.name) LIKE %:keyword% OR lower(r.hashtag) LIKE %:keyword%")
    Page<Recipe> findByNameOrHashtagContainingIgnoreCase(String keyword, Pageable pageable);

    @Modifying
    @Query(value = "INSERT INTO favorite (recipe_id, user_seq, ischecked) " +
            "SELECT :recipeId, :userSeq, true " +
            "WHERE NOT EXISTS (SELECT recipe_id, user_seq " +
            "FROM favorite " +
            "WHERE recipe_id = :recipeId and user_seq = :userSeq)", nativeQuery = true)
    void upFavorite(long recipeId, String userSeq);

    @Modifying
    @Query(value = "DELETE FROM favorite WHERE recipe_id = :recipeId and user_seq = :userSeq", nativeQuery = true)
    void downFavorite(long recipeId, String userSeq);
}
