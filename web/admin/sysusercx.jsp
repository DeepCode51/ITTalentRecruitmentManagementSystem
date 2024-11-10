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

<form name="f1" autocomplete="off"  action="sysusercx.jsp"  method="post" style="display: inline;font-size: 14px">
<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>站内资讯管理</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">

                <tr>
				  <td colspan=4 style="padding: 12px">   
				  
				   &nbsp;
<% 
String puname = request.getParameter("uname")==null?"":request.getParameter("uname"); 
String startsavetime = request.getParameter("startsavetime")==null?"":request.getParameter("startsavetime"); 
String endsavetime = request.getParameter("endsavetime")==null?"":request.getParameter("endsavetime"); 
   %>

<%
   new CommDAO().delete(request,"sysuser"); 
   HashMap mmm = new HashMap();%> 

&nbsp;
发布人 
: 
<input type=text class=''  size=15 name='uname' />
&nbsp;

&nbsp;
发布时间 :
 
<input type=text class=''  size=8 name='startsavetime' onclick='WdatePicker();' />
&nbsp;至&nbsp;

<input type=text class=''  size=8 name='endsavetime' onclick='WdatePicker();' />
&nbsp;

<input type=submit class='' value='查询信息' />
 &nbsp;

  
<input type=button   class='' value='添加信息' onclick="window.location.replace('sysusertj.jsp')" /> 
&nbsp;</td> 
				</tr> 
                <tr> 
					<th scope="col">发布人</th>
<th scope="col">发布时间</th>
<th scope="col">操作</th> 
				</tr> 
				<% 
if(request.getParameter("statusid1")!=null){ 
 new CommDAO().commOper("update sysuser set status ='锁定' where id="+request.getParameter("statusid1"));  
} 
if(request.getParameter("statusid2")!=null){ 
 new CommDAO().commOper("update sysuser set status ='正常' where id="+request.getParameter("statusid2"));  
} 
String sql = "select * from sysuser where 1=1 " ;
 if(!puname.equals("")){ 
 sql+= " and uname like'%"+puname+"%' " ;
 }  
 if(!startsavetime.equals("")){  
 mmm.put("startsavetime",startsavetime) ;
 sql+= " and savetime >'"+startsavetime+"' " ;
 }  
 if(!endsavetime.equals("")){  
 mmm.put("endsavetime",endsavetime) ;
 sql+= " and savetime <'"+Info.getDay(endsavetime,1)+"' " ;
 }  
  sql +="  and (1!=1 " ; 
  sql +="  or  utype like'%会员%' " ;
  sql +="  ) ";
  sql +=" order by id desc ";
String url = "sysusercx.jsp?1=1&uname="+puname+"&savetime="+startsavetime+"&savetime="+endsavetime+""; 
ArrayList<HashMap> list = PageManager.getPages(url,5, sql, request ); 
for(HashMap map:list){ %>
<tr>
 
					<td scope="col"><%=map.get("uname")%></td> 
					<td scope="col"><%=map.get("savetime")%></td> 
					<td scope="col">
<a href="sysusercx.jsp?value=锁定&statusid1=<%=map.get("id")%>">锁定</a>
 | 
<a href="sysusercx.jsp?value=正常&statusid2=<%=map.get("id")%>">解锁</a>
 | 

<a href="sysuserxg.jsp?id=<%=map.get("id")%>">修改</a>
 | 

<a onclick="return confirm('确定要删除这条记录吗?')" href="sysusercx.jsp?plids=<%=map.get("id")%>">删除</a>
</td>
</tr>
<%}%> 
				<tr> 
					<td colspan=4 align="center">${page.info}</td> 
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
<% 
mmm.put("uname",puname); 
mmm.put("startsavetime",startsavetime); 
mmm.put("endsavetime",endsavetime); 
%>
<%=Info.tform(mmm)%> 
<script language=javascript >  
 
</script>  
<%=Info.tform(mmm)%> 
<script language=javascript src='/itivtmgr/js/My97DatePicker/WdatePicker.js'></script>
<script language=javascript src='/itivtmgr/js/popup.js'></script>
<script language=javascript> 
function update(no){ 
pop('sysuserxg.jsp?id='+no,'信息修改',550,'100%') 
}
</script> 
<script language=javascript> 
function add(){ 
pop('sysusertj.jsp','信息添加',550,'100%') 
}
</script> 
<%@page import="util.Info"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.HashMap"%> 
<%@page import="util.PageManager"%> 
<%@page import="dao.CommDAO"%> 
