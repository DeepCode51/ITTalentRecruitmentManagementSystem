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

<!-- servletformstart 
<form  action="/itivtmgr/itivtmgr?ac=pvisysuserxg&id=${id}"   autocomplete="off"  style="display: inline;font-size: 15px" name="f1" method="post"  onsubmit="return checkform()" >
 servletformend -->
<!-- s2formstart -->
<form  name="f1" method="post"  onsubmit="return checkform()" action="/itivtmgr/sysuser/pvisysuserxg.do?id=${id}" > 
<!-- servletformend --> 

<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>查看学生</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">
  
<tr>
<th width='17%' align="center"><center>发布人</center></td> 
<td> ${updateentity.uname}
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:window.history.go(-1)">【返回上页】</a>
</td>
</tr>
<tr>
<th width='17%' align="center"><center>类别</center></td> 
<td> ${updateentity.utype} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>企业名称</center></td> 
<td> ${updateentity.tname} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>性别</center></td> 
<td> ${updateentity.sex} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>邮箱</center></td> 
<td> ${updateentity.email} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>联系电话</center></td> 
<td> ${updateentity.tel} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>地址</center></td> 
<td> ${updateentity.addrs} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>户籍</center></td> 
<td> ${updateentity.houhold} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>生日</center></td> 
<td> ${updateentity.birth} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>体重</center></td> 
<td> ${updateentity.wei} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>身高</center></td> 
<td> ${updateentity.hei} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>健康状况</center></td> 
<td> ${updateentity.health} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>毕业院校</center></td> 
<td> ${updateentity.school} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>专业领域</center></td>
<td> ${updateentity.specialty} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>学历</center></td> 
<td> ${updateentity.education} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>毕业日期</center></td> 
<td> ${updateentity.cbdate} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>个人技能</center></td> 
<td> ${updateentity.skill} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>兴趣爱好</center></td> 
<td> ${updateentity.hobby} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>工作经历</center></td> 
<td> ${updateentity.experience} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>外语技能</center></td> 
<td> ${updateentity.languages} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>求职意向</center></td> 
<td> ${updateentity.intention} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>简介</center></td> 
<td> ${updateentity.remo} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>发布时间</center></td> 
<td> ${updateentity.savetime} </td> 
</tr>
<tr>
<th width='17%' align="center"><center>图片</center></td> 
<td> <img src='/itivtmgr/upfile/${updateentity.filename}' height='80' style="border-radius: 0px;height: 120px" /> </td>
</tr>
<tr>
<th width='17%' align="center"><center>相关证书</center></td> 
<td> <img src='/itivtmgr/upfile/${updateentity.filename2}' height='80' style="border-radius: 0px;height: 120px" /> </td>
</tr>
<tr>
<th width='17%' align="center"><center>电子档简历</center></td>
<td> <a href='/itivtmgr/itivtmgr/download.do?filename=${updateentity.docname3}'>${updateentity.docname3}</a> </td>
</tr>
 
<tr>
<td colspan="2" align="center"> 
                     

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
<script language=javascript src='/itivtmgr/js/ajax.js'></script> 
<script language=javascript src='/itivtmgr/js/popup.js'></script> 
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
