package com.digivalle.nomina.components.bigdata.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import mx.bigdata.sat.cfdi.v32.schema.Comprobante;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Complemento;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Conceptos;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Conceptos.Concepto;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Emisor;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Impuestos.Retenciones.Retencion;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Receptor;
import mx.bigdata.sat.common.nomina.schema.Nomina;
import mx.bigdata.sat.common.nomina.schema.Nomina.Deducciones;
import mx.bigdata.sat.common.nomina.schema.Nomina.Deducciones.Deduccion;
import mx.bigdata.sat.common.nomina.schema.Nomina.HorasExtras;
import mx.bigdata.sat.common.nomina.schema.Nomina.HorasExtras.HorasExtra;
import mx.bigdata.sat.common.nomina.schema.Nomina.Incapacidades;
import mx.bigdata.sat.common.nomina.schema.Nomina.Incapacidades.Incapacidad;
import mx.bigdata.sat.common.nomina.schema.Nomina.Percepciones;
import mx.bigdata.sat.common.nomina.schema.Nomina.Percepciones.Percepcion;

import org.junit.Before;
import org.junit.Test;

import com.digivalle.nomina.components.extractors.implementations.RawDataExtractorExcelImpl;
import com.digivalle.nomina.models.NominaInfo;

public class BigDataConverterTest {

	BigDataConverter bigDataConverter;
	NominaInfo nominaInfo;

	@Before
	public void initialize() throws IOException {
		RawDataExtractorExcelImpl dataExtractor = new RawDataExtractorExcelImpl();
		InputStream dataStream = getClass().getClassLoader()
				.getResourceAsStream("ejemplo.xls");
		nominaInfo = dataExtractor.readNomina(dataStream);
		bigDataConverter = new BigDataConverter();

	}

	@Test
	public void createComprobanteTest() {
		List<Comprobante> comprobantes = bigDataConverter
				.createComprobante(nominaInfo);
		assertNotNull(comprobantes);
		assertFalse(comprobantes.isEmpty());
		assertEquals(1, comprobantes.size());
	}

	// emisor
	@Test
	public void createEmisorTest() {
		List<Comprobante> comprobantes = bigDataConverter
				.createComprobante(nominaInfo);
		Comprobante comprobante = comprobantes.get(0);

		Emisor emisor = comprobante.getEmisor();

		assertEquals("av juarez", emisor.getDomicilioFiscal().getCalle());
		assertEquals("800", emisor.getDomicilioFiscal().getNoExterior());
		assertEquals("C", emisor.getDomicilioFiscal().getNoInterior());
		assertEquals("Centro", emisor.getDomicilioFiscal().getColonia());
		assertEquals("Torreon", emisor.getDomicilioFiscal().getMunicipio());
		assertEquals("Torreon", emisor.getDomicilioFiscal().getLocalidad());
		assertEquals("", emisor.getDomicilioFiscal().getReferencia());
		assertEquals("Mexico", emisor.getDomicilioFiscal().getPais());
		assertEquals("32000", emisor.getDomicilioFiscal().getCodigoPostal());

		assertEquals("av juarez", emisor.getExpedidoEn().getCalle());
		assertEquals("800", emisor.getExpedidoEn().getNoExterior());
		assertEquals("C", emisor.getExpedidoEn().getNoInterior());
		assertEquals("Centro", emisor.getExpedidoEn().getColonia());
		assertEquals("Torreon", emisor.getExpedidoEn().getMunicipio());
		assertEquals("Torreon", emisor.getExpedidoEn().getLocalidad());
		assertEquals("", emisor.getExpedidoEn().getReferencia());
		assertEquals("Mexico", emisor.getExpedidoEn().getPais());
		assertEquals("32000", emisor.getExpedidoEn().getCodigoPostal());

		assertEquals("Empresas de Prueba SA de CV", emisor.getNombre());
		assertEquals("Regimen General", emisor.getRegimenFiscal().get(0)
				.getRegimen());
		assertEquals("SCD010101ABC", emisor.getRfc());
	}

	// receptor
	@Test
	public void createReceptorTest() {
		List<Comprobante> comprobantes = bigDataConverter
				.createComprobante(nominaInfo);
		Comprobante comprobante = comprobantes.get(0);
		Receptor receptor = comprobante.getReceptor();

		assertEquals("Juan Perez Mendez", receptor.getNombre());
		assertEquals("JJJJ010101U89", receptor.getRfc());
	}

