package tests;

import models.lombok.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.UserProfileSpec.*;

public class HomeWorkReqResTests {

    @Test
    @Tag("hw_rest_assured_models_specs_35")
    void checkLastNameUserWithId2Test() {

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

        step("The last_name is Weaver", () ->
        assertEquals("Weaver", response.getData().getLastName()));
    }

    @Test
    @Tag("hw_rest_assured_models_specs_35")
    void updateUserNameAndJobTest() {

        LoginBodyApiKeyLombokModel apiKey = new LoginBodyApiKeyLombokModel();
        apiKey.setApiKey("reqres-free-v1");

        UpdateUserProfileRequestLombokModel reqBody = new UpdateUserProfileRequestLombokModel();
        reqBody.setName("morpheus");
        reqBody.setJob("zion resident");

        UpdateUserProfileResponseLombokModel response = step("Make a request", () ->
                given(userProfileReqSpec)
                        .header("x-api-key", apiKey.getApiKey())
                        .body(reqBody)
                .when()
                        .patch()
                .then()
                        .spec(userProfileResSpec)
                        .extract().as(UpdateUserProfileResponseLombokModel.class));

        step("The name is" + " " + reqBody.getName(), () ->
                assertEquals(reqBody.getName(), response.getName()));

        step("The job is" + " " + reqBody.getJob(), () ->
                assertEquals(reqBody.getJob(), response.getJob()));
    }

    @Test
    @Tag("hw_rest_assured_models_specs_35")
    void checkThatUserEveHoltExistsTest() {

        LoginBodyApiKeyLombokModel apiKey = new LoginBodyApiKeyLombokModel();
        apiKey.setApiKey("reqres-free-v1");

        GetUserResponseLombokModel response = step("Make a request", () ->
                given(getUserProfileReqSpec)
                        .header("x-api-key", apiKey.getApiKey())
                        .when()
                        .get()
                        .then()
                        .spec(userProfileResSpec)
                        .extract().as(GetUserResponseLombokModel.class));

        step("User Eve Holt exists", () -> {

            String correctUserEmail = "eve.holt@reqres.in";
            GetUserResponseLombokModel.User userByEmail = response.getData().stream()
                    .filter(user -> correctUserEmail.equals(user.getEmail()))
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("User not found"));

            assertEquals("Eve", userByEmail.getFirstName());
            assertEquals("Holt", userByEmail.getLastName());
        });
    }
}
