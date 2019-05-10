<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Atividade"%>
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
        Atividade atividade = (Atividade) request.getAttribute("resultado");
    %>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2>Atividade - ID: <%= atividade.getId() %></h2>
			<p>Dados de atividade.</p>
		</div>

	</div>
	<div class="container">
		<div class="row">
	    <div class="form-group">
		     <a href="/gct/atividade?acao=editar&id=<%= atividade.getId() %>"><button type="button" class="btn btn-primary">Editar</button></a>
	     </div>
	                <div class=" ">
		     <a href="/gct/atividade?acao=excluir&id=<%= atividade.getId() %>"><button type="button" class="btn btn-warning">Excluir</button></a>
	     </div>
	     
	     <div class=" ">
              <a href="/gct/atividade"><button type="button" class="btn btn-secundary">Voltar</button></a>
           </div>
	    </div>
			<hr>
           <div class="row">
           		<h5>Chamado</h5>
           	</div>
           	<div class="row">
           		<div class="form-group col-md-fluid">
                    <span>ID: </span><p><%= atividade.getChamado().getId() %></p>
                </div>     
                <div class="form-group col-md-fluid">
                    <span>Título: </span> <p><%= atividade.getChamado().getTitulo() %></p>
                </div>
	        </div>
			<hr>
	        <div class="row">
	        	<h5>UsuarioAtribuido</h5>
        	</div>
        	<div class="row">
	        	<div class="col-md-fluid">
	                <p><span>Nome: </span><%= atividade.getUsuarioAtribuido().getContato().getNome() %></p>
                </div>
                <div class="col-md-fluid">
	                <p><span>SLA: </span><%= atividade.getUsuarioAtribuido().getId() %></p>
                </div>
            </div>
	        <hr>
			<div class="row">
				<h5>Informações</h5>
			</div>
			<div class="row">
				<div class="form-group col-md-fluid">
	                <p><span>Título: </span> <%= atividade.getTitulo() %></p>
                </div>
            </div>
			<div class="row">
				<div class="form-group col-md">
	                <span>Descrição: </span>
	                <p> <%= atividade.getDescricao() %></p>
                </div>
	        </div>
	        <hr>
	        <div class="row">
                <div class="form-group col-md-4">
                    <span>Data inclusão</span> 
                    <p><%= atividade.getDataInclusao() %></p>
                </div>
                
                <div class="form-group col-md-4">
                    <span>Data alteração</span> 
                    <p><%= atividade.getDataAlteracao() %></p>
                </div>
            </div>
            
			<div class="row">
				    <div class="form-group">
					     <a href="/gct/atividade?acao=editar&id=<%= atividade.getId() %>"><button type="button" class="btn btn-primary">Editar</button></a>
				     </div>
                    <div class="form-group ">
					     <a href="/gct/atividade?acao=excluir&id=<%= atividade.getId() %>"><button type="button" class="btn btn-warning">Excluir</button></a>
				     </div>
				     
				     <div class="form-group ">
                         <a href="/gct/atividade"><button type="button" class="btn btn-secundary">Voltar</button></a>
                     </div>
		     </div>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>