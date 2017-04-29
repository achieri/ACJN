package br.com.acjn.importacao.vo;

import java.io.Serializable;

import org.springframework.stereotype.Service;

/**
 * Parametros do arquivo de importacao
 * 
 * @author achie
 *
 */
@Service
public class ImportacaoVO implements Serializable {

	private String delimitador;
	private String qualificador;
	private String pathArquivo;
	private Boolean isCabecalho;

	public Boolean getIsCabecalho() {
		return isCabecalho;
	}

	public void setIsCabecalho(Boolean isCabecalho) {
		this.isCabecalho = isCabecalho;
	}

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

	public String getPathArquivo() {
		return pathArquivo;
	}

	public void setPathArquivo(String pathArquivo) {
		this.pathArquivo = pathArquivo;
	}
}
