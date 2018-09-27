package br.com.fiap.speventos.beans;

public class PessoaFisica extends Pessoa implements Comparable<Usuario>{

	private long cpf;
	private int cpfDigito;
	private char genero;
	private int rg;
	private char rgDigito;
	private String dataNascimento;

	public int compareTo(PessoaFisica outro) {
		return this.dataNascimento.compareTo(outro.dataNascimento);
	}
	
	public PessoaFisica() {
		super();
	}

	public PessoaFisica(int codigoUsuario, String email, String senha, String nome, long telefone, String endereco,
			long cpf, int cpfDigito, char genero, int rg, char rgDigito, String dataNascimento) {
		super(codigoUsuario, email, senha, nome, telefone, endereco);
		this.cpf = cpf;
		this.cpfDigito = cpfDigito;
		this.genero = genero;
		this.rg = rg;
		this.rgDigito = rgDigito;
		this.dataNascimento = dataNascimento;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public int getCpfDigito() {
		return cpfDigito;
	}

	public void setCpfDigito(int cpfDigito) {
		this.cpfDigito = cpfDigito;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public int getRg() {
		return rg;
	}

	public void setRg(int rg) {
		this.rg = rg;
	}

	public char getRgDigito() {
		return rgDigito;
	}

	public void setRgDigito(char rgDigito) {
		this.rgDigito = rgDigito;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}