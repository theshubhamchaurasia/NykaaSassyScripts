package com.Nykaa.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GiftCard {

    private WebDriver driver;

    public GiftCard(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By birthdayWishesSendBtn = By.xpath(
            "//div[@class='greeting pb10' and normalize-space(text())='Birthday Wishes']" + "/ancestor::div[@class='gift-card-view']//button[.//span[normalize-space()='SEND']]"
    );

    By nykaaGiftSendBtn = By.xpath(
            "//div[@class='greeting pb10' and normalize-space(text())='Nykaa Gift Card']" + "/ancestor::div[@class='gift-card-view']//button[.//span[normalize-space()='SEND']]"
    );
    

    public SentGiftCard SelectGiftCard(String cardName) {
    	if (cardName.equalsIgnoreCase("Birthday Wishes")) {
			return clickBirthdayWishesSendButton();
		} else if (cardName.equalsIgnoreCase("Nykaa Gift Card")) {
			return clickNykaaGiftSendButton();
		}
    	return null;
    }
    
    public SentGiftCard clickBirthdayWishesSendButton() {
        driver.findElement(birthdayWishesSendBtn).click();
        return new SentGiftCard(driver);
    }

    public SentGiftCard clickNykaaGiftSendButton() {
        driver.findElement(nykaaGiftSendBtn).click();
        return new SentGiftCard(driver);
    }

}
