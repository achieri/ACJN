package br.com.acjn.importacao.business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.acjn.importacao.exception.ImportacaoException;

/**
 * Essa classe serve para impotar aquivos TXT Importacao
 * 
 * @author André Chierichetti
 * @version 1
 * @since Release 01 da aplicação
 */
public class ImportacaoTXT implements Importacao {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	private static final long serialVersionUID = 1L;
	private String delimitador;
	private String qualificador;

	public String getDelimitador() {
		return delimitador;
	}

	public void setDelimitador(String delimitador) {
		this.delimitador = delimitador;
	}

	public String getQualificador() {
		return qualificador;
	}

	public void setQualificador(String qualificador) {
		this.qualificador = qualificador;
	}

	/**
	 * Le linha por linha e transforma em tabela
	 * 
	 * @throws ImportacaoException
	 */
	public Map<Integer, List<String>> obtemDadosArquivo(String caminhoArquivo) throws ImportacaoException {
		BufferedReader br = null;
		FileReader fr = null;
		Map<Integer, List<String>> mapa = new HashMap<Integer, List<String>>();
		try {

			fr = new FileReader(caminhoArquivo);
			br = new BufferedReader(fr);
			String sCurrentLine;
			br = new BufferedReader(new FileReader(caminhoArquivo));
			StringTokenizer st;
			int i = 0;
			List<String> lista;
			while ((sCurrentLine = br.readLine()) != null) {
				st = new StringTokenizer(sCurrentLine, delimitador);
				lista = new ArrayList<String>();
				while (st.hasMoreElements()) {
					lista.add((String) st.nextElement());
				}
				mapa.put(i++, lista);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "Map<Integer, List<String>> obtemDadosArquivo(String caminhoArquivo)", e);
			throw new ImportacaoException(e);
		} finally {
			try {
				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return mapa;
	}

}
