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


<!-- product tab slider start -->
<section class="product-tab-section">

    <div class="container" >
        <div class=" " align="center">
            <div class="col-xl-5 col-md-4 col-12" >
                <div class="section-title">
                    <h2 class="title" style="font-size: 38px;padding: 10px;letter-spacing: 3px">人才信息</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="tab-content" id="pills-tabContent">
                    <div class="tab-pane fade show active" id="pills-home">






                        <div class="   ">
                            <div class=" ">


<div style="padding: 15px">
<form name="f1" autocomplete="off" action="/itivtmgr/sysuser/rcxxs.do" method="post" style="display: inline">
	 &nbsp;&nbsp;&nbsp;
用户名 
&nbsp;:&nbsp; 
<input type=text class=''  size=15 name='uname' />
&nbsp;&nbsp;&nbsp;
姓名 
&nbsp;:&nbsp; 
<input type=text class=''  size=15 name='tname' />
&nbsp;&nbsp;&nbsp;
性别&nbsp;:&nbsp;<select name='sex'>
<option value="">不限</option>
<option value='男'>男</option> 
<option value='女'>女</option> 
</select>
&nbsp;&nbsp;&nbsp;
邮箱 
&nbsp;:&nbsp; 
<input type=text class=''  size=15 name='email' />
&nbsp;&nbsp;&nbsp;
联系电话 
&nbsp;:&nbsp; 
<input type=text class=''  size=15 name='tel' />
&nbsp;&nbsp;&nbsp;
地址 
&nbsp;:&nbsp; 
<input type=text class=''  size=15 name='addrs' />

    <table><tr><td height="15px"></td></tr></table>

&nbsp;&nbsp;&nbsp;
毕业院校 
&nbsp;:&nbsp; 
<input type=text class=''  size=15 name='school' />
&nbsp;&nbsp;&nbsp;
学历&nbsp;:&nbsp;<select name='education'>
<option value="">不限</option>
<option value='初中'>初中</option> 
<option value='高中'>高中</option> 
<option value='大专'>大专</option> 
<option value='本科'>本科</option> 
<option value='硕士'>硕士</option> 
<option value='博士'>博士</option> 
</select>
&nbsp;&nbsp;&nbsp;
 毕业日期&nbsp;:&nbsp; 
 
<input type=text class=''  size=9 name='startcbdate' onclick='WdatePicker();' />
&nbsp;至&nbsp;

<input type=text class=''  size=9 name='endcbdate' onclick='WdatePicker();' />
&nbsp;&nbsp;&nbsp;
个人技能 
&nbsp;:&nbsp; 
<input type=text class=''  size=15 name='skill' />
&nbsp;&nbsp;&nbsp;
外语技能 
&nbsp;:&nbsp; 
<input type=text class=''  size=15 name='languages' />
&nbsp;&nbsp;&nbsp;

<input type=submit  value=' 查询信息 ' />
 

  
	</form>
</div>

                                <div class="row mb-n7 row-cols-1">


 

<c:forEach items="${slist}" var="sdata" varStatus="sta">
 <!-- single blog start -->
<div class="col mb-7"  >
	<div class="blog-card blog-card-list2">
		<div class="thumb bg-light p-0 text-center">
			<a href="/itivtmgr/sysuser/torcxxx.do?id=${sdata.id}">
				<img src="/itivtmgr/upfile/${sdata.filename}" alt="img" height="154" style="width: 180px">
			</a>
		</div>
		<div class="blog-content" style="padding: 8px">
			<h3 class="title"  style="margin-bottom: 5px">
				<a href="/itivtmgr/sysuser/torcxxx.do?id=${sdata.id}" style="font-size: 18px">${sdata.uname} - ${sdata.tname}</a>
			</h3>
            <p class="blog_link_meta" style="margin-bottom: 5px">
                    性别 : ${sdata.sex}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    学历 : ${sdata.education}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    毕业院校 : ${sdata.school}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    专业领域 : ${sdata.specialty}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    毕业时间 : ${sdata.cbdate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </p>
			<p class="blog_link_meta">
				 ${sdata.remo}
			</p>
			<p>
				
			</p> 
		</div>
	</div>
</div>
<!-- single blog end -->
</c:forEach>
  
                                </div>
                                <div class="section-pt section-mb">
                                    <!-- pagination start -->
                                  <center style="font-size: 17px"> ${page.info } </center>
                                    <!-- pagination start -->
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
</html><script language=javascript src='/itivtmgr/js/ajax.js'></script>
${fillForm} 
<script language=javascript >  
 
</script>  
${fillForm} 
<script language=javascript src='/itivtmgr/js/My97DatePicker/WdatePicker.js'></script>
<script language=javascript src='/itivtmgr/js/popup.js'></script>
<%@page import="util.Info"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.HashMap"%> 
<%@page import="util.PageManager"%> 
<%@page import="dao.CommDAO"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
