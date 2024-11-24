package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static void main(String[] args) throws IOException {

		String fileLocation="testdata/Login_data.xlsx";
		try (XSSFWorkbook wbook = new XSSFWorkbook (fileLocation)) {
			XSSFSheet sheet = wbook.getSheetAt(0);

			XSSFRow row =sheet.getRow(1);
			XSSFCell cell = row.getCell(0);
			String value = cell.getStringCellValue();
			System.out.println(value);
		}
	}
}
