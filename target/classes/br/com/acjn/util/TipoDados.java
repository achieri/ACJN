package br.com.acjn.util;

public enum TipoDados {
	INTEIRO("1"), LONG("2"), DATA_BR(""), DATA_US(""), DATA_BR_BARRA();

	public final String valor;

	TipoDados(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}
}3=percentual 4=bigdecimal 5=dd-MM-yyyy 6=yyyy-MM-dd 7=dd-MM-

yyyy HH:mm:ss 8=yyyy-MM-
dd HH:mm:ss 9=yyyy-M 10=yyyy-MM