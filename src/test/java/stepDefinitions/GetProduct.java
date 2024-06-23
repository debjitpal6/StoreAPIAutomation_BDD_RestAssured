package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import endpoints.endPointManager;

import static io.restassured.RestAssured.*;

public class GetProduct{

	private String getAllProductEndpoint;
    private Response response;

    @Given("I have GET product endpoint")
    public void iHaveGETProductEndpoint() {
    	getAllProductEndpoint = endPointManager.productEndpoint(); // Replace with your actual method to get endpoint
    }

    @When("the user makes a GET request to the endpoint")
    public void theUserMakesGETRequestToEndpoint() {
        response = get(getAllProductEndpoint); // get request
    }

    @Then("response status code should be {int}")
    public void responseStatusCodeShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain a list of products with attribute ID, title, price etc.")
    public void theResponseShouldContainListOfProductsWithAttributes() {
        // Assuming you have a Product class mapped using Jackson
        response.then()
                .body("$", hasSize(greaterThan(0))) // Ensure products array is not empty
                .body("[0].id", notNullValue()) // Check if first product has an ID
                .body("[0].title", notNullValue()) // Check if first product has a title
                .body("[0].price", notNullValue()); // Check if first product has a price
    }
    
    @When("the user makes a GET request to the endpoint with {int}")
    public void theUserMakesGETRequestToEndpointWithProductID(int productID) {
        response = get(getAllProductEndpoint+"/"+productID); // get request
    }
    
    @Then("the response should contain the product details")
    public void theResponseShouldContainTheProductDetails() {
        // Assuming you have a Product class mapped using Jackson
        response.then()
                .body("$", hasSize(greaterThan(0))) // Ensure products array is not empty
                .body("[0].id", notNullValue()) // Check if first product has an ID
                .body("[0].title", notNullValue()) // Check if first product has a title
                .body("[0].price", notNullValue()); // Check if first product has a price
    }
}

