package br.com.acjn.importacao.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.acjn.importacao.business.ImportacaoTXT;
import br.com.acjn.importacao.exception.ImportacaoException;

public class ImportacaoTXTTest {
	private ImportacaoTXT imp = new ImportacaoTXT();

	@Test
	public void testObtemDadosArquivo() throws ImportacaoException {

		assertNotNull(imp.obtemDadosArquivo("xxx"));
		// fail("Not yet implemented");
	}

}
