package br.com.fiap.speventos.teste;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.RespostaChatbot;
import br.com.fiap.speventos.dao.RespostaChatbotDAO;
import br.com.fiap.speventos.excecao.Excecao;

public class testeChatbot {

	public static void main(String[] args) {
		try {

			List<RespostaChatbot> listaResposta = new ArrayList<RespostaChatbot>();

			RespostaChatbotDAO dao = new RespostaChatbotDAO();
			listaResposta = dao.consultar("CINEMA", "ANIMACAO", "2018-09-20 18:00");
			
			for (RespostaChatbot respostaTemp : listaResposta) {
				String linkImagem = respostaTemp.getLinkImagem();
				String nomeEvento = respostaTemp.getNomeEvento();
				String resposta = "[\"<img src=\'" + linkImagem +"\' /><br />"
						+ "<a href=\'" + nomeEvento + "\'>" + nomeEvento + "</a>";

				List<String> listaHorariosLocalPorFilme = respostaTemp.getHorariosLocalPorFilme();
				for(String horariosPorLocalFilmeTemp : listaHorariosLocalPorFilme) {
					resposta = resposta + horariosPorLocalFilmeTemp + "<br />";
				}
				resposta = resposta + "\"]";
				
				System.out.println(resposta);

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}
	}
}
