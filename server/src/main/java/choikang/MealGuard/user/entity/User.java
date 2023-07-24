package choikang.MealGuard.user.entity;

import choikang.MealGuard.recipe.entity.Favorite;
import choikang.MealGuard.recipe.entity.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private String user_seq;

    public User(String user_seq) {
        this.user_seq = user_seq;
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<RecentRecipe> recentRecipes;

    // 양방향 관계
    public void addRecentRecipe(Recipe recipe) {
        RecentRecipe recentRecipe = new RecentRecipe(this, recipe);
        recentRecipes.add(recentRecipe);
    }

    // 최근 조회 레시피 목록의 사이즈를 제한하는 메서드
    public void limitRecentRecipesSize() {
        int maxSize = 10;
        if (recentRecipes.size() > maxSize) {
            recentRecipes.subList(0, recentRecipes.size() - maxSize).clear();
        }
    }
}
