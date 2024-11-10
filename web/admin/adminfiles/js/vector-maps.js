(function($) {
  'use strict';

  //World Map
  var worldVector = new Datamap({
    element: document.getElementById("world-map"),
    highlightOnHover: false,
    responsive: true,
    fills: {
      defaultFill: '#384ea1',
      authorHasTraveledTo: "#6678ba"
    },
    geographyConfig: {
      highlightFillColor: 'rgb(50,92,204)',
      borderColor: '#eee',
      highlightBorderColor: '#eee',
    },
    data: {
      USA: { fillKey: "authorHasTraveledTo" },
      JPN: { fillKey: "authorHasTraveledTo" },
      ITA: { fillKey: "authorHasTraveledTo" },
      CRI: { fillKey: "authorHasTraveledTo" },
      KOR: { fillKey: "authorHasTraveledTo" },
      DEU: { fillKey: "authorHasTraveledTo" },
      RUS: { fillKey: "authorHasTraveledTo" },
      AUS: { fillKey: "authorHasTraveledTo" },
      BRA: { fillKey: "authorHasTraveledTo" },
      IND: { fillKey: "authorHasTraveledTo" },
    }

  });

  //USA Map
  var usaVector = new Datamap({
    element: document.getElementById('usa-map'),
    scope: 'usa',
    responsive: true,
    fills: {
      defaultFill: '#384ea1',
    },
    geographyConfig: {
      highlightFillColor: 'rgb(50,92,204)',
      borderColor: '#eee',
      highlightBorderColor: '#eee',
    },
  });

  //Map with Arc
  var map = new Datamap({
    scope: 'world',
    element: document.getElementById('arc-map'),
    projection: 'orthographic',
    responsive: true,
    fills: {
      defaultFill: "#384ea1",
    },
    geographyConfig: {
      highlightFillColor: 'rgb(50,92,204)',
      borderColor: '#eee',
      highlightBorderColor: '#eee',
    },
    projectionConfig: {
      rotation: [97,-30]
    },
    data: {
      'USA': {fillKey: 'lt50' },
      'MEX': {fillKey: 'lt25' },
      'CAN': {fillKey: 'gt50' },
      'GTM': {fillKey: 'gt500'},
      'HND': {fillKey: 'eq50' },
      'BLZ': {fillKey: 'pink' },
      'GRL': {fillKey: 'eq0' },
      'CAN': {fillKey: 'gt50' }
    }
  });

  map.graticule();

  map.arc(
    [{
    origin: {
      latitude: 61,
      longitude: -149
    },
    destination: {
      latitude: -22,
      longitude: -43
    }
    },
    {
    origin: {
      latitude: 64,
      longitude: -144
    },
    destination: {
      latitude: -24,
      longitude: -31
    }
    }], {
    greatArc: true,
    animationSpeed: 2000
  });

  var bombMap = new Datamap({
    element: document.getElementById('map-bubbles'),
    scope: 'world',
    responsive: true,
    geographyConfig: {
        highlightFillColor: 'rgb(50,92,204)',
        borderColor: '#eee',
        highlightBorderColor: '#eee',
    },
    fills: {
        'USA': '#1f77b4',
        'RUS': '#9467bd',
        'PRK': '#ff7f0e',
        'PRC': '#2ca02c',
        'IND': '#e377c2',
        'GBR': '#8c564b',
        'FRA': '#d62728',
        'PAK': '#7f7f7f',
        defaultFill: '#384ea1'
    },
    data: {
        'RUS': {fillKey: 'RUS'},
        'PRK': {fillKey: 'PRK'},
        'PRC': {fillKey: 'PRC'},
        'IND': {fillKey: 'IND'},
        'GBR': {fillKey: 'GBR'},
        'FRA': {fillKey: 'FRA'},
        'PAK': {fillKey: 'PAK'},
        'USA': {fillKey: 'USA'}
    }
});

 var bombs = [{
    name: 'Joe 4',
    radius: 25,
    yield: 400,
    country: 'USSR',
    fillKey: 'RUS',
    significance: 'First fusion weapon test by the USSR (not "staged")',
    date: '1953-08-12',
    latitude: 50.07,
    longitude: 78.43
  },{
    name: 'RDS-37',
    radius: 40,
    yield: 1600,
    country: 'USSR',
    fillKey: 'RUS',
    significance: 'First "staged" thermonuclear weapon test by the USSR (deployable)',
    date: '1955-11-22',
    latitude: 50.07,
    longitude: 78.43

  },{
    name: 'Tsar Bomba',
    radius: 75,
    yield: 50000,
    country: 'USSR',
    fillKey: 'RUS',
    significance: 'Largest thermonuclear weapon ever tested—scaled down from its initial 100 Mt design by 50%',
    date: '1961-10-31',
    latitude: 73.482,
    longitude: 54.5854
  }
];

//draw bubbles for bombs
bombMap.bubbles(bombs, {
  popupTemplate: function (geo, data) {
    return ['<div class="hoverinfo">' +  data.name,
    '<br/>Payload: ' +  data.yield + ' kilotons',
    '<br/>Country: ' +  data.country + '',
    '<br/>Date: ' +  data.date + '',
    '</div>'].join('');
    }
});

$(window).on('resize', function() {
  bombMap.resize();
  worldVector.resize();
  usaVector.resize();
});


})(jQuery);
