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

<!-- servletformstart 
<form  action="/itivtmgr/itivtmgr?ac=chargestj&tglparentid=${tglparentid}"   autocomplete="off"  style="display: inline;font-size: 15px" name="f1" method="post"  onsubmit="return checkform()" >
 servletformend -->
<!-- s2formstart -->
<form name="f1" method="post" onsubmit="return checkform()" action="/itivtmgr/charges/chargestj.do?tglparentid=${tglparentid}" > 
<!-- servletformend --> 

<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>信息添加</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">
  
<tr>
<th width='17%' align="center"><center>服务类别</center></td> 
<td> <span id="stypedanx"><label  style='display:inline' ><input type=radio checked=checked name='stype' id='stype0' value='面试咨询' />&nbsp;面试咨询 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio  name='stype' id='stype1' value='简历升级' />&nbsp;简历升级 </label>&nbsp;&nbsp;
</span> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>支付方式</center></td> 
<td> <span id="atypedanx"><label  style='display:inline' ><input type=radio checked=checked name='atype' id='atype0' value='支付宝' />&nbsp;支付宝 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio  name='atype' id='atype1' value='微信' />&nbsp;微信 </label>&nbsp;&nbsp;
<label  style='display:inline' ><input type=radio  name='atype' id='atype2' value='银行卡' />&nbsp;银行卡 </label>&nbsp;&nbsp;
</span> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>付款账户</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='acct' name='acct' size=35 /><label style='display:inline' id='clabelacct' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>付款密码</center></td> 
<td> <input type=text  onblur='checkform()' class='' id='apass' name='apass' size=35 /><label style='display:inline' id='clabelapass' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>付款金额</center></td> 
<td> <input type=text autocomplete='off'  size='8' class=''  id='amt'  name='amt'  onkeyup='clearNoNum(this);' onblur='clearNoNum(this);checkform();' onmouseup='clearNoNum(this);'    /><label id='clabelamt' /> </td> 
</tr>
<tr>
<th width='17%' align="center"><center>简述</center></td> 
<td> <textarea  cols='65' rows='5'  style='margin-top: 1px;margin-bottom: 1px'  class=''  name='remo'  ></textarea> </td> 
</tr>
 
<tr>
<td colspan="2" align="center"> 
<label>
<input type="button" onclick="if(checkform()){f1.submit();}" name="button" id="button" value=" 提交信息 " />
</label>
&nbsp;&nbsp;&nbsp;&nbsp;                     
<input type=button value=' 返回上页 ' onclick='window.location.replace("/itivtmgr/charges/chargescx.do")' />   
 </td> 
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
            
<script language=javascript src='/itivtmgr/js/My97DatePicker/WdatePicker.js'></script>
<script language=javascript src='/itivtmgr/js/popup.js'></script>
<script language=javascript src='/itivtmgr/js/ajax.js'></script>
<script language=javascript src='/itivtmgr/js/jquery-3.3.1.min.js'></script>
<%@page import="util.Info"%>
<%@page import="util.Info"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.HashMap"%> 
<%@page import="util.PageManager"%> 
<%@page import="dao.CommDAO"%> 
<script language=javascript >  
 
 function checkform(){  
var amtobj = document.getElementById("amt");  
if(amtobj.value==""){  
document.getElementById("clabelamt").innerHTML="&nbsp;&nbsp;<font color=red>请输入付款金额</font>";  
return false;  
}else{
document.getElementById("clabelamt").innerHTML="  ";  
}  
  
return true;   
}   
</script>  
${applyinsertstr}