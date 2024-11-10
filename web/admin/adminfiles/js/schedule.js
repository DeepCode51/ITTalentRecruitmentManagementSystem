(function($) {
  'use strict';


/* Audience Metrics Graph */
var audienceMetricsCtx = document.getElementById('audience-metrics-chart').getContext("2d");
var gradientStrokePrimary = audienceMetricsCtx.createLinearGradient(0, 0, 0, 450);
gradientStrokePrimary.addColorStop(0, '#357ffa');

var gradientFillPrimary = audienceMetricsCtx.createLinearGradient(0, 0, 0, 450);
gradientFillPrimary.addColorStop(0, "rgba(53,127,250,0.4)");
gradientFillPrimary.addColorStop(1, "rgba(255,255,255,0)");

// datasets 2
var gradientStrokePrimaryDanger = audienceMetricsCtx.createLinearGradient(0, 0, 0, 450);
gradientStrokePrimaryDanger.addColorStop(0, '#ec4e3f');

var gradientFillPrimaryDanger = audienceMetricsCtx.createLinearGradient(0, 0, 0, 450);
gradientFillPrimaryDanger.addColorStop(0, "rgba(236, 78, 63,0.4)");
gradientFillPrimaryDanger.addColorStop(1, "rgba(255, 255, 255,0)");

// all data
var data_1 = [3800, 3900, 3300, 3800, 4000, 4200, 4400, 3800, 4600, 3900, 3800];
var data_2 = [2100, 3000, 2200, 2400, 2800, 2600, 2800, 2600, 2300, 2900, 2800];
var labels = ["12 AM", "2 PM", "4 PM", "6 PM", "8 PM", "10 PM", "12 PM", "2 PM", "6 PM", "8 AM", "10 PM"];

var audienceMetrics = new Chart(audienceMetricsCtx, {
    type: 'line',
    data: {
        labels: labels,
        datasets: [{
            label: "Traffic",
            borderColor: gradientStrokePrimary,
            pointBorderWidth: 9,
            pointRadius: 9,
            pointBorderColor: 'transparent',
            pointHoverRadius: 8,
            pointHoverBorderWidth: 3,
            pointHoverBackgroundColor: 'white',
            pointHoverBorderColor: '#357ffa',
            pointBackgroundColor: 'transparent',
            fill: true,
            backgroundColor: gradientFillPrimary,
            borderWidth: 2,
            data: data_1
        },
        {
            label: "Sales",
            borderColor: gradientStrokePrimaryDanger,
            pointBorderWidth: 9,
            pointRadius: 9,
            pointBorderColor: 'transparent',
            pointHoverRadius: 8,
            pointHoverBorderWidth: 3,
            pointHoverBackgroundColor: 'white',
            pointHoverBorderColor: '#ec4e3f',
            pointBackgroundColor: 'transparent',
            fill: true,
            backgroundColor: gradientFillPrimaryDanger,
            borderWidth: 2,
            data: data_2
        }
        ]
    },
    options: {
        elements: {
          line: {
              tension: 0
          }
        },
        tooltips: {
          titleFontColor: '#3a3952',
          bodyFontFamily:  'Roboto',
          backgroundColor: '#FFF',
          bodyFontColor: '#878793',
          bodyFontSize: 14,
          displayColors: false,
          bodySpacing: 10,
          intersect: false,
          bodyFontStyle: 'bold',
          xPadding: 15,
          yPadding: 15,
          mode: 'index'
        },
        legend: {
          display: false,
          position: "top"
        },
        scales: {
            yAxes: [{
                ticks: {
                    fontColor: "rgba(0,0,0,0.5)",
                    fontStyle: "bold",
                    beginAtZero: true,
                    maxTicksLimit: 200,
                    padding: 20
                },
                gridLines: {
                    drawTicks: false,
                    display: false
                }

            }],
            xAxes: [{
                gridLines: {
                    zeroLineColor: "transparent"
                },
                ticks: {
                    padding: 20,
                    beginAtZero: true,
                    fontColor: "rgba(0,0,0,0.5)",
                    fontStyle: "bold"
                }
            }]
        }
    }
});

$(".ms-graph-metrics .day").on('click', function(){
    var data = audienceMetrics.config.data;
    data.datasets[0].data = data_1;
    data.datasets[1].data = data_2;
    data.labels = labels;
    audienceMetrics.update();
});

$(".ms-graph-metrics .month").on('click', function(){
  var chart_labels = ["Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"];
  var data_1 = [4800, 4900, 4300, 4800, 5000, 5200, 5400, 4800, 5600, 4900, 4800, 5500];
  var data_2 = [3100, 4000, 3200, 3400, 3800, 3600, 3800, 3600, 3300, 3900, 3800, 2500];
  var data = audienceMetrics.config.data;
  data.datasets[0].data = data_1;
  data.datasets[1].data = data_2;
  data.labels = chart_labels;
  audienceMetrics.update();
});

$(".ms-graph-metrics .year").on('click', function(){
  var chart_labels = ["2007", "2008", "2010", "2011", "2012", "2013", "2014","2015", "2016", "2017", "2018", "2019"];
  var data_1 = [6800, 6900, 6300, 6800, 7000, 7200, 7400, 6800, 7600, 6900, 6800, 7500];
  var data_2 = [5100, 6000, 5200, 5400, 5800, 5600, 5800, 5600, 5300, 5900, 5800, 4500];
  var data = audienceMetrics.config.data;
  data.datasets[0].data = data_1;
  data.datasets[1].data = data_2;
  data.labels = chart_labels;
  audienceMetrics.update();
});



})(jQuery);
