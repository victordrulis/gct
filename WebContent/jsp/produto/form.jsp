<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.classificacao.ProdutoStatus"%>
<%@page import="br.com.drulis.gct.dominio.classificacao.ProdutoTipo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
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
			<h2>Novo Produto</h2>
			<p>Inserir dados de produto.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->

		<form action="/gct/produto?acao=salvar" method="post">
			<div class="row">
	            <div class="form-group col-md-5">
	                <label for="titulo">Título</label>
	                <input type="text" class="form-control" id="titulo" name="titulo" placeholder="Título">
	            </div>
	        </div>
	        
            <div class="row">
            	<div class="form-group col-md-3">
	                <label for="versao">Versão</label>
	                <input type="text" class="form-control" id="versao" name="versao" placeholder="0.0.0.0">
	            </div>
	            <div class="form-group col-md-3">
	                <label for="codigo">Código</label>
	                <input type="text" class="form-control" id="codigo" name="codigo" placeholder="Código">
	            </div>
            </div>
	            
	        <div class="row">
                <div class="form-group col-md-6">
	                <label for="descricao">Descrição</label> 
	                <textarea class="form-control" id="descricao" name="descricao" placeholder="Descrição"> </textarea>
                </div>
                
	        </div>
	       <div class="row">
                <div class="form-group col-md-2">
                    <label for="tipo"><strong>Tipo</strong></label>
                    <select class="form-control" name="tipo" id="tipo">
                    		<option value="0">Selecione...</option>
                    	<%
                    		for(Map.Entry<Integer, ProdutoTipo> tipo : ProdutoTipo.getMapa().entrySet()) {
                   		%>
                   				<option value="<%= tipo.getKey()%>"><%= tipo.getValue().getDescricao() %></option>
                    	<%
                    		}
                    	%>
                    </select>
                </div>
                
                <div class="form-group col-md-2">
                    <label for="status"><strong>Status</strong></label>
                    <select class="form-control" name="status" id="status">
                    		<option value="0">Selecione...</option>
                    	<%
                    		for(Map.Entry<Integer, ProdutoStatus> status : ProdutoStatus.getMapa().entrySet()) {
                   		%>
                   				<option value="<%= status.getKey()%>"><%= status.getValue().getDescricao() %></option>
                    	<%
                    		}
                    	%>
                    </select>
                </div>
            </div>
	       <div class="row">
				<div class="form-group col-md-3">
					<input type="checkbox" class="form-check-input" id="ativo" name="ativo" checked="checked">
					<label class="form-check-label" for="ativo">Ativo</label>
				</div>
				
			</div>
			<div class="row">
			     <button type="submit" class="btn btn-primary col-md-2" id="acao" name="acao" value="salvar">Salvar</button>
			     <button type="reset" class="btn btn-secundary col-md-2">Cancelar</button>
			</div>
			
		</form>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>