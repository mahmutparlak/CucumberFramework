package com.hrms.utils;

import com.hrms.testbase.PageInitializer;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class CommonMethods extends PageInitializer {
    /**
     * This method will clear a textBox and send text to it
     *
     * @param element
     * @param textToSend
     */
    public static void sendText(WebElement element, String textToSend) {
        element.clear();
        element.sendKeys(textToSend);
    }

    /**
     * This method will return an object of Explicit wait with time set to 20 sec.
     *
     * @return WebDriverWait
     */
    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        return wait;
    }

    /**
     * This method will wait until given element becomes clickable
     *
     * @param element
     */
    public static void waitForClickable(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * This element will wait until given element become visible
     *
     * @param element
     */
    public static void waitForUntilElementBecomeVisible(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated((By) element));
    }

    /**
     * This method will wait till and then click
     *
     * @param element
     */
    public static void click(WebElement element) {
        waitForClickable(element);
        element.click();
    }

    public static JavascriptExecutor getJSExecutor() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        return javascriptExecutor;
    }

    public static void JSClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click", element);
    }

    public static void clickSelectBtn(WebElement element) {
        if (!element.isSelected()) {
            element.click();
        }
    }

    /**
     * @param fileName
     */
    public static byte[] takeScreenShot(String fileName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        byte[] bytes = takesScreenshot.getScreenshotAs(OutputType.BYTES);
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILEPATH + fileName
                    + getTimeStamp("yyyy_MM_dd-HH_mm_ss") + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * @param pattern
     * @return
     */
    public static String getTimeStamp(String pattern) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * This method will click on a radio button or a checkbox by the given list of elements and the value
     *
     * @param radioOrCheckboxes
     * @param value
     */
    public static void clickRadioOrCheckbox(List<WebElement> radioOrCheckboxes, String value) {

        for (WebElement radioOrCheckbox : radioOrCheckboxes) {

            String actualValue = radioOrCheckbox.getAttribute("value").trim();

            if (radioOrCheckbox.isEnabled() && actualValue.equals(value)) {
                click(radioOrCheckbox);
                break;
            }
        }
    }

    public static void clickRadioOrCheckboxText(List<WebElement> radioOrCheckboxes, String text) {

        for (WebElement radioOrCheckbox : radioOrCheckboxes) {

            String actualValue = radioOrCheckbox.getText().trim();

            if (radioOrCheckbox.isEnabled() && actualValue.equals(text)) {
                click(radioOrCheckbox);
                break;
            }
        }
    }

    /**
     * This method select a value from a given dropDown by the given visible text
     * @param dd
     * @param visibleText
     */
    public static void selectDDValue(WebElement dd, String visibleText) {
        try {
            Select select = new Select(dd);

            List<WebElement> options = select.getOptions();

            for (WebElement option : options) {
                if (option.getText().trim().equals(visibleText)) {
                    select.selectByVisibleText(visibleText);
                    break;
                }
            }
        } catch (UnexpectedTagNameException e) {
            e.printStackTrace();
        }
    }

    public static void selectDDValue(WebElement dd, int indexNumber) {
        Select select = new Select(dd);
        select.selectByIndex(indexNumber);
    }

    public static void selectDDValue(String desireValue, WebElement dd) {

        Select select = new Select(dd);

        List<WebElement> values = select.getAllSelectedOptions();

        for (WebElement value : values) {

            String actualValue = value.getAttribute("value");

            if (actualValue.trim().equals(desireValue)) {
                select.selectByVisibleText(actualValue);
                break;
            }
        }
    }

    public static void switchToFrame(int frameIndex) {
        try {
            driver.switchTo().frame(frameIndex);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }

    public static void switchToFrame(String nameOrId) {
        try {
            driver.switchTo().frame(nameOrId);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }

    public static void switchToFrame(WebElement element) {
        try {
            driver.switchTo().frame(element);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }


    public static String mainWindowId = driver.getWindowHandle();

    public static void switchToChildWindow() {

        Set<String> WindowsId = driver.getWindowHandles();

        for (String windowId : WindowsId) {
            if (windowId.equals(mainWindowId)) {
                driver.switchTo().window(windowId);
                break;
            }
        }
    }

    public static void switchToMainWindow() {
        driver.switchTo().window(mainWindowId);
    }

}
