package choikang.MealGuard.snack.controller;

import choikang.MealGuard.snack.entity.Snack;
import choikang.MealGuard.snack.mapper.SnackMapper;
import choikang.MealGuard.snack.service.SnackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/snacks")
@RequiredArgsConstructor
public class SnackController {

    private final SnackService snackService;
    private final SnackMapper mapper;

    @GetMapping
    public ResponseEntity getRandomSnack() {
        Snack randomSnack = snackService.findRandomSnack();
        return new ResponseEntity<>(mapper.snackToResponse(randomSnack), HttpStatus.OK);
    }

}
