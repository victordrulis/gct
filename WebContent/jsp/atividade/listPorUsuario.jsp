<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@include file="../fragmentos/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GCT - Contatos</title>
<style type="text/css">
.conteudo-topo {
    padding-top: 40px;
}
</style>
</head>
<body>
<%@include file="../fragmentos/nav.jsp" %>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
<div class="row conteudo-topo">
        <div class="container">
            <h2>Atividades atribuidas</h2>
            <p>Lista das atividades atribuidas.</p>
            <a href="../../jsp/contato/form.jsp">Novo Contato</a>
        </div>

    </div>
    <hr>
<div class="container">
<div class="row">
	<div class="col-sm-12 col-md-4">
	    <div id="dtBasicExample_filter" class="dataTables_filter">
	        <label>Responsável:
                <select class="form-control">
                    <option class="form-control">Contato 1</option>
                    <option class="form-control">Contato 2</option>
                    <option class="form-control">Contato 3</option>
                </select>
            </label>
	    </div>
    </div>
    <div class="col-sm-12 col-md-6">
        <div id="dtBasicExample_filter" class="dataTables_filter">
            <label>Busca:<input type="search" class="form-control" placeholder="" aria-controls="dtBasicExample"></label>
        </div>
    </div>
    <div class="col-sm-12 col-md-2">
	    <div class="dataTables_length bs-select" id="dtBasicExample_length" >
	        <label>Registros por página <select name="dtBasicExample_length" aria-controls="dtBasicExample" class="custom-select custom-select-sm form-control form-control-sm"><option value="10">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select></label>
	    </div>
    </div>
</div>
<hr>
    <div class="row">
        <div class="col-md-4 border">
          <h4>Atividade 209</h4>
          <a class="btn" href="#" role="button">Editar</a>
          <a class="btn" href="#" role="button">Excluir</a>
          
          <p>Chamado: Titulo do Chamado</p>
          <p>Cliente: Cliente 2</p>
          <p>Prazo: 00/00/2019</p>
          <p><a href="#"></a></p>
          <p><a class="btn btn-secondary" href="#" role="button">Mostrar »</a></p>
        </div>
        
        <div class="col-md-4 border">
          <h4>Atividade 2</h4>
          <a class="btn" href="#" role="button">Editar</a>
          <a class="btn" href="#" role="button">Excluir</a>
          
          <p>Chamado: Titulo do Chamado</p>
          <p>Cliente: Cliente 2</p>
          <p>Prazo: 00/00/2019</p>
          <p><a href="#"></a></p>
          <p><a class="btn btn-secondary" href="#" role="button">Mostrar »</a></p>
        </div>
        
        <div class="col-md-4 border">
          <h4>Atividade 89</h4>
          <a class="btn" href="#" role="button">Editar</a>
          <a class="btn" href="#" role="button">Excluir</a>
          
          <p>Chamado: Titulo do Chamado</p>
          <p>Cliente: Cliente 2</p>
          <p>Prazo: 00/00/2019</p>
          <p><a href="#"></a></p>
          <p><a class="btn btn-secondary" href="#" role="button">Mostrar »</a></p>
        </div>
        
        <div class="col-md-4 border">
          <h4>Atividade 22</h4>
          <a class="btn" href="#" role="button">Editar</a>
          <a class="btn" href="#" role="button">Excluir</a>
          
          <p>Chamado: Titulo do Chamado</p>
          <p>Cliente: Cliente 2</p>
          <p>Prazo: 00/00/2019</p>
          <p><a href="#"></a></p>
          <p><a class="btn btn-secondary" href="#" role="button">Mostrar »</a></p>
        </div>
        
        <div class="col-md-4 border">
          <h4>Atividade 34</h4>
          <a class="btn" href="#" role="button">Editar</a>
          <a class="btn" href="#" role="button">Excluir</a>
          
          <p>Chamado: Titulo do Chamado</p>
          <p>Cliente: Cliente 2</p>
          <p>Prazo: 00/00/2019</p>
          <p><a href="#"></a></p>
          <p><a class="btn btn-secondary" href="#" role="button">Mostrar »</a></p>
        </div>
        
        <div class="col-md-4 border">
          <h4>Atividade 101</h4>
          <a class="btn" href="#" role="button">Editar</a>
          <a class="btn" href="#" role="button">Excluir</a>
          
          <p>Chamado: Titulo do Chamado</p>
          <p>Cliente: Cliente 2</p>
          <p>Prazo: 00/00/2019</p>
          <p><a href="#"></a></p>
          <p><a class="btn btn-secondary" href="#" role="button">Mostrar »</a></p>
        </div>
    </div>
</div>
</main>
<%@include file="../fragmentos/footer.jsp" %>

<script>
//Basic example
$(document).ready(function () {
  $('#dtBasicExample').DataTable({
    "paging": false // false to disable pagination (or any other option)
  });
  $('.dataTables_length').addClass('bs-select');
});
</script>
</body>
</html>