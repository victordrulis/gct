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
        <canvas id="chart-1" style="display: block; width: 800px; height: 400px;" width="800" height="400" class="chartjs-render-monitor"></canvas>
        <hr>
    </div>
    <br>
    </div>
</div>
</main>
    
<%@include file="../fragmentos/footer.jsp" %>
<script>
var labels = JSON.parse('${labels}');
var label = JSON.parse('${label}');
var values = JSON.parse('${values}');
var mapaStatus = JSON.parse('${mapaStatus}');


var dados = JSON.parse('${dadosGrafico}');
var estatus = []

for(i in dados) {
   estatus.push(dados[i]);
   console.log("labels : " + i);
   for(x in dados[i]) {
        console.log("    status: " + x);
       for(z in dados[i][x]) {
          console.log(z);
       }
    }
}
       
var jsonfile = dados

    var labels = JSON.parse('${dadosGrafico}').map(function(e) {
       return e.name;
    });
    
    var data = jsonfile.map(function(e) {
       return e.age;
    });;

    var ctx = canvas.getContext('2d');
    var config = {
       type: 'line',
       data: {
          labels: labels,
          datasets: [{
             label: 'Graph Line',
             data: data,
             backgroundColor: 'rgba(0, 119, 204, 0.3)'
          }]
       }
    };
    


var lineChartData = {
        labels: labels,
        datasets: [{
            label: 'Melhoria',
            borderColor: window.chartColors.red,
            backgroundColor: window.chartColors.red,
            fill: false,
            data: values[0],
            yAxisID: 'y-axis-1',
        }, {
            label: 'Tarefa',
            borderColor: window.chartColors.blue,
            backgroundColor: window.chartColors.blue,
            fill: false,
            data: values[1],
            yAxisID: 'y-axis-2'
        },{
            label: 'Alteração',
            borderColor: window.chartColors.blue,
            backgroundColor: window.chartColors.blue,
            fill: false,
            data: [
                randomScalingFactor(),
                randomScalingFactor(),
                randomScalingFactor(),
                randomScalingFactor(),
                randomScalingFactor(),
                randomScalingFactor(),
                randomScalingFactor()
            ],
            yAxisID: 'y-axis-2'
        }]
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
                    text: [
                        'ATIVIDADE: STATUS',
                        'Quantidade de atividades ativas por status'
                    ]
                },
                scales: {
                    yAxes: [{
                        type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
                        display: true,
                        position: 'left',
                        id: 'y-axis-1',
                    }, {
                        type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
                        display: true,
                        position: 'right',
                        id: 'y-axis-2',

                        // grid line settings
                        gridLines: {
                            drawOnChartArea: false, // only want the grid lines for one axis to show up
                        },
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