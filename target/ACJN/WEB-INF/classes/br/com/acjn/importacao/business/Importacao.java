package br.com.acjn.importacao.business;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.com.acjn.importacao.exception.ImportacaoException;
import br.com.acjn.importacao.vo.ImportacaoVO;

public interface Importacao extends Serializable {

	public Map<Integer, List<String>> obtemDadosArquivo(ImportacaoVO importacaoVO) throws ImportacaoException;

}
