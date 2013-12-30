package com.digivalle.nomina.components.extractors;

import java.io.IOException;
import java.io.InputStream;

import com.digivalle.nomina.models.NominaInfo;

public interface RawDataExtractor { 
	public NominaInfo readNomina(InputStream fileInputStream) throws IOException;
}
