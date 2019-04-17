<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Contato"%>
<%@page import="br.com.drulis.gct.dominio.Cliente"%>
<%@page import="br.com.drulis.gct.dominio.Cliente"%>
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
	<%
	   System.out.println(this.getClass().getSimpleName() + ": Show");
	   Cliente cliente = (Cliente) request.getAttribute("resultado");
	%>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2>Cliente - ID: <%= cliente.getId() %></h2>
			<p>Dados do cliente.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->

			<div class="row">
                <div class="form-group">
                    <h5><strong>Cliente</strong></h5>
                    <p>
	                    <span><strong>Nome:</strong> <%= cliente.getContato().getNome()%></span>
	                    <span><strong>CPF/CNPJ:</strong> <%= cliente.getContato().getCpfCnpj()%></span>
	                    <span><strong>Email:</strong> <%= cliente.getContato().getEmail()%></span>
	                    <span><strong>Telefone:</strong> <%= cliente.getContato().getTel()%></span>
	                    <span><strong>SLA:</strong> <%= cliente.getSla()%></span>
                    </p>
                </div>
            </div>
			<div class="row">
			    <a href="/gct/contato?acao=exibir&id=<%= cliente.getContato().getId() %>">
                    <button type="button" class="btn btn-primary">Exibir Contato</button>
                </a>
            </div>
	        <hr>
	        <h5><strong>Produtos contratados:</strong></h5>
	        <div class="row">
                <table id="listaCliente" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
        <thead>
          <tr>
              <th class="th-sm">ID</th>
              <th class="th-sm">Título</th>
              <th class="th-sm">Status</th>
              <th class="th-sm">Início do Contrato</th>
              <th class="th-sm">Duração (meses)</th>
              <th class="th-sm">Ativo</th>
              <th class="th-sm">Ação</th>
          </tr>
        </thead>
  <tbody>
    <tr>
      <td>1</td>
      <td>Título</td>
      <td>Em prodrução</td>
      <td>21/03/2019</td>
      <td>48</td>
      <td>Sim</td>
      <td>
            <a href="/gct/produto?acao=exibir&id=1">Visualizar</a>
            <a href="#">Remover</a> <!-- TODO: adicionar caminho para remover produto do cliente. Talvez um update?  -->
      </td>
    </tr>
    
    <tr>
      <td>3</td>
      <td>Título 3</td>
      <td>Em homologação</td>
      <td>11/02/2019</td>
      <td>48</td>
      <td>Sim</td>
      <td>
            <a href="/gct/produto?acao=exibir&id=2">Visualizar</a>
            <a href="#">Remover</a> <!-- TODO: adicionar caminho para remover produto do cliente. Talvez um update?  -->
      </td>
    </tr>
  </tbody>
</table>
       </div>
              <hr>
		<div class="row">
		    <a href="/gct/cliente?acao=editar&id=1">
		        <button type="button" class="btn btn-primary">Editar</button>
	        </a>
	        <a href="/gct/cliente?acao=excluir&id=1">
                      <button type="button" class="btn btn-warning">Excluir</button>
                  </a>
		     <a href="/gct/cliente">
		        <button type="button" class="btn btn-secundary">Voltar</button>
	         </a>
		</div>
			

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>