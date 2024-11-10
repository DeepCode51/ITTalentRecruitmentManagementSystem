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






})(jQuery);
