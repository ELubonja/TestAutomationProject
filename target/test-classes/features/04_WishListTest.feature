Feature: Wish list test

  Background: User is signed in
    Given I am on the home page
#    And I sign in using "test1234test@test.com" and "Password123"
    And I sign in
    When I hover the Women dropdown on the store menu
    And I hover over the Tops dropdown
    And I click on the Jacket menu option
    And I click on the Color menu option
    And I choose a color
    Then I should see all displayed products with the selected color bordered in red
    When I click on the Price dropdown and select the first option
    And I check each displayed product should have a price within the range selected

  Scenario: Add items in the wish list
    Then I remove the Price filter
    Then I check that the items number displayed is increased
    When I add the first item to the Wish List
    Then I see a successful message with text and icon
    Then I check that the correct number of items is displayed in My Wish List

  Scenario: Add items to Shopping Cart and verify the total
    When I add all displayed items to the shopping cart selecting appropriate sizes
    Then I check a success message with a confirmation icon should be displayed
    When I click on the shopping cart link in the success message
    Then I check that the Shopping Cart page should be displayed
    And I check that the sum of the prices for all items should equal the Order Total in the Summary section