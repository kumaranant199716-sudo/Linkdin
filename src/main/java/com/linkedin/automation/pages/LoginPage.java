package com.linkedin.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(xpath = "//button[text()='Sign in']")
    private WebElement signInButton;
    
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    public void navigateToLoginPage() {
        driver.get("https://www.linkedin.com/login");
    }
    
    public void enterUsername(String username) {
        waitAndSendKeys(usernameField, username);
    }
    
    public void enterPassword(String password) {
        waitAndSendKeys(passwordField, password);
    }
    
    public void clickSignIn() {
        waitAndClick(signInButton);
    }
    
    public void login(String username, String password) {
        navigateToLoginPage();
        enterUsername(username);
        enterPassword(password);
        clickSignIn();
    }
}
