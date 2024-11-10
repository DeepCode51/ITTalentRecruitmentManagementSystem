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

<form name="f1" autocomplete="off"  action="/itivtmgr/offers/fshofferscx.do"  method="post" style="display: inline;font-size: 14px">
<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>查看OFFER</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">

                <tr>
				  <td colspan=9 style="padding: 12px">   
				  
				   &nbsp;


 会员 
: 
<input type=text class=''  size=15 name='touname' />
&nbsp; 


 标题 
: 
<input type=text class=''  size=15 name='otitle' />
&nbsp; 


 职位 
: 
<input type=text class=''  size=15 name='position' />
&nbsp; 

 
发送时间  : 
 
<input type=text class=''  size=9 name='startsavetime' onclick='WdatePicker();' />
 至 

<input type=text class=''  size=9 name='endsavetime' onclick='WdatePicker();' />
&nbsp; 

<input type=hidden  name='zdycol' id='zdycol' />
  
<input type=submit class='"+showbuttoncss+"' value=' 查询信息 ' />&nbsp;&nbsp;


  </td> 
				</tr> 
                <tr> 
					<th scope="col">企业</th>
<th scope="col">企业用户名</th>
<th scope="col">会员</th>
<th scope="col">标题</th>
<th scope="col">职位</th>
<th scope="col">附件</th>
<th scope="col">发送时间</th>
<th scope="col">操作</th> 
				</tr> 
				
<c:forEach items="${slist}" var="sdata" varStatus="sta">
<tr>
 
					<td scope="col">${sdata.qyuname}</td> 
					<td scope="col">${sdata.qytname}</td> 
					<td scope="col">${sdata.touname}</td> 
					<td scope="col">${sdata.otitle}</td> 
					<td scope="col">${sdata.position}</td> 
					<td scope="col"><a href="/itivtmgr//itivtmgr/download.do?filename=${sdata.docname}" title='点击可以下载'>${sdata.docname}</a></td> 
					<td scope="col">${sdata.savetime}</td> 
					<td scope="col">
<!----><a href="/itivtmgr/offers/topvioffersxg.do?spage=fshofferscx&id=${sdata.id}">查看</a>
  |
<a onclick="return confirm('确定要删除这条记录吗?')" href="/itivtmgr/offers/delete.do?plids=${sdata.id}&surl=fshofferscx">删除</a>
</td>
</tr>
</c:forEach> 
				<tr> 
					<td colspan=9 align="center">${page.info}</td> 
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
pop('offersxg.jsp?id='+no,'信息修改',550,'100%') 
}
</script> 
<script language=javascript> 
function add(){ 
pop('offerstj.jsp','信息添加',550,'100%') 
}
</script> 
<%@page import="util.Info"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.HashMap"%> 
<%@page import="util.PageManager"%> 
<%@page import="dao.CommDAO"%> 
