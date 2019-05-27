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
var dadosGrafico = JSON.parse('${dadosGrafico}');
let meses = [];
let statusMes = [];
let status = [];

console.log(dadosGrafico);

for(let mes in dadosGrafico) {
	meses.push(mes);
	statusMes.push(dadosGrafico[mes]);
}

for(let s in statusMes) {
	status.push(statusMes[s]);
}

for(let qtd in status){
	console.log("Quantidades: " + qtd);
}

console.log("---------");
console.log("MESES: ");
console.log(meses);
console.log("---------");
console.log("Status por MESES: ")
console.log(statusMes);
console.log("---------");
console.log("SATUS: ");
console.log(status);
console.log("---------");

console.log('Meses analisados: ' + meses);

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

var data = {
	labels: meses,
	datasets: [{
		data: [
			10,
			5,
			7,
			25
		],
		backgroundColor: '#4cff33',
		borderColor: '#419e34',
	}]
};

var options = {
	legend: false,
	tooltips: true,
	title: {
		display: true,
		text: [
			'ATIVIDADES: STATUS',
			'Quantidade de atividades ativos por status'
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
	type: 'line',
	data: data,
	options: options
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