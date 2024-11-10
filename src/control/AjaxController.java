package control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.alibaba.fastjson.JSONObject;

import dao.CommDAO;
import service.*;
import util.Info;
import util.PageManager;
import util.StrUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

	 @Autowired 
     public SysuserService sysuserService; 

	@ResponseBody
	@RequestMapping("/checkno")
	public void checkno(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		String table = request.getParameter("table")==null?"":request.getParameter("table");
		String col = request.getParameter("col")==null?"":request.getParameter("col");
		String value = request.getParameter("value")==null?"":request.getParameter("value");
		if(!(java.nio.charset.Charset.forName("GBK").newEncoder().canEncode(value)))value = Info.getUTFStr(value);
		String checktype = request.getParameter("checktype")==null?"":request.getParameter("checktype");
		String valuecopy = "";
		for(String str:value.split(","))
		{
		if(str.startsWith("sysuser-"))
		{
		str = Info.getUser(request).get(str.split("-")[1]).toString();
		}
		valuecopy+=str+",";
		}
		if(valuecopy.length()>1)valuecopy=valuecopy.substring(0,valuecopy.length()-1);
		value = valuecopy;
		if(checktype.equals("insert"))
		{
		value = Info.getUTFStr(value);
		System.out.println(value);
		HashMap<String, String> smap = new HashMap();
		smap.put("sql", "select * from "+table+" where "+col+"='"+value+"'");
		List list = sysuserService.findByParamWithPages("ajaxSelect", smap, 100, "", request);
		if(list.size()>0)
		{
			response.getWriter().print("Y");
		}else{
			response.getWriter().print("N");
		}
		}


		if(checktype.equals("zhinsert"))
		{
		value = Info.getUTFStr(value);
		System.out.println(value);
		String sql = "select * from "+table+" where 1=1 ";
		String[] cols = col.split(",");
		String[] values = value.split(",");
		for(int i=0;i<cols.length;i++)
		{
		String pcol = cols[i]==null?"":cols[i];
		String pvalue = values[i]==null?"":values[i];
		if(pcol.equals(""))continue;
		if(pvalue.equals(""))continue;
		sql+=" and "+pcol+"='"+pvalue+"' " ;
		}
		System.out.println(sql);
		HashMap<String, String> smap = new HashMap();
		smap.put("sql", sql);
		List list = sysuserService.findByParamWithPages("ajaxSelect", smap, 100, "", request);
		if(list.size()>0)
		{
			response.getWriter().print("Y");
		}else{
			response.getWriter().print("N");
		}
		}


		if(checktype.equals("zhinserts"))
		{
		value = Info.getUTFStr(value);
		System.out.println(value);
		String sql = "select * from "+table+" where 1=1 ";
		String[] cols = col.split(",");
		String[] values = value.split(",");

		sql+=" and "+cols[0]+"='"+values[0]+"' " ;
		String sdate = values[1];
		String edate = values[2];
		sql+=" and status in('待处理','同意') and  ( 1!=1 " ;
		sql+=" or (sdate<='"+sdate+"' and edate>='"+sdate+"' ) " ;
		sql+=" or (sdate<='"+edate+"' and edate>='"+edate+"' ) " ;
		sql+=" or (sdate<='"+sdate+"' and edate>='"+edate+"' ) " ;
		sql+=" or (sdate>='"+sdate+"' and edate<='"+edate+"' ) " ;
		sql+=" ) " ;

		System.out.println(sql);
		List list = new CommDAO().select(sql);
		if(list.size()>0)
		{
			response.getWriter().print("Y");
		}else{
			response.getWriter().print("N");
		}
		}

		if(checktype.equals("update"))
		{
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		value = Info.getUTFStr(value);
		System.out.println(value);

		HashMap<String, String> smap = new HashMap();
		smap.put("sql", "select * from "+table+" where "+col+"='"+value+"' and id!="+id);
		List list = sysuserService.findByParamWithPages("ajaxSelect", smap, 100, "", request);  
		if(list.size()>0)
		{
			response.getWriter().print("Y");
		}else{
			response.getWriter().print("N");
		}
		}


		if(checktype.equals("zhupdate"))
		{
		String id = request.getParameter("id")==null?"":request.getParameter("id");
		value = Info.getUTFStr(value);
		String sql = "select * from "+table+" where 1=1 ";
		String[] cols = col.split(",");
		String[] values = value.split(",");
		for(int i=0;i<cols.length;i++)
		{
		String pcol = cols[i]==null?"":cols[i];
		String pvalue = values[i]==null?"":values[i];
		if(pcol.equals(""))continue;
		if(pvalue.equals(""))continue;
		sql+=" and "+pcol+"='"+pvalue+"' " ;
		}
		sql+=" and id!="+id;
		System.out.println(sql);
		HashMap<String, String> smap = new HashMap();
		smap.put("sql", sql);
		List list = sysuserService.findByParamWithPages("ajaxSelect", smap, 100, "", request);   

		if(list.size()>0)
		{
		    response.getWriter().print("Y");
		}else{
			response.getWriter().print("N");
		}
		}


		if(checktype.equals("checkdate"))
		{
		value = Info.getUTFStr(value);
		String sqzt = request.getParameter("sqzt");
		String fid = request.getParameter("fid");
		//sqzt = Info.getUTFStr(sqzt); 
		String datechecktype = request.getParameter("datechecktype");
		String sql = "select * from "+table+" where fid= "+fid; 
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");
		sql+=" and fshstatus in('"+(sqzt.replaceAll("-", "','"))+"') and   " ;

		if(datechecktype.equals("rqd")) {
		sql+="( (sdate<='"+sdate+"' and edate>='"+sdate+"' ) " ;
		sql+=" or (sdate<='"+edate+"' and edate>='"+edate+"' ) " ;
		sql+=" or (sdate<='"+sdate+"' and edate>='"+edate+"' ) " ;
		sql+=" or (sdate>='"+sdate+"' and edate<='"+edate+"' ) )" ;
		}

		if(datechecktype.equals("rq")) {
		sql+=" sdate='"+sdate+"'  " ; 
		}

		sql+="  " ;

		System.out.println(sql);
		List list = new CommDAO().select(sql);
		if(list.size()>0)
		{
		response.getWriter().print("Y");
		}else{
		response.getWriter().print("N");
		}
		}

		if(checktype.equals("checkzt"))
		{
		value = Info.getUTFStr(value);
		String sqzt = request.getParameter("sqzt");
		String fid = request.getParameter("fid");
		//sqzt = Info.getUTFStr(sqzt); 
		String datechecktype = request.getParameter("datechecktype");
		String sql = "select * from "+table+" where "
		+ " uname='"+Info.getUser(request).get("uname")+"' "   //！！！这里的uname条件视具体情况，决定要还是不要
		+ " and fid= "+fid;  
		if(StringUtils.isNotEmpty(sqzt))
		sql+=" and fshstatus in('"+(sqzt.replaceAll("-", "','"))+"')    " ; 
		System.out.println(sql);
		List list = new CommDAO().select(sql);
		if(list.size()>0)
		{
		response.getWriter().print("Y");
		}else{
		response.getWriter().print("N");
		}
		}

	} 


	@ResponseBody
	@RequestMapping("/getsonops")
	public void getsonops(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		String glb = request.getParameter("glb")==null?"":request.getParameter("glb");
		String glzd = request.getParameter("glzd")==null?"":request.getParameter("glzd");
		String value = request.getParameter("value")==null?"":request.getParameter("value"); 
		if(!(java.nio.charset.Charset.forName("GBK").newEncoder().canEncode(value)))value = Info.getUTFStr(value);
		String ctype = request.getParameter("ctype")==null?"":request.getParameter("ctype");
		String jlzd = request.getParameter("jlzd")==null?"":request.getParameter("jlzd");
		String jlzdb = request.getParameter("jlzdb")==null?"":request.getParameter("jlzdb");
		String zd = request.getParameter("zd")==null?"":request.getParameter("zd"); 
		glzd = Info.getGBKStr(glzd);
		//value = Info.getUTFStr(value); 
		String insql = value.replaceAll(" ","");
		String insqld = "";
		for(String str:insql.split("-"))
		{
		if(str.split(":").length==1){
		insqld+=str.trim()+",";
		}else{
		insqld+=str.split(":")[1].trim()+",";
		}
		}

		if(insqld.length()>0)insqld = insqld.substring(0,insqld.length()-1);
		String likesql = "";
		for(String str:insqld.split(","))
		{
		   if(!str.trim().equals(""))
		   {
		   if(jlzdb.equals("tglparentid")||jlzdb.equals("datashowname"))
		   {
		   likesql+= " or tglparentid in( select id from "+glb+" where datashowname like'%"+str+"%' ) ";
		   }else{
		   likesql+= " or "+jlzdb+" like'%"+str+"%' ";
		   }
		   }


		}
		String sql = "select * from "+glb+" where 1!=1 "+likesql; 

		HashMap<String, String> smap = new HashMap();
		smap.put("sql", sql);
		List<HashMap> list = new ArrayList<HashMap>();
		if(StringUtils.isNotEmpty(glb))
	    list = sysuserService.findByParamWithPages("ajaxSelect", smap, 100, "", request);   

		if(ctype.equals("select")){
		String select = "@@@";
			for(HashMap permap:list){ 
			String optionstr = "";
			if(glzd.split(";").length==1){
			optionstr=permap.get(glzd.split("~")[0]).toString();
			}else{
			for(String str:glzd.split(";"))
			{
			 String zdstr = str.split("~")[0];
			 String zdnamestr = str.split("~")[1].equals("hnoname")?"":(str.split("~")[1]+":");
			 optionstr+=zdnamestr+permap.get(zdstr)+" - ";
			} 
			}
			if(optionstr.indexOf(" - ")>-1)optionstr=optionstr.substring(0,optionstr.length()-3);
			select+=optionstr+"@@@";
			}
		 if(select.length()>3)select.substring(0,select.length()-3).trim();

		 response.getWriter().print(select);
		 }


		 if(ctype.equals("text")){
		String select = ""; 
			for(HashMap permap:list){ 
			String optionstr = "";
			if(glzd.split(";").length==1){
			optionstr=permap.get(glzd.split("~")[0]).toString();
			}else{
			for(String str:glzd.split(";"))
			{
			 String zdstr = str.split("~")[0];
			 String zdnamestr = str.split("~")[1].equals("hnoname")?"":(str.split("~")[1]+":");
			 optionstr+=zdnamestr+permap.get(zdstr)+" - ";
			} 
			}
			if(optionstr.indexOf(" - ")>-1)optionstr=optionstr.substring(0,optionstr.length()-3);
			select+=optionstr+" ";
			} 

			response.getWriter().print(select.trim());
		 }

		if(ctype.equals("radio")){
		String radio = "";
		    int dxii = 0;
			for(HashMap permap:list){ 
			 String check="";
			 if(dxii==0)check="checked=checked";
			String optionstr = "";
			if(glzd.split(";").length==1){
			optionstr=permap.get(glzd.split("~")[0]).toString();
			}else{

			for(String str:glzd.split(";"))
			{
			 String zdstr = str.split("~")[0];
			 String zdnamestr = str.split("~")[1].equals("hnoname")?"":(str.split("~")[1]+":");
			 optionstr+=zdnamestr+permap.get(zdstr)+" - ";
			} 
			}
			if(optionstr.indexOf(" - ")>-1)optionstr=optionstr.substring(0,optionstr.length()-3);
			radio+="<label><input type='radio' name='"+zd+"' "+check+" value=\""+optionstr+"\">"+optionstr+"</label>\n";
			 dxii++;
			}
		 if(radio.length()>3)radio.substring(0,radio.length()-3).trim();

		 response.getWriter().print(radio);
		 }


		if(ctype.equals("checkbox")){
		String checkbox = "";
		    int dxii = 0;
			for(HashMap permap:list){ 
			 String check="";
			 if(dxii==0)check="checked=checked";
			String optionstr = "";
			System.out.println(glzd);
			if(glzd.split(";").length==1){
			optionstr=permap.get(glzd.split("~")[0]).toString();
			}else{

			for(String str:glzd.split(";"))
			{
			 String zdstr = str.split("~")[0];
			 String zdnamestr = str.split("~")[1].equals("hnoname")?"":(str.split("~")[1]+":");
			 optionstr+=zdnamestr+permap.get(zdstr)+" - ";
			} 
			}
			if(optionstr.indexOf(" - ")>-1)optionstr=optionstr.substring(0,optionstr.length()-3);
			checkbox+="<label><input type='checkbox' name='"+zd+"' "+check+" value=\""+optionstr+"\">"+optionstr+"</label>\n";
			 dxii++;
			}
			checkbox+="<input type=hidden name='"+zd+"' value='' />";
		 if(checkbox.length()>3)checkbox.substring(0,checkbox.length()-3).trim();

		 response.getWriter().print(checkbox);
		 }


		if(ctype.equals("checkboxdk")){
		String checkbox = "";
		  int i=0;
		for(HashMap permap:list){ 

		 String optionstr = "";
		 for(String str:glzd.split(";"))
		 {
			 String zdstr = str.split("~")[0];
			 String zdnamestr = str.split("~")[1].equals("hnoname")?"":(str.split("~")[1]+":");
			 optionstr+=zdnamestr+permap.get(zdstr)+" - ";
		 }
		 if(optionstr.length()>0)optionstr=optionstr.substring(0,optionstr.length()-3);
		 String nbs = "";
		 if(i>0)nbs="&nbsp;";
		 checkbox+="<label>"+nbs+"<input type='checkbox' name='"+zd+"' value=\""+optionstr+"\">"+optionstr+"</label>&nbsp;&nbsp;\n";
		 checkbox+="<label>&nbsp;- 这里要改 &nbsp;<input type='text' size='5' name='"+optionstr+"' value=\"\"></label><br />\n";
		 i++;
		}
		checkbox+="<input type=hidden name='"+zd+"' value='' /><input type=hidden name='dk-"+zd+"-value' value='这里要改' />";

		response.getWriter().print(checkbox);
		 }


if(ctype.equals("tchat")){
String id = request.getParameter("id");
String tname = Info.getUTFStr(request.getParameter("tname"));
String tuname = Info.getUTFStr(request.getParameter("touname")) ;
String tcontent = request.getParameter("tcontent") ;
String tsql = "insert into zxjl values(null,'"+tname+"','"+tuname+"','"+tcontent+"','"+Info.getDateStr()+"','"+id+"',null,null) " ;
new CommDAO().commOper(tsql);
}

//在线聊天处理开始
if(ctype.equals("chat")){
String str = "";
String id = request.getParameter("id");
String tname = Info.getUTFStr(request.getParameter("tname")) ;

String tsql = "select * from (select * from zchats where pid='"+id+"' or id='"+id+"' " ;

  tsql +="  and (1!=1 " ; 
  tsql +="  or  uname like'%"+Info.getUser(request).get("uname")+"%'  or  touname like'%"+Info.getUser(request).get("uname")+"%' " ;
  tsql +="  ) ";
  tsql +=" order by id desc limit 0,6 ) a order by id  ";

String url = "szxjlcx.jsp?1=1&id="+id ; 
 smap = new HashMap<String, String>();
 smap.put("sql", tsql);
list = sysuserService.findByParamWithPages("ajaxSelect", smap, 100, url, request); 
for(HashMap map:list){  
 str+="  &nbsp;&nbsp; <font color=orange>";
 str+= map.get("uname")+" &nbsp;  "+map.get("savetime")+" : <br />  </font>&nbsp;&nbsp; ";
 str+= map.get("tcontent")+"<table><tr><td height=1></td></tr></table>"; 
} 
response.getWriter().print(str);
 }

if(ctype.equals("tchat")){
String id = request.getParameter("id");
String tname = Info.getUTFStr(request.getParameter("tname"));
String tuname = Info.getUTFStr(request.getParameter("touname")) ;
String tcontent = request.getParameter("tcontent") ;
String tsql = "insert into zchats values(null,'"+tname+"','"+tuname+"','"+tcontent+"','"+Info.getDateStr()+"','"+id+"',null,null) " ;
new CommDAO().commOper(tsql);
}
//在线聊天处理结束

     //浏览记录处理开始
     if(ctype.equals("viewhistory")) {
         CommDAO dao = new CommDAO();
         HashMap cuser = Info.getUser(request);
         String uname = "";
         String uid = "";
         String tname = "";
         if(cuser!=null){
             uname = cuser.get("uname").toString();
             uid = cuser.get("id")+"";
             tname = cuser.get("tname").toString();
         }
         String fid = request.getParameter("fid")==null?"":request.getParameter("fid");
         String ftitle = request.getParameter("ftitle")==null?"":request.getParameter("ftitle");
         String ftable = request.getParameter("ftable")==null?"":request.getParameter("ftable");
         if(StringUtils.isNotEmpty(fid)) {
         dao.commOper("insert into viewhistory ( " +
         "uname," +
         "tname," +
         "uid," +
         "fid," +
         "ftable," +
         "ftitle," +
         "savetime) values ( " +
         "'"+uname+"'," +
         "'"+tname+"'," +
         "'"+uid+"'," +
         "'"+fid+"'," +
         "'"+ftable+"'," +
         "'"+ftitle+"'," +
         "'"+Info.getDateStr()+"')");
         }
         List<HashMap> tlist = new CommDAO().select("select ftable,fid,count(1) clicknums from viewhistory group by ftable,fid");
          for(HashMap<String,String> m: tlist){
          ftable = m.get("ftable");
          fid = m.get("fid");
          String clicknums = m.get("clicknums");
          new CommDAO().commOper("update "+ftable+" set clicknums='"+clicknums+"' where id="+fid);
          new CommDAO().commOper("update "+ftable+" set clicknums='0' where clicknums is null");
         }
     }
     //浏览记录处理结束


//业务代码块结束---

	} 


}
