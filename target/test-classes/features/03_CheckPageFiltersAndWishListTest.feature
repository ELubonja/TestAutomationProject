Feature: Check page filters and wish list

  Background: User is signed in
    Given I am on the home page
#    And I sign in using "test1234test@test.com" and "Password123"
    And I sign in

  Scenario: Verify page filters functionality
    When I hover the Women dropdown on the store menu
    And I hover over the Tops dropdown
    And I click on the Jacket menu option
    And I click on the Color menu option
    And I choose a color
    Then I should see all displayed products with the selected color bordered in red
    When I click on the Price dropdown and select the first option
    And I check each displayed product should have a price within the range selected