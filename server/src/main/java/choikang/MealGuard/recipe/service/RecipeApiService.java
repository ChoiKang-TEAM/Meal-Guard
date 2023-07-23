package choikang.MealGuard.recipe.service;

import choikang.MealGuard.recipe.entity.Recipe;
import choikang.MealGuard.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RecipeApiService {

    private final RecipeRepository recipeRepository;

    public void getRecipeList() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.foodsafetykorea.go.kr/api/13bb56f08d29451da3b1/COOKRCP01/json/1/1000");
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String data = sb.toString();

        JSONObject jObject = new JSONObject(data);
        JSONObject response = jObject.getJSONObject("COOKRCP01");
        JSONArray jArray = response.getJSONArray("row");



        for(int i = 0; i < jArray.length(); i++) {
            JSONObject obj = jArray.getJSONObject(i);

            String RCP_SEQ = obj.get("RCP_SEQ").toString();
            String RCP_NM = obj.get("RCP_NM").toString();
            String RCP_WAY2 = obj.get("RCP_WAY2").toString();
            String RCP_PAT2 = obj.get("RCP_PAT2").toString();
            String INFO_WGT = obj.get("INFO_WGT").toString();
            Double INFO_ENG = Double.valueOf(obj.get("INFO_ENG").toString());
            Double INFO_CAR = Double.valueOf(obj.get("INFO_CAR").toString());
            Double INFO_PRO = Double.valueOf(obj.get("INFO_PRO").toString());
            Double INFO_FAT = Double.valueOf(obj.get("INFO_FAT").toString());
            Double INFO_NA = Double.valueOf(obj.get("INFO_NA").toString());
            String HASH_TAG = obj.get("HASH_TAG").toString();
            String ATT_FILE_NO_MK = obj.get("ATT_FILE_NO_MK").toString();
            String RCP_PARTS_DTLS = obj.get("RCP_PARTS_DTLS").toString();
            String RCP_NA_TIP = obj.get("RCP_NA_TIP").toString();
            String MANUAL_IMG01 = obj.get("MANUAL_IMG01").toString();
            String MANUAL01 = obj.get("MANUAL01").toString();
            String MANUAL_IMG02 = obj.get("MANUAL_IMG02").toString();
            String MANUAL02 = obj.get("MANUAL02").toString();
            String MANUAL_IMG03 = obj.get("MANUAL_IMG03").toString();
            String MANUAL03 = obj.get("MANUAL03").toString();
            String MANUAL_IMG04 = obj.get("MANUAL_IMG04").toString();
            String MANUAL04 = obj.get("MANUAL04").toString();
            String MANUAL_IMG05 = obj.get("MANUAL_IMG05").toString();
            String MANUAL05 = obj.get("MANUAL05").toString();
            String MANUAL_IMG06 = obj.get("MANUAL_IMG06").toString();
            String MANUAL06 = obj.get("MANUAL06").toString();
            String MANUAL_IMG07 = obj.get("MANUAL_IMG07").toString();
            String MANUAL07 = obj.get("MANUAL07").toString();
            String MANUAL_IMG08 = obj.get("MANUAL_IMG08").toString();
            String MANUAL08 = obj.get("MANUAL08").toString();
            String MANUAL_IMG09 = obj.get("MANUAL_IMG09").toString();
            String MANUAL09 = obj.get("MANUAL09").toString();
            String MANUAL_IMG10 = obj.get("MANUAL_IMG10").toString();
            String MANUAL10 = obj.get("MANUAL10").toString();
            String MANUAL_IMG11 = obj.get("MANUAL_IMG11").toString();
            String MANUAL11 = obj.get("MANUAL11").toString();
            String MANUAL_IMG12 = obj.get("MANUAL_IMG12").toString();
            String MANUAL12 = obj.get("MANUAL12").toString();
            String MANUAL_IMG13 = obj.get("MANUAL_IMG13").toString();
            String MANUAL13 = obj.get("MANUAL13").toString();
            String MANUAL_IMG14 = obj.get("MANUAL_IMG14").toString();
            String MANUAL14 = obj.get("MANUAL14").toString();
            String MANUAL_IMG15 = obj.get("MANUAL_IMG15").toString();
            String MANUAL15 = obj.get("MANUAL15").toString();
            String MANUAL_IMG16 = obj.get("MANUAL_IMG16").toString();
            String MANUAL16 = obj.get("MANUAL16").toString();
            String MANUAL_IMG17 = obj.get("MANUAL_IMG17").toString();
            String MANUAL17 = obj.get("MANUAL17").toString();
            String MANUAL_IMG18 = obj.get("MANUAL_IMG18").toString();
            String MANUAL18 = obj.get("MANUAL18").toString();
            String MANUAL_IMG19 = obj.get("MANUAL_IMG19").toString();
            String MANUAL19 = obj.get("MANUAL19").toString();
            String MANUAL_IMG20 = obj.get("MANUAL_IMG20").toString();
            String MANUAL20 = obj.get("MANUAL20").toString();

            Recipe recipe = new Recipe(Long.parseLong(RCP_SEQ), RCP_NM, RCP_WAY2, RCP_PAT2, INFO_WGT, INFO_ENG, INFO_CAR, INFO_PRO, INFO_FAT, INFO_NA, HASH_TAG, ATT_FILE_NO_MK, RCP_PARTS_DTLS, RCP_NA_TIP,
                    MANUAL_IMG01, MANUAL01, MANUAL_IMG02, MANUAL02, MANUAL_IMG03, MANUAL03, MANUAL_IMG04, MANUAL04, MANUAL_IMG05, MANUAL05, MANUAL_IMG06, MANUAL06, MANUAL_IMG07,
                    MANUAL07, MANUAL_IMG08, MANUAL08, MANUAL_IMG09, MANUAL09, MANUAL_IMG10, MANUAL10, MANUAL_IMG11, MANUAL11, MANUAL_IMG12, MANUAL12, MANUAL_IMG13, MANUAL13, MANUAL_IMG14, MANUAL14,
                    MANUAL_IMG15, MANUAL15, MANUAL_IMG16, MANUAL16, MANUAL_IMG17, MANUAL17, MANUAL_IMG18, MANUAL18, MANUAL_IMG19, MANUAL19, MANUAL_IMG20, MANUAL20);

            recipeRepository.save(recipe);
        }
    }
}
