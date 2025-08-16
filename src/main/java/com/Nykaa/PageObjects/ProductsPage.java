package com.Nykaa.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Nykaa.AbstractPageObject.Abstract;


public class ProductsPage extends Abstract {

    private WebDriver driver;

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='customer top rated']")
    WebElement topRatedFilter;

    @FindBy(id = "filter-sort")
    WebElement sortByDropdown;

    @FindBy(xpath = "//div[@id='product-list-wrap']/div[1]")
    WebElement topRatedProduct;
    
    @FindBy(xpath = "//div[text()='Minimalist Daily Skincare Routine For Oily & Acne Prone Skin...']")
    WebElement topRatedProductDetails;
    
    public String GetProductDetails() {
    	return topRatedProduct.getText();
    }

    public void clickTopRatedFilter() {
        WaitToAppear(sortByDropdown).click();
        WaitToAppear(topRatedFilter).click();
    }

    public Product ClickOnTopRatedProduct(){
        WaitToAppear(topRatedProduct).click();
        WebDriver childDriver = SwitchDriverToChildWindow(driver);
        return new Product(childDriver);
    }

}
