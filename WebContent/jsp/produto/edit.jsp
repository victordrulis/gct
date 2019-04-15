<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
       System.out.println(this.getClass().getSimpleName() + "-- Formulário de edição");
       Contato contato = (Contato) request.getAttribute("resultado");
    %>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2>Alterar Contato - ID: <%= contato.getId() %></h2>
			<p>Alterar ou atualizar dados de contato.</p>
		</div>

	</div>
	<div class="container">
		<form action="/gct/contato?acao=alterar" method="post">
		      <input type="hidden" name="id" id="id" value="<%= contato.getId() %>">
			<div class="row">
	            <div class="form-group col-md-5">
	                <label for="nome">Nome</label>
	                <input type="text" class="form-control" id="nome" name="nome" value="<%= contato.getNome() %>" readonly>
	            </div>
	            
	            <div class="form-group col-md-4">
	                <label for="cpfCnpj">CPF/CNPJ</label>
	                <input type="text" class="form-control" id="cpfCnpj" name="cpfCnpj" value="<%= contato.getCpfCnpj() %>" readonly>
	            </div>
	            
	            
	        </div>
	        
	        <div class="row">
                <div class="form-group col-md-4">
	                <label for="email">Email</label> 
	                <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" value="<%= contato.getEmail() %>">
	                <small id="emailHelp" class="form-text text-muted">Receberá link de confirmação</small>
                </div>
                
                <div class="form-group col-md-4">
                    <label for="telefone">Telefone</label> 
                    <input type="text" class="form-control" id="telefone" name="telefone" value="<%= contato.getTel() %>">
                </div>
	        </div>
				<div class="form-check">
					<input type="checkbox" class="form-check-input" id="ativo" name="ativo" <%= contato.getAtivo() == 1 ? "checked" : null%>>
					<label class="form-check-label" for="ativo">Ativo</label>
				</div>
				<div class="row">
				     <button type="submit" class="btn btn-primary col-md-1">Salvar</button>
				     <button type="submit" class="btn btn-secundary col-md-1">Cancelar</button>
				</div>
			
		</form>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>