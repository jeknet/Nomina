package com.digivalle.nomina.components.extractors.implementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.digivalle.nomina.components.extractors.RawDataExtractor;
import com.digivalle.nomina.models.DatosEmpleado;
import com.digivalle.nomina.models.NominaInfo;

public class RawDataExtractorExcelImplTest {

	private RawDataExtractor dataExtractor;
	
	@Before
	public void initialize(){
		dataExtractor = new RawDataExtractorExcelImpl();
	}
	
	@Test
	public void readEmpleadorHeaderTest() throws FileNotFoundException, IOException{
		File excelFile = new File("/Projects/DigiValle/Nomina/poc/ejemplo.xls");
		
		NominaInfo nominaInfo = dataExtractor.readNomina(new FileInputStream(excelFile));
		 
		assertEquals("SCD010101ABC", nominaInfo.getEmpleador().getRfc());
		assertEquals("Empresas de Prueba SA de CV", nominaInfo.getEmpleador().getRazonSocial());
		assertEquals("Regimen General", nominaInfo.getEmpleador().getRegimen());
		assertEquals("av juarez", nominaInfo.getEmpleador().getDireccionFiscal().getCalle());
		assertEquals("12345678912345700000", nominaInfo.getEmpleador().getRegistroPatronal());
	}
	
	@Test
	public void readDetalleInfoHeaderTest() throws FileNotFoundException, IOException, ParseException{
		File excelFile = new File("/Projects/DigiValle/Nomina/poc/ejemplo.xls");
		
		NominaInfo nominaInfo = dataExtractor.readNomina(new FileInputStream(excelFile));
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		assertEquals(df.parse("2013-12-01 00:00:00"), nominaInfo.getDetalleNomina().getFechaPago());
		assertEquals(df.parse("2013-12-01 00:00:00"), nominaInfo.getDetalleNomina().getFechaInicioPago());
		assertEquals(df.parse("2013-12-15 00:00:00"), nominaInfo.getDetalleNomina().getFechaFinPago());
		assertEquals("UA", nominaInfo.getDetalleNomina().getSerie());
		assertEquals(new Integer(1), nominaInfo.getDetalleNomina().getFolioInicioNomina());
		assertEquals("Torreon Coahuila", nominaInfo.getDetalleNomina().getLugarExpedicion());
		assertEquals(df.parse("2013-12-01 11:20:00"), nominaInfo.getDetalleNomina().getFechaEmision());
		 
		assertEquals("av juarez", nominaInfo.getDetalleNomina().getDireccionEmision().getCalle()); 
	}
	 
}
