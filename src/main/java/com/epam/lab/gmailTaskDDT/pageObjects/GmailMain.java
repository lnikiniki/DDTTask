package com.epam.lab.gmailTaskDDT.pageObjects;

import com.epam.lab.gmailTaskDDT.webElements.Button;
import com.epam.lab.gmailTaskDDT.webElements.Input;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailMain extends GmailPageObject {
    private static final Logger LOG = Logger.getLogger(GmailMain.class);
    @FindBy(css = "div.z0")
    private Button composeButton;

    @FindBy(name = "to")
    private Input emailTo;

    @FindBy(name = "subjectbox")
    private Input subjectInput;

    @FindBy(css = "div[role = 'textbox']")
    private Input emailBody;

    @FindBy(css = "td.gU.Up")
    private Button submitButton;

    @FindBy(css = "div[role = 'alertdialog']")
    private WebElement alertDialog;

    @FindBy(name = "ok")
    private Button alertSubmitButton;

    @FindBy(css = "div.vM")
    private Button emailAddressDeleteButton;

    @FindBy(css = "div.aoD.hl")
    private Input emailInputArea;

    @FindBy(css = "div.vh > span.aT")
    private WebElement notification;

    @FindBy(xpath = "//*[@id='link_undo' and @role='link']")
    private WebElement newn;

    public GmailMain(WebDriver driver) {
        super(driver);
    }

    public void composeButtonClick(){
        LOG.info("Click on compose button.");
        composeButton.click();
    }

    public void printRecipient(String email){
        LOG.info("Input email.");
        emailTo.print(email);
    }

    public void printSubject(String subject){
        LOG.info("Input subject.");
        subjectInput.print(subject);
    }

    public void printEmailBody(String text){
        LOG.info("Input body.");
        emailBody.print(text);
    }

    public void submitSend() {
        LOG.info("Click on submit button.");
        submitButton.click();
    }

    public boolean alertDialogIsPresent() {
        waitUntilVisible(alertDialog);
        return alertDialog.isDisplayed();
    }

    public void submitAlert() {
        LOG.info("Submit wrong email alert.");
        alertSubmitButton.click();
    }

    public void makeDeleteButtonActive(){
        emailInputArea.sendKeys(" ");
    }

    public void deleteAddress() {
        LOG.info("Delete wrong email");
        emailAddressDeleteButton.waitUntilVisible(driver);
        emailAddressDeleteButton.waitUntilClickable(driver);
        emailAddressDeleteButton.click();
    }

    public boolean checkEmailNotificationIsPresent() {
        waitUntilVisible(notification);
        return notification.isDisplayed();
    }

    public void waitToSend(){
        LOG.info("Wait for email to be send");
        new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOf(newn));
    }
}
