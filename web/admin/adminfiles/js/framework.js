(function($) {
  'use strict';

  /*========= Mednalytics Framework ===========*/
  var MednalyticsFramework = (function(){

    /* Initialize all required functions */
    function init(){

      mednalyticsShortcuts();

      setActiveMenuItem();

      customToggleActions();

      draggableElements('.ms-sortable', '.ms-draggable', 'enable', false);
      sortableLists();

      if($(".ms-quick-bar").length > 0){
        quickBarToggle();
        quickBarConfigure();
        quickBarPopulate();
      }

      customScrollbar();

      tooltipsInit();
      resetPopperPositioning();

      initRoundedProgress();

      notesMemberAppend();
      notesMemberRemove();
      deletableItem();

      addTaskBlockQB();
      addTaskBlockApp();
      addTask();
      confirmTask();
      taskComplete();

      starRating();

      formValidation();

      emailCheckAll();
      emailFlag();

      initDatePickers();

    }

    /* Preloaders */
    $(window).on('load', function(){
      $('#preloader-wrap').addClass('loaded');
    });

    /* Custom Keyboard Shortcuts */
    function mednalyticsShortcuts(){
      var quickBarItems = document.getElementsByClassName("ms-quick-bar-item");
      $('body').on('keyup', function(e){
        // Hide Quickbar - KEY = ESCAPE
        if(e.keyCode == 27){
          quickBarReset();
        }
        // Enable Quickbar configure mode - KEY = ALT + Q
        if(e.altKey && e.keyCode == 81){
          $('.ms-configure-qa').trigger('click');
        }
        // Launch first Quickbar item - KEY = ALT + 1
        if(e.altKey && e.keyCode == 49){
          $(quickBarItems[0]).find("a:first-child").trigger('click');
        }
        // Launch second Quickbar item - KEY = ALT + 2
        if(e.altKey && e.keyCode == 50){
          $(quickBarItems[1]).find("a:first-child").trigger('click');
        }
        // Launch third Quickbar item - KEY = ALT + 3
        if(e.altKey && e.keyCode == 51){
          $(quickBarItems[2]).find("a:first-child").trigger('click');
        }
        // Launch fourth Quickbar item - KEY = ALT + 4
        if(e.altKey && e.keyCode == 52){
          $(quickBarItems[3]).find("a:first-child").trigger('click');
        }
        // Launch fifth Quickbar item - KEY = ALT + 5
        if(e.altKey && e.keyCode == 53){
          $(quickBarItems[4]).find("a:first-child").trigger('click');
        }
        // Launch sixth Quickbar item - KEY = ALT + 6
        if(e.altKey && e.keyCode == 54){
          $(quickBarItems[5]).find("a:first-child").trigger('click');
        }

      });
    }

    /* Checks the protocol being used  (storeToLocal needs a running webserver on IE) */
    function checkReffererProtocol(){
      return (location.protocol == 'http:' || location.protocol == 'https:');
    }

    /* Store to local storage */
    function storeToLocal(key, items){
      localStorage[key] = JSON.stringify(items);
    }

    /* Get from local storage */
    function getFromLocal(key){
      if(localStorage[key])
        return JSON.parse(localStorage[key]);
      else
        return [];
    }

    /* Sets the active class to the currently viewed page */
    function setActiveMenuItem(){
      var current = location.pathname.split("/").slice(-1)[0].replace(/^\/|\/$/g, '');
      $('.ms-main-aside .menu-item a', $('#ms-side-nav')).each(function() {
        var $this = $(this);
        if (current === "" || current === "index.html") {
          //for root url
          if ($this.attr('href').indexOf("index.html") !== -1) {
            $(this).addClass('active');
            $(this).parents('.collapse').prev().addClass('active');
            if ($(this).parents('.collapse').length) {
              $(this).closest('.collapse').addClass('show');
            }
          }
        } else {
          //for other url
          if ($this.attr('href').indexOf(current) !== -1) {
            $(this).addClass('active');
            $(this).parents('.collapse').prev().addClass('active');
            if ($(this).parents('.collapse').length) {
              $(this).closest('.collapse').addClass('show');
            }
          }
        }
      });
    }

    /*  Custom Toggle Actions */
    function customToggleActions(){
      $(".ms-toggler").bind('click', function(){

        var target = $(this).data('target');
        var toggleType = $(this).data('toggle');

        switch(toggleType) {

        //Aside Left
        case 'slideLeft':
          $(target).toggleClass('ms-aside-open');
          $(".ms-aside-overlay.ms-overlay-left").toggleClass('d-block');
          $("body").toggleClass('ms-aside-left-open');
          break;
        // Aside Right
        case 'slideRight':
          $(target).toggleClass('ms-aside-open');
          $(".ms-aside-overlay.ms-overlay-right").toggleClass('d-block');
          break;
        // Navbar Slide down
        case 'slideDown':
          $(target).toggleClass('ms-slide-down');
          break;
        // Quick bar
        case 'hideQuickBar':
          quickBarReset();
          break;
        default:
          return;

        }

      });
    }

    /* Drag with Sorting */
    function draggableElements(elemToSort, elemToDrag, stateSort, stateDrag){
      $( elemToSort ).sortable({
        scroll: false,
        placeholder: "ui-state-highlight",
        start: function(e, ui){
          ui.placeholder.height(ui.item.height());
          ui.placeholder.width(ui.item.width());
        }
      });
      $( elemToSort ).sortable(stateSort);
      $( elemToDrag ).draggable({
        opacity: 0.75,
        connectToSortable: elemToSort,
        containment: 'parent',
        revert: "invalid",
        disabled: stateDrag,
      });
      $( elemToSort ).disableSelection();
    }

    /* Allows an element to be droppable */
    function sortableLists(){
      $('.ms-sortable-list').sortable({
        opacity: 0.75,
        connectWith: '.ms-sortable-list',
        placeholder: "ui-state-highlight",
        start: function(e, ui){
          ui.placeholder.height(ui.item.height());
        }
      }).disableSelection();

    }

    /* Quickbar */
    function quickBarToggle(){

      $(".ms-quick-bar-list").on('click', '.ms-has-qa', function(){
        if( isConfigureMode() == false ){
          $(".ms-quick-bar").addClass("ms-quick-bar-open");
          $('.ms-quick-bar-title').text($(this).data('title'));
        }
      });

    }

    /* Checks if configure mode is enabled */
    function isConfigureMode(){
      return $('.ms-quick-bar-list').hasClass('ms-qa-configure-mode');
    }

    /* Reset quick bar */
    function quickBarReset(){
      $(".ms-quick-bar").removeClass("ms-quick-bar-open");
      $('.ms-quick-bar-item > a').removeClass('active show');
      $('.ms-quick-bar-item > a').attr('aria-selected', 'false');
      $('.ms-quick-bar > .tab-content .tab-pane').removeClass('active show');
    }

    /* Animate Round progress bars */
    function animateRoundedProgress(rpb){
      if (!$(rpb).hasClass('animated')){
        $(rpb).css('stroke-dashoffset', 358.141563 - (358.141563 / 100) * $(rpb).attr('aria-valuenow'));
        $(rpb).addClass('animated');
      }
    }
    function initRoundedProgress() {
      var roundedProgress = $('.progress-cicle');
      roundedProgress.each(function () {
        animateRoundedProgress(this);
      });
    }

    /* Configure Quick Bar */
    function quickBarConfigure(){

      var stateSort, stateDrag;
      draggableElements('.ms-quick-bar-list', '.ms-has-qa', 'disable', true);
      $('.ms-configure-qa').on('click', function(e){
        e.preventDefault();
        $('.ms-quick-bar-item a').removeAttr('data-toggle');
        quickBarReset();

        $('.ms-quick-bar-list').toggleClass('ms-qa-configure-mode');
        stateSort = ($('.ms-quick-bar-list').hasClass('ms-qa-configure-mode')) ? "enable" : "disable";
        stateDrag = (stateSort == 'disable') ? true : false;

        if( stateSort == 'disable' ){
          $('.ms-quick-bar-item a').attr('data-toggle', 'tab');
          quickBarSaveLocal();
        }

        draggableElements('.ms-quick-bar-list', '.ms-has-qa', stateSort, stateDrag);
      });

    }

    /* Save Quickbar Markup in local application storage */
    function quickBarSaveLocal(){

      if(checkReffererProtocol()){
        var quickBarLayout = $('.ms-quick-bar-list')[0].innerHTML;
        storeToLocal('quickbar_layout', quickBarLayout);
      }

    }

    /* Populate Quickbar with saved values */
    function quickBarPopulate(){
      if(checkReffererProtocol()){
        var localQuickBar = getFromLocal("quickbar_layout");
        if(localQuickBar.length > 0){
          $('.ms-quick-bar-list')[0].innerHTML = localQuickBar;
        }
      }
    }

    /* Custom Scrollbar */
    function customScrollbar(){
      // Scroll bar in content
      if( $('.ms-scrollable').length > 0 ){
        $('.ms-scrollable').each(function(){
          var ps = new PerfectScrollbar($(this)[0], {
            maxScrollbarLength : 700,
            wheelPropagation : true
          });
        });
      }
      //Special Case for Side nav
      if( $('.ms-aside-scrollable').length > 0 ){
        var psAside = new PerfectScrollbar($('.ms-aside-scrollable')[0], {
          maxScrollbarLength : 700,
          wheelPropagation : true,
          wheelSpeed : 0.5
        });

        $(".ms-main-aside").on('click', '.has-chevron', function(){
          psAside.update();
        });
      }

    }

    /* Bootstrap Tooltips */
    function tooltipsInit(){
      $('body').tooltip({
        selector: '[data-toggle="tooltip"]',
        trigger: 'hover',
        template: '<div class="tooltip" role="tooltip"><div class="tooltip-inner"></div></div>'
      });
    }

    /* Prevent Popper from using its own X-placement for placing Dropdowns */
    function resetPopperPositioning(){
      Popper.Defaults.modifiers.computeStyle.gpuAcceleration = false;
    }

    /* @TODO: MOVE WHATS AFTER THIS COMMENT TO THEIR OWN DESIGNATED SCRIPT FILES */

    /* Notes Members Add */
    function notesMemberAppend(){
      $('.ms-members-list').on('click', 'a', function(){
        var memberSrc = $(this).find('img').attr('src');
        $(this).closest('.ms-card-footer').prev().find('.ms-note-members').append('<li class="ms-deletable"> <img src="' + memberSrc + '" alt="member"> </li>');
      });
    }

    /* Notes Members Remove */
    function notesMemberRemove(){
      $('.ms-note-members').on('click', '.ms-deletable', function(){
        $(this).remove();
      });
    }

    /* Notes Remove */
    function deletableItem(){
      $('body').on('click', '.ms-delete-trigger', function(){
        $(this).closest('.ms-deletable').slideUp('slow', function(){ $(this).closest('.ms-deletable').remove(); });
      });
    }

    /* Add Task Block (QuickBar) */
    function addTaskBlockQB(){
      $(".ms-add-task-block").on('submit', function(e){
        e.preventDefault();
        $(".ms-quickbar-container .ms-todo-list").prepend(
          '<div class="ms-card ms-qa-card ms-deletable"> <div class="ms-card-header clearfix">' +
          '<h6 class="ms-card-title">'+ $("#task-block").val() +'</h6> <button data-toggle="tooltip" data-placement="left" title="Add a Task to this block" class="ms-add-task-to-block ms-btn-icon float-right">' +
          '<i class="material-icons">add</i> </button> </div> <div class="ms-card-body"> <ul class="ms-list ms-task-block"> </ul></div>' +
          '<div class="ms-card-footer clearfix"><a href="#" class="text-disabled mr-2"> <i class="flaticon-archive"> </i> Archive </a><a href="#" class="text-disabled ms-delete-trigger float-right">' +
          '<i class="flaticon-trash"> </i> Delete </a> </div> </div>'
        );
      });
    }

    /* Add Task Block (App) */
    function addTaskBlockApp(){
      $(".ms-add-task-block-2").on('submit', function(e){
        e.preventDefault();
        $(".ms-todo-list").prepend(
          '<div class="col-xl-4 col-md-6 col-sm-12 ms-deletable"> <div class="ms-card"> <div class="ms-card-header clearfix">' +
          '<h6 class="ms-card-title">'+ $("#task-block-2").val() +'</h6> <button data-toggle="tooltip" data-placement="left" title="Add a Task to this block" class="ms-add-task-to-block ms-btn-icon float-right">' +
          '<i class="material-icons">add</i> </button> </div> <div class="ms-card-body"> <ul class="ms-list ms-task-block"> </ul></div>' +
          '<div class="ms-card-footer clearfix"><a href="#" class="text-disabled mr-2"> <i class="flaticon-archive"> </i> Archive </a><a href="#" class="text-disabled ms-delete-trigger float-right">' +
          '<i class="flaticon-trash"> </i> Delete </a> </div> </div> </div>'
        );
      });
    }

    /* Add a Task to Block */
    function addTask(){
      $('.ms-todo-list').on('click', '.ms-add-task-to-block' , function(){
        var taskBlock = $(this).parent().next().find('.ms-task-block');
        taskBlock.append(
          '<li class="ms-list-item ms-to-do-task ms-deletable">' +
          '<label class="ms-checkbox-wrap ms-todo-complete" for="">' +
          '<input type="checkbox" name="" value="">' +
          '<i class="ms-checkbox-check"></i>' +
          '</label>' +
          '<form class="ms-confirm-task-form"> <input type="text" class="ms-task-input ms-task-edit"/>' +
          '<button type="submit" class="close"><i class="material-icons fs-16 ms-confirm-trigger">check</i></button></form>' +
          '</li>'
        );
        taskBlock.find('.ms-task-edit').focus();
      });
    }

    /* Confirm task after adding */
    function confirmTask(){
      $(".ms-todo-list").on('submit', '.ms-confirm-task-form', function(e){
        e.preventDefault();
        var confirmBtn = $(this).find('i');
        var taskInput = $(this).find('.ms-task-input');
        confirmBtn.removeClass('material-icons fs-14 ms-confirm-trigger');
        confirmBtn.addClass('');
        confirmBtn.text('');
        $(this).replaceWith('<span>'+ taskInput.val() +'</span><button class="close"><i class="flaticon-trash ms-delete-trigger"> </i></button>');

      });
    }

    /* Complete To-Do Task */
    function taskComplete(){
      $(".ms-todo-list").on('click', '.ms-todo-complete' , function(){
        $(this).parent().toggleClass('task-complete');
      });
    }

    /* Star Rating */
    function starRating(){
      $('.ms-star-rating').on('click', '.ms-rating-item', function(){
        $(this).prevAll().removeClass('rated');
        $(this).addClass('rated');
        $(this).nextAll().addClass('rated');
      });
    }

    /* Form Validation */
    function formValidation(){
      window.addEventListener('load', function() {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function(form) {
          form.addEventListener('submit', function(event) {
            if (form.checkValidity() === false) {
              event.preventDefault();
              event.stopPropagation();
            }
            form.classList.add('was-validated');
          }, false);
        });
      }, false);
    }

    /* Email Check All Checkbox */
    function emailCheckAll(){
      $(".ms-email-check-all").on('click', function(){
        $(".ms-email input").not(this).prop('checked', this.checked);
      });
    }

    /* Sets an email as flagged */
    function emailFlag(){
      $(".ms-email-content").on('click', '.ms-pin-email', function(){
        $(this).closest('.ms-email').toggleClass('pinned');
      });
    }

    /* Initialize Datepickers */
    function initDatePickers(){
      $(".datepicker").each(function(){
        $(this).datepicker();
      });
    }

    return {
      init: init
    }

  })();

  MednalyticsFramework.init();

})(jQuery);
