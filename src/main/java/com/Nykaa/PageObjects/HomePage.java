package com.Nykaa.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Nykaa.AbstractPageObject.Abstract;

import java.util.List;

public class HomePage extends Abstract {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Gift Card']")
    WebElement giftCardLink;

    @FindBy(id = "header-bag-icon")
    WebElement cart;

    @FindBy(xpath = "//p[text()='Your Shopping Bag is empty']")
    WebElement emptyCartMessage;

    @FindBy(xpath = "//p[contains(text(),'feels too light!')]")
    WebElement emptyCartInstruction;

    @FindBy(xpath = "//input[@placeholder='Search on Nykaa']")
    WebElement searchCartInputBox;

    @FindBy(xpath = "//a[text()='brands']")
    WebElement brands;

    @FindBy(css = ".brand-logo.menu-links")
    List<WebElement> dotKeyBrand;

    public ProductsPage moveToDotKeyBrand() {
        a.moveToElement(brands).moveToElement(dotKeyBrand.get(1)).click().build().perform();
        return new ProductsPage(driver);
    }

    public GiftCard clickGiftCardLink() {
        giftCardLink.click();
        WebDriver giftDriver = SwitchDriverToChildWindow(driver);
        return new GiftCard(giftDriver);
    }

    public void clickCart() {
        cart.click();
    }

    public String getEmptyCartMessage() throws InterruptedException {
    	Thread.sleep(2000);
    	driver.switchTo().frame(driver.findElement(By.className("css-wlxg94")));
        String emptyCartText = WaitToAppear(emptyCartMessage).getText();
        driver.switchTo().defaultContent();
        return emptyCartText;
    }

    public String getEmptyCartInstruction() {
        return emptyCartInstruction.getText();
    }

    public ProductsPage enterSearchText(String searchText) {
    	a.moveToElement(searchCartInputBox).click().sendKeys(searchText).keyDown(Keys.ENTER).keyUp(Keys.ENTER).build().perform();
    	return new ProductsPage(driver);
    }

}
