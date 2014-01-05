package com.digivalle.nomina.components.bigdata.converter;

import java.math.BigDecimal;
import java.util.List;

import mx.bigdata.sat.common.nomina.schema.Nomina;
import mx.bigdata.sat.common.nomina.schema.Nomina.Deducciones;
import mx.bigdata.sat.common.nomina.schema.Nomina.Deducciones.Deduccion;

import com.digivalle.nomina.models.DetalleDeduccion;
import com.digivalle.nomina.models.DetalleNominaEmpleado;

public class BigDataComplementoNominaDeduccionesConverter {

	public static void addDeducciones(Nomina nomina,
			DetalleNominaEmpleado detalleNominaEmpleado) {

		Deducciones deducciones = new Deducciones();
		for (DetalleDeduccion detalleDeduccion : detalleNominaEmpleado
				.getDeducciones()) {
			Deduccion deduccion = new Deduccion();
			// TODO: Eliminar el parse cuando se altere el tipo de dato del
			// esquema
			deduccion.setTipoDeduccion(Integer.parseInt(detalleDeduccion
					.getTipoDeduccion()));
			deduccion.setClave(detalleDeduccion.getClave());
			deduccion.setConcepto(detalleDeduccion.getConcepto());
			deduccion.setImporteExento(new BigDecimal(detalleDeduccion
					.getImporteExcento()));
			deduccion.setImporteGravado(new BigDecimal(detalleDeduccion
					.getImporteGravado()));
			deducciones.getDeduccion().add(deduccion);
		}
		deducciones
				.setTotalGravado(getTotalDeduccionesGravado(detalleNominaEmpleado
						.getDeducciones()));
		deducciones
				.setTotalExento(getTotalDeduccionesExento(detalleNominaEmpleado
						.getDeducciones()));
		nomina.setDeducciones(deducciones);
	}

	public static BigDecimal getTotalDeduccionesGravado(
			List<DetalleDeduccion> detalleDeducciones) {

		Double total = new Double(0);
		for (DetalleDeduccion detalleDeduccion : detalleDeducciones) {
			total += detalleDeduccion.getImporteGravado();
		}

		return new BigDecimal(total);

	}

	public static BigDecimal getTotalDeduccionesGravadoSinISR(
			List<DetalleDeduccion> detalleDeducciones) {

		Double total = new Double(0);
		for (DetalleDeduccion detalleDeduccion : detalleDeducciones) {
			// TODO:Cambiar por la clave del ISR cuando se arregle el esquema
			if (!detalleDeduccion.getConcepto().equals("ISR")) {
				total += detalleDeduccion.getImporteGravado();
			}
		}

		return new BigDecimal(total);

	}

	public static BigDecimal getTotalDeduccionesExento(
			List<DetalleDeduccion> detalleDeducciones) {

		Double total = new Double(0);
		for (DetalleDeduccion detalleDeduccion : detalleDeducciones) {
			total += detalleDeduccion.getImporteExcento();
		}
		return new BigDecimal(total);
	}

	public static BigDecimal getTotalDeduccionesExentoSinISR(
			List<DetalleDeduccion> detalleDeducciones) {

		Double total = new Double(0);
		for (DetalleDeduccion detalleDeduccion : detalleDeducciones) {
			// TODO:Cambiar por la clave del ISR cuando se arregle el esquema
			if (!detalleDeduccion.getConcepto().equals("ISR")) {
				total += detalleDeduccion.getImporteExcento();
			}
		}
		return new BigDecimal(total);
	}
}
