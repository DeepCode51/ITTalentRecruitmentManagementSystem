(function($) {
  'use strict';

  // Line chart
  new Morris.Line({
    element: 'line-chart',
    data: [
      { year: '2008', value: 20 },
      { year: '2009', value: 10 },
      { year: '2010', value: 5 },
      { year: '2011', value: 5 },
      { year: '2012', value: 20 }
    ],
    xkey: 'year',
    ykeys: ['value'],
    labels: ['Value']
  });

  // Bar Chart
  new Morris.Bar({
    element: 'bar-chart',
    data: [
      { y: '2006', a: 100, b: 90 },
      { y: '2007', a: 75,  b: 65 },
      { y: '2008', a: 50,  b: 40 },
      { y: '2009', a: 75,  b: 65 },
      { y: '2010', a: 50,  b: 40 },
      { y: '2011', a: 75,  b: 65 },
      { y: '2012', a: 100, b: 90 }
    ],
    xkey: 'y',
    ykeys: ['a', 'b'],
    labels: ['Series A', 'Series B']
  });

  //Doughnut Chart
  new Morris.Donut({
    element: 'doughnut-chart',
    data: [
      {label: "Download Sales", value: 12},
      {label: "In-Store Sales", value: 30},
      {label: "Mail-Order Sales", value: 20}
    ]
  });

  //Stacked Bar
  new Morris.Bar({
    element: 'stacked-bar-chart',
    data: [
      {x: '2011 Q1', y: 3, z: 2, a: 3},
      {x: '2011 Q2', y: 2, z: null, a: 1},
      {x: '2011 Q3', y: 0, z: 2, a: 4},
      {x: '2011 Q4', y: 2, z: 4, a: 3}
    ],
    xkey: 'x',
    ykeys: ['y', 'z', 'a'],
    labels: ['Y', 'Z', 'A'],
    stacked: true
  });

  //Updating Chart
  var nReloads = 0;
  function data(offset) {
    var ret = [];
    for (var x = 0; x <= 360; x += 10) {
      var v = (offset + x) % 360;
      ret.push({
        x: x,
        y: Math.sin(Math.PI * v / 180).toFixed(4),
        z: Math.cos(Math.PI * v / 180).toFixed(4)
      });
    }
    return ret;
  }
  var graph = new Morris.Line({
      element: 'real-time-chart',
      data: data(0),
      xkey: 'x',
      ykeys: ['y', 'z'],
      labels: ['sin()', 'cos()'],
      parseTime: false,
      ymin: -1.0,
      ymax: 1.0,
      hideHover: true
  });
  function update() {
    nReloads++;
    graph.setData(data(5 * nReloads));
    $('#reloadStatus').text(nReloads + ' reloads');
  }
  setInterval(update, 1000);

  //Area Chart
  new Morris.Area({
    element: 'area-chart',
    data: [
      { y: '2006', a: 100, b: 90 },
      { y: '2007', a: 75,  b: 65 },
      { y: '2008', a: 50,  b: 40 },
      { y: '2009', a: 75,  b: 65 },
      { y: '2010', a: 50,  b: 40 },
      { y: '2011', a: 75,  b: 65 },
      { y: '2012', a: 100, b: 90 }
    ],
    xkey: 'y',
    ykeys: ['a', 'b'],
    labels: ['Series A', 'Series B']
  });

})(jQuery);
