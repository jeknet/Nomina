package com.digivalle.nomina.components.extractors.implementations;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.digivalle.nomina.components.extractors.RawDataExtractor;
import com.digivalle.nomina.exceptions.InvalidDataSetException;
import com.digivalle.nomina.models.DetalleDeduccion;
import com.digivalle.nomina.models.DetalleHorasExtras;
import com.digivalle.nomina.models.DetalleIncapacidad;
import com.digivalle.nomina.models.DetalleNominaEmpleado;
import com.digivalle.nomina.models.DetalleNominaInfo;
import com.digivalle.nomina.models.DetallePercepcion;
import com.digivalle.nomina.models.Direccion;
import com.digivalle.nomina.models.Empleado;
import com.digivalle.nomina.models.EmpleadorInfo;
import com.digivalle.nomina.models.NominaInfo;
import com.digivalle.nomina.utils.ExcelUtils;

public class RawDataExtractorExcelImpl implements RawDataExtractor {

	public NominaInfo readNomina(InputStream source) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook(source);
		HSSFSheet activeSheet = workbook.getSheetAt(0);

		EmpleadorInfo empleador = createEmpleador(activeSheet);
		DetalleNominaInfo detalleNomina = createDetalleNomina(activeSheet);
		List<DetalleNominaEmpleado> detalleEmpleados = createDetalleEmpleados(activeSheet);

