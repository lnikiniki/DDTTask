package com.epam.lab.gmailTaskDDT;

import com.epam.lab.gmailTaskDDT.businessObjects.GmailMainBO;
import com.epam.lab.gmailTaskDDT.businessObjects.GmailSignInBO;
import com.epam.lab.gmailTaskDDT.drivers.DriverPool;
import com.epam.lab.gmailTaskDDT.utils.CSVParser;
import com.epam.lab.gmailTaskDDT.utils.PropertyParser;
import com.epam.lab.gmailTaskDDT.utils.XLSXParser;
import com.epam.lab.gmailTaskDDT.utils.XMLParser;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Iterator;

public class GmailTest {
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
        GmailSignInBO signIn = new GmailSignInBO(DriverPool.getDriver());
        signIn.signIn(email, password);
        GmailMainBO main = new GmailMainBO(DriverPool.getDriver());
        main.composeEmail(incorrectEmail, emailSubject, emailText);
        Assert.assertTrue(main.alertIsDisplayed());
        main.submitAlert();
        main.changeEmail(correctEmail);
        Assert.assertTrue(main.notificationIsDisplayed());
        main.waitNotificationToDisappear();
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
