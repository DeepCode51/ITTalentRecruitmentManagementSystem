(function ($) {
  "use strict";

  var $sToggle = $(".search-btn-mobile");
  var $searchBody = $(".search-body");
  $sToggle.on("click", function (e) {
    e.preventDefault();
    $searchBody.slideToggle();
  });

  /*----------------------------------
  # header sticky 
  -----------------------------------*/

  var activeSticky = $("#active-sticky"),
    winDow = $(window);
  winDow.on("scroll", function () {
    var scroll = $(window).scrollTop(),
      isSticky = activeSticky;
    $("#active-sticky2").removeClass("is-sticky-mobile");
    if (scroll < 1) {
      isSticky.removeClass("is-sticky");
    } else {
      isSticky.addClass("is-sticky");
      $("#active-sticky2").addClass("is-sticky-mobile");
    }
  });

  /*----------------------------------
  # Off Canvas Menu
  -----------------------------------*/

  var $offcanvasNav = $("#offcanvasNav a");
  $offcanvasNav.on("click", function () {
    var link = $(this);
    var closestUl = link.closest("ul");
    var activeLinks = closestUl.find(".active");
    var closestLi = link.closest("li");
    var linkStatus = closestLi.hasClass("active");
    var count = 0;

    closestUl.find("ul").slideUp(function () {
      if (++count == closestUl.find("ul").length)
        activeLinks.removeClass("active");
    });

    if (!linkStatus) {
      closestLi.children("ul").slideDown();
      closestLi.addClass("active");
    }
  });

  var $offcanvasMenu2 = $("#offcanvas-menu2 li a");
  $offcanvasMenu2.on("click", function (e) {
    // e.preventDefault();
    $(this).closest("li").toggleClass("active");
    $(this).closest("li").siblings().removeClass("active");
    $(this).closest("li").siblings().children(".category-sub-menu").slideUp();
    $(this)
      .closest("li")
      .siblings()
      .children(".category-sub-menu")
      .children("li")
      .toggleClass("active");
    $(this)
      .closest("li")
      .siblings()
      .children(".category-sub-menu")
      .children("li")
      .removeClass("active");
    $(this).parent().children(".category-sub-menu").slideToggle();
  });

  var $offcanvasMenu3 = $("#offcanvas-menu3 li a");
  $offcanvasMenu3.on("click", function (e) {
    // e.preventDefault();
    $(this).closest("li").toggleClass("active");
    $(this).closest("li").siblings().removeClass("active");
    $(this).closest("li").siblings().children(".category-sub-menu").slideUp();
    $(this)
      .closest("li")
      .siblings()
      .children(".category-sub-menu")
      .children("li")
      .toggleClass("active");
    $(this)
      .closest("li")
      .siblings()
      .children(".category-sub-menu")
      .children("li")
      .removeClass("active");
    $(this).parent().children(".category-sub-menu").slideToggle();
  });
  /*-----------------------------  
# Category more toggle  
-------------------------------*/

  $(".category-menu li.hidden").hide();
  $("#more-btn").on("click", function (e) {
    e.preventDefault();
    $(".category-menu li.hidden").toggle(500);
    var htmlAfter =
      '<i class="ion-ios-minus-empty" aria-hidden="true"></i> Less Categories';
    var htmlBefore =
      '<i class="ion-ios-plus-empty" aria-hidden="true"></i> More Categories';

    if ($(this).html() == htmlAfter) {
      $(this).html(htmlBefore);
    } else {
      $(this).html(htmlAfter);
    }
  });

  /*---------------------------
# menu-content
------------------------------ */
  const $btnMenu = $(".menu-btn");
  const $vmenuContent = $(".vmenu-content");
  $btnMenu.on("click", function (event) {
    $vmenuContent.slideToggle(500);
  });

  $vmenuContent.each(function () {
    var $ul = $(this),
      $lis = $ul.find(".menu-item:gt(10)"),
      isExpanded = $ul.hasClass("expanded");
    $lis[isExpanded ? "show" : "hide"]();

    if ($lis.length > 0) {
      $ul.append(
        $(
          '<li class="expand">' +
            (isExpanded
              ? '<a href="javascript:void(0);"><span><i class="ion-android-remove"></i>Close Categories</span></a>'
              : '<a href="javascript:void(0);"><span><i class="ion-android-add"></i>More Categories</span></a>') +
            "</li>"
        ).on("click", function (event) {
          var isExpanded = $ul.hasClass("expanded");
          event.preventDefault();
          $(this).html(
            isExpanded
              ? '<a href="javascript:void(0);"><span><i class="ion-android-add"></i>More Categories</span></a>'
              : '<a href="javascript:void(0);"><span><i class="ion-android-remove"></i>Close Categories</span></a>'
          );
          $ul.toggleClass("expanded");
          $lis.toggle(300);
        })
      );
    }
  });

  /*----------------------------  
        All Category toggle  
     ------------------------------*/

  $(".more-btn").on("click", function (e) {
    $(".category-menu").slideToggle(300);
  });
  $(".menu-item-has-children-1").on("click", function (e) {
    $(".category-mega-menu-1").slideToggle("slow");
  });
  $(".menu-item-has-children-2").on("click", function (e) {
    $(".category-mega-menu-2").slideToggle("slow");
  });
  $(".menu-item-has-children-3").on("click", function (e) {
    $(".category-mega-menu-3").slideToggle("slow");
  });
  $(".menu-item-has-children-4").on("click", function (e) {
    $(".category-mega-menu-4").slideToggle("slow");
  });
  $(".menu-item-has-children-5").on("click", function (e) {
    $(".category-mega-menu-5").slideToggle("slow");
  });
  $(".menu-item-has-children-6").on("click", function () {
    $(".category-mega-menu-6").slideToggle("slow");
  });
  /*-----------------------------  
                Category more toggle  
          -------------------------------*/

  var tooltipTriggerList = [].slice.call(
    document.querySelectorAll('[data-bs-toggle="tooltip"]')
  );
  var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl);
  });

  /*-----------------------------------
  # hero-slider
  ------------------------------ */
  var heroSlider = new Swiper(".hero-slider .swiper-container", {
    loop: true,
    speed: 600,
    autoplay: true,
    lazy: true,
    fadeEffect: {
      crossFade: true,
    },
    pagination: {
      el: ".hero-slider .swiper-pagination",
      clickable: true,
    },

    navigation: {
      nextEl: ".hero-slider .swiper-button-next",
      prevEl: ".hero-slider .swiper-button-prev",
    },
  });

  /*-----------------------------------
  # brand-carousel
  ------------------------------ */

  var brandCarousel = new Swiper(".brand-carousel .swiper-container", {
    loop: true,
    speed: 800,
    slidesPerView: 5,
    spaceBetween: 10,
    pagination: false,
    navigation: {
      nextEl: ".brand-carousel .swiper-button-next",
      prevEl: ".brand-carousel .swiper-button-prev",
    },
    // Responsive breakpoints
    breakpoints: {
      // when window width is >= 320px
      0: {
        slidesPerView: 1,
      },

      // when window width is >= 480px
      480: {
        slidesPerView: 2,
      },

      768: {
        slidesPerView: 3,
      },

      // when window width is >= 640px
      992: {
        slidesPerView: 4,
      },
      1200: {
        slidesPerView: 5,
      },
    },
  });

  /*-----------------------------------
  # food-category-carousel
  ------------------------------ */

  var foodCategoryCarousel = new Swiper(
    ".food-category-carousel .swiper-container",
    {
      loop: false,
      speed: 800,
      slidesPerView: 6,
      spaceBetween: 10,
      navigation: {
        nextEl: ".food-category-carousel .swiper-button-next",
        prevEl: ".food-category-carousel .swiper-button-prev",
      },
      // Responsive breakpoints
      breakpoints: {
        // when window width is >= 320px
        0: {
          slidesPerView: 1,
        },

        480: {
          slidesPerView: 2,
        },
        // when window width is >= 640px
        768: {
          slidesPerView: 3,
        },
        992: {
          slidesPerView: 4,
        },

        // when window width is >= 640px
        1200: {
          slidesPerView: 6,
        },
      },
    }
  );

  /*-----------------------------------
  # food carousel four items
  ------------------------------ */

  var foodCarouselFourItems = new Swiper(
    ".food-carousel-four-items .swiper-container",
    {
      loop: false,
      speed: 800,
      slidesPerView: 4,
      spaceBetween: 20,
      navigation: {
        nextEl: ".food-carousel-four-items .swiper-button-next",
        prevEl: ".food-carousel-four-items .swiper-button-prev",
      },
      // Responsive breakpoints
      breakpoints: {
        // when window width is >= 320px
        0: {
          slidesPerView: 1,
        },

        480: {
          slidesPerView: 2,
        },
        992: {
          slidesPerView: 3,
        },

        // when window width is >= 640px
        1200: {
          slidesPerView: 4,
        },
      },
    }
  );

  /*-----------------------------------
  # food carousel five items
  ------------------------------ */

  var foodCarouselFiveItems = new Swiper(
    ".food-carousel-five-items .swiper-container",
    {
      loop: false,
      speed: 800,
      slidesPerView: 5,
      spaceBetween: 20,
      navigation: {
        nextEl: ".food-carousel-five-items .swiper-button-next",
        prevEl: ".food-carousel-five-items .swiper-button-prev",
      },
      // Responsive breakpoints
      breakpoints: {
        // when window width is >= 320px
        0: {
          slidesPerView: 1,
        },

        480: {
          slidesPerView: 2,
        },
        992: {
          slidesPerView: 3,
        },

        // when window width is >= 640px
        1200: {
          slidesPerView: 4,
        },
        1500: {
          slidesPerView: 5,
        },
      },
    }
  );

  /*-----------------------------------
  # food carousel six items
  ------------------------------ */

  var foodCarouselSixItems = new Swiper(
    ".food-carousel-six-items .swiper-container",
    {
      loop: false,
      speed: 800,
      slidesPerView: 6,
      spaceBetween: 20,
      navigation: {
        nextEl: ".food-carousel-six-items .swiper-button-next",
        prevEl: ".food-carousel-six-items .swiper-button-prev",
      },
      // Responsive breakpoints
      breakpoints: {
        // when window width is >= 320px
        0: {
          slidesPerView: 1,
        },

        480: {
          slidesPerView: 2,
        },
        768: {
          slidesPerView: 3,
        },
        992: {
          slidesPerView: 4,
        },

        // when window width is >= 640px
        1200: {
          slidesPerView: 5,
        },
        1500: {
          slidesPerView: 6,
        },
      },
    }
  );

  /*-----------------------------------
  # tab carousel
  ------------------------------ */

  var tabCarousel = new Swiper(".tab-carousel .swiper-container", {
    loop: false,
    speed: 800,
    slidesPerView: 3,
    spaceBetween: 10,
    pagination: false,
    navigation: {
      nextEl: ".tab-carousel .swiper-button-next",
      prevEl: ".tab-carousel .swiper-button-prev",
    },
    observer: true,
    observeParents: true,
    // Responsive breakpoints
    breakpoints: {
      // when window width is >= 320px
      0: {
        slidesPerView: 1,
      },
      // when window width is >= 640px
      768: {
        slidesPerView: 2,
      },

      // when window width is >= 640px
      1200: {
        slidesPerView: 3,
      },
    },
  });

  /*-----------------------------------
  # dealCarouselOne
  ------------------------------ */

  var dealCarouselOne = new Swiper(".deal-carousel-one .swiper-container", {
    loop: false,
    speed: 800,
    slidesPerView: 2,
    spaceBetween: 20,
    pagination: false,
    navigation: {
      nextEl: ".deal-carousel-one .swiper-button-next",
      prevEl: ".deal-carousel-one .swiper-button-prev",
    },
    observer: true,
    observeParents: true,

    breakpoints: {
      0: {
        slidesPerView: 1,
      },

      992: {
        slidesPerView: 1,
      },

      1200: {
        slidesPerView: 2,
      },
    },
  });

  /*-----------------------------------
  # dealCarouseltwo
  ------------------------------ */

  var dealCarouseltwo = new Swiper(".deal-carousel-two .swiper-container", {
    loop: false,
    speed: 800,
    slidesPerView: 1,
    spaceBetween: 20,
    pagination: false,
    navigation: {
      nextEl: ".deal-carousel-two .swiper-button-next",
      prevEl: ".deal-carousel-two .swiper-button-prev",
    },
    observer: true,
    observeParents: true,

    breakpoints: {
      0: {
        slidesPerView: 1,
      },

      992: {
        slidesPerView: 1,
      },

      1200: {
        slidesPerView: 1,
      },
    },
  });

  /*-----------------------------------
  # featured Carousel
  ------------------------------ */

  var featuredCarousel = new Swiper(".featured-carousel .swiper-container", {
    loop: false,
    speed: 800,
    slidesPerView: 6,
    spaceBetween: 10,
    pagination: false,
    navigation: {
      nextEl: ".featured-carousel .swiper-button-next",
      prevEl: ".featured-carousel .swiper-button-prev",
    },
    observer: true,
    observeParents: true,
    // Responsive breakpoints
    breakpoints: {
      // when window width is >= 320px
      0: {
        slidesPerView: 1,
        loop: true,
        autoplay: {
          delay: 2000,
        },
        speed: 1000,
      },
      // when window width is >= 480px
      480: {
        slidesPerView: 2,
        loop: true,
        speed: 1000,
      },
      // when window width is >= 640px
      657: {
        slidesPerView: 3,
      },
      992: {
        slidesPerView: 4,
      },

      // when window width is >= 640px
      1200: {
        slidesPerView: 6,
      },
    },
  });
  /*-----------------------------------
  # featured Carousel
  ------------------------------ */

  var newArrivalCarousel = new Swiper(
    ".new-arrival-carousel .swiper-container",
    {
      loop: false,
      speed: 800,
      slidesPerView: 5,
      spaceBetween: 10,
      pagination: false,
      navigation: {
        nextEl: ".new-arrival-carousel .swiper-button-next",
        prevEl: ".new-arrival-carousel .swiper-button-prev",
      },
      observer: true,
      observeParents: true,
      // Responsive breakpoints
      breakpoints: {
        // when window width is >= 320px
        0: {
          slidesPerView: 1,
          loop: true,
          autoplay: {
            delay: 2000,
          },
          speed: 1000,
        },
        // when window width is >= 480px
        480: {
          slidesPerView: 2,
          loop: true,
          speed: 1000,
        },
        // when window width is >= 640px
        657: {
          slidesPerView: 3,
        },
        992: {
          slidesPerView: 4,
        },

        // when window width is >= 640px
        1200: {
          slidesPerView: 5,
        },
      },
    }
  );

  /*-----------------------------------
  # categories carousel
  ------------------------------ */

  var categoriesCarousel = new Swiper(
    ".categories-carousel .swiper-container",
    {
      loop: true,
      speed: 800,
      slidesPerView: 1,
      spaceBetween: 0,
      pagination: false,
      navigation: {
        nextEl: ".categories-carousel .swiper-button-next",
        prevEl: ".categories-carousel .swiper-button-prev",
      },
      // Responsive breakpoints
      breakpoints: {
        // when window width is >= 320px
        0: {
          slidesPerView: 1,
        },
        576: {
          slidesPerView: 2,
        },
        992: {
          slidesPerView: 1,
        },
      },
    }
  );
  /*-----------------------------------
  # categories carousel2
  ------------------------------ */

  var categoriesCarousel2 = new Swiper(
    ".categories-carousel2 .swiper-container",
    {
      loop: true,
      speed: 800,
      slidesPerView: 1,
      spaceBetween: 0,
      pagination: false,
      navigation: {
        nextEl: ".categories-carousel2 .swiper-button-next",
        prevEl: ".categories-carousel2 .swiper-button-prev",
      },

      // Responsive breakpoints
      breakpoints: {
        // when window width is >= 320px
        0: {
          slidesPerView: 1,
        },
        576: {
          slidesPerView: 2,
        },
        992: {
          slidesPerView: 1,
        },
      },
    }
  );
  /*-----------------------------------
  # categories carousel3
  ------------------------------ */

  var categoriesCarousel3 = new Swiper(
    ".categories-carousel3 .swiper-container",
    {
      loop: true,
      speed: 800,
      slidesPerView: 1,
      spaceBetween: 0,
      pagination: false,
      navigation: {
        nextEl: ".categories-carousel3 .swiper-button-next",
        prevEl: ".categories-carousel3 .swiper-button-prev",
      },

      // Responsive breakpoints
      breakpoints: {
        // when window width is >= 320px
        0: {
          slidesPerView: 1,
        },
        576: {
          slidesPerView: 2,
        },
        992: {
          slidesPerView: 1,
        },
      },
    }
  );

  /*-----------------------------------
  # categories carousel4
  ------------------------------ */

  var categoriesCarousel3 = new Swiper(
    ".categories-carousel4 .swiper-container",
    {
      loop: true,
      speed: 800,
      slidesPerView: 1,
      spaceBetween: 0,
      pagination: false,
      navigation: {
        nextEl: ".categories-carousel4 .swiper-button-next",
        prevEl: ".categories-carousel4 .swiper-button-prev",
      },

      // Responsive breakpoints
      breakpoints: {
        // when window width is >= 320px
        0: {
          slidesPerView: 1,
        },
        576: {
          slidesPerView: 2,
        },
        992: {
          slidesPerView: 1,
        },
      },
    }
  );

  // swiper thumb gallery

  var galleryThumbs = new Swiper(
    ".product-modal-gallery-thumbs .swiper-container",
    {
      spaceBetween: 0,
      slidesPerView: 4,
      loop: false,
      watchSlidesVisibility: true,
      watchSlidesProgress: true,
    }
  );

  var galleryTop = new Swiper(".product-modal-gallery .swiper-container", {
    spaceBetween: 1,
    spaceBetween: 0,
    loop: false,
    navigation: {
      nextEl: ".product-modal-gallery .swiper-button-next",
      prevEl: ".product-modal-gallery .swiper-button-prev",
    },
    thumbs: {
      swiper: galleryThumbs,
    },
  });
  // swiper thumb gallery

  var Thumbs = new Swiper(".gallery-thumbs .swiper-container", {
    spaceBetween: 0,
    slidesPerView: 4,
    loop: false,
    watchSlidesVisibility: true,
    watchSlidesProgress: true,
  });

  var galleryTop2 = new Swiper(".gallery .swiper-container", {
    spaceBetween: 1,
    spaceBetween: 0,
    loop: false,
    navigation: {
      nextEl: ".gallery .swiper-button-next",
      prevEl: ".gallery .swiper-button-prev",
    },
    thumbs: {
      swiper: Thumbs,
    },
  });

  /*----------------------------------------*/
  /*  Countdown
/*----------------------------------------*/
  function makeTimer($endDate, $this, $format) {
    var today = new Date();
    var BigDay = new Date($endDate),
      msPerDay = 24 * 60 * 60 * 1000,
      timeLeft = BigDay.getTime() - today.getTime(),
      e_daysLeft = timeLeft / msPerDay,
      daysLeft = Math.floor(e_daysLeft),
      e_hrsLeft = (e_daysLeft - daysLeft) * 24,
      hrsLeft = Math.floor(e_hrsLeft),
      e_minsLeft = (e_hrsLeft - hrsLeft) * 60,
      minsLeft = Math.floor((e_hrsLeft - hrsLeft) * 60),
      e_secsLeft = (e_minsLeft - minsLeft) * 60,
      secsLeft = Math.floor((e_minsLeft - minsLeft) * 60);

    var yearsLeft = 0;
    var monthsLeft = 0;
    var weeksLeft = 0;

    if ($format != "short") {
      if (daysLeft > 365) {
        yearsLeft = Math.floor(daysLeft / 365);
        daysLeft = daysLeft % 365;
      }

      if (daysLeft > 30) {
        monthsLeft = Math.floor(daysLeft / 30);
        daysLeft = daysLeft % 30;
      }
      if (daysLeft > 7) {
        weeksLeft = Math.floor(daysLeft / 7);
        daysLeft = daysLeft % 7;
      }
    }

    var yearsLeft = yearsLeft < 10 ? "0" + yearsLeft : yearsLeft,
      monthsLeft = monthsLeft < 10 ? "0" + monthsLeft : monthsLeft,
      weeksLeft = weeksLeft < 10 ? "0" + weeksLeft : weeksLeft,
      daysLeft = daysLeft < 10 ? "0" + daysLeft : daysLeft,
      hrsLeft = hrsLeft < 10 ? "0" + hrsLeft : hrsLeft,
      minsLeft = minsLeft < 10 ? "0" + minsLeft : minsLeft,
      secsLeft = secsLeft < 10 ? "0" + secsLeft : secsLeft,
      yearsText = yearsLeft > 1 ? "years" : "year",
      monthsText = monthsLeft > 1 ? "months" : "month",
      weeksText = weeksLeft > 1 ? "weeks" : "week",
      daysText = daysLeft > 1 ? "days" : "day",
      hourText = hrsLeft > 1 ? "hrs" : "hr",
      minsText = minsLeft > 1 ? "mins" : "min",
      secText = secsLeft > 1 ? "secs" : "sec";

    var $markup = {
      wrapper: $this.find(".countdown__item"),
      year: $this.find(".yearsLeft"),
      month: $this.find(".monthsLeft"),
      week: $this.find(".weeksLeft"),
      day: $this.find(".daysLeft"),
      hour: $this.find(".hoursLeft"),
      minute: $this.find(".minsLeft"),
      second: $this.find(".secsLeft"),
      yearTxt: $this.find(".yearsText"),
      monthTxt: $this.find(".monthsText"),
      weekTxt: $this.find(".weeksText"),
      dayTxt: $this.find(".daysText"),
      hourTxt: $this.find(".hoursText"),
      minTxt: $this.find(".minsText"),
      secTxt: $this.find(".secsText"),
    };

    var elNumber = $markup.wrapper.length;
    $this.addClass("item-" + elNumber);
    $($markup.year).html(yearsLeft);
    $($markup.yearTxt).html(yearsText);
    $($markup.month).html(monthsLeft);
    $($markup.monthTxt).html(monthsText);
    $($markup.week).html(weeksLeft);
    $($markup.weekTxt).html(weeksText);
    $($markup.day).html(daysLeft);
    $($markup.dayTxt).html(daysText);
    $($markup.hour).html(hrsLeft);
    $($markup.hourTxt).html(hourText);
    $($markup.minute).html(minsLeft);
    $($markup.minTxt).html(minsText);
    $($markup.second).html(secsLeft);
    $($markup.secTxt).html(secText);
  }

  $(".countdown").each(function () {
    var $this = $(this);
    var $endDate = $(this).data("countdown");
    var $format = $(this).data("format");
    setInterval(function () {
      makeTimer($endDate, $this, $format);
    }, 0);
  });

  $("#slider-range").slider({
    range: true,
    min: 0,
    max: 100,
    values: [0, 100],
    slide: function slide(event, ui) {
      $("#amount").val("€" + ui.values[0] + " - €" + ui.values[1]);
    },
  });
  $("#amount").val(
    "€" +
      $("#slider-range").slider("values", 0) +
      " - €" +
      $("#slider-range").slider("values", 1)
  );

  /*--------------------------     
# counter 
-------------------------- */

  $(".count").each(function () {
    var count = $(this),
      input = count.find('input[type="number"]'),
      increament = count.find(".increment"),
      decreament = count.find(".decrement"),
      minValue = input.attr("min"),
      maxValue = input.attr("max");
    increament.on("click", function () {
      var oldValue = parseFloat(input.val());

      if (oldValue >= maxValue) {
        var newVal = oldValue;
      } else {
        var newVal = oldValue + 1;
      }

      count.find("input").val(newVal);
      count.find("input").trigger("change");
    });
    decreament.on("click", function () {
      var oldValue = parseFloat(input.val());

      if (oldValue <= minValue) {
        var newVal = oldValue;
      } else {
        var newVal = oldValue - 1;
      }

      count.find("input").val(newVal);
      count.find("input").trigger("change");
    });
  });

  /*-------------------------
    Create an account toggle
    --------------------------*/

  $(".checkout-toggle2").on("click", function () {
    $(".open-toggle2").slideToggle(1000);
  });
  $(".checkout-toggle").on("click", function () {
    $(".open-toggle").slideToggle(1000);
  });

  /*----------------------------
  # Mail Chip Ajax active
  ------------------------------*/
  var mCForm = $("#mc-form");
  mCForm.ajaxChimp({
    callback: mailchimpCallback,
    //Replace this with your own mailchimp post URL. Don't remove the "". Just paste the url inside "".
    url:
      "http://devitems.us11.list-manage.com/subscribe/post?u=6bbb9b6f5827bd842d9640c82&id=05d85f18ef",
  });
  function mailchimpCallback(resp) {
    if (resp.result === "success") {
      alert(resp.msg);
    } else if (resp.result === "error") {
      alert(resp.msg);
    }
    return false;
  }

  /*------------------------------------
  # Contact Form Validation Settings
  --------------------------------------*/
  var contactForm = $("#contactForm");
  if ($("#contactForm").length) {
    contactForm.validate({
      onfocusout: false,
      onkeyup: false,
      rules: {
        name: "required",
        number: "required",
        email: {
          required: true,
          email: true,
        },
      },
      errorPlacement: function (error, element) {
        error.insertBefore(element);
      },
      messages: {
        name: "Enter your name?",
        email: {
          required: "Enter your email?",
          email: "Please, enter a valid email",
        },
      },

      highlight: function (element) {
        $(element).text("").addClass("error");
      },

      success: function (element) {
        element.text("").addClass("valid");
      },
    });
  }

  /*----------------------------
  # Contact Form Script
  -------------------------------*/

  if ($("#formSubmit").length) {
    var formSubmit = $("#formSubmit");
    CTForm.submit(function () {
      // submit the form
      if ($(this).valid()) {
        formSubmit.button("loading");
        var action = $(this).attr("action");
        $.ajax({
          url: action,
          type: "POST",
          data: {
            contactname: $("#name").val(),
            contactemail: $("#email").val(),
            contactmessage: $("#massage").val(),
          },
          success: function () {
            formSubmit.button("reset");
            formSubmit.button("complete");
          },
          error: function () {
            formSubmit.button("reset");
            formSubmit.button("error");
          },
        });
        // return false to prevent normal browser submit and page navigation
      } else {
        formSubmit.button("reset");
      }
      return false;
    });
  }

  /*----------------------------
  #  Copy Right Year Update
  -------------------------------*/

  $("#currentYear").text(new Date().getFullYear());
  /*----------------------------
  #  scrollUp
  -------------------------------*/
  $.scrollUp({
    scrollName: "scrollUp",
    // Element ID
    scrollDistance: 400,
    // Distance from top/bottom before showing element (px)
    scrollFrom: "top",
    // 'top' or 'bottom'
    scrollSpeed: 400,
    // Speed back to top (ms)
    easingType: "linear",
    // Scroll to top easing (see http://easings.net/)
    animation: "fade",
    // Fade, slide, none
    animationSpeed: 200,
    // Animation speed (ms)
    scrollTrigger: false,
    // Set a custom triggering element. Can be an HTML string or jQuery object
    scrollTarget: false,
    // Set a custom target element for scrolling to. Can be element or number
    scrollText: '<i class="las la-arrow-up"></i>',
    // Text for element, can contain HTML
    scrollTitle: false,
    // Set a custom <a> title if required.
    scrollImg: false,
    // Set true to use image
    activeOverlay: false,
    // Set CSS color to display scrollUp active point, e.g '#00FFFF'
    zIndex: 214, // Z-Index for the overlay
  });
})(jQuery);
