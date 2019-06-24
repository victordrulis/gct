<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Contato"%>
<%@page import="br.com.drulis.gct.dominio.Contrato"%>
<%@page import="br.com.drulis.gct.dominio.Produto"%>
<%@page import="br.com.drulis.gct.dominio.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat" %>
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
	   Cliente cliente = (Cliente) request.getAttribute("resultado");
	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	   
	%>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2><span>Cliente #<%= cliente.getId() %></span></h2>
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
              <th class="th-sm">Ativo</th>
              <th class="th-sm">Ação</th>
          </tr>
        </thead>
  <tbody>
  		<%
  			if(cliente.getListaContrato() != null && !cliente.getListaContrato().isEmpty()) {
	  			for(Contrato contrato : cliente.getListaContrato()) {
		%>
			    <tr>
			      <td align="center"><%= contrato.getProduto().getId() %></td>
			      <td><%= contrato.getProduto().getTitulo() %></td>
			      <td><%= contrato.getProduto().getProdutoStatus().getDescricao() %></td>
			      <td align="center"><%= dateFormat.format(contrato.getDataInicio()) %></td>
			      <td align="center"><%= contrato.getAtivo() > 0 ? "Sim" : "-" %></td>
			      <td align="center">
			            <a href="/gct/produto?acao=exibir&id=<%= contrato.getProduto().getId() %>"><button type="button" class="btn btn-primary">Visualizar</button></a>
			            <span><a href="/gct/chamado?acao=novo&produtoId=<%= contrato.getProduto().getId() %>&clienteId=<%= contrato.getCliente().getId() %>"><button type="button" class="btn btn-warning">Chamado</button></a></span>
			      </td>
			    </tr>
				
		<%
	  			}
  			} else {
  		%>
  			<tr>
		      <td colspan="12" align="center">Não existem produtos contratados.</td>
	      	</tr>
      	<% } %>
    
  </tbody>
  		<tfoot>
		    <tr>
		      <td align="left" colspan="12"><span><strong>Total:</strong></span> <%= cliente.getListaContrato().size() %></td>
		    </tr>
		  </tfoot>
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