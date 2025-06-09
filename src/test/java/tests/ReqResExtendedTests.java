package tests;

import models.lombok.LoginBodyLombokModel;
import models.lombok.LoginResponseLombokModel;
import models.pojo.LoginBodyModel;
import models.pojo.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ReqResExtendedTests {

    @Test
    void successfulLoginBadPracticeTest(){
        String authData = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";

        given() // Дано:
                .body(authData)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
        .when()
                .post("https://reqres.in/api/login")

        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }

    @Test
    void successfulPojoLoginTest(){

        LoginBodyModel authData = new LoginBodyModel();

        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");
        authData.setHeaderApiKey("reqres-free-v1");

        LoginResponseModel response = given()
        // LoginResponseModel response сохраняет Java-объект (в джава объект завернули на этапе extract())
        // в переменную для последующего использования.
                .body(authData)
                .header("x-api-key", authData.getHeaderApiKey())
                .contentType(JSON)
                .log().uri()
        .when()
                .post("https://reqres.in/api/login")
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
        // .extract().as(LoginResponseModel.class) - преобразует JSON-ответ от сервера в Java-объект.
        // То есть объект был создан, но не сохранен.

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());

    }

    @Test
    void successfulLombokLoginTest(){

        LoginBodyLombokModel authData = new LoginBodyLombokModel();

        // Чтобы lombok работал, надо установить плагин в идее

        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");
        authData.setHeaderApiKey("reqres-free-v1");

        LoginResponseLombokModel response = given()
                .body(authData)
                .header("x-api-key", authData.getHeaderApiKey())
                .contentType(JSON)
                .log().uri()
        .when()
                .post("https://reqres.in/api/login")
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());

    }
}
