package com.epam.lab.gmailTaskDDT.businessObjects;

import com.epam.lab.gmailTaskDDT.pageObjects.GmailSignIn;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class GmailSignInBO {
    private static final Logger LOG = Logger.getLogger(GmailSignInBO.class);
    private GmailSignIn gmailSignIn;

    public GmailSignInBO(WebDriver driver){
        gmailSignIn = new GmailSignIn(driver);
    }

    public void signIn(String email, String password){
        LOG.info(String.format("Open gmail sign in page and enter username - %s, password - %s", email, password));
        gmailSignIn.openPage();
        gmailSignIn.printEmail(email);
        gmailSignIn.clickNextButton();
        gmailSignIn.printPassword(password);
        gmailSignIn.clickPasswordNextButton();
    }
}
