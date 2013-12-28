package com.digivalle.nomina.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class NominaInfo {
	private final EmpleadorInfo empleador;
	private final DetalleNominaInfo detalleNomina;
	
	public NominaInfo(EmpleadorInfo empleador, DetalleNominaInfo detalleNomina){
		this.empleador = empleador;
		this.detalleNomina = detalleNomina;
	}

	public EmpleadorInfo getEmpleador() {
		return empleador;
	}

	public DetalleNominaInfo getDetalleNomina() {
		return detalleNomina;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof NominaInfo)){
			return false;
		}
		
		NominaInfo that = (NominaInfo)obj;
		
		return new EqualsBuilder()
			.append(getEmpleador(), that.getEmpleador())
			.append(getDetalleNomina(), that.getDetalleNomina())
			.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(93, 39)
			.append(getEmpleador())
			.append(getDetalleNomina())
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
