(function($) {
  'use strict';

  $("#animate-it").on('click', function(){
    $("#img-animate").addClass($("#animation-selection").val());
    $("#img-animate").on('animationend', function() { $("#img-animate").attr('class', 'animated'); })
  });

})(jQuery);
