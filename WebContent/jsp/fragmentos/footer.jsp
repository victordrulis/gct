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
        function newWindow(url) {
            var myWindow = window.open(url, "_blank", "location=no,height=570,width=520,scrollbars=yes,status=yes");
        }
    </script>
	<script>
	function gerarGrafico() {
		var ctx = document.getElementById("myChart");
		var myChart = new Chart(ctx, {
			type : 'line',
			data : {
				labels : [ "Tim", "Rodrigo's Company", "AT&T", "Atacadão",
						"UMC", "Claro", "Vivo" ],
				datasets : [ {
					data : [ 339, 345, 483, 303, 489, 92, 34 ],
					lineTension : 0,
					backgroundColor : 'transparent',
					borderColor : '#007bff',
					borderWidth : 4,
					pointBackgroundColor : '#007bff'
				} ]
			},
			options : {
				scales : {
					yAxes : [ {
						ticks : {
							beginAtZero : false
						}
					} ]
				},
				legend : {
					display : false,
				}
			}
		});
	}
	
	function novaLinha(idPai, idLinha) {
	    var linha = document.getElementById(idLinha);
	    var elementoPai = document.getElementById(idPai);
	    var clone = linha.cloneNode(true);
	    clone.id = "linhaClonada";
	    elementoPai.appendChild(clone);
	}
	</script>