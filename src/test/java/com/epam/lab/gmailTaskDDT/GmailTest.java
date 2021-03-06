package com.epam.lab.gmailTaskDDT;

import com.epam.lab.gmailTaskDDT.businessObjects.GmailMainBO;
import com.epam.lab.gmailTaskDDT.businessObjects.GmailSignInBO;
import com.epam.lab.gmailTaskDDT.drivers.DriverPool;
import com.epam.lab.gmailTaskDDT.utils.*;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Iterator;

@Listeners(TestListener.class)
public class GmailTest {
    private static final Logger LOG = Logger.getLogger(GmailTest.class);
    private String correctEmail;
    private String incorrectEmail;
    private String emailSubject;
    private String emailText;

    @BeforeClass
    public void preparing() {
        System.setProperty("webdriver.chrome.driver", new PropertyParser("src/main/resources/driver.properties").getDriverPath());
        XMLParser emailDataParser = new XMLParser("src/test/resources/email.xml");
        correctEmail = emailDataParser.getName("correct");
        incorrectEmail = emailDataParser.getName("incorrect");
        emailSubject = emailDataParser.getName("subject");
        emailText = emailDataParser.getName("text");
    }

    @Test(dataProvider = "SignInXML")
    public void sendEmail(String email, String password) {
        LOG.info("Starting test");
        GmailSignInBO signIn = new GmailSignInBO(DriverPool.getDriver());
        signIn.signIn(email, password);
        GmailMainBO main = new GmailMainBO(DriverPool.getDriver());
        main.composeEmail(incorrectEmail, emailSubject, emailText);
        Assert.assertTrue(main.alertIsDisplayed());
        main.submitAlert();
        main.changeEmail(correctEmail);
        Assert.assertTrue(main.notificationIsDisplayed());
        main.waitNotificationToDisappear();
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

    @DataProvider(name = "SignInCSV", parallel = true)
    public static Iterator<Object[]> signInCSV(){
        return new CSVParser().getSignIndata().iterator();
    }

    @DataProvider(name = "SignInXSLX", parallel = true)
    public static Iterator<Object[]> signInXLSX(){
        return XLSXParser.getSignInData().iterator();
    }
}
