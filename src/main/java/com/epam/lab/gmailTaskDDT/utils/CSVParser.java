package com.epam.lab.gmailTaskDDT.utils;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    public List<Object[]> getSignIndata(){
        List<Object[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("src/test/resources/signIn.csv"))) {
            Object[] line;
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
