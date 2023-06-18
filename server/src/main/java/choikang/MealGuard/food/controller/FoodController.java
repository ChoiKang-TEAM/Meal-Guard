package choikang.MealGuard.food.controller;

import choikang.MealGuard.food.entity.Food;
import choikang.MealGuard.food.mapper.FoodMapper;
import choikang.MealGuard.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    private final FoodMapper foodMapper;

    @GetMapping
    public ResponseEntity getFood(){

        Food food = foodService.findFood();

        return new ResponseEntity<>(foodMapper.foodToFoodResponseDto(food), HttpStatus.OK);
    }

}
