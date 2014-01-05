package com.digivalle.nomina.components.bigdata.converter;

import java.math.BigDecimal;

import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Conceptos;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Conceptos.Concepto;

import com.digivalle.nomina.models.DetalleNominaEmpleado;
import com.digivalle.nomina.models.DetallePercepcion;

public class BigDataConceptosConverter {
	public static Conceptos createConceptos(DetalleNominaEmpleado detalleNominaEmpleado) {
		Conceptos conceptos = new Conceptos();
		int noIdentifiacion = 1;
		
		for(DetallePercepcion percepcion : detalleNominaEmpleado.getPercepciones()){ 
			Concepto concepto = new Concepto();
			concepto.setCantidad(new BigDecimal(1));
			concepto.setDescripcion(percepcion.getConcepto());
			concepto.setImporte(new BigDecimal(percepcion.getImporteGravado()));
			concepto.setNoIdentificacion(String.format("%d", noIdentifiacion++));
			concepto.setUnidad("SERVICIO");
			concepto.setValorUnitario(new BigDecimal(percepcion.getImporteGravado()));
			
			conceptos.getConcepto().add(concepto);
		}
		 
		return conceptos;
	}

}
