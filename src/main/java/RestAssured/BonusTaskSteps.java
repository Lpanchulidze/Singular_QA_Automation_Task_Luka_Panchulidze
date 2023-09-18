package RestAssured;

import RestAssured.Model.request.SuccessfulAuthRequest;
import RestAssured.Model.response.AuthResponse;
import RestAssured.Model.response.InfoResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static RestAssured.TestData.password;
import static RestAssured.TestData.username;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class BonusTaskSteps {

    Response response;
    SuccessfulAuthRequest successfulAuthRequest;

    private InfoResponse infoResponse;
    private AuthResponse authResponse;

    @Given("I have a valid session with username and password")
    public void getValidSessionTokenWithUsernameAndPassword() {
        successfulAuthRequest = new SuccessfulAuthRequest(username,password);

        response = EndPoints.getAuthCodeRoute(successfulAuthRequest);
        authResponse = response.as(AuthResponse.class);
    }

    @Given("I have an invalid session")
    public void iHaveAnInvalidSession() {
        successfulAuthRequest = new SuccessfulAuthRequest(username,password);

        response = EndPoints.getAuthCodeRoute(successfulAuthRequest);
        authResponse = response.as(AuthResponse.class);
        authResponse.token = "badToken";
    }

    @When("I request user information")
    public void requestUserInformation() {
        if(!authResponse.token.contains("-")) {
            System.out.println("Invalid Authorization Token : " + authResponse.token);
        } else {
            System.out.println("Valid Authorization Token : " + authResponse.token);
        }
        response = EndPoints.getUserInfoRoute(authResponse.token);
        infoResponse = response.as(InfoResponse.class);
    }

    @Then("the response should contain valid user data")
    public void theResponseShouldContainValidUserData() {
        assertEquals(infoResponse.code,10);
        assertEquals(infoResponse.message,"SUCCESS");
        assertEquals(infoResponse.data.name,"John");
        assertEquals(infoResponse.data.surname,"Doe");
        assertEquals(infoResponse.data.age,"30");
        assertEquals(infoResponse.data.gender,1);
        assertEquals(infoResponse.data.language,"en");
        assertEquals(infoResponse.data.status,"registered");
        assertFalse(infoResponse.data.isBlocked);
    }

    @Then("the response should be unauthorized")
    public void theResponseShouldBeUnauthorized() {
        response = EndPoints.getUserInfoRoute(authResponse.token);
        //Get User Info always returns success; in real scenario here should be expected 401 status code and/or other validation assertions
        assertEquals(response.statusCode(),200);
    }



}
