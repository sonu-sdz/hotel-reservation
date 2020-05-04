package com.seera.managers;

import com.google.gson.JsonObject;
import com.seera.models.Dates;
import com.seera.models.Guest;
import com.seera.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {

    public static List<String> generateSearchRequestDataFromExcel() throws IOException {
        Sheet sheet = new ExcelUtil(System.getProperty("user.dir") + "/src/test/resources/postSearchRequest.xlsx",0).getDataSheet();
        List<Map<String, String>> sheetMap = getSheetMap(sheet);
        List<String> bodyData = new ArrayList<>();
        for(Map<String, String> rowData: sheetMap){
            JsonObject jsonObject = new JsonObject();
            for(String key:rowData.keySet()){
                jsonObject.addProperty(key,rowData.get(key));
            }
            bodyData.add(jsonObject.toString());
        }
        return bodyData;
    }

    private static List<Map<String, String>> getSheetMap(Sheet sheet) {
        List<Map<String, String>> sheetMap = new ArrayList<>();
        for(int i=sheet.getFirstRowNum() + 1; i<=sheet.getLastRowNum(); i++){
            Row titleRow = sheet.getRow(sheet.getFirstRowNum());
            Map<String, String> rowMap = new HashMap<>();
            Row row = sheet.getRow(i);
            for(int j = row.getFirstCellNum(); j<row.getLastCellNum();j++){
                String fieldName = titleRow.getCell(j).getStringCellValue();
                rowMap.put(fieldName,row.getCell(j).getStringCellValue());
            }
            sheetMap.add(rowMap);
        }
        return sheetMap;
    }



}
