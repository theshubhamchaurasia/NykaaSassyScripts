package com.Nykaa.baseTestUtil;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.Nykaa.AbstractPageObject.Abstract;
import com.Nykaa.PageObjects.HomePage;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    private static WebDriver driver;
    Abstract abstractPage;
    public HomePage home;

    public static String getGlobalPrarmeter(String key) {
        Properties prop = new Properties();
        String globalPropertiesFilePath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\Nykaa\\GlobalProperties\\GlobalData.properties";
        try {
            FileInputStream file = new FileInputStream(globalPropertiesFilePath);
            prop.load(file);
        } catch (Exception e) {
            System.out.println("Error loading global properties file: " + e.getMessage());
        }
        return prop.getProperty(key);
    }

    public void TakeScreenshot(String picName, WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(System.getProperty("user.dir") + "\\ScreenShot\\" + picName + ".png");
        try {
            /*FileUtils is part of the Apache Commons IO library, not the standard Java library*/
            FileUtils.copyFile(source, destination);
        } catch (Exception e) {
            System.out.println("Error taking screenshot: " + e.getMessage());
        }
//        return System.getProperty("user.dir") + "\\ScreenShot\\" + picName + ".png";
    }

    public static WebDriver InitializeDriver(){
        if (driver == null) {
            String testingBrowser = getGlobalPrarmeter("browser");
            if (testingBrowser.equalsIgnoreCase("Chrome")) {
                driver = new ChromeDriver();
            } else if (testingBrowser.equalsIgnoreCase("Firefox")) {
                driver = new FirefoxDriver();
            } else if (testingBrowser.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            return driver;
        }
        return driver;
    }

    public void CloseDriver(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public HomePage LaunchNaykaa(){
        driver.get(getGlobalPrarmeter("prodURL"));
        return home = new HomePage(driver);
    }
}
