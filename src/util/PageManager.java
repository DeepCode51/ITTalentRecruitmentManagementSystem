

package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
 

import dao.CommDAO;
import service.SysuserService;

 
public class PageManager {
	private PageManager() {

	}

	//默认一页数量
	public static int defPageSize = 5;

	// 默认一页最大记录数
	public static final int DEFAULTPAGESIZE = 100;

	// 分页段
	public static final int segment = 10;

	// 当前页数
	protected int currentPage;

	// 一页长度
	protected int pageSize;

	// 总页数
	protected long pageNumber;

	// 总记录数
	protected long count;

	// 数据
	protected Collection collection;

	// 数据查询对象
	protected CommDAO dao = new CommDAO();

	// 表现层代码
	protected String info;

	// 请求路径
	protected String path;

	// 服务器请求对象
	protected HttpServletRequest request;

	/*
	 * 仅仅只是加到路径中去
	 */
	protected String parameter = "";
	 

	/**
	 * 
	 * @param 下一页的分页链接
	 * @param 一页最大记录数
	 * @param 当前HttpServletRequest对象
	 * @param 数据库操作对象
	 */
	public PageManager(String path, int pageSize, HttpServletRequest request) {
		// 任意一个dao都行
		this.currentPage = 1;
		this.pageNumber = 1;
		this.count = 0;
		this.pageSize = pageSize <= 0 ? DEFAULTPAGESIZE : pageSize;
		this.request = request;
		this.path = path;

		request.setAttribute("page", this);

		try {
			this.currentPage = Integer.parseInt(request
					.getParameter("currentPage")) <= 0 ? 1 : Integer
					.parseInt(request.getParameter("currentPage"));

		} catch (Exception e) {

			try {
				this.currentPage = Integer.parseInt((String) request
						.getSession().getAttribute("currentPage"));

			} catch (Exception e1) {
				this.currentPage = 1;

			}

		}

	}
	
	
	
	

	
	

	/**
	 * 
	 * @param 下一页的分页链接
	 * @param 一页最大记录数
	 * @param 当前HttpServletRequest对象
	 * @param 数据库操作对象
	 */
	public static PageManager getPage(String path, int pageSize,
			HttpServletRequest request) {
		return new PageManager(path, pageSize, request);

	}

	/** 
	 * @param hql语句 
	 */
	public  String getDataList(String table,String method,HashMap<String, String> selectmap,HttpServletRequest request) { 
	    table = table.substring(0,1).toUpperCase()+table.substring(1).toLowerCase();
	    table = "entity.mapper."+table+"Mapper."+method;
		List<HashMap> list=this.sqlsession.selectList(table, selectmap);
		String col = selectmap.get("tablecol");
		List<List> rlist = new ArrayList();
		for(HashMap<String, String> map:list) {
			List olist = new ArrayList();
			for(String str:col.split("@")) {
				String colname = str.split("-")[0];
				olist.add(map.get(colname));
			}
			rlist.add(olist);
		}
		String filename = Info.writeExcel("", col, rlist, request);
		return filename;
	}
	
	/** 
	 * @param hql语句 
	 */
	public void doList(String selectMethod,HashMap<String, String> selectmap) { 
	 
		List<HashMap> list = new ArrayList<HashMap>(); 
		list = this.sqlsession.selectList(selectMethod, selectmap);
		this.count = list.size();
		if (this.count != 0) {
			this.pageNumber = count % this.pageSize == 0 ? this.count
					/ this.pageSize : this.count / this.pageSize + 1;
			if (this.currentPage > this.pageNumber)
				this.currentPage = (int) this.pageNumber; 
		}
		this.request.getSession().setAttribute("currentPage",
				String.valueOf(this.currentPage));
		this.collection = this.selectToPage(list,this.currentPage , this.pageSize); 
		this.refreshUrl();
	}
	
