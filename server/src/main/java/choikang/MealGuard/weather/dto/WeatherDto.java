package choikang.MealGuard.weather.dto;

import choikang.MealGuard.weather.entity.Weather;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class WeatherDto {

    @Getter
    @Setter
    @Builder
    public static class Response{

        private String region;
        private Weather weather;

        private String message;
    }
}
