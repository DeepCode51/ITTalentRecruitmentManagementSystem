(function($) {
  'use strict';


    //Group Bar Chart
    new Chart(document.getElementById("bar-chart-grouped"), {
      type: 'bar',
      data: {
        labels: ["1900", "1950", "1999", "2050"],
        datasets: [
          {
            label: "ICU",
            backgroundColor: "#009efb",
            data: [133,221,783,2478]
          }, {
            label: "OPD",
            backgroundColor: "#8e5ea2",
            data: [408,547,675,734]
          }
        ]
      },
      options: {
        title: {
          display: true,
          text: 'Patient In'
        }
      }
    });


    //Line Chart
    var ctx = document.getElementById('line-chart').getContext("2d");
    var gradientStroke = ctx.createLinearGradient(0, 0, 0, 450);
    gradientStroke.addColorStop(0, '#357ffa');

    var gradientFill = ctx.createLinearGradient(0, 0, 0, 450);
    gradientFill.addColorStop(0, "rgba(53,127,250,0.4)");
    gradientFill.addColorStop(1, "rgba(255,255,255,0)");

    // all data
    var data_1 = [1800, 1600, 2300, 2800, 3600, 2900, 3000, 3800, 3600];
    var data_2 = [4100, 3800, 3200, 3400, 2700, 2600, 3300, 3000, 2900];
    var labels = ["Jan-11", "Jan-12", "Jan-13", "Jan-14", "Jan-15", "Jan-16", "Jan-17","Jan-18", "Jan-19"];

    var lineChart = new Chart(ctx, {
      type: 'line',
      data: {
          labels: labels,
          datasets: [{
              label: "Patient",
              borderColor: gradientStroke,
              pointBorderColor: gradientStroke,
              pointBackgroundColor: gradientStroke,
              pointHoverBackgroundColor: gradientStroke,
              pointHoverBorderColor: gradientStroke,
              pointBorderWidth: 1,
              pointHoverRadius: 4,
              pointHoverBorderWidth: 1,
              pointRadius: 2,
              fill: true,
              backgroundColor: gradientFill,
              borderWidth: 1,
              data: data_1
          }]
      },
      options: {
          legend: {
          display: false,
          position: "bottom"
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
                fontColor: "rgba(0,0,0,0.5)",
                fontStyle: "bold"
              }
          }]
        }
      }
    });


  // Bar chart
  var barChart = new Chart(document.getElementById("bar-chart"), {
      type: 'bar',
      data: {
        labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
        datasets: [
          {
            label: "Population (millions)",
            backgroundColor: ["#357ffa", "#f7b11b","#ff6c60","#8663e1","#08bf6f"],
            data: [2478,5267,1734,3384,1433]
          }
        ]
      },
      options: {
        legend: { display: false },
        title: {
          display: true,
          text: 'Predicted world population (millions) in 2050'
        }
      }
  });



  //Pie Chart
  new Chart(document.getElementById("pie-chart"), {
    type: 'pie',
    data: {
      labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
      datasets: [{
        label: "Population (millions)",
        backgroundColor: ["#357ffa", "#f7b11b","#ff6c60","#8663e1","#08bf6f"],
        data: [2478,5267,734,784,433]
      }]
    },
    options: {
      title: {
        display: true,
        text: 'Predicted world population (millions) in 2050'
      }
    }
  });

  //Polar Chart
  new Chart(document.getElementById("polar-chart"), {
    type: 'polarArea',
    data: {
      labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
      datasets: [
        {
          label: "Population (millions)",
          backgroundColor: ["#357ffa", "#f7b11b","#ff6c60","#8663e1","#08bf6f"],
          data: [2478,5267,734,784,433]
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'Predicted world population (millions) in 2050'
      }
    }
  });

  //Doughnut Chart
  new Chart(document.getElementById("doughnut-chart"), {
    type: 'doughnut',
    data: {
      labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
      datasets: [
        {
          label: "Population (millions)",
          backgroundColor: ["#357ffa", "#f7b11b","#ff6c60","#8663e1","#08bf6f"],
          data: [2478,5267,734,784,433]
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'Predicted world population (millions) in 2050'
      }
    }
  });


  //Mixed Chart
  new Chart(document.getElementById("mixed-chart"), {
    type: 'bar',
    data: {
      labels: ["1900", "1950", "1999", "2050"],
      datasets: [{
          label: "Europe",
          type: "line",
          borderColor: "#8e5ea2",
          data: [408,547,675,734],
          fill: false
        }, {
          label: "Africa",
          type: "line",
          borderColor: "#3e95cd",
          data: [133,221,783,2478],
          fill: false
        }, {
          label: "Europe",
          type: "bar",
          backgroundColor: "#ff6c60",
          data: [408,547,675,734],
        }, {
          label: "Africa",
          type: "bar",
          backgroundColor: "#f7b11b",
          backgroundColorHover: "#3e95cd",
          data: [133,221,783,2478]
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'Population growth (millions): Europe & Africa'
      },
      legend: { display: false }
    }
  });

})(jQuery);
