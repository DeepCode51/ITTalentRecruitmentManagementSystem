(function($) {
  'use strict';
  //Pie Chart
  new Chart(document.getElementById("pie-chart"), {
    type: 'pie',
    data: {
      labels: ["Emergency", "ICU", "Neurology", "Cardiology", "Gynaecology"],
      datasets: [{
        label: "Population (millions)",
        backgroundColor: ["#357ffa", "#f7b11b","#ff6c60","#8663e1","#08bf6f"],
        data: [2478,5267,734,784,433]
      }]
    },
    options: {
      title: {
        display: true,
        text: 'Number of staffs according to deparments'
      }
    }
  });


    // Bar chart
    var barChart = new Chart(document.getElementById("bar-chart"), {
        type: 'bar',
        data: {
          labels: ["Emergency", "ICU", "Neurology", "Cardiology", "Gynaecology"],
          datasets: [
            {
              label: "Employes",
              backgroundColor: ["#357ffa", "#f7b11b","#ff6c60","#8663e1","#08bf6f"],
              data: [478,1267,1734,2384,133]
            }
          ]
        },
        options: {
          legend: { display: false },
          title: {
            display: true,
            text: 'Maximum number of employes in departments'
          }
        }
    });

})(jQuery);
