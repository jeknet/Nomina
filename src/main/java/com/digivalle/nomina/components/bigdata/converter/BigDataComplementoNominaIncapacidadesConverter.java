package com.digivalle.nomina.components.bigdata.converter;

import java.math.BigDecimal;
import java.util.Arrays;

import mx.bigdata.sat.common.nomina.schema.Nomina;
import mx.bigdata.sat.common.nomina.schema.Nomina.Incapacidades;
import mx.bigdata.sat.common.nomina.schema.Nomina.Incapacidades.Incapacidad;

import com.digivalle.nomina.models.DetalleIncapacidad;
import com.digivalle.nomina.models.DetalleNominaEmpleado;

public class BigDataComplementoNominaIncapacidadesConverter {

	public static void addIncapacidades(Nomina nomina,
			DetalleNominaEmpleado detalleNominaEmpleado) {

		Incapacidades incapacidades = new Incapacidades();
		 
		for (DetalleIncapacidad detalleIncapacidad : detalleNominaEmpleado.getIncapacidad()) {
			Incapacidad incapacidad = new Incapacidad();
			incapacidad.setTipoIncapacidad(detalleIncapacidad
					.getTipoIncapacidad());
			incapacidad.setDiasIncapacidad(new BigDecimal(detalleIncapacidad
					.getDiasIncapacidad()));
			incapacidad.setDescuento(new BigDecimal(detalleIncapacidad
					.getDescuento()));
			incapacidades.getIncapacidad().add(incapacidad);
		}
		nomina.setIncapacidades(incapacidades);

	}

}