	// conceptos
	@Test
	public void createConceptosTest() {
		List<Comprobante> comprobantes = bigDataConverter
				.createComprobante(nominaInfo);
		Comprobante comprobante = comprobantes.get(0);
		Conceptos conceptos = comprobante.getConceptos();
		assertEquals(3, conceptos.getConcepto().size());
		Concepto concepto1 = conceptos.getConcepto().get(0);
		Concepto concepto2 = conceptos.getConcepto().get(1);
		Concepto concepto3 = conceptos.getConcepto().get(2);

		assertEquals(new BigDecimal(20000), concepto1.getImporte());
		assertEquals("Salario", concepto1.getDescripcion());
		assertEquals("1", concepto1.getNoIdentificacion());
		assertEquals(new BigDecimal(1), concepto1.getCantidad());
		assertEquals("SERVICIO", concepto1.getUnidad());
		assertEquals(new BigDecimal(20000), concepto1.getValorUnitario());

		assertEquals(new BigDecimal(300), concepto2.getImporte());
		assertEquals("Bono puntualidad", concepto2.getDescripcion());
		assertEquals("2", concepto2.getNoIdentificacion());
		assertEquals(new BigDecimal(1), concepto2.getCantidad());
		assertEquals("SERVICIO", concepto2.getUnidad());
		assertEquals(new BigDecimal(300), concepto2.getValorUnitario());

		assertEquals(new BigDecimal(700), concepto3.getImporte());
		assertEquals("Vales Despensa", concepto3.getDescripcion());
		assertEquals("3", concepto3.getNoIdentificacion());
		assertEquals(new BigDecimal(1), concepto3.getCantidad());
		assertEquals("SERVICIO", concepto3.getUnidad());
		assertEquals(new BigDecimal(700), concepto3.getValorUnitario());
	}

	// impuestos
	@Test
	public void createImpuestosTest() {

		List<Comprobante> comprobantes = bigDataConverter
				.createComprobante(nominaInfo);
		Comprobante comprobante = comprobantes.get(0);
		Retencion retencion = comprobante.getImpuestos().getRetenciones()
				.getRetencion().get(0);

		assertEquals("ISR", retencion.getImpuesto());
		assertEquals(new BigDecimal(500), retencion.getImporte());

	}

	// complemento
	@Test
	public void createComplementoNominaAttributesTest() { 
		List<Comprobante> comprobantes = bigDataConverter
				.createComprobante(nominaInfo);
		Comprobante comprobante = comprobantes.get(0);
		Complemento complemento = comprobante.getComplemento();
		Nomina nomina = (Nomina)complemento.getAny().get(0);
		assertEquals("12345678912345700000", nomina.getRegistroPatronal());
		assertEquals("8888", nomina.getNumEmpleado());
		assertEquals("AAAA990909HAABBBA9", nomina.getCURP());
		assertEquals(1, nomina.getTipoRegimen());
		assertEquals("122222222", nomina.getNumSeguridadSocial());
		assertEquals("2013-12-01T00:00:00.000-06:00", nomina.getFechaPago().toString());
		assertEquals("2013-12-01T00:00:00.000-06:00", nomina.getFechaInicialPago().toString());
		assertEquals("2013-12-15T00:00:00.000-06:00", nomina.getFechaFinalPago().toString());
		assertEquals(new BigDecimal(10), nomina.getNumDiasPagados());
		assertEquals("DEP999", nomina.getDepartamento());
		assertEquals(new BigInteger("123456789012347000"), nomina.getCLABE());
		assertEquals(new Integer(1), nomina.getBanco());
		assertEquals("2011-12-01T00:00:00.000-06:00", nomina.getFechaInicioRelLaboral().toString());
		assertEquals(new Integer(2), nomina.getAntiguedad());
		assertEquals("soldador", nomina.getPuesto());
		assertEquals("planta", nomina.getTipoContrato());
		assertEquals("Diurna", nomina.getTipoJornada());
		assertEquals("Semanal", nomina.getPeriodicidadPago());
		assertEquals(new BigDecimal(200.22), nomina.getSalarioBaseCotApor());
		assertEquals(new Integer(1), nomina.getRiesgoPuesto());
		assertEquals(new BigDecimal(220.333), nomina.getSalarioDiarioIntegrado());
	}
	
