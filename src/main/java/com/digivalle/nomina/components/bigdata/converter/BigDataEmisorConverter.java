package com.digivalle.nomina.components.bigdata.converter;

import mx.bigdata.sat.cfdi.v32.schema.TUbicacion;
import mx.bigdata.sat.cfdi.v32.schema.TUbicacionFiscal;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Emisor;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Emisor.RegimenFiscal;

import com.digivalle.nomina.models.DetalleNominaInfo;
import com.digivalle.nomina.models.Direccion;
import com.digivalle.nomina.models.EmpleadorInfo;

public class BigDataEmisorConverter {
	public static Emisor createEmisor(EmpleadorInfo empleador, DetalleNominaInfo detalleNominaInfo) {
		Emisor emisor = new Emisor();
		TUbicacionFiscal domicilioFiscal = createDomicilioFiscal(empleador.getDireccionFiscal());
		TUbicacion expedidoEn = createExpedidoEn(detalleNominaInfo.getDireccionEmision());
		emisor.setDomicilioFiscal(domicilioFiscal);
		emisor.setExpedidoEn(expedidoEn);
		emisor.setNombre(empleador.getRazonSocial());
		emisor.setRfc(empleador.getRfc());
		RegimenFiscal regimenFiscal = new RegimenFiscal();
		regimenFiscal.setRegimen(empleador.getRegimen());
		emisor.getRegimenFiscal().add(regimenFiscal);
		return emisor;
	}
	
	private static TUbicacion createExpedidoEn(Direccion direccionFiscal) {
		TUbicacion expedidoEn = new TUbicacion();
		expedidoEn.setCalle(direccionFiscal.getCalle());
		expedidoEn.setNoExterior(direccionFiscal.getNoExterior());
		expedidoEn.setNoInterior(direccionFiscal.getNoInterior());
		expedidoEn.setColonia(direccionFiscal.getColonia());
		expedidoEn.setLocalidad(direccionFiscal.getLocalidad());
		expedidoEn.setReferencia(direccionFiscal.getReferencia());
		expedidoEn.setMunicipio(direccionFiscal.getMunicipio());
		expedidoEn.setEstado(direccionFiscal.getEstado());
		expedidoEn.setPais(direccionFiscal.getPais());
		expedidoEn.setCodigoPostal(direccionFiscal.getCp());
		
		return expedidoEn;
	}

	private static TUbicacionFiscal createDomicilioFiscal(Direccion direccionFiscal) {
		TUbicacionFiscal domicilioFiscal = new TUbicacionFiscal();
		domicilioFiscal.setCalle(direccionFiscal.getCalle());
		domicilioFiscal.setNoExterior(direccionFiscal.getNoExterior());
		domicilioFiscal.setNoInterior(direccionFiscal.getNoInterior());
		domicilioFiscal.setColonia(direccionFiscal.getColonia());
		domicilioFiscal.setLocalidad(direccionFiscal.getLocalidad());
		domicilioFiscal.setReferencia(direccionFiscal.getReferencia());
		domicilioFiscal.setMunicipio(direccionFiscal.getMunicipio());
		domicilioFiscal.setEstado(direccionFiscal.getEstado());
		domicilioFiscal.setPais(direccionFiscal.getPais());
		domicilioFiscal.setCodigoPostal(direccionFiscal.getCp());
		
		return domicilioFiscal;
	}
}
