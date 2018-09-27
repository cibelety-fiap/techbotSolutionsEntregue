package br.com.fiap.speventos.beans;

public class Colaborador extends Usuario implements Comparable<Usuario> {

	private String nivelAcesso;
	private String departamento;

	public int compareTo(Colaborador outro) {
		return this.departamento.compareTo(outro.departamento);
	}
	
	public Colaborador() {
		super();
	}

	public Colaborador(int codigoUsuario, String email, String senha, String nome, String nivelAcesso,
			String departamento) {
		super(codigoUsuario, email, senha, nome);
		this.nivelAcesso = nivelAcesso;
		this.departamento = departamento;
	}

	public String getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(String nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
}