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
<form  action="/itivtmgr/itivtmgr?ac=pviexamsxg&id=${id}"   autocomplete="off"  style="display: inline;font-size: 15px" name="f1" method="post"  onsubmit="return checkform()" >
 servletformend -->
<!-- s2formstart -->
<form  name="f1" method="post"  onsubmit="return checkform()" action="/itivtmgr/exams/pviexamsxg.do?id=${id}" >
<!-- servletformend -->

<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>笔试查看</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">

<tr>
<th width='17%' align="center"><center>企业</center></td>
<td> ${updateentity.qyuname} </td>
</tr>
<tr>
<th width='17%' align="center"><center>应聘人</center></td>
<td> ${updateentity.touname} </td>
</tr>
<tr>
<th width='17%' align="center"><center>笔试标题</center></td>
<td> ${updateentity.etitle} </td>
</tr>
<tr>
<th width='17%' align="center"><center>题目</center></td>
<td>
<c:set var="frgroupno" value="${updateentity.frgroupno}" scope="request" />
<% String frgroupno = request.getAttribute("frgroupno").toString(); %>
<input type=hidden name='frgroupno' value='<%=frgroupno%>' />
<input type=hidden name='dytablename' value='examdtl' />
<jsp:include page='/examdtl/dytab_examdtl.do'>
<jsp:param name="frgroupno" value="<%=frgroupno %>" />
<jsp:param name="onlycheck" value="1" />
</jsp:include>

 </td>
</tr>
<tr>
<th width='17%' align="center"><center>发布时间</center></td>
<td> ${updateentity.savetime} </td>
</tr>

<tr>
<td colspan="2" align="center">

<input type=button value=' 返回上页 ' onclick="window.location.replace('${tobackurl}')" />
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
