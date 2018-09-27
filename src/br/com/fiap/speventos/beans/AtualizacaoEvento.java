package br.com.fiap.speventos.beans;

public class AtualizacaoEvento implements Comparable<AtualizacaoEvento> {
	private int codigoAtualizacaoEvento;
	private Evento evento;
	private Usuario usuario;
	private String dataHoraAtualizacao;
	private String tipoAtualizacao;
	
	public int compareTo(AtualizacaoEvento outro) {
		return this.tipoAtualizacao.compareTo(outro.tipoAtualizacao);
	}

	public AtualizacaoEvento() {
		super();
	}

	public AtualizacaoEvento(int codigoAtualizacaoEvento, Evento evento, Usuario usuario, String dataHoraAtualizacao,
			String tipoAtualizacao) {
		super();
		setCodigoAtualizacaoEvento(codigoAtualizacaoEvento);
		setEvento(evento);
		setUsuario(usuario);
		setDataHoraAtualizacao(dataHoraAtualizacao);
		setTipoAtualizacao(tipoAtualizacao);
	}

	public int getCodigoAtualizacaoEvento() {
		return codigoAtualizacaoEvento;
	}

	public void setCodigoAtualizacaoEvento(int codigoAtualizacaoEvento) {
		this.codigoAtualizacaoEvento = codigoAtualizacaoEvento;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}

	public void setDataHoraAtualizacao(String dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}

	public String getTipoAtualizacao() {
		return tipoAtualizacao;
	}

	public void setTipoAtualizacao(String tipoAtualizacao) {
		this.tipoAtualizacao = tipoAtualizacao;
	}

}