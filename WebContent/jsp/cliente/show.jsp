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
			<h2>Novo Cliente</h2>
			<p>Inserir dados do cliente.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->

		<form action="../../jsp/cliente/list.jsp">
			<div class="row">
                <div class="form-group col-md-3">
                    <label for="contato"><strong>Contato</strong></label>
                    <select class="form-control" id="contato">
                        <option>Contato 1</option>
                        <option>Contato 2</option>
                        <option>Contato 3</option>
                        <option>Contato 4</option>
                        <option>Contato 5</option>
                    </select>
                </div>
            </div>
	        <hr>
	        <h5><strong>Produtos contratados</strong></h5>
	        <div class="row">
                <div class="form-group col-md-3">
                    <label for="produto">Produto</label>
                    <select class="form-control" id="produto">
                        <option>Produto 1</option>
                        <option>Produto 2</option>
                        <option>Produto 3</option>
                        <option>Produto 4</option>
                        <option>Produto 5</option>
                    </select>
                </div>
                
                <div class="form-group col-md-2">
                    <label for="contratoProdutoInicio">Início contrato</label> 
                    <input type="text" class="form-control" id="contratoProdutoInicio" placeholder="22/02/2019">
                </div>
                
                <div class="form-group col-md-2">
                    <label for="duracaoContrato">Duracao</label> 
                    <input type="text" class="form-control" id="duracaoContrato" placeholder="XX (meses)">
                </div>
				<div class="form-check">
					<label class="form-check-label" for="stivo">Ativo</label>
					<input type="checkbox" class="form-check-input form-control" id="ativo" checked="checked">
				</div>
	        </div>
	        
	        <div class="row">
                <div class="form-group col-md-3">
                    <label for="produto">Produto</label>
                    <select class="form-control" id="produto">
                        <option>Produto 1</option>
                        <option>Produto 2</option>
                        <option>Produto 3</option>
                        <option>Produto 4</option>
                        <option>Produto 5</option>
                    </select>
                </div>
                
                <div class="form-group col-md-2">
                    <label for="contratoProdutoInicio">Início contrato</label> 
                    <input type="text" class="form-control" id="contratoProdutoInicio" placeholder="22/02/2019">
                </div>
                
                <div class="form-group col-md-2">
                    <label for="duracaoContrato">Duracao</label> 
                    <input type="text" class="form-control" id="duracaoContrato" placeholder="XX (meses)">
                </div>
                <div class="form-check">
                    <label class="form-check-label" for="stivo">Ativo</label>
                    <input type="checkbox" class="form-check-input form-control" id="ativo" checked="checked">
                </div>
            </div>
            
            <div class="row">
                <div class="form-group col-md-3">
                    <label for="produto">Produto</label>
                    <select class="form-control" id="produto">
                        <option>Produto 1</option>
                        <option>Produto 2</option>
                        <option>Produto 3</option>
                        <option>Produto 4</option>
                        <option>Produto 5</option>
                    </select>
                </div>
                
                <div class="form-group col-md-2">
                    <label for="contratoProdutoInicio">Início contrato</label> 
                    <input type="text" class="form-control" id="contratoProdutoInicio" placeholder="22/02/2019">
                </div>
                
                <div class="form-group col-md-2">
                    <label for="duracaoContrato">Duracao</label> 
                    <input type="text" class="form-control" id="duracaoContrato" placeholder="XX (meses)">
                </div>
                <div class="form-check">
                    <label class="form-check-label" for="stivo">Ativo</label>
                    <input type="checkbox" class="form-check-input form-control" id="ativo" checked="checked">
                </div>
                
            </div>
            <div class="form-group col-md-3">
                <div class="form-group col-md-3">
                    <a href="#"><button type="submit" class="btn btn-primary">+ Produto</button></a>
                </div>
            </div>
                <hr>
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