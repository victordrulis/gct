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
			<h2>Usu�rio</h2>
			<p>Dados de usu�rio.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->

		<form action="../../jsp/usuario/edit.jsp">
            <div class="row">
                <div class="form-group col-md-5">
                    <label for="contato">Contato</label>
                    <input type="text" class="form-control" id="contato" value="Contato">
                </div>
            </div>
			<div class="row">
	            <div class="form-group col-md-3">
	                <label for="usuario">Usu�rio</label>
	                <input type="text" class="form-control" id="usuario" value="user">
	            </div>
            </div>
	        
				<div class="form-check">
					<input type="checkbox" class="form-check-input" id="ativo" checked="checked">
					<label class="form-check-label" for="stivo">Ativo</label>
				</div>
				<div class="row">
				     <button type="submit" class="btn btn-primary col-md-1">Editar</button>
				</div>
			
		</form>
        <a href="../../jsp/usuario/list.jsp"><button type="submit" class="btn btn-secundary col-md-2">Voltar</button></a>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>