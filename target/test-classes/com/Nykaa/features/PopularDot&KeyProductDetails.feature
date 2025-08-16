Feature: Popular Dot & Key Product Details

      Scenario: Verify user can filter the product by "Customer Top Rated"
        Given User is on Nykaa home page
        And User navigate to Dot & Key brand page
        And User select Sort By Customer Top Rated
        Then User should see the products sorted by "Customer Top Rated" and get the first product details