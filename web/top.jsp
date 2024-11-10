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
    <meta charset="GB2312">
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

    <style type="text/css">
        select{height: 29px}
    </style>

</head>
<!-- head end -->

<body>
<!-- header section start -->


<header>

    <!-- header top end -->
    <div id="active-sticky2" class="header-space bg-white">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-6 col-sm-4 col-lg-7 col-xl-8">
                    <div class="d-flex">
                        <div class="flex-shrink-0">
                            <div class="d-flex align-items-center vertical-menu">

                                <a class="logo" href="/itivtmgr/itivtmgr/index.do">
                                    <%--<img src="/itivtmgr/frontfiles/images/logo/logo-dark.jpg" alt="image_not_found" />--%>
                                    <h1 style="color: black;font-size: 26px">IT人才招聘系统</h1>
                                </a>

                                <!-- menu content -->

                            </div>
                        </div>
                        <div class="flex-grow-1 d-none d-lg-block" style="margin-left: 50px;">
                            <form class="search-form position-relative" method="post" action="/itivtmgr/recruiting/zpxxs.do" >
                                <div class="input-group">
                                    <input class="form-control" name="rtitle_top" type="text" placeholder="快速搜索职位 . . ." />
                                    <div class="input-group-text">
                                        <button class="search-btn btn-success">
                                            <i class="ion-ios-search-strong"></i>
                                        </button>
                                    </div>
                                </div>

                                <select class="form-select d-none d-xl-block" id="autoSizingSelect" name="btype_top">
                                    <option value="">行业大类</option>
                                    <c:forEach items="${listtype}" var="sdata" varStatus="sta">
                                    <option value="${sdata.datashowname}">${sdata.datashowname}</option>
                                    </c:forEach>
                                </select>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-6 col-sm-8 col-lg-5 col-xl-4" >

                    <ul class="quick-links" >
                  <c:if test="${!empty admin}" >
                        <!-- quick-link-item -->
                        <li class="quick-link-item d-none d-md-inline-flex">
                                <span class="quick-link-icon flex-shrink-0">
                              <a href="#" class="quick-link">
                                <%--<i class="las la-user-circle"></i>--%>
                                  <img src="/itivtmgr/upfile/${admin.filename}" style="border-radius: 50%" height="49" />

                              </a>
                            </span>
                            &nbsp;&nbsp;
                        <span class="flex-grow-1" style="width: 250px" >
                            <a href="/itivtmgr/itivtmgr/index.do?zx=zx" class="my-account"> ${admin.uname} ( ${admin.tname} )  &nbsp;<strong>退出系统</strong></a>
                            <div style="height: 12px" ></div>
                            <a href="/itivtmgr/itivtmgr/toadminindex.do" target="_blank" class="my-account">${admin.utype}   ，  <font color="red">去服务中心</font> </a>
                         </span>
                        </li>
                        <!-- quick-link-item end -->
                        </c:if>

                        <c:if test="${empty admin}" >
                            <!-- quick-link-item -->
                            <li class="quick-link-item d-inline-flex">
                            <span class="quick-link-icon flex-shrink-0">
                              <a href="#" class="quick-link">
                                <i class="las la-user-circle"  ></i>
                              </a>
                            </span>
                             <span class="flex-grow-1" style="width: 250px" >
                              欢迎，您还没有登录，<a href="/itivtmgr/itivtmgr/tologin.do"  class="sign-in">去登录</a>
                                 <div style="height: 11px" ></div>
                              注册为：
                                 <a href="/itivtmgr/sysuser/tohysysuserregedit.do" class="sign-in">会员</a>
                                 ，
                                 <a href="/itivtmgr/sysuser/toqysysuserregedit.do" class="sign-in">企业</a>
                            </span>
                            </li>
                            <!-- quick-link-item end -->
                            </c:if>


                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- header middle end -->
    <div id="active-sticky" class="header-botom bg-dark d-none d-lg-block">
        <div class="container position-relative">
            <div class="row align-items-center">
                <div class="col-md-8">
                    <ul class="main-menu">
                        <li class="main-menu-item position-relative">
                            <a class="main-menu-link"  href="/itivtmgr/itivtmgr/index.do">首页 </a>
                        </li>

                        <li class="main-menu-item position-static">
                            <a class="main-menu-link" href="/itivtmgr/recruiting/zpxxs.do">招聘信息 </a>
                        </li>

                        <li class="main-menu-item position-static">
                            <a class="main-menu-link" href="/itivtmgr/sysuser/qyxxs.do">企业信息 </a>
                        </li>

                        <c:if test="${admin.utype == '企业'}" >
                        <li class="main-menu-item position-static">
                            <a class="main-menu-link" href="/itivtmgr/sysuser/rcxxs.do">人才信息 </a>
                        </li>
                        </c:if>

                        <li class="main-menu-item position-relative">
                            <a class="main-menu-link" href="/itivtmgr/itivtmgr/siteinfo.do?id=2">网站公告</a>
                        </li>

                        <li class="main-menu-item position-relative">
                            <a class="main-menu-link"  href="/itivtmgr/zresources/znzxs.do">站内资讯 </a>
                        </li>

                        <li class="main-menu-item position-relative">
                            <a class="main-menu-link" href="/itivtmgr/itivtmgr/siteinfo.do?id=3">网站简介</a>
                        </li>

                        <li class="main-menu-item position-relative">
                            <a class="main-menu-link" href="/itivtmgr/itivtmgr/siteinfo.do?id=4">联系我们</a>
                        </li>

                        <li class="main-menu-item position-relative">
                            <a class="main-menu-link" href="/itivtmgr/itivtmgr/guestbook.do?id=4">留言板</a>
                        </li>

                    </ul>
                </div>
                <div class="col-md-4">
                    <div class="contact-info">
                        <i class="las la-headset"></i>
                        <span>Call us : </span>
                        <a href="tel:0123456789">0123456789</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- header middle end -->
