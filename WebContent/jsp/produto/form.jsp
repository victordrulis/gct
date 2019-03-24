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
			<h2>Novo Produto</h2>
			<p>Inserir dados do produto.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->

		<form>
			<div class="row">
	            <div class="form-group col-md-5">
	                <label for="titulo">Título</label>
	                <input type="text" class="form-control" id="titulo" placeholder="Título do produto">
	            </div>
	            
	            
	            
	        </div>
	        
	        <div class="row">
	        <div class="form-group col-md-8">
	           <label for="mantenedor">Descrição</label>
	           <textarea class="form-control" rows="3" id="mantenedor">Descricao do produto</textarea>
	           </div>
	        </div>
	        <div class="row">
	            <div class="form-group col-md-4">
	                <label for="mantenedor">Mantenedor</label>
	                <select class="form-control" id="mantenedor">
                        <option>Contato 1</option>
                        <option>Contato 2</option>
                        <option>Contato 3</option>
                        <option>Contato 4</option>
                        <option>Contato 5</option>
                    </select>
	            </div>
                <div class="form-group col-md-4">
	                <label for="datainicio">Início</label> 
	                <input type="text" class="form-control" id="datainicio" placeholder="22/02/2015">
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