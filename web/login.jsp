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
<!doctype html>
<html lang="en">

<head>
   
    <!--====== Required meta tags ======-->
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
     
</head>

<body onload="">

    <jsp:include page="/itivtmgr/top.do"></jsp:include>
 
  
    <!--====== SHOP HOME 1 PART START ======-->
    
    <section class="shop-home-1-area pb-60">
        <div class="container">
        
         
                
        
            <div class="row justify-content-center">
              
              
              
                <div class="col-lg-92">
                    <div class="shop-home-area mt-30">
                         
                   
                        
                        <div class="row justify-content-center">
                            <div class="col-lg-42 col-md-6 col-sm-7">
                                <div class="shop-home-sidebar mt-302">
                                    <div class="shop-lookbook-item mt-75">
                                        <div class="shop-lookbook-title">
                                            <h5 class="title">&nbsp;&nbsp;用户登录</h5>
                                        </div>
                                         
                                        
                                        <div class="row">
                 
                                           
							                <div class="col-lg-42"> 
							                <center>
							                <form  class="form-signin" action="/itivtmgr/itivtmgr/login.do" name="f22" method="post" >
							                    <div class="cart_total text-left mt-502" style="width: 30%">
<input type="text" name="uname" class="form-control" placeholder="User ID" autofocus>

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

<div style="height: 25px"></div>
            <input type="password" class="form-control" name="upass" placeholder="Password">
            <div style="height: 25px"></div>
            <label class="checkbox">
            
                 <label><input type="radio" value="用户" name="utype" checked="checked"/> <font color=black>用户</font> </label>
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
                 <label><input type="radio" name="utype" value="管理员" /> <font color=black>管理员</font> </label>
                
            </label>
            <button class="btn btn-lg btn-login btn-block" type="submit" style="background-color: lightblue"> 登   录 </button>
							                    </div>
							                   
							                </div>
							                </form>
							                </center>
							                 
							            </div>
							             
                                        
                                    </div>
                                </div>
                            </div>
                             
                          
                        </div>
                      
                    </div>
                </div>
            </div>
        </div>
    </section>
     
    
    <jsp:include page="foot.jsp"></jsp:include>
 

</body>

</html>
 
<script type="text/javascript">
<!--
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

//-->
</script> 