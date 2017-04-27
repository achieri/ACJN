package br.com.acjn.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.acjn.importacao.business.Importacao;
import br.com.acjn.importacao.vo.ImportacaoVO;

@Service
public class ImportacaoService {

	private Map<Integer, List<String>> mapa;

	public Map<Integer, List<String>> obtemDadosArquivo(Importacao importacao, ImportacaoVO importacaoVO)
			throws Exception {
		mapa = importacao.obtemDadosArquivo(importacaoVO);
		return mapa;
	}
}
