package com.Nykaa.stepDefinitions;

import io.cucumber.java.*;

import java.io.FileNotFoundException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.Nykaa.baseTestUtil.BaseTest;

public class Hooks extends BaseTest {

    private WebDriver driver;

    @Before
    public void Setup(){
        driver = InitializeDriver();
    }

    @AfterStep
    public void ResultCheck(Scenario scenario) throws InterruptedException, FileNotFoundException {
        if(scenario.isFailed()){
            Thread.sleep(3000);
            String screenshotPath = TakeScreenshot(scenario.getName(), driver);
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            JiraAPI.CreateBugOnTestFailure(screenshotPath, scenario.getName(), "CRED", scenario.getName() + " scenario is " + scenario.getStatus());
        }
    }

    @After
    public void TearDown(){
        CloseDriver();
    }
}
