(function($) {
  'use strict';
  const driver = new Driver();

  // Define the steps for introduction
  driver.defineSteps([
    {
      element: '#step-1',
      popover: {
        title: 'Popover Positioning',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.',
        position: 'right'
      }
    },
    {
      element: '#step-2',
      popover: {
        title: '<em>This is an italicized Title</em>',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.',
        position: 'bottom'
      }
    },
    {
      element: '#step-3',
      popover: {
        title: 'Popover with Image',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. <img src="../../assets/img/misc/misc-1.jpg" alt="img"/>',
        position: 'top'
      }
    },
    {
      element: '#step-4',
      popover: {
        title: 'Keyboard control',
        description: 'Move with left and right, and close with ESC',
        position: 'left'
      }
    },
    {
      element: '#step-5',
      popover: {
        title: 'This Concludes it ',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.',
        position: 'top'
      }
    },
  ]);

  // Start the introduction
  driver.start();
  $("#replay-tour").on('click', function(e){
    e.stopPropagation();
    driver.start();
  });

})(jQuery);
