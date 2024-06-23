package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import utilities.CreateRequest;
import utilities.MyConfig;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import endpoints.endPointManager;

public class Login {

		private String loginEndpoint;
	    private String loginPayload;
	    private Response response;
	    static String expectedErrorMessage;

	    @Given("I have login endpoint")
	    public void iHaveLoginEndpointAndLoginPayload() {
	        //  the API endpoint for login is stored in EndpointManager
	        loginEndpoint = endPointManager.loginEndpoint();
	        
	    }

	    @When("I send a POST request with {string} & {string}")
	    public void iSendPOSTRequestWithUserNamePassword(String userName, String password) throws IOException {
	        // Build JSON payload dynamically
	    	loginPayload = CreateRequest.createLoginPayload(userName,password); 

	        // Send POST request and store response
	        response = given()
	            .contentType(ContentType.JSON)
	            .body(loginPayload)
	        .when()
	            .post(loginEndpoint)
	        .then()
	            .extract()
	            .response();
	    }

	    @Then("the status code should be {int}")
	    public void verifyStatusCode(int expectedStatusCode) {
	        response.then().statusCode(expectedStatusCode);
	    }

	    @Then("the response should contain a token")
	    public void verifyResponseContainsToken() {
	        response.then().body("token", notNullValue());
	        
	     // Extract specific string using JSONPath or other methods
	        String actualToken = response.path("token");
	        MyConfig.setProperty("token",actualToken );
	    }
	    
	    @Then("the response should contain an errorMessage")
	    public void verifyResponseContainsErrorMessage() {
	    	
	        if(response.getStatusCode() == 400) {
	        	
	        	expectedErrorMessage = "username and password are not provided in JSON format";
	        	
	        }else if(response.getStatusCode() == 401) {
	        	
	        	expectedErrorMessage = "username or password is incorrect";
	        }
	        
        	response.then()
            .assertThat()
            .body(containsString(expectedErrorMessage));
	    }
	
}
