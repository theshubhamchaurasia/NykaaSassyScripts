Feature: Verify user is able to send gift
  As a user
  I want to send different types of gift cards
  
  Scenario Outline: Verify user is able to send gift card with valid details
    Given User is on Nykaa home page
    And User clicks on Gift Card link
    When User selects "<GiftCardType>" gift card
    And User enter details :
      | To           | <To>          |
      | Email        | <Email>       |
      | Message      | <Message>     |
      | Amount       | <Amount>      |
      | From         | <From>        |
      | Phone Number | <PhoneNumber> |
    And User clicks on PROCEED TO PAY button
    And User selects Scan & Pay UPI payment option
    Then User should be able to see QR code to scan and pay

    Examples:
      | GiftCardType    | To             | Email             | Message        | Amount | From              | PhoneNumber |
      | Nykaa Gift Card | Shimran Sharma | shimran@gmail.com | Happy Birthday | 1000   | Shubham Chaurasia | 8965748596  |
  
  Scenario: Verify that 'PROCEED TO PAY' button is disabled when no details are entered
    Given User is on Nykaa home page
    And User clicks on Gift Card link
    When User selects "Nykaa Gift Card" gift card
    Then User should see that PROCEED TO PAY button is disabled
  
  Scenario Outline: Verify that text message is framed and displayed correctly
    Given User is on Nykaa home page
    And User clicks on Gift Card link
    When User selects "<GiftCardType>" gift card
    And User enter details :
      | To           | <To>          |
      | Email        | <Email>       |
      | Message      | <Message>     |
      | Amount       | <Amount>      |
      | From         | <From>        |
      | Phone Number | <PhoneNumber> |
    Then User should see the text message framed as "Hi, <To> You have received a gift card from <From> <Message>"
    Examples:
      | GiftCardType    | To             | Email             | Message        | Amount | From              | PhoneNumber |
      | Nykaa Gift Card | Shimran Sharma | shimran@gmail.com | Happy Birthday | 1000   | Shubham Chaurasia | 8965748596  |