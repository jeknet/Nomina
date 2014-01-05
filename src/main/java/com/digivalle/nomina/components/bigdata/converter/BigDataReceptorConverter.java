package com.digivalle.nomina.components.bigdata.converter;

import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Receptor;

import com.digivalle.nomina.models.Empleado;

public class BigDataReceptorConverter {
	public static Receptor createReceptor(Empleado empleado) {
		Receptor receptor = new Receptor();
		receptor.setNombre(empleado.getNombreCompleto());
		receptor.setRfc(empleado.getRfc());
		
		return receptor;
	}
 

}
