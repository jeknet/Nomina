package com.digivalle.nomina.components.bigdata.converter;

import java.math.BigDecimal;

import mx.bigdata.sat.common.nomina.schema.Nomina;
import mx.bigdata.sat.common.nomina.schema.Nomina.HorasExtras;
import mx.bigdata.sat.common.nomina.schema.Nomina.HorasExtras.HorasExtra;

import com.digivalle.nomina.models.DetalleHorasExtras;
import com.digivalle.nomina.models.DetalleNominaEmpleado;

public class BigDataComplementoNominaHorasExtrasConverter {

	public static void addHorasExtras(Nomina nomina,
			DetalleNominaEmpleado detalleNominaEmpleado) {
		HorasExtras horasExtras = new HorasExtras();
		
		for (DetalleHorasExtras detalleHorasExtra : detalleNominaEmpleado.getHorasExtras()) {
			HorasExtra horasExtra = new HorasExtra();
			horasExtra.setDias(detalleHorasExtra.getDias());
			horasExtra.setHorasExtra(detalleHorasExtra.getHorasExtras());
			horasExtra.setTipoHoras(detalleHorasExtra.getTipoHoras());
			horasExtra.setImportePagado(new BigDecimal(detalleHorasExtra
					.getHorasPagadas()));

			horasExtras.getHorasExtra().add(horasExtra);
		}
		nomina.setHorasExtras(horasExtras);
	}

}
