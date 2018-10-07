package com.epam.lab.gmailTaskDDT.drivers;

import com.epam.lab.gmailTaskDDT.utils.PropertyParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverPool {

//    private ThreadLocal<WebDriver> localDriver = new ThreadLocal<>() {
//        @Override
//        protected WebDriver initialValue() {
//            WebDriver chrome = new ChromeDriver();
//            int wait = new PropertyParser("src/main/resources/driver.properties").getWaitTime();
//            chrome.manage().timeouts().implicitlyWait(wait, TimeUnit.SECONDS);
//            return chrome;
//        }
//
//        @Override
//        public void remove() {
//            WebDriver driver = get();
//            if (driver != null) driver.close();
//            super.remove();
//        }
//
//        @Override
//        public void set(WebDriver value) {
//            throw new UnsupportedOperationException();
//        }
//    };
//
//    public WebDriver getDriver(){
//        return localDriver.get();
//    }

    private static ThreadLocal<WebDriver> chromeDriverPool = ThreadLocal.withInitial(() -> {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(new PropertyParser("src/main/resources/driver.properties").getWaitTime(), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    });

    public static WebDriver getDriver() {
        return chromeDriverPool.get();
    }
}
