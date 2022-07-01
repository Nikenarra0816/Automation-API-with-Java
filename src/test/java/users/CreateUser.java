package users;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import share.BaseTest;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CreateUser extends BaseTest {
    @Test
    public void createUser(ITestContext context) {
        RequestSpecification request = given();
        JSONObject param = new JSONObject();
        param.put("name", "morpheus");
        param.put("job", "leader");

        request.body(param.toString());

        request.header("Content-type", "application/json");
        Response response = request.post("/api/users");

        response.then().assertThat()
                .statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchema
                        (new File("src/resources/CreateUser.json")
                        ));

        System.out.println(response.asString());
    }
}
