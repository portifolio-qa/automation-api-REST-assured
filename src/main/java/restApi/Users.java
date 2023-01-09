package restApi;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class Users {

    String uri = "https://reqres.in/api/users";
    Data data;

    @BeforeMethod
    public void setup(){
        data = new Data();
    }

    @Test
    public void createUser() throws IOException {

        String jsonBody = data.lerJson("data/users.json");

        String userId =
                given()
                        .contentType("application/json")
                        .log().all()
                        .body(jsonBody)
                        .when()
                        .post(uri)
                        .then()
                        .log().all()
                        .statusCode(201)
                        .body("code", is(201))
                        .body("type", is("unknown"))
                        .extract()
                        .path("message")
                ;

        System.out.println("O userID é " + userId);
    }


}



