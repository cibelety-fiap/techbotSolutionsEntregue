package br.com.fiap.speventos.beans;

public class PessoaJuridica extends Pessoa implements Comparable<Usuario> {

	private String razaoSocial;
	private long cnpj;
	private int cnpjDigito;

	public int compareTo(PessoaJuridica outro) {
		return this.razaoSocial.compareTo(outro.razaoSocial);
	}
	
	public PessoaJuridica() {
		super();
	}

	public PessoaJuridica(int codigoUsuario, String email, String senha, String nome, long telefone, String endereco,
			String razaoSocial, long cnpj, int cnpjDigito) {
		super(codigoUsuario, email, senha, nome, telefone, endereco);
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.cnpjDigito = cnpjDigito;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public long getCnpj() {
		return cnpj;
	}

	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}

	public int getCnpjDigito() {
		return cnpjDigito;
	}

	public void setCnpjDigito(int cnpjDigito) {
		this.cnpjDigito = cnpjDigito;
	}
}