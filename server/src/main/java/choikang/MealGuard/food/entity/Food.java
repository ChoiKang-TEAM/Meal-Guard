package choikang.MealGuard.food.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOOD_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String kcal;

    @OneToMany(mappedBy = "food",cascade = CascadeType.ALL)
    private List<Time> times = new ArrayList<>();

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<Season> seasons = new ArrayList<>();
}
