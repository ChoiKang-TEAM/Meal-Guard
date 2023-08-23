package choikang.MealGuard.recipe.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import choikang.MealGuard.recipe.dto.FavoriteRecipeResponse;
import choikang.MealGuard.recipe.dto.RecipeDto;
import choikang.MealGuard.recipe.entity.Recipe;
import choikang.MealGuard.recipe.mapper.RecipeMapper;
import choikang.MealGuard.recipe.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
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

        when(recipeService.findRecipes(anyString(), anyString(), anyInt(), anyInt())).thenReturn(mockRecipePage);

        List<RecipeDto.ListResponse> mockListResponses = new ArrayList<>();

        when(mapper.recipeToListResponse(anyList())).thenReturn(mockListResponses);

        mockMvc.perform(get("/recipes")
                        .param("name", "닭가슴살")
                        .param("sortBy", "highProtein")
                        .param("page", "1")
                        .param("size", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray());

        verify(recipeService).findRecipes(eq("닭가슴살"), eq("highProtein"), eq(1), eq(10));
        verify(mapper).recipeToListResponse(eq(mockRecipes));
    }

    @Test
    void 레시피_상세_가져오기() throws Exception {
        long recipeId = 1L;
        RecipeDto.Response mockRecipe = new RecipeDto.Response();
        mockRecipe.setRecipeId(recipeId);
        String accessToken = "Bearer eyJhbGciOiJIUzUxMiJ9.NjA2NDUwOTc3LCJtZW1iZXJJZCI6Mjd9.1TvYDexLUkOkOsBksbS6dnyJ4Ig1m9LMdTJ2FzCdOW0GEEdM4S6MpLZTpMGZCa-BN9jnbC9htZljsi5e7Mc-OQ";

        Recipe recipe = new Recipe();
        when(recipeService.findRecipe(anyString(),anyLong())).thenReturn(recipe);
        when(mapper.recipeToResponse(any())).thenReturn(mockRecipe);

        mockMvc.perform(get("/recipes/" + recipeId)
                        .header("Authorization", accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(recipeService).findRecipe(eq(accessToken), eq(recipeId));
        verify(mapper).recipeToResponse(eq(recipe));

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
}