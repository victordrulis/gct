<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
		<canvas id="chart1" width="1000" height="300" style="display: block;" class="chartjs-render-monitor"></canvas>
	</div>
	<br>
	<br> Chart Type:
	<select id="type">
		<option value="line" selected="selected">Line</option>
		<option value="bar">Bar</option>
	</select>
	<button id="update">update</button>
	</div>
</div>
</main>
	
<%@include file="../fragmentos/footer.jsp" %>
	
<script>
	function randomNumber(min, max) {
		return Math.random() * (max - min) + min;
	}

	function randomBar(date, lastClose) {
		var open = randomNumber(lastClose * 0.95, lastClose * 1.05)
				.toFixed(2);
		var close = randomNumber(open * 0.95, open * 1.05).toFixed(2);
		return {
			t : date.valueOf(),
			y : close
		};
	}

	var dateFormat = 'DD MMMM YYYY';
	var date = moment('April 01 2019', dateFormat);
	var data = [ randomBar(date, 30) ];
	while (data.length < 60) {
		date = date.clone().add(1, 'd');
		if (date.isoWeekday() <= 5) {
			data.push(randomBar(date, data[data.length - 1].y));
		}
	}

	var ctx = document.getElementById('chart1').getContext('2d');
	ctx.canvas.width = 1000;
	ctx.canvas.height = 300;

	var color = Chart.helpers.color;
	var cfg = {
		type : 'bar',
		data : {
			datasets : [ {
				label : 'CHRT - Chart.js Corporation',
				backgroundColor : color(window.chartColors.red).alpha(0.5)
						.rgbString(),
				borderColor : window.chartColors.red,
				data : data,
				type : 'line',
				pointRadius : 0,
				fill : false,
				lineTension : 0,
				borderWidth : 2
			} ]
		},
		options : {
			scales : {
				xAxes : [ {
					type : 'time',
					distribution : 'series',
					ticks : {
						source : 'data',
						autoSkip : true
					}
				} ],
				yAxes : [ {
					scaleLabel : {
						display : true,
						labelString : 'Closing price ($)'
					}
				} ]
			},
			tooltips : {
				intersect : false,
				mode : 'index',
				callbacks : {
					label : function(tooltipItem, myData) {
						var label = myData.datasets[tooltipItem.datasetIndex].label
								|| '';
						if (label) {
							label += ': ';
						}
						label += parseFloat(tooltipItem.value).toFixed(2);
						return label;
					}
				}
			}
		}
	};

	var chart = new Chart(ctx, cfg);

	document.getElementById('update').addEventListener('click', function() {
		var type = document.getElementById('type').value;
		chart.config.data.datasets[0].type = type;
		chart.update();
	});
</script>



</body>
</html>