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
    <title>itivtmgr</title>

    <link rel="shortcut icon" type="image/x-icon" href="/itivtmgr/frontfiles/images/favicon.ico">
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/vendor/ionicons.css" />
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/vendor/line-awesome.min.css" />
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/plugins/animate.min.css" />
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/plugins/swiper-bundle.min.css" />
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/plugins/jquery-ui.min.css" />
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/style.css" />

</head>
<!-- head end -->

<% 
String tglparentid=request.getParameter("tglparentid")==null?"":request.getParameter("tglparentid"); 
%>
<body>

<jsp:include page="/itivtmgr/tofrtop.do"></jsp:include>

<!-- product tab slider start -->
<section class="product-tab-section">

    <div class="container" >
        <div class=" " align="center">
            <div class="col-xl-5 col-md-4 col-12" >
                <div class="section-title">
                    <h2 class="title" style="font-size: 38px;padding: 10px;letter-spacing: 3px">会员注册</h2>
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
                                                    <!-- servletformstart 
                                                    <form  action="/itivtmgr/itivtmgr?ac=hysysuserregedit&tglparentid=${tglparentid}"   autocomplete="off"  style="display: inline;font-size: 15px" name="f1" method="post"  onsubmit="return checkform()" >
                                                         servletformend -->
                                                        <!-- s2formstart -->
                                                        <form name="f1" method="post" onsubmit="return checkform()" action="/itivtmgr/sysuser/hysysuserregedit.do?tglparentid=${tglparentid}" > 
                                                        <!-- servletformend -->
                                                        <table style="width: 70%;font-weight: 500;font-size: 18px" align="center"  >


                                                            <tr>
                                                                <td align="center" width="20%" height="46"></td>
                                                                <td align="center" width="15%" height="46"><center>用户名 : </center></td>
                                                                <td align="left"> <input type=text  onblur='checkform()' class='' id='uname' name='uname' size=35 /><label style='display:inline' id='clabeluname' />  </td>
                                                            </tr>
<tr>
                                                                <td align="center" width="20%" height="46"></td>
                                                                <td align="center" width="15%" height="46"><center>密码 : </center></td>
                                                                <td align="left"> <input type=password  onblur='checkform()' class='' id='upass' name='upass' size=35 /><label style='display:inline' id='clabelupass' />  </td>
                                                            </tr><tr>
                                                                <td align="center" width="20%" height="46"></td>
                                                                <td align="center" width="15%" height="46"><center>确认密码 : </center></td>
                                                                <td align="left"> <input type=password  onblur='checkform()' class='' id='reupass' name='reupass' size=35 /><label style='display:inline' id='clabelreupass' />  </td>
                                                            </tr>
<tr>
                                                                <td align="center" width="20%" height="46"></td>
                                                                <td align="center" width="15%" height="46"><center>名称 : </center></td>
                                                                <td align="left"> <input type=text  onblur='checkform()' class='' id='tname' name='tname' size=35 /><label style='display:inline' id='clabeltname' />  </td>
                                                            </tr>
<tr>
                                                                <td align="center" width="20%" height="46"></td>
                                                                <td align="center" width="15%" height="46"><center>性别 : </center></td>
                                                                <td align="left"> <span id="sexdanx"><label  style='display:inline' ><input type=radio checked=checked name='sex' id='sex0' value='男' />&nbsp;男 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio  name='sex' id='sex1' value='女' />&nbsp;女 </label>&nbsp;&nbsp;
</span>  </td>
                                                            </tr>
<tr>
                                                                <td align="center" width="20%" height="46"></td>
                                                                <td align="center" width="15%" height="46"><center>邮箱 : </center></td>
                                                                <td align="left"> <input type=text  onblur='checkform()' class='' id='email' name='email' size=35 /><label style='display:inline' id='clabelemail' />  </td>
                                                            </tr>
<tr>
                                                                <td align="center" width="20%" height="46"></td>
                                                                <td align="center" width="15%" height="46"><center>联系电话 : </center></td>
                                                                <td align="left"> <input type=text  onblur='checkform()' class='' id='tel' name='tel' size=35 /><label style='display:inline' id='clabeltel' />  </td>
                                                            </tr>
<tr>
                                                                <td align="center" width="20%" height="46"></td>
                                                                <td align="center" width="15%" height="46"><center>地址 : </center></td>
                                                                <td align="left"> <input type=text  onblur='checkform()' class='' id='addrs' name='addrs' size=60 /><label style='display:inline' id='clabeladdrs' />  </td>
                                                            </tr>

<tr>
                                                                <td align="center" width="20%" height="46"></td>
                                                                <td align="center" width="15%" height="46"><center>密保问题 : </center></td>
                                                                <td align="left"> <input type=text  onblur='checkform()' class='' id='passques' name='passques' size=40 /><label style='display:inline' id='clabelpassques' />  </td>
                                                            </tr>
<tr>
                                                                <td align="center" width="20%" height="46"></td>
                                                                <td align="center" width="15%" height="46"><center>密保答案 : </center></td>
                                                                <td align="left"> <input type=text  onblur='checkform()' class='' id='passans' name='passans' size=40 /><label style='display:inline' id='clabelpassans' />  </td>
                                                            </tr>
<tr>
                                                                <td align="center" width="20%" height="46"></td>
                                                                <td align="center" width="15%" height="46"><center>相片 : </center></td>
                                                                <td align="left"> 
