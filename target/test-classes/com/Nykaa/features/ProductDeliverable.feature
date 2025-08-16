Feature: Verify the product deliverable functionality based on PIN code

  Background:
    Given User is on Nykaa home page
    And User search for "Minimalist Sunscreen"
    And User select Sort By Customer Top Rated

  Rule: Product delivery check on Valid PIN code
    Scenario: Verify user can check product deliverable by entering valid PIN code
      And User clicks on top rated Minimalist Sunscreen product
      When User enters "110001" in the PIN code field
      And Click on Check button
      Then User should see "COD available" delivery text displayed

  Rule: Product delivery check on Invalid PIN code
    Scenario: Verify user can check product deliverable by entering invalid PIN code
      And User clicks on top rated Minimalist Sunscreen product
      When User enters "999999" in the PIN code field
      And Click on Check button
      Then User should see "Does not ship to pincode" not delivery text displayed