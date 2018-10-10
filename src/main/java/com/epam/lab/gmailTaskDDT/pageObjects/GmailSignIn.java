package com.epam.lab.gmailTaskDDT.pageObjects;

import com.epam.lab.gmailTaskDDT.webElements.Button;
import com.epam.lab.gmailTaskDDT.webElements.Input;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class GmailSignIn extends GmailPageObject {
    private static final Logger LOG = Logger.getLogger(GmailSignIn.class);

    @FindBy(id = "identifierId")
    private Input emailInput;

    @FindBy(id = "identifierNext")
    private Button nextButton;

    @FindBy(name = "password")
    private Input passwordInput;

    @FindBy(css = "div#passwordNext")
    private Button passwordNextButton;

    public GmailSignIn(WebDriver driver) {
        super(driver);
    }

//    public void enterEmail(String email) {
//        emailInput.print(email);
//        nextButton.click();
//    }
//
//    public void enterPassword(String password) {
//        passwordInput.sendKeys(password);
//        passwordNextButton.waitUntilVisible(driver);
//        passwordNextButton.waitUntilClickable(driver);
//        passwordNextButton.click();
//    }

    public void printEmail(String email){
        LOG.info("Input username");
        emailInput.print(email);
    }

    public void clickNextButton(){
        LOG.info("Click on 'Next' button");
        nextButton.click();
    }

    public void printPassword(String password){
        LOG.info("Input password");
        passwordInput.sendKeys(password);
    }

    public void clickPasswordNextButton(){
        LOG.info("Click on 'Next' button");
        passwordNextButton.waitUntilVisible(driver);
        passwordNextButton.waitUntilClickable(driver);
        passwordNextButton.click();
    }

    public void openPage(){
        LOG.info("Navigate to page");
        driver.get("https://gmail.com");
    }
}
