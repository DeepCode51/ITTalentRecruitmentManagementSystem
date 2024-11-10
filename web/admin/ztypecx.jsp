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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

<form name="f1" autocomplete="off"  action="/itivtmgr/ztype/ztypecx.do"  method="post" style="display: inline;font-size: 14px">
<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>专业领域</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">

                <tr>
				  <td colspan=5 style="padding: 12px">   
				  
				   &nbsp;


 名称 
: 
<input type=text class=''  size=15 name='zname' />
&nbsp; 

<input type=hidden  name='zdycol' id='zdycol' />
  
<input type=submit class='"+showbuttoncss+"' value=' 查询信息 ' />&nbsp;&nbsp;


  
<input type=button   class='' value=' 添加信息 ' onclick="window.location.replace('/itivtmgr/ztype/toztypetj.do?topage=ztypetj')" />&nbsp;&nbsp; 
 
<input type=button   class='' value=' 批量删除 ' onclick="batchDelete()" />&nbsp;&nbsp; 
 </td> 
				</tr> 
                <tr> 
					<th scope="col" width='3%'><input type="checkbox" value='' id='aplids' onclick='ckplids();' /></th>
<th scope="col">名称</th>
<th scope="col">操作</th> 
				</tr> 
				
<c:forEach items="${slist}" var="sdata" varStatus="sta">
<tr>
 
					<td scope="col" width='3%' >
<input type='checkbox' value='${sdata.id}' name='plids' />
          </td>
 
					<td scope="col">${sdata.zname}</td> 
					<td scope="col">
<!----><a href="/itivtmgr/ztype/toztypexg.do?id=${sdata.id}&topage=ztypexg">修改</a>
 | 
<a onclick="return confirm('确定要删除这条记录吗?')" href="/itivtmgr/ztype/delete.do?plids=${sdata.id}&surl=ztypecx">删除</a>
</td>
</tr>
</c:forEach> 
				<tr> 
					<td colspan=5 align="center">${page.info}</td> 
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
            
<script language=javascript src='/itivtmgr/js/ajax.js'></script>
${fillForm} 
<script language=javascript >  
 
</script>  
${fillForm} 
<script language=javascript src='/itivtmgr/js/My97DatePicker/WdatePicker.js'></script>
<script language=javascript src='/itivtmgr/js/popup.js'></script>
<script language=javascript src='/itivtmgr/js/jquery-3.3.1.min.js'></script>
<script language=javascript> 
function update(no){ 
pop('ztypexg.jsp?id='+no,'信息修改',550,'100%') 
}
</script> 
<script language=javascript> 
function add(){ 
pop('ztypetj.jsp','信息添加',550,'100%') 
}
</script> 
<script language=javascript> 
function batchUpdate(col,cvalue,id){ 
if(countplids()) { 
f1.action='/itivtmgr/ztype/customUpdate.do?surl=ztypecx&col='+col+''; 
f1.zdycol.value=cvalue; 
f1.submit(); 
}
}
</script> 
<script language=javascript> 
function batchDelete(){ 
if(confirm('是否确认删除？')){ 
if(countplids()) { 
f1.action='/itivtmgr/ztype/delete.do?surl=ztypecx'; 
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
