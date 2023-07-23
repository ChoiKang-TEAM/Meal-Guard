package choikang.MealGuard.recipe.service;

import choikang.MealGuard.global.exception.BusinessLogicException;
import choikang.MealGuard.global.exception.ExceptionCode;
import choikang.MealGuard.helper.JwtUtils;
import choikang.MealGuard.recipe.dto.FavoriteRecipeResponse;
import choikang.MealGuard.recipe.entity.Favorite;
import choikang.MealGuard.recipe.entity.Recipe;
import choikang.MealGuard.recipe.entity.SearchKeyword;
import choikang.MealGuard.recipe.repository.FavoriteRepository;
import choikang.MealGuard.recipe.repository.RecipeRepository;
import choikang.MealGuard.recipe.repository.SearchKeywordRepository;
import choikang.MealGuard.user.entity.User;
import choikang.MealGuard.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static choikang.MealGuard.recipe.helper.BlockWord.BLOCKED_WORDS;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final SearchKeywordRepository searchKeywordRepository;
    private final JwtUtils jwtUtils;


    // 전체 조회
    public Page<Recipe> findRecipes(String name, String sortBy, int page, int size) {
        Page<Recipe> recipes;

        if (name != null) {
            name = name.toLowerCase().trim();
            if (BLOCKED_WORDS.contains(name)) {
                // 욕설이 포함된 검색어는 저장하지 않고 무시
                return recipeRepository.findAll(PageRequest.of(page - 1, size, Sort.by("recipeId").descending()));
            }

            SearchKeyword searchKeyword = searchKeywordRepository.findByKeyword(name);
            if (searchKeyword == null) {
                searchKeyword = new SearchKeyword();
                searchKeyword.setKeyword(name);
                searchKeyword.setSearchCount(1);
            } else {
                searchKeyword.setSearchCount(searchKeyword.getSearchCount() + 1);
            }
            searchKeywordRepository.save(searchKeyword);
            return recipeRepository.findByNameContainingIgnoreCase(name, PageRequest.of(page - 1, size, Sort.by("recipeId").descending()));
        } else {
            Sort sort;
            if (sortBy == null) {
                sort = Sort.by("recipeId").descending();
            } else if (sortBy.equals("highCalorie")) {
                sort = Sort.by("kcal").descending();
            } else if (sortBy.equals("lowCalorie")) {
                sort = Sort.by("kcal").ascending();
            } else if (sortBy.equals("highCarbohydrate")) {
                sort = Sort.by("carbohydrate").descending();
            } else if (sortBy.equals("lowCarbohydrate")) {
                sort = Sort.by("carbohydrate").ascending();
            } else if (sortBy.equals("highProtein")) {
                sort = Sort.by("protein").descending();
            } else if (sortBy.equals("lowProtein")) {
                sort = Sort.by("protein").ascending();
            } else if (sortBy.equals("highFat")) {
                sort = Sort.by("fat").descending();
            } else if (sortBy.equals("lowFat")) {
                sort = Sort.by("fat").ascending();
            } else if (sortBy.equals("highSodium")) {
                sort = Sort.by("sodium").descending();
            } else if (sortBy.equals("lowSodium")) {
                sort = Sort.by("sodium").ascending();
            } else {
                sort = Sort.by("recipeId").descending();
            }

            recipes = recipeRepository.findAll(PageRequest.of(page - 1, size, sort));
            return recipes;
        }
    }

    public Recipe findRecipe(long recipeId){
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        return optionalRecipe.orElseThrow(() -> new BusinessLogicException(ExceptionCode.RECIPE_NOT_FOUND));
    }

    public void createFavorite(long recipeId,String token) {
        Recipe recipe = findRecipe(recipeId);

        Claims claims = jwtUtils.parseToken(token.substring(7));
        String id = (String) claims.get("userSeq");

        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        for (Favorite favorite : user.getFavorites()) {
            if (favorite.getRecipe().getRecipeId() == recipe.getRecipeId()) {
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

    public Page<FavoriteRecipeResponse> findFavoritesRecipes(int page, int size, String token){
        Claims claims = jwtUtils.parseToken(token.substring(7));
        String id = (String) claims.get("userSeq");

        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return favoriteRepository.findFavoriteRecipesByUser_seq(id, PageRequest.of(page - 1, size, Sort.by("recipe.recipeId").descending()));
    }

    public List<SearchKeyword> findTop10PopularKeywords() {
        return searchKeywordRepository.findTop10ByOrderBySearchCountDesc();
    }

    public List<Recipe> findNames(){
        return recipeRepository.findAll();
    }
}
