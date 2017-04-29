package br.com.acjn.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class Utils {

	public static Properties obtemProperties() {
		Properties prop = new Properties();

		InputStream input = null;

		try {

			input = new FileInputStream("formatacao_campo.properties");
			prop.load(input);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return prop;
	}

	/**
	 * obtem todos os valores da propriedade
	 * 
	 * @param prop
	 * @return
	 */
	public static Map<String, String> obtemValoresPropriedades(Properties prop) {
		Enumeration<?> e = prop.propertyNames();
		Map<String, String> mapa = new HashMap<String, String>();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			mapa.put(key, prop.getProperty(key));
		}
		return mapa;
	}

}
