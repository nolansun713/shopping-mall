(function ($) {
    "use strict";

    /*----------------------------------------
      Sticky Menu Activation
    ------------------------------------------*/

    $(window).on('scroll', function () {
      if ($(this).scrollTop() > 150) {
        $('.header-sticky').addClass('sticky');
      } else {
        $('.header-sticky').removeClass('sticky');
      }
    });

    /*---------------------
        Header Search Action
    --------------------- */

    $(".action-execute").on('click', function() {
      // If the clicked element has the active class, remove the active class from EVERY .nav-link>.state element
      if ($(".action-execute, .header-search-form").hasClass("visible-execute")) {
        $(".action-execute, .header-search-form").removeClass("visible-execute");
      }
      // Else, the element doesn't have the active class, so we remove it from every element before applying it to the element that was clicked
      else {
        $(".action-execute, .header-search-form").removeClass("visible-execute");
        $(".action-execute, .header-search-form").addClass("visible-execute");
      }
    });

    /*---------------------
        Header Cart Toggle
    --------------------- */

    $(".cart-visible").on('click', function(){
      $(".header-cart-content").slideToggle("slow");
    });

    /*-----------------------------------------
      Off Canvas Mobile Menu
    -------------------------------------------*/

    $(".header-action-btn-menu").on('click', function () {
      $("body").addClass('fix');
      $(".mobile-menu-wrapper").addClass('open');
    });

    $(".offcanvas-btn-close,.offcanvas-overlay").on('click', function () {
      $("body").removeClass('fix');
      $(".mobile-menu-wrapper").removeClass('open');
    });

    /*----------------------------------------*/
    /* Toggle Function Active
    /*----------------------------------------*/

    // showlogin toggle
    $('#showlogin').on('click', function () {
      $('#checkout-login').slideToggle(500);
    });
    // showlogin toggle
    $('#showcoupon').on('click', function () {
      $('#checkout_coupon').slideToggle(500);
    });
    // showlogin toggle
    $('#cbox').on('click', function () {
      $('#cbox-info').slideToggle(500);
    });

    // Ship box toggle
    $('#ship-box').on('click', function () {
      $('#ship-box-info').slideToggle(1000);
    });
    
    /*----------------------------------------
      Responsive Mobile Menu
    ------------------------------------------*/

    //Variables
    var $offCanvasNav = $('.mobile-menu, .category-menu'),
    $offCanvasNavSubMenu = $offCanvasNav.find('.dropdown');

    //Close Off Canvas Sub Menu
    $offCanvasNavSubMenu.slideUp();

    //Category Sub Menu Toggle
    $offCanvasNav.on('click', 'li a, li .menu-expand', function(e) {
    var $this = $(this);
      if ( ($this.parent().attr('class').match(/\b(menu-item-has-children|has-children|has-sub-menu)\b/)) && ($this.attr('href') === '#' || $this.hasClass('menu-expand')) ) {
        e.preventDefault();
        if ($this.siblings('ul:visible').length){
          $this.parent('li').removeClass('active');
          $this.siblings('ul').slideUp();
        } else {
          $this.parent('li').addClass('active');
          $this.closest('li').siblings('li').removeClass('active').find('li').removeClass('active');
          $this.closest('li').siblings('li').find('ul:visible').slideUp();
          $this.siblings('ul').slideDown();
        }
      }
    });

    /*----------------------------------------
	   Slider Activation
    ------------------------------------------*/

    /* Hero Slider Activation */
    var swiper = new Swiper('.hero-slider.swiper-container', {
      loop: true,
      speed: 1150,
      spaceBetween: 30,
      slidesPerView: 1,
      effect: 'fade',

      // Navigation arrows
      navigation: {
          nextEl: '.hero-slider .home-slider-next',
          prevEl: '.hero-slider .home-slider-prev'
      },
      pagination: {
          el: '.hero-slider .swiper-pagination',
          type: 'bullets',
          clickable: 'true'
        },
    });

    /* Product Deal Activation */
    var swiper = new Swiper('.product-deal-carousel .swiper-container', {
      loop: true,
      slidesPerView: 1,
      spaceBetween: 20,
      pagination: true,
      navigation: true,
      slideVisibleClass: 'swiper-slide-visible',
      watchSlidesVisibility:true,



      
    });

    // Testimonial Carousel
    var galleryThumbs = new Swiper('.testimonial-gallery-top', {
      slidesPerView: 1,
      loop: true,
      effect: 'fade',
      fadeEffect: {
        crossFade: true
      },
      grabCursor: false,
      centeredSlides: true,
    });
    var galleryTop = new Swiper('.testimonial-gallery-thumbs', {
      loop: true,
      effect: 'coverflow',      
      coverflowEffect: {
        rotate: 0,
        stretch: 0,
        depth: 50,
        modifier: 6,
        slideShadows : false,
	    },
      pagination: {
        el: '.swiper-pagination',
        clickable: true,
      },
      thumbs: {
        swiper: galleryThumbs,
      }
    });

    // // Single Product Carousel
    // var productgalleryThumbs = new Swiper('.product-gallery-thumbs', {
    //   spaceBetween: 10,
    //   slidesPerView: 3,
    //   // loop: false,
    //   freeMode: true,
    //   watchSlidesVisibility: true,
    //   watchSlidesProgress: true,
    // });
    // var galleryTop = new Swiper('.product-gallery-top', {
    //   spaceBetween: 10,
    //   loop: true,
    //   navigation: {
    //     nextEl: '.swiper-button-next',
    //     prevEl: '.swiper-button-prev',
    //   },
    //   thumbs: {
    //     swiper: productgalleryThumbs
    //   }
    // });


    //商品详情页，加入购物车的数量的增加
    $('#btn-add-product').click(function(){
      var sums=  parseInt($('#nums').val());
        $('#nums').val(sums+1);
    })
    $('#btn-reduce-product').click(function(){
        var sums = parseInt($('#nums').val());
        if(sums<=1){
            sums=1;
            $('#nums').val(sums);
        }else {
            sums=sums-1;
            $('#nums').val(sums);
        }

    })






    /* Pruduct Carousel Activation */
    var productCarousel = new Swiper('.product-carousel .swiper-container', {
      loop: true,
      slidesPerView: 4,
      spaceBetween: 20,
      slideVisibleClass: 'swiper-slide-visible',
      watchSlidesVisibility:true,
      observer: true,
      observeParents: true,

      pagination: {
        el: '.product-carousel .swiper-pagination',
        type: 'bullets',
        clickable: 'true'
      },

      // Navigation arrows
      navigation: {
          nextEl: '.product-carousel .swiper-button-next',
          prevEl: '.product-carousel .swiper-button-prev',
      },		
      // Responsive breakpoints
      breakpoints: {
        // when window width is >= 320px
        320: {
        slidesPerView: 1,
        spaceBetween: 10
        },
        // when window width is >= 480px
        480: {
        slidesPerView: 2,
        spaceBetween: 20
        },
        // when window width is >= 768px
        768: {
        slidesPerView: 3,
        spaceBetween: 20
        },
        // when window width is >= 992px
        992: {
        slidesPerView: 3,
        spaceBetween: 20
        },
        // when window width is >= 1200px
        1200: {
        slidesPerView: 4,
        spaceBetween: 20
        }
      }
    });

    /* Modal Product Carousel Activation */
    var productCarousel = new Swiper('.modal-product-carousel .swiper-container', {
      loop: true,
      slidesPerView: 1,
      spaceBetween: 0,
      pagination: false,
      navigation: true,
      observer: true,
      observeParents: true,


    });

    /*----------------------------------------*/
    /*  Shop Grid Activation
    /*----------------------------------------*/

    $('.shop_toolbar_btn > button').on('click', function (e) {
    
      e.preventDefault();
      
      $('.shop_toolbar_btn > button').removeClass('active');
      $(this).addClass('active');
      
      var parentsDiv = $('.shop_wrapper');
      var viewMode = $(this).data('role');
      
      
      parentsDiv.removeClass('grid_3 grid_4 grid_5 grid_list').addClass(viewMode);

      if(viewMode == 'grid_3'){
        parentsDiv.children().addClass('col-lg-4 col-md-4 col-sm-6').removeClass('col-lg-3 col-cust-5 col-12');
        
      }

      if(viewMode == 'grid_4'){
        parentsDiv.children().addClass('col-xl-3 col-lg-4 col-md-4 col-sm-6').removeClass('col-lg-4 col-cust-5 col-12');
      }
      
      if(viewMode == 'grid_list'){
        parentsDiv.children().addClass('col-12').removeClass('col-xl-3 col-lg-3 col-lg-4 col-md-6 col-md-4 col-sm-6 col-cust-5');
      }
        
    });

    /*----------------------------------------*/
    /*  Countdown
    /*----------------------------------------*/

    $('[data-countdown]').each(function() {
      var $this = $(this), finalDate = $(this).data('countdown');
      $this.countdown(finalDate, function(event) {
        $this.html(event.strftime('<div class="single-countdown"><span class="single-countdown_time">%D</span><span class="single-countdown_text">Days</span></div><div class="single-countdown"><span class="single-countdown_time">%H</span><span class="single-countdown_text">Hours</span></div><div class="single-countdown"><span class="single-countdown_time">%M</span><span class="single-countdown_text">Mins</span></div><div class="single-countdown"><span class="single-countdown_time">%S</span><span class="single-countdown_text">Secs</span></div>'));
      });
    });

    	/*----------------------------------------*/
    /*  Cart Plus Minus Button
    /*----------------------------------------*/

    // /*按加号数量增*/
    // function addNum(rid) {
    //     var n = parseInt($("#goodsCount"+rid).val());
    //     $("#goodsCount"+rid).val(n + 1);
    //     calcRow(rid);
    // }
    // /*按减号数量减*/
    // function reduceNum(rid) {
    //     var n = parseInt($("#goodsCount"+rid).val());
    //     if (n == 0)
    //         return;
    //     $("#goodsCount"+rid).val(n - 1);
    //     calcRow(rid);
    // }
    // function calcRow(rid) {
    //     //取单价
    //     var vprice = parseFloat($("#goodsPrice"+rid).html());
    //     //取数量
    //     var vnum = parseFloat($("#goodsCount"+rid).val());
    //     //小计金额
    //     var vtotal = vprice * vnum;
    //     //赋值
    //     $("#goodsCast"+rid).html("¥" + vtotal);
    // }

    // $('.cart-plus-minus').append(
    //   '<div class="dec qtybutton">-</div><div class="inc qtybutton">+</div>'
    // );
    // $('.qtybutton').on('click', function () {
    //   var $button = $(this);
    //   var oldValue = $button.parseInt().find('input').val();
    //   if ($button.hasClass('inc')) {
    //     var newVal = parseInt(oldValue) + 1;
    //   } else {
    //     // Don't allow decrementing below zero
    //     if (oldValue > 1) {
    //       var newVal = parseInt(oldValue) - 1;
    //     } else {
    //       newVal = 1;
    //     }
    //   }
    //   $button.parent().find('input').val(newVal);
    // });

    /*----------------------------------------*/
    /*  Lightgallery Active
    /*----------------------------------------*/

    $(".popup-gallery").lightGallery({
      pager: false, // Enable/Disable pager
      thumbnail: false, // Enable thumbnails for the gallery
      fullScreen: true, // Enable/Disable fullscreen mode
      zoom: true, // Enable/Disable zoom option
      rotateLeft: true, // Enable/Disable Rotate Left
      rotateRight: true, // Enable/Disable Rotate Right
      });

    /*---------------------------------
      MailChimp
      -----------------------------------*/
      function mailChimpResponse(resp) {
          if (resp.result === 'success') {
              $('.mailchimp-success').html('' + resp.msg).fadeIn(900);
              $('.mailchimp-error').fadeOut(400);
          } else if (resp.result === 'error') {
              $('.mailchimp-error').html('' + resp.msg).fadeIn(900);
          }
    }
	  /*-------------------------
        Ajax Contact Form 
    ---------------------------*/
    $(function() {

        // Get the form.
        var form = $('#contact-form');

        // Get the messages div.
        var formMessages = $('.form-messege');

        // Set up an event listener for the contact form.
    });
    /*----------------------------------------*/
	  /*  Scroll to top
	  /*----------------------------------------*/
    function scrollToTop() {
      var $scrollUp = $('#scroll-top'),
          $lastScrollTop = 0,
          $window = $(window);

      $window.on('scroll', function () {
          var st = $(this).scrollTop();
          if (st > $lastScrollTop) {
              $scrollUp.removeClass('show');
          } else {
              if ($window.scrollTop() > 200) {
                  $scrollUp.addClass('show');
              } else {
                  $scrollUp.removeClass('show');
              }
          }
          $lastScrollTop = st;
      });

      $scrollUp.on('click', function (evt) {
          $('html, body').animate({scrollTop: 0}, 600);
          evt.preventDefault();
      });
    }
    scrollToTop();

    /*----------------------------------------*/
	  /*  When document is loading, do
	  /*----------------------------------------*/
    var varWindow = $(window);
    varWindow.on('load', function() {
      AOS.init({
        once: true,
      });
    });
    
})(jQuery);







