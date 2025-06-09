package tests;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class RestAssured35HomeWork {

    String url = "https://reqres.in";

    @Test
    @DisplayName("Get data on non-existing user")
    void getInfoOnNonexistingUserTest() {

        given()
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
    @DisplayName("Get data by user with id = 3")
    void getInfoByUserWithId3(){

        given()
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
    @DisplayName("Check that number of colors are 6")
    void checkThatNumberOfColorsAre6Test() {

        given()
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
                //.size() — это метод, а не поле. В Java/Kotlin/Groovy
                // (а RestAssured использует Groovy-подобный синтаксис для JSON Path)
                // размер коллекции получают через вызов метода size().
    }

    @Test
    @DisplayName("Check that the sky color is in the list of colors")
    void checkThatDataArrayHasAquaSkyColorTest() {

        given()
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
        .when()
                .get(url + "/api/unknown")
        .then()
                .log().status()
                .statusCode(200)
                .log().body()
                .body("data.name", hasItem(equalTo("aqua sky")));
    }

    @Test
    @DisplayName("Register in a new user")
    void registerNewUserTest() {

        String newUser = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

        given()
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .body(newUser)
        .when()
                .post(url + "/api/register")
        .then()
                .log().status()
                .statusCode(200)
                .log().body()
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }
}
