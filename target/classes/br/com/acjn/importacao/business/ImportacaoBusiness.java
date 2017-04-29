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
		List<String> listaTipoCampo = new ArrayList<String>();
		Iterator<?> it = mapa.entrySet().iterator();
		List<String> listaCampos = mapa.get(1);
		for (String s : listaCampos) {
			Iterator<String> itTemp = listaTipoCampo.iterator();
			while (itTemp.hasNext()) {
				String key = (String) it.next();
				adicionaTipoCampo(s);
			}


			System.out.println(pair.getKey() + " = " + pair.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}

		return lista;
	}

	private void adicionaTipoCampo(String s) {
		Properties prop = Utils.obtemProperties();
		Map<String, String> mapPropriedades = Utils.obtemValoresPropriedades(prop);
		switch (key) {
		case value:
			
			break;

		default:
			break;
		}
		
	}

}
