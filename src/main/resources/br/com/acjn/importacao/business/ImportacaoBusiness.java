package br.com.acjn.importacao.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import br.com.acjn.util.Utils;

public class ImportacaoBusiness {
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
		// TODO Auto-generated constructor stub
	}

	public List<String> obtemListaDataTypes(Map<Integer, List<String>> mapa, Boolean isCabecalho) {
		Properties prop = Utils.obtemProperties();
		List<String> listaTipoCampo = new ArrayList<String>();
		Iterator<?> it = mapa.entrySet().iterator();
		Map<String, String> mapPropriedades = Utils.obtemValoresPropriedades(prop);
		List<String> listaCampos = mapa.get(1);
		for (String s : listaCampos) {
			boolean isTipoCerto = false;
			try {
				Iterator<String> itTemp = listaTipoCampo.iterator();
				while (itTemp.hasNext()) {
					String key = (String) it.next();
					mapa.put(key, prop.getProperty(key));
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

			System.out.println(pair.getKey() + " = " + pair.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}

		return lista;
	}

}
