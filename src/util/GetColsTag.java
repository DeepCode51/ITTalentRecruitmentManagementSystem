package util;
 
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.web.context.support.WebApplicationContextUtils;

import dao.CommDAO;
import entity.bean.Sysuser;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
 
public class GetColsTag extends SimpleTagSupport {
	
	public String type;
	public String trid;
	public String name;
	public String url;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTrid() {
		return trid;
	}
	public void setTrid(String trid) {
		this.trid = trid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void doTag() {
		JspWriter out = getJspContext().getOut();
		HttpServletRequest request = (HttpServletRequest)((PageContext)getJspContext()).getRequest();
//		try {
//			out.write(Info.getCol(type, trid, name, url, request));
//		} catch (IOException e) { 
//			e.printStackTrace();
//		}		
		
		try {
			String oname = "";
			HashMap<String, String> umap = Info.getUser(request);
			
			List<HashMap> ulist = new CommDAO().select("select * from sysuser where uname='"+name+"'");
			if(ulist.size()>0)
			{
				HashMap<String, String> usermap = ulist.get(0);
				if(usermap.get("isnm").equals("匿名")) {
					oname = "匿名网友";
				}
			}
			
			if(umap!=null){
				if(umap.get("utype").equals("教师"))oname = name;
				if(umap.get("uname").equals(name))oname = name;
			}
			
			 
			if(request.getRequestURI().contains("compl")) {
			if(ulist.size()>0)
			{
				HashMap<String, String> usermap = ulist.get(0);
				if(usermap.get("isnm").equals("匿名")) {
					oname = "匿名网友";
				}
			}
			}
			
			if(oname.equals(""))oname=name;
			
			out.write(oname);
		} catch (IOException e) { 
			e.printStackTrace();
		}	
		
	}
	
}

		

