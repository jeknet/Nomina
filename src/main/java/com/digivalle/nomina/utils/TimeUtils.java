package com.digivalle.nomina.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class TimeUtils {
	public static Date parseDate(String stringDate, String pattern) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);

		try {
			return dateFormatter.parse(stringDate);
		} catch (ParseException pse) {
			return null;
		}
	}

	public static XMLGregorianCalendar createXmlGregorianCalendar(Date date) {
		try {
			GregorianCalendar gregory = new GregorianCalendar();
			gregory.setTime(date);

			XMLGregorianCalendar calendar = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(gregory);

			return calendar;
		} catch (DatatypeConfigurationException ce) {
			return null;
		}
	}

}
