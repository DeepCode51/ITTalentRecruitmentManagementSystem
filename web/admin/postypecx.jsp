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

<form name="f1" autocomplete="off"  action="postypecx.jsp"  method="post" style="display: inline;font-size: 14px">
<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>职位类别管理</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">

				<tr> 
					<th scope="col">大类</th>
					<th scope="col">小类</th>
				</tr>

				<c:forEach items="${slist}" var="sdata" varStatus="sta"> 
				<c:set var="erliststr" value="erlist_${sdata.id}" />
				<tr> 
					<td>
<a href="javascript:update('${sdata.id}')">${sdata.datashowname}</a>
&nbsp;
<a href="/itivtmgr/postype/delete.do?plids=${sdata.id}&surl=postypecx" onclick="return confirm('确定要删除吗?')" >
<font color=gray>[删除]</font>
</a> 
</td>
<td> 
<c:forEach items="${ermap[erliststr]}" var="erdata" varStatus="ersta"> 
<a href="javascript:update('${erdata.id}')">${erdata.datashowname}</a>
&nbsp;
<a href="/itivtmgr/postype/delete.do?plids=${erdata.id}&surl=postypecx" onclick="return confirm('确定要删除吗?')" >
<font color=gray>[删除]</font>
</a>
&nbsp;&nbsp;&nbsp; 
</c:forEach>
&nbsp;&nbsp;<a href="javascript:add('${sdata.id}')">[点击添加]</a> 
</td> 
				</tr>
			</c:forEach>
			
<tr>
<td align=left  colspan=2>   
<a href="javascript:add('-1')">[点击添加大类]</a> 
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
<script language=javascript> 
function update(no){ 
window.location.replace('/itivtmgr/postype/topostypexg.do?erjitype=erjitype&id='+no); 
}
</script> 
<script language=javascript> 
function add(no){ 
window.location.replace('/itivtmgr/postype/topostypetj.do?tglparentid='+no); 
}
</script> 
<%@page import="util.Info"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.HashMap"%> 
<%@page import="util.PageManager"%> 
<%@page import="dao.CommDAO"%> 
