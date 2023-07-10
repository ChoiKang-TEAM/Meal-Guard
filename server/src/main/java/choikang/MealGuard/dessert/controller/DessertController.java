package choikang.MealGuard.dessert.controller;

import choikang.MealGuard.dessert.entity.Dessert;
import choikang.MealGuard.dessert.mapper.DessertMapper;
import choikang.MealGuard.dessert.service.DessertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/desserts")
@RequiredArgsConstructor
public class DessertController {

    private final DessertService dessertService;

    private final DessertMapper mapper;

    @GetMapping
    public ResponseEntity getRandomDessert() {
        Dessert dessert = dessertService.findRandomDessert();


        return new ResponseEntity(mapper.dessertToDessertResponse(dessert), HttpStatus.OK);
    }
}
