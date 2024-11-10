(function ($) {
  'use strict';

  $(".ms-panel-body").on('click', '.trigger-swal', function () {

    var swalType = $(this).data('swal');

    switch (swalType) {

      case 'basic':
        Swal.fire('The Internet?', 'That thing is still around?', 'question');
        break;
      case 'with-footer':
        Swal.fire({
          type: 'error',
          title: 'Oops...',
          text: 'Something went wrong!',
          footer: '<a href="#">Why do I have this issue?</a>'
        });
        break;
      case 'positioned':
        Swal.fire({
          position: 'top-end',
          type: 'success',
          title: 'Your work has been saved',
          showConfirmButton: false,
          timer: 1500
        });
        break;
      case 'with-html':
        Swal.fire({
          title: '<strong>HTML <u>example</u></strong>',
          type: 'info',
          html: 'You can use <b>bold text</b>, ' + '<a href="#">links</a> ' + 'and other HTML tags',
          showCloseButton: true,
          showCancelButton: true,
          focusConfirm: false,
          confirmButtonText: 'Great!',
          confirmButtonAriaLabel: 'Thumbs up, great!',
          cancelButtonText: 'Close',
          cancelButtonAriaLabel: 'Thumbs down'
        });
        break;
      case 'multi-step':
        Swal.fire({
          title: 'Are you sure?',
          text: "You won't be able to revert this!",
          type: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, delete it!'
        }).then(function (result) {
          if (result.value) {
            Swal.fire('Deleted!', 'Your file has been deleted.', 'success');
          }
        });
        break;
      case 'auto-close':
        var timerInterval = void 0;
        Swal.fire({
          title: 'Auto close alert!',
          html: 'I will close in <strong></strong> seconds.',
          timer: 2000,
          onBeforeOpen: function onBeforeOpen() {
            Swal.showLoading();
            timerInterval = setInterval(function () {
              Swal.getContent().querySelector('strong').textContent = Swal.getTimerLeft();
            }, 100);
          },
          onClose: function onClose() {
            clearInterval(timerInterval);
          }
        }).then(function (result) {
          if (
          // Read more about handling dismissals
          result.dismiss === Swal.DismissReason.timer) ;
        });
        break;
      case 'ajax':
        var ipAPI = 'https://api.ipify.org?format=json';
        Swal.queue([{
          title: 'Your public IP',
          confirmButtonText: 'Show my public IP',
          text: 'Your public IP will be received ' + 'via AJAX request',
          showLoaderOnConfirm: true,
          preConfirm: function preConfirm() {
            return fetch(ipAPI).then(function (response) {
              return response.json();
            }).then(function (data) {
              return Swal.insertQueueStep(data.ip);
            }).catch(function () {
              Swal.insertQueueStep({
                type: 'error',
                title: 'Unable to get your public IP'
              });
            });
          }
        }]);
        break;
      case 'custom':
        Swal.fire({
          title: 'Sweet!',
          text: 'Modal with a custom image.',
          imageUrl: '../../assets/img/misc/misc-1.jpg',
          imageWidth: 400,
          imageHeight: 200,
          imageAlt: 'Custom image',
          animation: false
        });
        break;
      default:
        Swal.fire('The Internet?', 'That thing is still around?', 'question');
        break;

    }
  });
})(jQuery);
