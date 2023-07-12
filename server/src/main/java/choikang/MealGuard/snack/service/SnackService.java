package choikang.MealGuard.snack.service;

import choikang.MealGuard.dessert.entity.Dessert;
import choikang.MealGuard.snack.entity.Snack;
import choikang.MealGuard.snack.repository.SnackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class SnackService {
    private final SnackRepository snackRepository;


    public Snack findRandomSnack(){
        List<Snack> snacks = snackRepository.findAll();

        if(!snacks.isEmpty()){
            Random rand = new Random();
            Snack randomSnack = snacks.get(rand.nextInt(snacks.size()));
            return randomSnack;
        }

        return null;

    }



}
