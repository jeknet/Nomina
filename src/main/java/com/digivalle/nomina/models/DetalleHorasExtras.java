package com.digivalle.nomina.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DetalleHorasExtras {
	private final Integer dias;
	private final String tipoHoras;
	private final Integer horasExtras;
	private final Double horasPagadas;
	
	public DetalleHorasExtras(Integer dias, String tipoHoras, Integer horasExtras, Double horasPagadas){
		this.dias = dias;
		this.tipoHoras = tipoHoras;
		this.horasExtras = horasExtras;
		this.horasPagadas = horasPagadas;
	}

	public Integer getDias() {
		return dias;
	}

	public String getTipoHoras() {
		return tipoHoras;
	}

	public Integer getHorasExtras() {
		return horasExtras;
	}

	public Double getHorasPagadas() {
		return horasPagadas;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof DetalleHorasExtras)){
			return false;
		}
		
		DetalleHorasExtras that = (DetalleHorasExtras)obj;
		
		return new EqualsBuilder()
			.append(getTipoHoras(), that.getTipoHoras())
			.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(93, 39)
			.append(getTipoHoras())
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("dias", getDias())
			.append("tipoHoras", getTipoHoras())
			.append("horasExtras", getHorasExtras())
			.append("horasPagadas", getHorasPagadas())
			.toString();
	}
}
