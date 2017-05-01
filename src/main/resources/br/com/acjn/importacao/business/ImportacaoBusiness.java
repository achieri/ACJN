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
		// Iterator<?> it = mapa.entrySet().iterator();
		List<String> listaCampos = mapa.get(1);
		for (String s : listaCampos) {
			listaTipoCampo.add(obtemTipoCampo(s));
		}
		return listaTipoCampo;

	}

	public List<String> executaProcessoImportacao(Importacao importacao, ImportacaoVO importacaoVO)
			throws ImportacaoException {

		Map<Integer, List<String>> mapa = importacao.obtemDadosArquivo(importacaoVO);
		List<TipoDadosEnum> listaTipoCampo = obtemListaDataTypes(mapa);
		List<String> lista = validaArquivo(mapa, listaTipoCampo, importacaoVO.getIsCabecalho());
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
	private List<String> validaArquivo(Map<Integer, List<String>> mapa, List<TipoDadosEnum> listaTipoCampo,
			Boolean temCabecalho) {
		List<String> listaRetorno = new ArrayList<String>();
		int j = temCabecalho ? 1 : 0;
		for (int i = j; i < mapa.size(); i++) {
			List<String> listaCampos = mapa.get(i);
			for (int k = 0; k < listaCampos.size(); k++) {
				TipoDadosEnum tipoTemp = obtemTipoCampo(listaCampos.get(k));
				if (listaTipoCampo.get(k) == tipoTemp) {
					listaRetorno.add("O campo esperado na linha " + k + " é " + listaTipoCampo.get(k).getValor()
							+ " e o detectado foi " + tipoTemp.getValor());
				}
			}
		}
		return null;
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
			switch (enumTemp) {
			case LONG:
				if (Long.class.isInstance(s)) {
					return TipoDadosEnum.LONG;
				}
				break;
			case DATA_BR_BARRA_COMPLETA:
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_BR_BARRA_COMPLETA.getValor()).parse(s);
					return TipoDadosEnum.DATA_BR_BARRA_COMPLETA;
				} catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s)", e);
					continue;
				}
			case DATA_BR_TRACO_COMPLETO:
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_BR_TRACO_COMPLETO.getValor()).parse(s);
					return TipoDadosEnum.DATA_BR_TRACO_COMPLETO;
				} catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s)", e);
					continue;
				}
			case DATA_BR_BARRA:
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_BR_BARRA.getValor()).parse(s);
					return TipoDadosEnum.DATA_BR_BARRA;
				} catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s)", e);
					continue;
				}
			case DATA_BR_TRACO:
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_BR_TRACO.getValor()).parse(s);
					return TipoDadosEnum.DATA_BR_TRACO;
				} catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s)", e);
					continue;
				}
			case DATA_BR_BARRA_ANO_MES:
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_BR_BARRA_ANO_MES.getValor()).parse("01/" + s);
					return TipoDadosEnum.DATA_BR_BARRA_ANO_MES;
				} catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s)", e);
					continue;
				}
			case DATA_BR_BARRA_ANO_MES1:
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_BR_BARRA_ANO_MES1.getValor()).parse("1" + s);
					return TipoDadosEnum.DATA_BR_BARRA_ANO_MES1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s)", e);
					continue;
				}
			case DATA_BR_TRACO_ANO_MES1:
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_BR_TRACO_ANO_MES1.getValor()).parse("1" + s);
					return TipoDadosEnum.DATA_BR_TRACO_ANO_MES1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s)", e);
					continue;
				}
			case DATA_BR_TRACO_ANO_MES:
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_BR_TRACO_ANO_MES.getValor()).parse("01" + s);
					return TipoDadosEnum.DATA_BR_TRACO_ANO_MES;
				} catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s)", e);
					continue;
				}
			case DATA_US_BARRA_COMPLETO:
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_US_BARRA_COMPLETO.getValor()).parse(s);
					return TipoDadosEnum.DATA_US_BARRA_COMPLETO;
				} catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s)", e);
					continue;
				}
			case DATA_US_TRACO_COMPLETO:
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_US_TRACO_COMPLETO.getValor()).parse(s);
					return TipoDadosEnum.DATA_US_TRACO_COMPLETO;
				} catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s)", e);
					continue;
				}
			case DATA_US_BARRA:
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_US_BARRA.getValor()).parse(s);
					return TipoDadosEnum.DATA_US_BARRA;
				} catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s)", e);
					continue;
				}
			case DATA_US_TRACO:
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_US_TRACO.getValor()).parse(s);
					return TipoDadosEnum.DATA_US_TRACO;
				} catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s)", e);
					continue;
				}
			case DATA_US_BARRA_ANO_MES:
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_US_BARRA_ANO_MES.getValor()).parse("01/" + s);
					return TipoDadosEnum.DATA_US_BARRA_ANO_MES;
				} catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s)", e);
					continue;
				}
			case DATA_US_BARRA_ANO_MES1:
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_US_BARRA_ANO_MES1.getValor()).parse("1" + s);
					return TipoDadosEnum.DATA_US_BARRA_ANO_MES1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s)", e);
					continue;
				}
			case DATA_US_TRACO_ANO_MES1:
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_US_TRACO_ANO_MES1.getValor()).parse("1" + s);
					return TipoDadosEnum.DATA_US_TRACO_ANO_MES1;
				} catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s)", e);
					continue;
				}
			case DATA_US_TRACO_ANO_MES:
				try {
					Date date = new SimpleDateFormat(TipoDadosEnum.DATA_US_TRACO_ANO_MES.getValor()).parse("01" + s);
					return TipoDadosEnum.DATA_US_TRACO_ANO_MES;
				} catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.INFO, "TipoDadosEnum adicionaTipoCampo(String s)", e);
					continue;
				}
			case DECIMAL:
				if (BigDecimal.class.isInstance(s)) {
					return TipoDadosEnum.DECIMAL;
				}
				break;
			default:
				// return TipoDadosEnum.STRING;
			}

		}
		return TipoDadosEnum.STRING;
	}

}