</header>

<!-- header section end -->

<!-- main content start -->
<%
    if(request.getRequestURI().contains("index")){
%>
<!-- Hero Slider Start -->
<section class="hero-section">
    <div class="hero-slider">
        <div class="swiper-container">
            <div class="swiper-wrapper">


                <c:forEach items="${list1}" var="sdata" varStatus="sta">
                <!-- swiper-slide start -->
                <div class="hero-slide-item slider-height1 swiper-slide animate-style1 slide-bg1" style="width: 100%;background-image: url('/itivtmgr/upfile/${sdata.filename}');">
                    <div  style="width: 100%;height: 90px;margin-top: 460px;background: rgba(0,0,0, 0.5);">
                        <div class="row">
                            <div class="col-12">
                                <div class="hero-slide-content">
                                    <h1 class="title text-white delay1 animated" style="font-size: 29px;margin-top: 30px">
                                        <a href="${sdata.remo}" target="_blank">${sdata.mtitle}</a>
                                    </h1>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- swiper-slide end-->
                </c:forEach>



            </div>
        </div>

        <!-- Add Pagination -->
        <div class="swiper-pagination"  ></div>
        <!-- swiper navigation -->
        <div class="d-none d-lg-block">
            <div class="swiper-button-prev">
                <i class="las la-chevron-circle-left"></i>
            </div>
            <div class="swiper-button-next">
                <i class="las la-chevron-circle-right"></i>
            </div>
        </div>
    </div>
</section>
<!-- Hero Slider End -->
<%}%>


<!-- main banner section start -->
<div class="main-banner-section section-mt  " style="height: 10px">

</div>
<!-- main banner section end -->




<style>
    .copyrights{text-indent:-9999px;height:0;line-height:0;font-size:0;overflow:hidden;}
</style>

</body>
</html>
