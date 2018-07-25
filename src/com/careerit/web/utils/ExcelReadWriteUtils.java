package com.careerit.web.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadWriteUtils {
	
	public static String readFromExcel(String filePath,int row,int column) throws IOException{
		FileInputStream fs = new FileInputStream(filePath);
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheetAt(0);
		DataFormatter formatter = new DataFormatter();
		String contentData=formatter.formatCellValue(sheet.getRow(row).getCell(column));
		//String lang1=formatter.formatCellValue(sheet.getRow(1).getCell(1));
		return contentData;
	}
	
	public static void writeToExcel(String filePath,String data,int rowNumber,int columnNumber) throws IOException{
		FileInputStream fs = new FileInputStream(filePath);
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(rowNumber);
		Cell cell = row.getCell(columnNumber);
		if (cell == null) {
			cell = row.createCell(columnNumber);
			cell.setCellValue(data);
		} else {
			cell.setCellValue(data);
		}
		FileOutputStream outputStream = new FileOutputStream(filePath) ;
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}

}
