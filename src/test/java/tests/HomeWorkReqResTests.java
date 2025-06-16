package tests;

import models.lombok.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.UserProfileSpec.*;

public class HomeWorkReqResTests {

    @Test
    @Tag("home_work_rest_assured_models_specs_35")
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

        // Если внутри лямбды нужно выполнить больше одного выражения, обязательно используйте {}:
        // step("Multi-line example", () -> {
        //    System.out.println("Начало шага");
        //    assertEquals("Weaver", response.getData().getLastName());
        //    System.out.println("Проверка завершена");
        //});
        // В вашем коде скобки не требуются, потому что:
        // Каждый step() содержит только один оператор (либо запрос, либо assertEquals).

    }

    @Test
    @Tag("home_work_rest_assured_models_specs_35")
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
    @Tag("home_work_rest_assured_models_specs_35")
    void checkThatUserTobiasFunkeExistTest() {

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

//        Почему GetUserResponseLombokModel.User?
//        User — это вложенный класс внутри GetUserResponseLombokModel.
//        Java для обращения к вложенным статическим классам используется синтаксис ВнешнийКласс.ВложенныйКласс.
//        userByEmail - Это имя переменной, в которую будет сохранён результат поиска пользователя.
//        .filter(user -> targetEmail.equals(user.getEmail()))
//        filter — это промежуточная операция Stream API, которая оставляет в потоке только те элементы, которые соответствуют условию.
//        Условие: targetEmail.equals(user.getEmail()) — проверяет, совпадает ли email пользователя (user.getEmail()) с искомым (targetEmail).
//        .findFirst() - Это терминальная операция, которая возвращает первый элемент из отфильтрованного потока
//        Лямбда-выражение user -> ... означает: "для каждого пользователя user в потоке примени проверку".

        });
    }
}
