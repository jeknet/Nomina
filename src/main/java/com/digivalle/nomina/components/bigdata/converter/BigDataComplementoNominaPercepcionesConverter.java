package com.digivalle.nomina.components.bigdata.converter;

import java.math.BigDecimal;
import java.util.List;

import mx.bigdata.sat.common.nomina.schema.Nomina;
import mx.bigdata.sat.common.nomina.schema.Nomina.Percepciones;
import mx.bigdata.sat.common.nomina.schema.Nomina.Percepciones.Percepcion;

import com.digivalle.nomina.models.DetalleNominaEmpleado;
import com.digivalle.nomina.models.DetallePercepcion;

public class BigDataComplementoNominaPercepcionesConverter {

	public static void addPercepciones(Nomina nomina,
			DetalleNominaEmpleado detalleNominaEmpleado) {

		Percepciones percepciones = new Percepciones();
		for (DetallePercepcion detallePercepcion : detalleNominaEmpleado
				.getPercepciones()) {
			Percepcion percepcion = new Percepcion();
			percepcion.setClave(detallePercepcion.getClave());
			percepcion.setConcepto(detallePercepcion.getConcepto());
			percepcion.setImporteExento(new BigDecimal(detallePercepcion
					.getImporteExcento()));
			percepcion.setImporteGravado(new BigDecimal(detallePercepcion
					.getImporteGravado()));
			// TODO: Quitar el parse cuando se altere el tipo de dato del
			// esquema
			percepcion.setTipoPercepcion(Integer.parseInt(detallePercepcion
					.getTipoPercepcion()));
			percepciones.getPercepcion().add(percepcion);
		}
		percepciones
				.setTotalGravado(addTotalPercepcionesGravado(detalleNominaEmpleado
						.getPercepciones()));
		percepciones
				.setTotalExento(addTotalPercepcionesExento(detalleNominaEmpleado
						.getPercepciones()));
		nomina.setPercepciones(percepciones);
	}

	public static BigDecimal addTotalPercepcionesGravado(
			List<DetallePercepcion> detallePercepciones) {

		Double total = new Double(0);
		for (DetallePercepcion detallePercepcion : detallePercepciones) {
			total += detallePercepcion.getImporteGravado();
		}
		return new BigDecimal(total);
	}

	public static BigDecimal addTotalPercepcionesExento(
			List<DetallePercepcion> detallePercepciones) {

		Double total = new Double(0);
		for (DetallePercepcion detallePercepcion : detallePercepciones) {
			total += detallePercepcion.getImporteExcento();
		}
		return new BigDecimal(total);
	}
}
