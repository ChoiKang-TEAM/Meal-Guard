package choikang.MealGuard.food.service;

import choikang.MealGuard.food.entity.Food;
import choikang.MealGuard.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public Food findFoodBuTime(){
        LocalTime currentTime =  LocalTime.now();

        String timezone = "";

        if(currentTime.isAfter(LocalTime.of(5,0)) && currentTime.isBefore(LocalTime.of(11,0))) timezone = "아침";
        else if(currentTime.isAfter(LocalTime.of(11,0)) && currentTime.isBefore(LocalTime.of(17,0))) timezone = "점심";
        else if(currentTime.isAfter(LocalTime.of(17,0)) && currentTime.isBefore(LocalTime.of(22,0))) timezone = "저녁";
        else  timezone = "야식";

        List<Food> foods = foodRepository.findAllByHours(timezone);

        if(!foods.isEmpty()){
            Random rand = new Random();
            Food randomFood = foods.get(rand.nextInt(foods.size()));
            return randomFood;
        }

        return null;
    }
}
