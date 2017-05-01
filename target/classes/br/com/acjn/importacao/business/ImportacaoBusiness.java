package br.com.acjn.importacao.business;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.acjn.importacao.exception.ImportacaoException;
import br.com.acjn.importacao.vo.ImportacaoVO;
import br.com.acjn.util.TipoDadosEnum;

public class ImportacaoBusiness {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private static ImportacaoBusiness instance = new ImportacaoBusiness();

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static ImportacaoBusiness getInstance() {
		return instance;
	}

	private ImportacaoBusiness() {
	}

	/**
	 * obtem lista de tipo dos campos
	 * 
	 * @param mapa
	 * @return
	 */
	public List<TipoDadosEnum> obtemListaDataTypes(Map<Integer, List<String>> mapa) {
		List<TipoDadosEnum> listaTipoCampo = new ArrayList<TipoDadosEnum>();
		List<String> listaCampos = mapa.get(1);
		for (String s : listaCampos) {
			listaTipoCampo.add(obtemTipoCampo(s));
		}
		return listaTipoCampo;

	}

	/**
	 * esse metodo é o principal da classe que fara todas as verificacoes
	 * 
	 * @param importacao
	 * @param importacaoVO
	 * @return
	 * @throws ImportacaoException
	 */
	public List<String> executaProcessoImportacao(Importacao importacao, ImportacaoVO importacaoVO)
			throws ImportacaoException {
		List<String> lista = new ArrayList<String>();
		Map<Integer, List<String>> mapa = importacao.obtemDadosArquivo(importacaoVO);
		List<TipoDadosEnum> listaTipoCampo = obtemListaDataTypes(mapa);
		List<String> listaCriticaQtdColuna = verificaUniformidadeColunas(mapa);
		if (listaCriticaQtdColuna.isEmpty()) {
			lista = validaTipagemArquivo(mapa, listaTipoCampo, importacaoVO.getIsCabecalho());
		} else {
			lista.addAll(listaCriticaQtdColuna);
		}
		return lista;
	}

	/**
	 * verifica se a quantidade de colunas é a mesma para todas as linhas da
	 * tabela
	 * 
	 * @param mapa
	 * @return
	 */
	private List<String> verificaUniformidadeColunas(Map<Integer, List<String>> mapa) {
		int totalColunas = mapa.get(0).size();
		List<String> lista = new ArrayList<String>();
		for (int i = 0; i < mapa.size(); i++) {
			if (totalColunas != mapa.get(i).size()) {
				lista.add("O total de colunas na linha " + i
						+ " não está de acordo com o tamanho espeficicado na linha 0. Total de campos encontrados é "
						+ mapa.get(i).size() + " e o esperado é " + totalColunas);
			}
		}
		return lista;
	}

	/**
	 * critica a cada linha o tipo que não esta de acordo com o especificado
	 * 
	 * @param mapa
	 * @param listaTipoCampo
	 * @param temCabecalho
	 * @return
	 */
	private List<String> validaTipagemArquivo(Map<Integer, List<String>> mapa, List<TipoDadosEnum> listaTipoCampo,
			Boolean temCabecalho) {
		List<String> listaRetorno = new ArrayList<String>();
		int j = temCabecalho ? 1 : 0;
		for (int i = j; i < mapa.size(); i++) {
			List<String> listaCampos = mapa.get(i);
			for (int k = 0; k < listaCampos.size(); k++) {
				TipoDadosEnum tipoTemp = obtemTipoCampo(listaCampos.get(k));
				if (listaTipoCampo.get(k) != tipoTemp) {
					listaRetorno.add("O campo esperado na linha " + j + " é " + listaTipoCampo.get(k).getValor()
							+ " e o detectado na linha " + i + " foi " + tipoTemp.getValor());
				}
			}
		}
		return listaRetorno;
	}

