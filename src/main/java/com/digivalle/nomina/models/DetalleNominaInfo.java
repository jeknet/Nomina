package com.digivalle.nomina.models;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DetalleNominaInfo { 
	private final Direccion direccionEmision; 
	private final Date fechaPago;
	private final Date fechaInicioPago;
	private final Date fechaFinPago;
	private final String serie;
	private final Integer folioInicioNomina;
	private final String lugarExpedicion;
	
	public DetalleNominaInfo(Direccion direccionEmision, Date fechaPago, Date fechaInicioPago, Date fechaFinPago, String serie, 
			Integer folioInicioNomina, String lugarExpedicion){ 
		this.direccionEmision = direccionEmision; 
		this.fechaPago = fechaPago;
		this.fechaInicioPago = fechaInicioPago;
		this.fechaFinPago = fechaFinPago;
		this.serie = serie;
		this.folioInicioNomina = folioInicioNomina;
		this.lugarExpedicion = lugarExpedicion;
	}

	public Direccion getDireccionEmision() {
		return direccionEmision;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public Date getFechaInicioPago() {
		return fechaInicioPago;
	}

	public Date getFechaFinPago() {
		return fechaFinPago;
	}

	public String getSerie() {
		return serie;
	}

	public Integer getFolioInicioNomina() {
		return folioInicioNomina;
	}

	public String getLugarExpedicion() {
		return lugarExpedicion;
	}


	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof DetalleNominaInfo)){
			return false;
		}
		
		DetalleNominaInfo that = (DetalleNominaInfo)obj;
		
		return new EqualsBuilder()
			.append(getDireccionEmision(), that.getDireccionEmision())
			.append(getFechaPago(), that.getFechaPago())
			.append(getSerie(), that.getSerie())
			.append(getFolioInicioNomina(), that.getFolioInicioNomina())
			.append(getLugarExpedicion(), that.getLugarExpedicion())
			.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(93, 39)
			.append(getDireccionEmision())
			.append(getFechaPago())
			.append(getSerie())
			.append(getFolioInicioNomina())
			.append(getLugarExpedicion())
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
