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

<form name="f1" autocomplete="off"  action="/itivtmgr/jobapply/fshjobapplycx.do"  method="post" style="display: inline;font-size: 14px">
<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>查看职位申请</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">

                <tr>
				  <td colspan=9 style="padding: 12px">   
				  
				   &nbsp;


 用户名 
: 
<input type=text class=''  size=12 name='uname' />
&nbsp; 


 姓名 
: 
<input type=text class=''  size=12 name='tname' />
&nbsp; 


 职位 
: 
<input type=text class=''  size=12 name='frtitle' />
&nbsp; 


 企业名称 
: 
<input type=text class=''  size=12 name='fqytname' />
&nbsp; 
受理状态 
 : 
<select name='fshstatus'>
<option value="">不限</option>
<option value='申请中'>申请中</option> 
<option value='邀请面试'>邀请面试</option>
<option value='邀请笔试'>邀请笔试</option>
<option value='不太合适'>不太合适</option>
</select>
&nbsp; 

 
申请时间  : 
 
<input type=text class=''  size=9 name='startsavetime' onclick='WdatePicker();' />
 至 

<input type=text class=''  size=9 name='endsavetime' onclick='WdatePicker();' />
&nbsp; 

<input type=hidden  name='zdycol' id='zdycol' />
  
<input type=submit class='"+showbuttoncss+"' value=' 查询 ' />


  </td> 
				</tr> 
                <tr> 
					<th scope="col">用户名</th>
<th scope="col">姓名</th>
<th scope="col">职位</th>
<th scope="col">企业名称</th>
<th scope="col">申请信息</th>
<th scope="col">受理状态</th>
<th scope="col">申请时间</th>
<th scope="col">操作</th> 
				</tr> 
				
<c:forEach items="${slist}" var="sdata" varStatus="sta">
<tr>
 
					<td scope="col">${sdata.uname}</td> 
					<td scope="col">${sdata.tname}</td> 
					<td scope="col">${sdata.frtitle}</td> 
					<td scope="col">${sdata.fqytname}</td> 
					<td scope="col">${sdata.applyremo}</td> 
					<td scope="col">${sdata.fshstatus}</td> 
					<td scope="col">${sdata.savetime}</td> 
					<td scope="col">
<!----><a href="/itivtmgr/sysuser/topvisysuserxg.do?spage=fshjobapplycx&uname=${sdata.uname}">简历</a>
  |  
<a href="/itivtmgr/jobapply/tofshjobapplyxg.do?id=${sdata.id}">处理</a>
 | 
<a onclick="return confirm('确定要删除这条记录吗?')" href="/itivtmgr/jobapply/delete.do?plids=${sdata.id}&surl=fshjobapplycx">删除</a>
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
pop('jobapplyxg.jsp?id='+no,'信息修改',550,'100%') 
}
</script> 
<script language=javascript> 
function add(){ 
pop('jobapplytj.jsp','信息添加',550,'100%') 
}
</script> 
<%@page import="util.Info"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.HashMap"%> 
<%@page import="util.PageManager"%> 
<%@page import="dao.CommDAO"%> 
