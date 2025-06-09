import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class rest_assured_35_hw {

    @Test
    void getInfoOnNonexistingUserTest() {

        String url = "https://reqres.in/";
        String authData = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";

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
    void checkThatResponseBodyContainsSixColorTest(){


    }
}
