package com.epam.lab.gmailTaskDDT.pageObjects;

import com.epam.lab.gmailTaskDDT.utils.PropertyParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class GmailPageObject {
    WebDriver driver;
    private int wait;

    GmailPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
        wait = new PropertyParser("src/main/resources/driver.properties").getWaitTime();
    }

    void waitUntilVisible(WebElement element) {
        new WebDriverWait(driver, wait).until(ExpectedConditions.visibilityOf(element));
    }

    void waitUntilClickable(WebElement element) {
        new WebDriverWait(driver, wait).until(ExpectedConditions.elementToBeClickable(element));
    }

}
