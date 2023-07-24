package choikang.MealGuard.user.service;

import choikang.MealGuard.global.exception.BusinessLogicException;
import choikang.MealGuard.global.exception.ExceptionCode;
import choikang.MealGuard.helper.JwtUtils;
import choikang.MealGuard.user.entity.RecentRecipe;
import choikang.MealGuard.user.entity.User;
import choikang.MealGuard.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public void createUser(String user_seq){
        User user = new User(user_seq);
        userRepository.save(user);
    }

    public List<RecentRecipe> getRecentRecipes(String token){
        Claims claims = jwtUtils.parseToken(token.substring(7));
        String id = (String) claims.get("userSeq");

        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        user.limitRecentRecipesSize();
        List<RecentRecipe> recentRecipes = user.getRecentRecipes();

        return recentRecipes;
    }
}
