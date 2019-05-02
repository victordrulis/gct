<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Cliente"%>
<%@page import="br.com.drulis.gct.dominio.Contato"%>
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
       Cliente cliente = (Cliente) request.getAttribute("resultado");
    %>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2>Alterar Cliente - ID: <%= cliente.getId() %></h2>
			<p>Alterar ou atualizar dados de cliente.</p>
		</div>

	</div>
	<div class="container">
		<form action="/gct/contato?acao=alterar" method="post">
		      <input type="hidden" name="id" id="id" value="<%= cliente.getId() %>">
			<div class="row">
	            <div class="form-group col-md-5">
	                <label for="nome">Nome</label>
	                <input type="text" class="form-control" id="nome" name="nome" value="<%= cliente.getContato().getNome() %>" readonly>
	            </div>
	            
	            <div class="form-group col-md-4">
	                <label for="cpfCnpj">CPF/CNPJ</label>
	                <input type="text" class="form-control" id="cpfCnpj" name="cpfCnpj" value="<%= cliente.getContato().getCpfCnpj() %>" readonly>
	            </div>
	            
	            
	        </div>
	        
	        <div class="row">
	        </div>
				<div class="form-check">
					<input type="checkbox" class="form-check-input" id="ativo" name="ativo" <%= cliente.getAtivo() == 1 ? "checked" : null%>>
					<label class="form-check-label" for="ativo">Ativo</label>
				</div>
				<div class="row">
				     <button type="submit" class="btn btn-primary col-md-1">Salvar</button>
				</div>
			
		</form>
	     <a href="/gct/cliente">
	       <button type="button" class="btn btn-secundary col-md-1">Cancelar</button>
         </a>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>