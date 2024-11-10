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
<form  action="/itivtmgr/itivtmgr?ac=pvijobapplyxg&id=${id}"   autocomplete="off"  style="display: inline;font-size: 15px" name="f1" method="post"  onsubmit="return checkform()" >
 servletformend -->
<!-- s2formstart -->
<form  name="f1" method="post"  onsubmit="return checkform()" action="/itivtmgr/jobapply/pvijobapplyxg.do?id=${id}" > 
<!-- servletformend --> 

<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>信息查看</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">
  
<tr>
<th width='17%' align="center"><center>用户名</center></td> 
<td> ${updateentity.uname} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>姓名</center></td> 
<td> ${updateentity.tname} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>职位</center></td> 
<td> ${updateentity.frtitle} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>企业用户名</center></td> 
<td> ${updateentity.fqyuname} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>企业名称</center></td> 
<td> ${updateentity.fqytname} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>申请信息</center></td> 
<td> ${updateentity.applyremo} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>受理状态</center></td> 
<td> ${updateentity.fshstatus} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>受理意见</center></td> 
<td> ${updateentity.fshremo} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>申请时间</center></td> 
<td> ${updateentity.savetime} </td> 
</tr>
 
<tr>
<td colspan="2" align="center"> 
                     
<input type=button value=' 返回上页 ' onclick="window.history.go(-1)" />   
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
return true;   
}   
</script>  
${fillForm} 
<script language=javascript >  
 
</script>  
${fillForm} 
