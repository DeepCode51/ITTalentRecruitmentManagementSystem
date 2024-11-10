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


<jsp:include page="/itivtmgr/toadmintop.do"></jsp:include>

<!-- servletformstart
<form  action="/itivtmgr/itivtmgr?ac=glysysusertj&tglparentid=${tglparentid}"   autocomplete="off"  style="display: inline;font-size: 15px" name="f1" method="post"  onsubmit="return checkform()" >
servletformend -->
<!-- s2formstart -->
<form name="f1" method="post" onsubmit="return checkform()" action="/itivtmgr/itivtmgr/uppass.do" >
    <!-- servletformend -->

    <div class="ms-panel">
        <div class="ms-panel-header">
            <h6>修改密码</h6>
        </div>
        <div class="ms-panel-body">

            <div class="table-responsive">
                <table  class="table table-bordered" style="width: 99%;margin-bottom: 5px" align="center">

                    <tr>
                        <th width="32%"  align="center" bgcolor="#FFFFFF"><center>请输入新密码</center></th>
                        <td width="68%"   align="left" bgcolor="#FFFFFF">&nbsp;
                            <input type="password" name="upass" id="textfield2" size="30" />                            </td>
                    </tr>
                    <tr>
                        <th align="center" bgcolor="#FFFFFF"><center>请再次输入新密码</center></th>
                        <td  align="left" bgcolor="#FFFFFF">&nbsp;
                            <label>

                                <input type="password" name="repass" id="textfield3" size="30" />
                            </label></td>
                    </tr>
                    <tr>
                        <td  colspan="2" align="center" bgcolor="#FFFFFF">
                            <label>
                                <input type="submit" onmousedown="checkk()" name="button" id="button" value="提交信息" />
                            </label>
                            &nbsp;&nbsp;&nbsp;           <input type="reset" name="button2" id="button2" value="重新填写" /></td>
                    </tr>


                </table>
            </div>
        </div>
    </div>
</form>

<jsp:include page="/itivtmgr/toadminfoot.do"></jsp:include>


	</body>
</html>

<script language=javascript src='/itivtmgr/js/My97DatePicker/WdatePicker.js'></script> 
<script language=javascript src='/itivtmgr/js/ajax.js'></script> 
<script language=javascript src='/itivtmgr/js/popup.js'></script> 
<%@page import="util.Info"%> 
<%@page import="util.Info"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.HashMap"%> 
<%@page import="util.PageManager"%> 
<%@page import="dao.CommDAO"%> 


<script language="javascript">
<%
String suc = (String)request.getAttribute("suc");
if(suc!=null)
{
%>
alert("操作成功");
<%}%>
</script>
 <script type="text/javascript">
   function checkk()
   {
  
   if(f1.upass.value=="")
   {
   alert("请输入新密码");
   return;
   }
   if(f1.repass.value!=f1.upass.value)
   {
   alert("两次密码输入不一致");
   return;
   }
   }
   </script>