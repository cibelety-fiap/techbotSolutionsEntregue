<%@ include file="imports/cabeca.jsp"%>

<article>
				<form autocomplete="off">
					<h1>Contato com a SP Eventos</h1>
					<br>
					<label>Nome:</label>
					<br>
					<input type="text" name="nome" value="">
					<br><br>
					<label>Email:</label>
					<br>
					<input type="text" name="email" value="">
					<br><br>
					<label>Telefone:</label>
					<br>
					<input type="text" name="fone" value="">
					<br>
					<br>
					<label>Qual o motivo do contato?</label>
					<select>
						<option></option>
    		    <option>Informa&ccedil;&otilde;es</option>
    		    <option>Sugest&otilde;es</option>
    		    <option>Cr&iacute;ticas</option>
    		    <option>Outros</option>
    	   </select>
					<br><br>
					<textarea class="msg" cols="38" rows="8"></textarea>
					<br>
					<br>
					<input type="submit" name="enviar" value="Enviar" class="tamanho_botao">
					<input type="reset" name="reset" value="Reset" class="tamanho_botao">
					<br>
					<br>
				</form>
			</article>

        <%@ include file="imports/rodape.jsp"%>