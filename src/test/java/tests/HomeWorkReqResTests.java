package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import models.lombok.LoginBodyApiKeyLombokModel;
import models.lombok.LoginBodyLombokModel;
import models.lombok.UserProfileResponseLombokModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeWorkReqResTests {

    @Test
    void checkLastNameUserWithId2() {

        baseURI = "https://reqres.in/";
        basePath = "/api/users/2";

        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        LoginBodyApiKeyLombokModel apiKey = new LoginBodyApiKeyLombokModel();

        apiKey.setApiKey("reqres-free-v1");

        UserProfileResponseLombokModel response = given()
                .filter(new AllureRestAssured())
                .log().headers()
                .log().method()
                .header("x-api-key", apiKey.getApiKey())
                .contentType(JSON)

        .when()
                .get()
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(UserProfileResponseLombokModel.class);

        assertEquals("Weaver", response.getData().getLastName());

    }
}
