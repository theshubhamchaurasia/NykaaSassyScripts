Feature: Verify the cart functionality

  Scenario: Verify that 'Your Shopping Bag is empty' text displayed when cart is empty
    Given User is on Nykaa home page
    When User clicks on Cart icon
    Then User should see "Your Shopping Bag is empty" text displayed
    And User should see text "This feels too light! Go on, add all your favourites"