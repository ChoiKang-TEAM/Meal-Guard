package choikang.MealGuard.recipe.service;

import choikang.MealGuard.global.exception.BusinessLogicException;
import choikang.MealGuard.global.exception.ExceptionCode;
import choikang.MealGuard.helper.JwtUtils;
import choikang.MealGuard.recipe.entity.Favorite;
import choikang.MealGuard.recipe.entity.Recipe;
import choikang.MealGuard.recipe.repository.RecipeRepository;
import choikang.MealGuard.user.entity.User;
import choikang.MealGuard.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    public Page<Recipe> findRecipes(int page,int size){
        return recipeRepository.findAll(PageRequest.of(page-1,size, Sort.by("recipeId").descending()));
    }

    public Recipe findRecipe(long recipeId){
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        return optionalRecipe.orElseThrow(() -> new BusinessLogicException(ExceptionCode.RECIPE_NOT_FOUND));
    }

    public Page<Recipe> findRecipesBySearch(String name,int page,int size){
        if(name == null) return recipeRepository.findAll(PageRequest.of(page-1,size, Sort.by("recipeId").descending()));
        else return recipeRepository.findByNameContainingIgnoreCase(name,PageRequest.of(page-1,size, Sort.by("recipeId").descending()));
    }

    public void createFavorite(long recipeId,String token) {
        Recipe recipe = findRecipe(recipeId);

        Claims claims = jwtUtils.parseToken(token.substring(7));
        String id = (String) claims.get("userSeq");

        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        for(Favorite favorite : user.getFavorites()){
            if(favorite.getRecipe().getRecipeId() == recipe.getRecipeId()){
                throw new BusinessLogicException(ExceptionCode.LIKE_NOT_TWICE);
            }
        }

        recipeRepository.upFavorite(recipe.getRecipeId(),user.getUser_seq());

    }

    //좋아요 취소
    public void cancleFavorite(long recipeId,String token) {
        Recipe recipe = findRecipe(recipeId);


        Claims claims = jwtUtils.parseToken(token.substring(7));
        String id = (String) claims.get("userSeq");

        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));


        recipe.getFavorites().stream()
                .filter(f -> f.getUser() == user)
                .findFirst().orElseThrow(() -> new BusinessLogicException(ExceptionCode.LIKE_NOT_CANCEL));

        recipeRepository.downFavorite(recipe.getRecipeId(),user.getUser_seq());

    }
}
