package com.digivalle.nomina.components.extractors.implementations;

import java.util.Arrays;
import java.util.List;

import mx.bigdata.sat.common.nomina.schema.Nomina;
import mx.bigdata.sat.common.nomina.schema.Nomina.Percepciones;
import mx.bigdata.sat.common.nomina.schema.Nomina.Percepciones.Percepcion;

import com.digivalle.nomina.components.extractors.NominaDataExtractor;

public class NominaDataExtractorExcelImpl implements NominaDataExtractor{

	public List<Nomina> retrieveNominaData() {
		Percepciones percepciones = new Percepciones();
		percepciones.getPercepcion().add(new Percepcion());
		Nomina nomina = new Nomina();
		nomina.setPercepciones(percepciones);
		return Arrays.asList(nomina);
	}

}
