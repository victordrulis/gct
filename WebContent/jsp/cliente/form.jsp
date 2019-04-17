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
			<h2>Novo Cliente</h2>
			<p>Inserir dados do cliente.</p>
		</div>

	</div>
	
	<div class="container">
		<!-- Example row of columns -->

		<form action="/gct/cliente?acao=salvar" method="post">
			<div class="row">
                <div class="form-group col-md-5">
                    <label for="contato"><strong>Contato</strong></label>
                    <select class="form-control" name="contatoId" id="contatoId">
						<%
						   List<Contato> listaContato = (List<Contato>) request.getAttribute("listaContato");
                           for(Contato contato : listaContato) {
						%>
                                <option value="<%= contato.getId() %>"><%= contato.getNome() %> (CPF/CNPJ: <%= contato.getCpfCnpj() %>)</option>
                        <%
                           }
                        %>
                    </select><a href="/gct/contato?acao=novo"><button type="button">+ Contato</button></a>
                </div>
            </div>
	        <hr>
	        <h5><strong>Produtos contratados</strong></h5>
	        
        <div class="listaProduto" id="listaProduto">
	        
	        <div class="row" id="addProduto">
                <div class="form-group col-md">
                    <label for="produto">Produto</label>
                    <select class="form-control" id="produto">
                        <%
                           List<Produto> listaProduto = (List<Produto>) request.getAttribute("listaProduto");
                           for(Produto produto : listaProduto) {
                        %>
                                <option value="<%= produto.getId() %>"><%= produto.getTitulo() %> (Versão atual: <%= produto.getVersao() %>)</option>
                        <%
                           }
                        %>
                    </select>
                </div>
                
                <div class="form-group col-md-2">
                    <label for="contratoProdutoInicio">Início contrato</label> 
                    <input type="text" class="form-control" id="contratoProdutoInicio" placeholder="22/02/2019">
                </div>
                
                <div class="form-group col-md-2">
                    <label for="duracaoContrato">Duracao</label> 
                    <input type="text" class="form-control" id="duracaoContrato" placeholder="XX (meses)">
                </div>
                <div class="form-check">
                    <label class="form-check-label" for="stivo">Ativo</label>
                    <input type="checkbox" class="form-check-input form-control" id="ativo" checked="checked">
                </div>
            </div>
            
            </div>
            <div class="form-group col-md-3">
                <div class="form-group col-md-3">
                    <button type="button" class="btn btn-primary" onclick="novaLinha('listaProduto', 'addProduto')">+ Produto</button>
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