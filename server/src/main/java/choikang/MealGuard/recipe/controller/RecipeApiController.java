package choikang.MealGuard.recipe.controller;

import choikang.MealGuard.recipe.service.RecipeApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeApiController {

    private final RecipeApiService recipeApiService;
    @GetMapping
    public ResponseEntity<String> getApiRecipe() throws IOException {
        recipeApiService.getRecipeList();

        return ResponseEntity.ok("초기화 완료");
    }

}