	public List<HashMap> selectToPage(List<HashMap> list,int pageno, int rowsize) { 
		List<HashMap> mlist=new ArrayList<HashMap>();
		try{ 
			int min = (pageno-1)*rowsize;
			int max = pageno*rowsize;
			
			for(int i=0;i<list.size();i++)
			{ 
				if(!(i<min||i>(max-1)))
				{
				mlist.add(list.get(i));
				}
			}
		}catch(RuntimeException re){
			re.printStackTrace();
			throw re;
		} 
		return mlist;
	}
	
	public List<HashMap> select(String selectmethod,HashMap<String, String> selectmap) {
		List<HashMap> list=new ArrayList<HashMap>(); 
		try{
			list=this.sqlsession.selectList(selectmethod, selectmap); 
			 
		}catch(RuntimeException re){
			re.printStackTrace();
			throw re;
		} 
		return list;
	}
	
	public void doListByParam(HashMap<String, String> param,HttpServletRequest request) { 
		this.count = this.dao.selectByParam(request, param).size();
		if (this.count != 0) {
			this.pageNumber = count % this.pageSize == 0 ? this.count
					/ this.pageSize : this.count / this.pageSize + 1;
			if (this.currentPage > this.pageNumber)
				this.currentPage = (int) this.pageNumber; 
		}
		this.request.getSession().setAttribute("currentPage",
				String.valueOf(this.currentPage));
		this.collection = this.dao.selectByParam(request,param,
				this.currentPage , this.pageSize); 
		this.refreshUrl();
	}
	

	/**
	 * 
	 * @param 查询条件集合
	 *            如没有条件只是列表就不使用这个方法
	 */
	public void addParameter(List parameter) {

		StringBuffer para = new StringBuffer("");
		if (parameter != null && parameter.size() != 0) {
			Iterator iterator = parameter.iterator();
			while (iterator.hasNext()) {
				para.append("&").append(iterator.next().toString());
			}
		}
		this.parameter = para.toString();

	}

	/**
	 * 刷新分页路径
	 * 
	 */
	protected void refreshUrl() {
		StringBuffer buf = new StringBuffer();
		buf.append("<font>共").append(count);
		buf.append("条");
		buf.append("&nbsp;&nbsp;");
		buf.append("第").append(this.currentPage).append("/").append(
				this.pageNumber).append("页");
		buf.append("&nbsp;&nbsp;&nbsp;&nbsp;");
		if (this.currentPage == 1)
			buf.append("首页");
		else
			buf.append("<a href=\"javascript:f1.action='").append(this.path).append("&currentPage=1';f1.submit();\"") 
					.append("' class='ls'>").append("首页")
					.append("</a>");
		// ////////////////////////#1157B7
		buf.append("&nbsp;&nbsp;&nbsp;&nbsp;");

		if (this.currentPage > 1) {
			buf.append("<a href=\"javascript:f1.action='").append(this.path).append("&currentPage=")
					.append(currentPage - 1).append(parameter).append(
							"';f1.submit();\" class='ls'>").append("上页")
					.append("</a>");
		} else {
			buf.append("上页");

		}
		buf.append("&nbsp;&nbsp;");

		int currentSegment = this.currentPage % segment == 0 ? this.currentPage
				/ segment : this.currentPage / segment + 1;

		/*for (int i = 1; i <= this.pageNumber; i++) {
			if (this.currentPage == i)
				buf.append("<font color='red'>").append(i).append("</font>");

			else
				buf.append("<a href='").append(this.path).append(
						"&currentPage=").append(i).append(parameter).append(
						"' class='ls'>[").append(i).append(
						"]</a>");
		}*/

		buf.append("&nbsp;&nbsp;");
		if (this.currentPage < this.pageNumber) {
			buf.append("<a href=\"javascript:f1.action='").append(this.path).append("&currentPage=")
					.append(currentPage + 1).append(parameter).append(
							"';f1.submit();\" class='ls'>").append("下页")
					.append("</a>");
		} else {

			buf.append("下页");

		}

		buf.append("&nbsp;&nbsp;&nbsp;&nbsp;");
		if (this.currentPage == this.pageNumber)
			buf.append("末页&nbsp;&nbsp;");
		else
			buf.append("<a href=\"javascript:f1.action='").append(this.path).append("&currentPage=")
					.append(this.pageNumber).append(parameter).append(
							"';f1.submit();\" class='ls'>").append("末页")
					.append("</a></font>&nbsp;&nbsp;");
		// ////////////////////
		// for (int i = 0; i < this.pageNumber; i++) {
		// if (this.currentPage == i + 1) {
		// buf.append("<font color=red>[" + (i + 1) + "]</font>").append(
		// "&nbsp;");
		// } else {
		// buf.append("<a href='").append(this.path).append(
		// "&currentPage=").append(i + 1).append(parameter)
		// .append("' style='TEXT-DECORATION:none'>").append(
		// "[" + (i + 1) + "]").append("</a>&nbsp;");
		// }

		// }
		buf.append("<select onchange=\"javascript:f1.action='"+this.path+"&currentPage='+").append(
				"this.options[this.selectedIndex].value;f1.submit();").append(parameter)
				.append("\">");
		for (int i = 0; i < this.pageNumber; i++) {
			if (this.currentPage == i + 1)
				buf.append("<option value=" + (i + 1)
						+ " selected=\"selected\">" + (i + 1) + "</option>");
			else
				buf.append("<option value=" + (i + 1) + ">" + (i + 1)
						+ "</option>");

		}
		buf.append("</select>");
		this.info = buf.toString();
		
	}

