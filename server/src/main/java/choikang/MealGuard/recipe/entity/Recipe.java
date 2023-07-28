package choikang.MealGuard.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Recipe {

    @Id
    private Long recipeId;

    private String name;

    private String way; // 만드는 방법

    private String part; // 종류

    private String weight;
    private Double kcal;

    private Double carbohydrate; // 탄수화물

    private Double protein; // 단백질

    private Double fat; // 지방

    private Double sodium; // 나트륨

    private String hashtag;
    private String thumbnail;
    private String mainImage;

    @Column(length = 1000)
    private String information; // 재료 정보

    private String sodiumTip; // 저나트륨 조리법 팀

    private String manualImg01;
    private String manual01;
    private String manualImg02;
    private String manual02;
    private String manualImg03;
    private String manual03;
    private String manualImg04;
    private String manual04;
    private String manualImg05;
    private String manual05;
    private String manualImg06;
    private String manual06;
    private String manualImg07;
    private String manual07;
    private String manualImg08;
    private String manual08;
    private String manualImg09;
    private String manual09;
    private String manualImg10;
    private String manual10;
    private String manualImg11;
    private String manual11;
    private String manualImg12;
    private String manual12;
    private String manualImg13;
    private String manual13;
    private String manualImg14;
    private String manual14;
    private String manualImg15;
    private String manual15;
    private String manualImg16;
    private String manual16;
    private String manualImg17;
    private String manual17;
    private String manualImg18;
    private String manual18;
    private String manualImg19;
    private String manual19;
    private String manualImg20;
    private String manual20;

    public Recipe(Long recipeId, String name, String way, String part, String weight, Double kcal, Double carbohydrate, Double protein, Double fat, Double sodium, String hashtag,String thumbnail, String mainImage, String information, String sodiumTip, String manualImg01, String manual01, String manualImg02, String manual02, String manualImg03, String manual03, String manualImg04, String manual04, String manualImg05, String manual05, String manualImg06, String manual06, String manualImg07, String manual07, String manualImg08, String manual08, String manualImg09, String manual09, String manualImg10, String manual10, String manualImg11, String manual11, String manualImg12, String manual12, String manualImg13, String manual13, String manualImg14, String manual14, String manualImg15, String manual15, String manualImg16, String manual16, String manualImg17, String manual17, String manualImg18, String manual18, String manualImg19, String manual19, String manualImg20, String manual20) {
        this.recipeId = recipeId;
        this.name = name;
        this.way = way;
        this.part = part;
        this.weight = weight;
        this.kcal = kcal;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.hashtag = hashtag;
        this.thumbnail = thumbnail;
        this.mainImage = mainImage;
        this.information = information;
        this.sodiumTip = sodiumTip;
        this.manualImg01 = manualImg01;
        this.manual01 = manual01;
        this.manualImg02 = manualImg02;
        this.manual02 = manual02;
        this.manualImg03 = manualImg03;
        this.manual03 = manual03;
        this.manualImg04 = manualImg04;
        this.manual04 = manual04;
        this.manualImg05 = manualImg05;
        this.manual05 = manual05;
        this.manualImg06 = manualImg06;
        this.manual06 = manual06;
        this.manualImg07 = manualImg07;
        this.manual07 = manual07;
        this.manualImg08 = manualImg08;
        this.manual08 = manual08;
        this.manualImg09 = manualImg09;
        this.manual09 = manual09;
        this.manualImg10 = manualImg10;
        this.manual10 = manual10;
        this.manualImg11 = manualImg11;
        this.manual11 = manual11;
        this.manualImg12 = manualImg12;
        this.manual12 = manual12;
        this.manualImg13 = manualImg13;
        this.manual13 = manual13;
        this.manualImg14 = manualImg14;
        this.manual14 = manual14;
        this.manualImg15 = manualImg15;
        this.manual15 = manual15;
        this.manualImg16 = manualImg16;
        this.manual16 = manual16;
        this.manualImg17 = manualImg17;
        this.manual17 = manual17;
        this.manualImg18 = manualImg18;
        this.manual18 = manual18;
        this.manualImg19 = manualImg19;
        this.manual19 = manual19;
        this.manualImg20 = manualImg20;
        this.manual20 = manual20;
    }

    @OneToMany(mappedBy = "recipe")
    private List<Ingredient> ingredients;

    @OneToMany(mappedBy = "recipe")
    private List<Favorite> favorites;
}
