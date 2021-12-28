package com.hrms.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    static Workbook workbook;
    static Sheet sheet;

    /**
     *
     * @param filePath
     */
    public static void openExcel(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param sheetName
     */
    public static void getSheet(String sheetName) {
        sheet = workbook.getSheet(sheetName);
    }

    /**
     *
     * @return
     */
    public static int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    /**
     *
     * @param rowIndex
     * @return
     */
    public static int getColumnCount(int rowIndex) {
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }

    /**
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    public static String getCellData(int rowIndex, int columnIndex) {
        return sheet.getRow(rowIndex).getCell(columnIndex).toString();
    }

    /**
     *
     * @return
     */
    public static List<Map<String, String>> excelIntoListMap(String filePath, String sheetName) {

        // open excel and get sheet implemented from upper methods.
        openExcel(filePath);
        getSheet(sheetName);

        List<Map<String, String>> listData = new ArrayList<>();

        for (int row = 1; row < getRowCount()-1; row++) {
            //Creating a map for every row
            Map<String, String> map = new LinkedHashMap<>();
            //looping through all cell in the row
            for (int column = 0; column < getColumnCount(row); column++) {
                map.put(getCellData(0, column), getCellData(row, column));
            }
            //adding map into list
            listData.add(map);
        }
        return listData;
    }
}
