<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@page import="java.io.InputStream"%>
<%@page import="org.apache.ibatis.io.Resources"%>
<%@page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="java.io.IOException"%>
<%@page import="org.apache.ibatis.session.SqlSessionFactoryBuilder"%>
<%@page import="util.Info"%>
<%@page import="dao.CommDAO"%>
<%@page import="util.PageManager"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="info" uri="http://www.javayou.com/dev/jsp/jstl/mail" %>

<!DOCTYPE html>
<html lang="en">
<!-- head start -->

<head>

    <!-- Required meta tags -->
    <meta name="description" content="Responsive Bootstrap5 Template">
    <meta name="keywords" content="Grano Organic & Food Responsive Bootstrap5 Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Responsive Bootstrap5 Template</title>

    <link rel="shortcut icon" type="image/x-icon" href="/itivtmgr/frontfiles/images/favicon.ico">
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/vendor/ionicons.css" />
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/vendor/line-awesome.min.css" />
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/plugins/animate.min.css" />
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/plugins/swiper-bundle.min.css" />
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/plugins/jquery-ui.min.css" />
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/style.css" />

</head>
<!-- head end -->

<body>

<jsp:include page="/itivtmgr/tofrtop.do"/>


<!-- product tab slider start -->
<section class="product-tab-section">

    <div class="container">
        <div class="row align-items-center">
            <div class="col-xl-5 col-md-4 col-12">
                <div class="section-title">
                    <h2 class="title">最新企业</h2>
                </div>
            </div>
            <div class="col-xl-7 col-md-8 col-12">

                <ul class="nav nav-pills product-tab-nav" id="pills-tab" role="tablist"  >


                    <li class="nav-item" role="presentation">
                        <font color="red">最火 :</font>
                    </li>

                    <c:forEach items="${listrmqy}" var="sdata" varStatus="sta">
                    <li class="nav-item" role="presentation">
                        <a href="/itivtmgr/sysuser/toqyxxx.do?id=${sdata.id}">${sdata.tname}（点击数  ${sdata.clicknums}）</a>
                    </li>
                    </c:forEach>


                </ul>
            </div>


        </div>
        <div class="row">
            <div class="col-12">
                <div class="tab-content" id="pills-tabContent">
                    <div class="tab-pane fade show active" id="pills-home">
                        <div class="tab-carousel">
                            <div class="swiper-container">
                                <div class="swiper-wrapper">


                                    <!-- swiper-slide start -->
                                    <div class="tab-carousel-item swiper-slide">

                                        <c:forEach items="${listqy1}" var="sdata" varStatus="sta">
                                        <!-- product-tab-card-list start -->
                                        <div class="product-tab-card-list">
                                            <!-- product-tab-card start -->
                                            <div class="product-tab-card">
                                                <div class="product-tab-thumb-nail">
                                                    <a href="/itivtmgr/sysuser/toqyxxx.do?id=${sdata.id}">
                                                        <img class="product-tab-image" style="height: 140px;width: 160px" src="/itivtmgr/upfile/${sdata.filename}" alt="image_not_found" />
                                                    </a>
                                                </div>
                                                <div class="product-tab-content">
                                                    <h4 class="product-sub-title" style="font-size: 14px">注册时间 : ${sdata.savetime}</h4>
                                                    <h3 class="product-title"><a href="/itivtmgr/sysuser/toqyxxx.do?id=${sdata.id}" style="font-weight: 700">${sdata.tname}</a></h3>
                                                    <div class="product-price-wrapp">
                                                        <span class="product-price-on-sale" style="color: black;font-weight: 500;font-size: 14px">行业 : ${sdata.mainbuss}</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- product-tab-card end -->
                                        </div>
                                        <!-- product-tab-card-list end -->
                                        </c:forEach>

                                    </div>
                                    <!-- swiper-slide end-->

                                    <!-- swiper-slide start -->
                                    <div class="tab-carousel-item swiper-slide">

                                        <c:forEach items="${listqy2}" var="sdata" varStatus="sta">
                                            <!-- product-tab-card-list start -->
                                            <div class="product-tab-card-list">
                                                <!-- product-tab-card start -->
                                                <div class="product-tab-card">
                                                    <div class="product-tab-thumb-nail">
                                                        <a href="/itivtmgr/sysuser/toqyxxx.do?id=${sdata.id}">
                                                            <img class="product-tab-image" style="height: 140px;width: 160px" src="/itivtmgr/upfile/${sdata.filename}" alt="image_not_found" />
                                                        </a>
                                                    </div>
                                                    <div class="product-tab-content">
                                                        <h4 class="product-sub-title" style="font-size: 14px">注册时间 : ${sdata.savetime}</h4>
                                                        <h3 class="product-title"><a href="/itivtmgr/sysuser/toqyxxx.do?id=${sdata.id}" style="font-weight: 700">${sdata.tname}</a></h3>
                                                        <div class="product-price-wrapp">
                                                            <span class="product-price-on-sale" style="color: black;font-weight: 500;font-size: 14px">行业 : ${sdata.mainbuss}</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- product-tab-card end -->
                                            </div>
                                            <!-- product-tab-card-list end -->
                                        </c:forEach>

                                    </div>
                                    <!-- swiper-slide end-->

                                    <!-- swiper-slide start -->
                                    <div class="tab-carousel-item swiper-slide">

                                        <c:forEach items="${listqy3}" var="sdata" varStatus="sta">
                                            <!-- product-tab-card-list start -->
                                            <div class="product-tab-card-list">
                                                <!-- product-tab-card start -->
                                                <div class="product-tab-card">
                                                    <div class="product-tab-thumb-nail">
                                                        <a href="/itivtmgr/sysuser/toqyxxx.do?id=${sdata.id}">
                                                            <img class="product-tab-image" style="height: 140px;width: 160px" src="/itivtmgr/upfile/${sdata.filename}" alt="image_not_found" />
                                                        </a>
                                                    </div>
                                                    <div class="product-tab-content">
                                                        <h4 class="product-sub-title" style="font-size: 14px">注册时间 : ${sdata.savetime}</h4>
                                                        <h3 class="product-title"><a href="/itivtmgr/sysuser/toqyxxx.do?id=${sdata.id}" style="font-weight: 700">${sdata.tname}</a></h3>
                                                        <div class="product-price-wrapp">
                                                            <span class="product-price-on-sale" style="color: black;font-weight: 500;font-size: 14px">行业 : ${sdata.mainbuss}</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- product-tab-card end -->
                                            </div>
                                            <!-- product-tab-card-list end -->
                                        </c:forEach>

                                    </div>
                                    <!-- swiper-slide end-->


                                </div>
                            </div>


                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- product tab slider end -->

