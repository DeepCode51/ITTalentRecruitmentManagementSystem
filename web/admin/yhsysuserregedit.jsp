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
<%@page import="java.io.InputStream"%> 
<%@page import="java.io.IOException"%>  
<%@page import="util.PageManager"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<link rel="stylesheet" href="/itivtmgr/admin/adminfiles/vendors/iconic-fonts/font-awesome/css/all.min.css">
<link rel="stylesheet" href="/itivtmgr/admin/adminfiles/vendors/iconic-fonts/flat-icons/flaticon.css">
<link rel="stylesheet" href="/itivtmgr/admin/adminfiles/vendors/iconic-fonts/cryptocoins/cryptocoins.css">
<link rel="stylesheet" href="/itivtmgr/admin/adminfiles/vendors/iconic-fonts/cryptocoins/cryptocoins-colors.css">
<link href="/itivtmgr/admin/adminfiles/css/bootstrap.min.css" rel="stylesheet">
<link href="/itivtmgr/admin/adminfiles/css/jquery-ui.min.css" rel="stylesheet">
<link href="/itivtmgr/admin/adminfiles/css/style.css" rel="stylesheet">
 

<!-- servletformstart 
<form  action="/itivtmgr/itivtmgr?ac=yhsysuserregedit&tglparentid=${tglparentid}"   autocomplete="off"  style="display: inline;font-size: 15px" name="f1" method="post"  onsubmit="return checkform()" >
 servletformend -->
<!-- s2formstart -->
<form name="f1" method="post" onsubmit="return checkform()" action="/itivtmgr/sysuser/yhsysuserregedit.do?tglparentid=${tglparentid}" > 
<!-- servletformend --> 

<div class="ms-panel">
	<div class="ms-panel-header">
		<center><h6>用户注册</h6></center>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">
  
				<tr>
				<th width='18%' align="center"><center>用户名</center></td> 
				<td> <input type=text  onblur='checkform()' class='' id='uname' name='uname' size=35 /><label style='display:inline' id='clabeluname' /> </td> 
				</tr>
<tr>
				<th width='18%' align="center"><center>密码</center></td> 
				<td> <input type=password  onblur='checkform()' class='' id='upass' name='upass' size=35 /><label style='display:inline' id='clabelupass' /> </td> 
				</tr><tr>
				<th width='18%' align="center"><center>确认密码</center></td> 
				<td> <input type=password  onblur='checkform()' class='' id='reupass' name='reupass' size=35 /><label style='display:inline' id='clabelreupass' /> </td> 
				</tr>
<tr>
				<th width='18%' align="center"><center>姓名</center></td> 
				<td> <input type=text  onblur='checkform()' class='' id='tname' name='tname' size=35 /><label style='display:inline' id='clabeltname' /> </td> 
				</tr>
<tr>
				<th width='18%' align="center"><center>性别</center></td> 
				<td> <span id="sexdanx"><label  style='display:inline' ><input type=radio checked=checked name='sex' id='sex0' value='男' />&nbsp;男 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio  name='sex' id='sex1' value='女' />&nbsp;女 </label>&nbsp;&nbsp;
</span> </td> 
				</tr>
<tr>
				<th width='18%' align="center"><center>联系电话</center></td> 
				<td> <input type=text  onblur='checkform()' class='' id='tel' name='tel' size=35 /><label style='display:inline' id='clabeltel' /> </td> 
				</tr>
<tr>
				<th width='18%' align="center"><center>联系QQ</center></td> 
				<td> <input type=text  onblur='checkform()' class='' id='qq' name='qq' size=35 /><label style='display:inline' id='clabelqq' /> </td> 
				</tr>
<tr>
				<th width='18%' align="center"><center>地址</center></td> 
				<td> <input type=text  onblur='checkform()' class='' id='addrs' name='addrs' size=60 /><label style='display:inline' id='clabeladdrs' /> </td> 
				</tr>
<tr>
				<th width='18%' align="center"><center>密保问题</center></td> 
				<td> <input type=text  onblur='checkform()' class='' id='passques' name='passques' size=60 /><label style='display:inline' id='clabelpassques' /> </td> 
				</tr>
<tr>
				<th width='18%' align="center"><center>密保答案</center></td> 
				<td> <input type=text  onblur='checkform()' class='' id='passans' name='passans' size=60 /><label style='display:inline' id='clabelpassans' /> </td> 
				</tr>
<tr>
				<th width='18%' align="center"><center>相片</center></td> 
				<td> 
<img style="border-radius:0;cursor: hand;margin:3px;height:85px" onclick="uploadimg()" src="/itivtmgr/js/nopic.jpg" id=txt height="65"/>
<input type=hidden name="filename" id="filename" value="" />
<script type="text/javascript"  src="/itivtmgr/js/popups.js"></script>
 </td> 
				</tr>
 


				<tr>
				<td colspan="2" align="center"> 
				<label>
<input type="button" onclick="if(checkform()){f1.submit();}" name="button" id="button" value=" 提交信息 " />
</label>
&nbsp;&nbsp;&nbsp;&nbsp;                     
				<input type=button value='返回首页' onclick='window.location.replace("/itivtmgr/admin/login.jsp")' />  
				 </td> 
				</tr>
		  

			</table>
		</div>
	</div>
</div>
</form>

<script src="/itivtmgr/admin/adminfiles/js/jquery-3.3.1.min.js"></script>
<script src="/itivtmgr/admin/adminfiles/js/popper.min.js"></script>
<script src="/itivtmgr/admin/adminfiles/js/bootstrap.min.js"></script>
<script src="/itivtmgr/admin/adminfiles/js/perfect-scrollbar.js"> </script>
<script src="/itivtmgr/admin/adminfiles/js/jquery-ui.min.js"> </script>
<script src="/itivtmgr/admin/adminfiles/js/framework.js"></script>
<script src="/itivtmgr/admin/adminfiles/js/settings.js"></script>


<c:if test="${!empty sessionScope.suc}">
<c:remove var="suc" scope="session" />
<script type="text/javascript"> 
alert("操作成功"); 
</script>
<c:remove var="suc" scope="session" />
</c:if> 
            
<script language=javascript src='/itivtmgr/js/My97DatePicker/WdatePicker.js'></script>
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
document.getElementById("clabeltname").innerHTML="&nbsp;&nbsp;<font color=red>请输入姓名</font>";  
return false;  
}else{
document.getElementById("clabeltname").innerHTML="  ";  
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
var qqobj = document.getElementById("qq");  
if(qqobj.value!=""){  
if(qqobj.value.length>12||isNaN(qqobj.value)){ 
document.getElementById("clabelqq").innerHTML="&nbsp;&nbsp;<font color=red>联系QQ必须为12位以内数字</font>";  
return false;
}else{  
document.getElementById("clabelqq").innerHTML="";  
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
