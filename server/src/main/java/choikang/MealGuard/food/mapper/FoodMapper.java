package choikang.MealGuard.food.mapper;

import choikang.MealGuard.food.dto.FoodDto;
import choikang.MealGuard.food.entity.Food;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FoodMapper {

    FoodDto.Response foodToFoodResponseDto(Food food);

}
