Feature: Sign up features

  Scenario: Verify user sign up

    Given I am on the duotify homepage
    When I sign up using valid credentials
    Then I should be able to land on the homepage