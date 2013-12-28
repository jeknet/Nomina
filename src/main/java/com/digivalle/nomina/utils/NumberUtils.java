package com.digivalle.nomina.utils;

import java.text.DecimalFormat;

public class NumberUtils {

	public static String parseAsIntString(double numericCellValue) {  
		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(0);
		return df.format(numericCellValue); 
	}

}
