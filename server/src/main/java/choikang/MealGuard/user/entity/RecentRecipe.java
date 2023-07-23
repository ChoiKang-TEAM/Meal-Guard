package choikang.MealGuard.user.entity;

import choikang.MealGuard.recipe.entity.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class RecentRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public RecentRecipe(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
    }
}
