package com.linkedin.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class JobSearchPage extends BasePage {
    
    @FindBy(xpath = "//input[contains(@class, 'jobs-search-box__text-input') and @aria-label='Search by title, skill, or company']")
    private WebElement searchInput;
    
    @FindBy(xpath = "//button[text()='Search']")
    private WebElement searchButton;
    
    @FindBy(xpath = "//button[contains(@class, 'jobs-search-advanced-filters__button--filter')]")
    private WebElement filterButton;
    
    @FindBy(xpath = "//label[contains(.,'Easy Apply')]")
    private WebElement easyApplyFilter;
    
    @FindBy(xpath = "//button[contains(@class, 'jobs-apply-button')]")
    private List<WebElement> applyButtons;
    
    @FindBy(xpath = "//button[contains(@class, 'artdeco-button--primary') and contains(.,'Submit application')]")
    private WebElement submitApplicationButton;
    
    @FindBy(xpath = "//button[contains(@class, 'artdeco-modal__dismiss')]")
    private WebElement closeButton;
    
    public JobSearchPage(WebDriver driver) {
        super(driver);
    }
    
    public void searchForJob(String jobTitle) {
        waitAndSendKeys(searchInput, jobTitle);
        waitAndClick(searchButton);
    }
    
    public void applyEasyApplyFilter() {
        try {
            waitAndClick(filterButton);
            waitAndClick(easyApplyFilter);
            // Wait for the filter to be applied
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void applyToJobs(int maxApplications) {
        int applicationsSubmitted = 0;
        
        while (applicationsSubmitted < maxApplications) {
            try {
                // Find all Easy Apply buttons
                wait.until(ExpectedConditions.visibilityOfAllElements(applyButtons));
                
                for (WebElement applyButton : applyButtons) {
                    if (applicationsSubmitted >= maxApplications) break;
                    
                    try {
                        // Scroll to the apply button
                        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", applyButton);
                        Thread.sleep(1000);
                        
                        // Click the apply button
                        waitAndClick(applyButton);
                        
                        // Handle the application form (simplified - you'll need to customize this part)
                        // This is where you'd fill out any required fields
                        
                        // Submit the application
                        if (isElementDisplayed(submitApplicationButton)) {
                            waitAndClick(submitApplicationButton);
                            System.out.println("Successfully submitted application " + (applicationsSubmitted + 1));
                            applicationsSubmitted++;
                        }
                        
                        // Close any modals that appear after submission
                        Thread.sleep(2000);
                        if (isElementDisplayed(closeButton)) {
                            waitAndClick(closeButton);
                        }
                        
                    } catch (Exception e) {
                        System.out.println("Error applying to job: " + e.getMessage());
                        // Try to close any open modals if there was an error
                        try {
                            if (isElementDisplayed(closeButton)) {
                                waitAndClick(closeButton);
                            }
                        } catch (Exception ex) {
                            // Ignore if we can't close the modal
                        }
                    }
                }
                
                // If we've gone through all jobs on the page, try to go to the next page
                // You would need to implement pagination logic here
                break;
                
            } catch (Exception e) {
                System.out.println("Error in job application process: " + e.getMessage());
                break;
            }
        }
    }
}
