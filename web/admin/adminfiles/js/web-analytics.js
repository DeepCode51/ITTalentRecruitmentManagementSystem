(function($) {
  'use strict';





    /* Sessions by Channel Trends Charts */
    var trend_1 = [1, 3, 5, 4, 3, 3, 4, 8, 5];
    var trend_2 = [3, 2, 5, 4, 8, 4, 5, 2, 3];
    var trend_3 = [7, 5, 4, 5, 4, 7, 5, 5, 4];
    var trend_4 = [3, 3, 4, 6, 7, 6, 6, 4, 3];
    var trend_5 = [5, 4, 6, 7, 6, 5, 5, 7, 4];
    var labels = ["Jan-11", "Jan-12", "Jan-13", "Jan-14", "Jan-15", "Jan-16", "Jan-17","Jan-18", "Jan-19"];
    var options = {
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
      },
      elements: {
         point:{
           radius: 0
         }
       }
    };

    new Chart(document.getElementById('trend-01').getContext("2d"), {
      type: 'line',
      data: {
          labels: labels,
          datasets: [{
              label: "Data",
              borderColor: '#07be6e',
              backgroundColor: 'transparent',
              borderWidth: 2,
              data: trend_1
          }]
      },
      options: options
    });
    new Chart(document.getElementById('trend-02').getContext("2d"), {
      type: 'line',
      data: {
          labels: labels,
          datasets: [{
              label: "Data",
              borderColor: '#ec4e3f',
              backgroundColor: 'transparent',
              borderWidth: 2,
              data: trend_2
          }]
      },
      options: options
    });
    new Chart(document.getElementById('trend-03').getContext("2d"), {
      type: 'line',
      data: {
          labels: labels,
          datasets: [{
              label: "Data",
              borderColor: '#07be6e',
              backgroundColor: 'transparent',
              borderWidth: 2,
              data: trend_3
          }]
      },
      options: options
    });
    new Chart(document.getElementById('trend-04').getContext("2d"), {
      type: 'line',
      data: {
          labels: labels,
          datasets: [{
              label: "Data",
              borderColor: '#07be6e',
              backgroundColor: 'transparent',
              borderWidth: 2,
              data: trend_4
          }]
      },
      options: options
    });
    new Chart(document.getElementById('trend-05').getContext("2d"), {
      type: 'line',
      data: {
          labels: labels,
          datasets: [{
              label: "Data",
              borderColor: '#ec4e3f',
              backgroundColor: 'transparent',
              borderWidth: 2,
              data: trend_5
          }]
      },
      options: options
    });




    /* Website Performance Charts */
    var bounceRateCtx = document.getElementById('bounce-rate').getContext("2d");
    var gradientStrokebounceRate = bounceRateCtx.createLinearGradient(0, 0, 0, 450);
    gradientStrokebounceRate.addColorStop(0, '#07be6e');

    var gradientFillbounceRate = bounceRateCtx.createLinearGradient(0, 0, 0, 450);
    gradientFillbounceRate.addColorStop(0, "rgba(7, 190, 110,0.3)");
    gradientFillbounceRate.addColorStop(1, "rgba(255,255,255,0)");

    new Chart(bounceRateCtx, {
      type: 'line',
      data: {
          labels: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20],
          datasets: [{
              label: "Data",
              borderColor: gradientStrokebounceRate,
              pointBorderColor: gradientStrokebounceRate,
              pointBackgroundColor: gradientStrokebounceRate,
              pointHoverBackgroundColor: gradientStrokebounceRate,
              pointHoverBorderColor: gradientStrokebounceRate,
              pointBorderWidth: 0,
              pointHoverRadius: 0,
              pointHoverBorderWidth: 0,
              pointRadius: 0,
              fill: true,
              backgroundColor: gradientFillbounceRate,
              borderWidth: 2,
              data: [5,1,8,1,3,7,8,4,3,6,8,9,4,5,8,2,6,4,8,3]
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

    var pageViewsCtx = document.getElementById('page-views').getContext("2d");
    var gradientStrokepageViews = pageViewsCtx.createLinearGradient(0, 0, 0, 450);
    gradientStrokepageViews.addColorStop(0, '#ec4e3f');

    var gradientFillpageViews = pageViewsCtx.createLinearGradient(0, 0, 0, 450);
    gradientFillpageViews.addColorStop(0, "rgba(236, 78, 63,0.4)");
    gradientFillpageViews.addColorStop(1, "rgba(255, 255, 255,0)");

    new Chart(pageViewsCtx, {
      type: 'line',
      data: {
          labels: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20],
          datasets: [{
              label: "Data",
              borderColor: gradientStrokepageViews,
              pointBorderColor: gradientStrokepageViews,
              pointBackgroundColor: gradientStrokepageViews,
              pointHoverBackgroundColor: gradientStrokepageViews,
              pointHoverBorderColor: gradientStrokepageViews,
              pointBorderWidth: 0,
              pointHoverRadius: 0,
              pointHoverBorderWidth: 0,
              pointRadius: 0,
              fill: true,
              backgroundColor: gradientFillpageViews,
              borderWidth: 2,
              data: [8,5,1,8,5,9,4,3,4,5,8,4,4,8,9,5,5,1,3,6]
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

    var newSessionsCtx = document.getElementById('new-sessions').getContext("2d");
    var gradientStrokenewSessions = newSessionsCtx.createLinearGradient(0, 0, 0, 450);
    gradientStrokenewSessions.addColorStop(0, '#ec4e3f');

    var gradientFillnewSessions = newSessionsCtx.createLinearGradient(0, 0, 0, 450);
    gradientFillnewSessions.addColorStop(0, "rgba(236, 78, 63,0.4)");
    gradientFillnewSessions.addColorStop(1, "rgba(255, 255, 255,0)");

    new Chart(newSessionsCtx, {
      type: 'line',
      data: {
          labels: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20],
          datasets: [{
              label: "Data",
              borderColor: gradientStrokenewSessions,
              pointBorderColor: gradientStrokenewSessions,
              pointBackgroundColor: gradientStrokenewSessions,
              pointHoverBackgroundColor: gradientStrokenewSessions,
              pointHoverBorderColor: gradientStrokenewSessions,
              pointBorderWidth: 0,
              pointHoverRadius: 0,
              pointHoverBorderWidth: 0,
              pointRadius: 0,
              fill: true,
              backgroundColor: gradientFillnewSessions,
              borderWidth: 2,
              data: [1,6,3,8,3,4,5,1,8,5,8,4,2,4,6,8,5,3,7,5,8]
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

    var timeSiteCtx = document.getElementById('time-site').getContext("2d");
    var gradientStroketimeSite = timeSiteCtx.createLinearGradient(0, 0, 0, 450);
    gradientStroketimeSite.addColorStop(0, '#07be6e');

    var gradientFilltimeSite = timeSiteCtx.createLinearGradient(0, 0, 0, 450);
    gradientFilltimeSite.addColorStop(0, "rgba(7, 190, 110,0.3)");
    gradientFilltimeSite.addColorStop(1, "rgba(255,255,255,0)");

    new Chart(timeSiteCtx, {
      type: 'line',
      data: {
          labels: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20],
          datasets: [{
              label: "Data",
              borderColor: gradientStroketimeSite,
              pointBorderColor: gradientStroketimeSite,
              pointBackgroundColor: gradientStroketimeSite,
              pointHoverBackgroundColor: gradientStroketimeSite,
              pointHoverBorderColor: gradientStroketimeSite,
              pointBorderWidth: 0,
              pointHoverRadius: 0,
              pointHoverBorderWidth: 0,
              pointRadius: 0,
              fill: true,
              backgroundColor: gradientFilltimeSite,
              borderWidth: 2,
              data: [5,1,8,1,3,7,8,4,3,6,8,9,4,5,8,2,6,4,8,3]
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

    var siteImpressionsCtx = document.getElementById('site-impressions').getContext("2d");
    var gradientStrokesiteImpressions = siteImpressionsCtx.createLinearGradient(0, 0, 0, 450);
    gradientStrokesiteImpressions.addColorStop(0, '#07be6e');

    var gradientFillsiteImpressions = siteImpressionsCtx.createLinearGradient(0, 0, 0, 450);
    gradientFillsiteImpressions.addColorStop(0, "rgba(7, 190, 110,0.3)");
    gradientFillsiteImpressions.addColorStop(1, "rgba(255,255,255,0)");

    new Chart(siteImpressionsCtx, {
      type: 'line',
      data: {
          labels: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20],
          datasets: [{
              label: "Data",
              borderColor: gradientStrokesiteImpressions,
              pointBorderColor: gradientStrokesiteImpressions,
              pointBackgroundColor: gradientStrokesiteImpressions,
              pointHoverBackgroundColor: gradientStrokesiteImpressions,
              pointHoverBorderColor: gradientStrokesiteImpressions,
              pointBorderWidth: 0,
              pointHoverRadius: 0,
              pointHoverBorderWidth: 0,
              pointRadius: 0,
              fill: true,
              backgroundColor: gradientFillsiteImpressions,
              borderWidth: 2,
              data: [8,5,1,8,5,9,4,3,4,5,8,4,4,8,9,5,5,1,3,6]
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









  //World Map
  var worldVector = new Datamap({
    element: document.getElementById("world-map"),
    highlightOnHover: false,
    responsive: true,
    fills: {
      defaultFill: 'rgb(199,206,234)',
      USA: "#445cc8",
      RUS: "#f0ad4e",
      AUS: "#5cb85c",
      IND: "#d9534f",
      BRA: "#357ffa",
    },
    geographyConfig: {
      highlightFillColor: 'rgb(50,92,204)',
      borderColor: 'transparent',
      highlightBorderColor: 'transparent',
      popupOnHover: false
    },
    data: {
      USA: { fillKey: "USA" },
      RUS: { fillKey: "RUS" },
      AUS: { fillKey: "AUS" },
      IND: { fillKey: "IND" },
      BRA: { fillKey: "BRA" },
    }

  });

  $(window).on('resize', function() {
    worldVector.resize();
  });



})(jQuery);
