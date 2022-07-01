package users;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import share.BaseTest;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UpdateUser extends BaseTest {
    @Test
    public void updateUser(ITestContext context) {
        RequestSpecification request = given();
        JSONObject param = new JSONObject();
        param.put("name", "morpheus");
        param.put("job", "zion resident");

        request.body(param.toString());

        request.header("Content-type", "application/json");
        Response response = request.put("/api/users/2");

        response.then().assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema
                        (new File("src/resources/UpdateUser.json")
                        ));

        System.out.println(response.asString());
    }
}
