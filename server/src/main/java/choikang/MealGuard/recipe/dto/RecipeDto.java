package choikang.MealGuard.recipe.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

public class RecipeDto {

    @Getter
    @Setter
    public static class ListResponse{

        private Long recipeId;

        private String name;

        private String way; // 만드는 방법

        private String part; // 종류

        private String hashtag;

        private String mainImage;
    }

    @Getter
    @Setter
    public static class Response{
        private Long recipeId;

        private String name;

        private String way; // 만드는 방법

        private String part; // 종류

        private String weight;
        private String kcal;

        private String carbohydrate; // 탄수화물

        private String protein; // 단백질

        private String fat; // 지방

        private String sodium; // 나트륨

        private String hashtag;

        private String mainImage;

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
    }

    @Getter
    @Setter
    public static class Names{
        private String name;
    }
}
