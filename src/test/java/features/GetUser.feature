Feature: Test Get Request

  Scenario Outline: Retrieving User Information
    Given a user with ID <userId>
    When a GET request is made to user <userId>
    Then the response status code should be <statusCode>
    And the response body should contain user information
    
    Examples:
      | userId | statusCode |
      | 3      | 200        |
      | 6      | 200        |
      | 9      | 200        |
    
  Scenario: Retrieving Non-existent User Information
    Given a user with ID 123
    When a GET request is made to user 123
    Then the response status code should be 404
    And the response body should contain an error message