package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmployeeListPage extends CommonMethods {

    @FindBy(xpath = "//input[@id = 'empsearch_id']")
    public WebElement employeeIdTxtBx;

    @FindBy(xpath = "//input[@id = 'searchBtn']")
    public WebElement searchBtn;

    @FindBy(xpath = "//table[@id = 'resultTable']/tbody/tr")
    public List<WebElement> employeeList;

    public EmployeeListPage() {
        PageFactory.initElements(driver, this);
    }
}