<!-- main-banner-section start -->

<div class="main-banner-section section-mt section-mb">
</div>

<!-- main-banner-section end -->


<!-- categories section start -->
<section class="categories-section section-mb">
    <div class="container">
        <div class="row">
            <div class=" ">
                <div class="row mb-n7">



                    <%--新闻1开始--%>
                    <div class="col-lg-4 mb-7">
                        <div class="section-title categories">
                            <h2 class="title">${typelist1[0].tname}</h2>
                        </div>
                        <div class="categories-carousel">
                            <div class="swiper-container">
                                <div class="swiper-wrapper">
                                    <!-- swiper-slide start -->
                                    <div class="categories-carousel-item swiper-slide" style="padding-left: 3px">
                                        <c:forEach items="${list11}" var="sdata" varStatus="sta">
                                        <!-- categories card-list start -->
                                        <div class="categories-card-list" style="padding-bottom: 10px">
                                            <!-- categories-card start -->
                                            <div class="categories-card">

                                                <div>
                                                    <a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}">
                                                        <img class="categories-image" src="/itivtmgr/upfile/${sdata.filename}" style="width: 180px;height: 120px">
                                                        <img class="categories-image-hover-style" src="/itivtmgr/upfile/${sdata.filename}" style="width: 180px;height: 120px">
                                                    </a>
                                                </div>
                                                <div   style="margin-left: 15px">
                                                    <h4 class="product-sub-title"><a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}" style="font-size: 15px">${sdata.savetime}</a></h4>
                                                    <h3 class="product-title"><a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}" style="font-size: 15px">${sdata.ntitle}</a></h3>
                                                    <div class="product-price-wrapp">
                                                        <span  style="font-size: 15px">

                                                            发布人 : ${sdata.uname}
                                                            &nbsp;&nbsp;
                                                            点击 : ${sdata.clicknums}

                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- categories-card end -->
                                        </div>
                                        <!-- categories card-list end -->
                                        </c:forEach>

                                    </div>
                                    <!-- swiper-slide end-->

                                    <!-- swiper-slide start -->
                                    <div class="categories-carousel-item swiper-slide" style="padding-left: 3px">
                                        <c:forEach items="${list12}" var="sdata" varStatus="sta">
                                            <!-- categories card-list start -->
                                            <div class="categories-card-list" style="padding-bottom: 10px">
                                                <!-- categories-card start -->
                                                <div class="categories-card">

                                                    <div>
                                                        <a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}">
                                                            <img class="categories-image" src="/itivtmgr/upfile/${sdata.filename}" style="width: 180px;height: 120px">
                                                            <img class="categories-image-hover-style" src="/itivtmgr/upfile/${sdata.filename}" style="width: 180px;height: 120px">
                                                        </a>
                                                    </div>
                                                    <div  style="margin-left: 15px" >
                                                        <h4 class="product-sub-title"><a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}" style="font-size: 15px">${sdata.savetime}</a></h4>
                                                        <h3 class="product-title"><a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}" style="font-size: 15px">${sdata.ntitle}</a></h3>
                                                        <div class="product-price-wrapp" >
                                                        <span   style="font-size: 15px">

                                                            发布人 : ${sdata.uname}
                                                            &nbsp;&nbsp;
                                                            点击 : ${sdata.clicknums}

                                                        </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- categories-card end -->
                                            </div>
                                            <!-- categories card-list end -->
                                        </c:forEach>

                                    </div>
                                    <!-- swiper-slide end-->
                                </div>

                            </div>

                            <!-- swiper navigation -->
                            <div class="swiper-button-prev common-swiper-button-prev">
                                <i class="las la-angle-left"></i>
                            </div>
                            <div class="swiper-button-next common-swiper-button-next" id="slide_1">
                                <i class="las la-angle-right"></i>
                            </div>
                        </div>
                    </div>
                    <%--新闻1结束--%>


                    <%--新闻2开始--%>
                    <div class="col-lg-4 mb-7">
                        <div class="section-title categories">
                            <h2 class="title">${typelist1[1].tname}</h2>
                        </div>
                        <div class="categories-carousel2">
                            <div class="swiper-container">
                                <div class="swiper-wrapper">


                                    <!-- swiper-slide start -->
                                    <div class="categories-carousel-item swiper-slide" style="padding-left: 3px">
                                        <c:forEach items="${list21}" var="sdata" varStatus="sta">
                                            <!-- categories card-list start -->
                                            <div class="categories-card-list" style="padding-bottom: 10px">
                                                <!-- categories-card start -->
                                                <div class="categories-card">

                                                    <div  >
                                                        <a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}">
                                                            <img class="categories-image" src="/itivtmgr/upfile/${sdata.filename}" style="width: 180px;height: 120px">
                                                            <img class="categories-image-hover-style" src="/itivtmgr/upfile/${sdata.filename}" style="width: 180px;height: 120px">
                                                        </a>
                                                    </div>
                                                    <div class=" " style="margin-left: 15px" >
                                                        <h4 class="product-sub-title"><a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}" style="font-size: 15px">${sdata.savetime}</a></h4>
                                                        <h3 class="product-title"><a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}" style="font-size: 15px">${sdata.ntitle}</a></h3>
                                                        <div class="product-price-wrapp" >
                                                    <span class=" " style="font-size: 15px">

                                                        发布人 : ${sdata.uname}
                                                        &nbsp;&nbsp;
                                                        点击 : ${sdata.clicknums}

                                                    </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- categories-card end -->
                                            </div>
                                            <!-- categories card-list end -->
                                        </c:forEach>

                                    </div>
                                    <!-- swiper-slide end-->

                                    <!-- swiper-slide start -->
                                    <div class="categories-carousel-item swiper-slide" style="padding-left: 3px">
                                        <c:forEach items="${list22}" var="sdata" varStatus="sta">
                                            <!-- categories card-list start -->
                                            <div class="categories-card-list" style="padding-bottom: 10px">
                                                <!-- categories-card start -->
                                                <div class="categories-card">

                                                    <div  >
                                                        <a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}">
                                                            <img class="categories-image" src="/itivtmgr/upfile/${sdata.filename}" style="width: 180px;height: 120px">
                                                            <img class="categories-image-hover-style" src="/itivtmgr/upfile/${sdata.filename}" style="width: 180px;height: 120px">
                                                        </a>
                                                    </div>
                                                    <div class=" " style="margin-left: 15px" >
                                                        <h4 class="product-sub-title"><a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}" style="font-size: 15px">${sdata.savetime}</a></h4>
                                                        <h3 class="product-title"><a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}" style="font-size: 15px">${sdata.ntitle}</a></h3>
                                                        <div class="product-price-wrapp" >
                                                    <span class=" " style="font-size: 15px">

                                                        发布人 : ${sdata.uname}
                                                        &nbsp;&nbsp;
                                                        点击 : ${sdata.clicknums}

                                                    </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- categories-card end -->
                                            </div>
                                            <!-- categories card-list end -->
                                        </c:forEach>

                                    </div>
                                    <!-- swiper-slide end-->

                                </div>


                            </div>

                            <!-- swiper navigation -->
                            <div class="swiper-button-prev common-swiper-button-prev">
                                <i class="las la-angle-left"></i>
                            </div>
                            <div class="swiper-button-next common-swiper-button-next" id="slide_2">
                                <i class="las la-angle-right"></i>
                            </div>
                        </div>
                    </div>
                    <%--新闻2结束--%>


                    <%--新闻3开始--%>
                    <div class="col-lg-4 mb-7">
                        <div class="section-title categories">
                            <h2 class="title">${typelist1[2].tname}</h2>
                        </div>
                        <div class="categories-carousel3">
                            <div class="swiper-container">
                                <div class="swiper-wrapper">


                                    <!-- swiper-slide start -->
                                    <div class="categories-carousel-item swiper-slide" style="padding-left: 3px">
                                        <c:forEach items="${list31}" var="sdata" varStatus="sta">
                                            <!-- categories card-list start -->
                                            <div class="categories-card-list" style="padding-bottom: 10px">
                                                <!-- categories-card start -->
                                                <div class="categories-card">

                                                    <div  >
                                                        <a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}">
                                                            <img class="categories-image" src="/itivtmgr/upfile/${sdata.filename}" style="width: 180px;height: 120px">
                                                            <img class="categories-image-hover-style" src="/itivtmgr/upfile/${sdata.filename}" style="width: 180px;height: 120px">
                                                        </a>
                                                    </div>
                                                    <div class=" " style="margin-left: 15px" >
                                                        <h4 class="product-sub-title"><a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}" style="font-size: 15px">${sdata.savetime}</a></h4>
                                                        <h3 class="product-title"><a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}" style="font-size: 15px">${sdata.ntitle}</a></h3>
                                                        <div class="product-price-wrapp" >
                                                    <span class=" " style="font-size: 15px">

                                                        发布人 : ${sdata.uname}
                                                        &nbsp;&nbsp;
                                                        点击 : ${sdata.clicknums}

                                                    </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- categories-card end -->
                                            </div>
                                            <!-- categories card-list end -->
                                        </c:forEach>

                                    </div>
                                    <!-- swiper-slide end-->

                                    <!-- swiper-slide start -->
                                    <div class="categories-carousel-item swiper-slide" style="padding-left: 3px">
                                        <c:forEach items="${list32}" var="sdata" varStatus="sta">
                                            <!-- categories card-list start -->
                                            <div class="categories-card-list" style="padding-bottom: 10px">
                                                <!-- categories-card start -->
                                                <div class="categories-card">

                                                    <div  >
                                                        <a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}">
                                                            <img class="categories-image" src="/itivtmgr/upfile/${sdata.filename}" style="width: 180px;height: 120px">
                                                            <img class="categories-image-hover-style" src="/itivtmgr/upfile/${sdata.filename}" style="width: 180px;height: 120px">
                                                        </a>
                                                    </div>
                                                    <div class=" " style="margin-left: 15px" >
                                                        <h4 class="product-sub-title"><a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}" style="font-size: 15px">${sdata.savetime}</a></h4>
                                                        <h3 class="product-title"><a href="/itivtmgr/zresources/toznzxx.do?id=${sdata.id}" style="font-size: 15px">${sdata.ntitle}</a></h3>
                                                        <div class="product-price-wrapp" >
                                                    <span class=" " style="font-size: 15px">

                                                        发布人 : ${sdata.uname}
                                                        &nbsp;&nbsp;
                                                        点击 : ${sdata.clicknums}

                                                    </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- categories-card end -->
                                            </div>
                                            <!-- categories card-list end -->
                                        </c:forEach>

                                    </div>
                                    <!-- swiper-slide end-->


                                </div>


                            </div>

                            <!-- swiper navigation -->
                            <div class="swiper-button-prev common-swiper-button-prev" >
                                <i class="las la-angle-left"></i>
                            </div>
                            <div class="swiper-button-next common-swiper-button-next" id="slide_3">
                                <i class="las la-angle-right"></i>
                            </div>
                        </div>
                    </div>
                    <%--新闻3结束--%>







                </div>
            </div>

        </div>
    </div>
</section>
<!-- categories section end -->

<!-- main content end -->

<jsp:include page="/itivtmgr/tofrfoot.do" />

</body>
</html>

<script language="JavaScript">
    function   slideinit(){
        document.getElementById("slide_1").click();
        document.getElementById("slide_2").click();
        document.getElementById("slide_3").click();
        setTimeout(slideinit,4000);
    }
    setTimeout(slideinit,4000);
</script>    

<script language="JavaScript">
    var indexhref = window.location.href;
    if(indexhref.indexOf("index")==-1){
        window.location.replace("/itivtmgr/itivtmgr/index.do")
    }
</script>