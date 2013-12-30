package com.digivalle.nomina.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	public static Date parseDate(String stringDate, String pattern) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);

		try {
			return dateFormatter.parse(stringDate);
		} catch (ParseException pse) {
			return null;
		}
	}

}
