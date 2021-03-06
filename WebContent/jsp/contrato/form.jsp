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
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2>Novo Contrato</h2>
			<p>Incluir produtos a um cliente.</p>
		</div>

	</div>
	
	<div class="container">
		<!-- Example row of columns -->

		<form action="/gct/contrato?acao=salvar" method="post">
			<div class="row">
                <div class="form-group col-md-5">
                    <label for="cliente"><strong>Cliente</strong></label>
                    <select class="form-control" name="clienteId" id="clienteId">
						<%
						   List<Cliente> listaCliente = (List<Cliente>) request.getAttribute("resultadoCliente");
                           for(Cliente cliente : listaCliente) {
						%>
                                <option value="<%= cliente.getId() %>"><%= cliente.getContato().getNome() %> (CPF/CNPJ: <%= cliente.getContato().getCpfCnpj() %>)</option>
                        <%
                           }
                        %>
                    </select><a href="/gct/cliente?acao=novo"><button type="button">+ Cliente</button></a>
                </div>
            </div>
	        
	        <div class="row">
                <div class="form-group col-md-5">
                    <label for="status">Status:</label>
                    <select class="form-control" name="status" id="status">
                        <%
                           // Abrir options
                        %>
                                <option value="1">Valor 1</option>
                                <option value="2">Valor 2</option>
                                <option value="3">Valor 3</option>
                        <%
                           // Fechar options
                        %>
                    </select>
                </div>
                
            </div>
	        
        <hr>
        <h5><strong>Produtos</strong></h5>
        <div class="listaProduto" id="listaProduto">
	        
	        <div class="row" id="addProduto">
                <div class="form-group col-md">
                    <label for="produto">Produto</label>
                    <select class="form-control" id="produtoId" name="produtoId">
                        <%
                           List<Produto> listaProduto = (List<Produto>) request.getAttribute("resultadoProduto");
                           for(Produto produto : listaProduto) {
                        %>
                                <option value="<%= produto.getId() %>"><%= produto.getTitulo() %> (Vers�o atual: <%= produto.getVersao() %>)</option>
                        <%
                           }
                        %>
                    </select>
                </div>
                
                <div class="form-group col-md-2">
                    <label for="dataInicio">In�cio do contrato</label> 
                    <input type="text" class="form-control" id="dataInicio" name="dataInicio" placeholder="22/02/2019">
                </div>
                
                <div class="form-group col-md-2">
                    <label for="dataFim">Fim do contrato</label> 
                    <input type="text" class="form-control" id="dataFim" name="dataFim" placeholder="22/02/2020">
                </div>
                
                <div class="form-group col-md-2">
                    <label for="duracaoContrato">Duracao</label> 
                    <input type="text" class="form-control" id="duracaoContrato" placeholder="XX (meses)">
                </div>
                <div class="form-check">
                    <label class="form-check-label" for="ativo">Ativo:</label>
                    <input type="checkbox" class="form-check-input form-control" id="ativo" name="ativo" checked="checked">
                </div>
            </div>
            
            </div>
                <hr>
				<div class="row">
				     <button type="submit" class="btn btn-primary col-md-1">Salvar</button>
				     <a class="col-md-1" href="/gct/cliente"><button type="button" class="btn btn-secundary col-md">Cancelar</button></a>
				     
				</div>
		</form>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>

</body>
</html>