package com.digivalle.nomina.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class EmpleadorInfo {
	private final String rfc;
	private final String razonSocial;
	private final String regimen;
	private final Direccion direccionFiscal; 
	private final String registroPatronal; 
	
	public EmpleadorInfo(String rfc, String razonSocial, String regimen, Direccion direccionFiscal, String registroPatronal){
		this.rfc = rfc;
		this.razonSocial = razonSocial;
		this.regimen = regimen;
		this.direccionFiscal = direccionFiscal; 
		this.registroPatronal = registroPatronal; 
	}

	public String getRfc() {
		return rfc;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public String getRegimen() {
		return regimen;
	}

	public Direccion getDireccionFiscal() {
		return direccionFiscal;
	}
 
	public String getRegistroPatronal() {
		return registroPatronal;
	}
 
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof EmpleadorInfo)){
			return false;
		}
		EmpleadorInfo that = (EmpleadorInfo)obj;
		
		return new EqualsBuilder()
			.append(getRfc(), that.getRfc())
			.append(getRazonSocial(), that.getRazonSocial())
			.append(getRegimen(), that.getRegimen())
			.append(getDireccionFiscal(), that.getDireccionFiscal())
			.append(getRegistroPatronal(), that.getRegistroPatronal())
			.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(93, 39)
			.append(getRfc())
			.append(getRazonSocial())
			.append(getRegimen())
			.append(getDireccionFiscal())
			.append(getRegistroPatronal())
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("rfc", rfc)
			.append("razonSocial", razonSocial)
			.append("regimen", regimen)
			.append("direccionFiscal", direccionFiscal)
			.append("registroPatronal", registroPatronal)
			.toString();
	}
}
