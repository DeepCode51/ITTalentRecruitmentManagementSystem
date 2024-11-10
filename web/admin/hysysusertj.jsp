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
<form  action="/itivtmgr/itivtmgr?ac=hysysusertj&tglparentid=${tglparentid}"   autocomplete="off"  style="display: inline;font-size: 15px" name="f1" method="post"  onsubmit="return checkform()" >
 servletformend -->
<!-- s2formstart -->
<form name="f1" method="post" onsubmit="return checkform()" action="/itivtmgr/sysuser/hysysusertj.do?tglparentid=${tglparentid}" > 
<!-- servletformend --> 

<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>信息添加</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">
  
<tr>
<th width='17%' align="center"><center>发布人</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='uname' name='uname' size=35 /><label style='display:inline' id='clabeluname' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>密码</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='upass' name='upass' size=35 /><label style='display:inline' id='clabelupass' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>企业名称</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='tname' name='tname' size=35 /><label style='display:inline' id='clabeltname' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>性别</center></td> 
<td> <span id="sexdanx"><label  style='display:inline' ><input type=radio checked=checked name='sex' id='sex0' value='男' />&nbsp;男 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio  name='sex' id='sex1' value='女' />&nbsp;女 </label>&nbsp;&nbsp;
</span> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>邮箱</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='email' name='email' size=35 /><label style='display:inline' id='clabelemail' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>联系电话</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='tel' name='tel' size=35 /><label style='display:inline' id='clabeltel' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>地址</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='addrs' name='addrs' size=60 /><label style='display:inline' id='clabeladdrs' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>户籍</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='houhold' name='houhold' size=35 /><label style='display:inline' id='clabelhouhold' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>生日</center></td> 
<td> <input type=text  onblur='checkform()'  size='12' class=''   name='birth'  id='birth' onclick='WdatePicker();'  /><label id='clabelbirth' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>体重</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='wei' name='wei' size=35 /><label style='display:inline' id='clabelwei' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>身高</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='hei' name='hei' size=35 /><label style='display:inline' id='clabelhei' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>健康状况</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='health' name='health' size=35 /><label style='display:inline' id='clabelhealth' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>毕业院校</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='school' name='school' size=35 /><label style='display:inline' id='clabelschool' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>专业领域</center></td>
<td>

	${stypeselect}

	<label style='display:inline' id='clabelspecialty' /> </td>
</tr>
<tr>
<th width='17%' align="center"><center>学历</center></td> 
<td> <span id="educationdanx"><label  style='display:inline' ><input type=radio checked=checked name='education' id='education0' value='初中' />&nbsp;初中 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio  name='education' id='education1' value='高中' />&nbsp;高中 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio  name='education' id='education2' value='大专' />&nbsp;大专 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio  name='education' id='education3' value='本科' />&nbsp;本科 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio  name='education' id='education4' value='硕士' />&nbsp;硕士 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio  name='education' id='education5' value='博士' />&nbsp;博士 </label>&nbsp;&nbsp;
</span> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>毕业日期</center></td> 
<td> <input type=text  onblur='checkform()'  size='12' class=''   name='cbdate'  id='cbdate' onclick='WdatePicker();'  /><label id='clabelcbdate' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>个人技能</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='skill' name='skill' size=60 /><label style='display:inline' id='clabelskill' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>兴趣爱好</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='hobby' name='hobby' size=60 /><label style='display:inline' id='clabelhobby' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>工作经历</center></td> 
<td> <textarea  cols='45' rows='3'  style='margin-top: 1px;margin-bottom: 1px'  class=''  name='experience'  ></textarea> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>外语技能</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='languages' name='languages' size=35 /><label style='display:inline' id='clabellanguages' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>求职意向</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='intention' name='intention' size=35 /><label style='display:inline' id='clabelintention' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>简介</center></td> 
<td> <textarea  cols='45' rows='3'  style='margin-top: 1px;margin-bottom: 1px'  class=''  name='remo'  ></textarea> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>密保问题</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='passques' name='passques' size=60 /><label style='display:inline' id='clabelpassques' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>密保答案</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='passans' name='passans' size=60 /><label style='display:inline' id='clabelpassans' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>图片</center></td> 
<td> 
<img style="border-radius:0;cursor: hand;margin:3px;height:85px" onclick="uploadimg()" src="/itivtmgr/js/nopic.jpg" id=txt height="65"/>
<input type=hidden name="filename" id="filename" value="" />
<script type="text/javascript"  src="/itivtmgr/js/popups.js"></script>
 </td> 
</tr>
<tr>
<th width='17%' align="center"><center>相关证书</center></td> 
<td> 
<img style="border-radius:0;cursor: hand;margin:3px;height:85px" onclick="uploadimg2()" src="/itivtmgr/js/nopic.jpg" id=txt2 height="65"/>
<input type=hidden name="filename2" id="filename2" value="" />
<script type="text/javascript"  src="/itivtmgr/js/popup.js"></script>
 </td> 
</tr>
<tr>
<th width='17%' align="center"><center>简历电子档</center></td> 
<td> 
<script src=/itivtmgr/js/popup.js></script>
<font onclick="uploaddoc3()" style='cursor:hand' id=filetxt3 >点击此处上传</font>
&nbsp;&nbsp;<input type=text readonly style='border:0px' size=30  name="docname3" id="docname3" value="" />
 </td> 
</tr>
 
<tr>
<td colspan="2" align="center"> 
<label>
<input type="button" onclick="if(checkform()){f1.submit();}" name="button" id="button" value=" 提交信息 " />
</label>
&nbsp;&nbsp;&nbsp;&nbsp;                     
<input type=button value=' 返回上页 ' onclick='window.location.replace("/itivtmgr/sysuser/hysysusercx.do")' />
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
document.getElementById("clabeluname").innerHTML="&nbsp;&nbsp;<font color=red>请输入发布人</font>";  
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
document.getElementById("clabeluname").innerHTML="&nbsp;&nbsp;<font color=red>发布人已存在</font>";  
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
  
var tnameobj = document.getElementById("tname");  
if(tnameobj.value==""){  
document.getElementById("clabeltname").innerHTML="&nbsp;&nbsp;<font color=red>请输入企业名称</font>";  
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
 popheight = 1300;
</script>  
