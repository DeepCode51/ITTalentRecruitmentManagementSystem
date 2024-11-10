(function($) {
  'use strict';

  //Line Chart
  var ctx = document.getElementById('line-chart').getContext("2d");
  var gradientStroke = ctx.createLinearGradient(0, 0, 0, 450);
  gradientStroke.addColorStop(0, '#F7931A');

  var gradientFill = ctx.createLinearGradient(0, 0, 0, 450);
  gradientFill.addColorStop(0, "rgba(247, 147, 26,0.2)");
  gradientFill.addColorStop(1, "rgba(255,255,255,0)");

  // all data
  var data_1 = [4100, 3800, 3200, 3400, 2700, 3600, 3300, 3700, 4900];
  var data_2 = [2800, 2600, 2300, 2800, 3600, 2900, 3000, 3100, 3600, 3000, 3100, 3200];
  var labels = ["Jan-11", "Jan-12", "Jan-13", "Jan-14", "Jan-15", "Jan-16", "Jan-17","Jan-18", "Jan-19"];
  var labels2 = ["Jan-11", "Jan-12", "Jan-13", "Jan-14", "Jan-15", "Jan-16", "Jan-17","Jan-18", "Jan-19", "Jan-20", "Jan-21", "Jan-22"];

  var lineChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: labels,
        datasets: [{
            label: "Data",
            borderColor: gradientStroke,
            pointBorderColor: gradientStroke,
            pointBackgroundColor: gradientStroke,
            pointHoverBackgroundColor: gradientStroke,
            pointHoverBorderColor: gradientStroke,
            pointBorderWidth: 0,
            pointHoverRadius: 0,
            pointHoverBorderWidth: 0,
            pointRadius: 0,
            fill: true,
            backgroundColor: gradientFill,
            borderWidth: 2,
            data: data_1
        }]
    },
    options: {
        elements: {
          line: {
              tension: 0
          }
        },
        legend: {
        display: false,
        position: "bottom"
        },
        scales: {
          yAxes: [{
            display: false,
          }],
          xAxes: [{
              display: false,
          }]
        }
      }
  });

  //Line Chart 2
  var ctx2 = document.getElementById('line-chart-2').getContext("2d");
  var gradientStroke2 = ctx.createLinearGradient(0, 0, 0, 450);
  gradientStroke2.addColorStop(0, '#07be6e');

  var gradientFill2 = ctx.createLinearGradient(0, 0, 0, 450);
  gradientFill2.addColorStop(0, "rgba(7, 190, 110,0.3)");
  gradientFill2.addColorStop(1, "rgba(255,255,255,0)");

  var lineChart2 = new Chart(ctx2, {
    type: 'line',
    data: {
        labels: labels2,
        datasets: [{
            label: "Data",
            borderColor: gradientStroke2,
            pointBorderColor: gradientStroke2,
            pointBackgroundColor: gradientStroke2,
            pointHoverBackgroundColor: gradientStroke2,
            pointHoverBorderColor: gradientStroke2,
            pointBorderWidth: 0,
            pointHoverRadius: 0,
            pointHoverBorderWidth: 0,
            pointRadius: 0,
            fill: true,
            backgroundColor: gradientFill2,
            borderWidth: 2,
            data: data_2
        }]
    },
    options: {
        elements: {
          line: {
              tension: 0
          }
        },
        legend: {
        display: false,
        position: "bottom"
        },
        scales: {
          yAxes: [{
            display: false,
          }],
          xAxes: [{
              display: false,
          }]
        }
      }
  });

  //Line chart 3
  var zCashCtx = document.getElementById('line-chart-3').getContext("2d");
  var gradientStrokeZCash = zCashCtx.createLinearGradient(0, 0, 0, 450);
  gradientStrokeZCash.addColorStop(0, '#e5a93d');

  var gradientFillZCash = zCashCtx.createLinearGradient(0, 0, 0, 450);
  gradientFillZCash.addColorStop(0, "rgba(229, 169, 61,0.2)");
  gradientFillZCash.addColorStop(1, "rgba(255,255,255,0)");

  new Chart(zCashCtx, {
    type: 'line',
    data: {
        labels: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15],
        datasets: [{
            label: "Data",
            borderColor: gradientStrokeZCash,
            pointBorderColor: gradientStrokeZCash,
            pointBackgroundColor: gradientStrokeZCash,
            pointHoverBackgroundColor: gradientStrokeZCash,
            pointHoverBorderColor: gradientStrokeZCash,
            pointBorderWidth: 0,
            pointHoverRadius: 0,
            pointHoverBorderWidth: 0,
            pointRadius: 0,
            fill: true,
            backgroundColor: gradientFillZCash,
            borderWidth: 2,
            data: [5,6,8,1,5,3,9,7,5,8,7,3,6,9,1]
        }]
    },
    options: {
        elements: {
          line: {
              tension: 0
          }
        },
        legend: {
        display: false,
        position: "bottom"
        },
        scales: {
          yAxes: [{
            display: false,
          }],
          xAxes: [{
              display: false,
          }]
        }
      }
  });

  //Line chart 4
  var peerCoinCtx = document.getElementById('line-chart-4').getContext("2d");
  var gradientStrokePeercoin = peerCoinCtx.createLinearGradient(0, 0, 0, 450);
  gradientStrokePeercoin.addColorStop(0, '#3FA30C');

  var gradientFillPeercoin = peerCoinCtx.createLinearGradient(0, 0, 0, 450);
  gradientFillPeercoin.addColorStop(0, "rgba(63, 163, 12,0.2)");
  gradientFillPeercoin.addColorStop(1, "rgba(255,255,255,0)");

  new Chart(peerCoinCtx, {
    type: 'line',
    data: {
        labels: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15],
        datasets: [{
            label: "Data",
            borderColor: gradientStrokePeercoin,
            pointBorderColor: gradientStrokePeercoin,
            pointBackgroundColor: gradientStrokePeercoin,
            pointHoverBackgroundColor: gradientStrokePeercoin,
            pointHoverBorderColor: gradientStrokePeercoin,
            pointBorderWidth: 0,
            pointHoverRadius: 0,
            pointHoverBorderWidth: 0,
            pointRadius: 0,
            fill: true,
            backgroundColor: gradientFillPeercoin,
            borderWidth: 2,
            data: [1,4,7,3,5,7,6,5,8,3,5,5,4,3,7]
        }]
    },
    options: {
        elements: {
          line: {
              tension: 0
          }
        },
        legend: {
        display: false,
        position: "bottom"
        },
        scales: {
          yAxes: [{
            display: false,
          }],
          xAxes: [{
              display: false,
          }]
        }
      }
  });

})(jQuery);
