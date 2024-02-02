package choikang.MealGuard.recipe.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import choikang.MealGuard.recipe.dto.FavoriteRecipeResponse;
import choikang.MealGuard.recipe.dto.RecipeDto;
import choikang.MealGuard.recipe.entity.Recipe;
import choikang.MealGuard.recipe.entity.SearchKeyword;
import choikang.MealGuard.recipe.mapper.RecipeMapper;
import choikang.MealGuard.recipe.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(RecipeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @MockBean
    private RecipeMapper mapper;

    @Test
    void 레시피_목록_가져오기() throws Exception {

        List<Recipe> mockRecipes = new ArrayList<>();

        Page<Recipe> mockRecipePage = new PageImpl<>(mockRecipes);

        Mockito.when(recipeService.findRecipes(anyString(), anyString(), anyInt(), anyInt())).thenReturn(mockRecipePage);

        List<RecipeDto.ListResponse> mockListResponses = new ArrayList<>();

        Mockito.when(mapper.recipeToListResponse(anyList())).thenReturn(mockListResponses);

        mockMvc.perform(get("/recipes")
                        .param("name", "닭가슴살")
                        .param("sortBy", "highProtein")
                        .param("page", "1")
                        .param("size", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void 레시피_상세_가져오기() throws Exception {
        long recipeId = 1L;
        RecipeDto.Response mockRecipe = new RecipeDto.Response();
        mockRecipe.setRecipeId(recipeId);
        String accessToken = "Bearer eyJhbGciOiJIUzUxMiJ9.NjA2NDUwOTc3LCJtZW1iZXJJZCI6Mjd9.1TvYDexLUkOkOsBksbS6dnyJ4Ig1m9LMdTJ2FzCdOW0GEEdM4S6MpLZTpMGZCa-BN9jnbC9htZljsi5e7Mc-OQ";

        Recipe recipe = new Recipe();
        Mockito.when(recipeService.findRecipe(anyString(),anyLong())).thenReturn(recipe);
        Mockito.when(mapper.recipeToResponse(any())).thenReturn(mockRecipe);

        mockMvc.perform(get("/recipes/" + recipeId)
                        .header("Authorization", accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void 레시피_좋아요_하기() throws  Exception{
        String accessToken = "Bearer eyJhbGciOiJIUzUxMiJ9.NjA2NDUwOTc3LCJtZW1iZXJJZCI6Mjd9.1TvYDexLUkOkOsBksbS6dnyJ4Ig1m9LMdTJ2FzCdOW0GEEdM4S6MpLZTpMGZCa-BN9jnbC9htZljsi5e7Mc-OQ";

        mockMvc.perform(post("/recipes/{recipe-id}/favorite",1)
                        .header("Authorization",accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void 레시피_좋아요_취소() throws Exception{
        String accessToken = "Bearer eyJhbGciOiJIUzUxMiJ9.NjA2NDUwOTc3LCJtZW1iZXJJZCI6Mjd9.1TvYDexLUkOkOsBksbS6dnyJ4Ig1m9LMdTJ2FzCdOW0GEEdM4S6MpLZTpMGZCa-BN9jnbC9htZljsi5e7Mc-OQ";

        mockMvc.perform(delete("/recipes/{recipe-id}/favorite",1)
                        .header("Authorization",accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void 좋아요_내역_불러오기() throws Exception{
        int page = 1;
        int size = 10;
        String accessToken = "Bearer eyJhbGciOiJIUzUxMiJ9.NjA2NDUwOTc3LCJtZW1iZXJJZCI6Mjd9.1TvYDexLUkOkOsBksbS6dnyJ4Ig1m9LMdTJ2FzCdOW0GEEdM4S6MpLZTpMGZCa-BN9jnbC9htZljsi5e7Mc-OQ";

        List<FavoriteRecipeResponse> favoriteRecipes = new ArrayList<>();
        Page<FavoriteRecipeResponse> pageRecipes = new PageImpl<>(favoriteRecipes);

        Mockito.when(recipeService.findFavoritesRecipes(anyInt(),anyInt(),anyString()))
                .thenReturn(pageRecipes);

        mockMvc.perform(get("/recipes/favorites")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .header("Authorization",accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void 인기검색어_순위() throws Exception {
        List<SearchKeyword> top10PopularKeywords = new ArrayList<>();

        Mockito.when(recipeService.findTop10PopularKeywords())
                .thenReturn(top10PopularKeywords);

        mockMvc.perform(get("/recipes/top10"))
                .andExpect(status().isOk());
    }

    @Test
    void 프론트_네임_캐싱() throws Exception {
        List<Recipe> names = new ArrayList<>();
        List<RecipeDto.Names> namesList = new ArrayList<>();

        Mockito.when(recipeService.findNames())
                .thenReturn(names);
        Mockito.when(mapper.recipeToNames(any()))
                .thenReturn(namesList);

        mockMvc.perform(get("/recipes/name"))
                .andExpect(status().isOk());
    }

    @Test
    void 추천_레시피_조리방법_종류() throws Exception {
        String way = "볶기";
        String part = "반찬";
        List<Recipe> recommendRecipes = new ArrayList<>();
        List<RecipeDto.ListResponse> listResponses = new ArrayList<>();

        Mockito.when(recipeService.findRecommendRecipes(anyString(),anyString()))
                .thenReturn(recommendRecipes);
        Mockito.when(mapper.recipeToListResponse(any()))
                .thenReturn(listResponses);

        mockMvc.perform(get("/recipes/recommend")
                        .param("way",way)
                        .param("part",part))
                .andExpect(status().isOk());

    }
}