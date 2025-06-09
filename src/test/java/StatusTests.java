import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;


public class StatusTests {

    @Test
    void checkTotal20() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(5));
    }

    @Test
    void checkTotalWithResponseLogs() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .body("total", is(5));
    }

    @Test
    void checkTotalWithLogs() {
        given()
                .log().all() // можно сделать log.uri
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all() // можно сделать log.body
                .body("total", is(5));
    }

    @Test
    void checkStatusCodeWithLogs() {
        given()
                .log().uri()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(5));
    }

    @Test
    void checkBrowserVersionWithLogs() {
        given()
                .log().uri()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(5))
                .body("browsers.chrome", hasKey("128.0"));
    }


}