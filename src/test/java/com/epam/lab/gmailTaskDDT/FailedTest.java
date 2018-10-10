package com.epam.lab.gmailTaskDDT;

import com.epam.lab.gmailTaskDDT.businessObjects.GmailMainBO;
import com.epam.lab.gmailTaskDDT.businessObjects.GmailSignInBO;
import com.epam.lab.gmailTaskDDT.drivers.DriverPool;
import com.epam.lab.gmailTaskDDT.utils.PropertyParser;
import com.epam.lab.gmailTaskDDT.utils.XMLParser;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

public class FailedTest {
    private static final Logger LOG = Logger.getLogger(FailedTest.class);
    private String correctEmail;
    private String emailSubject;
    private String emailText;

    @BeforeClass
    public void preparing() {
        System.setProperty("webdriver.chrome.driver", new PropertyParser("src/main/resources/driver.properties").getDriverPath());
        XMLParser emailDataParser = new XMLParser("src/test/resources/email.xml");
        correctEmail = emailDataParser.getName("correct");
        emailSubject = emailDataParser.getName("subject");
        emailText = emailDataParser.getName("text");
    }

    @Test(dataProvider = "SignInXML")
    public void sendEmail(String email, String password) {
        LOG.info("Starting test");
        GmailSignInBO signIn = new GmailSignInBO(DriverPool.getDriver());
        signIn.signIn(email, password);
        GmailMainBO main = new GmailMainBO(DriverPool.getDriver());
        main.composeEmail(correctEmail, emailSubject, emailText);
        Assert.assertTrue(main.alertIsDisplayed());
        LOG.info("Ending test");
    }

    @AfterMethod
    public void quit(){
        DriverPool.getDriver().quit();
    }

    @DataProvider(name = "SignInXML", parallel = true)
    public static Iterator<Object[]> signInXML(){
        return XMLParser.getSignInData().iterator();
    }
}
