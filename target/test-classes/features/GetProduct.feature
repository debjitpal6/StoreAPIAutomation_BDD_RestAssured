Feature: Get products

  Scenario: Get all products successfully
    Given I have GET product endpoint
    When the user makes a GET request to the endpoint
    Then response status code should be 200
    And the response should contain a list of products with attribute ID, title, price etc.
    
  Scenario Outline: Get a signle product successfully
    Given I have GET product endpoint
    When the user makes a GET request to the endpoint with <productID>
    Then response status code should be 200
    And the response should contain the product details
    
    Examples:
      | ProductId |
      |     1     | 
    