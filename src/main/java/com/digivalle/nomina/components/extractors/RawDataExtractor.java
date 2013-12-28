package com.digivalle.nomina.components.extractors;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.digivalle.nomina.models.DatosEmpleado;
import com.digivalle.nomina.models.NominaInfo;

public interface RawDataExtractor {
	public Map<String, DatosEmpleado> readData(InputStream source) throws IOException;

	public NominaInfo readNomina(FileInputStream fileInputStream) throws IOException;
}
