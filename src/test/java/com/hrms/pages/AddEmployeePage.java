package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePage extends CommonMethods {

    @FindBy(xpath = "//h1[text() = 'Add Employee']")
    public WebElement addEmployeeText;

    @FindBy(xpath = "//input[@id = 'firstName']")
    public WebElement firstNameTxtBx;

    @FindBy(xpath = "//input[@id = 'middleName']")
    public WebElement middleNameTxtBx;

    @FindBy(xpath = "//input[@id = 'lastName']")
    public WebElement lastNameTxtBx;

    @FindBy(xpath = "//input[@id = 'employeeId']")
    public WebElement employeeIDTxtBx;

    @FindBy(xpath = "//input[@id = 'photofile']")
    public WebElement chooseFile;

    @FindBy(xpath = "//input[@id = 'chkLogin']")
    public WebElement checkBx;

    @FindBy(xpath = "//input[@id = 'user_name']")
    public WebElement userName;

    @FindBy(xpath = "//input[@id = 'user_password']")
    public WebElement password;

    @FindBy(xpath = "//input[@id = 're_password']")
    public WebElement confirmPassword;

    @FindBy(xpath = "//select[@id = 'status']")
    public WebElement status;

    @FindBy(xpath = "//input[@id = 'btnSave']")
    public WebElement saveBtn;

    @FindBy(xpath = "//input[@id = 'personal_txtEmployeeId']")
    public WebElement employeeID;

    public AddEmployeePage() {
        PageFactory.initElements(driver, this);
    }

}
