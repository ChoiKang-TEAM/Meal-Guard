package choikang.MealGuard.recipe.controller;

import choikang.MealGuard.dto.MultiResponseDto;
import choikang.MealGuard.recipe.dto.FavoriteRecipeResponse;
import choikang.MealGuard.recipe.entity.Recipe;
import choikang.MealGuard.recipe.entity.SearchKeyword;
import choikang.MealGuard.recipe.mapper.RecipeMapper;
import choikang.MealGuard.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeMapper mapper;
    // 레시피 보기
    @GetMapping
    public ResponseEntity getRecipes(@RequestParam(required = false) String name,@RequestParam(required = false) String sortBy,@RequestParam int page,@RequestParam int size){
        Page<Recipe> recipePage = recipeService.findRecipes(name,sortBy,page, size);
        List<Recipe> recipes = recipePage.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.recipeToListResponse(recipes),recipePage), HttpStatus.OK);
    }

    @GetMapping("/{recipe-id}")
    public ResponseEntity getRecipe(@PathVariable("recipe-id") long recipeId, @RequestHeader("Authorization") String token){
        Recipe recipe = recipeService.findRecipe(token,recipeId);

        return new ResponseEntity<>(mapper.recipeToResponse(recipe),HttpStatus.OK);
    }
    // 좋아요 하기
    @PostMapping("/{recipe-id}/favorite")
    public ResponseEntity<String> postFavorite(@PathVariable("recipe-id") long recipeId , @RequestHeader("Authorization") String token) {
        recipeService.createFavorite(recipeId,token);

        return ResponseEntity.ok("좋아요");
    }

    //좋아요 취소
    @DeleteMapping("/{recipe-id}/favorite")
    public ResponseEntity<String> deleteFavorite(@PathVariable("recipe-id") long recipeId , @RequestHeader("Authorization") String token) {
        recipeService.cancleFavorite(recipeId,token);

        return ResponseEntity.ok("좋아요 취소");
    }

    // 좋아요 내역 불러오기
    @GetMapping("/favorites")
    public ResponseEntity getFavoriteRecipes(@RequestParam int page, @RequestParam int size, @RequestHeader("Authorization") String token){
        Page<FavoriteRecipeResponse> pageRecipes = recipeService.findFavoritesRecipes(page, size, token);
        List<FavoriteRecipeResponse> favoriteRecipes = pageRecipes.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(favoriteRecipes,pageRecipes),HttpStatus.OK);
    }

    // 인기검색어 순위
    @GetMapping("/top10")
    public ResponseEntity getTop10PopularKeywords() {
        List<SearchKeyword> top10PopularKeywords = recipeService.findTop10PopularKeywords();

        return new ResponseEntity<>(top10PopularKeywords,HttpStatus.OK);
    }

    // 프론트엔드 캐싱용
    @GetMapping("/name")
    public ResponseEntity getNames(){
        List<Recipe> names = recipeService.findNames();

        return new ResponseEntity<>(mapper.recipeToNames(names),HttpStatus.OK);
    }

    @GetMapping("/recommend")
    public ResponseEntity getRecommendRecipes(@RequestParam String way,
                                              @RequestParam String part){
        List<Recipe> recommendRecipes = recipeService.findRecommendRecipes(way, part);

        return new ResponseEntity<>(mapper.recipeToListResponse(recommendRecipes),HttpStatus.OK);
    }
}
