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
			<h3>Chamado #123 - <span>Titulo do chamado</span></h3>
			<p>Informações do chamado.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->

        <div class="row">
           <div class="col-md-8">
                   <h6><strong>Descrição</strong></h6> 
                   <span>
                       Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut maximus justo lectus, non rutrum 
                       sapien gravida nec. Donec suscipit ultrices erat vel vestibulum. Donec laoreet diam lectus, eget 
                       tincidunt neque auctor ac. Duis at est id tellus cursus ultricies. In ac laoreet odio, ullamcorper 
                       auctor lacus. Donec aliquam id nunc in molestie. Nunc eu mauris consectetur, viverra metus egestas, 
                       rhoncus nisi. 
                   </span>
               </div>
        </div>
                   <br>
		
		  <div class="row">
                <div class="form-group col-md-3">
                    <h6><strong>Produto</strong></h6>
                    <span>Produto XXX</span>
                </div>
                
                <div class="form-group col-md-3">
                    <h6><strong>Responsável</strong></h6>
                    <span>Contato XXX</span>
                </div>
                
                <div class="form-group col-md-2">
                    <h6><strong>Status</strong></h6>
                    <span>Em andamento</span>
                </div>
            </div>
				        
	        <div class="row">
                <div class="form-group col-md-8">
                    
                    <h6><strong>Atividades</strong></h6>
                    <table>
                        <thead>
	                        <tr>
	                            <th>ID</th>
	                            <th>Título</th>
	                            <th>Status</th>
	                            <th>Ação</th>
	                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>12</td>
                                <td>Título da atividade</td>
                                <td>Em espera</td>
                                <td>
                                    <a class="col-md-1" href="../../jsp/atividade/show.jsp">Visualizar</a>
                                </td>
                            </tr>
                            <tr>
                                <td>204</td>
                                <td>Título da atividade</td>
                                <td>Atribuido</td>
                                <td>
                                    <a class="col-md-1" href="../../jsp/atividade/show.jsp">Visualizar</a>
                                </td>
                            </tr>
                            <tr>
                                <td>35</td>
                                <td>Título da atividade</td>
                                <td>Finalizado</td>
                                <td>
                                    <a class="col-md-1" href="../../jsp/atividade/show.jsp">Visualizar</a>
                                </td>
                            </tr>
                            <tr>
                                <td>47</td>
                                <td>Título da atividade</td>
                                <td>Em execução</td>
                                <td>
                                    <a class="col-md-1" href="../../jsp/atividade/show.jsp">Visualizar</a>
                                </td>
                            </tr>
                            
                        </tbody>
                    </table>
                    <br>
                     <a href="../../jsp/atividade/form.jsp"><button type="submit" class="btn btn-primary col-md-3">Nova Atividade</button></a>
                    <hr>
                </div>
                
	        </div>
	        
	        <div class="row">
                <div class="form-group col-md-3">
                    <h6><strong>Início</strong></h6>
                    <span>22/02/2019</span>
                </div>
                
                <div class="form-group col-md-3">
                    <h6><strong>Prazo</strong></h6>
                    <span>25/02/2019</span>
                </div>
            </div>
            
             <hr>
			<div class="row">
			     
			</div>	
				
				<div class="row">
				     <a class="col-md-1" href="../../jsp/chamado/edit.jsp"><button type="submit" class="btn btn-primary">Editar</button></a>
				     <a class="col-md-1" href="../../jsp/chamado/list.jsp"><button type="submit" class="btn btn-primary">Finalizar</button></a>
				</div>
			

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>