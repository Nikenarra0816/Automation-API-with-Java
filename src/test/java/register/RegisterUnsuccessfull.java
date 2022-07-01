package register;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;
import share.BaseTest;

import java.io.File;

import static io.restassured.RestAssured.given;

public class RegisterUnsuccessfull extends BaseTest {
    @Test
    public void register400(){
        RequestSpecification request = given();
        JSONObject params = new JSONObject();
        params.put("email", "sydney@fife");
        request.body(params.toString());

        request.header("Content-Type", "application/json");
        Response response = request.post("/api/register");
        response.then().assertThat()
                .statusCode(400)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/resources/RegisterUnsuccessfull.json")));
        System.out.println(response.asString());
    }

}
