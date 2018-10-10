package com.epam.lab.gmailTaskDDT.businessObjects;

import com.epam.lab.gmailTaskDDT.pageObjects.GmailMain;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class GmailMainBO {
    private static final Logger LOG = Logger.getLogger(GmailMainBO.class);
    private GmailMain gmailMain;

    public GmailMainBO(WebDriver driver){
        gmailMain = new GmailMain(driver);
    }

    public void composeEmail(String email, String subject, String text){
        LOG.info(String.format("Try to send letter with receiver - %s, subject - %s, body - %s", email, subject, text));
        gmailMain.composeButtonClick();
        gmailMain.printRecipient(email);
        gmailMain.printSubject(subject);
        gmailMain.printEmailBody(text);
        gmailMain.submitSend();
    }

    public boolean alertIsDisplayed(){
        return gmailMain.alertDialogIsPresent();
    }

    public void submitAlert(){
        gmailMain.submitAlert();
    }

    public void changeEmail(String email){
        LOG.info(String.format("Change receiver's email to %s", email));
        gmailMain.makeDeleteButtonActive();
        gmailMain.deleteAddress();
        gmailMain.printRecipient(email);
        gmailMain.submitSend();
    }

    public boolean notificationIsDisplayed(){
        return  gmailMain.checkEmailNotificationIsPresent();
    }

    public void waitNotificationToDisappear(){
        gmailMain.waitToSend();
    }
}
