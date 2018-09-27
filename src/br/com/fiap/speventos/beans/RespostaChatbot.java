package br.com.fiap.speventos.beans;

import java.util.ArrayList;

public class RespostaChatbot implements Comparable <RespostaChatbot> {

	private String nomeEvento;
	private String linkImagem;
	private ArrayList<String> horariosLocalPorFilme;

	public int compareTo(RespostaChatbot outro) {
		return this.nomeEvento.compareTo(outro.nomeEvento);
	}
	
	public RespostaChatbot() {
	}

	public RespostaChatbot(String nomeEvento, String linkImagem, ArrayList<String> horariosLocalPorFilme) {
		super();
		this.nomeEvento = nomeEvento;
		this.linkImagem = linkImagem;
		this.horariosLocalPorFilme = horariosLocalPorFilme;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public String getLinkImagem() {
		return linkImagem;
	}

	public void setLinkImagem(String linkImagem) {
		this.linkImagem = linkImagem;
	}

	public ArrayList<String> getHorariosLocalPorFilme() {
		return horariosLocalPorFilme;
	}

	public void setHorariosLocalPorFilme(ArrayList<String> horariosLocalPorFilme) {
		this.horariosLocalPorFilme = horariosLocalPorFilme;
	}

}