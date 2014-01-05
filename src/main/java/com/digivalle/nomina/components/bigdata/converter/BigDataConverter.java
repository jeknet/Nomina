package com.digivalle.nomina.components.bigdata.converter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import mx.bigdata.sat.cfdi.v32.schema.Comprobante;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Complemento;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Conceptos;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Emisor;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Impuestos;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Receptor;

import com.digivalle.nomina.models.DetalleNominaEmpleado;
import com.digivalle.nomina.models.NominaInfo;

public class BigDataConverter {

	// Calcular subtotal (suma de conceptos)
	// Caluclar descuento (suma de deducciones excepto ISR)
	// Calcular deducciones (suma de deducciones)
	// Calcluar total (subtotal - deducciones)
	public List<Comprobante> createComprobante(NominaInfo nominaInfo) {
		List<Comprobante> comprobantes = new LinkedList<Comprobante>();
		Emisor emisor = BigDataEmisorConverter.createEmisor(
				nominaInfo.getEmpleador(), nominaInfo.getDetalleNomina());

		for (DetalleNominaEmpleado detalleNominaEmpleado : nominaInfo
				.getDetalleNominaEmpleados()) {
			Comprobante comprobante = new Comprobante();
			Receptor receptor = BigDataReceptorConverter
					.createReceptor(detalleNominaEmpleado.getEmpleado());
			Conceptos conceptos = BigDataConceptosConverter
					.createConceptos(detalleNominaEmpleado);
			Impuestos impuestos = BigDataImpuestosConverter
					.createImpuestos(detalleNominaEmpleado);
			Complemento complemento = BigDataComplementeNominaConverter
					.createComplemento(detalleNominaEmpleado, nominaInfo);

			comprobante.setVersion("3.2");
			comprobante.setTipoDeComprobante("egreso");
			comprobante.setEmisor(emisor);
			comprobante.setReceptor(receptor);
			comprobante.setConceptos(conceptos);
			comprobante.setImpuestos(impuestos);
			comprobante.setComplemento(complemento);
			comprobante.setSubTotal(getSubtotal(detalleNominaEmpleado));
			comprobante.setDescuento(getDescuento(detalleNominaEmpleado));
			comprobante.setTotal(getTotal(detalleNominaEmpleado));
			comprobante.setMotivoDescuento("Descuentos Nomina");
			comprobante.setTipoCambio("1");
			comprobante.setMoneda("MXN");
			comprobante.setMetodoDePago("No identificado");
			comprobante.setCondicionesDePago("Contado");
			comprobante.setLugarExpedicion(emisor.getExpedidoEn().getEstado());
			comprobante.setFecha(new Date());

			comprobantes.add(comprobante);
		}

		return comprobantes;
	}

	private BigDecimal getSubtotal(DetalleNominaEmpleado detalleNominaEmpleado) {

		return BigDataComplementoNominaPercepcionesConverter
				.addTotalPercepcionesExento(
						detalleNominaEmpleado.getPercepciones())
				.add(BigDataComplementoNominaPercepcionesConverter
						.addTotalPercepcionesGravado(detalleNominaEmpleado
								.getPercepciones()));

	}

	private BigDecimal getDescuento(DetalleNominaEmpleado detalleNominaEmpleado) {
		return BigDataComplementoNominaDeduccionesConverter
				.getTotalDeduccionesGravadoSinISR(
						detalleNominaEmpleado.getDeducciones())
				.add(BigDataComplementoNominaDeduccionesConverter
						.getTotalDeduccionesExentoSinISR(detalleNominaEmpleado
								.getDeducciones()));
	}

	private BigDecimal getTotal(DetalleNominaEmpleado detalleNominaEmpleado) {
		return getSubtotal(detalleNominaEmpleado)
				.subtract(
						BigDataComplementoNominaDeduccionesConverter
								.getTotalDeduccionesGravado(
										detalleNominaEmpleado.getDeducciones())
								.add(BigDataComplementoNominaDeduccionesConverter
										.getTotalDeduccionesExento(detalleNominaEmpleado
												.getDeducciones())));
	}

}
