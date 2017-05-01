package br.com.acjn.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class StringUtils {
	private static Logger logger = Logger.getLogger("Utils");
	private static Calendar cal = Calendar.getInstance();

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

	private static boolean isDataValida(TipoDadosEnum tipoDadosEnum, String campo) {
		try {
			Date date = new SimpleDateFormat(tipoDadosEnum.getValor()).parse(campo);
			cal.setTime(date);
			if (cal.get(Calendar.YEAR) > 1950) {
				return true;
			}
		} catch (Exception e) {
			logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) " + tipoDadosEnum.name());
		}
		return false;
	}

	/**
	 * obtem a lista de tipos de dados
	 * 
	 * @param s
	 * @return
	 */
	public static TipoDadosEnum obtemTipoCampo(String s) {
		List<TipoDadosEnum> list = Arrays.asList(TipoDadosEnum.values());
		for (TipoDadosEnum enumTemp : list) {
			int tamFormatoEnum = enumTemp.getValor().length();
			switch (enumTemp) {
			case LONG:
				try {
					Long.parseLong(s);
					return TipoDadosEnum.LONG;
				} catch (Exception e) {
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO LONG");
					continue;
				}

			case DATA_BR_BARRA_COMPLETA:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				if (!isDataValida(TipoDadosEnum.DATA_BR_BARRA_COMPLETA, s)) {
					continue;
				}
				return TipoDadosEnum.DATA_BR_BARRA_COMPLETA;

			case DATA_BR_BARRA_HH_MIN:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				if (!isDataValida(TipoDadosEnum.DATA_BR_BARRA_HH_MIN, s)) {
					continue;
				}
				return TipoDadosEnum.DATA_BR_BARRA_HH_MIN;

			case DATA_BR_TRACO_COMPLETO:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				if (!isDataValida(TipoDadosEnum.DATA_BR_TRACO_COMPLETO, s)) {
					continue;
				}
				return TipoDadosEnum.DATA_BR_TRACO_COMPLETO;

			case DATA_BR_TRACO_HH_MIN:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				if (!isDataValida(TipoDadosEnum.DATA_BR_TRACO_HH_MIN, s)) {
					continue;
				}
				return TipoDadosEnum.DATA_BR_TRACO_HH_MIN;

			case DATA_BR_BARRA:
				if (tamFormatoEnum != s.length()) {
					continue;
				}

				if (!isDataValida(TipoDadosEnum.DATA_BR_BARRA, s)) {
					continue;
				}
				return TipoDadosEnum.DATA_BR_BARRA;

			case DATA_BR_TRACO:
				if (s.length() - tamFormatoEnum > 1 || s.length() - tamFormatoEnum < 0) {
					continue;
				}

				if (!isDataValida(TipoDadosEnum.DATA_BR_TRACO, s)) {
					continue;
				}
				return TipoDadosEnum.DATA_BR_TRACO;

			case DATA_BR_BARRA_ANO_MES:
				if (tamFormatoEnum - s.length() > 1 || tamFormatoEnum - s.length() < 0) {
					continue;
				}

				if (!isDataValida(TipoDadosEnum.DATA_BR_BARRA_ANO_MES, s)) {
					continue;
				}
				return TipoDadosEnum.DATA_BR_BARRA_ANO_MES;

			case DATA_BR_TRACO_ANO_MES:
				if (tamFormatoEnum - s.length() > 1 || tamFormatoEnum - s.length() < 0) {
					continue;
				}

				if (!isDataValida(TipoDadosEnum.DATA_BR_TRACO_ANO_MES, s)) {
					continue;
				}
				return TipoDadosEnum.DATA_BR_TRACO_ANO_MES;

			case DATA_US_BARRA_COMPLETO:
				if (tamFormatoEnum != s.length()) {
					continue;
				}

				if (!isDataValida(TipoDadosEnum.DATA_US_BARRA_COMPLETO, s)) {
					continue;
				}

				return TipoDadosEnum.DATA_US_BARRA_COMPLETO;

			case DATA_US_BARRA_HH_MIN:
				if (tamFormatoEnum != s.length()) {
					continue;
				}

				if (!isDataValida(TipoDadosEnum.DATA_US_BARRA_HH_MIN, s)) {
					continue;
				}

				return TipoDadosEnum.DATA_US_BARRA_HH_MIN;

			case DATA_US_TRACO_COMPLETO:
				if (tamFormatoEnum != s.length()) {
					continue;
				}

				if (!isDataValida(TipoDadosEnum.DATA_US_TRACO_COMPLETO, s)) {
					continue;
				}

				return TipoDadosEnum.DATA_US_TRACO_COMPLETO;

			case DATA_US_TRACO_HH_MIN:
				if (tamFormatoEnum != s.length()) {
					continue;
				}

				if (!isDataValida(TipoDadosEnum.DATA_US_TRACO_HH_MIN, s)) {
					continue;
				}

				return TipoDadosEnum.DATA_US_TRACO_HH_MIN;

			case DATA_US_BARRA:
				if (tamFormatoEnum != s.length()) {
					continue;
				}

				if (!isDataValida(TipoDadosEnum.DATA_US_BARRA, s)) {
					continue;
				}

				return TipoDadosEnum.DATA_US_BARRA;

			case DATA_US_TRACO:
				if (tamFormatoEnum != s.length()) {
					continue;
				}

				if (!isDataValida(TipoDadosEnum.DATA_US_TRACO, s)) {
					continue;
				}

				return TipoDadosEnum.DATA_US_TRACO;

			case DATA_US_BARRA_ANO_MES:
				if (tamFormatoEnum - s.length() > 1 || tamFormatoEnum - s.length() < 0) {
					continue;
				}

				if (!isDataValida(TipoDadosEnum.DATA_US_BARRA_ANO_MES, s)) {
					continue;
				}

				return TipoDadosEnum.DATA_US_BARRA_ANO_MES;

			case DATA_US_TRACO_ANO_MES:
				if (tamFormatoEnum - s.length() > 1 || tamFormatoEnum - s.length() < 0) {
					continue;
				}

				if (!isDataValida(TipoDadosEnum.DATA_US_TRACO_ANO_MES, s)) {
					continue;
				}

				return TipoDadosEnum.DATA_US_TRACO_ANO_MES;

			case DECIMAL_BR:
				if (s.isEmpty()) {
					continue;
				}
				try {
					s = s.replaceAll("\\.", "").replaceAll(",", ".");
					return TipoDadosEnum.DECIMAL_BR;
				} catch (Exception e) {
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO DECIMAL_BR");
					continue;
				}
			case DECIMAL_US:
				if (s.isEmpty()) {
					continue;
				}
				try {
					BigDecimal bigDecimal = new BigDecimal(s);
					return TipoDadosEnum.DECIMAL_US;
				} catch (Exception e) {
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s)");
					continue;
				}
			default:
				// return TipoDadosEnum.STRING;
			}

		}
		return TipoDadosEnum.STRING;

	}

}
