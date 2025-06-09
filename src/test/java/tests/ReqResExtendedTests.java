package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.LoginBodyApiKeyLombokModel;
import models.lombok.LoginBodyLombokModel;
import models.lombok.LoginResponseLombokModel;
import models.pojo.LoginBodyApiKeyModel;
import models.pojo.LoginBodyModel;
import models.pojo.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.*;
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
    void successfulLoginPojoTest(){

        LoginBodyModel authData = new LoginBodyModel();

        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel response = given()
                // LoginResponseModel response сохраняет Java-объект (в джава объект завернули на этапе extract())
                // в переменную для последующего использования.
                .body(authData)
                .header("x-api-key", "reqres-free-v1")
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
    void successfulLoginLombokTest(){

        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        LoginBodyApiKeyLombokModel apiKey = new LoginBodyApiKeyLombokModel();

        // Чтобы lombok работал, надо установить плагин в идее

        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");
        apiKey.setApiKey("reqres-free-v1");

        LoginResponseLombokModel response = given()
                .body(authData)
                .header("x-api-key", apiKey.getApiKey())
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

    @Test
    void successfulLoginAllureTest(){

        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        LoginBodyApiKeyLombokModel apiKey = new LoginBodyApiKeyLombokModel();

        // Чтобы lombok работал, надо установить плагин в идее

        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");
        apiKey.setApiKey("reqres-free-v1");

        LoginResponseLombokModel response = given()
                .filter(new AllureRestAssured())
                .log().headers()
                .log().uri()
                .log().body()
                .body(authData)
                .header("x-api-key", apiKey.getApiKey())
                .contentType(JSON)

        .when()
                .post("https://reqres.in/api/login")
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());

    }

    @Test
    void successfulLoginCustomAllureTest(){

        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        LoginBodyApiKeyLombokModel apiKey = new LoginBodyApiKeyLombokModel();


        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");
        apiKey.setApiKey("reqres-free-v1");

        LoginResponseLombokModel response = given()
                .filter(withCustomTemplates())
                .log().headers()
                .log().uri()
                .log().body()
                .body(authData)
                .header("x-api-key", apiKey.getApiKey())
                .contentType(JSON)

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
