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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="nowtime" class="java.util.Date" scope="page"></jsp:useBean>
<fmt:formatDate value="${nowtime}" pattern="yyyy-MM-dd HH:mm:ss" var="nowtime" />
<jsp:useBean id="nowdate" class="java.util.Date" scope="page"></jsp:useBean>
<fmt:formatDate value="${nowdate}" pattern="yyyy-MM-dd" var="nowdate" />

<%
HashMap user = Info.getUser(request);
String uname = user.get("uname").toString();
String utype = user.get("utype").toString();
String userid = user.get("id").toString();
 %> 

<jsp:include page="/itivtmgr/toadmintop.do"></jsp:include>

<!-- servletformstart 
<form  action="/itivtmgr/itivtmgr?ac=fshchargesxg&id=${id}"   autocomplete="off"  style="display: inline;font-size: 15px" name="f1" method="post"  onsubmit="return checkform()" >
 servletformend -->
<!-- s2formstart -->
<form  name="f1" method="post"  onsubmit="return checkform()" action="/itivtmgr/charges/fshchargesxg.do?id=${id}" > 
<!-- servletformend --> 

<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>信息查看</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">
  
<tr>
<th width='17%' align="center"><center>会员</center></td> 
<td> ${updateentity.uname} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>服务类别</center></td> 
<td> ${updateentity.stype} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>支付方式</center></td> 
<td> ${updateentity.atype} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>付款账户</center></td> 
<td> ${updateentity.acct} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>付款密码</center></td> 
<td> ${updateentity.apass} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>付款金额</center></td> 
<td> ${updateentity.amt} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>简述</center></td> 
<td> ${updateentity.remo} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>发布时间</center></td> 
<td> ${updateentity.savetime} </td> 
</tr>
 
<tr>
<td colspan="2" align="center"> 
<label>
<input type="button" onclick="if(checkform()){f1.submit();}" name="button" id="button" value=" 提交信息 " />
</label>
&nbsp;&nbsp;&nbsp;&nbsp;                     
<input type=button value=' 返回上页 ' onclick='window.location.replace("/itivtmgr/charges/fshchargescx.do")' />   
 </td> 
</tr>
		  

			</table>
		</div>
	</div>
</div>
</form>

<jsp:include page="/itivtmgr/toadminfoot.do"></jsp:include>
<script language=javascript src='/itivtmgr/js/jquery-3.3.1.min.js'></script>

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
<script language=javascript src='/itivtmgr/js/jquery-3.3.1.min.js'></script>
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
