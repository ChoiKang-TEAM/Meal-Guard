package choikang.MealGuard.user.entity;

import choikang.MealGuard.recipe.entity.Favorite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites;
}
