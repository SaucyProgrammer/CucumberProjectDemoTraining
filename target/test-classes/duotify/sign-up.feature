


Feature: Sign up features

  Scenario: Verify user sign up
    Given I am on the duotify homepage
    When I sign up using valid credentials
    Then I should be able to land on the homepage
    And I should be able to verify that the user details are in the database



  @datable
  Scenario: Verify user sign up using Datatable

    Given I am on the duotify homepage
    When I sign up using the following credentials
      | username   | first | last   | email                 | password |
      | Zak.darwes | Zak   | Darwes | zak.darwesc@gmail.com | zak12345 |
    Then I should be able to land on the homepage
    And I should be able to verify that the user details are in the database



  @datable @mockaRooDataTable
  Scenario Outline: Verify user sign up using Datatable

    Given I am on the duotify homepage
    When I sign up using the following credentials
      | username   | first   | last   | email   | password   |
      | <username> | <first> | <last> | <email> | <password> |
    Then I should be able to land on the homepage
    And I should be able to verify that the user details are in the database

    Examples:

      | username   | first      | last     | email                               | password     |
      | Zak.darwes | Zak        | Darwes   | zak.darwesc@gmail.com               | zak12345     |
      | eharbron0  | Edmund     | Harbron  | eharbron0@deviantart.com            | wcESWXAh1g74 |
      | pcrowd1    | Patrice    | Crowd    | pcrowd1@vkontakte.ru                | xvl7lN7bkOSm |
      | hwalczak2  | Harper     | Walczak  | hwalczak2@sourceforge.net           | i2ptdbh8H    |
      | tohare3    | Tyrone     | O'Hare   | tohare3@yellowbook.com	dKsiYjYjAbpH |              |
      | jfarren4   | Jemimah    | Farren   | jfarren4@moonfruit.com              | opwCXZ       |
      | abeteriss5 | Andrus     | Beteriss | abeteriss5@netvibes.com             | tGDnasXPV5   |
      | jfarnon6   | Joby       | Farnon   | jfarnon6@bloomberg.com              | 2bOVYuFRfvPO |
      | hhedden7   | Helene     | Hedden   | hhedden7@amazon.de                  | dIZZo2a1d    |
      | ksimister8 | Kristopher | Simister | ksimister8@topsy.com                | Wj3jFJ       |
      | cessam9    | Corabel    | Essam    | cessam9@ft.com                      | nYvlVCNn5    |




@dbFlowToUi
  Scenario Outline: Verify user sign up flow from DB to UI
    Given I create a new user in the Database with the following details
      | username   | firstName   | lastName   | email   | password   |
      | <username> | <firstName> | <lastName> | <email> | <password> |
    When I login with the same credentials on the UI
    Then I should be able to land on the homepage
    And firstname, lastname and email should be correct
    Examples:

      | username | firstName | lastName    | email                  | password    |
      | Zakzaddy11 | Zaki      | darweschzad | zak.darwesch@yahoo.com | zak12345678 |




