package users;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import share.BaseTest;

import java.io.File;


import static io.restassured.RestAssured.given;

public class SingleUserTest extends BaseTest {

    @Test
    public void single(ITestContext context) {

        //RestAssured.baseURI = "https://reqres.in";
        RequestSpecification request = given();
        request.param("page", 1);

        Response response = request.get("/api/users/"+context.getAttribute("id_user"));

        response.then().assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/resources/SingleUser.json")));

        System.out.println(response.asString());

    }
}
