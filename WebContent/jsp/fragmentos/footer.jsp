	<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="../js/jquery-3.3.1.slim.min.js.download"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="assets/js/vendor/jquery-slim.min.js"><\/script>')
	</script>
	<script src="../js/popper.min.js.download"></script>
	<script	src="../js/bootstrap.min.js.download"></script>

	<!-- Icons -->
	<script	src="../js/feather.min.js.download"></script>
	<script>feather.replace()</script>

	<!-- Graphs -->
	<script	src="../js/Chart.min.js.download"></script>
	
	<script>
	$(document).ready(function(){
	    $("#busca").on("keyup", function() {
	      var value = $(this).val().toLowerCase();
	      $("#tabela tr").filter(function() {
	        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	      });
	    });
	  });
  </script>