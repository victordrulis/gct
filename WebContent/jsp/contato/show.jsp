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
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2>Contato</h2>
			<p>Dados de contato.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->

        <%
        Contato contato = (Contato) request.getAttribute("resultado");
        
        %>
		<form>
			<div class="row">
	            <div class="form-group col-md-5">
	                <label for="nome">Nome</label>
	                <input type="text" class="form-control" id="nome" value="<%= contato.getNome() %>">
	            </div>
	            
	            <div class="form-group col-md-4">
	                <label for="cpfCnpj">CPF/CNPJ</label>
	                <input type="text" class="form-control" name="cpfCnpj" id="cpfCnpj" value="<%= contato.getCpfCnpj() %>">
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
					<input type="checkbox" class="form-check-input" id="ativo" name="ativo" checked="checked">
					<label class="form-check-label" for="ativo">Ativo</label>
				</div>
				<div class="row">
				    <div class="form-group col-md-4">
					     <a href="/gct/contato?acao=alterar&id<%= contato.getId() %>"><button type="submit" class="btn btn-primary">Editar</button></a>
				     </div>
				</div>
			
		</form>
		      <div class="row">
                    <div class="form-group col-md-4">
					     <a href="/gct/contato"><button type="submit" class="btn btn-secundary">Voltar</button></a>
				     </div>
		     </div>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>