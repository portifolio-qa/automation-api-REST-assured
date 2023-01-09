package apiTests;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;

public class Users {

    public String usersJson(String patchJson) throws IOException {

        return new String(Files.readAllBytes(Paths.get(patchJson)));
    }
    @Test
    public void createUser() throws IOException {
        String jsonBody = usersJson("src/test/resources/data/users.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)

        .when()
                .post("https://reqres.in/api/users")


        .then()
                .log().all()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                ;

    }
}