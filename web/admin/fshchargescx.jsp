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

<form name="f1" autocomplete="off"  action="/itivtmgr/charges/fshchargescx.do"  method="post" style="display: inline;font-size: 14px">
<div class="ms-panel">
	<div class="ms-panel-header">
		<h6>付费服务</h6>
	</div>
	<div class="ms-panel-body">

		<div class="table-responsive">
			<table class="table table-bordered thead-primary">

                <tr>
				  <td colspan=11 style="padding: 12px">

				   &nbsp;


 会员
:
<input type=text class=''  size=15 name='uname' />
&nbsp;
服务类别
 :
<select name='stype'>
<option value="">不限</option>
<option value='面试咨询'>面试咨询</option>
<option value='简历升级'>简历升级</option>
</select>
&nbsp;


发布时间  :

<input type=text class='' autocomplete='off' style='width:90px' size=9 name='startsavetime' onclick='WdatePicker();' />
 至

<input type=text class='' autocomplete='off' style='width:90px' size=9 name='endsavetime' onclick='WdatePicker();' />
&nbsp;

<input type=hidden  name='zdycol' id='zdycol' />

<input type=submit class='"+showbuttoncss+"' value=' 查询信息 ' />&nbsp;&nbsp;


  </td>
				</tr>
                <tr>
					<th scope="col">会员</th>
<th scope="col">服务类别</th>
<th scope="col">支付方式</th>
<th scope="col">付款账户</th>
<th scope="col">付款金额</th>
<th scope="col">简述</th>
<th scope="col">发布时间</th>
<th scope="col">操作</th>
				</tr>

<c:forEach items="${slist}" var="sdata" varStatus="sta">
<tr>

					<td scope="col">${sdata.uname} <font color="grey">[${sdata.uname_tname}]</font></td>
					<td scope="col">${sdata.stype}</td>
					<td scope="col">${sdata.atype}</td>
					<td scope="col">${sdata.acct}</td>
					<td scope="col">${sdata.amt}</td>
					<td scope="col">${sdata.remo}</td>
					<td scope="col">${sdata.savetime}</td>
					<td scope="col">
<!----><a href="/itivtmgr/charges/topvichargesxg.do?spage=fshchargescx&id=${sdata.id}&topage=pvichargesxg">查看</a>
  |
<a href="javascript:add('20000${sdata.id}')">在线交流</a>
 |
						<a href="/itivtmgr/sysuser/tohysysuserxg.do?id=${sdata.sysuserkey}">修改简历</a>
						|
<a onclick="return confirm('确定要删除这条记录吗?')" href="/itivtmgr/charges/delete.do?plids=${sdata.id}&surl=fshchargescx">删除</a>
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
pop('chargesxg.jsp?id='+no,'信息修改',550,'100%')
}
</script>
<script language=javascript>
function add(){
pop('chargestj.jsp','信息添加',550,'100%')
}
</script>
<%@page import="util.Info"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="util.PageManager"%>
<%@page import="dao.CommDAO"%>
<script language=javascript>
	function add(no){
		pop('/itivtmgr/itivtmgr/szxjlcx.do?id='+no,'<font color=gray>在线交流</font>',750,314)
	}
	popheight = 70;
</script>
