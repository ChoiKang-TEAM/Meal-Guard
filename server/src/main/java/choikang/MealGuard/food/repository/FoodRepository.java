package choikang.MealGuard.food.repository;

import choikang.MealGuard.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Long> {


    List<Food> findAllByTimesHours(String hours);

    List<Food> findAllByTasteAndTimesHours(String taste,String hours);

    List<Food> findAllBySeasonsName(String name);

    List<Food> findAllByTasteAndSeasonsName(String taste, String name);
}
