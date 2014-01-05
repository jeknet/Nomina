package com.digivalle.nomina.models;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Empleado {
	private final Integer id;
	private final String idInterno;
	private final String nombreCompleto;
	private final String rfc;
	private final String curp;
	private final Integer tipoRegimen;
	private final String nss;
	private final String departamento;
	private final String clabe;
	private final String banco;
	private final Date fechaInicioLaboral;
	private final Integer antiguedad;
	private final String puesto;
	private final String tipoContrato;
	private final String tipoJornada;
	private final String periodicidadPago;
	private final Double salarioBase;
	private final Integer riesgoPuesto;
	private final Double salarioBaseIntegrado;
	private final Double totalPercepcionesGravado;
	private final Double totalPercepcionesExento;
	private final Double totalDeduccionesGravado;
	private final Double totalDeduccionesExento;

	public Empleado(Integer id, String idInterno, String nombreCompleto,
			String rfc, String curp, Integer tipoRegimen, String nss,
			String departamento, String clabe, String banco,
			Date fechaInicioLaboral, Integer antiguedad, String puesto,
			String tipoContrato, String tipoJornada, String periodicidadPago,
			Double salarioBase, Integer riesgoPuesto,
			Double salarioBaseIntegrado, Double totalPercepcionesGravado,
			Double totalPercepcionesExento, Double totalDeduccionesGravado,
			Double totalDeduccionesExento) {
		this.id = id;
		this.idInterno = idInterno;
		this.nombreCompleto = nombreCompleto;
		this.rfc = rfc;
		this.curp = curp;
		this.tipoRegimen = tipoRegimen;
		this.nss = nss;
		this.departamento = departamento;
		this.clabe = clabe;
		this.banco = banco;
		this.fechaInicioLaboral = fechaInicioLaboral;
		this.antiguedad = antiguedad;
		this.puesto = puesto;
		this.tipoContrato = tipoContrato;
		this.tipoJornada = tipoJornada;
		this.periodicidadPago = periodicidadPago;
		this.salarioBase = salarioBase;
		this.riesgoPuesto = riesgoPuesto;
		this.salarioBaseIntegrado = salarioBaseIntegrado;
		this.totalPercepcionesGravado = totalPercepcionesGravado;
		this.totalPercepcionesExento = totalPercepcionesExento;
		this.totalDeduccionesGravado = totalDeduccionesGravado;
		this.totalDeduccionesExento = totalDeduccionesExento;
	}

	public Integer getId() {
		return id;
	}

	public String getIdInterno() {
		return idInterno;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public String getRfc() {
		return rfc;
	}

	public String getCurp() {
		return curp;
	}

	public Integer getTipoRegimen() {
		return tipoRegimen;
	}

	public String getNss() {
		return nss;
	}

	public String getDepartamento() {
		return departamento;
	}

	public String getClabe() {
		return clabe;
	}

	public String getBanco() {
		return banco;
	}

	public Date getFechaInicioLaboral() {
		return fechaInicioLaboral;
	}

	public Integer getAntiguedad() {
		return antiguedad;
	}

	public String getPuesto() {
		return puesto;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public String getTipoJornada() {
		return tipoJornada;
	}

	public String getPeriodicidadPago() {
		return periodicidadPago;
	}

	public Double getSalarioBase() {
		return salarioBase;
	}

	public Integer getRiesgoPuesto() {
		return riesgoPuesto;
	}

	public Double getSalarioBaseIntegrado() {
		return salarioBaseIntegrado;
	}

	public Double getTotalPercepcionesGravado() {
		return totalPercepcionesGravado;
	}

	public Double getTotalPercepcionesExento() {
		return totalPercepcionesExento;
	}

	public Double getTotalDeduccionesGravado() {
		return totalDeduccionesGravado;
	}

	public Double getTotalDeduccionesExento() {
		return totalDeduccionesExento;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Empleado)) {
			return false;
		}

		Empleado that = (Empleado) obj;

		return new EqualsBuilder().append(getIdInterno(), that.getIdInterno())
				.append(getRfc(), that.getRfc()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(93, 39).append(getIdInterno())
				.append(getRfc()).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("idInterno", getIdInterno())
				.append("nombre", getNombreCompleto()).append("rfc", getRfc())
				.append("curp", getCurp()).toString();
	}
}
