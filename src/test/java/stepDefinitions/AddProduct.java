package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import java.io.File;
//import java.io.IOException;

import endpoints.endPointManager;

import static io.restassured.RestAssured.*;

public class AddProduct {
	
		private String productEndpoint;
		private Response response;
	    private int productId;

	    @Given("I have product endpoint")
	    public void iHaveProductEndpoint() {
	    	 //  the API endpoint for login is stored in EndpointManager
	    	productEndpoint = endPointManager.productEndpoint();
	    }

	    @When("I send a POST request to add a new product")
	    public void iSendPOSTRequestToAddNewProduct() {
	       
	    	// Load JSON request body from file
	        File payloadFile = new File("src/test/resources/" + "payloads/addProduct.json");
	    	
	        // Send POST request to add a new product
	        response = given()
	                .contentType("application/json")
	                .body(payloadFile)
	            .when()
	                .post(productEndpoint);

	        // Extract product ID from response
	        productId = response.then().extract().path("id");
	    }

	    @Then("the product should be successfully added and I should get the productID")
	    public void productShouldBeSuccessfullyAdded() {
	        // Assert the status code
	        response.then().statusCode(200);

	        // Assert that productId is not null or empty
	        Assert.assertNotNull("Product ID should not be null", productId);

	        // Print or log the productId
	        System.out.println("Product ID: " + productId);
	    }
	
	
}
