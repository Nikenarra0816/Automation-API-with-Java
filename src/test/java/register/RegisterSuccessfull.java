package register;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import share.BaseTest;

import java.io.File;

import static io.restassured.RestAssured.given;

public class RegisterSuccessfull extends BaseTest {
    @Test
    public void register200(){
        RequestSpecification request = given();
        JSONObject params = new JSONObject();
        params.put("email", "eve.holt@reqres.in");
        params.put("password", "pistol");

        request.body(params.toString());

        request.header("Content-Type", "application/json");
        Response response = request.post("/api/register");
        response.then().assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/resources/RegisterSuccessfull.json")));
        System.out.println(response.asString());
    }
}
