package RestAssured;

import RestAssured.Model.request.SuccessfulAuthRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class EndPoints {

    public static Response getAuthCodeRoute(SuccessfulAuthRequest successfulAuthRequest) {
        RestAssured.baseURI = "https://ayco69dbm3.execute-api.us-east-1.amazonaws.com/singular_qa";

        RequestSpecification request = RestAssured.given();
        request.given()
                .contentType("application/json")
                .when();
        Response response = request.body(successfulAuthRequest).post("/authorize");

        Assert.assertEquals(response.statusCode(),200);

        return response;
    }

    public static Response getUserInfoRoute(String AuthorizationToken) {
        RestAssured.baseURI = "https://ayco69dbm3.execute-api.us-east-1.amazonaws.com/singular_qa";

        RequestSpecification request = RestAssured.given();
        request.given()
                .contentType("application/json")
                .header("Authorization",AuthorizationToken)
                .when();
        Response response = request.get("/info");

        Assert.assertEquals(response.statusCode(),200);

        return response;
    }

}
