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

<form name="f1" autocomplete="off"  action="/itivtmgr/itivtmgr/rctjsysusercx.do"  method="post" style="display: inline;font-size: 14px">
<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>人才推荐</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">

                <tr>
				  <td colspan=10 style="padding: 12px">   
				  
				   &nbsp;


 用户名 
: 
<input type=text class=''  size=15 name='uname' />
&nbsp; 


 姓名 
: 
<input type=text class=''  size=15 name='tname' />
&nbsp; 
性别 
 : 
<select name='sex'>
<option value="">不限</option>
<option value='男'>男</option> 
<option value='女'>女</option> 
</select>
&nbsp; 


 邮箱 
: 
<input type=text class=''  size=15 name='email' />
&nbsp; 


 联系电话 
: 
<input type=text class=''  size=15 name='tel' />
&nbsp; 


 地址 
: 
<input type=text class=''  size=15 name='addrs' />

					  <table><tr><td style='border: 0px;height: 1px;padding: 7px'></td></tr></table>

&nbsp; 


 毕业院校 
: 
<input type=text class=''  size=15 name='school' />



&nbsp; 
学历 
 : 
<select name='education'>
<option value="">不限</option>
<option value='初中'>初中</option> 
<option value='高中'>高中</option> 
<option value='大专'>大专</option> 
<option value='本科'>本科</option> 
<option value='硕士'>硕士</option> 
<option value='博士'>博士</option> 
</select>
&nbsp; 

 
毕业日期  : 
 
<input type=text class=''  size=9 name='startcbdate' onclick='WdatePicker();' />
 至 

<input type=text class=''  size=9 name='endcbdate' onclick='WdatePicker();' />
&nbsp; 


 个人技能 
: 
<input type=text class=''  size=15 name='skill' />
&nbsp; 


 外语技能 
: 
<input type=text class=''  size=15 name='languages' />
&nbsp; 



<input type=hidden  name='zdycol' id='zdycol' />
  
<input type=submit class='"+showbuttoncss+"' value=' 查询 ' />
 </td> 
				</tr> 
                <tr> 
					<th scope="col" width='3%'><input type="checkbox" value='' id='aplids' onclick='ckplids();' /></th>
<th scope="col">用户名</th>
<th scope="col">姓名</th>
<th scope="col">性别</th>
<th scope="col">联系电话</th>
<th scope="col">毕业院校</th>
<th scope="col">专业领域</th>
<th scope="col">学历</th>
<th scope="col">毕业日期</th>
<th scope="col">操作</th> 
				</tr> 
				
<c:forEach items="${slist}" var="sdata" varStatus="sta">
<tr>
 
					<td scope="col" width='3%' >
<input type='checkbox' value='${sdata.id}' name='plids' />
          </td>
 
					<td scope="col">${sdata.uname}</td> 
					<td scope="col">${sdata.tname}</td> 
					<td scope="col">${sdata.sex}</td> 
					<td scope="col">${sdata.tel}</td> 
					<td scope="col">${sdata.school}</td> 
					<td scope="col">${sdata.specialty}</td> 
					<td scope="col">${sdata.education}</td> 
					<td scope="col">${sdata.cbdate}</td> 
					<td scope="col">
						<a href="/itivtmgr/sysuser/topvisysuserxg.do?id=${sdata.id}">查看</a>
</td>
</tr>
</c:forEach> 
				<tr> 
					<td colspan=10 align="center">${page.info}</td> 
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
pop('sysuserxg.jsp?id='+no,'信息修改',550,'100%') 
}
</script> 
<script language=javascript> 
function add(){ 
pop('sysusertj.jsp','信息添加',550,'100%') 
}
</script> 
<script language=javascript> 
function batchUpdate(col,cvalue,id){ 
if(countplids()) { 
f1.action='/itivtmgr/sysuser/customUpdate.do?surl=rctjsysusercx&col='+col+''; 
f1.zdycol.value=cvalue; 
f1.submit(); 
}
}
</script> 
<script language=javascript> 
function batchDelete(){ 
if(confirm('是否确认删除？')){ 
if(countplids()) { 
f1.action='/itivtmgr/sysuser/delete.do?surl=rctjsysusercx'; 
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
