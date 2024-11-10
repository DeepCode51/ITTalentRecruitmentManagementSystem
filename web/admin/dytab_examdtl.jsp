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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

 <%
 String frgroupno=request.getParameter("frgroupno")==null?"":request.getParameter("frgroupno");
 String onlycheck=request.getParameter("onlycheck")==null?"":request.getParameter("onlycheck");
 %>

<style>
.dytab{
border: 0.5px solid #f5f5f5;border-collapse: collapse;margin-top:3px;margin-bottom: 3px;
}
.dytab td {
border: 0.5px solid #f5f5f5;
}
.dytab select {
height:28px;
font-size:13px;
}
</style>

<table id="examdtl" class="dytab" width='99%' align="center">

<tr>
<!--
<td height="30" align="center">信息标题</td>
-->
<td height="30" align="center">标题</td>
<td height="30" align="center">选项A</td>
<td height="30" align="center">选项B</td>
<td height="30" align="center">选项C</td>
<td height="30" align="center">选项D</td>


 <%if(Info.getUser(request).get("utype").equals("企业")){%>
<td height="30" align="center">
 标准答案
</td>
 <%}%>
 <%if(!"".equals(onlycheck)){%>
 <%if(Info.getUser(request).get("utype").equals("会员")||Info.getUser(request).get("utype").equals("企业")){%>
 <td height="30" align="center">
  应聘人回答
 </td>
 <%}%>
 <%}%>


 <%if("".equals(onlycheck)){%>
<td  height="30" align="center"  id="titledelete">&nbsp;</td>
<%}%>
</tr>


<!--
<td height="30" align="center">信息内容</td>
-->
<%
if("".equals(onlycheck)){
for(int x=1;x<=50;x++){
request.setAttribute("x", x);
%>
<tr style="display:<%=x==1?"":"none"%>" id="examdtl_tr_<%=x%>">
<td height="30" align="center" id='td_ititle_<%=x%>' >
<input type=text    class='' id='ititle<%=x%>' name='ititle<%=x%>' size=50 /></td>
<td height="30" align="center" id='td_itema_<%=x%>' >
<input type=text    class='' id='itema<%=x%>' name='itema<%=x%>' size=5 /></td>
<td height="30" align="center" id='td_itemb_<%=x%>' >
<input type=text    class='' id='itemb<%=x%>' name='itemb<%=x%>' size=5 /></td>
<td height="30" align="center" id='td_itemc_<%=x%>' >
<input type=text    class='' id='itemc<%=x%>' name='itemc<%=x%>' size=5 /></td>
<td height="30" align="center" id='td_itemd_<%=x%>' >
<input type=text    class='' id='itemd<%=x%>' name='itemd<%=x%>' size=5 /></td>
<td height="30" align="center" id='td_banswer_<%=x%>' >
<span id="banswer<%=x%>danx"><label id='label_banswer<%=x%>_1'><input type=radio name='banswer<%=x%>' checked="checked" value='A' />&nbsp;A &nbsp;&nbsp;</label>
<label id='label_banswer<%=x%>_2'><input type=radio name='banswer<%=x%>'  value='B' />&nbsp;B &nbsp;&nbsp;</label>
<label id='label_banswer<%=x%>_3'><input type=radio name='banswer<%=x%>'  value='C' />&nbsp;C &nbsp;&nbsp;</label>
<label id='label_banswer<%=x%>_4'><input type=radio name='banswer<%=x%>'  value='D' />&nbsp;D &nbsp;&nbsp;</label>
</span></td>

<%if("".equals(onlycheck)){%>
<td  height="30" align="center" id="deletetd<%=x%>"><font onclick="main_remove_row(<%=x%>)">删除</font></td>
<%}%>
</tr>
<%}%>

<tr  id="traddcols">
<td colspan="10" height="38" align="center" >
<font onclick="examdtl_add_row();">增加一行</font>
<input type="hidden" name="dytab_del_rows" value="" id="dytab_del_rows"/>
<input type="hidden" name="grtable_crows" value="1" id="grtable_crows"/>
<input type="hidden" name="frgroupno" id="frgroupno" value="<%=frgroupno%>" />
<input type="hidden" name="table_rela_cols" value="" id="table_rela_cols"/>
</td>
</tr>
<%}else{%>
 <%if(Info.getUser(request).get("utype").equals("企业")){%>
<%=new CommDAO().dyTformTR(frgroupno,"ititle-标题;itema-选项A;itemb-选项B;itemc-选项C;itemd-选项D;banswer-标准答案;canswer-应聘人回答","examdtl") %>
 <%}%>
 <%if(Info.getUser(request).get("utype").equals("会员")){%>
 <%=new CommDAO().dyTformTR(frgroupno,"ititle-标题;itema-选项A;itemb-选项B;itemc-选项C;itemd-选项D;canswer-应聘人回答","examdtl") %>
 <%}%>
<%} %>
</table>

