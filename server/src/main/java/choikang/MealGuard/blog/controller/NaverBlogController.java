package choikang.MealGuard.blog.controller;

import choikang.MealGuard.blog.component.NaverBlogSearchApi;
import choikang.MealGuard.blog.dto.NaverBlogDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/naverblog")
@RequiredArgsConstructor
public class NaverBlogController {

    private final NaverBlogSearchApi api;

    @GetMapping
    public ResponseEntity getFood(@RequestParam String food) throws JsonProcessingException {
        List<NaverBlogDto.Response> blogPosts = api.search(food);

        return new ResponseEntity<>(blogPosts, HttpStatus.OK);
    }


}
