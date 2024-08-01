Feature: Create Account Test
  Scenario: User creates an account successfully
    Given I am on the home page
    When I navigate to the create account page
    Then I should see the create account page title
    When I fill in the account details
    And I click the create account button
    Then I should see the success message
    And I should be redirected to the account page
    Then I sign out