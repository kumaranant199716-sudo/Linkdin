package com.linkedin.automation.tests;

import com.linkedin.automation.base.BaseTest;
import com.linkedin.automation.pages.JobSearchPage;
import com.linkedin.automation.pages.LoginPage;
import com.linkedin.automation.utils.FileUtils;
import com.linkedin.automation.utils.ScreenshotUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.util.Properties;

public class LinkedInJobApplicationTest extends BaseTest {
    
    private LoginPage loginPage;
    private JobSearchPage jobSearchPage;
    private Properties testData;
    
    @BeforeMethod
    public void setupTest() {
        // Initialize page objects
        loginPage = new LoginPage(driver);
        jobSearchPage = new JobSearchPage(driver);
        
        // Load test data
        String configPath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
        testData = FileUtils.loadProperties(configPath);
        
        // Create screenshots directory if it doesn't exist
        new File(System.getProperty("user.dir") + "/screenshots").mkdirs();
    }
    
    @Test(description = "Apply to SDET jobs with Easy Apply")
    public void applyToSDETJobs() {
        try {
            // Login to LinkedIn
            loginPage.login(
                testData.getProperty("linkedin.username"), 
                testData.getProperty("linkedin.password")
            );
            
            // Navigate to jobs page
            driver.get("https://www.linkedin.com/jobs/");
            
            // Search for SDET jobs
            jobSearchPage.searchForJob("SDET");
            
            // Apply Easy Apply filter
            jobSearchPage.applyEasyApplyFilter();
            
            // Apply to jobs (max 5 applications for this test)
            int maxApplications = 5;
            jobSearchPage.applyToJobs(maxApplications);
            
            // Add assertions or verifications here
            Assert.assertTrue(true, "Job application process completed");
            
        } catch (Exception e) {
            // Take screenshot on failure
            ScreenshotUtils.takeScreenshot(driver, "test_failure");
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
}
