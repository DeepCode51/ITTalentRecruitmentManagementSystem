(function($) {
  'use strict';

  $(".ms-settings-toggle").on('click', function(e){
    $('body').toggleClass('ms-settings-open');
  });

  $("#dark-mode").on('click', function(){
    $('body').toggleClass('ms-dark-theme');
  });
  $("#remove-quickbar").on('click', function(){
    $('body').toggleClass('ms-has-quickbar');
  });
  $("#hide-aside-left").on('click', function(){
    $('body').toggleClass('ms-aside-left-open');
  });


})(jQuery);
