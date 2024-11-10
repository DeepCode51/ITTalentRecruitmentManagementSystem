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
<%
    HashMap user = Info.getUser(request);
    String uname = user.get("uname").toString();
    String utype = user.get("utype").toString();
    String userid = user.get("id").toString();
    String tglparentid = request.getParameter("tglparentid")==null?"":request.getParameter("tglparentid");
    String trcurrentid = request.getParameter("trcurrentid");
    if(trcurrentid!=null)
    {
        session.setAttribute("trcurrentid",trcurrentid);
    }
    String trcurrentpage = request.getParameter("page");
    if(trcurrentpage!=null)
    {
        session.setAttribute("trcurrentpage",trcurrentpage);
    }
    String perurl = "";
    if(utype.equals("会员"))perurl = "toperhysysuserxg";
    if(utype.equals("企业"))perurl = "toperqysysuserxg";
    if(utype.equals("管理员"))perurl = "toperglysysuserxg";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <title>Mednalytics</title>
    <!-- Iconic Fonts -->
    <link rel="stylesheet" href="/itivtmgr/admin/adminfiles/vendors/iconic-fonts/font-awesome/css/all.min.css">
    <link rel="stylesheet" href="/itivtmgr/admin/adminfiles/vendors/iconic-fonts/flat-icons/flaticon.css">
    <link rel="stylesheet" href="/itivtmgr/admin/adminfiles/vendors/iconic-fonts/cryptocoins/cryptocoins.css">
    <link rel="stylesheet" href="/itivtmgr/admin/adminfiles/vendors/iconic-fonts/cryptocoins/cryptocoins-colors.css">
    <!-- Bootstrap core CSS -->
    <link href="/itivtmgr/admin/adminfiles/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery UI -->
    <link href="/itivtmgr/admin/adminfiles/css/jquery-ui.min.css" rel="stylesheet">
    <!-- Mednalytics styles -->
    <link href="/itivtmgr/admin/adminfiles/css/style.css" rel="stylesheet">

    <style type="text/css">
        select{height: 27px}
    </style>

    <!-- Favicon -->
    <link rel="icon" type="image/png" sizes="32x32" href="../../favicon.ico">
</head>
<body class="ms-body ms-aside-left-open ms-primary-theme ms-has-quickbar">
<!-- Preloader -->
<div id="preloader-wrap">
    <div class="spinner spinner-8">
        <div class="ms-circle1 ms-child"></div>
        <div class="ms-circle2 ms-child"></div>
        <div class="ms-circle3 ms-child"></div>
        <div class="ms-circle4 ms-child"></div>
        <div class="ms-circle5 ms-child"></div>
        <div class="ms-circle6 ms-child"></div>
        <div class="ms-circle7 ms-child"></div>
        <div class="ms-circle8 ms-child"></div>
        <div class="ms-circle9 ms-child"></div>
        <div class="ms-circle10 ms-child"></div>
        <div class="ms-circle11 ms-child"></div>
        <div class="ms-circle12 ms-child"></div>
    </div>
