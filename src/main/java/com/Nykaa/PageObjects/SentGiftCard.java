package com.Nykaa.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SentGiftCard {
    private WebDriver driver;

    public SentGiftCard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private By toNameInput       = By.id("name");
    private By recipientEmailInput = By.id("recipientEmail");
    private By messageTextArea   = By.xpath("//textarea[@placeholder=\"Here's a little something to make your day!\"]");
    private By amountInput       = By.xpath("//input[@placeholder='Enter Amount(Max. 10000)']");
    private By senderNameInput   = By.id("senderName");
    private By senderMobileInput = By.id("senderMobile");
    private By proceedToPayBtn   = By.xpath("//button[.//span[normalize-space()='PROCEED TO PAY']]");
    private By greetingText = By.cssSelector("p.css-bqriga.e1gd7f430");
    private By messageText  = By.cssSelector("p.subtitle.css-11w2irk.eoygcm00");
    
    @FindBy(xpath = "//button[contains(@class,'width-full p15 css-aymsyc')]")
    public WebElement payButton;

    public void enterRecipientName(String name) {
        driver.findElement(toNameInput).clear();
        driver.findElement(toNameInput).sendKeys(name);
    }

    public void enterRecipientEmailOrPhone(String emailOrPhone) {
        driver.findElement(recipientEmailInput).clear();
        driver.findElement(recipientEmailInput).sendKeys(emailOrPhone);
    }

    public void enterMessage(String message) {
        WebElement msgBox = driver.findElement(messageTextArea);
        msgBox.clear();
        msgBox.sendKeys(message);
    }

    public void enterAmount(String amount) {
        driver.findElement(amountInput).clear();
        driver.findElement(amountInput).sendKeys(amount);
    }

    public void enterSenderName(String senderName) {
        driver.findElement(senderNameInput).clear();
        driver.findElement(senderNameInput).sendKeys(senderName);
    }

    public void enterSenderMobile(String senderMobile) {
        driver.findElement(senderMobileInput).clear();
        driver.findElement(senderMobileInput).sendKeys(senderMobile);
    }

    public PaymentPage clickProceedToPay() {
        driver.findElement(proceedToPayBtn).click();
        return new PaymentPage(driver);
    }

    public void fillGiftCardForm(String recipientName, String recipientEmailOrPhone, String message,
                                 String amount, String senderName, String senderMobile) {
        enterRecipientName(recipientName);
        enterRecipientEmailOrPhone(recipientEmailOrPhone);
        enterMessage(message);
        enterAmount(amount);
        enterSenderName(senderName);
        enterSenderMobile(senderMobile);
    }

    public String getFullGiftMessage() {
        String greeting = driver.findElement(greetingText).getText().trim();
        String message  = driver.findElement(messageText).getText().trim();
        String message2  = driver.findElements(messageText).get(1).getText().replace("Here's a little something to make your day!", " ");
        return greeting + " " + message + message2;
    }

}
