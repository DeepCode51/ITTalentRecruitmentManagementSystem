(function($) {
  'use strict';

  /* Youtube Subs */
  var data_1 = [7, 6, 3, 5, 4, 2, 3, 6, 8, 5, 7];
  var data_2 = [0, -4, -2, 0, -5, -3, 0, 0, -2, -5, -3];
  var labels = ["12 AM", "2 PM", "4 PM", "6 PM", "8 PM", "10 PM", "12 PM", "2 PM", "6 PM", "8 AM", "10 PM"];
  var youtubeSubs = new Chart(document.getElementById("youtube-subscribers"), {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [{
            label: "Subscribers Gained",
            backgroundColor: '#357ffa',
            data: data_1
         }, {
            label: "Subscribers List",
            backgroundColor: '#ec4e3f',
            data: data_2
         }],
      },
      options: {
        legend: { display: false },
        title: {
          display: false,
        },
        scales: {
					xAxes: [{
						stacked: true,
					}],
					yAxes: [{
						stacked: true
					}]
				},
      }
  });

  $(".ms-graph-metrics .day").on('click', function(){
      var data = youtubeSubs.config.data;
      data.datasets[0].data = data_1;
      data.datasets[1].data = data_2;
      data.labels = labels;
      youtubeSubs.update();
  });

  $(".ms-graph-metrics .month").on('click', function(){
    var chart_labels = ["Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"];
    var data_1 = [24,27,21,27,24,23,25,28,21,23,24,27];
    var data_2 = [-25,-24,-23,-27,-21,-23,0,0,-23,0,-26,-23];
    var data = youtubeSubs.config.data;
    data.datasets[0].data = data_1;
    data.datasets[1].data = data_2;
    data.labels = chart_labels;
    youtubeSubs.update();
  });

  $(".ms-graph-metrics .year").on('click', function(){
    var chart_labels = ["2007", "2008", "2010", "2011", "2012", "2013", "2014","2015", "2016", "2017", "2018", "2019"];
    var data_1 = [300, 321, 259, 391, 429, 249, 300, 183, 341, 412, 422, 214]
    var data_2 = [-120, -85, -31, -180, -51, -44, -101, -125, -44, -31, -34, -29];
    var data = youtubeSubs.config.data;
    data.datasets[0].data = data_1;
    data.datasets[1].data = data_2;
    data.labels = chart_labels;
    youtubeSubs.update();
  });

  /* Engaged Users */
  var engagedUsersCtx = document.getElementById('engaged-users').getContext("2d");
  var gradientStrokeengagedUsers = engagedUsersCtx.createLinearGradient(0, 0, 0, 450);
  gradientStrokeengagedUsers.addColorStop(0, '#07be6e');

  var gradientFillengagedUsers = engagedUsersCtx.createLinearGradient(0, 0, 0, 450);
  gradientFillengagedUsers.addColorStop(0, "rgba(7, 190, 110,0.3)");
  gradientFillengagedUsers.addColorStop(1, "rgba(255,255,255,0)");

  new Chart(engagedUsersCtx, {
    type: 'line',
    data: {
        labels: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20],
        datasets: [{
            label: "Data",
            borderColor: gradientStrokeengagedUsers,
            pointBorderColor: gradientStrokeengagedUsers,
            pointBackgroundColor: gradientStrokeengagedUsers,
            pointHoverBackgroundColor: gradientStrokeengagedUsers,
            pointHoverBorderColor: gradientStrokeengagedUsers,
            pointBorderWidth: 0,
            pointHoverRadius: 0,
            pointHoverBorderWidth: 0,
            pointRadius: 0,
            fill: true,
            backgroundColor: gradientFillengagedUsers,
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

  /* Page Impressions */
  var pageImpressionsCtx = document.getElementById('page-impressions').getContext("2d");
  var gradientStrokepageImpressions = pageImpressionsCtx.createLinearGradient(0, 0, 0, 450);
  gradientStrokepageImpressions.addColorStop(0, '#07be6e');

  var gradientFillpageImpressions = pageImpressionsCtx.createLinearGradient(0, 0, 0, 450);
  gradientFillpageImpressions.addColorStop(0, "rgba(7, 190, 110,0.3)");
  gradientFillpageImpressions.addColorStop(1, "rgba(255,255,255,0)");

  new Chart(pageImpressionsCtx, {
    type: 'line',
    data: {
        labels: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20],
        datasets: [{
            label: "Data",
            borderColor: gradientStrokepageImpressions,
            pointBorderColor: gradientStrokepageImpressions,
            pointBackgroundColor: gradientStrokepageImpressions,
            pointHoverBackgroundColor: gradientStrokepageImpressions,
            pointHoverBorderColor: gradientStrokepageImpressions,
            pointBorderWidth: 0,
            pointHoverRadius: 0,
            pointHoverBorderWidth: 0,
            pointRadius: 0,
            fill: true,
            backgroundColor: gradientFillpageImpressions,
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
