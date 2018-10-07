package com.epam.lab.gmailTaskDDT.pageObjects;

import com.epam.lab.gmailTaskDDT.webElements.Button;
import com.epam.lab.gmailTaskDDT.webElements.Input;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailMain extends GmailPageObject {
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
        composeButton.click();
    }

    public void printRecipient(String email){
        emailTo.print(email);
    }

    public void printSubject(String subject){
        subjectInput.print(subject);
    }

    public void printEmailBody(String text){
        emailBody.print(text);
    }

    public void submitSend() {
        submitButton.click();
    }

    public boolean alertDialogIsPresent() {
        waitUntilVisible(alertDialog);
        return alertDialog.isDisplayed();
    }

    public void submitAlert() {
        alertSubmitButton.click();
    }

    public void makeDeleteButtonActive(){
        emailInputArea.sendKeys(" ");
    }

    public void deleteAddress() {
        emailAddressDeleteButton.waitUntilVisible(driver);
        emailAddressDeleteButton.waitUntilClickable(driver);
        emailAddressDeleteButton.click();
    }

    public boolean checkEmailNotificationIsPresent() {
        waitUntilVisible(notification);
        return notification.isDisplayed();
    }

    public void waitToSend(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOf(newn));
    }
}
