package choikang.MealGuard.recipe.service;

import choikang.MealGuard.global.exception.BusinessLogicException;
import choikang.MealGuard.global.exception.ExceptionCode;
import choikang.MealGuard.recipe.entity.Recipe;
import choikang.MealGuard.recipe.repository.RecipeRepository;
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
}
