package choikang.MealGuard.dessert.service;

import choikang.MealGuard.dessert.entity.Dessert;
import choikang.MealGuard.dessert.erpository.DessertRepository;
import choikang.MealGuard.food.entity.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class DessertService {
    private final DessertRepository dessertRepository;

    public Dessert findRandomDessert(){

        List<Dessert> desserts = dessertRepository.findAll();

        if(!desserts.isEmpty()){
            Random rand = new Random();
            Dessert randomDessert = desserts.get(rand.nextInt(desserts.size()));
            return randomDessert;
        }

        return null;

    }
}
