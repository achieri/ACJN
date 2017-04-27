package br.com.acjn.test;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.acjn.importacao.vo.ImportacaoVO;

public class Teste {

	public static void main(String[] args) {
		Teste1 teste1 = new Teste1();
		teste1.setaParametros();

	}

}

class Teste1 {
	@Autowired
	ImportacaoVO importacaoVO;

	void setaParametros() {
		importacaoVO.setDelimitador(";");
		System.out.println(importacaoVO.getDelimitador());
	}

}