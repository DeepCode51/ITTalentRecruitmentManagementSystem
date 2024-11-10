<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="util.Info"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="info" uri="http://www.javayou.com/dev/jsp/jstl/mail" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> </title> 
 
</head>

<body>

 
<style>
A.applink:hover {border: 2px dotted #DCE6F4;padding:2px;background-color:#ffff00;color:green;text-decoration:none}
A.applink       {border: 2px dotted #DCE6F4;padding:2px;color:#2F5BFF;background:transparent;text-decoration:none}
A.info          {color:#2F5BFF;background:transparent;text-decoration:none}
A.info:hover    {color:green;background:transparent;text-decoration:underline}
</style> 
 
    
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="" align="center">
  <tr>
    <td align="center" valign="top" >　 
 
 
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" >

  <tr align="center" valign="top">
    <td  >
      <table width="100%"  border="0" cellpadding="0" cellspacing="0"  >
   
     
    
     <c:forEach items="${slist}" var="sdata" varStatus="sta">
        <tr>
          <td width="89" height="34" align="center" valign="middle" >
          
          <img src="/itivtmgr/upfile/${sdata.filename}" width="45" height="40" />           </td>
          <td width="1201" align="left" valign="middle" style="padding: 10px" > 
         <font color=orange> ${sdata.saver} 
          &nbsp;&nbsp;
           (${sdata.savetime}) : </font> 
          
          <table><tr><td height="0"></td></tr></table>
          
          <c:if test="${not empty sdata.pf}">
          ${sdata.pf}分，
          </c:if>
          
          ${sdata.content}           </td>
           
          <td width="21" align="right" valign="middle"></td>
        </tr>
        </c:forEach>
          
           <tr>
          <td height="45" align="center" colspan="4" valign="middle" >
          
          ${page.info }          </td>
          </tr> 
          
          <form action="/itivtmgr/itivtmgr/pinlunsub.do" name="f1" method="post">
          <input type="hidden" name="id" value="${id}" />
          <input type="hidden" name="title" value="${title}" />
          <input type="hidden" name="table" value="${table}" />
          <input type="hidden" name="url" value="${url}" /> 
          <c:if test="${not empty user}"> 
          <tr>
          <td height="45" align="center" colspan="4" valign="middle" style="padding: 10px" >  
          <textArea cols="55" rows="3" name="content" ></textArea>
           
               <table><tr><td height="2"></td></tr></table>  
     <c:if test="${pinlunNum == 0 && pf eq '1'}">           
          评分 :  <label><input type=radio checked="checked" value="1" name="pf" /> &nbsp;1&nbsp; </label>
                <label><input type=radio value="2" name="pf" /> &nbsp;2&nbsp; </label>
                <label><input type=radio value="3" name="pf" /> &nbsp;3&nbsp; </label>
                <label><input type=radio value="4" name="pf" /> &nbsp;4&nbsp; </label>
                <label><input type=radio value="5" name="pf" /> &nbsp;5&nbsp; </label>
           
          <table><tr><td height="3"></td></tr></table>
          </c:if>
          
          <input type="submit" value=" 提交评论 " />
                    </td>
          </tr>  
          </c:if>
          </form>
      </table></td>
  </tr>
 
</table> 
 
</td>
  </tr>
 

</table> 
 
 

</body>
</html>
 
