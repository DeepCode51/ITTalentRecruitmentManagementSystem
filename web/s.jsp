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
                    <h2 class="title" style="font-size: 38px;padding: 10px;letter-spacing: 3px"></h2>
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
<form name="f1" autocomplete="off" action="/itivtmgr/recruiting/s.do" method="post" style="display: inline">
	 &nbsp;&nbsp;&nbsp;
企业名称 
&nbsp;:&nbsp; 
<input type=text class=''  size=15 name='qytname' />
&nbsp;&nbsp;&nbsp;
招聘标题 
&nbsp;:&nbsp; 
<input type=text class=''  size=15 name='rtitle' />
&nbsp;&nbsp;&nbsp;
招聘类别&nbsp;:&nbsp;<select name='infotype'>
<option value="">不限</option>
<option value='社招'>社招</option> 
<option value='校招'>校招</option> 
</select>
&nbsp;&nbsp;&nbsp;
职位大类&nbsp;:&nbsp;${btypeselect}&nbsp;&nbsp;&nbsp;
职位细类&nbsp;:&nbsp;${stypeselect}&nbsp;&nbsp;&nbsp;
<script language=javascript> 
function btypechange(){ 
document.getElementById("btype").onchange=btypechange; 
var btypevalue = document.getElementById("btype").value; 
var stypeobj = document.getElementById("stype"); 
stypeobj.options.length=0; 
var boption = new Option("不限",""); 
stypeobj.add(boption); 
var ajax = new AJAX(); 
ajax.post(encodeURI("/itivtmgr/ajax/getsonops.do?jl_parent_glzd=datashowname&jl_parent_glb=postype&glb=postype&glzd=datashowname&jlzd=btype&jlzdb=tglparentid&value="+btypevalue+"&ctype=select&ttime=<%=Info.getDateStr()%>")); 
//ajax.post("/itivtmgr/ajax/getsonops.do?jl_parent_glzd=datashowname&jl_parent_glb=postype&glb=postype&glzd=datashowname&jlzd=btype&jlzdb=tglparentid&value="+btypevalue+"&ctype=select&ttime=<%=Info.getDateStr()%>"); 
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
工作地点 
&nbsp;:&nbsp; 
<input type=text class=''  size=15 name='addrs' />
&nbsp;&nbsp;&nbsp;
 发布时间&nbsp;:&nbsp; 
 
<input type=text class=''  size=9 name='startsavetime' onclick='WdatePicker();' />
&nbsp;至&nbsp;

<input type=text class=''  size=9 name='endsavetime' onclick='WdatePicker();' />
&nbsp;&nbsp;&nbsp;

<input type=submit  value=' 查询信息 ' />
 

  
</form>
</div>

                                <div class="row mb-n7 row-cols-1">
 


<c:forEach items="${slist}" var="sdata" varStatus="sta">
<!-- single blog start -->
<div class="col mb-7"  >
	<div class=" blog-card-list2">
		<div class="thumb  p-0 text-center" style="height: 32px;margin-top: 10px" >
			<a href="/itivtmgr/recruiting/tox.do?id=${sdata.id}">
				<h3 class="title" style="font-weight: 300;font-size: 18px">
					<font color="#9A9FA3">[  ] </font>  
					<font color="black"></font>
					 &nbsp;&nbsp;&nbsp;&nbsp;
					<font color="#9A9FA3">  </font>  
				</h3>
			</a>
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
btypechange();
 
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
