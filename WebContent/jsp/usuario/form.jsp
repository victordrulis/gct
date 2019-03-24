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
			<h2>Novo usuário</h2>
			<p>Inserir dados de usuário.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->

		<form action="../../jsp/usuario/list.jsp">
            <div class="row">
                <div class="form-group col-md-5">
                    <label for="contato">Selecione o contato</label>
                    <select class="form-control" id="contato">
                        <option>Contato 1</option>
                        <option>Contato 2</option>
                        <option>Contato 3</option>
                        <option>Contato 4</option>
                        <option>Contato 5</option>
                    </select>
                </div>
            </div>
			<div class="row">
	            <div class="form-group col-md-3">
	                <label for="usuario">Usuário</label>
	                <input type="text" class="form-control" id="usuario" placeholder="Usuário">
	            </div>
            </div>
	        <div class="row">
	            <div class="form-group col-md-3">
	                <label for="senha">Senha</label>
	                <input type="password" class="form-control" id="senha">
	            </div>
	            
	            <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="gerarSenha" checked="checked">
                    <label class="form-check-label" for="gerarSenha">Gerar senha</label>
                </div>
            </div>
            <div class="row">
	            <div class="form-group col-md-3">
                    <label for="confirmaSenha">Confirme a senha</label>
                    <input type="password" class="form-control" id="confirmaSenha">
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