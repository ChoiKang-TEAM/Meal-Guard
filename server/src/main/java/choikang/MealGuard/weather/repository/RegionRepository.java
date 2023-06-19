package choikang.MealGuard.weather.repository;

import choikang.MealGuard.weather.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region,Long> {
}
