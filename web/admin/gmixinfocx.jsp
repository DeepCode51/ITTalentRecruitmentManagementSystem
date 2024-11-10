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

<form name="f1" autocomplete="off"  action="/itivtmgr/mixinfo/gmixinfocx.do"  method="post" style="display: inline;font-size: 14px">
<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>滚动图片</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">


                <tr>
<th scope="col">标题</th>
<th scope="col">图片</th>
<th scope="col">操作</th> 
				</tr> 
				
<c:forEach items="${slist}" var="sdata" varStatus="sta">
<tr>
 

 
					<td scope="col">${sdata.mtitle}</td> 
					<td scope="col"><img src="/itivtmgr/upfile/${sdata.filename}" style="border-radius: 0px;height: 100px;width: 290px" /></td>
					<td scope="col">
<!----><a href="/itivtmgr/mixinfo/togmixinfoxg.do?id=${sdata.id}">修改</a>
</td>
</tr>
</c:forEach> 
				<tr> 
					<td colspan=4 align="center"> </td>
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
            
<script language=javascript src='/itivtmgr/js/ajax.js'></script>
${fillForm} 
<script language=javascript >  
 
</script>  
${fillForm} 
<script language=javascript src='/itivtmgr/js/My97DatePicker/WdatePicker.js'></script>
<script language=javascript src='/itivtmgr/js/popup.js'></script>
<script language=javascript> 
function update(no){ 
pop('gmixinfoxg.jsp?id='+no,'信息修改',550,'100%') 
}
</script> 
<script language=javascript> 
function add(){ 
pop('mixinfotj.jsp','信息添加',550,'100%') 
}
</script> 
<script language=javascript> 
function batchUpdate(col,cvalue,id){ 
if(countplids()) { 
f1.action='/itivtmgr/mixinfo/customUpdate.do?surl=gmixinfocx&col='+col+''; 
f1.zdycol.value=cvalue; 
f1.submit(); 
}
}
</script> 
<script language=javascript> 
function batchDelete(){ 
if(confirm('是否确认删除？')){ 
if(countplids()) { 
f1.action='/itivtmgr/mixinfo/delete.do?surl=gmixinfocx'; 
f1.submit(); 
}
}
}
</script> 
<%@page import="util.Info"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.HashMap"%> 
<%@page import="util.PageManager"%> 
<%@page import="dao.CommDAO"%> 
