package com.digivalle.nomina.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DetalleIncapacidad {
	private final Integer diasIncapacidad;
	private final String tipoIncapacidad;
	private final Double descuento;
	
	public DetalleIncapacidad(Integer diasIncapacidad, String tipoIncapacidad, Double descuento){
		this.diasIncapacidad = diasIncapacidad;
		this.tipoIncapacidad = tipoIncapacidad;
		this.descuento = descuento;
	}

	public Integer getDiasIncapacidad() {
		return diasIncapacidad;
	}

	public String getTipoIncapacidad() {
		return tipoIncapacidad;
	}

	public Double getDescuento() {
		return descuento;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof DetalleIncapacidad)){
			return false;
		}
		
		DetalleIncapacidad that = (DetalleIncapacidad)obj;
		
		return new EqualsBuilder()
			.append(getTipoIncapacidad(), that.getTipoIncapacidad())
			.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(93, 39)
			.append(getTipoIncapacidad())
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("tipoIncapacidad", getTipoIncapacidad())
			.append("descuento", getDescuento())
			.toString();
	}
}
