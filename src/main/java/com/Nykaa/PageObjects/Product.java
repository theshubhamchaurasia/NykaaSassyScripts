package com.Nykaa.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Nykaa.AbstractPageObject.Abstract;

public class Product extends Abstract {

    private WebDriver driver;

    public Product(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Does not ship to pincode']")
    WebElement notDeliverableMessage;

    @FindBy(xpath = "//span[text()='COD available']")
    WebElement deliverableMessage;

    private By pincodeInput = By.xpath("//input[@placeholder='Enter pincode']");
    private By checkButton  = By.xpath("//button[normalize-space()='Check']");

    public void enterPincode(String pincode) {
        driver.findElement(pincodeInput).clear();
        driver.findElement(pincodeInput).sendKeys(pincode);
    }

    public void clickCheckButton() {
        driver.findElement(checkButton).click();
    }

    public String checkDeliveryForValidPincode() {
    	return WaitToAppear(deliverableMessage).getText();
    }
    
    public String checkDeliveryForInvalidPincode() {
    	return WaitToAppear(notDeliverableMessage).getText();
    }
}
