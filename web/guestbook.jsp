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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="info" uri="http://www.javayou.com/dev/jsp/jstl/mail" %>

<!DOCTYPE html>
<html lang="en">
<!-- head start -->

<head>

    <!-- Required meta tags -->
    <meta name="description" content="Responsive Bootstrap5 Template">
    <meta name="keywords" content="Grano Organic & Food Responsive Bootstrap5 Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="utf-8">

    <title>itivtmgr - Responsive Bootstrap5 Template</title>

    <link rel="shortcut icon" type="image/x-icon" href="/itivtmgr/frontfiles/images/favicon.ico">
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/vendor/ionicons.css" />
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/vendor/line-awesome.min.css" />
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/plugins/animate.min.css" />
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/plugins/swiper-bundle.min.css" />
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/plugins/jquery-ui.min.css" />
    <link rel="stylesheet" href="/itivtmgr/frontfiles/css/style.css" />

</head>
<!-- head end -->

<body>

<jsp:include page="/itivtmgr/tofrtop.do" />

<!-- <form name="f1" autocomplete="off"  action="/itivtmgr/itivtmgr?ac=qyxxx&id=${id}"  method="post" style="display: inline"></form>-->

<!-- product tab slider start -->
<section class="product-tab-section">

    <div class="container" >
        <div class=" " align="center">
            <div class="col-xl-5 col-md-4 col-12" >
                <div class="section-title">
                    <h2 class="title" style="font-size: 38px;padding: 10px;letter-spacing: 3px">留言板</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="tab-content" id="pills-tabContent">
                    <div class="tab-pane fade show active" id="pills-home">






                        <div class="   ">
                            <div class=" ">


                                <div class="row mb-n7 row-cols-1">


 
<table width="100%" height="84" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                                                      
                   <td width="100%" height="27" class="">
                       <font style="font-size: 14px;padding-top: 5px;padding-bottom: 5px;margin-bottom: 5px">





                           <TABLE cellSpacing=2 cellPadding=3 width="100%" align=center  bgColor=#ffffff border=0>

                               <c:forEach items="${slist}" var="sdata" varStatus="sta">
                                   <tr>
                                       <td width="10%" height="32" align="center" style="padding-top: 10px;border: 0px" valign="middle" class="dd">
                                           <img src="/itivtmgr/upfile/${sdata.filename}"   height=72 width="85" />
                                       </td>

                                       <td width="90%" align="left" style="padding: 10px;border: 0px" valign="middle" class="dd"><font color=red>${sdata.uname} &nbsp;&nbsp;
                                           (${sdata.savetime}) : </font>
                                           <table>
                                               <tr>
                                                   <td height="1" style="padding-top: 5px;border: 0px"></td>
                                               </tr>
                                           </table>
                                               ${sdata.cont}
                                           <table>
                                               <tr>
                                                   <td height="1" style="padding-top: 5px;border: 0px"></td>
                                               </tr>
                                           </table>

                                           <c:if test="${!empty sdata.recont}" >
                                               <font color=gray>
                                                   管理员回复 ： ${sdata.recont}
                                               </font>
                                           </c:if>

                                       </td>
                                   </tr>
                               </c:forEach>


                               <tr  >
                                   <td height="20" align="center" colspan="4" style="padding: 10px;border: 0px" id="page">

                                       ${page.info }		 	</td>
                               </tr>



                               <%
                                   if(Info.getUser(request)!=null){
                               %>
                               <tr>
                                   <td height="45" align="center" colspan="5" valign="middle" style="padding: 10px;border: 0px" >
                                       <form action="/itivtmgr/itivtmgr/guestbookopr.do" name="f1" method="post">
                                           <textArea cols="55" rows="3" name="cont" ></textArea>

                                           <table><tr><td height="3"></td></tr></table>

                                           <input type="submit" value=" 提交留言 " />
                                       </form>          </td>
                               </tr>
                               <%} %>
                           </table>



                       </font>
                  </td>
</tr>

 
   
  
</table>

  
                                </div>
                                 
                            </div>

                        </div>







                    </div>
                   
                </div>
            </div>
        </div>
    </div>
</section>
<!-- product tab slider end -->


<!-- main content end -->

<jsp:include page="foot.jsp" />

</body>
</html><script language=javascript src='/itivtmgr/js/My97DatePicker/WdatePicker.js'></script> 
<script language=javascript src='/itivtmgr/js/ajax.js'></script> 
<script language=javascript src='/itivtmgr/js/popup.js'></script> 
<%@page import="util.Info"%> 
<%@page import="util.Info"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.HashMap"%> 
<%@page import="util.PageManager"%> 
<%@page import="dao.CommDAO"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<jsp:include page='/ajax/getsonops.do'>
<jsp:param name="fid" value="${id}" />
<jsp:param name="ftitle" value="${updateentity.tname}" />
<jsp:param name="ftable" value="sysuser" />
<jsp:param name="ctype" value="viewhistory" />
</jsp:include>
<c:if test="${!empty sessionScope.suc}">
    <c:remove var="suc" scope="session" />
    <script type="text/javascript">
        alert("操作成功");
    </script>
    <c:remove var="suc" scope="session" />
</c:if> 