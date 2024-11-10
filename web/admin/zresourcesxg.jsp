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
<form  action="/itivtmgr/itivtmgr?ac=zresourcesxg&id=${id}"   autocomplete="off"  style="display: inline;font-size: 15px" name="f1" method="post"  onsubmit="return checkform()" >
 servletformend -->
<!-- s2formstart -->
<form  name="f1" method="post"  onsubmit="return checkform()" action="/itivtmgr/zresources/zresourcesxg.do?id=${id}" > 
<!-- servletformend --> 

<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>信息修改</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">
  
<tr>
<th width='17%' align="center"><center>标题</center></td> 
<td> <input type=text  onblur='checkform()'  class='' id='ntitle' name='ntitle' size=60 /><label  style='display:inline'  id='clabelntitle' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>类别</center></td> 
<td> ${btypeselect}<label id='clabelbtype' />
 </td> 
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
<th width='17%' align="center"><center>详情</center></td> 
<td> 
<TEXTAREA   name="content" id="content">${updateentity.content}</TEXTAREA>
<script type="text/javascript"  src="/itivtmgr/fckeditor/ckeditor.js"></script>
<script language="javascript">
function fckinit()
{
 CKEDITOR.replace('content');
}
fckinit();
</script>
 </td> 
</tr>
 
<tr>
<td colspan="2" align="center"> 
<label>
<input type="button" onclick="if(checkform()){f1.submit();}" name="button" id="button" value=" 提交信息 " />
</label>
&nbsp;&nbsp;&nbsp;&nbsp;                     
<input type=button value=' 返回上页 ' onclick='window.location.replace("/itivtmgr/zresources/zresourcescx.do")' />   
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
var ntitleobj = document.getElementById("ntitle");  
if(ntitleobj.value==""){  
document.getElementById("clabelntitle").innerHTML="&nbsp;&nbsp;<font color=red>请输入标题</font>";  
return false;  
}else{ 
document.getElementById("clabelntitle").innerHTML="  ";  
}  
var btypeobj = document.getElementById("btype");  
if(btypeobj.value==""){  
document.getElementById("clabelbtype").innerHTML="&nbsp;&nbsp;<font color=red>请输入类别</font>";  
return false;  
}else{ 
document.getElementById("clabelbtype").innerHTML="  ";  
}  
return true;   
}   
</script>  
${fillForm} 
<script language=javascript >  
 
</script>  
${fillForm} 
