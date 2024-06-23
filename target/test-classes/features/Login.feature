Feature: Login API Tests
	@smoke
  Scenario Outline: Successful login with valid credentials
    Given I have login endpoint
    When I send a POST request with <userName> & <password>
    Then the status code should be <statusCode>
    And the response should contain a token
    
    Examples:
      | userName   |  password  | statusCode |
      | "mor_2314" |  "83r5^_"  |   200      |
      
      
   Scenario Outline: Login with invalid credentials
    Given I have login endpoint
    When I send a POST request with <userName> & <password>
    Then the status code should be <statusCode>
    And the response should contain an errorMessage
    
    Examples:
      | userName   |  password  | statusCode |  TestName   |
      | "a"        |  "83r5^_"  |   401      |	Invalid UN |
      | "mor_2314" |  "b"       |   401      |  Invalid PW |
      | ""         |  "83r5^_"  |   400      |  Blank UN   | 
      | "mor_2314" |  ""        |   400      |  Blank PW   |
    
    