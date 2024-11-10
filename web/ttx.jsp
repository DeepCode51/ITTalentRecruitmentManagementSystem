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

<!-- <form name="f1" autocomplete="off"  action="/itivtmgr/itivtmgr?ac=ttx&id=${id}"  method="post" style="display: inline"></form>-->

<!-- product tab slider start -->
<section class="product-tab-section">

    <div class="container" >
        <div class=" " align="center">
            <div class="col-xl-5 col-md-4 col-12" >
                <div class="section-title">
                  
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
                   <center>
                    <font style="font-size: 35px"> ${updateentity.rtitle} </font>
                                  
                                       
                                       <table><tr><td height="25"></td></tr></table>   
                                                                              
<img src="/itivtmgr/upfile/${updateentity.filename}" height="380" />                                           
<table><tr><td height="15"></td></tr></table>

 <c:if test="${!empty updateentity.docname2}" >  
    <video width="520" height="440" controls="controls">
	  <source src="movie.ogg" type="video/ogg">
	  <source src="/itivtmgr/upfile/${updateentity.docname2}" type="video/mp4"> 
	</video>
 <table><tr><td height="15"></td></tr></table>
 </c:if>

    
     <span class="">  ${updateentity.savetime} </span>

	  <table><tr><td height="15"></td></tr></table>
     <span class="">    &nbsp; <c:if test="${ucollectNum==0}"> 
		          <a href="/itivtmgr/ucollect/ucollectopr.do?surl=tottx&id=${id}&table=recruiting&title=${updateentity.rtitle}">[收藏本页]</a> &nbsp;&nbsp;
		          </c:if> 
  &nbsp;  </span>
   
              
               </center>
              
              </td>
</tr>

  <tr>
    <td height="30" class=" " valign="top"> 
       企业名称：${updateentity.qytname} 
     </td>
  </tr>
 

  <tr>
    <td height="30" class=" " valign="top"> 
       企业用户名：${updateentity.qyuname} 
     </td>
  </tr>

  <tr>
    <td height="30" class=" " valign="top"> 
       招聘类别：${updateentity.infotype} 
     </td>
  </tr>

  <tr>
    <td height="30" class=" " valign="top"> 
       职位大类：${updateentity.btype} 
     </td>
  </tr>

  <tr>
    <td height="30" class=" " valign="top"> 
       职位细类：${updateentity.stype} 
     </td>
  </tr>
  
  <tr>
    <td height="30" class=" " valign="top"> 
       工作地点：${updateentity.addrs} 
     </td>
  </tr>

   <tr>
    <td height="30" class=" " valign="top"> 
       工作地点：${updateentity.addrs} 
     </td>
  </tr>

   <tr>
    <td height="30" class=" " valign="top"> 
       学历要求：${updateentity.education} 
     </td>
  </tr>

   <tr>
    <td height="30" class=" " valign="top"> 
       技能要求：${updateentity.skills} 
     </td>
  </tr>

   <tr>
    <td height="30" class=" " valign="top"> 
       薪资待遇：${updateentity.sal} 
     </td>
  </tr>

   <tr>
    <td height="30" class=" " valign="top"> 
       招聘人数：${updateentity.nums} 
     </td>
  </tr>

   <tr>
    <td height="30" class=" " valign="top"> 
       其他要求：${updateentity.remo} 
     </td>
  </tr>

   <tr>
    <td height="30" class=" " valign="top"> 
       点击数：${updateentity.clicknums} 
     </td>
  </tr>

  <tr>
    <td height="30" class=" " valign="top"> 
        
     </td>
  </tr>

  <tr>
    <td height="30" class=" " valign="top"> 
       <c:if test="${not empty admin}">
<form autocomplete="off" action="/itivtmgr/itivtmgr/sipapply.do?id=${id}" name="f1" method="post"> 

<input type=hidden name='applytable' value='jobapply' /> 
<input type=hidden name='applytablecols' value='uname,tname,fid,frtitle,fqyuname,fqytname,applyremo,fshstatus,fshremo,savetime' /> 
<input type=hidden name='fid' value='${id}' /> 
<input type=hidden name='fshstatus' value='申请中' /> 
<input type=hidden name='url' value='/recruiting/tottx.do' /> 
<input type=hidden name='fqyuname' value='${updateentity.qyuname}' /> 
<input type=hidden name='fqytname' value='${updateentity.qytname}' /> 
<input type=hidden name='frtitle' value='${updateentity.rtitle}' /> 
<input type=hidden name='finfotype' value='${updateentity.infotype}' /> 
<input type=hidden name='fbtype' value='${updateentity.btype}' /> 
<input type=hidden name='fstype' value='${updateentity.stype}' /> 
<input type=hidden name='fnums' value='${updateentity.nums}' /> 
<input type=hidden name='faddrs' value='${updateentity.addrs}' /> 
<input type=hidden name='feducation' value='${updateentity.education}' /> 
<input type=hidden name='fskills' value='${updateentity.skills}' /> 
<input type=hidden name='fsal' value='${updateentity.sal}' /> 
<input type=hidden name='fremo' value='${updateentity.remo}' /> 
<input type=hidden name='fsavetime' value='${updateentity.savetime}' /> 
<input type=hidden name='fsysuserkey' value='${updateentity.sysuserkey}' /> 
<input type=hidden name='fclicknums' value='${updateentity.clicknums}' /> 
<input type=hidden name='fbtypekey' value='${updateentity.btypekey}' /> 
<input type=hidden name='fstypekey' value='${updateentity.stypekey}' /> 

&nbsp;&nbsp;&nbsp;
申请信息 ：<input type='text' size='55' name='applyremo' id='applyremo' />  

&nbsp;&nbsp;&nbsp;
 <input type='submit' value=' 申 请 ' onmousedown='return checkapply();' />  

</form> 
</c:if>
<script type="text/javascript"> 
 function checkapply(){ 
 var ajax = new AJAX();  
 ajax.post(encodeURI("/itivtmgr/ajax/checkno.do?fw=2&checktype=checkzt&fid=${updateentity.id}&sqzt=申请中&table=jobapply&datechecktype=cxsqztjc&ttime=<%=Info.getDateStr()%>")) ; 
 var msg = ajax.getValue();  
 if(msg.indexOf('Y')>-1){  ;
 alert('请不要重复申请')  ;
 return  false;
 } 
 } 
 </script> 
     </td>
  </tr>

    <tr>
    <td height="30" class=" " valign="top"> 
        
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
<jsp:param name="ftitle" value="${updateentity.rtitle}" />
<jsp:param name="ftable" value="recruiting" />
<jsp:param name="ctype" value="viewhistory" />
</jsp:include>