	/**
	 * obtem a lista de tipos de dados
	 * 
	 * @param s
	 * @return
	 */
	private TipoDadosEnum obtemTipoCampo(String s) {
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
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_BR_BARRA_COMPLETA.getValor()).parse(s);
					return TipoDadosEnum.DATA_BR_BARRA_COMPLETA;
				} catch (Exception e) {
					// e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO DATA_BR_BARRA_COMPLETA");
					continue;
				}
			case DATA_BR_TRACO_COMPLETO:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_BR_TRACO_COMPLETO.getValor()).parse(s);
					return TipoDadosEnum.DATA_BR_TRACO_COMPLETO;
				} catch (Exception e) {
					// e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO DATA_BR_TRACO_COMPLETO");
					continue;
				}
			case DATA_BR_BARRA:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_BR_BARRA.getValor()).parse(s);
					return TipoDadosEnum.DATA_BR_BARRA;
				} catch (Exception e) {
					// e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO DATA_BR_BARRA");
					continue;
				}
			case DATA_BR_TRACO:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_BR_TRACO.getValor()).parse(s);
					return TipoDadosEnum.DATA_BR_TRACO;
				} catch (Exception e) {
					// e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO DATA_BR_TRACO");
					continue;
				}
			case DATA_BR_BARRA_ANO_MES:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_BR_BARRA_ANO_MES.getValor()).parse("01/" + s);
					return TipoDadosEnum.DATA_BR_BARRA_ANO_MES;
				} catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO DATA_BR_BARRA_ANO_MES");
					continue;
				}
			case DATA_BR_BARRA_ANO_MES1:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_BR_BARRA_ANO_MES1.getValor()).parse("1" + s);
					return TipoDadosEnum.DATA_BR_BARRA_ANO_MES1;
				} catch (Exception e) {
					// e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO DATA_BR_BARRA_ANO_MES1");
					continue;
				}
			case DATA_BR_TRACO_ANO_MES1:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_BR_TRACO_ANO_MES1.getValor()).parse("1" + s);
					return TipoDadosEnum.DATA_BR_TRACO_ANO_MES1;
				} catch (Exception e) {
					// e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO DATA_BR_TRACO_ANO_MES1");
					continue;
				}
			case DATA_BR_TRACO_ANO_MES:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_BR_TRACO_ANO_MES.getValor()).parse("01" + s);
					return TipoDadosEnum.DATA_BR_TRACO_ANO_MES;
				} catch (Exception e) {
					// e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO DATA_BR_TRACO_ANO_MES");
					continue;
				}
			case DATA_US_BARRA_COMPLETO:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_US_BARRA_COMPLETO.getValor()).parse(s);
					return TipoDadosEnum.DATA_US_BARRA_COMPLETO;
				} catch (Exception e) {
					// e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO DATA_US_BARRA_COMPLETO");
					continue;
				}
			case DATA_US_TRACO_COMPLETO:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_US_TRACO_COMPLETO.getValor()).parse(s);
					return TipoDadosEnum.DATA_US_TRACO_COMPLETO;
				} catch (Exception e) {
					// e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO DATA_US_TRACO_COMPLETO");
					continue;
				}
			case DATA_US_BARRA:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_US_BARRA.getValor()).parse(s);
					return TipoDadosEnum.DATA_US_BARRA;
				} catch (Exception e) {
					// e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO DATA_US_BARRA");
					continue;
				}
			case DATA_US_TRACO:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_US_TRACO.getValor()).parse(s);
					return TipoDadosEnum.DATA_US_TRACO;
				} catch (Exception e) {
					// e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO DATA_US_TRACO");
					continue;
				}
			case DATA_US_BARRA_ANO_MES:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_US_BARRA_ANO_MES.getValor()).parse("01/" + s);
					return TipoDadosEnum.DATA_US_BARRA_ANO_MES;
				} catch (Exception e) {
					// e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO DATA_US_BARRA_ANO_MES");
					continue;
				}
			case DATA_US_BARRA_ANO_MES1:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_US_BARRA_ANO_MES1.getValor()).parse("1" + s);
					return TipoDadosEnum.DATA_US_BARRA_ANO_MES1;
				} catch (Exception e) {
					// e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO DATA_US_BARRA_ANO_MES1");
					continue;
				}
			case DATA_US_TRACO_ANO_MES1:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_US_TRACO_ANO_MES1.getValor()).parse("1" + s);
					return TipoDadosEnum.DATA_US_TRACO_ANO_MES1;
				} catch (Exception e) {
					// e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO DATA_US_TRACO_ANO_MES1");
					continue;
				}
			case DATA_US_TRACO_ANO_MES:
				if (tamFormatoEnum != s.length()) {
					continue;
				}
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_US_TRACO_ANO_MES.getValor()).parse("01" + s);
					return TipoDadosEnum.DATA_US_TRACO_ANO_MES;
				} catch (Exception e) {
					// e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s) TIPO DATA_US_TRACO_ANO_MES");
					continue;
				}
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
