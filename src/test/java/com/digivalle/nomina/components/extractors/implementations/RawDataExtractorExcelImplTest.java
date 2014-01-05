package com.digivalle.nomina.components.extractors.implementations;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import com.digivalle.nomina.components.extractors.RawDataExtractor;
import com.digivalle.nomina.models.DetalleNominaEmpleado;
import com.digivalle.nomina.models.NominaInfo;

public class RawDataExtractorExcelImplTest {

	private RawDataExtractor dataExtractor;
	private InputStream dataStream;
	
	@Before
	public void initialize(){
		dataExtractor = new RawDataExtractorExcelImpl();
		dataStream = getClass().getClassLoader().getResourceAsStream("ejemplo.xls");
	}
	
	@Test
	public void readEmpleadorHeaderTest() throws FileNotFoundException, IOException{
		NominaInfo nominaInfo = dataExtractor.readNomina(dataStream);
		 
		assertEquals("AAA010101AAA", nominaInfo.getEmpleador().getRfc());
		assertEquals("Empresas de Prueba SA de CV", nominaInfo.getEmpleador().getRazonSocial());
		assertEquals("Regimen General", nominaInfo.getEmpleador().getRegimen());
		assertEquals("av juarez", nominaInfo.getEmpleador().getDireccionFiscal().getCalle());
		assertEquals("12345678912345700000", nominaInfo.getEmpleador().getRegistroPatronal());
	}
	
	@Test
	public void readDetalleInfoHeaderTest() throws FileNotFoundException, IOException, ParseException{ 
		NominaInfo nominaInfo = dataExtractor.readNomina(dataStream);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		assertEquals(df.parse("2013-12-01 00:00:00"), nominaInfo.getDetalleNomina().getFechaPago());
		assertEquals(df.parse("2013-12-01 00:00:00"), nominaInfo.getDetalleNomina().getFechaInicioPago());
		assertEquals(df.parse("2013-12-15 00:00:00"), nominaInfo.getDetalleNomina().getFechaFinPago());
		assertEquals("UA", nominaInfo.getDetalleNomina().getSerie());
		assertEquals(new Integer(1), nominaInfo.getDetalleNomina().getFolioInicioNomina());
		assertEquals("Torreon Coahuila", nominaInfo.getDetalleNomina().getLugarExpedicion());
		 
		assertEquals("av juarez", nominaInfo.getDetalleNomina().getDireccionEmision().getCalle()); 
	}
	
	@Test
	public void readDetalleEmpleadosTest() throws FileNotFoundException, IOException, ParseException{ 
		NominaInfo nominaInfo = dataExtractor.readNomina(dataStream);
		assertEquals(1, nominaInfo.getDetalleNominaEmpleados().size());
		DetalleNominaEmpleado nominaEmpleado = nominaInfo.getDetalleNominaEmpleados().get(0);
		assertEquals("8888", nominaEmpleado.getEmpleado().getIdInterno());
		assertEquals("Juan Perez Mendez", nominaEmpleado.getEmpleado().getNombreCompleto());
		assertEquals("JJJJ010101U89", nominaEmpleado.getEmpleado().getRfc());
		assertEquals("AAAA990909HAABBBA9", nominaEmpleado.getEmpleado().getCurp());
		assertEquals(new Double("21000"), nominaEmpleado.getEmpleado().getTotalPercepcionesGravado());
		
		assertEquals(3, nominaEmpleado.getPercepciones().size());
		assertEquals(2, nominaEmpleado.getDeducciones().size());
		assertEquals(new Integer(2), nominaEmpleado.getIncapacidad().getDiasIncapacidad()); 
		assertEquals(new Integer(20), nominaEmpleado.getHorasExtras().getHorasExtras());
	}
	 
}
