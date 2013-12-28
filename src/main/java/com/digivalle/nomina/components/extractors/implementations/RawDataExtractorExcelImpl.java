package com.digivalle.nomina.components.extractors.implementations;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.digivalle.nomina.components.extractors.RawDataExtractor;
import com.digivalle.nomina.models.DatosEmpleado;
import com.digivalle.nomina.models.DetalleNominaInfo;
import com.digivalle.nomina.models.Direccion;
import com.digivalle.nomina.models.EmpleadorInfo;
import com.digivalle.nomina.models.NominaInfo;
import com.digivalle.nomina.utils.ExcelUtils;

public class RawDataExtractorExcelImpl implements RawDataExtractor {

	public NominaInfo readNomina(FileInputStream source) throws IOException {
		FileInputStream excelStream = (FileInputStream) source;
		HSSFWorkbook workbook = new HSSFWorkbook(excelStream);
		HSSFSheet activeSheet = workbook.getSheetAt(0);

		EmpleadorInfo empleador = createEmpleador(activeSheet);
		DetalleNominaInfo detalleNomina = createDetalleNomina(activeSheet);
		
		return new NominaInfo(empleador, detalleNomina);
	}

	private EmpleadorInfo createEmpleador(HSSFSheet activeSheet) {
		String rfc = ExcelUtils.readString(activeSheet, 0, 0);
		String razonSocial = ExcelUtils.readString(activeSheet, 1, 0);
		String regimen = ExcelUtils.readString(activeSheet, 2, 0);
		Direccion direccionFiscal = createDireccion(activeSheet, 3);
		String registroPatronal = ExcelUtils.readString(activeSheet, 23, 0);

		return new EmpleadorInfo(rfc, razonSocial, regimen, direccionFiscal, registroPatronal);
	}
	

	private DetalleNominaInfo createDetalleNomina(HSSFSheet activeSheet) {
		// TODO Auto-generated method stub
		return null;
	}

	private Direccion createDireccion(HSSFSheet activeSheet, int startIndex) {
		String calle = ExcelUtils.readString(activeSheet, startIndex + 0, 0);
		String noExterior = ExcelUtils.readString(activeSheet, startIndex + 1, 0);
		String noInterior = ExcelUtils.readString(activeSheet, startIndex + 2, 0);
		String colonia = ExcelUtils.readString(activeSheet, startIndex + 3, 0);
		String localidad = ExcelUtils.readString(activeSheet, startIndex + 4, 0);
		String referencia = ExcelUtils.readString(activeSheet, startIndex + 5, 0);
		String municipio = ExcelUtils.readString(activeSheet, startIndex + 6, 0);
		String estado = ExcelUtils.readString(activeSheet, startIndex + 7, 0);
		String pais = ExcelUtils.readString(activeSheet, startIndex + 8, 0);
		String cp = ExcelUtils.readString(activeSheet, startIndex + 9, 0);
		
		return new Direccion(calle, noExterior, noInterior, colonia, localidad, referencia, municipio, estado, pais, cp);
	}

	public Map<String, DatosEmpleado> readData(InputStream source)
			throws IOException {
		FileInputStream excelStream = (FileInputStream) source;
		HSSFWorkbook workbook = new HSSFWorkbook(excelStream);
		HSSFSheet activeSheet = workbook.getSheetAt(0);
		int rowsNum = activeSheet.getLastRowNum();

		Map<String, DatosEmpleado> data = new HashMap<String, DatosEmpleado>();

		for (int i = 1; i <= rowsNum; i++) {
			extractData(activeSheet, data, i);
		}

		excelStream.close();
		source.close();
		return data;
	}

	private void extractData(HSSFSheet activeSheet,
			Map<String, DatosEmpleado> data, int i) {
		HSSFRow row = activeSheet.getRow(i);
		String billId = String.format("%d", new Double(row.getCell(0)
				.getNumericCellValue()).intValue());

		if (!data.containsKey(billId)) {
			DatosEmpleado datosEmpleado = new DatosEmpleado();
			data.put(billId, datosEmpleado);
		}

	}

	private DatosEmpleado createEmployeeData(List<Map<String, Object>> values) {
		DatosEmpleado data = new DatosEmpleado();
		for (Map concept : values) {
			String key = String.format("%d", new Date().getTime());
			data.getConcepts().put(key, null);
			System.out.println("added value");
		}
		return data;
	}

}
