package com.digivalle.nomina.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DetalleDeduccion {
	private final String tipoDeduccion;
	private final String clave;
	private final String concepto;
	private final Double importeGravado;
	private final Double importeExcento;
	
	public DetalleDeduccion(String tipoDeduccion, String clave, String concepto, Double importeGravado, Double importeExcento){
		this.tipoDeduccion = tipoDeduccion;
		this.clave = clave;
		this.concepto = concepto;
		this.importeGravado = importeGravado;
		this.importeExcento = importeExcento;
	}

	public String getTipoDeduccion() {
		return tipoDeduccion;
	}

	public String getClave() {
		return clave;
	}

	public String getConcepto() {
		return concepto;
	}

	public Double getImporteGravado() {
		return importeGravado;
	}

	public Double getImporteExcento() {
		return importeExcento;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof DetalleDeduccion)){
			return false;
		}
		
		DetalleDeduccion that = (DetalleDeduccion)obj;
		
		return new EqualsBuilder()
			.append(getClave(), that.getClave())
			.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(93, 39)
			.append(getClave())
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("clave",getClave())
		.append("concepto",getConcepto())
		.append("importeGravado",getImporteGravado())
		.append("importeExcento",getImporteExcento())
		.toString();
	}
}
