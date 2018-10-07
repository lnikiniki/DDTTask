package com.epam.lab.gmailTaskDDT.utils;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    private Document document;

    public XMLParser(String pathToFile) {
        try {
            File file = new File(pathToFile);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public String getName(String name) {
        return document.getElementsByTagName(name).item(0).getTextContent();
    }

    private String[] getNames(String name){
        String[] strings = new String[3];
        for (int i = 0; i < strings.length; i++){
                strings[i] = document.getElementsByTagName(name).item(i).getTextContent();
        }
        return strings;
    }
//
//    public String[][] getData(){
//        String[][] strings = new String[3][2];
//        String[] emails = getNames("email");
//        String[] passwords = getNames("password");
//        for (int i = 0; i < 3; i++){
//            strings[i][0] = emails[i];
//            strings[i][1] = passwords[i];
//        }
//        return strings;
//    }

    public static List<Object[]> getSignInData(){
        XMLParser xmlParser = new XMLParser("src/test/resources/signIn.xml");
        Object[] emails = xmlParser.getNames("email");
        Object[] passwords = xmlParser.getNames("password");
        List<Object[]> data = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            Object[] object = new Object[2];
            object[0] = emails[i];
            object[1] = passwords[i];
            data.add(object);
        }
        return data;
    }

}
