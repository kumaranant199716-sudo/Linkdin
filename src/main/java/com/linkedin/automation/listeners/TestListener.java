package com.linkedin.automation.listeners;

import com.linkedin.automation.utils.ScreenshotUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener implements ITestListener {
    
    @Override
    public void onTestFailure(ITestResult result) {
        // Get the WebDriver instance from the test class
        Object currentClass = result.getInstance();
        java.lang.reflect.Field driverField = null;
        try {
            driverField = currentClass.getClass().getSuperclass().getDeclaredField("driver");
            driverField.setAccessible(true);
            org.openqa.selenium.WebDriver driver = (org.openqa.selenium.WebDriver) driverField.get(currentClass);
            
            // Take screenshot on test failure
            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, result.getName());
            
            // Add screenshot to TestNG report
            if (!screenshotPath.isEmpty()) {
                System.setProperty("org.uncommons.reportng.escape-output", "false");
                Reporter.log("<br><a href=\"" + screenshotPath + "\">" + "<img src=\"" + screenshotPath + "\" height='200' width='200'/> </a><br>");
            }
        } catch (Exception e) {
            System.err.println("Error while taking screenshot: " + e.getMessage());
        }
    }
}
