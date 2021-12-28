package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonMethods {
    @FindBy(xpath = "//input[@id = 'txtUsername']")
    public WebElement usernameTextBox;

    @FindBy(xpath = "//input[@id = 'txtPassword']")
    public WebElement passwordTextBox;

    @FindBy(xpath = "//input[@id = 'btnLogin']")
    public WebElement loginBtn;

    @FindBy(xpath = "//img[contains(@src, 'humanresources')]")
    public WebElement logoImg;

    @FindBy(xpath = "//div[@id = 'logInPanelHeading']")
    public WebElement loginPanel;

    @FindBy(xpath = "//span[@id = 'spanMessage']")
    public WebElement errorMessage;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        sendText(usernameTextBox, username);
        sendText(passwordTextBox, password);
    }
}
