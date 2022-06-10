Feature: Duobank features

  @api
  Scenario: API to UI flow

    Given The base URI is set to uri
    And The valid body is added to the request
    When I send a POST request to endpoint
    Then the status code should be int and response body or payload should contain message.
    And I navigate to homepage
    When I enter the same credentials sent by API request
    Then I should be able to login