package com.digivalle.nomina.components.extractors.implementations;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import mx.bigdata.sat.common.nomina.schema.Nomina;

import org.junit.Before;
import org.junit.Test;

import com.digivalle.nomina.components.extractors.NominaDataExtractor;
 
public class NominaDataExtractorExcelImplTest {

	private NominaDataExtractor dataExtractor;
	
	@Before
	public void initialize(){
		dataExtractor = new NominaDataExtractorExcelImpl();
	}
	
	@Test
	public void testParseData(){
		List<Nomina> data = dataExtractor.retrieveNominaData();
		assertNotNull(data);
//		assertNotNull(data.getPercepciones());
//		assertFalse(data.getPercepciones().getPercepcion().isEmpty());
	}
}
