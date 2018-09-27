package br.com.fiap.speventos.beans;

public class Noticia implements Comparable<Noticia> {
	
	private int codigoNoticia;
	private String linkImagem;
	private String nomeNoticia;
	private String categoriaNoticia;
	private String dataHoraNoticia;
	private String noticia;
	
	public int compareTo(Noticia outra) {
		return this.nomeNoticia.compareTo(outra.nomeNoticia);
	}
	
	public Noticia() {
		super();
	}

	public Noticia(int codigoNoticia, String linkImagem, String nomeNoticia, String categoriaNoticia,
			String dataHoraNoticia, String noticia) {
		super();
		this.codigoNoticia = codigoNoticia;
		this.linkImagem = linkImagem;
		this.nomeNoticia = nomeNoticia;
		this.categoriaNoticia = categoriaNoticia;
		this.dataHoraNoticia = dataHoraNoticia;
		this.noticia = noticia;
	}

	public int getCodigoNoticia() {
		return codigoNoticia;
	}

	public void setCodigoNoticia(int codigoNoticia) {
		this.codigoNoticia = codigoNoticia;
	}

	public String getLinkImagem() {
		return linkImagem;
	}

	public void setLinkImagem(String linkImagem) {
		this.linkImagem = linkImagem;
	}

	public String getNomeNoticia() {
		return nomeNoticia;
	}

	public void setNomeNoticia(String nomeNoticia) {
		this.nomeNoticia = nomeNoticia;
	}

	public String getCategoriaNoticia() {
		return categoriaNoticia;
	}

	public void setCategoriaNoticia(String categoriaNoticia) {
		this.categoriaNoticia = categoriaNoticia;
	}

	public String getDataHoraNoticia() {
		return dataHoraNoticia;
	}

	public void setDataHoraNoticia(String dataHoraNoticia) {
		this.dataHoraNoticia = dataHoraNoticia;
	}

	public String getNoticia() {
		return noticia;
	}

	public void setNoticia(String noticia) {
		this.noticia = noticia;
	}
}