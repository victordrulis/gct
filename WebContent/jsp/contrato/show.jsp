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


<!-- TODO Nesta view tem que aparecer os dados do cliente e lista de todos os produtos -->
<!--        contratados por ele mostrando status do contrato -->



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
               <div class="form-group col-md">
                    <table id="listaContrato" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                    <thead>
                      <tr>
                          <th class="th-sm">ID</th>
                          <th class="th-sm">Produto</th>
                          <th class="th-sm">Status</th>
                          <th class="th-sm">Ativo</th>
                          <th class="th-sm">Data Início</th>
                          <th class="th-sm">Data Fim</th>
                          <th class="th-sm">Ação</th>
                      </tr>
                    </thead>
              <tbody>
                <%
                   for(Produto produto : listaProduto) {
                %>
                <tr>
                  <td><%= produto.getId() %></td>
                  <td><%= produto.getTitulo() %></td>
                  <td><%= produto.get %></td>
                  <td><%= produto.getDataInclusao() %></td>
                  <td><%= produto.getDuracaoContrato() %></td>
                  <td><%= produto.getAtivo() %></td>
                  <td>       </td>
                  <td>
                        <a href="/gct/produto?acao=exibir&id=<%= contrato.getId() %>"><span>Visualizar</span></a>
                  </td>
                </tr>
                <%
                   }
                %>
              </tbody>
            </table>
                </div>
                
               <div class="form-group col-md">
                    <label for="status">Status do contrato</label>
                    <select class="form-control" id="status" name="status">
                        <%
                           List<ContratoStatus> listaStatus = (List<ContratoStatus>) request.getAttribute("listaStatus");
                           for(ContratoStatus status : listaStatus) {
                        %>
                                <option value="<%= status.getId() %>"><%= status.getDescricao() %></option>
                        <%
                           }
                        %>
                    </select>
                </div>
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