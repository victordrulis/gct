<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Contrato"%>
<%@page import="br.com.drulis.gct.dominio.ContratoStatus"%>
<%@page import="br.com.drulis.gct.dominio.Cliente"%>
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
	   Contrato contrato = (Contrato) request.getAttribute("resultado");
	%>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2>Contrato - ID: <%= contrato.getId() %></h2>
			<p>Dados do contrato.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->


<!-- TODO Nesta view tem que aparecer os dados do contrato e lista de todos os produtos -->
<!--        contratados por ele mostrando status do contrato -->



			<div class="row">
                <div class="form-group">
                    <h5><strong>Cliente</strong></h5>
                    <p>
	                    <span><strong>ID:</strong> <%= contrato.getCliente().getId()%></span>
	                    <span><strong>Nome:</strong> <%= contrato.getCliente().getContato().getNome()%></span>
	                    <span><strong>CPF/CNPJ:</strong> <%= contrato.getCliente().getContato().getCpfCnpj()%></span>
	                    <span><strong>Status:</strong> <%= contrato.getCliente().getStatus()%></span>
	                    <span><strong>Ativo:</strong> <%= contrato.getCliente().getAtivo()%></span>
                    </p>
                </div>
            </div>
            <div class="row">
               <div class="form-group col-md">
                    <h5><strong>Produto</strong></h5>
                    <p>
                        <span><strong>ID:</strong> <%= contrato.getProduto().getId()%></span>
                        <span><strong>Titulo:</strong> <%= contrato.getProduto().getTitulo()%></span>
                        <span><strong>Versao:</strong> <%= contrato.getProduto().getVersao()%></span>
                        <span><strong>Status:</strong> <%= contrato.getProduto().getStatus()%></span>
                        <span><strong>Ativo:</strong> <%= contrato.getProduto().getAtivo()%></span>
                    </p>
                </div>
            </div>                
            <div class="row">
               <div class="form-group col-md">
                 <p>
                     <span><strong>Data Início:</strong> <%= contrato.getDataInicio()%></span>
                     <span><strong>Data Fim:</strong> <%= contrato.getDataFim()%></span>
                 </p>
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
                
                 <p>
                     <span><strong>Ativo:</strong> <%= contrato.getAtivo() %></span>
                 </p>
                 
                </div>
            </div>
            
              <hr>
		<div class="row">
		    <a href="/gct/contrato?acao=editar&id=1">
		        <button type="button" class="btn btn-primary">Editar</button>
	        </a>
	        <a href="/gct/contrato?acao=excluir&id=1">
                      <button type="button" class="btn btn-warning">Excluir</button>
                  </a>
		     <a href="/gct/contrato">
		        <button type="button" class="btn btn-secundary">Voltar</button>
	         </a>
		</div>
			

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>