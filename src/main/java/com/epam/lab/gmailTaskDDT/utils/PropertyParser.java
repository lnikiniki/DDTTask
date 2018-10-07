package com.epam.lab.gmailTaskDDT.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyParser {
    private Properties properties;

    public PropertyParser(String path) {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getWaitTime() {
        return Integer.parseInt(properties.getProperty("waitTime"));
    }

    public String getDriverPath() {
        return properties.getProperty("chromedriver");
    }
}
