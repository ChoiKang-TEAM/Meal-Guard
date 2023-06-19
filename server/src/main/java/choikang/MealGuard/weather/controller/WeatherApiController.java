package choikang.MealGuard.weather.controller;

import choikang.MealGuard.weather.dto.WeatherDto;
import choikang.MealGuard.weather.entity.Region;
import choikang.MealGuard.weather.entity.Weather;
import choikang.MealGuard.weather.service.WeatherApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
@Slf4j
public class WeatherApiController {

    private final WeatherApiService weatherApiService;

    @GetMapping
    @Transactional
    public ResponseEntity getRegionWeather(@RequestParam(defaultValue = "1") Long regionId) {

        WeatherDto.Response regionWeather = weatherApiService.getRegionWeather(regionId);

        return new ResponseEntity<>(regionWeather, HttpStatus.OK);
    }
}
