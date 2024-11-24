package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static Object[][] getExcelData(String filePath, String sheetName) throws IOException {
        Workbook workbook;
        Sheet sheet;

        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        List<Object[]> filteredData = new ArrayList<>();

        for (int i = 1; i < rowCount; i++) { 
            String firstCell = sheet.getRow(i).getCell(0).toString(); 
            if (firstCell == null || firstCell.trim().isEmpty()) {
                continue; 
            }

            Object[] rowData = new Object[colCount];
            for (int j = 0; j < colCount; j++) {
                rowData[j] = sheet.getRow(i).getCell(j) == null ? "" : sheet.getRow(i).getCell(j).toString();
            }
            filteredData.add(rowData);
        }

        fis.close();
        workbook.close();
        return filteredData.toArray(new Object[0][]);
    }

    public static String[] getLoginCredentials(String filePath, String sheetName) throws IOException {
        Workbook workbook;
        Sheet sheet;

        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        String username = sheet.getRow(1).getCell(0).toString();
        String password = sheet.getRow(1).getCell(1).toString();

        fis.close();
        workbook.close();
        return new String[]{username, password};
    }
}
