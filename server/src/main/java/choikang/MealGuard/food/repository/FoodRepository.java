package choikang.MealGuard.food.repository;

import choikang.MealGuard.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> {
}
