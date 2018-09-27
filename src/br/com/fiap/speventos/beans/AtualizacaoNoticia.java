package br.com.fiap.speventos.beans;

public class AtualizacaoNoticia implements Comparable<AtualizacaoNoticia> {
	private int codigoAtualizacaoNoticia;
	private Noticia noticia;
	private Colaborador colaborador;
	private String dataHoraAtualizacao;
	private String tipoAtualizacao;

	public int compareTo(AtualizacaoNoticia outra) {
		return this.tipoAtualizacao.compareTo(outra.tipoAtualizacao);
	}

	public AtualizacaoNoticia() {
		super();
	}

	public AtualizacaoNoticia(int codigoAtualizacaoNoticia, Noticia noticia, Colaborador colaborador,
			String dataHoraAtualizacao, String tipoAtualizacao) {
		super();
		setCodigoAtualizacaoNoticia(codigoAtualizacaoNoticia);
		setColaborador(colaborador);
		setNoticia(noticia);
		setDataHoraAtualizacao(dataHoraAtualizacao);
		setTipoAtualizacao(tipoAtualizacao);
	}

	public int getCodigoAtualizacaoNoticia() {
		return codigoAtualizacaoNoticia;
	}

	public void setCodigoAtualizacaoNoticia(int codigoAtualizacaoNoticia) {
		this.codigoAtualizacaoNoticia = codigoAtualizacaoNoticia;
	}

	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
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