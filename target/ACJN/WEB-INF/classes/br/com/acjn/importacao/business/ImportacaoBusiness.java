package br.com.acjn.importacao.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import br.com.acjn.importacao.exception.ImportacaoException;
import br.com.acjn.importacao.vo.ImportacaoVO;
import br.com.acjn.util.StringUtils;
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
			listaTipoCampo.add(StringUtils.obtemTipoCampo(s));
		}
		return listaTipoCampo;

	}

	/**
	 * esse metodo � o principal da classe que fara todas as verificacoes
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
	 * verifica se a quantidade de colunas � a mesma para todas as linhas da
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
						+ " n�o est� de acordo com o tamanho espeficicado na linha 0. Total de campos encontrados � "
						+ mapa.get(i).size() + " e o esperado � " + totalColunas);
			}
		}
		return lista;
	}

	/**
	 * critica a cada linha o tipo que n�o esta de acordo com o especificado
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
				TipoDadosEnum tipoTemp = StringUtils.obtemTipoCampo(listaCampos.get(k));
				if (listaTipoCampo.get(k) != tipoTemp) {
					listaRetorno.add("O campo esperado na coluna " + (k + 1) + " � " + listaTipoCampo.get(k).getValor()
							+ " e o detectado na linha " + i + " foi " + tipoTemp.getValor());
				}
			}
		}
		return listaRetorno;
	}

}
