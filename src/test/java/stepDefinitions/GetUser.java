package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import io.restassured.path.json.JsonPath;

public class GetUser {
	
	private String baseUrl = "https://reqres.in/api";
    private Response response;
    private JsonPath jsonPath; 
    private int userId;

    @Given("a user with ID {int}")
    public void givenUserId(int userId) {
        // This step is just for description purpose, no action is needed in this case
    	this.userId=userId;
        System.out.println("Given a user with ID: " + userId);
    }

    @When("a GET request is made to user {int}")
    public void whenGetRequestMade(int userId) {
        response = RestAssured.get(baseUrl + "/users/" + userId);
    }

    @Then("the response status code should be {int}")
    public void thenResponseStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("the response body should contain user information")
    public void thenResponseBodyContainsUserInfo() {
    	jsonPath = response.jsonPath();
    	int actualID = jsonPath.getInt("data.id");
        assertEquals(userId, actualID);
    }
    
    @Then("the response body should contain an error message")
    public void thenResponseBodyContainsErrorMessage() {
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.equals("{}"));
    }
	
}
