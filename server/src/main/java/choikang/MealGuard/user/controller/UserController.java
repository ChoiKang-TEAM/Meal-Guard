package choikang.MealGuard.user.controller;

import choikang.MealGuard.user.dto.RecentRecipeResponse;
import choikang.MealGuard.user.entity.RecentRecipe;
import choikang.MealGuard.user.mapper.RecentRecipeMapper;
import choikang.MealGuard.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final RecentRecipeMapper recentRecipeMapper;
    @PostMapping("/create")
    public ResponseEntity<String> getUserId(@RequestBody String user_seq){
        userService.createUser(user_seq);

        return ResponseEntity.ok("유저 저장 완료");
    }

    @GetMapping("/recent-recipes")
    public ResponseEntity getRecentRecipes(@RequestHeader("Authorization") String token){
        List<RecentRecipe> recentRecipes = userService.getRecentRecipes(token);

        List<RecentRecipeResponse> recentRecipeResponses = recentRecipeMapper.recentRecipesToResponses(recentRecipes);
        Collections.reverse(recentRecipeResponses);

        return new ResponseEntity(recentRecipeResponses, HttpStatus.OK);
    }

}
