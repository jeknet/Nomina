package com.digivalle.nomina.utils;

import java.text.DecimalFormat;

public class NumberUtils {

	public static String parseAsIntString(double numericCellValue) {  
		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(0);
		return df.format(numericCellValue); 
	}

	public static Integer parseAsInt(String numericStringCellValue, Integer defaultValue) {
		try{
			return Integer.parseInt(numericStringCellValue);
		}catch(NumberFormatException nfe){
			return defaultValue;
		}
	}
	
	public static Integer parseAsInt(Double numericDoubleCellValue, Integer defaultValue) {
		if(numericDoubleCellValue==null){
			return defaultValue;
		}
		return numericDoubleCellValue.intValue(); 
	}

	public static Double parseAsDouble(String numericStringCellValue, double defaultValue) {
		try{
			return Double.parseDouble(numericStringCellValue);
		}catch(NumberFormatException nfe){
			return defaultValue;
		}
	}

}
