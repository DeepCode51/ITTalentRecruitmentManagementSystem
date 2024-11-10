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
 
<!DOCTYPE html>

<body>

        
        <center>
          <form action="/itivtmgr/itivtmgr/sfotpassopr.do?id=${usermap.id}" method="post" style="display: inline">
            <table width="100%"  border="0"  align="center" style="border: 0px" cellpadding="0" cellspacing="0">
                
                 <thead>
                  <tr> 
                    <td  height="60" align="center" colspan="3" style="border: 0px"  valign="middle">
                    <font style="font-size: 26px">找回密码</font>                     </td>
                  </tr>
                  </thead>
                
                  <tr>
                    
                    <td width="206" height="45" style="border: 0px"  align="center">&nbsp;</td>
                    <td width="225" style="border: 0px"  align="center">用户名 ： </td>
                    <td width="690"  style="border: 0px" height="35">&nbsp;
                    ${usermap.uname}
                    </td>
                  </tr>
                  <tr>
                    <td height="35" style="border: 0px" align="center">&nbsp;</td>
                    <td align="center" style="border: 0px">密保问题 ： </td>
                    <td height="45" style="border: 0px">&nbsp;
                        ${usermap.passques}
                    </td>
                  </tr>
                  <tr>
                    <td height="35" align="center" style="border: 0px">&nbsp;</td>
                    <td align="center" style="border: 0px">密保答案 ： </td>
                    <td height="45" style="border: 0px">
                    <label>
                    <input name="passans"  id="passans"  type="text"  size="25"   />     </label>
                                     </td>
                  </tr>

                <tr>
                    <td height="35" align="center" style="border: 0px">&nbsp;</td>
                    <td align="center" style="border: 0px">新密码 ： </td>
                    <td height="45" style="border: 0px">
                        <label>
                            <input name="password"  id="password"  type="password"  size="25"   />     </label>
                    </td>
                </tr>

                <tr>
                    <td height="35" align="center" style="border: 0px">&nbsp;</td>
                    <td align="center" style="border: 0px">确认密码 ： </td>
                    <td height="45" style="border: 0px">
                        <label>
                            <input name="repassword"  id="repassword"  type="password"  size="25"   />     </label>
                    </td>
                </tr>
                 
                   
                  <tr>
                    <td height="45" colspan="3" style="border: 0px" align="center">
                    
                    <label>
                      <input type="submit" name="button" onMouseDown="check();" id="button" value="提交信息"> 
                      
                      <script type="text/javascript">
                      function check()
                      {
                      var passans = document.getElementById("passans");
                      var password = document.getElementById("password");
                      var repassword = document.getElementById("repassword");
                      if(passans.value=="")
                      {
                          alert("请输入密保答案");
                          passans.focus();
                          return;
                      }
                      if(passans.value!="${usermap.passans}")
                      {
                          alert("密保答案错误");
                          passans.focus();
                          return;
                      }
                      if(password.value=="")
                      {
                          alert("请输入新密码");
                          password.focus();
                          return;
                      }
                      if(repassword.value=="")
                      {
                          alert("请确认新密码");
                          repassword.focus();
                          return;
                      }

                      if(repassword.value!=password.value)
                      {
                          alert("两次密码输入不一致");
                          repassword.focus();
                          return;
                      }
                     
                      }
                      
                      </script>
                      
                      &nbsp;&nbsp;&nbsp;&nbsp;
                      <input type="button" name="button2" onclick="popclose();" id="button2" value="关闭窗口">
                        <script language=javascript src='/itivtmgr/js/popup.js'></script>
                    </label>                    </td>
                    </tr>
                    
                    
                    
                    
                     <tr>
                      <td height="65" colspan="3" style="border: 0px" align="center">
                      </td>
                    </tr>
                   
                   
                   
                </table>
          </form>
          </center>

        

</body>
</html>
 
<script type="text/javascript">
 
<%
if(request.getAttribute("suc")!=null){
%>
alert("密码已更换");
popclose();
<%}%>
  
</script> 
            