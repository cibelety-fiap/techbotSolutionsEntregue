package br.com.fiap.speventos.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context; //Precisei importar
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import br.com.fiap.speventos.beans.RespostaChatbot;
import br.com.fiap.speventos.chat.Chat;
import br.com.fiap.speventos.dao.RespostaChatbotDAO;

@WebServlet(urlPatterns = "/chat")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 6093948662123015033L;

	private String dia = new String();
	private String horario = new String();
	private String nomeEntity = new String();
	private String nomeIntent = new String();
	private String tipo_filme = new String();
	private String respostaParaChatbot = new String();
	private Context contexto = null;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		ArrayList<String> listaRespostasFormatadas = new ArrayList<String>();
		String respFormatadas = new String();
		String message = req.getParameter("question");
		if (message.isEmpty()) {
			contexto = null;
			nomeIntent = "";
			tipo_filme = "";
			dia = "";
			horario = "";
		}
		MessageResponse response = this.conversationAPI(message, contexto);

		Chat chat = new Chat();
		chat.addUserMessage(message);
		for (String text : response.getOutput().getText()) {
			chat.addBotMessage(text);
		}
		contexto = response.getContext();
		
		resp.setContentType("application/json");
		JSONArray jsonArrayIntents = new JSONArray(response.getIntents());
		System.out.println(jsonArrayIntents);

		JSONArray jsonArrayEntities = new JSONArray(response.getEntities());
		System.out.println(jsonArrayEntities);

		if (!jsonArrayIntents.isEmpty()) {
			nomeIntent = jsonArrayIntents.getJSONObject(0).getString("intent").toUpperCase();
		}
		
		if (!jsonArrayEntities.isEmpty()) {
			nomeEntity = jsonArrayEntities.getJSONObject(0).getString("entity");
			if (nomeEntity.equals("tipo-filme")) {
				tipo_filme = jsonArrayEntities.getJSONObject(0).getString("value").toUpperCase();
			} 
			if (nomeEntity.equals("sys-date")) {
				dia = jsonArrayEntities.getJSONObject(0).getString("value");
				System.out.println(dia);
			} 
			if (nomeEntity.equals("sys-time")) {
				horario = jsonArrayEntities.getJSONObject(0).getString("value");
				System.out.println(horario);
			}
		}

		if (nomeIntent.equals("CINEMA") && !tipo_filme.isEmpty() && !dia.isEmpty() && !horario.isEmpty()) {
			
			try {
				respFormatadas = this.buscarRespostas(nomeIntent, tipo_filme, dia + " " + horario);
				respostaParaChatbot = respFormatadas;
//				Exemplo de String com imagem e link para ser enviada para chatbot
//				respostaParaChatbot = "[\"<img src='img/os_incriveis2.png' /><br /><a href='OS INCRIVEIS 2'>OS INCRIVEIS 2</a> <br />18:20 SHOP. CENTER3<br />18:00 20:00 SHOP. CIDADE SAO PAULO<br />\"]";
				System.out.println(respostaParaChatbot);
				contexto = null;
				nomeIntent = "";
				tipo_filme = "";
				dia = "";
				horario = "";
				
			} catch (Exception e) {
				e.printStackTrace();

			}
		} else {
			respostaParaChatbot = new Gson().toJson(response.getOutput().getText());
		}
		resp.setContentType("text/html");
		resp.getWriter().write(respostaParaChatbot);
	}
	
	private String buscarRespostas(String nomeIntent, String tipo_filme, String dataHora) throws Exception {
//		ArrayList<String> listaRespostasFormatadas = new ArrayList<String>();
		String respostasFormatadas = new String();
		RespostaChatbotDAO dao = new RespostaChatbotDAO();
//		Imprime na console infos sobre os dados enviados para consulta
//		System.out.println(nomeIntent + " " + tipo_filme + " " + dia + " " + horario);
		List<RespostaChatbot> listaResposta = dao.consultar(nomeIntent, tipo_filme, dia + " " + horario);
			
		for (RespostaChatbot respostaTemp : listaResposta) {
			if (respostaTemp.getNomeEvento().isEmpty()) {
				return "[\"Desculpe, não encontramos um filme parecido com o que você deseja.\"]";
			}
			String linkImagem = respostaTemp.getLinkImagem();
			String nomeEvento = respostaTemp.getNomeEvento();
			String resposta = "<img src=\'" + linkImagem +"\' /><br /><br />"
					+ "<a href=\'filme/" + nomeEvento.toLowerCase().replaceAll("\\s+","") + ".jsp\' target=\'_blank\' class=\'titulo_evento\'><b>" + nomeEvento + "</b></a><br /><br />";

			List<String> listaHorariosLocalPorFilme = respostaTemp.getHorariosLocalPorFilme();
			for(String horariosPorLocalFilmeTemp : listaHorariosLocalPorFilme) {
				resposta = resposta + horariosPorLocalFilmeTemp + "<br />";
			}
			resposta = resposta + "<br />";
//			Imprime a resposta montada na console	
//		  	System.out.println(resposta);
			respostasFormatadas = respostasFormatadas + resposta;
		}
		respostasFormatadas = "[\"" +respostasFormatadas + "\"]";
		dao.fechar();
		return respostasFormatadas;
	}

	private MessageResponse conversationAPI(String input, Context context) {

		Conversation service = new Conversation("2018-09-10");
		service.setUsernameAndPassword("10208ce1-5e86-4c70-80ba-ab7e6ce81ab8", "oH2zLDGISFUJ");
		String workspaceId = "b4247604-967d-424b-a1ab-a224b831dddb";

		MessageOptions newMessageOptions = new MessageOptions.Builder().workspaceId(workspaceId)
				.input(new InputData.Builder(input).build()).context(context).build();
//		Imprime o que esta sendo enviado para o chatbot tratar e responder
//		System.out.println(newMessageOptions);
		
		MessageResponse response = service.message(newMessageOptions).execute();

		return response;
	}
}
