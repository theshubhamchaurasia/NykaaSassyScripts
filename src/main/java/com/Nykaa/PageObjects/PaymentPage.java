package com.Nykaa.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {
    private WebDriver driver;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='qr-info-heading']")
    WebElement paymentUPIs;


    public void ScanQRCode(){
    	driver.findElement(By.xpath("//button[contains(text(),'Scan & Pay')]")).click();
    }
    
    public WebDriver getDriver() {
    	return driver;
    }
    
}
