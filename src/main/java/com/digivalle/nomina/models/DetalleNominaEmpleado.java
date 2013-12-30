package com.digivalle.nomina.models;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DetalleNominaEmpleado { 
	private final Empleado empleado; 
	private final Integer diasPagados;
	private final List<DetallePercepcion> percepciones;
	private final List<DetalleDeduccion> deducciones;
	private final DetalleIncapacidad incapacidad;
	private final DetalleHorasExtras horasExtras;
	
	public DetalleNominaEmpleado(Empleado empleado, Integer diasPagados, List<DetallePercepcion> percepciones, List<DetalleDeduccion> deducciones,
			DetalleIncapacidad incapacidad, DetalleHorasExtras horasExtras){
		this.empleado = empleado;
		this.diasPagados = diasPagados;
		this.percepciones = percepciones;
		this.deducciones = deducciones;
		this.incapacidad = incapacidad;
		this.horasExtras = horasExtras;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public Integer getDiasPagados() {
		return diasPagados;
	}

	public List<DetallePercepcion> getPercepciones() {
		return percepciones;
	}

	public List<DetalleDeduccion> getDeducciones() {
		return deducciones;
	}

	public DetalleIncapacidad getIncapacidad() {
		return incapacidad;
	}

	public DetalleHorasExtras getHorasExtras() {
		return horasExtras;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof DetalleNominaEmpleado)){
			return false;
		}
		DetalleNominaEmpleado that = (DetalleNominaEmpleado)obj;
		
		return new EqualsBuilder()
			.append(getEmpleado(), that.getEmpleado())
			.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(93, 39)
			.append(getEmpleado())
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("empleado",getEmpleado())
			.append("diasPagados", getDiasPagados())
			.toString();
	}
}
