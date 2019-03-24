<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
			<h2>Novo Contato</h2>
			<p>Inserir dados de contato.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->

		<form action="../../jsp/contato/list.jsp">
			<div class="row">
	            <div class="form-group col-md-5">
	                <label for="nome">Nome</label>
	                <input type="text" class="form-control" id="nome" placeholder="Nome">
	            </div>
	            
	            <div class="form-group col-md-4">
	                <label for="cpfCnpj">CPF/CNPJ</label>
	                <input type="text" class="form-control" id="cpfCnpj" placeholder="CPF/CNPJ">
	            </div>
	            
	            
	        </div>
	        
	        <div class="row">
                <div class="form-group col-md-4">
	                <label for="email">Email</label> 
	                <input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email">
	                <small id="emailHelp" class="form-text text-muted">Receberá link de confirmação</small>
                </div>
                
                <div class="form-group col-md-4">
                    <label for="telefone">Telefone</label> 
                    <input type="text" class="form-control" id="telefone" placeholder="(DDD) 99999-9999">
                </div>
	        </div>
				<div class="form-check">
					<input type="checkbox" class="form-check-input" id="ativo" checked="checked">
					<label class="form-check-label" for="stivo">Ativo</label>
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