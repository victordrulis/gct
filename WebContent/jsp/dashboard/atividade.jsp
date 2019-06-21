<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="br.com.drulis.gct.dominio.dashboard.Dashboard"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@include file="../fragmentos/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Line Chart</title>
<script src="js/moment.js"></script>
<script src="js/Chart.js"></script>
<script src="js/utils.js"></script>
<style>
canvas {
    -moz-user-select: none;
    -webkit-user-select: none;
    -ms-user-select: none;
}
</style>
<style type="text/css">/* Chart.js */
@
keyframes chartjs-render-animation {
    from {opacity: .99
}

to {
    opacity: 1
}

}
.chartjs-render-monitor {
    animation: chartjs-render-animation 1ms
}

.chartjs-size-monitor, .chartjs-size-monitor-expand,
    .chartjs-size-monitor-shrink {
    position: absolute;
    direction: ltr;
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
    overflow: hidden;
    pointer-events: none;
    visibility: hidden;
    z-index: -1
}

.chartjs-size-monitor-expand>div {
    position: absolute;
    width: 1000000px;
    height: 1000000px;
    left: 0;
    top: 0
}

.chartjs-size-monitor-shrink>div {
    position: absolute;
    width: 200%;
    height: 200%;
    left: 0;
    top: 0
}
</style>
</head>
<body>
<%@include file="../fragmentos/nav.jsp" %>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
<div class="row conteudo-topo">
  <div class="container">
    <hr>
    <h3>DASHBOARD</h3>
    <hr>
    <div style="width: 1000px">
        <div class="chartjs-size-monitor">
            <div class="chartjs-size-monitor-expand">
                <div class=""></div>
            </div>
            <div class="chartjs-size-monitor-shrink">
                <div class=""></div>
            </div>
        </div>
        <canvas id="chart-0" style="display: block; width: 800px; height: 400px;" width="800" height="400" class="chartjs-render-monitor"></canvas>
        <hr>
        <button id="randomizeData">Randomize Data</button>
    </div>
    <br>
    </div>
</div>
</main>

<!--
var labels = JSON.parse('${dadosGrafico}').map(function(e) {
	console.log(e);
	return e.name;
});

var data = jsonfile.map(function(e) {
	console.log(e);
	return e.age;
});
-->
    
<%@include file="../fragmentos/footer.jsp" %>
<script>
var dados = ${dadosGrafico};
var labels = [];
var qtds = [];
var dataSets = [];

console.log(" ----  ATIVIDADES ATIVAS POR STATUS E DATA DE INCLUSAO ----")
formataDataSet(dados);
console.log(dados);

console.log("--- MESES gravados");
console.log(${meses});

console.log("--- MAPAS gravados");
console.log(${mapa});

/**
 * Transforma os dados vindos na request no formato para ChartJS
 */
function formataDataSet(dados) {
	for (i in dados) {
		labels.push(i); // pegando o eixo X do gráfico.
		console.log(": " + i);
		var count = 0;
		
		for(x in dados[i]) {
			console.log("\tvalor: " + dados[i][x]);
		}
		
		dataSets.push(
				{label: "label",
					borderColor: window.chartColors.red,
					backgroundColor: window.chartColors.red,
					fill: false,
					data: [
						"1",
						"5",
					],
					yAxisID: 'y-axis-' + (count+1)
				}	
			);
	}
	
	
	console.log("---------");
	console.log("labels: " + labels);
	console.log("+++++");
	console.log("DATASETSSSSS");
	console.log(dataSets);
	
};

var lineChartData = {
		labels: ${meses},
		datasets: dataSets
	};

	window.onload = function() {
		var ctx = document.getElementById('chart-0').getContext('2d');
		window.myLine = Chart.Line(ctx, {
			data: lineChartData,
			options: {
				responsive: true,
				hoverMode: 'index',
				stacked: false,
				title: {
					display: true,
					text: ['ATIVIDADES', 'Quantidade de atividades ativas por status']
				},
				scales: {
					yAxes: [{
						type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
						display: true,
						position: 'left',
						id: 'y-axis-1',
					}],
				}
			}
		});
	};

	document.getElementById('randomizeData').addEventListener('click', function() {
		lineChartData.datasets.forEach(function(dataset) {
			dataset.data = dataset.data.map(function() {
				return randomScalingFactor();
			});
		});

		window.myLine.update();
	});
</script>
</body>
</html>