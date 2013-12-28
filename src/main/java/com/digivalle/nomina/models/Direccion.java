package com.digivalle.nomina.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Direccion {
	private final String calle;
	private final String noExterior;
	private final String noInterior;
	private final String colonia;
	private final String localidad;
	private final String referencia;
	private final String municipio;
	private final String estado;
	private final String pais;
	private final String cp;
	
	public Direccion(String calle, String noExterior, String noInterior, String colonia, String localidad, String referencia, String municipio,
			String estado, String pais, String cp){
		this.calle = calle;
		this.noExterior = noExterior;
		this.noInterior = noInterior;
		this.colonia = colonia;
		this.localidad = localidad;
		this.referencia = referencia;
		this.municipio = municipio;
		this.estado = estado;
		this.pais = pais;
		this.cp = cp;
	}

	public String getCalle() {
		return calle;
	}

	public String getNoExterior() {
		return noExterior;
	}

	public String getNoInterior() {
		return noInterior;
	}

	public String getColonia() {
		return colonia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public String getReferencia() {
		return referencia;
	}

	public String getMunicipio() {
		return municipio;
	}

	public String getEstado() {
		return estado;
	}

	public String getPais() {
		return pais;
	}

	public String getCp() {
		return cp;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Direccion)){
			return false;
		}
		Direccion that = (Direccion)obj;
		
		return new EqualsBuilder()
			.append(getCalle(), that.getCalle())
			.append(getNoExterior(), that.getNoExterior())
			.append(getNoInterior(), that.getNoInterior())
			.append(getColonia(), that.getColonia())
			.append(getMunicipio(), that.getMunicipio())
			.append(getEstado(), that.getEstado())
			.append(getPais(), that.getPais())
			.append(getCp(), that.getCp()).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(93, 39)
			.append(getCalle())
			.append(getNoExterior())
			.append(getNoInterior())
			.append(getColonia())
			.append(getMunicipio())
			.append(getEstado())
			.append(getPais())
			.append(getCp())
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("calle", calle)
			.append("noExterior", noExterior)
			.append("noInterior", noInterior)
			.append("colonia", colonia)
			.append("municipio", municipio)
			.append("estado", estado)
			.append("pais", pais)
			.append("cp", cp)
			.build();
	}
}
