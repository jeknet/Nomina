package com.digivalle.nomina.components.extractors;

import java.util.List;

import mx.bigdata.sat.common.nomina.schema.Nomina;

public interface NominaDataExtractor {

	List<Nomina> retrieveNominaData();
	
}
