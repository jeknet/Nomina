package com.digivalle.nomina;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.List;

import mx.bigdata.sat.cfdi.CFDv32;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante;
import mx.bigdata.sat.security.KeyLoaderEnumeration;
import mx.bigdata.sat.security.factory.KeyLoaderFactory;

import com.digivalle.nomina.components.bigdata.converter.BigDataConverter;
import com.digivalle.nomina.components.extractors.implementations.RawDataExtractorExcelImpl;
import com.digivalle.nomina.models.NominaInfo;
import com.digivalle.nomina.utils.FileUtils;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		RawDataExtractorExcelImpl dataExtractor = new RawDataExtractorExcelImpl();
		InputStream dataStream;
		try {
			dataStream = new FileInputStream(
					"/Users/huac/Clients/digivalle/nomina/src/test/resources/ejemplo.xls");
			NominaInfo nominaInfo = dataExtractor.readNomina(dataStream);
			BigDataConverter bigDataConverter = new BigDataConverter();
			List<Comprobante> comprobantes = bigDataConverter
					.createComprobante(nominaInfo);
			for (Comprobante comprobante : comprobantes) {
				CFDv32 cfd32 = new CFDv32(comprobante,
						"mx.bigdata.sat.common.nomina.schema");
				cfd32.addNamespace("http://www.sat.gob.mx/nomina", "nomina");
				PrivateKey key = KeyLoaderFactory
						.createInstance(
								KeyLoaderEnumeration.PRIVATE_KEY_LOADER,
								new FileInputStream(
										"/Users/huac/Clients/digivalle/nomina/src/test/resources/CSD01_AAA010101AAA.key"),
								"12345678a").getKey();
				X509Certificate cert = KeyLoaderFactory
						.createInstance(
								KeyLoaderEnumeration.PUBLIC_KEY_LOADER,
								"/Users/huac/Clients/digivalle/nomina/src/test/resources/CSD01_AAA010101AAA.cer")
						.getKey();
				cfd32.sellar(key, cert);
				cfd32.validar();
				cfd32.verificar();
				cfd32.guardar(new FileOutputStream("/Users/huac/1.xml"));
				timbrar("/Users/huac/1.xml");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	private static void timbrarInvoice(String absolutePath) throws Exception {
        Timbre comercioDigitalResponse;
        File xml = new File(absolutePath);
            comercioDigitalResponse = Servicios.timbrar(FileUtils.readFileContents(xml), "AAA010101AAA", "PWD", Boolean.TRUE);
            if (comercioDigitalResponse.status == 200) {
                FileUtils.overwriteFileContents(xml, comercioDigitalResponse.xml_timbrado);
                return;
            }
            throw new Exception("-Comercio Digital- Status:" + comercioDigitalResponse.status + ", Error: " + comercioDigitalResponse.error);
    }
}
