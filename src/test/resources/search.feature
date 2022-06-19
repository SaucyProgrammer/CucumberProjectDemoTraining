#adding a tag to a feature file will implicitly add it all for every single scenario even when they don't have it.
#@search tags can be added globally on feature file when placed on top of "Feature"

@all
Feature: Search Bar Functionality

  Background: I am on the home page
    #This "Iam on the homepage" was repeated in all scenarios so I now remove it and place it at a common location
    # Background automatically uses this Given or any other keyword steps in this feature file
  # IMPORTANT: There can only be on background keyword in a feature file


  @search @product @blouse
  Scenario: Product search

    When  I search for a Blouse
    Then  I should see the Blouse when I land on the results page


  @search @product @smoke
  Scenario: Product search

    When  I search for a Printed Dress
    Then  I should see the Printed Dress when I land on the results page


  @search @printedSummer @smoke
  Scenario: Product search

    When  I search for a Printed Summer Dress
    Then  I should see the Printed Summer Dress when I land on the results page

