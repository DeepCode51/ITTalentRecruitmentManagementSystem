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
<form  action="/itivtmgr/itivtmgr?ac=pvirecruitingxg&id=${id}"   autocomplete="off"  style="display: inline;font-size: 15px" name="f1" method="post"  onsubmit="return checkform()" >
 servletformend -->
<!-- s2formstart -->
<form  name="f1" method="post"  onsubmit="return checkform()" action="/itivtmgr/recruiting/pvirecruitingxg.do?id=${id}" > 
<!-- servletformend --> 

<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>信息查看</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">
  
<tr>
<th width='17%' align="center"><center>企业用户名</center></td> 
<td> ${updateentity.qyuname} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>企业名称</center></td> 
<td> ${updateentity.qytname} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>招聘标题</center></td> 
<td> ${updateentity.rtitle} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>招聘类别</center></td> 
<td> ${updateentity.infotype} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>职位大类</center></td> 
<td> ${updateentity.btype} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>职位细类</center></td> 
<td> ${updateentity.stype} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>招聘人数</center></td> 
<td> ${updateentity.nums} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>工作地点</center></td> 
<td> ${updateentity.addrs} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>学历要求</center></td> 
<td> ${updateentity.education} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>技能要求</center></td> 
<td> ${updateentity.skills} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>薪资待遇</center></td> 
<td> ${updateentity.sal} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>其他要求</center></td> 
<td> ${updateentity.remo} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>发布时间</center></td> 
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
