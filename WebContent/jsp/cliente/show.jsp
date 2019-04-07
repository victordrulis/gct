<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Cliente"%>
<%@page import="br.com.drulis.gct.dominio.Contato"%>
<%@page import="br.com.drulis.gct.dominio.Produto"%>
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
                    <h5><strong>Contato</strong></h5>
                    <p>
	                    <span><strong>Nome:</strong></span> <%= cliente.getContato().getNome() %>, 
	                    <span><strong>CPF/CNPJ:</strong></span> <%= cliente.getContato().getCpfCnpj() %>
                    </p>
                </div>
            </div>
			<div class="row">
			    <a href="/gct/contato?acao=exibir&id=<%= cliente.getContato().getId() %>">
                    <button type="button" class="btn btn-primary">Exibir Contato</button>
                </a>
            </div>
	        <hr>
	        <h5><strong>Produtos contratados</strong></h5>
	        <div class="row">
                <table id="listaCliente" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
        <thead>
          <tr>
              <th class="th-sm">ID</th>
              <th class="th-sm">Título</th>
              <th class="th-sm">Status</th>
              <th class="th-sm">Início do Contrato</th>
              <th class="th-sm">Duração</th>
              <th class="th-sm">Ativo</th>
              <th class="th-sm">Ação</th>
          </tr>
        </thead>
  <tbody>
    <%
       List<Produto> listaProduto = (List<Produto>) cliente.getListaProdutos();
   
       if(listaProduto != null) {
           for(Produto produto : listaProduto) {
   %>
    <tr>
      <td><%= produto.getId() %></td>
      <td>Título</td>
      <td><%= produto.getStatus() %></td>
      <td><%= produto.getDataInclusao() %></td>
      <td>X horas</td>
      <td><%= produto.getAtivo() %></td>
      <td><a href="/gct/produto?acao=exibir&id=<%= produto.getId() %>">Visualizar</a></td>
      <td>
            <a href="/gct/cliente?acao=exibir&id=<%= cliente.getId() %>"><span>Visualizar</span></a>
      </td>
    </tr>
    <%
           }
       }
    %>
  </tbody>
</table>
       </div>
	        
            <div class="form-group col-md-3">
                <div class="form-group col-md-3">
                    <a href="#"><button type="submit" class="btn btn-primary">+ Produto</button></a>
                </div>
            </div>
                <hr>
				<div class="row">
				    <a href="/gct/cliente?acao=editar&id=<%= cliente.getId() %>">
				        <button type="button" class="btn btn-primary">Editar</button>
			        </a>
			        <a href="/gct/cliente?acao=excluir&id=<%= cliente.getId() %>">
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