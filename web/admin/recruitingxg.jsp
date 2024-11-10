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
<form  action="/itivtmgr/itivtmgr?ac=recruitingxg&id=${id}"   autocomplete="off"  style="display: inline;font-size: 15px" name="f1" method="post"  onsubmit="return checkform()" >
 servletformend -->
<!-- s2formstart -->
<form  name="f1" method="post"  onsubmit="return checkform()" action="/itivtmgr/recruiting/recruitingxg.do?id=${id}" > 
<!-- servletformend --> 

<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>信息查看</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">
  
<tr>
<th width='17%' align="center"><center>招聘标题</center></td> 
<td> <input type=text  onblur='checkform()'  class='' id='rtitle' name='rtitle' size=60 /><label  style='display:inline'  id='clabelrtitle' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>招聘类别</center></td> 
<td> <span id="infotypedanx"><label  style='display:inline' ><input type=radio name='infotype' value='社招' />&nbsp;&nbsp;社招 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio name='infotype' value='校招' />&nbsp;&nbsp;校招 </label>&nbsp;&nbsp;
</span> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>职位大类</center></td> 
<td> ${btypeselect}<label id='clabelbtype' />
 </td> 
</tr>
<tr>
<th width='17%' align="center"><center>职位细类</center></td> 
<td> ${stypeselect}<script language=javascript> 
function btypechange(){ 
document.getElementById("btype").onchange=btypechange; 
var btypevalue = document.getElementById("btype").value; 
var stypeobj = document.getElementById("stype"); 
stypeobj.options.length=0; 
var boption = new Option("不限",""); 
stypeobj.add(boption); 
var ajax = new AJAX(); 
ajax.post("/itivtmgr/ajax/getsonops.do?jl_parent_glzd=datashowname~hnoname&jl_parent_glb=postype&glb=postype&glzd=datashowname~hnoname&jlzd=btype&jlzdb=tglparentid&value="+btypevalue+"&ctype=select&ttime=<%=Info.getDateStr()%>"); 
//ajax.post(encodeURI("/itivtmgr/ajax/getsonops.do?jl_parent_glzd=datashowname~hnoname&jl_parent_glb=postype&glb=postype&glzd=datashowname~hnoname&jlzd=btype&jlzdb=tglparentid&value="+btypevalue+"&ctype=select&ttime=<%=Info.getDateStr()%>")); 
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
<label id='clabelstype' />
 </td> 
</tr>
<tr>
<th width='17%' align="center"><center>招聘人数</center></td> 
<td> <input type=text  onblur='checkform()'  class='' id='nums' name='nums' size=35 /><label  style='display:inline'  id='clabelnums' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>工作地点</center></td> 
<td> <input type=text  onblur='checkform()'  class='' id='addrs' name='addrs' size=60 /><label  style='display:inline'  id='clabeladdrs' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>学历要求</center></td> 
<td> <span id="educationdanx"><label  style='display:inline' ><input type=radio name='education' value='初中' />&nbsp;&nbsp;初中 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio name='education' value='高中' />&nbsp;&nbsp;高中 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio name='education' value='大专' />&nbsp;&nbsp;大专 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio name='education' value='本科' />&nbsp;&nbsp;本科 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio name='education' value='硕士' />&nbsp;&nbsp;硕士 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio name='education' value='博士' />&nbsp;&nbsp;博士 </label>&nbsp;&nbsp;
</span> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>技能要求</center></td> 
<td> <textarea  cols='45' rows='3'  onblur='checkform()'   style='margin-top: 1px;margin-bottom: 1px'  class=''   name='skills'   ></textarea> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>薪资待遇</center></td> 
<td> <input type=text  onblur='checkform()'  class='' id='sal' name='sal' size=60 /><label  style='display:inline'  id='clabelsal' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>其他要求</center></td> 
<td> <textarea  cols='45' rows='3'  onblur='checkform()'   style='margin-top: 1px;margin-bottom: 1px'  class=''   name='remo'   ></textarea> </td> 
</tr>
 
<tr>
<td colspan="2" align="center"> 
<label>
<input type="button" onclick="if(checkform()){f1.submit();}" name="button" id="button" value=" 提交信息 " />
</label>
&nbsp;&nbsp;&nbsp;&nbsp;                     
<input type=button value=' 返回上页 ' onclick='window.location.replace("/itivtmgr/recruiting/recruitingcx.do")' />   
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
btypechange();
 
</script>  
<script language=javascript >  
 function checkform(){  
var rtitleobj = document.getElementById("rtitle");  
if(rtitleobj.value==""){  
document.getElementById("clabelrtitle").innerHTML="&nbsp;&nbsp;<font color=red>请输入招聘标题</font>";  
return false;  
}else{ 
document.getElementById("clabelrtitle").innerHTML="  ";  
}  
var btypeobj = document.getElementById("btype");  
if(btypeobj.value==""){  
document.getElementById("clabelbtype").innerHTML="&nbsp;&nbsp;<font color=red>请输入职位大类</font>";  
return false;  
}else{ 
document.getElementById("clabelbtype").innerHTML="  ";  
}  
var stypeobj = document.getElementById("stype");  
if(stypeobj.value==""){  
document.getElementById("clabelstype").innerHTML="&nbsp;&nbsp;<font color=red>请输入职位细类</font>";  
return false;  
}else{ 
document.getElementById("clabelstype").innerHTML="  ";  
}  
var numsobj = document.getElementById("nums");  
if(numsobj.value==""){  
document.getElementById("clabelnums").innerHTML="&nbsp;&nbsp;<font color=red>请输入招聘人数</font>";  
return false;  
}else{ 
document.getElementById("clabelnums").innerHTML="  ";  
}  
var addrsobj = document.getElementById("addrs");  
if(addrsobj.value==""){  
document.getElementById("clabeladdrs").innerHTML="&nbsp;&nbsp;<font color=red>请输入工作地点</font>";  
return false;  
}else{ 
document.getElementById("clabeladdrs").innerHTML="  ";  
}  
var salobj = document.getElementById("sal");  
if(salobj.value==""){  
document.getElementById("clabelsal").innerHTML="&nbsp;&nbsp;<font color=red>请输入薪资待遇</font>";  
return false;  
}else{ 
document.getElementById("clabelsal").innerHTML="  ";  
}  
return true;   
}   
</script>  
${fillForm} 
<script language=javascript >  
btypechange();
 
</script>  
${fillForm} 
