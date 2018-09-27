package br.com.fiap.speventos.teste;

import java.util.List;

import br.com.fiap.speventos.beans.RealizacaoEvento;
import br.com.fiap.speventos.bo.RealizacaoEventoBO;
import br.com.fiap.speventos.excecao.Excecao;
import br.com.fiap.speventos.view.Magica;

public class Funcionalidade2 {

	public static void main(String[] args) {

		try {

			String nomeEvento = Magica.texto("Digite o nome evento"); //buscas validas: "incriveis", "carros", "alfa"
			List<RealizacaoEvento> listaRealizEvento = RealizacaoEventoBO.consultaRealizEventoPorNomeEvento(nomeEvento);

			if (!listaRealizEvento.isEmpty()) {
				for (RealizacaoEvento realizEvento : listaRealizEvento) {
					String[] dataHoraInicio = realizEvento.getDataHoraInicio().split(" ");
					String[] dataHoraTermino = realizEvento.getDataHoraTermino().split(" ");

					System.out.println("Cod realizacao do evento: " + realizEvento.getCodigoRealizacaoEvento() + "\n"
							+ "Nome do evento: " + realizEvento.getEvento().getNomeEvento() + "\n" 
							+ "Link da imagem: " + realizEvento.getEvento().getLinkImagem() + "\n"  
							+ "Nome do local: " + realizEvento.getLocal().getNomeLocal() + "\n" 
							+ "Endereço do local: " + realizEvento.getLocal().getEnderecoLocal() + "\n" 
							+ "Data inicio: " + dataHoraInicio[0] + "\n" 
							+ "Hora inicio: " + dataHoraInicio[1] + "\n" 
							+ "Data termino: " + dataHoraTermino[0] + "\n" 
							+ "Hora termino: " + dataHoraTermino[1] + "\n");
					System.out.println("==============================================================");
				}
			} else {
				System.out.println("Realizacao de Evento nao encontrada");
			}

		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}
	}
}
