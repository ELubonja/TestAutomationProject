Feature: Sign In Test
  Scenario: User signs in and signs out successfully
    Given I am on the home page
#    When I sign in using "test1234test@test.com" and "Password123"
    When I sign in
    Then I should see my username displayed
    Then I sign out
