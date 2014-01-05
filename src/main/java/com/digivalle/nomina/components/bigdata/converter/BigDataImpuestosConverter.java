package com.digivalle.nomina.components.bigdata.converter;

import java.math.BigDecimal;

import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Impuestos;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Impuestos.Retenciones;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Impuestos.Retenciones.Retencion;
import mx.bigdata.sat.cfdi.v32.schema.ObjectFactory;

import com.digivalle.nomina.models.DetalleDeduccion;
import com.digivalle.nomina.models.DetalleNominaEmpleado;

public class BigDataImpuestosConverter {

	public static Impuestos createImpuestos(
			DetalleNominaEmpleado detalleNominaEmpleado) {

		Impuestos impuestos = new ObjectFactory().createComprobanteImpuestos();
		Retenciones retenciones = new Retenciones();

		for (DetalleDeduccion deduccion : detalleNominaEmpleado
				.getDeducciones()) {
			if (deduccion.getConcepto().contains("ISR")) {
				Retencion retencion = new Retencion();
				retencion.setImporte(new BigDecimal(deduccion.getImporteGravado()));
				retencion.setImpuesto(deduccion.getConcepto());
				retenciones.getRetencion().add(retencion);
			}
		}
		impuestos.setRetenciones(retenciones);

		return impuestos;
	}

}
