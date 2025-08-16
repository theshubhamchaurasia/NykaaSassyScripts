package com.Nykaa.cucumberTestNGRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/com/Nykaa/features",
        glue = {"com.Nykaa.stepDefinitions"},
        plugin = {"pretty", "html:Report/cucumber-reports.html", "json:Report/cucumber.json", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true
)
public class RunAll extends AbstractTestNGCucumberTests {

}
