
@all
Feature: Product details

  Background: I am on the home page



  #Feature cannot have the same title as the feature file
  @productDetails @module2
  Scenario: Verify product title
    Given I am on the home page2
    When  I click on a product Faded Short Sleeve T-shirts
    And   I land on a product details page with title containing Faded Short Sleeve T-shirts
    Then  The title of the product should be the same

    #Scenario is == to using the @Test annotation in Testng

  @productDetails @module1
  Scenario: Product search

    When  I search for a Blouse
    Then  I should see the Blouse when I land on the results page


  @productDetails
  Scenario: Product search

    When  I search for a Printed Dress
    Then  I should see the Printed Dress when I land on the results page


  @productDetails
  Scenario: Product search

    When  I search for a Printed Summer Dress
    Then  I should see the Printed Summer Dress when I land on the results page



