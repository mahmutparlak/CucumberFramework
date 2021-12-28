package com.hrms.stepdefinitions;

import com.hrms.utils.CommonMethods;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDefinition extends CommonMethods {

    @When("enter valid credentials")
    public void enter_valid_credentials() {
        loginPage.login("Admin", "Hum@nhrm123");
    }

    @When("click on login button")
    public void click_on_login_button() {
        click(loginPage.loginBtn);
    }

    @Then("verify dashboard is displayed")
    public void verify_dashboard_is_displayed() {
        Assert.assertTrue(dashboardPage.dashboardLogo.isDisplayed());
        Assert.assertEquals("Welcome message is not match", "Welcome Admin", dashboardPage.welcomeMsg.getText());
    }

    @When("enter invalid credentials")
    public void enter_invalid_credentials() {
        loginPage.login("Adddmin", "Mahiioooo");
    }

    @Then("verify error message")
    public void verify_error_message() {
        Assert.assertEquals("Error message is not matched", "Invalid credentials", loginPage.errorMessage.getText());
    }

    @When("entering just password")
    public void entering_just_password() {
        loginPage.login("", "Hum@nhrm123");
    }

    @Then("get error message and verify")
    public void get_error_message_and_verify() {
        Assert.assertEquals("Empty username error message doesn't matched",
                "Username cannot be empty", loginPage.errorMessage.getText());
    }

    @When("entering just username")
    public void entering_just_username() {
        loginPage.login("Admin", "");
    }

    @Then("get password error message and verify")
    public void get_password_error_message_and_verify() {
        Assert.assertEquals("Empty password error message doesn't matched",
                "Password cannot be empty", loginPage.errorMessage.getText());
    }
}
