package br.com.acjn.importacao.business;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

interface Importacao extends Serializable {

	public Map<Integer, List<String>> obtemDadosArquivo(String caminhoArquivo) throws Exception;

}