<img style="border-radius:0;cursor: hand;margin:3px;height:85px" onclick="uploadimg()" src="/itivtmgr/js/nopic.jpg" id=txt height="65"/>
<input type=hidden name="filename" id="filename" value="" />
<script type="text/javascript"  src="/itivtmgr/js/popups.js"></script>
  </td>
                                                            </tr>




                                                            <tr>
                                                                <td colspan="3" align="center"  height="68">
                                                                    <label>
<input type="button" onclick="if(checkform()){f1.submit();}" name="button" id="button" value=" 提交信息 " />
</label>
&nbsp;&nbsp;&nbsp;&nbsp;
                                                                    <label>
<input type="button" value=' 返回首页 ' onclick='window.location.replace("/itivtmgr/itivtmgr/index.do")' />
</label>
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


<jsp:include page="/itivtmgr/tofrfoot.do"></jsp:include>

</body>
</html><script language=javascript src='/itivtmgr/js/My97DatePicker/WdatePicker.js'></script>
<script language=javascript src='/itivtmgr/js/popup.js'></script>
<script language=javascript src='/itivtmgr/js/ajax.js'></script>
<%@page import="util.Info"%>
<%@page import="util.Info"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.HashMap"%> 
<%@page import="util.PageManager"%> 
<%@page import="dao.CommDAO"%> 
<script language=javascript >  
 
 function checkform(){  
var unameobj = document.getElementById("uname");  
if(unameobj.value==""){  
document.getElementById("clabeluname").innerHTML="&nbsp;&nbsp;<font color=red>请输入用户名</font>";  
return false;  
}else{
document.getElementById("clabeluname").innerHTML="  ";  
}  
  
var unameobj = document.getElementById("uname");  
if(unameobj.value!=""){  
var ajax = new AJAX();
ajax.post(encodeURI("/itivtmgr/ajax/checkno.do?table=sysuser&col=uname&value="+unameobj.value+"&checktype=insert&ttime=<%=Info.getDateStr()%>")) 
//ajax.post("/itivtmgr/ajax/checkno.do?table=sysuser&col=uname&value="+unameobj.value+"&checktype=insert&ttime=<%=Info.getDateStr()%>") 
var msg = ajax.getValue();
if(msg.indexOf('Y')>-1){
document.getElementById("clabeluname").innerHTML="&nbsp;&nbsp;<font color=red>用户名已存在</font>";  
return false;
}else{document.getElementById("clabeluname").innerHTML="  ";  
}  
}  
var upassobj = document.getElementById("upass");  
if(upassobj.value==""){  
document.getElementById("clabelupass").innerHTML="&nbsp;&nbsp;<font color=red>请输入密码</font>";  
return false;  
}else{
document.getElementById("clabelupass").innerHTML="  ";  
}  
  
var reupassobj = document.getElementById("reupass");  
if(reupassobj.value==""){  
document.getElementById("clabelreupass").innerHTML="&nbsp;&nbsp;<font color=red>请再次输入密码</font>";  
return false;  
}else{
document.getElementById("clabelreupass").innerHTML="  ";  
}  
  
if(upassobj.value!=reupassobj.value){  
document.getElementById("clabelreupass").innerHTML="&nbsp;&nbsp;<font color=red>两次密码输入不一致</font>";  
return false;  
}else{
document.getElementById("clabelreupass").innerHTML="  ";  
}  
  
var tnameobj = document.getElementById("tname");  
if(tnameobj.value==""){  
document.getElementById("clabeltname").innerHTML="&nbsp;&nbsp;<font color=red>请输入名称</font>";  
return false;  
}else{
document.getElementById("clabeltname").innerHTML="  ";  
}  
  
var emailobj = document.getElementById("email");  
if(emailobj.value==""){  
document.getElementById("clabelemail").innerHTML="&nbsp;&nbsp;<font color=red>请输入邮箱</font>";  
return false;  
}else{
document.getElementById("clabelemail").innerHTML="  ";  
}  
  
var emailobj = document.getElementById("email");  
if(emailobj.value!=""){  
var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;  
if(!myreg.test(emailobj.value)){ 
document.getElementById("clabelemail").innerHTML="&nbsp;&nbsp;<font color=red>邮箱格式不正确</font>";  
return false;
}else{  
document.getElementById("clabelemail").innerHTML="";  
}  
}  
var telobj = document.getElementById("tel");  
if(telobj.value==""){  
document.getElementById("clabeltel").innerHTML="&nbsp;&nbsp;<font color=red>请输入联系电话</font>";  
return false;  
}else{
document.getElementById("clabeltel").innerHTML="  ";  
}  
  
var telobj = document.getElementById("tel");  
if(telobj.value!=""){  
if(telobj.value.length>11||telobj.value.length<8||isNaN(telobj.value)){ 
document.getElementById("clabeltel").innerHTML="&nbsp;&nbsp;<font color=red>联系电话必须为8-11位数字</font>";  
return false;
}else{  
document.getElementById("clabeltel").innerHTML="";  
}  
}  
var passquesobj = document.getElementById("passques");  
if(passquesobj.value==""){  
document.getElementById("clabelpassques").innerHTML="&nbsp;&nbsp;<font color=red>请输入密保问题</font>";  
return false;  
}else{
document.getElementById("clabelpassques").innerHTML="  ";  
}  
  
var passansobj = document.getElementById("passans");  
if(passansobj.value==""){  
document.getElementById("clabelpassans").innerHTML="&nbsp;&nbsp;<font color=red>请输入密保答案</font>";  
return false;  
}else{
document.getElementById("clabelpassans").innerHTML="  ";  
}  
  
return true;   
}   
</script>
<c:if test="${!empty sessionScope.suc}">
    <c:remove var="suc" scope="session" />
    <script type="text/javascript">
        alert("操作成功");
    </script>
</c:if>