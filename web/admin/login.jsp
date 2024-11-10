<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="util.Info"%>
<%@page import="dao.CommDAO"%>
<%@page import="util.PageManager"%>
<%
session.invalidate();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Mednalytics</title>
    <!-- Iconic Fonts -->
    <link href="/itivtmgr/admin/adminfiles/vendors/iconic-fonts/font-awesome/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/itivtmgr/admin/adminfiles/vendors/iconic-fonts/flat-icons/flaticon.css">
    <link rel="stylesheet" href="/itivtmgr/admin/adminfiles/vendors/iconic-fonts/cryptocoins/cryptocoins.css">
    <link rel="stylesheet" href="/itivtmgr/admin/adminfiles/vendors/iconic-fonts/cryptocoins/cryptocoins-colors.css">
    <!-- Bootstrap core CSS -->
    <link href="/itivtmgr/admin/adminfiles/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery UI -->
    <link href="/itivtmgr/admin/adminfiles/css/jquery-ui.min.css" rel="stylesheet">
    <!-- Mednalytics styles -->
    <link href="/itivtmgr/admin/adminfiles/css/style.css" rel="stylesheet">

    <!-- Favicon -->
    <link rel="icon" type="image/png" sizes="32x32" href="/itivtmgr/admin/adminfiles/favicon.ico">
</head>
<body class="ms-body ms-primary-theme ms-logged-out" style="background-image: url('../upfile/103.jpg');background-size:cover">
<!-- Setting Panel -->
<%--style="background-image: url('/itivtmgr/upfile/103.jpg');background-size:cover"--%>

<div class=" " >
    <div class="ms-auth-form"  >
        <form class="needs-validation" novalidate="" method="post" style="background-color: white;padding: 35px" action="/itivtmgr/itivtmgr/login.do">
            <h1 style="font-size: 40px">IT人才招聘系统  -  用户登录</h1>
            <p>Please enter your username and password to continue</p>
            <div class="mb-3">
                <label for="uname" style="font-size: 20px">请输入用户名</label>
                <div class="input-group">
                    <input type="text"  class="form-control" id="uname" name="uname" placeholder="Username" required="">
                </div>
            </div>

            <div class="mb-2">
                <label for="upass" style="font-size: 20px">请输入密码</label>
                <div class="input-group">
                    <input type="password" class="form-control" id="upass" name="upass" placeholder="Password" required="">
                </div>
            </div>

            <div class="mb-2">
                <div class="input-group">
                    <label style="font-size: 20px">
                        <input type="radio" style="zoom: 150%" id="utype1" name="utype"  required="" checked value="管理员" /> &nbsp; 管理员
                    </label>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <label style="font-size: 20px">
                        <input type="radio" style="zoom: 150%"  id="utype2" name="utype"  required="" value="会员" /> &nbsp; 会员
                    </label>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <label style="font-size: 20px">
                        <input type="radio" style="zoom: 150%"  id="utype3" name="utype"  required="" value="企业" /> &nbsp; 企业
                    </label>
                </div>
            </div>

            <div class="form-group">
                <label class="d-block mt-3">


                    &nbsp;&nbsp;
                    <div  style="display: inline">
                        <a href="javascript:fotpass()"  >[ 忘记密码 ]</a>
                    </div>
                    <script language=javascript src='/itivtmgr/js/popup.js'></script>
                    <script language="JavaScript">
                        popheight = 100;
                        function fotpass(){
                            var uname = document.getElementById("uname").value;
                            if(uname==''){
                                alert("请输入用户名！");
                                return false;
                            }
                            pop('/itivtmgr/itivtmgr/sfotpass.do?uname='+uname,'忘记密码',550,380);
                        }
                    </script>

                </label>
            </div>
            <button class="btn btn-primary mt-4 d-block w-100" type="submit" style="font-size: 20px" onMouseDown="check();">Sign In</button>

            <%--<p class="mb-0 mt-3 text-center">未注册 ？  <a class="btn-link" href="/itivtmgr/sysuser/toyhsysuserregedit.do">用户注册</a> </p>--%>

            <p class="mb-0 mt-3 text-center"> </p>
        </form>
    </div>
</div>


<!-- SCRIPTS -->
<!-- Global Required Scripts Start -->
<script src="/itivtmgr/admin/adminfiles/js/jquery-3.3.1.min.js"></script>
<script src="/itivtmgr/admin/adminfiles/js/popper.min.js"></script>
<script src="/itivtmgr/admin/adminfiles/js/bootstrap.min.js"></script>
<script src="/itivtmgr/admin/adminfiles/js/perfect-scrollbar.js"> </script>
<script src="/itivtmgr/admin/adminfiles/js/jquery-ui.min.js"> </script>
<!-- Global Required Scripts End -->
<!-- Mednalytics core JavaScript -->
<script src="/itivtmgr/admin/adminfiles/js/framework.js"></script>
<!-- Settings -->
<script src="/itivtmgr/admin/adminfiles/js/settings.js"></script>
</body>
</html>

<script type="text/javascript">
    function check()
    {
        var uname = document.getElementById("uname");
        var upass = document.getElementById("upass");
        var pagerandom = document.getElementById("pagerandom");
        if(uname.value=="")
        {
            alert("请输入用户名");
            uname.focus();
            return;
        }
        if(upass.value=="")
        {
            alert("请输入密码");
            upass.focus();
            return;
        }
    }
</script>

<script type="text/javascript">

<%
if(request.getAttribute("error")!=null){
%>
alert("用户名或密码错误");
<%}%>

<%
if(request.getAttribute("random")!=null){
%>
alert("验证码错误");
<%}%>

document.getElementById("uname").focus();

</script>
