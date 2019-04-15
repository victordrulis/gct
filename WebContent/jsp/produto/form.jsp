<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.ProdutoStatus"%>
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
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2>Novo Produto</h2>
			<p>Inserir dados de produto.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->

		<form action="/gct/produto" method="post">
			<div class="row">
	            <div class="form-group col-md-5">
	                <label for="titulo">Título</label>
	                <input type="text" class="form-control" id="titulo" name="titulo" placeholder="Título">
	            </div>
	        </div>
	        
            <div class="row">
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
                <div class="form-group col-md-5">
                    <label for="contato"><strong>Contato</strong></label>
                    <select class="form-control" name="contatoId" id="contatoId">
                          <option value="1">Status 1</option>
                          <option value="2">Status 2</option>
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