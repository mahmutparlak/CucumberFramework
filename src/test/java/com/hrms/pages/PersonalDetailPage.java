package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalDetailPage extends CommonMethods {

    @FindBy(xpath = "//input[@id = 'personal_txtEmployeeId']")
    public WebElement employeeID;

    @FindBy(xpath = "//div[@id = 'profile-pic']/h1")
    public WebElement employeeFullNameField;

    public PersonalDetailPage() {
        PageFactory.initElements(driver, this);
    }
}
