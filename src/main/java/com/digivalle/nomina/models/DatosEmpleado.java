package com.digivalle.nomina.models;

import java.util.HashMap;
import java.util.Map;

public class DatosEmpleado {
	private Map<String, Object> concepts;
	
	public DatosEmpleado(){
		concepts = new HashMap<String, Object>();
	}

	public Map<String, Object> getConcepts() { 
		return concepts;
	}

}
