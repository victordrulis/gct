<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Chamado"%>
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
        Chamado chamado = (Chamado) request.getAttribute("resultado");
    %>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2>Chamado - ID: <%= chamado.getId() %></h2>
			<p>Dados de chamado.</p>
		</div>

	</div>
	<div class="container">
		<div class="row">
	    <div class="form-group">
		     <a href="/gct/chamado?acao=editar&id=<%= chamado.getId() %>"><button type="button" class="btn btn-primary">Editar</button></a>
	     </div>
	                <div class=" ">
		     <a href="/gct/chamado?acao=excluir&id=<%= chamado.getId() %>"><button type="button" class="btn btn-warning">Excluir</button></a>
	     </div>
	     
	     <div class=" ">
              <a href="/gct/chamado"><button type="button" class="btn btn-secundary">Voltar</button></a>
           </div>
	    </div>
			<hr>
           <div class="row">
           		<h5>Produto</h5>
           	</div>
           	<div class="row">
           		<div class="form-group col-md-fluid">
                    <span>ID: </span><p><%= chamado.getProduto().getId() %></p>
                </div>     
                <div class="form-group col-md-fluid">
                    <span>Título: </span> <p><%= chamado.getProduto().getTitulo() %></p>
                </div>
	        </div>
			<hr>
	        <div class="row">
	        	<h5>Cliente</h5>
        	</div>
        	<div class="row">
	        	<div class="col-md-fluid">
	                <p><span>Nome: </span><%= chamado.getCliente().getContato().getNome() %></p>
                </div>
                <div class="col-md-fluid">
	                <p><span>SLA: </span><%= chamado.getCliente().getSla() %></p>
                </div>
            </div>
	        <hr>
			<div class="row">
				<h5>Informações</h5>
			</div>
			<div class="row">
				<div class="form-group col-md-fluid">
	                <p><span>Título: </span> <%= chamado.getTitulo() %></p>
                </div>
            </div>
			<div class="row">
				<div class="form-group col-md">
	                <span>Descrição: </span>
	                <p> <%= chamado.getDescricao() %></p>
                </div>
	        </div>
	        <hr>
	        <div class="row">
                <div class="form-group col-md-4">
                    <span>Data inclusão</span> 
                    <p><%= chamado.getDataInclusao() %></p>
                </div>
                
                <div class="form-group col-md-4">
                    <span>Data alteração</span> 
                    <p><%= chamado.getDataAlteracao() %></p>
                </div>
            </div>
            
			<div class="row">
				    <div class="form-group">
					     <a href="/gct/chamado?acao=editar&id=<%= chamado.getId() %>"><button type="button" class="btn btn-primary">Editar</button></a>
				     </div>
                    <div class="form-group ">
					     <a href="/gct/chamado?acao=excluir&id=<%= chamado.getId() %>"><button type="button" class="btn btn-warning">Excluir</button></a>
				     </div>
				     
				     <div class="form-group ">
                         <a href="/gct/chamado"><button type="button" class="btn btn-secundary">Voltar</button></a>
                     </div>
		     </div>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>