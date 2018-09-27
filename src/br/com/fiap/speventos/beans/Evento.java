package br.com.fiap.speventos.beans;

public class Evento implements Comparable<Evento> {

	private int codigoEvento;
	private String linkImagem;
	private String nomeEvento;
	private String tipoEvento;
	private String subtipoEvento;
	private String descricaoEvento;
	private String contatoMaisInfo;

	public int compareTo(Evento outro) {
		return this.tipoEvento.compareTo(outro.tipoEvento);
	}
	
	public Evento() {

	}

	public Evento(int codigoEvento, String linkImagem, String nomeEvento, String tipoEvento, String subtipoEvento,
			String descricaoEvento, String contatoMaisInfo) {
		super();
		this.codigoEvento = codigoEvento;
		this.linkImagem = linkImagem;
		this.nomeEvento = nomeEvento;
		this.tipoEvento = tipoEvento;
		this.subtipoEvento = subtipoEvento;
		this.descricaoEvento = descricaoEvento;
		this.contatoMaisInfo = contatoMaisInfo;
	}

	public int getCodigoEvento() {
		return codigoEvento;
	}

	public void setCodigoEvento(int codigoEvento) {
		this.codigoEvento = codigoEvento;
	}

	public String getLinkImagem() {
		return linkImagem;
	}

	public void setLinkImagem(String linkImagem) {
		this.linkImagem = linkImagem;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public String getSubtipoEvento() {
		return subtipoEvento;
	}

	public void setSubtipoEvento(String subtipoEvento) {
		this.subtipoEvento = subtipoEvento;
	}

	public String getDescricaoEvento() {
		return descricaoEvento;
	}

	public void setDescricaoEvento(String descricaoEvento) {
		this.descricaoEvento = descricaoEvento;
	}

	public String getContatoMaisInfo() {
		return contatoMaisInfo;
	}

	public void setContatoMaisInfo(String contatoMaisInfo) {
		this.contatoMaisInfo = contatoMaisInfo;
	}

}