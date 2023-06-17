package choikang.MealGuard.food.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TIME_ID")
    private Long id;

    @Column(nullable = false)
    private String hours;

    @ManyToOne
    @JoinColumn(name = "FOOD_ID")
    private Food food;

}
