(function($) {
  'use strict';

  /* Default Range Slider */
  $("#demo_0").ionRangeSlider();

  /* Double Slider */
  $("#demo_1").ionRangeSlider({
    type: "double",
    grid: true,
    min: 0,
    max: 1000,
    from: 200,
    to: 800,
    prefix: "$"
  });

  /* Month Slider */
  $("#demo_2").ionRangeSlider({
    grid: true,
    from: new Date().getMonth(),
    values: [
      "Jan", "Feb", "Mar", "Apr", "May", "Jun",
      "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    ]
  });

  /* Prevent Collision */
  $("#demo_3").ionRangeSlider({
    type: "double",
    grid: true,
    min: 0,
    max: 100,
    from: 47,
    to: 53,
    prefix: "Weight: ",
    postfix: " million pounds",
    decorate_both: true // false,
    // values_separator: " to "
  });

  /* Snap */
  $("#demo_4").ionRangeSlider({
    type: "double",
    grid: true,
    min: -1000,
    max: 1000,
    from: -500,
    to: 500,
    step: 250
  });

  /* Floating Points */
  $("#demo_5").ionRangeSlider({
    type: "double",
    grid: true,
    min: -12.8,
    max: 12.8,
    from: -3.2,
    to: 3.2,
    step: 0.1
  });

  /* Skins */
  $("#demo_6").ionRangeSlider();

  $("#demo_7").ionRangeSlider({
    skin: 'big'
  });

  $("#demo_8").ionRangeSlider({
    skin: 'modern'
  });

  $("#demo_9").ionRangeSlider({
    skin: 'sharp'
  });

  $("#demo_10").ionRangeSlider({
    skin: 'round'
  });

  $("#demo_11").ionRangeSlider({
    skin: 'square'
  });

})(jQuery);
