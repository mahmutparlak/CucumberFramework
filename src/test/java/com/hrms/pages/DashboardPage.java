package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends CommonMethods {

    @FindBy(xpath = "//a[@id = 'welcome']")
    public WebElement welcomeMsg;

    @FindBy(xpath = "//a[@target = '_blank']/img[contains(@src, 'humanresources')]")
    public WebElement dashboardLogo;

    @FindBy(xpath = "//b[text() = 'Admin']")
    public WebElement adminBtn;

    @FindBy(xpath = "//b[text() = 'PIM']")
    public WebElement PIMBtn;

    @FindBy(xpath = "//b[text() = 'Leave']")
    public WebElement leaveBtn;

    @FindBy(xpath = "//b[text() = 'Time']")
    public WebElement timeBtn;

    @FindBy(xpath = "//b[text() = 'Recruitment']")
    public WebElement recruitmentBtn;

    @FindBy(xpath = "//b[text() = 'Performance']")
    public WebElement performanceBtn;

    @FindBy(xpath = "//b[text() = 'Dashboard']")
    public WebElement dashboardBtn;

    @FindBy(xpath = "//b[text() = 'Directory']")
    public WebElement directoryBtn;

    @FindBy(xpath = "//div[@class='menu']/ul/li")
    public List<WebElement> dashboardTabs;

    public List<String> getDashTab() {

        List<String> dashBoardTabs = new ArrayList<>();

        for(WebElement eachTab:dashboardTabs) {
            String eachTabText = eachTab.getText();
            dashBoardTabs.add(eachTabText);
        }
        return dashBoardTabs;
    }

    public DashboardPage() {
        PageFactory.initElements(driver, this);
    }
}
