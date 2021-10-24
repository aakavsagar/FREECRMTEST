package com.qa.utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	String excelPath;
	FileInputStream fis;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;

	public ExcelUtility(String excelPath, String sheetName) throws IOException {
		this.excelPath = excelPath;
		fis = new FileInputStream(excelPath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
	}

	public int getRowCount() {
		int count = sheet.getLastRowNum();
		return count;
	}

	public int getColCount(int num) {
		return sheet.getRow(num).getLastCellNum();
	}

	public String getCellData(int rowNum, int colNum) throws IOException {
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		String value = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case STRING:
				value = cell.getStringCellValue();
				break;
			case NUMERIC:
				value = String.valueOf((int) cell.getNumericCellValue());
				break;

			default:
				break;
			}
		}
		return value;
	}

	@Override
	public void finalize() throws Throwable {
		workbook.close();
	}
}