</div>
<!-- Overlays -->
<div class="ms-aside-overlay ms-overlay-left ms-toggler" data-target="#ms-side-nav" data-toggle="slideLeft"></div>
<div class="ms-aside-overlay ms-overlay-right ms-toggler" data-target="#ms-recent-activity" data-toggle="slideRight"></div>
<!-- Sidebar Navigation Left -->
<aside id="ms-side-nav" class="side-nav fixed ms-aside-scrollable ms-aside-left">
    <!-- Logo -->
    <div class="logo-sn ms-d-block-lg">
        <a class="pl-0 ml-0 text-center" href="../../index.html">
            <h1 style="color: white;font-size: 27px">IT人才招聘系统</h1>
            <%--<img src="/itivtmgr/admin/adminfiles/img/mednalytics-logo-216x62.png" alt="logo">--%>
        </a>
        <a href="#" class="text-center ms-logo-img-link"> <img src="/itivtmgr/upfile/${admin.filename}" alt="logo"></a>
        <h5 class="text-center text-white mt-2">${admin.tname}</h5>
        <h6 class="text-center text-white mb-3">${admin.uname} ( ${admin.utype} ) </h6>
    </div>
    <!-- Navigation -->
    <ul class="accordion ms-main-aside fs-14" id="side-nav-accordion"  >



        <!-- Icons
        <li class="menu-item">
            <a href="#" class="has-chevron" data-toggle="collapse" data-target="#icons" aria-expanded="false" aria-controls="icons">
                <span><i class="material-icons fs-16" style="margin-top: -15px">equalizer</i>系统用户管理</span>
            </a>
            <ul id="icons" class="collapse" aria-labelledby="icons" data-parent="#side-nav-accordion">
                <li> <a href="../icons/fontawesome.html">Fontawesome</a> </li>
                <li> <a href="../icons/flaticons.html">Flaticons</a> </li>
                <li> <a href="../icons/materialize.html">Materialize</a> </li>
            </ul>
        </li>
         /Icons -->


        <%if(utype.equals("管理员")){ %>

        <%=Info.getCol("A", "1", "招聘信息管理", "", request) %>
        <%=Info.getCol("B", "1", "招聘管理", "/itivtmgr/recruiting/fshrecruitingcx.do", request) %>
        <%=Info.getCol("B", "1", "职位类别管理", "/itivtmgr/postype/postypecx.do", request) %>
        <%=Info.getCol("B", "1", "专业领域", "/itivtmgr/ztype/ztypecx.do", request) %>
        <%=Info.getCol("B", "1", "付费服务", "/itivtmgr/charges/fshchargescx.do", request) %>
        <%=Info.getCol("C", "1", "", "", request) %>

        <%=Info.getCol("A", "4", "网站信息管理", "", request) %>
        <%=Info.getCol("B", "4", "基础信息", "/itivtmgr/mixinfo/zmixinfocx.do", request) %>
        <%=Info.getCol("B", "4", "滚动图片", "/itivtmgr/mixinfo/gmixinfocx.do", request) %>
        <%=Info.getCol("B", "4", "站内资讯", "/itivtmgr/zresources/zresourcescx.do", request) %>
        <%=Info.getCol("B", "4", "信息类别", "/itivtmgr/zrtypes/zrtypescx.do", request) %>
        <%=Info.getCol("B", "4", "留言管理", "/itivtmgr/messages/messagescx.do", request) %>
        <%=Info.getCol("C", "4", "", "", request) %>

        <%=Info.getCol("A", "3", "系统用户管理", "", request) %>
        <%=Info.getCol("B", "3", "管理员", "/itivtmgr/sysuser/glysysusercx.do", request) %>
        <%=Info.getCol("B", "3", "会员", "/itivtmgr/sysuser/hysysusercx.do", request) %>
        <%=Info.getCol("B", "3", "企业", "/itivtmgr/sysuser/qysysusercx.do", request) %>
        <%=Info.getCol("C", "3", "", "", request) %>

         <%=Info.getCol("A", "2", "个人信息管理", "", request) %>
        <%=Info.getCol("B", "2", "修改个人资料", "/itivtmgr/sysuser/toperglysysuserxg.do", request) %>
        <%=Info.getCol("B", "2", "修改登录密码", "/itivtmgr/itivtmgr/touppass.do", request) %>
        <%=Info.getCol("C", "2", "", "", request) %>
        <%} %>


        <%if(utype.equals("会员")){ %>
        <%=Info.getCol("A", "1", "求职信息管理", "", request) %>
        <%=Info.getCol("B", "1", "我申请的职位", "/itivtmgr/jobapply/jobapplycx.do", request) %>
        <%=Info.getCol("B", "1", "在线交流", "/itivtmgr/zchats/zchatscx.do", request) %>
        <%=Info.getCol("B", "1", "查看笔试", "/itivtmgr/exams/fshexamscx.do", request) %>
        <%=Info.getCol("B", "1", "查看OFFER", "/itivtmgr/offers/fshofferscx.do", request) %>
        <%=Info.getCol("B", "1", "付费服务", "/itivtmgr/charges/chargescx.do", request) %>
        <%=Info.getCol("B", "1", "我收藏的企业", "/itivtmgr/ucollect/qyucollectcx.do", request) %>
        <%=Info.getCol("B", "1", "我收藏的职位", "/itivtmgr/ucollect/zwucollectcx.do", request) %>
        <%=Info.getCol("C", "1", "", "", request) %>

        <%=Info.getCol("A", "2", "个人信息管理", "", request) %>
        <%=Info.getCol("B", "2", "修改个人资料", "/itivtmgr/sysuser/toperhysysuserxg.do", request) %>
        <%=Info.getCol("B", "2", "修改登录密码", "/itivtmgr/itivtmgr/touppass.do", request) %>
        <%=Info.getCol("C", "2", "", "", request) %>
        <%} %>

        <%if(utype.equals("企业")){ %>
        <%=Info.getCol("A", "1", "招聘管理", "", request) %>
        <%=Info.getCol("B", "1", "招聘信息管理", "/itivtmgr/recruiting/recruitingcx.do", request) %>
        <%=Info.getCol("B", "1", "求职信息管理", "/itivtmgr/jobapply/fshjobapplycx.do", request) %>
        <%=Info.getCol("B", "1", "笔试管理", "/itivtmgr/exams/examscx.do", request) %>
        <%=Info.getCol("B", "1", "在线交流", "/itivtmgr/zchats/zchatscx.do", request) %>
        <%=Info.getCol("B", "1", "OFFER管理", "/itivtmgr/offers/offerscx.do", request) %>
        <%=Info.getCol("B", "1", "人才收藏", "/itivtmgr/ucollect/rcucollectcx.do", request) %>
        <%=Info.getCol("C", "1", "", "", request) %>

        <%=Info.getCol("A", "2", "个人信息管理", "", request) %>
        <%=Info.getCol("B", "2", "修改个人资料", "/itivtmgr/sysuser/toperqysysuserxg.do", request) %>
        <%=Info.getCol("B", "2", "修改登录密码", "/itivtmgr/itivtmgr/touppass.do", request) %>
        <%=Info.getCol("C", "2", "", "", request) %>
        <%} %>


        <script language="JavaScript">
           document.getElementById("picon1").click();
        </script>


    </ul>
