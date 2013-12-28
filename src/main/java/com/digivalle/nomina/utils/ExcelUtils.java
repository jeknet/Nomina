package com.digivalle.nomina.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class ExcelUtils {
 

	public static String readString(HSSFSheet activeSheet, int rowIndex, int cellIndex) {
		HSSFCell cell = activeSheet.getRow(rowIndex).getCell(cellIndex);
		if(cell==null){
			return "";
		}
		if(HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()){ 
			return NumberUtils.parseAsIntString(cell.getNumericCellValue());
		}
		return cell.getStringCellValue(); 
	}

}