	@Test
	public void createComplementoNominaPercepcionesTest(){
		List<Comprobante> comprobantes = bigDataConverter
				.createComprobante(nominaInfo);
		Comprobante comprobante = comprobantes.get(0);
		Complemento complemento = comprobante.getComplemento();
		Nomina nomina = (Nomina)complemento.getAny().get(0);
		Percepciones percepciones = nomina.getPercepciones();
		Percepcion percepcion = percepciones.getPercepcion().get(0);
		Percepcion percepcion2 = percepciones.getPercepcion().get(1);
		Percepcion percepcion3 = percepciones.getPercepcion().get(2);
		
		assertEquals("CCC", percepcion.getClave());
		assertEquals(100, percepcion.getTipoPercepcion());
		assertEquals("Salario", percepcion.getConcepto());
		assertEquals(new BigDecimal(20000), percepcion.getImporteGravado());
		assertEquals(new BigDecimal(0), percepcion.getImporteExento());
		
		assertEquals("BBB", percepcion2.getClave());
		assertEquals(102, percepcion2.getTipoPercepcion());
		assertEquals("Bono puntualidad", percepcion2.getConcepto());
		assertEquals(new BigDecimal(300), percepcion2.getImporteGravado());
		assertEquals(new BigDecimal(0), percepcion2.getImporteExento());
		
		assertEquals("AAA", percepcion3.getClave());
		assertEquals(103, percepcion3.getTipoPercepcion());
		assertEquals("Vales Despensa", percepcion3.getConcepto());
		assertEquals(new BigDecimal(700), percepcion3.getImporteGravado());
		assertEquals(new BigDecimal(0), percepcion3.getImporteExento());
		
		assertEquals(new BigDecimal(21000), percepciones.getTotalGravado());
		assertEquals(new BigDecimal(0), percepciones.getTotalExento());
		

		
	}
	
	@Test
	public void createComplementoNominaDeduccionesTest(){
		List<Comprobante> comprobantes = bigDataConverter
				.createComprobante(nominaInfo);
		Comprobante comprobante = comprobantes.get(0);
		Complemento complemento = comprobante.getComplemento();
		Nomina nomina = (Nomina)complemento.getAny().get(0);
		Deducciones deducciones = nomina.getDeducciones();
		Deduccion deduccion = deducciones.getDeduccion().get(0);
		Deduccion deduccion2 = deducciones.getDeduccion().get(1);
		
		assertEquals(101, deduccion.getTipoDeduccion());
		assertEquals("XSS", deduccion.getClave());
		assertEquals("IMSS", deduccion.getConcepto());
		assertEquals(new BigDecimal(1000), deduccion.getImporteGravado());
		assertEquals(new BigDecimal(0), deduccion.getImporteExento());
		
		assertEquals(102, deduccion2.getTipoDeduccion());
		assertEquals("DDD", deduccion2.getClave());
		assertEquals("ISR", deduccion2.getConcepto());
		assertEquals(new BigDecimal(500), deduccion2.getImporteGravado());
		assertEquals(new BigDecimal(0), deduccion2.getImporteExento());
		
		assertEquals(new BigDecimal(1500), deducciones.getTotalGravado());
		assertEquals(new BigDecimal(0), deducciones.getTotalExento());
		

		
	}
	
	@Test
	public void createComplementoNominaIncapacidadesTest(){
		List<Comprobante> comprobantes = bigDataConverter
				.createComprobante(nominaInfo);
		Comprobante comprobante = comprobantes.get(0);
		Complemento complemento = comprobante.getComplemento();
		Nomina nomina = (Nomina)complemento.getAny().get(0);
		Incapacidades incapacidades = nomina.getIncapacidades();
		Incapacidad incapacidad = incapacidades.getIncapacidad().get(0);
		
		assertEquals(new BigDecimal(2), incapacidad.getDiasIncapacidad());
		assertEquals(1, incapacidad.getTipoIncapacidad());
		assertEquals(new BigDecimal(2000), incapacidad.getDescuento());
	}
	
	
	@Test
	public void createComplementoNominaHorasExtraTest(){
		List<Comprobante> comprobantes = bigDataConverter
				.createComprobante(nominaInfo);
		Comprobante comprobante = comprobantes.get(0);
		Complemento complemento = comprobante.getComplemento();
		Nomina nomina = (Nomina)complemento.getAny().get(0);
		HorasExtras horasExtras = nomina.getHorasExtras();
		HorasExtra horasExtra = horasExtras.getHorasExtra().get(0);
		
		assertEquals(1, horasExtra.getDias());
		assertEquals("Dobles", horasExtra.getTipoHoras());
		assertEquals(20, horasExtra.getHorasExtra());
		assertEquals(new BigDecimal(3000), horasExtra.getImportePagado());
		
	}
	
	
	@Test
	public void createComprobanteAttributesTest(){
		List<Comprobante> comprobantes = bigDataConverter
				.createComprobante(nominaInfo);
		Comprobante comprobante = comprobantes.get(0);
		
		assertEquals("3.2", comprobante.getVersion());
		assertEquals("Contado", comprobante.getCondicionesDePago());
		assertEquals(new BigDecimal(1000), comprobante.getDescuento());
		assertEquals(new BigDecimal(21000), comprobante.getSubTotal());
		assertEquals(new BigDecimal(19500), comprobante.getTotal());
		
	}

	// complemento
	// condicionesDePago
	// descuento
	// fecha
	// folio
	// formaDePago
	// impuestos
	// lugarExpedicion
	// metodoDePago
	// motivoDescuento / fijo
	// subtotal
	// total
	// version / fijo 3.2

}
