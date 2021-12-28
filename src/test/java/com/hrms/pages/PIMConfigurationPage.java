package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PIMConfigurationPage extends CommonMethods {
    @FindBy(xpath = "//a[@id = 'menu_pim_Configuration']")
    public WebElement configurationDropdown;

    @FindBy(xpath = "//a[@id = 'menu_pim_configurePim']")
    public WebElement optionalFields;

    @FindBy(xpath = "//input[@id = 'btnSave']")
    public WebElement editAndSaveBtn;

    @FindBy(xpath = "//li[@class= 'checkbox']")
    public List<WebElement> configurePIMCheckboxes;

    public PIMConfigurationPage() {
        PageFactory.initElements(driver, this);
    }
}
