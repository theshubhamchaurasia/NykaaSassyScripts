package com.Nykaa.stepDefinitions;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.Nykaa.PageObjects.GiftCard;
import com.Nykaa.PageObjects.PaymentPage;
import com.Nykaa.PageObjects.Product;
import com.Nykaa.PageObjects.ProductsPage;
import com.Nykaa.PageObjects.SentGiftCard;
import com.Nykaa.baseTestUtil.BaseTest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class StepDefinitions extends BaseTest {
	private WebDriver driver;
	ProductsPage productPage;
	Product topRatedProduct;
	GiftCard giftCard;
	SentGiftCard selectGiftCard;
	PaymentPage paymentPage;
	
	@Given("User is on Nykaa home page")
	public void user_is_on_nykaa_home_page() {
		/*driver = InitializeDriver();*/
		LaunchNaykaa();
	}
	
	@When("User clicks on Cart icon")
	public void user_clicks_on_cart_icon() {
		home.clickCart();
	}
	
	@Then("User should see {string} text displayed")
	public void user_should_see_text_displayed(String emptyCartText) throws InterruptedException {
		Assert.assertEquals(home.getEmptyCartMessage(), emptyCartText);
	}
	
	@Then("User should see text {string}")
	public void user_should_see_text(String cartInstruction) {
		Assert.assertEquals(home.getEmptyCartInstruction(), cartInstruction);
	}
	
	/***************************************************************************/
	
	@Given("User search for {string}")
	public void user_search_for(String productName) {
		productPage = home.enterSearchText(productName);
	}
	@Given("User select Sort By Customer Top Rated")
	public void user_select_sort_by_customer_top_rated() {
		productPage.clickTopRatedFilter();
		
	}
	@Given("User clicks on top rated Minimalist Sunscreen product")
	public void user_clicks_on_top_rated_minimalist_sunscreen_product() {
		topRatedProduct = productPage.ClickOnTopRatedProduct();
	}
	@When("User enters {string} in the PIN code field")
	public void user_enters_in_the_pin_code_field(String pinCode) {
		topRatedProduct.enterPincode(pinCode);
	}
	@When("Click on Check button")
	public void click_on_button() {
		topRatedProduct.clickCheckButton();
	}
	@Then("User should see {string} delivery text displayed")
	public void user_should_see_delivery_text_displayed(String text) {
		Assert.assertEquals(topRatedProduct.checkDeliveryForValidPincode(), text);
	}
	@Then("User should see {string} not delivery text displayed")
	public void user_should_see_not_delivery_text_displayed(String text) {
		Assert.assertEquals(topRatedProduct.checkDeliveryForInvalidPincode(), text);
	}
	
	/******************************************************************/
	
	@Given("User navigate to Dot & Key brand page")
	public void user_navigate_to_dot_key_brand_page() {
		productPage = home.moveToDotKeyBrand();
		
	}
	@Then("User should see the products sorted by {string} and get the first product details")
	public void user_should_see_the_products_sorted_by_and_get_the_first_product_details(String filterType) {
		System.out.println(productPage.GetProductDetails().toString().split("MRP")[0]);
	}
	
	/*****************************************************************************/
	
	@Given("User clicks on Gift Card link")
	public void user_clicks_on_gift_card_link() {
		giftCard = home.clickGiftCardLink();
	}
	@When("User selects {string} gift card")
	public void user_selects_gift_card(String cardType) {
		selectGiftCard = giftCard.SelectGiftCard(cardType);
	}
	@Then("User should see that PROCEED TO PAY button is disabled")
	public void user_should_see_that_proceed_to_pay_button_is_disabled() {
		Assert.assertTrue(selectGiftCard.payButton.isDisplayed());
	}
	@When("User enter details :")
	public void user_enter_details(DataTable dataTable) {
		 Map<String, String> data = dataTable.asMap(String.class, String.class);

	        // Call your POM method
		 selectGiftCard.fillGiftCardForm(
	            data.get("To"),
	            data.get("Email"),
	            data.get("Message"),
	            data.get("Amount"),
	            data.get("From"),
	            data.get("Phone Number")
	        );
	}
	@Then("User should see the text message framed as {string}")
	public void user_should_see_the_text_message_framed_as(String message) {
		Assert.assertEquals(selectGiftCard.getFullGiftMessage(), message);
	}
	@When("User clicks on PROCEED TO PAY button")
	public void user_clicks_on_proceed_to_pay_button() {
		paymentPage = selectGiftCard.clickProceedToPay();
	}
	@When("User selects Scan & Pay UPI payment option")
	public void user_selects_scan_pay_upi_payment_option() {
		paymentPage.ScanQRCode();
	}
	@Then("User should be able to see QR code to scan and pay")
	public void user_should_be_able_to_see_qr_code_to_scan_and_pay() throws InterruptedException {
		Thread.sleep(2000);
		TakeScreenshot("QR_Scanner", paymentPage.getDriver());
	}

}
