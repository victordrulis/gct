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
			<h2>Novo chamado</h2>
			<p>Inserir dados do chamado.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->

		<form>
		  <div class="row">
                <div class="form-group col-md-6">
                    <label for="cliente">Cliente</label>
                    <select class="form-control" id="cliente">
                        <option>Cliente 1</option>
                        <option>Cliente 2</option>
                        <option>Cliente 3</option>
                        <option>Cliente 4</option>
                        <option>Cliente 5</option>
                    </select>
                </div>
            </div>
			<div class="row">
	            <div class="form-group col-md-8">
	                <label for="titulo">Título</label>
	                <input type="text" class="form-control" id="titulo" placeholder="Titulo">
	            </div>
	        </div>
	        
	        <div class="row">
	           <div class="form-group col-md-3">
                    <label for="tipo">Tipo</label>
                    <select class="form-control" id="tipo">
                        <option>Manutenção</option>
                        <option>Melhoria</option>
                        <option>Alteração</option>
                        <option>Configuração</option>
                        <option>Adequação</option>
                    </select>
                </div>
	        </div>
	        
	        <div class="row">
                <div class="form-group col-md-8">
	                <label for="descricao">Descricao</label> 
	                <textarea class="form-control" id="descricao" rows="3"></textarea>
                </div>
                
	        </div>
	        
	        <div class="row">
               <div class="form-group col-md-4">
                    <label for="contato">Atribuido</label>
                    <select class="form-control" id="contato">
                        <option>Contato 1</option>
                        <option>Contato 2</option>
                        <option>Contato 3</option>
                        <option>Contato 4</option>
                        <option>Contato 5</option>
                    </select>
                </div>
                
                <div class="form-group col-md-4">
                    <label for="contato">Status</label>
                    <select class="form-control" id="contato">
                        <option>Atribuido</option>
                        <option>Em execução</option>
                        <option>Aguardando</option>
                        <option>Finalizado</option>
                        <option>Cancelado</option>
                    </select>
                </div>
            </div>
            
            <div class="row">
                <div class="form-group col-md-3">
                    <label for="dataInicio">Início</label>
                    <input type="text" class="form-control" id="dataInicio" placeholder="00/00/0000">
                </div>
            </div>
			
			<div class="row">
			     <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="ativo" checked="checked">
                    <label class="form-check-label" for="stivo">Ativo</label>
                </div>
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