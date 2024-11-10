﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
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

<form name="f1" autocomplete="off"  action="/itivtmgr/jobapply/jobapplycx.do"  method="post" style="display: inline;font-size: 14px">
<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>我申请的职位</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">

                <tr>
				  <td colspan=9 style="padding: 12px">   
				  
				   &nbsp;


 职位 
: 
<input type=text class=''  size=15 name='frtitle' />
&nbsp; 


 企业名称 
: 
<input type=text class=''  size=15 name='fqytname' />
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

<table><tr><td style='border: 0px;height: 1px;padding: 7px'></td></tr></table>&nbsp;

<input type=hidden  name='zdycol' id='zdycol' />
  
<input type=submit class='"+showbuttoncss+"' value=' 查询信息 ' />&nbsp;&nbsp;

<input type=button   class='' value=' 批量删除 ' onclick="batchDelete()" />&nbsp;&nbsp; 
 </td> 
				</tr> 
                <tr> 
					<th scope="col" width='3%'><input type="checkbox" value='' id='aplids' onclick='ckplids();' /></th>
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
 
					<td scope="col" width='3%' >
<c:if test="${ sdata.fshstatus eq '申请中'  }"> 

<input type='checkbox' value='${sdata.id}' name='plids' />
</c:if>
          </td>
 
					<td scope="col">${sdata.uname}</td> 
					<td scope="col">${sdata.tname}</td> 
					<td scope="col">${sdata.frtitle}</td> 
					<td scope="col">${sdata.fqytname}</td> 
					<td scope="col">${sdata.applyremo}</td> 
					<td scope="col">${sdata.fshstatus}</td> 
					<td scope="col">${sdata.savetime}</td> 
					<td scope="col">
<!----><a href="/itivtmgr/jobapply/topvijobapplyxg.do?spage=jobapplycx&id=${sdata.id}">查看</a>
  |  
<c:if test="${ sdata.fshstatus eq '申请中'  }">
<a onclick="return confirm('确定要删除这条记录吗?')" href="/itivtmgr/jobapply/delete.do?plids=${sdata.id}&surl=jobapplycx">删除</a>
</c:if>
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
<script language=javascript> 
function batchUpdate(col,cvalue,id){ 
if(countplids()) { 
f1.action='/itivtmgr/jobapply/customUpdate.do?surl=jobapplycx&col='+col+''; 
f1.zdycol.value=cvalue; 
f1.submit(); 
}
}
</script> 
<script language=javascript> 
function batchDelete(){ 
if(confirm('是否确认删除？')){ 
if(countplids()) { 
f1.action='/itivtmgr/jobapply/delete.do?surl=jobapplycx'; 
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
