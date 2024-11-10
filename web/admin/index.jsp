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
<%
HashMap user = Info.getUser(request);
String uname = user.get("uname").toString();
String utype = user.get("utype").toString();
String userid = user.get("id").toString();

if(utype.equals("管理员"))
response.sendRedirect("/itivtmgr/recruiting/fshrecruitingcx.do");

if(utype.equals("会员"))
response.sendRedirect("/itivtmgr/jobapply/jobapplycx.do");

if(utype.equals("企业"))
response.sendRedirect("/itivtmgr/recruiting/recruitingcx.do");
 
 

%>