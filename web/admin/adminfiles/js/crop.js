(function($) {
  'use strict';

  /* Default */
  var defaultCrop = document.getElementById('default');
  var cropper = new Cropper(defaultCrop, {
    aspectRatio: 16 / 9,
    responsive: true,
  });

  /* Zoom Disabled */
  var zoomDisabled = document.getElementById('zoom-disabled');
  var cropper1 = new Cropper(zoomDisabled, {
    aspectRatio: 16 / 9,
    responsive: true,
    zoomable: false
  });

  /* Has Preview */
  var cropperExpanded = document.getElementById('cropper-expanded');
  var cropperExp = new Cropper(cropperExpanded, {
    aspectRatio: 16 / 9,
    guides: false,
    responsive: true,
    preview: '.ms-crop-preview',
  });

  $("#zoom-in").on('click', function(){
    cropperExp.zoom(0.1);
  });
  $("#zoom-out").on('click', function(){
    cropperExp.zoom(-0.1);
  });
  $("#rotate-right").on('click', function(){
    cropperExp.rotate(45);
  });
  $("#rotate-left").on('click', function(){
    cropperExp.rotate(-45);
  });
  $("#move-left").on('click', function(){
    cropperExp.move(-10, 0);
  });
  $("#move-down").on('click', function(){
    cropperExp.move(0, 10);
  });
  $("#move-right").on('click', function(){
    cropperExp.move(10, 0);
  });
  $("#move-up").on('click', function(){
    cropperExp.move(0, -10);
  });
  $("#lock").on('click', function(){
    cropperExp.disable();
  });
  $("#unlock").on('click', function(){
    cropperExp.enable();
  });
  $("#reset").on('click', function(){
    cropperExp.reset();
  });

})(jQuery);
