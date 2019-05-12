<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Contrato"%>
<%@page import="br.com.drulis.gct.dominio.classificacao.ContratoStatus"%>
<%@page import="br.com.drulis.gct.dominio.Cliente"%>
<%@page import="br.com.drulis.gct.dominio.Produto"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../fragmentos/header.jsp"%>
<style type="text/css">
.conteudo-topo {
	padding-top: 40px;
}
</style>

</head>
<body>
	<%@include file="../fragmentos/nav.jsp"%>
    <%
       Contrato contrato = (Contrato) request.getAttribute("resultado");
    %>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2>Alterar Contrato - ID: <%= contrato.getId() %></h2>
			<p>Alterar ou atualizar dados do contrato de cliente.</p>
		</div>

	</div>
	
	<!-- TODO Nesta tela deve-se exibir apenas os dados de um contrato: Cliente, Produto e campos de edição -->
	
	
	<div class="container">
		<form action="/gct/contrato?acao=alterar" method="post">
		      <input type="hidden" name="id" id="id" value="<%= contrato.getId() %>">
			<div class="row">
	            <div class="form-group col-md-5">
	                <p><span>Nome do cliente: </span><%= contrato.getCliente().getContato().getNome() %></p>
	            </div>
	            
	        </div>
	        
	        
				<div class="form-check">
					<input type="checkbox" class="form-check-input" id="ativo" name="ativo" <%= contrato.getAtivo() == 1 ? "checked" : null%>>
					<label class="form-check-label" for="ativo">Ativo</label>
				</div>
				<div class="row">
				     <button type="submit" class="btn btn-primary col-md-2">Salvar</button>
				</div>
			
		</form>
	     <a class="col-md-2" href="/gct/contrato">
	       <button type="button" class="btn btn-secundary col-md-2">Cancelar</button>
         </a>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>