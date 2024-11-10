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

<%
HashMap user = Info.getUser(request);
String uname = user.get("uname").toString();
String utype = user.get("utype").toString();
String userid = user.get("id").toString();
 %> 

<jsp:include page="/itivtmgr/toadmintop.do"></jsp:include>

<!-- servletformstart 
<form  action="/itivtmgr/itivtmgr?ac=perqysysuserxg&id=${id}"   autocomplete="off"  style="display: inline;font-size: 15px" name="f1" method="post"  onsubmit="return checkform()" >
 servletformend -->
<!-- s2formstart -->
<form  name="f1" method="post"  onsubmit="return checkform()" action="/itivtmgr/sysuser/perqysysuserxg.do?id=${id}" > 
<!-- servletformend --> 

<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>修改个人信息</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">
  
<tr>
<th width='17%' align="center"><center>用户名</center></td> 
<td> ${updateentity.uname} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>类别</center></td> 
<td> ${updateentity.utype} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>状态</center></td> 
<td> ${updateentity.status} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>名称</center></td> 
<td> <input type=text  onblur='checkform()'  class='' id='tname' name='tname' size=35 /><label  style='display:inline'  id='clabeltname' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>性别</center></td> 
<td> <span id="sexdanx"><label  style='display:inline' ><input type=radio name='sex' value='男' />&nbsp;&nbsp;男 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio name='sex' value='女' />&nbsp;&nbsp;女 </label>&nbsp;&nbsp;
</span> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>邮箱</center></td> 
<td> <input type=text  onblur='checkform()'  class='' id='email' name='email' size=35 /><label  style='display:inline'  id='clabelemail' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>联系电话</center></td> 
<td> <input type=text  onblur='checkform()'  class='' id='tel' name='tel' size=35 /><label  style='display:inline'  id='clabeltel' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>地址</center></td> 
<td> <input type=text  onblur='checkform()'  class='' id='addrs' name='addrs' size=60 /><label  style='display:inline'  id='clabeladdrs' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>成立日期</center></td> 
<td> <input type=text size='12'   onblur='checkform()'  class=''   name='founddate'  id='founddate' onclick='WdatePicker();'  /><label id='clabelfounddate' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>企业类型</center></td> 
<td> <span id="companytypedanx"><label  style='display:inline' ><input type=radio name='companytype' value='民企' />&nbsp;&nbsp;民企 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio name='companytype' value='国企' />&nbsp;&nbsp;国企 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio name='companytype' value='事业' />&nbsp;&nbsp;事业 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio name='companytype' value='外资' />&nbsp;&nbsp;外资 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio name='companytype' value='合资' />&nbsp;&nbsp;合资 </label>&nbsp;&nbsp;
</span> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>行业</center></td> 
<td> <input type=text  onblur='checkform()'  class='' id='mainbuss' name='mainbuss' size=35 /><label  style='display:inline'  id='clabelmainbuss' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>简介</center></td> 
<td> <textarea  cols='45' rows='3'  onblur='checkform()'   style='margin-top: 1px;margin-bottom: 1px'  class=''   name='remo'   ></textarea> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>密保问题</center></td> 
<td> <input type=text  onblur='checkform()'  class='' id='passques' name='passques' size=60 /><label  style='display:inline'  id='clabelpassques' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>密保答案</center></td> 
<td> <input type=text  onblur='checkform()'  class='' id='passans' name='passans' size=60 /><label  style='display:inline'  id='clabelpassans' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>相片</center></td> 
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
<input type=button onclick="getPvalue();" value=' 重置信息 ' />   
 </td> 
</tr>
		  

			</table>
		</div>
	</div>
</div>
</form>

<jsp:include page="/itivtmgr/toadminfoot.do"></jsp:include>


<c:if test="${!empty sessionScope.suc}">
<c:remove var="suc" scope="session" />
<script type="text/javascript"> 
alert("操作成功"); 
</script>
<c:remove var="suc" scope="session" />
</c:if> 
            
<script language=javascript src='/itivtmgr/js/My97DatePicker/WdatePicker.js'></script> 
<script language=javascript src='/itivtmgr/js/ajax.js'></script> 
<script language=javascript src='/itivtmgr/js/popup.js'></script> 
<%@page import="util.Info"%> 
<%@page import="util.Info"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.HashMap"%> 
<%@page import="util.PageManager"%> 
<%@page import="dao.CommDAO"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<script language=javascript >  
 
</script>  
<script language=javascript >  
 function checkform(){  
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
${fillForm} 
<script language=javascript >  
 
</script>  
${fillForm} 
