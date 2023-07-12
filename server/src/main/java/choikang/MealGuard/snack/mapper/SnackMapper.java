package choikang.MealGuard.snack.mapper;

import choikang.MealGuard.snack.dto.SnackDto;
import choikang.MealGuard.snack.entity.Snack;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SnackMapper {

    SnackDto.Response snackToResponse(Snack snack);
}
