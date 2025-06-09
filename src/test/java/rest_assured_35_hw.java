import io.restassured.path.json.JsonPath;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class rest_assured_35_hw {

    String url = "https://reqres.in";
    String authData = "{\n" +
            "    \"email\": \"eve.holt@reqres.in\",\n" +
            "    \"password\": \"cityslicka\"\n" +
            "}";

    @Test
    void getInfoOnNonexistingUserTest() {

        given()
                .body(authData)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
        .when()
                .get(url + "/api/users/39")
        .then()
                .log().status()
                .statusCode(404)
                .body(Matchers.anything()); // проверка пустого body
    }

    @Test
    void getInfoByUserWithId3(){

        given()
                .body(authData)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
        .when()
                .get(url + "/api/users/3")
        .then()
                .log().status()
                .statusCode(200)
                .log().body() // 18 цветов в объекте
                .body("data.id", is(3));
    }

    @Test
    void checkThatTotalofColorsAre6Test() {

        given()
                .body(authData)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
        .when()
                .get(url + "/api/unknown")
        .then()
                .log().status()
                .statusCode(200)
                .log().body()
                .body("data.size()", equalTo(6));

    }


}
