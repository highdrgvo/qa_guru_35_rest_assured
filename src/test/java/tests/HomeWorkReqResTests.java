package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.lombok.LoginBodyApiKeyLombokModel;
import models.lombok.LoginBodyLombokModel;
import models.lombok.UserProfileResponseLombokModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.UserProfileSpec.userProfileReqSpec;
import static specs.UserProfileSpec.userProfileResSpec;

public class HomeWorkReqResTests {

    @Test
    void checkLastNameUserWithId2() {

        LoginBodyApiKeyLombokModel apiKey = new LoginBodyApiKeyLombokModel();
        apiKey.setApiKey("reqres-free-v1");

        UserProfileResponseLombokModel response = given(userProfileReqSpec)
                .header("x-api-key", apiKey.getApiKey())
        .when()
                .get()
        .then()
                .spec(userProfileResSpec)
                .extract().as(UserProfileResponseLombokModel.class);

        assertEquals("Weaver", response.getData().getLastName());

    }
}
