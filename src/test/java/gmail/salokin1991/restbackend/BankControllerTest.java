package gmail.salokin1991.restbackend;

import gmail.salokin1991.restbackend.domain.UserInfo;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.with;

public class BankControllerTest {

    private RequestSpecification spec = with()
            .baseUri("http://localhost:8080")
            .basePath("/");

    @Test
    void bankControllerTest() {

        UserInfo[] userInfos = spec
                .get("user/getAll")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response()
                .as(UserInfo[].class);

        Stream.of(userInfos)
                .filter(userInfo -> userInfo.getUserName().equals("Dima"))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException());
                .peek(userInfo -> System.out.println(userInfo.getUserName()))
                .map(userInfo -> userInfo.toString())
                .collect(Collectors.toList());
    }
}
