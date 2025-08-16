package com.Nykaa.AbstractPageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class Abstract {

    private WebDriver driver;
    public Actions a;

    public Abstract(WebDriver driver) {
    	a = new Actions(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement WaitToAppear(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));;
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void ScrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    public WebDriver SwitchDriverToChildWindow(WebDriver driver) {
    	Set<String> windows = driver.getWindowHandles();
    	Iterator<String> it = windows.iterator();
    	String parent = it.next();
    	String child = it.next();
    	return driver.switchTo().window(child);
    }

}
