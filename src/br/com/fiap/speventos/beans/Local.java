package br.com.fiap.speventos.beans;

public class Local implements Comparable<Local> {
	
	private int codigoLocal;
	private String nomeLocal;
	private String enderecoLocal;

	public int compareTo(Local outro) {
		return this.nomeLocal.compareTo(outro.nomeLocal);
	}
	
	public Local() {
	}

	public Local(int codigoLocal, String nomeLocal, String enderecoLocal) {
		this.codigoLocal = codigoLocal;
		this.nomeLocal = nomeLocal;
		this.enderecoLocal = enderecoLocal;
	}

	public int getCodigoLocal() {
		return codigoLocal;
	}

	public void setCodigoLocal(int codigoLocal) {
		this.codigoLocal = codigoLocal;
	}

	public String getNomeLocal() {
		return nomeLocal;
	}

	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}

	public String getEnderecoLocal() {
		return enderecoLocal;
	}

	public void setEnderecoLocal(String enderecoLocal) {
		this.enderecoLocal = enderecoLocal;
	}

}
