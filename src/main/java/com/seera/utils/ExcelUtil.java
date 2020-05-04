package com.seera.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelUtil {

    private String fileName;
    private int sheetIndex;
    Workbook workbook;
    Sheet dataSheet;

    public ExcelUtil(String fileName, int sheetIndex) throws IOException {
        this.fileName = fileName;
        this.sheetIndex = sheetIndex;
        FileInputStream excelFile = new FileInputStream(new File(fileName));
        this.workbook = new XSSFWorkbook(excelFile);
        this.dataSheet = workbook.getSheetAt(sheetIndex);
    }

    public Sheet getDataSheet() {
        return dataSheet;
    }

}
