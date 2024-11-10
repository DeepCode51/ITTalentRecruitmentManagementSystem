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

<form name="f1" autocomplete="off"  action="/itivtmgr/recruiting/fshrecruitingcx.do"  method="post" style="display: inline;font-size: 14px">
<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>招聘信息管理</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">

                <tr>
				  <td colspan=11 style="padding: 12px">   
				  
				   &nbsp;


 企业用户名 
: 
<input type=text class=''  size=15 name='qyuname' />
&nbsp; 


 企业名称 
: 
<input type=text class=''  size=15 name='qytname' />
&nbsp; 


 招聘标题 
: 
<input type=text class=''  size=15 name='rtitle' />
&nbsp; 
招聘类别 
 : 
<select name='infotype'>
<option value="">不限</option>
<option value='社招'>社招</option> 
<option value='校招'>校招</option> 
</select>

					  <table><tr><td style='border: 0px;height: 1px;padding: 7px'></td></tr></table>&nbsp;

职位大类 
 : 
${btypeselect}&nbsp; 
职位细类 
 : 
${stypeselect}&nbsp; 
<script language=javascript> 
function btypechange(){ 
document.getElementById("btype").onchange=btypechange; 
var btypevalue = document.getElementById("btype").value; 
var stypeobj = document.getElementById("stype"); 
stypeobj.options.length=0; 
var boption = new Option("不限",""); 
stypeobj.add(boption); 
var ajax = new AJAX(); 
ajax.post(encodeURI("/itivtmgr/ajax/getsonops.do?jl_parent_glzd=datashowname&jl_parent_glb=postype&glb=postype&glzd=datashowname&jlzd=btype&jlzdb=tglparentid&value="+btypevalue+"&ctype=select&ttime=<%=Info.getDateStr()%>")); 
//ajax.post("/itivtmgr/ajax/getsonops.do?jl_parent_glzd=datashowname&jl_parent_glb=postype&glb=postype&glzd=datashowname&jlzd=btype&jlzdb=tglparentid&value="+btypevalue+"&ctype=select&ttime=<%=Info.getDateStr()%>"); 
var msg = ajax.getValue(); 
var msgs = msg.split("@@@"); 
for(var i=1;i<msgs.length;i++){ 
if(msgs[i]!=""){ 
var option = new Option(msgs[i],msgs[i]); 
stypeobj.add(option); 
} 
} 
} 
</script> 

 
发布时间  : 
 
<input type=text class=''  size=9 name='startsavetime' onclick='WdatePicker();' />
 至 

<input type=text class=''  size=9 name='endsavetime' onclick='WdatePicker();' />
&nbsp; 

<input type=hidden  name='zdycol' id='zdycol' />
  
<input type=submit class='"+showbuttoncss+"' value=' 查询信息 ' />&nbsp;&nbsp;


  </td> 
				</tr> 
                <tr> 
					<th scope="col">企业用户名</th>
<th scope="col">企业名称</th>
<th scope="col">招聘标题</th>
<th scope="col">招聘类别</th>
<th scope="col">职位大类</th>
<th scope="col">职位细类</th>
<th scope="col">招聘人数</th>
<th scope="col">学历要求</th>
<th scope="col">发布时间</th>
<th scope="col">操作</th> 
				</tr> 
				
<c:forEach items="${slist}" var="sdata" varStatus="sta">
<tr>
 
					<td scope="col">${sdata.qyuname}</td> 
					<td scope="col">${sdata.qytname}</td> 
					<td scope="col">${sdata.rtitle}</td> 
					<td scope="col">${sdata.infotype}</td> 
					<td scope="col">${sdata.btype}</td> 
					<td scope="col">${sdata.stype}</td> 
					<td scope="col">${sdata.nums}</td> 
					<td scope="col">${sdata.education}</td> 
					<td scope="col">${sdata.savetime}</td> 
					<td scope="col">
<!----><a href="/itivtmgr/recruiting/topvirecruitingxg.do?spage=fshrecruitingcx&id=${sdata.id}">查看</a>
  |
<a onclick="return confirm('确定要删除这条记录吗?')" href="/itivtmgr/recruiting/delete.do?plids=${sdata.id}&surl=fshrecruitingcx">删除</a>
</td>
</tr>
</c:forEach> 
				<tr> 
					<td colspan=11 align="center">${page.info}</td> 
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
btypechange();
 
</script>  
${fillForm} 
<script language=javascript src='/itivtmgr/js/My97DatePicker/WdatePicker.js'></script>
<script language=javascript src='/itivtmgr/js/popup.js'></script>
<script language=javascript> 
function update(no){ 
pop('recruitingxg.jsp?id='+no,'信息修改',550,'100%') 
}
</script> 
<script language=javascript> 
function add(){ 
pop('recruitingtj.jsp','信息添加',550,'100%') 
}
</script> 
<%@page import="util.Info"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.HashMap"%> 
<%@page import="util.PageManager"%> 
<%@page import="dao.CommDAO"%> 
