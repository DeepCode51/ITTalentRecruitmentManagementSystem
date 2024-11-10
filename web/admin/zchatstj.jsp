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
<form  action="/itivtmgr/itivtmgr?ac=zchatstj&tglparentid=${tglparentid}"   autocomplete="off"  style="display: inline;font-size: 15px" name="f1" method="post"  onsubmit="return checkform()" >
 servletformend -->
<!-- s2formstart -->
<form name="f1" method="post" onsubmit="return checkform()" action="/itivtmgr/zchats/zchatstj.do?tglparentid=${tglparentid}" > 
<!-- servletformend --> 

<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>发起会话</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">
  
<tr>
<th width='17%' align="center"><center>接收人</center></td> 
<td> 
<input type=text  style="width: 278px"   onblur='checkform();clearvalue();setTimeout(removeAuto,300)' autocomplete="off"  onKeyUp="tounameautoidchange();autocomplateh()" onMouseDown="tounameautoidchange();autocomplateh()"  class='' id='touname' name='touname' size=35 />
<label id='clabeltouname' ></label>
<br />
<div style=" background:#ffffff;height:200px;width:278px;border:solid 1px #D5D5D5;border-top-style:none; overflow: visible; overflow-x:hidden ; display:none; position: absolute;z-index:9999;" id="tounamediv"></div>

 </td> 
</tr>${tounameautocomp}
<tr>
<th width='17%' align="center"><center>内容</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='tcontent' name='tcontent' size=60 /><label style='display:inline' id='clabeltcontent' /> </td> 
</tr>
 
<tr>
<td colspan="2" align="center"> 
<label>
<input type="button" onclick="if(checkform()){f1.submit();}" name="button" id="button" value=" 提交信息 " />
</label>
&nbsp;&nbsp;&nbsp;&nbsp;                     
<input type=button value=' 返回上页 ' onclick='window.location.replace("/itivtmgr/zchats/zchatscx.do")' />   
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
var tounameobj = document.getElementById("touname");  
if(tounameobj.value==""){  
document.getElementById("clabeltouname").innerHTML="&nbsp;&nbsp;<font color=red>请输入接收人</font>";  
return false;  
}else{
document.getElementById("clabeltouname").innerHTML="  ";  
}  
  
var tcontentobj = document.getElementById("tcontent");  
if(tcontentobj.value==""){  
document.getElementById("clabeltcontent").innerHTML="&nbsp;&nbsp;<font color=red>请输入内容</font>";  
return false;  
}else{
document.getElementById("clabeltcontent").innerHTML="  ";  
}  
  
return true;   
}   
</script>  
