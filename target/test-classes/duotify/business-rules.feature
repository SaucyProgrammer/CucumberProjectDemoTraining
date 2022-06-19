Feature: Business logic and rules

  @dbBusinessLogic
  Scenario: Verify songs table column names
    When I send a query to retrieve column names for songs table
    Then the column names should be the following
      | id         |
      | title      |
      | artist     |
      | album      |
      | genre      |
      | duration   |
      | path       |
      | albumOrder |
      | plays      |



    Scenario: Verify Unicode support
      When I update the last name of the user with the username "duotech" with "片仮名"
      Then the value should be updated correctly


      @db
      Scenario: Verify business logic for duplicate usernames
        When I send a query to retrieve all usernames
        Then the usernames column should not contain duplicates