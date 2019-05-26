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
		<canvas id="chart-0" style="display: block; width: 512px; height: 256px;" width="512" height="256" class="chartjs-render-monitor"></canvas>
	</div>
	<br>
	</div>
</div>
</main>
	
<%@include file="../fragmentos/footer.jsp" %>
	
<script>
var presets = window.chartColors;
var utils = Samples.utils;
var inputs = {
	min: 8,
	max: 16,
	count: 8,
	decimals: 2,
	continuity: 1
};

function generateData() {
	// radar chart doesn't support stacked values, let's do it manually
	var values = utils.numbers(inputs);
	inputs.from = values;
	return values;
}

function generateLabels() {
	return utils.months({count: inputs.count});
}

console.log(generateLabels());

utils.srand(42);

var data = {
	labels: generateLabels(),
	datasets: [{
		backgroundColor: utils.transparentize(presets.red),
		borderColor: presets.red,
		data: generateData(),
		label: 'D0'
	}, {
		backgroundColor: utils.transparentize(presets.orange),
		borderColor: presets.orange,
		data: generateData(),
		hidden: true,
		label: 'D1',
		fill: '-1'
	}, {
		backgroundColor: utils.transparentize(presets.yellow),
		borderColor: presets.yellow,
		data: generateData(),
		label: 'D2',
		fill: 1
	}, {
		backgroundColor: utils.transparentize(presets.green),
		borderColor: presets.green,
		data: generateData(),
		label: 'D3',
		fill: false
	}, {
		backgroundColor: utils.transparentize(presets.blue),
		borderColor: presets.blue,
		data: generateData(),
		label: 'D4',
		fill: '-1'
	}, {
		backgroundColor: utils.transparentize(presets.purple),
		borderColor: presets.purple,
		data: generateData(),
		label: 'D5',
		fill: '-1'
	}]
};

var options = {
	maintainAspectRatio: true,
	spanGaps: false,
	elements: {
		line: {
			tension: 0.000001
		}
	},
	plugins: {
		filler: {
			propagate: false
		},
		'samples-filler-analyser': {
			target: 'chart-analyser'
		}
	}
};

var chart = new Chart('chart-0', {
	type: 'radar',
	data: data,
	options: options
});

// eslint-disable-next-line no-unused-vars
function togglePropagate(btn) {
	var value = btn.classList.toggle('btn-on');
	chart.options.plugins.filler.propagate = value;
	chart.update();
}

// eslint-disable-next-line no-unused-vars
function toggleSmooth(btn) {
	var value = btn.classList.toggle('btn-on');
	chart.options.elements.line.tension = value ? 0.4 : 0.000001;
	chart.update();
}

// eslint-disable-next-line no-unused-vars
function randomize() {
	inputs.from = [];
	chart.data.datasets.forEach(function(dataset) {
		dataset.data = generateData();
	});
	chart.update();
}
</script>



</body>
</html>