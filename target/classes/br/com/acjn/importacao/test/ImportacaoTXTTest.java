package br.com.acjn.importacao.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.acjn.importacao.business.ImportacaoBusiness;
import br.com.acjn.importacao.dao.ImportacaoTXT;
import br.com.acjn.importacao.exception.ImportacaoException;
import br.com.acjn.importacao.vo.ImportacaoVO;

public class ImportacaoTXTTest {
	private ImportacaoTXT imp = new ImportacaoTXT();
	private static ImportacaoVO importacaoVO = new ImportacaoVO();
	private Map<Integer, List<String>> mapa;

	@BeforeClass
	public static void setup() {
		importacaoVO.setDelimitador(";");
		importacaoVO.setPathArquivo("src/main/resources/arquivos_importacao_teste/Exemplos_Dia_a_Dia.txt");
		importacaoVO.setIsCabecalho(true);
	}

	@Test
	public void testObtemDadosArquivo() throws ImportacaoException {
		mapa = imp.obtemDadosArquivo(importacaoVO);
		List<String> lista = ImportacaoBusiness.getInstance().executaProcessoImportacao(imp, importacaoVO);
		assertNotNull(lista.isEmpty());
	}

	@Test
	public void testIdentidicaCampos() {
		// ImportacaoBusiness.
	}

}
