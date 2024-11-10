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

<div style="height: 30px"></div>
<!-- footer section start -->
<footer class="footer">
    <div class="container">
        <div class="row mb-n7">
            <div class="  ">
                <div class="footer-widget">

                    <p>
                        <center>
                        技术支持 : * * 级 * * 班 * * 同学 ， 接受24小时全天咨询
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <i class="las la-map-marked"></i> <span>联系电话 :  4710 - 8888 8888 </span>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <i class="las la-envelope-open"></i> <a href="mailto:demo@hasthemes.com">联系邮箱 : 666666@666.com</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <i class="las la-phone-volume"></i> <a href="tel:+11238889999">地址 : **市**区，**大道8888号，808室</a>
                        </center>
                    </p>

                </div>
            </div>
        </div>
    </div>

    <div class="copy-right bg-dark">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <p> 本 系 统 开 源 ， 可 供 学 习 交 流 使 用 ，交 流 电 话 13888888888 ，欢 迎 来 电 咨 询 </p>
                </div>
            </div>
        </div>
    </div>

</footer>
<!-- footer section end -->

<style>
    .copyrights{text-indent:-9999px;height:0;line-height:0;font-size:0;overflow:hidden;}
</style>

<script src="/itivtmgr/frontfiles/js/vendor/jquery-3.5.1.min.js"></script>
<script src="/itivtmgr/frontfiles/js/vendor/jquery-migrate-3.3.0.min.js"></script>
<script src="/itivtmgr/frontfiles/js/plugins/jquery-ui.min.js"></script>
<script src="/itivtmgr/frontfiles/js/vendor/bootstrap.bundle.min.js"></script>
<script src="/itivtmgr/frontfiles/js/vendor/modernizr-3.11.2.min.js"></script>
<script src="/itivtmgr/frontfiles/js/plugins/ajax-contact.js"></script>
<script src="/itivtmgr/frontfiles/js/plugins/ajax-mailchimp.js"></script>
<script src="/itivtmgr/frontfiles/js/plugins/form-validation.js"></script>
<script src="/itivtmgr/frontfiles/js/plugins/swiper-bundle.min.js"></script>
<script src="/itivtmgr/frontfiles/js/main.js"></script>