</aside>
<!-- Main Content -->
<main class="body-content">
    <!-- Navigation Bar -->
    <nav class="navbar ms-navbar">
        <div class="ms-aside-toggler ms-toggler pl-0" data-target="#ms-side-nav" data-toggle="slideLeft">
            <span class="ms-toggler-bar bg-white"></span>
            <span class="ms-toggler-bar bg-white"></span>
            <span class="ms-toggler-bar bg-white"></span>
        </div>
        <div class="logo-sn logo-sm ms-d-block-sm">
            <a class="pl-0 ml-0 text-center navbar-brand mr-0" href="../../index.html"><img src="/itivtmgr/admin/adminfiles/img/mednalytics-logo-84x41.png" alt="logo"> </a>
        </div>
        <ul class="ms-nav-list ms-inline mb-0" id="ms-nav-options">

            <li class="ms-nav-item  ms-d-none" style="width: 370px">
                <a href="#mymodal" class="text-white" data-toggle="modal">
                    <i class="flaticon-spreadsheet mr-2"></i>

                    <div id="clock" style="width: 400px;display: inline"></div>
                    <script type="text/javascript">
                        function showRealTime(){
                            var d = new Date();
                            var year = d.getFullYear();
                            var month = d.getMonth() + 1;
                            var date = d.getDate();
                            var days = new Array("日","一","二","三","四","五","六");
                            var day = d.getDay();
                            var hour = (d.getHours() < 10) ? ("0" + d.getHours()) : d.getHours();
                            var min = (d.getMinutes() < 10) ? ("0" + d.getMinutes()) : d.getMinutes();
                            var sec = (d.getSeconds() < 10) ? ("0" + d.getSeconds()) : d.getSeconds();
                            var now = year + "年" + month + "月" + date + "日&nbsp;星期" + days[day] + "&nbsp;" + hour + ":" + min + ":" + sec;
                            document.getElementById("clock").innerHTML = "当前时间：" + now;
                        }
                        showRealTime();
                        setInterval('showRealTime()',1000);
                    </script>

                </a>
            </li>

            <li class="ms-nav-item ms-d-none" style="width: 250px">
                <a href="#prescription" class="text-white" data-toggle="modal"><i class="flaticon-pencil mr-2"></i>
                    客服电话 : 400-8888-8888
                </a>
            </li>

            <li class="ms-nav-item ms-d-none"  >
                <a href="#report1" class="text-white" data-toggle="modal"><i class="flaticon-list mr-2"></i>

                    欢迎回来：${admin.utype}，${admin.uname}（${admin.tname}）

                </a>
            </li>

            <li class="ms-nav-item ms-nav-user dropdown">
                <a href="#" id="userDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <img class="ms-user-img ms-img-round float-right" src="/itivtmgr/upfile/${admin.filename}" alt="people"> </a>
                <ul class="dropdown-menu dropdown-menu-right user-dropdown" aria-labelledby="userDropdown">

                    <li class="ms-dropdown-list">
                        <a class="media fs-14 p-2" href="/itivtmgr/sysuser/<%=perurl%>.do"> <span><i class="flaticon-user mr-2"></i> 修改个人信息</span> </a>
                    </li>
                    <li class="dropdown-menu-footer">
                        <a class="media fs-14 p-2" href="/itivtmgr/itivtmgr/tologin.do"> <span><i class="flaticon-shut-down mr-2"></i> 注销</span> </a>
                    </li>
                </ul>
            </li>
        </ul>
        <div class="ms-toggler ms-d-block-sm pr-0 ms-nav-toggler" data-toggle="slideDown" data-target="#ms-nav-options">
            <span class="ms-toggler-bar bg-white"></span>
            <span class="ms-toggler-bar bg-white"></span>
            <span class="ms-toggler-bar bg-white"></span>
        </div>
    </nav>
    <!-- Body Content Wrapper -->
    <div class="ms-content-wrapper">
