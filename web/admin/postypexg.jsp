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
<form  action="/itivtmgr/itivtmgr?ac=postypexg&id=${id}"   autocomplete="off"  style="display: inline;font-size: 15px" name="f1" method="post"  onsubmit="return checkform()" >
 servletformend -->
<!-- s2formstart -->
<form  name="f1" method="post"  onsubmit="return checkform()" action="/itivtmgr/postype/postypexg.do?id=${id}" > 
<!-- servletformend --> 

<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>信息查看</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">
  
<tr>
<th width='17%' align="center"><center>类别名称</center></td> 
<td> <input type=text  onblur='checkform()'  class='' id='datashowname' name='datashowname' size=35 /><label  style='display:inline'  id='clabeldatashowname' /> </td> 
</tr>
 
<tr>
<td colspan="2" align="center"> 
<label>
<input type="button" onclick="if(checkform()){f1.submit();}" name="button" id="button" value=" 提交信息 " />
</label>
&nbsp;&nbsp;&nbsp;&nbsp;                     
<input type=button value=' 返回上页 ' onclick='window.location.replace("/itivtmgr/postype/postypecx.do")' />   
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
var datashownameobj = document.getElementById("datashowname");  
if(datashownameobj.value==""){  
document.getElementById("clabeldatashowname").innerHTML="&nbsp;&nbsp;<font color=red>请输入类别名称</font>";  
return false;  
}else{ 
document.getElementById("clabeldatashowname").innerHTML="  ";  
}  
var datashownameobj = document.getElementById("datashowname");  
if(datashownameobj.value!=""){  
var ajax = new AJAX(); 
ajax.post("/itivtmgr/ajax/checkno.do?table=postype&col=datashowname&value="+datashownameobj.value+"&checktype=update&id=${id}&ttime=<%=Info.getDateStr()%>"); 
//ajax.post(encodeURI("/itivtmgr/ajax/checkno.do?table=postype&col=datashowname&value="+datashownameobj.value+"&checktype=update&id=${id}&ttime=<%=Info.getDateStr()%>")); 
var msg = ajax.getValue(); 
if(msg.indexOf('Y')>-1){ 
document.getElementById("clabeldatashowname").innerHTML="&nbsp;&nbsp;<font color=red>类别名称已存在</font>";  
return false; 
}else{ 
document.getElementById("clabeldatashowname").innerHTML="  ";  
}  
}  
return true;   
}   
</script>  
${fillForm} 
<script language=javascript >  
 
</script>  
${fillForm} 
