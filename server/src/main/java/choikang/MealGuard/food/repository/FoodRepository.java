package choikang.MealGuard.food.repository;

import choikang.MealGuard.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Long> {

    @Query("select f from Food f join f.times t where t.hours = :hours")
    List<Food> findAllByHours(@Param("hours") String hours);

    @Query("select f from Food f join f.seasons s where s.name = :name")
    List<Food> findAllByName(@Param("name") String name);
}
