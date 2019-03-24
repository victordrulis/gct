<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="fragmentos/header.jsp" %>
<style type="text/css">
.conteudo-topo {
  padding-top: 40px;
}
</style>

</head>
<body>
<%@include file="fragmentos/nav.jsp" %>    
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
        <div class="row conteudo-topo">
            <div class="container">
                <h2>Gestão de Chamados Técnicos</h2>
                <p>Controle de chamados técnicos.</p>
            </div>
            
        </div>
        <div class="container">
        <!-- Example row of columns -->
        <div class="row">
                <div class="col-md-4 border">
                  <h4>Urgentes</h4>
                  <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                  <p><a class="btn btn-secondary" href="#" role="button">View details »</a></p>
                </div>
                <div class="col-md-4 border">
                  <h4>Abertos</h4>
                  <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                  <p><a class="btn btn-secondary" href="#" role="button">View details »</a></p>
                </div>
                <div class="col-md-4 border">
                  <h4>Mais antigos</h4>
                  <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
                  <p><a class="btn btn-secondary" href="#" role="button">View details »</a></p>
                </div>
              </div>
      
              <hr>
      
            
        </div>
</main>
    <%@include file="fragmentos/footer.jsp" %>
</body>
</html>