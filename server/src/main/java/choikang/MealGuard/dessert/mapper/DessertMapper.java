package choikang.MealGuard.dessert.mapper;

import choikang.MealGuard.dessert.dto.DessertDto;
import choikang.MealGuard.dessert.entity.Dessert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DessertMapper {

    DessertDto.Response dessertToDessertResponse(Dessert dessert);

}
