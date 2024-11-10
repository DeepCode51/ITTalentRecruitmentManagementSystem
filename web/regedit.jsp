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
    <title>mywebprojectfullname</title>

    <link rel="shortcut icon" type="image/x-icon" href="/mywebprojectfullname/frontfiles/images/favicon.ico">
    <link rel="stylesheet" href="/mywebprojectfullname/frontfiles/css/vendor/ionicons.css" />
    <link rel="stylesheet" href="/mywebprojectfullname/frontfiles/css/vendor/line-awesome.min.css" />
    <link rel="stylesheet" href="/mywebprojectfullname/frontfiles/css/plugins/animate.min.css" />
    <link rel="stylesheet" href="/mywebprojectfullname/frontfiles/css/plugins/swiper-bundle.min.css" />
    <link rel="stylesheet" href="/mywebprojectfullname/frontfiles/css/plugins/jquery-ui.min.css" />
    <link rel="stylesheet" href="/mywebprojectfullname/frontfiles/css/style.css" />

</head>
<!-- head end -->

<body>

<jsp:include page="top.jsp"/>

<!-- product tab slider start -->
<section class="product-tab-section">

    <div class="container" >
        <div class=" " align="center">
            <div class="col-xl-5 col-md-4 col-12" >
                <div class="section-title">
                    <h2 class="title" style="font-size: 38px;padding: 10px;letter-spacing: 3px">这里要放功能名称</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="tab-content" id="pills-tabContent">
                    <div class="tab-pane fade show active" id="pills-home">

                        <div class="   ">
                            <div class=" ">
                                <div class="row mb-n7 row-cols-1">

                                    <!-- single blog start -->
                                    <div class="col mb-7"  >
                                        <div class=" ">
                                            <div class="thumb  p-0 text-center" style="height: 42px;margin-top: 10px;display: inline" >

                                                <h3  style="font-weight: 500">

                                                    <!-- single blog start -->
                                                    <!-- servletformstart -->
                                                    <form action=""  autocomplete="off"  style="display: inline;font-size: 15px" name="f1" method="post"  onsubmit="return checkform()" >
                                                        <!-- servletformend -->
                                                        <!-- s2formstart
                                                        sactionurl
                                                        s2formend -->
                                                        <table style="width: 70%;font-weight: 500;font-size: 18px" align="center"  >


                                                            <tr>
                                                                <td align="center" width="25%" height="46"></td>
                                                                <td align="center" width="15%" height="46"><center>字段名 : </center></td>
                                                                <td align="left"> 控件  </td>
                                                            </tr>



                                                            <tr>
                                                                <td colspan="3" align="center"  height="52">
                                                                    提交按钮
                                                                    返回按钮
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </form>
                                                    <!-- single blog end -->

                                                </h3>

                                            </div>

                                        </div>
                                    </div>
                                    <!-- single blog end -->


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


<jsp:include page="foot.jsp" />

</body>
</html>