		return new NominaInfo(empleador, detalleNomina, detalleEmpleados);
	}

	private EmpleadorInfo createEmpleador(HSSFSheet activeSheet) {
		String rfc = ExcelUtils.readString(activeSheet, 0, 0);
		String razonSocial = ExcelUtils.readString(activeSheet, 1, 0);
		String regimen = ExcelUtils.readString(activeSheet, 2, 0);
		Direccion direccionFiscal = createDireccion(activeSheet, 3);
		String registroPatronal = ExcelUtils.readString(activeSheet, 23, 0);

		return new EmpleadorInfo(rfc, razonSocial, regimen, direccionFiscal,
				registroPatronal);
	}

	private DetalleNominaInfo createDetalleNomina(HSSFSheet activeSheet) {
		Direccion direccionEmision = createDireccion(activeSheet, 13);
		Date fechaPago = ExcelUtils.readDate(activeSheet, 24, 0, "");
		Date fechaInicioPago = ExcelUtils.readDate(activeSheet, 25, 0, "");
		Date fechaFinPago = ExcelUtils.readDate(activeSheet, 26, 0, "");
		String serie = ExcelUtils.readString(activeSheet, 27, 0);
		Integer folioInicioNomina = ExcelUtils.readInteger(activeSheet, 28, 0);
		String lugarExpedicion = ExcelUtils.readString(activeSheet, 29, 0);

		return new DetalleNominaInfo(direccionEmision, fechaPago,
				fechaInicioPago, fechaFinPago, serie, folioInicioNomina,
				lugarExpedicion);
	}

	private List<DetalleNominaEmpleado> createDetalleEmpleados(
			HSSFSheet activeSheet) {
		List<DetalleNominaEmpleado> detalleEmpleados = new LinkedList<DetalleNominaEmpleado>();
		Map<String, List<HSSFRow>> detalleNomina = readDatosNomina(activeSheet);

		for (String key : detalleNomina.keySet()) {
			DetalleNominaEmpleado detalleNominaEmpleado = readDetalleNominaEmpleado(detalleNomina
					.get(key));
			if (detalleNominaEmpleado != null) {
				detalleEmpleados.add(detalleNominaEmpleado);
			}
		}
		return detalleEmpleados;
	}

	private DetalleNominaEmpleado readDetalleNominaEmpleado(List<HSSFRow> list) {
		try {
			Empleado empleado = readEmpleado(list);
			Integer diasPagados = readDiasPagados(list);
			List<DetallePercepcion> percepciones = readPercepciones(list);
			List<DetalleDeduccion> deducciones = readDeducciones(list);
			DetalleIncapacidad incapacidad = readIncapacidad(list);
			DetalleHorasExtras horasExtras = readHorasExtras(list);

			return new DetalleNominaEmpleado(empleado, diasPagados,
					percepciones, deducciones, incapacidad, horasExtras);
		} catch (InvalidDataSetException idse) {
			return null;
		}
	}

	private Empleado readEmpleado(List<HSSFRow> rows) {
		for (HSSFRow row : rows) {
			String tipoLinea = ExcelUtils.readString(row, 1);
			if (TiposLineaExcel.LINEA_EMPLEADO.equals(tipoLinea)) {
				return readDetalleEmpleado(row);
			}
		}
		throw new InvalidDataSetException(
				"Linea de empleado no existente, registro inválido");
	}

	private Empleado readDetalleEmpleado(HSSFRow row) {
		String idInterno = ExcelUtils.readString(row, 2);
		String nombreCompleto = ExcelUtils.readString(row, 3);
		String rfc = ExcelUtils.readString(row, 4);
		String curp = ExcelUtils.readString(row, 5);
		Integer tipoRegimen = ExcelUtils.readInteger(row, 6);
		String nss = ExcelUtils.readString(row, 7);
		String departamento = ExcelUtils.readString(row, 9);
		String clabe = ExcelUtils.readString(row, 10);
		String banco = ExcelUtils.readString(row, 11);
		Date fechaInicioLaboral = ExcelUtils.readDate(row, 12, "");
		Integer antiguedad = ExcelUtils.readInteger(row, 13);
		String puesto = ExcelUtils.readString(row, 14);
		String tipoContrato = ExcelUtils.readString(row, 15);
		String tipoJornada = ExcelUtils.readString(row, 16);
		String periodicidadPago = ExcelUtils.readString(row, 17);
		Double salarioBase = ExcelUtils.readDouble(row, 18);
		Integer riesgoPuesto = ExcelUtils.readInteger(row, 19);
		Double salarioBaseIntegrado = ExcelUtils.readDouble(row, 20);
		Double totalPercepcionesGravado = ExcelUtils.readDouble(row, 21);
		Double totalPercepcionesExento = ExcelUtils.readDouble(row, 22);
		Double totalDeduccionesGravado = ExcelUtils.readDouble(row, 23);
		Double totalDeduccionesExento = ExcelUtils.readDouble(row, 24);

		return new Empleado(0, idInterno, nombreCompleto, rfc, curp,
				tipoRegimen, nss, departamento, clabe, banco,
				fechaInicioLaboral, antiguedad, puesto, tipoContrato,
				tipoJornada, periodicidadPago, salarioBase, riesgoPuesto,
				salarioBaseIntegrado, totalPercepcionesGravado,
				totalPercepcionesExento, totalDeduccionesGravado,
				totalDeduccionesExento);
	}

	private Integer readDiasPagados(List<HSSFRow> rows) {
		for (HSSFRow row : rows) {
			String tipoLinea = ExcelUtils.readString(row, 1);
			if (TiposLineaExcel.LINEA_EMPLEADO.equals(tipoLinea)) {
				return ExcelUtils.readInteger(row, 8);
			}
		}
		throw new InvalidDataSetException(
				"Linea de empleado no existente, registro inválido");
	}

	private List<DetallePercepcion> readPercepciones(List<HSSFRow> rows) {
		List<DetallePercepcion> percepciones = new LinkedList<DetallePercepcion>();
		for (HSSFRow row : rows) {
			String tipoLinea = ExcelUtils.readString(row, 1);
			if (TiposLineaExcel.LINEA_PERCEPCION.equals(tipoLinea)) {
				DetallePercepcion detallePercepcion = readDetallePercepcion(row);
				if (detallePercepcion != null) {
					percepciones.add(detallePercepcion);
				}
			}
		}
		if (percepciones.isEmpty()) {
			throw new InvalidDataSetException(
					"Linea de percepciones no existente, registro inválido");
		}
		return percepciones;
	}

	private DetallePercepcion readDetallePercepcion(HSSFRow row) {
		String tipoPercepcion = ExcelUtils.readString(row, 2);
		String clave = ExcelUtils.readString(row, 3);
		String concepto = ExcelUtils.readString(row, 4);
		Double importeGravado = ExcelUtils.readDouble(row, 5);
		Double importeExcento = ExcelUtils.readDouble(row, 6);

		return new DetallePercepcion(tipoPercepcion, clave, concepto,
				importeGravado, importeExcento);
	}

	private List<DetalleDeduccion> readDeducciones(List<HSSFRow> rows) {
		List<DetalleDeduccion> deducciones = new LinkedList<DetalleDeduccion>();
		for (HSSFRow row : rows) {
			String tipoLinea = ExcelUtils.readString(row, 1);
			if (TiposLineaExcel.LINEA_DEDUCCION.equals(tipoLinea)) {
				DetalleDeduccion detalleDeduccion = readDetalleDeduccion(row);
				if (detalleDeduccion != null) {
					deducciones.add(detalleDeduccion);
				}
			}
		}
		return deducciones;
	}

	private DetalleDeduccion readDetalleDeduccion(HSSFRow row) {
		String tipoPercepcion = ExcelUtils.readString(row, 2);
		String clave = ExcelUtils.readString(row, 3);
		String concepto = ExcelUtils.readString(row, 4);
		Double importeGravado = ExcelUtils.readDouble(row, 5);
		Double importeExcento = ExcelUtils.readDouble(row, 6);

		return new DetalleDeduccion(tipoPercepcion, clave, concepto,
				importeGravado, importeExcento);
	}

	private DetalleIncapacidad readIncapacidad(List<HSSFRow> rows) {
		for (HSSFRow row : rows) {
			String tipoLinea = ExcelUtils.readString(row, 1);
			if (TiposLineaExcel.LINEA_INCAPACIDAD.equals(tipoLinea)) {
				return readDetalleIncapacidad(row);
			}
		}
		return null;
	}

	private DetalleIncapacidad readDetalleIncapacidad(HSSFRow row) {
		Integer diasIncapacidad = ExcelUtils.readInteger(row, 2);
		Integer tipoIncapacidad = ExcelUtils.readInteger(row, 3);
		Double descuento = ExcelUtils.readDouble(row, 4);

		return new DetalleIncapacidad(diasIncapacidad, tipoIncapacidad,
				descuento);
	}

	private DetalleHorasExtras readHorasExtras(List<HSSFRow> rows) {
		for (HSSFRow row : rows) {
			String tipoLinea = ExcelUtils.readString(row, 1);
			if (TiposLineaExcel.LINEA_HORAS_EXTAS.equals(tipoLinea)) {
				return readDetalleHorasExtras(row);
			}
		}
		return null;
	}

	private DetalleHorasExtras readDetalleHorasExtras(HSSFRow row) {
		Integer dias = ExcelUtils.readInteger(row, 2);
		String tipoHoras = ExcelUtils.readString(row, 3);
		Integer horasExtras = ExcelUtils.readInteger(row, 4);
		Double horasPagadas = ExcelUtils.readDouble(row, 5);

		return new DetalleHorasExtras(dias, tipoHoras, horasExtras,
				horasPagadas);
	}

	private Map<String, List<HSSFRow>> readDatosNomina(HSSFSheet activeSheet) {
		int lastRow = activeSheet.getLastRowNum();
		Map<String, List<HSSFRow>> datosNomina = new HashMap<String, List<HSSFRow>>();
		for (int index = 31; index <= lastRow; index++) {
			String consecutivo = ExcelUtils.readString(activeSheet, index, 0);
			if (datosNomina.containsKey(consecutivo)) {
				datosNomina.get(consecutivo).add(activeSheet.getRow(index));
			} else {
				List<HSSFRow> datosEmpleado = new LinkedList<HSSFRow>();
				datosEmpleado.add(activeSheet.getRow(index));
				datosNomina.put(consecutivo, datosEmpleado);
			}
		}
		return datosNomina;
	}

	private Direccion createDireccion(HSSFSheet activeSheet, int startIndex) {
		String calle = ExcelUtils.readString(activeSheet, startIndex + 0, 0);
		String noExterior = ExcelUtils.readString(activeSheet, startIndex + 1,
				0);
		String noInterior = ExcelUtils.readString(activeSheet, startIndex + 2,
				0);
		String colonia = ExcelUtils.readString(activeSheet, startIndex + 3, 0);
		String localidad = ExcelUtils
				.readString(activeSheet, startIndex + 4, 0);
		String referencia = ExcelUtils.readString(activeSheet, startIndex + 5,
				0);
		String municipio = ExcelUtils
				.readString(activeSheet, startIndex + 6, 0);
		String estado = ExcelUtils.readString(activeSheet, startIndex + 7, 0);
		String pais = ExcelUtils.readString(activeSheet, startIndex + 8, 0);
		String cp = ExcelUtils.readString(activeSheet, startIndex + 9, 0);

		return new Direccion(calle, noExterior, noInterior, colonia, localidad,
				referencia, municipio, estado, pais, cp);
	}

}
