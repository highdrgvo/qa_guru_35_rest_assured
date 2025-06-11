package tests;

import models.lombok.LoginBodyApiKeyLombokModel;
import models.lombok.UserProfileResponseLombokModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.UserProfileSpec.userProfileReqSpec;
import static specs.UserProfileSpec.userProfileResSpec;

public class HomeWorkReqResTests {

    @Test
    void checkLastNameUserWithId2() {

        LoginBodyApiKeyLombokModel apiKey = new LoginBodyApiKeyLombokModel();
        apiKey.setApiKey("reqres-free-v1");

        UserProfileResponseLombokModel response = step("Make a request", () ->
        given(userProfileReqSpec)
                .header("x-api-key", apiKey.getApiKey())
        .when()
                .get()
        .then()
                .spec(userProfileResSpec)
                .extract().as(UserProfileResponseLombokModel.class));

        step("Check that the last_name is Weaver", () ->
        assertEquals("Weaver", response.getData().getLastName()));

        // Если внутри лямбды нужно выполнить больше одного выражения, обязательно используйте {}:
        // step("Multi-line example", () -> {
        //    System.out.println("Начало шага");
        //    assertEquals("Weaver", response.getData().getLastName());
        //    System.out.println("Проверка завершена");
        //});
        // В вашем коде скобки не требуются, потому что:
        // Каждый step() содержит только один оператор (либо запрос, либо assertEquals).

    }
}
