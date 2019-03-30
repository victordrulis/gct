<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Contato"%>
<%@page import="br.com.drulis.gct.dominio.Cliente"%>
<%@include file="../fragmentos/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GCT - Clientes</title>
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
            <h2>Clientes</h2>
            <p>Lista    </p>
            <a href="/gct/contato">Contatos</a>
            <a href="/gct/cliente?acao=novo">Novo Cliente</a>
        </div>
    </div>
    <hr>
<div class="container">
<div class="row">
	<div class="col-sm-12 col-md-6">
	    <div id="dtBasicExample_filter" class="dataTables_filter">
	        <label>Busca:<input type="search" class="form-control" placeholder="" aria-controls="dtBasicExample"></label>
	    </div>
    </div>
    <div class="col-sm-12 col-md-6">
	    <div class="dataTables_length bs-select" id="dtBasicExample_length" >
	        <label>Registros por página <select name="dtBasicExample_length" aria-controls="dtBasicExample" class="custom-select custom-select-sm form-control form-control-sm"><option value="10">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select></label>
	    </div>
    </div>
</div>
    
    <table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
		<thead>
		  <tr>
		      <th class="th-sm">Nome</th>
		      <th class="th-sm">Email</th>
			  <th class="th-sm">Telefone</th>
	          <th class="th-sm">CPF/CNPJ</th>
			  <th class="th-sm">Cadastrado em</th>
			  <th class="th-sm">Produtos</th>
			  <th class="th-sm">Ação</th>
		  </tr>
		</thead>
  <tbody>
    <tr>
      <td>Tiger Nixon</td>
      <td>email@exemplo.com.br</td>
      <td>(99) 9999-0000</td>
      <td>123.123.123-12</td>
      <td>2011/04/25</td>
      <td>
            <ul>
                <li>Produto 1</li>
                <li>Produto 2</li>
            </ul>
      </td>
      <td>
            <a href="../../jsp/cliente/show.jsp"><span>Visualizar</span></a>
      </td>
    </tr>
    <tr>
      <td>Garrett Winters</td>
      <td>email@exemplo.com.br</td>
      <td>(99) 9999-0000</td>
      <td>123.123.123-12</td>
      <td>2011/07/25</td>
      <td>
            <ul>
                <li>Produto 1</li>
                <li>Produto 2</li>
            </ul>
      </td>
      <td>
        <a href="../../jsp/cliente/show.jsp"><span>Visualizar</span></a>
      </td>
    </tr>
    <tr>
      <td>Ashton Cox</td>
      <td>email@exemplo.com.br</td>
      <td>(99) 9999-0000</td>
      <td>123.123.123-12</td>
      <td>2009/01/12</td>
      <td>
            <ul>
                <li>Produto 1</li>
                <li>Produto 2</li>
            </ul>
      </td>
      <td>
            <a href="../../jsp/cliente/show.jsp"><span>Visualizar</span></a>
      </td>
    </tr>
    <tr>
      <td>Cedric Kelly</td>
      <td>email@exemplo.com.br</td>
      <td>(99) 9999-0000</td>
      <td>123.123.123-12</td>
      <td>2012/03/29</td>
      <td>
            <ul>
                <li>Produto 1</li>
                <li>Produto 2</li>
            </ul>
      </td>
      <td>
            <a href="../../jsp/cliente/show.jsp"><span>Visualizar</span></a>
      </td>
    </tr>
    <tr>
      <td>Airi Satou</td>
      <td>email@exemplo.com.br</td>
      <td>(99) 9999-0000</td>
      <td>123.123.123-12</td>
      <td>2008/11/28</td>
      <td>
            <ul>
                <li>Produto 1</li>
                <li>Produto 2</li>
            </ul>
      </td>
      <td>
            <a href="../../jsp/cliente/show.jsp"><span>Visualizar</span></a>
      </td>
    </tr>
    <tr>
      <td>Brielle Williamson</td>
      <td>email@exemplo.com.br</td>
      <td>(99) 9999-0000</td>
      <td>123.123.123-12</td>
      <td>2012/12/02</td>
      <td>
            <ul>
                <li>Produto 1</li>
                <li>Produto 2</li>
            </ul>
      </td>
      <td>
            <a href="../../jsp/cliente/show.jsp"><span>Visualizar</span></a>
      </td>
    </tr>
    <tr>
      <td>Herrod Chandler</td>
      <td>email@exemplo.com.br</td>
      <td>(99) 9999-0000</td>
      <td>123.123.123-12</td>
      <td>2012/08/06</td>
      <td>
            <ul>
                <li>Produto 1</li>
                <li>Produto 2</li>
            </ul>
      </td>
      <td>
            <a href="../../jsp/cliente/show.jsp"><span>Visualizar</span></a>
      </td>
    </tr>
    <tr>
      <td>Rhona Davidson</td>
      <td>email@exemplo.com.br</td>
      <td>(99) 9999-0000</td>
      <td>123.123.123-12</td>
      <td>2010/10/14</td>
      <td>
            <ul>
                <li>Produto 1</li>
                <li>Produto 2</li>
            </ul>
      </td>
      <td>
            <a href="../../jsp/cliente/show.jsp"><span>Visualizar</span></a>
      </td>
    </tr>
    <tr>
      <td>Colleen Hurst</td>
      <td>email@exemplo.com.br</td>
      <td>(99) 9999-0000</td>
      <td>123.123.123-12</td>
      <td>2009/09/15</td>
      <td>
            <ul>
                <li>Produto 1</li>
                <li>Produto 2</li>
            </ul>
      </td>
      <td>
            <a href="../../jsp/cliente/show.jsp"><span>Visualizar</span></a>
      </td>
    </tr>
    
  </tbody>
</table>
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