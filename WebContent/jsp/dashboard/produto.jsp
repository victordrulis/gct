<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
		<canvas id="chart-1" style="display: block; width: 800px; height: 400px;" width="800" height="400" class="chartjs-render-monitor"></canvas>
		<hr>
	</div>
	<br>
	</div>
</div>
</main>
	
<%@include file="../fragmentos/footer.jsp" %>
<script>
var dadosGraficoTipo = '${dadosGraficoTipo}';
var convertido = dadosGraficoTipo.slice(1, dadosGraficoTipo.length - 1).split(",");
let tipos = [];
let tipoQtd = [];

convertido.forEach(item => {
	tipos.push(item.split("=")[0]);
	tipoQtd.push(item.split("=")[1])
});

console.log(tipos);
console.log(tipoQtd);

var DATA_COUNT = 12;

var utils = Samples.utils;

utils.srand(110);

function alternatePointStyles(ctx) {
	var index = ctx.dataIndex;
	return index % 2 === 0 ? 'circle' : 'rect';
}

function makeHalfAsOpaque(ctx) {
	var c = ctx.dataset.backgroundColor;
	return utils.transparentize(c);
}

function adjustRadiusBasedOnData(ctx) {
	var v = ctx.dataset.data[ctx.dataIndex];
	return v < 10 ? 5
		: v < 25 ? 7
		: v < 50 ? 9
		: v < 75 ? 11
		: 15;
}

function generateData() {
	return utils.numbers({
		count: DATA_COUNT,
		min: 0,
		max: 100
	});
}

var dataTipo = {
	labels: tipos,
	datasets: [{
		data: tipoQtd,
		backgroundColor: '#4cff33',
		borderColor: '#419e34',
	}]
};

var optionsTipo = {
	legend: false,
	tooltips: true,
	title: {
		display: true,
		text: [
			'PRODUTO: TIPO',
			'Quantidade por tipos de produtos ativos'
			],
		fontSize: 20
	},
	elements: {
		line: {
			fill: false,
		},
		point: {
			hoverBackgroundColor: makeHalfAsOpaque,
			radius: adjustRadiusBasedOnData,
			pointStyle: alternatePointStyles,
			hoverRadius: 15,
		}
	},
	scales: {
        yAxes: [{
            ticks: {
                beginAtZero: true
            }
        }],
        xAxes: [{
            barPercentage: 0.618
        }]
    }
};

var chart = new Chart('chart-0', {
	type: 'bar',
	data: dataTipo,
	options: optionsTipo
});


// eslint-disable-next-line no-unused-vars
function addDataset() {
	var newColor = utils.color(chart.data.datasets.length);

	chart.data.datasets.push({
		data: generateData(),
		backgroundColor: newColor,
		borderColor: newColor
	});
	chart.update();
}

// eslint-disable-next-line no-unused-vars
function randomize() {
	chart.data.datasets.forEach(function(dataset) {
		dataset.data = generateData();
	});
	chart.update();
}

// eslint-disable-next-line no-unused-vars
function removeDataset() {
	chart.data.datasets.shift();
	chart.update();
}

</script>
</body>
</html>