	public Collection getCollection() {
		return collection;
	}

	public long getCount() {
		return count;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public long getPageNumber() {
		return pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getInfo() {
		return info;
	}
	
	public static ArrayList<HashMap> getPages(String url,int pagesize,String selectMethod,HashMap selectmap,HttpServletRequest httprequest )
	{   
	  if(sqlsession==null)initSqlSession(httprequest); 
	  PageManager pageManager = PageManager.getPage(url, pagesize, httprequest);
	  //取session中的信息，放入查询条件
	  if(Info.getUser(httprequest)!=null){
	    for(Entry<String, String> entry:((HashMap<String, String>)Info.getUser(httprequest)).entrySet()){ 
	    	if(entry.getKey().equals("id"))continue; 
	    	selectmap.put("sysuser_"+entry.getKey(), entry.getValue()); 
	    } 
	  }
	  pageManager.doList(selectMethod,selectmap);
	  PageManager bean = (PageManager) httprequest.getAttribute("page"); 
	  ArrayList<HashMap> nlist = (ArrayList) bean.getCollection();
	  ServletContext request=(ServletContext)httprequest.getSession().getServletContext();
	  request.setAttribute("slist", nlist);
	  request.setAttribute("page", pageManager);
	  return nlist;
	}

	public static ArrayList<HashMap> getPages(HttpServletRequest request )
	{  
	  PageManager bean = (PageManager) request.getAttribute("page"); 
	  ServletContext response=(ServletContext)request.getSession().getServletContext();
	  ArrayList<HashMap> nlist = (ArrayList<HashMap>)response.getAttribute("slist");
	  return nlist;
	}
	
	public static ArrayList<HashMap> getPagesByParam(String url,int pagesize,HashMap<String, String> param,HttpServletRequest request )
	{ 
		PageManager pageManager = PageManager.getPage(url, pagesize, request);
		  pageManager.doListByParam(param, request);
		  PageManager bean = (PageManager) request.getAttribute("page"); 
		  ArrayList<HashMap> nlist = (ArrayList) bean.getCollection();
		  return nlist;
	}
	
	public static SqlSessionFactory fac = null; 
	public static SqlSession sqlsession = null;  
	public static void initSqlSession(HttpServletRequest request)
	{
		 fac = (SqlSessionFactory)Info.getDao(request,"sqlSessionFactory"); 
		 sqlsession = fac.openSession(); 
	}
	
	
    public static void main(String[] args) { 
		
	}

}
