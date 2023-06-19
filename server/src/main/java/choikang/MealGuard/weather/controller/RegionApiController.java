package choikang.MealGuard.weather.controller;

import choikang.MealGuard.weather.entity.Region;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class RegionApiController {

    @Value("${resources.location}")
    private String resourceLocation;

    private final EntityManager em;

    @PostMapping("/region")
    @Transactional
    public ResponseEntity<String> resetRegionList() {
        String fileLocation = resourceLocation + "/init/regionList.csv";
        Path path = Paths.get(fileLocation); // Path 클래스는 File 클래스와 비교하여 개선된 파일 및 디렉토리 경로를 나타내는 Java API. Path 클래스는 Java 7에서 소개되었으며, 파일 시스템 경로를 나타내는 데 보다 향상된 기능을 제공
        URI uri = path.toUri(); // toUri() 메서드는 Path 객체를 URI 객체로 변환할 때 file://을 접두사로 사용하는 경로를 생성

        try (BufferedReader br = new BufferedReader(new InputStreamReader( // InputStreamReader를 사용하여 UrlResource의 입력 스트림을 BufferedReader로 래핑. 이렇게 하면 효율적으로 파일을 읽을 수 있다.
                new UrlResource(uri).getInputStream()))) //UrlResource 클래스를 사용하여 URI 객체를 기반으로 UrlResource 인스턴스를 생성. UrlResource는 주어진 URL 또는 URI를 나타내는 Resource 인터페이스의 구현체 , getInputStream() 메소드를 호출하여 UrlResource의 입력 스트림을 얻는다. 이는 BufferedReader에 전달하여 파일을 읽을 수 있도록 한다.
        {
            String line = br.readLine(); // BufferedReader를 사용하여 첫 번째 줄을 읽고 line 변수에 할당. 파일의 헤더를 읽어 온다.
            while ((line = br.readLine()) != null) {
                String[] splits = line.split(",");
                em.persist(new Region(Long.parseLong(splits[0]), splits[1], splits[2],
                        Integer.parseInt(splits[3]), Integer.parseInt(splits[4])));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류");
        }
        return ResponseEntity.ok("초기화 성공");
    }

}