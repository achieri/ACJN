package br.com.acjn.util;

public enum TipoDadosEnum {
	LONG("1"), STRING("3"), DATA_BR_TRACO("dd-MM-yyyy"), DATA_BR_TRACO_COMPLETO("dd-MM-yyyy HH:mm:ss"), DATA_US_TRACO(
			"yyyy-MM-dd"), DATA_US_TRACO_COMPLETO("yyyy-MM-dd HH:mm:ss"), DATA_US_TRACO_ANO_MES(
					"yyyy-MM"), DATA_US_TRACO_ANO_MES1("yyyy-M"), DATA_US_BARRA("yyyy/MM/dd"), DATA_US_BARRA_COMPLETO(
							"yyyy/MM/dd HH:mm:ss"), DATA_US_BARRA_ANO_MES("yyyy/MM"), DATA_US_BARRA_ANO_MES1(
									"yyyy/M"), DATA_BR_TRACO_ANO_MES("MM-yyyy"), DATA_BR_TRACO_ANO_MES1(
											"M-yyyy"), DATA_BR_BARRA("dd/MM/yyyy"), DATA_BR_BARRA_COMPLETA(
													"dd/MM/yyyy HH:mm:ss"), DATA_BR_BARRA_ANO_MES(
															"MM/yyyy"), DATA_BR_BARRA_ANO_MES1("M/yyyy"), DECIMAL("2");

	public final String valor;

	TipoDadosEnum(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}

}