package com.epam.lab.gmailTaskDDT.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XLSXParser {
    public static List<Object[]> getSignInData(){
        List<Object[]> data = new ArrayList<>();
        Object[] line;
        try {
            FileInputStream excelFile = new FileInputStream("src/test/resources/signIn.xlsx");
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            for(Row row : datatypeSheet){
                line = new String[2];
                for(Cell cell: row){
                    line[cell.getColumnIndex()] = cell.getStringCellValue();
                }
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
