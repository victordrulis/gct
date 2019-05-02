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
	                <span>Cliente</span> 
	                <p><%= chamado.getCliente().getContato().getNome() %></p>
            </div>

			<div class="row">
	                <p><span>Título: </span> <%= chamado.getTitulo() %></p>
            </div>	            
			<div class="row">
	                <span>Descrição: </span>
	                <p> <%= chamado.getDescricao() %></p>
	        </div>
	        
                
                <div class="form-group col-md-4">
                    <span for="telefone">Telefone</span> 
                    <p type="text" class="form-control" id="telefone" name="telefone" value="<%= chamado.getDataAbertura() %>">
                </div>
	        </div>
	        
	        <div class="row">
                <div class="form-group col-md-4">
                    <span for="dataInclusao">Data inclusão</span> 
                    <p type="date" class="form-control" id="dataInclusao" name="dataInclusao" value="<%= chamado.getDataInclusao() %>">
                </div>
                
                <div class="form-group col-md-4">
                    <span for="dataAlteracao">Data alteração</span> 
                    <p type="date" class="form-control" id="dataAlteracao" name="dataAlteracao" value="<%= chamado.getDataAlteracao() %>">
                </div>
            </div>
            
            <div class="row">
				<div class="form-check">
					<p type="checkbox" class="form-check-p" id="ativo" name="ativo" <%= chamado.getAtivo() == 1 ? "checked" : null %>>
					<span>Ativo</span>
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