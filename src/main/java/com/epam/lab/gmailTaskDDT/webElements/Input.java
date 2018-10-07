package com.epam.lab.gmailTaskDDT.webElements;

import org.openqa.selenium.WebElement;

public class Input extends Element {
    public Input(WebElement element) {
        super(element);
    }

    public void print(String text) {
        super.clear();
        super.sendKeys(text);
    }
}
