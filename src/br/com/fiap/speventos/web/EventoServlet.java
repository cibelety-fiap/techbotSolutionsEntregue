package br.com.fiap.speventos.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.speventos.beans.Evento;
import br.com.fiap.speventos.bo.EventoBO;
import br.com.fiap.speventos.excecao.Excecao;

@WebServlet("/EventoServlet")
public class EventoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String comando = request.getParameter("comando");
			
			if (comando == null) {
				comando = "listar";
			}
			
			switch (comando) {
			
			case "listar":
				listarEvento(request, response);
				break;
				
			case "adicionar":
				adicionarEvento(request, response);
				break;
				
			case "carregar":
				carregarEvento(request, response);
				break;
				
			case "editar":
				editarEvento(request, response);
				break;
			
			case "remover":
				removerEvento(request, response);
				break;
				
			default:
				listarEvento(request, response);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}
	}

	private void listarEvento(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
			HttpSession session = request.getSession(true);
			int codigoUsuario = (int) session.getAttribute("codigoUsuario");
			
			List<Evento> listaEventos = EventoBO.consultaPorUsuario(codigoUsuario); //ALTERAR
		
			request.setAttribute("LISTA_EVENTO", listaEventos);
					
			RequestDispatcher dispatcher = request.getRequestDispatcher("/formularios/lista_evento.jsp");
			dispatcher.forward(request, response);
		}
	
	private void adicionarEvento(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int codEvento = Integer.parseInt(request.getParameter("codEvento"));
		String linkImagem = request.getParameter("linkImagem");
		String nomeEvento = request.getParameter("nomeEvento");
		String tipoEvento = request.getParameter("tipoEvento");
		String subtipoEvento = request.getParameter("subtipoEvento");
		String descricaoEvento = request.getParameter("descricaoEvento");
		String contatoMaisInfo = request.getParameter("contatoMaisInfo");
		
		Evento evento = new Evento(codEvento, linkImagem, nomeEvento, tipoEvento, subtipoEvento, 
				descricaoEvento, contatoMaisInfo);
		
		EventoBO.novoEvento(evento);
				
		listarEvento(request, response);
	}
	
	private void carregarEvento(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			int codigoEvento = Integer.parseInt(request.getParameter("codigoEvento"));
			
			Evento evento = EventoBO.consultaEvento(codigoEvento);
			
			request.setAttribute("EVENTO", evento);
			
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("RealizacaoEventoServlet?comando=listar&codEvento=" + codigoEvento);
			dispatcher.forward(request, response);		
		}

	private void editarEvento(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		int codEvento = Integer.parseInt(request.getParameter("codEvento"));
		String linkImagem = request.getParameter("linkImagem");
		String nomeEvento = request.getParameter("nomeEvento");
		String tipoEvento = request.getParameter("tipoEvento");
		String subtipoEvento = request.getParameter("subtipoEvento");
		String descricaoEvento = request.getParameter("descricaoEvento");
		String contatoMaisInfo = request.getParameter("contatoMaisInfo");
		
		Evento evento = new Evento(codEvento, linkImagem, nomeEvento, tipoEvento, subtipoEvento, 
				descricaoEvento, contatoMaisInfo);
		
		EventoBO.edicaoEvento(evento);
				
		listarEvento(request, response);
		
	}

	private void removerEvento(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			int codEvento = Integer.parseInt(request.getParameter("codEvento"));
			
			EventoBO.remocaoEvento(codEvento);
			
			listarEvento(request, response);
		}
}
