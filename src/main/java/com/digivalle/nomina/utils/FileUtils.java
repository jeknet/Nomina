/*
 * This code was developed by SISCO - Sistemas Corporativos
 * 2009 All Rights reserved.
 */
package com.digivalle.nomina.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 
 * @author Roberto Del Valle
 * @version 1.0
 * @since 9/04/2009
 */
public class FileUtils {

	public static String readFileContents(File file) throws Exception {
		return readFileContents(file, Charset.forName("UTF-8"));
	}

	public static String readFileContents(File file, Charset charset)
			throws Exception {
		BufferedReader br = null;
		StringBuilder sb = null;
		String line;
		if (file != null && charset != null) {
			if (file.exists()) {
				try {
					sb = new StringBuilder();
					br = Files.newBufferedReader(Paths.get(file.getPath()),
							charset);
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					br.close();
					return sb.toString();
				} catch (IOException ex) {
					throw ex;
				}
			}
			throw new Exception("The file does not exist.");
		}
		throw new Exception("Invalid or incomplete parameters.");
	}

	public static void writeFileContents(File file, String content)
			throws Exception {
		writeFileContents(file, content, Boolean.TRUE);
	}

	public static void overwriteFileContents(File file, String content)
			throws Exception {
		writeFileContents(file, content, Boolean.FALSE);
	}

	private static void writeFileContents(File file, String content,
			boolean newFile) throws Exception {
		if (file != null && content != null) {
			try {
				Files.write(FileSystems.getDefault().getPath(file.getPath()),
						content.getBytes(),
						(newFile ? StandardOpenOption.CREATE
								: StandardOpenOption.TRUNCATE_EXISTING));
				return;
			} catch (IOException ioe) {
				throw ioe;
			}
		}
		throw new Exception("Invalid or incomplete parameters.");
	}
}