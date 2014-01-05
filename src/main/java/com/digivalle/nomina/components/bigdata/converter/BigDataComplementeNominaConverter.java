package com.digivalle.nomina.components.bigdata.converter;

import java.math.BigDecimal;
import java.math.BigInteger;

import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Complemento;
import mx.bigdata.sat.common.nomina.schema.Nomina;

import com.digivalle.nomina.models.DetalleNominaEmpleado;
import com.digivalle.nomina.models.NominaInfo;
import com.digivalle.nomina.utils.TimeUtils;

public class BigDataComplementeNominaConverter {

	public static Complemento createComplemento(
			DetalleNominaEmpleado detalleNominaEmpleado, NominaInfo nominaInfo) {
		Complemento complemento = new Complemento();
		addNominaAttributes(complemento, detalleNominaEmpleado, nominaInfo);
		
		return complemento;
	}

	private static void addNominaAttributes(Complemento complemento,
			DetalleNominaEmpleado detalleNominaEmpleado, NominaInfo nominaInfo) {
		Nomina nomina = new Nomina();
		nomina.setVersion("1.1");
		nomina.setRegistroPatronal(nominaInfo.getEmpleador()
				.getRegistroPatronal());
		nomina.setNumEmpleado(detalleNominaEmpleado.getEmpleado()
				.getIdInterno());
		nomina.setCURP(detalleNominaEmpleado.getEmpleado().getCurp());
		nomina.setTipoRegimen(detalleNominaEmpleado.getEmpleado()
				.getTipoRegimen());
		nomina.setNumSeguridadSocial(detalleNominaEmpleado.getEmpleado()
				.getNss());
		nomina.setNumDiasPagados(new BigDecimal(detalleNominaEmpleado
				.getDiasPagados()));
		nomina.setDepartamento(detalleNominaEmpleado.getEmpleado()
				.getDepartamento());
		// TODO:Recompilar proyecto para que acepte String ya que esta mal
		// definido en el SAT 
		nomina.setCLABE(new BigInteger(detalleNominaEmpleado.getEmpleado()
				.getClabe()));
		// TODO:Recompilar proyecto para que acepte String ya que esta mal
		// definido en el SAT
		nomina.setBanco(Integer.parseInt(detalleNominaEmpleado.getEmpleado()
				.getBanco()));
		nomina.setAntiguedad(detalleNominaEmpleado.getEmpleado()
				.getAntiguedad());
		nomina.setPuesto(detalleNominaEmpleado.getEmpleado().getPuesto());
		nomina.setTipoContrato(detalleNominaEmpleado.getEmpleado()
				.getTipoContrato());
		nomina.setTipoJornada(detalleNominaEmpleado.getEmpleado()
				.getTipoJornada());
		nomina.setPeriodicidadPago(detalleNominaEmpleado.getEmpleado()
				.getPeriodicidadPago());
		nomina.setSalarioBaseCotApor(new BigDecimal(detalleNominaEmpleado
				.getEmpleado().getSalarioBase()));
		nomina.setRiesgoPuesto(detalleNominaEmpleado.getEmpleado()
				.getRiesgoPuesto());
		nomina.setSalarioDiarioIntegrado(new BigDecimal(detalleNominaEmpleado
				.getEmpleado().getSalarioBaseIntegrado()));

		nomina.setFechaPago(TimeUtils.createXmlGregorianCalendar(nominaInfo
				.getDetalleNomina().getFechaPago()));
		nomina.setFechaInicialPago(TimeUtils
				.createXmlGregorianCalendar(nominaInfo.getDetalleNomina()
						.getFechaInicioPago()));
		nomina.setFechaFinalPago(TimeUtils
				.createXmlGregorianCalendar(nominaInfo.getDetalleNomina()
						.getFechaFinPago()));
		nomina.setFechaInicioRelLaboral(TimeUtils
				.createXmlGregorianCalendar(detalleNominaEmpleado.getEmpleado()
						.getFechaInicioLaboral()));
		
		BigDataComplementoNominaPercepcionesConverter.addPercepciones(nomina, detalleNominaEmpleado);
		BigDataComplementoNominaDeduccionesConverter.addDeducciones(nomina, detalleNominaEmpleado);
		BigDataComplementoNominaIncapacidadesConverter.addIncapacidades(nomina, detalleNominaEmpleado);
		BigDataComplementoNominaHorasExtrasConverter.addHorasExtras(nomina, detalleNominaEmpleado);


		
		complemento.getAny().add(nomina);
	}
}
