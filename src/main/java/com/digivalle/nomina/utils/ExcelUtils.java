package com.digivalle.nomina.utils;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class ExcelUtils {

	public static String readString(HSSFSheet activeSheet, int rowIndex,
			int cellIndex) {
		HSSFCell cell = activeSheet.getRow(rowIndex).getCell(cellIndex);
		if (cell == null) {
			return "";
		}
		if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
			cell.setCellType(HSSFCell.CELL_TYPE_STRING); 
			return NumberUtils.parseAsDoubleString(cell.getStringCellValue());
		}
		return cell.getStringCellValue();
	}
	
	public static String readString(HSSFRow row, int cellIndex) {
		HSSFCell cell = row.getCell(cellIndex);
		if (cell == null) {
			return "";
		}
		if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) { 
			cell.setCellType(HSSFCell.CELL_TYPE_STRING); 
		}
		return cell.getStringCellValue();
	}

	public static Date readDate(HSSFSheet activeSheet, int rowIndex,
			int cellIndex, String dateFormat) {
		HSSFCell cell = activeSheet.getRow(rowIndex).getCell(cellIndex);
		if (cell == null) {
			return null;
		}
		Date value = null;
		if (HSSFCell.CELL_TYPE_STRING == cell.getCellType()) { 
			String stringDate = cell.getStringCellValue();
			value = TimeUtils.parseDate(stringDate, dateFormat);
		} else {
			value = cell.getDateCellValue();
		}
		return value;
	}
	
	public static Date readDate(HSSFRow row, int cellIndex, String dateFormat) {
		HSSFCell cell = row.getCell(cellIndex);
		if (cell == null) {
			return null;
		}
		Date value = null;
		if (HSSFCell.CELL_TYPE_STRING == cell.getCellType()) { 
			String stringDate = cell.getStringCellValue();
			value = TimeUtils.parseDate(stringDate, dateFormat);
		} else {
			value = cell.getDateCellValue();
		}
		return value;
	}

	public static Integer readInteger(HSSFSheet activeSheet, int rowIndex,
			int cellIndex) {
		HSSFCell cell = activeSheet.getRow(rowIndex).getCell(cellIndex);
		if (cell == null) {
			return 0;
		}
		if (HSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
			return NumberUtils.parseAsInt(cell.getStringCellValue(), 0);
		}
		return NumberUtils.parseAsInt(cell.getNumericCellValue(), 0);
	}

	public static Integer readInteger(HSSFRow row, int cellIndex) {
		HSSFCell cell = row.getCell(cellIndex);
		if (cell == null) {
			return 0;
		}
		if (HSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
			return NumberUtils.parseAsInt(cell.getStringCellValue(), 0);
		}
		return NumberUtils.parseAsInt(cell.getNumericCellValue(), 0);
	}

	public static Double readDouble(HSSFRow row, int cellIndex) {
		HSSFCell cell = row.getCell(cellIndex);
		if (cell == null) {
			return 0d;
		}
		if (HSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
			return NumberUtils.parseAsDouble(cell.getStringCellValue(), 0);
		}
		return cell.getNumericCellValue();
	}


}
