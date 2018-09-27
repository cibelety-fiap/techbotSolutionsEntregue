package br.com.fiap.speventos.beans;

public class RealizacaoEvento implements Comparable <RealizacaoEvento> {

	private int codigoRealizacaoEvento;
	private Evento evento;
	private Local local;
	private String dataHoraInicio;
	private String dataHoraTermino;

	public int compareTo(RealizacaoEvento outro) {
		return this.evento.compareTo(outro.evento);
	}
	
	public RealizacaoEvento() {
	}

	public RealizacaoEvento(int codigoRealizacaoEvento, Evento evento, Local local, String dataHoraInicio,
			String dataHoraTermino) {
		super();
		this.codigoRealizacaoEvento = codigoRealizacaoEvento;
		this.evento = evento;
		this.local = local;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraTermino = dataHoraTermino;
	}

	public int getCodigoRealizacaoEvento() {
		return codigoRealizacaoEvento;
	}

	public void setCodigoRealizacaoEvento(int codigoRealizacaoEvento) {
		this.codigoRealizacaoEvento = codigoRealizacaoEvento;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public String getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(String dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public String getDataHoraTermino() {
		return dataHoraTermino;
	}

	public void setDataHoraTermino(String dataHoraTermino) {
		this.dataHoraTermino = dataHoraTermino;
	}

}