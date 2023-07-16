package choikang.MealGuard.user.controller;

import choikang.MealGuard.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> getUserId(@RequestBody String user_seq){
        userService.createUser(user_seq);

        return ResponseEntity.ok("유저 저장 완료");
    }
}
