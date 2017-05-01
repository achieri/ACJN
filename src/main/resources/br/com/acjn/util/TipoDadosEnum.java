package br.com.acjn.util;

/**
 * nao mexer na sequencia de tipo de dado
 * 
 * @author achie
 *
 */
public enum TipoDadosEnum {
	LONG("LONG"), DATA_BR_TRACO("dd-MM-yyyy"), DATA_BR_TRACO_COMPLETO("dd-MM-yyyy HH:mm:ss"), DATA_BR_TRACO_HH_MIN(
			"dd-MM-yyyy HH:mm"), DATA_BR_TRACO_ANO_MES("MM-yyyy"), DATA_BR_BARRA("dd/MM/yyyy"), DATA_BR_BARRA_COMPLETA(
					"dd/MM/yyyy HH:mm:ss"), DATA_BR_BARRA_HH_MIN("dd/MM/yyyy HH:mm"), DATA_BR_BARRA_ANO_MES(
							"MM/yyyy"), DATA_US_TRACO("yyyy-MM-dd"), DATA_US_TRACO_COMPLETO(
									"yyyy-MM-dd HH:mm:ss"), DATA_US_TRACO_HH_MIN(
											"yyyy-MM-dd HH:mm"), DATA_US_TRACO_ANO_MES("yyyy-MM"), DATA_US_BARRA(
													"yyyy/MM/dd"), DATA_US_BARRA_COMPLETO(
															"yyyy/MM/dd HH:mm:ss"), DATA_US_BARRA_HH_MIN(
																	"yyyy/MM/dd HH:mm"), DATA_US_BARRA_ANO_MES(
																			"yyyy/MM"), DECIMAL_US(
																					"DECIMAL_US"), DECIMAL_BR(
																							"DECIMAL_BR"), STRING(
																									"STRING"); //

	public final String valor;

	TipoDadosEnum(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}

}