<script type="application/javascript">
    function setweight(){}

    var examdtl_table_crows = 1;
	var dytabcols = "ititle-标题;itema-选项A;itemb-选项B;itemc-选项C;itemd-选项D;banswer-标准答案";
	var autoidfr = dytabcols.split("-")[0];
	var autotablename = "examdtl";

    function examdtl_add_row(){
          examdtl_table_crows++;
          document.getElementById("examdtl_tr_"+examdtl_table_crows).style.display="";
		  document.getElementById("grtable_crows").value=examdtl_table_crows;
    }


    function main_remove_row(rowno) {
         document.getElementById("examdtl_tr_"+rowno).style.display="none";
         var dytab_del_rows_value = document.getElementById("dytab_del_rows").value+"_"+rowno;
		 dytab_del_rows_value+="_";
        document.getElementById("dytab_del_rows").value = dytab_del_rows_value;
    }
	function showRedTr(trid)
	{
	  document.getElementById(trid).style.background='orange';
	  setTimeout("document.getElementById('"+trid+"').style.background=''",1000);
	}

//字段名  字段汉字名  表名
function checkColOnly(colname,colhyname,tablename)
{
    for(var i=1;i<=50;i++)
    {
       var trid = tablename+"_tr_"+i;
       var dytr = document.getElementById(trid);
       if(dytr.style.display=="")
       {
          var colnamevalue = document.getElementById(colname+i).value;
          if(colnamevalue=='')
          {
            continue;
          }
                        for(var x=1;x<=50;x++)
					    {
					       var xtrid = tablename+"_tr_"+x;
					       var xdytr = document.getElementById(xtrid);
					       if(xdytr.style.display=="")
					       {
					          var xcolnamevalue = document.getElementById(colname+x).value;
					          if(xcolnamevalue=='')
					          {
					            continue;
					          }
					          if(x==i)
					          {
					            continue;
					          }
					           if(xcolnamevalue==colnamevalue)
					          {
					            alert(colhyname+" 有重复");
					            showRedTr(trid);
								showRedTr(xtrid);
					            return false;
					          }
					       }
					    }

       }
    }
}


</script>





<script language="JavaScript">
 function  initdytab(){
    datastrs = "";
    for(var i=1;i<=50;i++) {
	 document.getElementById("td_"+autoidfr+"_"+i).align='center';
     document.getElementById("td_"+autoidfr+"_"+i).width='';
     document.getElementById(autotablename+"_tr_"+i).style.display='none';
     document.getElementById('deletetd'+i).style.display='none';
     var zcols = dytabcols.split(";");
     for(var j=1;j<zcols.length;j++) {
      var zcolname = zcols[j].split("-")[0];
      if(document.getElementById(zcolname+i)) {
          document.getElementById(zcolname+i).value="";
      }
     }
     document.getElementById('grtable_crows').value=0;
    }
    eval(autoidfr+"1autoidchangeAjax();");
     if(datastrs!=''){
          var datastrses = datastrs.split("--");
          document.getElementById('titledelete').style.display='none';
          document.getElementById('traddcols').style.display='none';
          for(var i=1;i<=datastrses.length;i++){
            document.getElementById(autotablename+"_tr_"+i).style.display='';
            document.getElementById(autoidfr+""+i).style.display='none';
            document.getElementById("td_"+autoidfr+"_"+i).innerHTML="<span style='display: none'>"+document.getElementById("td_"+autoidfr+"_"+i).innerHTML+"</span>"+(datastrses[i-1]);
            document.getElementById(autoidfr+""+i).value=(datastrses[i-1]);
            var zcols = dytabcols.split(";");
            for(var j=1;j<zcols.length;j++) {
               var zcolname = zcols[j].split("-")[0];
               if(document.getElementById(zcolname+i)&&document.getElementById(zcolname+i).readOnly) {
                 try {
                  eval(zcolname + i + autoidfr + i + "change();");
                  document.getElementById("td_"+zcolname+"_"+i).innerHTML="<span style='display: none'></span>"+(document.getElementById(zcolname+i).value+"<input type=hidden value='"+(document.getElementById(zcolname+i).value)+"' name='"+(zcolname+i)+"' id='x"+(zcolname+i)+"'/>"+"<input type=text style='display:none' value='"+(document.getElementById(zcolname+i).value)+"' readonly id='"+(zcolname+i)+"'/>");
                 }catch (e){
                 }
               }
            }

          }
       document.getElementById('grtable_crows').value=datastrses.length;
     }
	 setTimeout(setweight,100);
 }

</script>



<script language=javascript src='/itivtmgr/js/ajax.js'></script>
<script language=javascript src='/itivtmgr/js/popup.js'></script>
<script language=javascript src='/itivtmgr/js/My97DatePicker/WdatePicker.js'></script>
<script language=javascript >
<%for(int x=1;x<=50;x++){%>
<%}%>
 function checkdytab(){
<%for(int x=1;x<=50;x++){%>
if(document.getElementById("examdtl_tr_<%=x%>").style.display==''){
var ititle<%=x%>obj = document.getElementById("ititle<%=x%>");
if(ititle<%=x%>obj.value==""){
alert('请输入标题');
showRedTr("examdtl_tr_<%=x%>");
return false;
}
}
<%}%>
return true;
}
</script>
