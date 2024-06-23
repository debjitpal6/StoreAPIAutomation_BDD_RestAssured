Feature: Add New Product

	Scenario: Add a new product
	    Given I have product endpoint
	    When I send a POST request to add a new product
	    Then the product should be successfully added and I should get the productID 