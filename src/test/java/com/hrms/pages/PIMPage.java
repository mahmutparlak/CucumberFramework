package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class PIMPage extends CommonMethods {
    @FindBy(xpath = "//a[@id = 'menu_pim_addEmployee']")
    public WebElement addEmployeeBtn;

    @FindBy(xpath = "//a[@id = 'menu_pim_viewEmployeeList']")
    public WebElement employeeList;

    public PIMPage() {
        PageFactory.initElements(driver, this);
    }
}
