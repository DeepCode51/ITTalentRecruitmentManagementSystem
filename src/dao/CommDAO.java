package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.ContextLoader;
 
 

import util.Info;
import util.StrUtil;


public class CommDAO
{
	public static String dbname = "";
	public static String dbtype = "";
	
	public static Connection conn = null;
	
	 @Resource 
     public org.springframework.jdbc.datasource.DriverManagerDataSource dataSource; 
	 
	 public static SqlSessionFactory fac = null; 
	 public static SqlSession sqlsession = null; 
	
	
	public CommDAO()  
	{
		//conn = this.getConn();
	}
	
	
	 

		
		public String getCheckBoxDkValues(HttpServletRequest request,String name)
		{
			String value="";
			String[] values = request.getParameterValues(name);
			if(values!=null){
 		for(String vstr:values)
 		{
 			if(vstr==null)vstr="";
 			if(vstr.equals("null"))vstr="";
 			if(vstr.trim().equals(""))continue; 
 			if(request.getParameter(vstr)!=null&&!"".equals(request.getParameter(vstr))&&request.getParameter("dk-"+name+"-value")!=null)
 			{
 				String dkv = request.getParameter(vstr);
 				String dknamevalue = request.getParameter("dk-"+name+"-value");
 				vstr+=" - "+dknamevalue+":"+dkv;
 			} 
 			value+=vstr+" ~ "; 
 		}
			}
 		if(value==null)value="";
			if(value.equals("null"))value="";
 		if(value.length()>0)value=value.substring(0,value.length()-3);
			return value;
		}

	
	public String getCheckBoxValues(HttpServletRequest request,String name)
	{
		String value = "";
		if(request.getParameterValues(name)!=null)
		{
			String[] values = request.getParameterValues(name);
			if(values.length>1) {
			for(String str:values)
			{
				value+=str+" ~ ";
			}
			if(value.length()>0)value = value.substring(0,value.length()-3);
			}else {
				value = values[0];
			}
		}
		
		return value;
	}
	
	public static void initSqlSession(HttpServletRequest request)
	{
		 fac = (SqlSessionFactory)Info.getDao(request,"sqlSessionFactory"); 
		 sqlsession = fac.openSession(); 
	}
	
	
	

	 public String getID(String tablename,String colname,String gltablename,String glcolname,String values)
		{
			String id = "";
            if(glcolname.contains(";"))glcolname=glcolname.split(";")[0];
			for(String v:values.split(" ~ ")){
			
			String sql = "select id from "+gltablename+" where 1=1 ";
			int i = 0;
			for(String str:glcolname.split(";"))
			{
				String cname = str.split("~")[0];
				String value = v.split(" - ")[i] ;
				if(value.split(":").length==2)
				{
					value = value.split(":")[1];
				} 
				sql+=" and "+cname+"='"+value+"' ";
				i++;
			}
			List<HashMap> list = select(sql); 
			if(list.size()>0)id += list.get(0).get("id").toString()+"-"; 
			}
			if(id.length()>0)id=id.substring(0,id.length()-1);
			return id;
		}
	 
	 //根据一个字段查找表里的另外一个字段   根据字段bycol   查找字段findcol  
	 public String getValueByOCol(String tablename,String bycol,String findcol,String byvalue)
	 {
		 String v = "";
		 String sql = "select * from "+tablename+" where "+bycol+"='"+byvalue+"'";
		 List<HashMap> list = select(sql);
		 if(list.size()>0)
		 {
			 v = list.get(0).get(findcol).toString();
		 }
		 return v;
	 }
		
	 public void updateById(String tablename,String colname,String gltablename,String glcolname,String jlzd,String isu)
		{ 
			String ckey = "";
			if(jlzd==null||"".equals(jlzd))ckey = colname;
			else ckey = jlzd; 
			if(isu.equals("1"))ckey="sysuser"; 
			for(HashMap tmap:select("select "+ckey+"key,id from "+tablename)){
				String id = tmap.get("id").toString();
				String key = tmap.get(ckey+"key").toString();
				if(key.length()>0)key="'"+key.replaceAll("-", "','")+"'";
				 String sql = "update "+tablename+" set "+colname+" = '";
				 String col = "";
				 String value="";
				 if(!"".equals(key)){
			for(HashMap map:select("select * from "+gltablename+" where id in("+key+")")){
			 HashMap<String, String> vmap = map; 
			 
			 for(String str : glcolname.split(";"))
			 { 
				 String cname=str.split("~")[0];
				 if(cname.split(":").length>1)cname = cname.split(":")[1];
				 String hcname = "";
				 if(str.split("~").length>0)
				 {
				 hcname=str.split("~")[1];
				 if(!str.contains("hnoname"))
				 value+=hcname+":"+vmap.get(cname);
				 else
				 value+=vmap.get(cname);
				 }else{
				 value+=hcname+":"+vmap.get(cname);
				 }
				 value+=" - ";
			 } 
			 if(value.length()>0)value=value.substring(0,value.length()-3);
			 value+=" ~ ";
			}
			}
			 if(value.length()>0)value=value.substring(0,value.length()-3);
			 sql+=value;
			 sql+="' where id= "+id; 
			 if(value.length()>0)
			 commOper(sql);
			}
		} 
		  
	
	 //	该方法返回一个table 用于流动图片
	public String DynamicImage(String categoryid,int cut,int width,int height){

		StringBuffer imgStr = new StringBuffer();
		StringBuffer thePics1 = new StringBuffer();
		StringBuffer theLinks1 = new StringBuffer();
		StringBuffer theTexts1 = new StringBuffer();
	
		imgStr.append("<div id=picViwer1  style='background-color: #ffffff' align=center></div><SCRIPT src='/itivtmgr/js/dynamicImage.js' type=text/javascript></SCRIPT>\n<script language=JavaScript>\n");
		thePics1.append("var thePics1=\n'");
		theLinks1.append("var theLinks1='");
		theTexts1.append("var theTexts1='");
		
		List<HashMap> co = this.select("select * from mixinfo  where infotype='滚动图片' order by id ",1,6);
		int i = co.size();
		
		int j = 0; 
		for(HashMap b:co)
		{
			j++; 
		int id = Integer.parseInt(b.get("id").toString()) ;
		String title = b.get("title").toString();
		
		String url = "/itivtmgr/upfile/"+b.get("filename");
		
		String purl = "/itivtmgr/nxiang.jsp?id="+b.get("id");
		
		if(j!=i){
		thePics1.append(url.replaceAll("\n", "")+"|");
		theLinks1.append(purl+"|");
		theTexts1.append(title+"|");
		}
		if(j==i)
		{
			thePics1.append(url.replaceAll("\n", ""));
			theLinks1.append("nxiang.jsp?id="+b.get("id"));
			theTexts1.append(title);
		}
		
		}
	   thePics1.append("';");
		
		theLinks1.append("';");
		theTexts1.append("';");
		imgStr.append(thePics1+"\n");
		imgStr.append(theLinks1+"\n");
		imgStr.append(theTexts1+"\n");
		imgStr.append("\n setPic(thePics1,theLinks1,theTexts1,"+width+","+height+",'picViwer1');</script>");
		return imgStr.toString();
	}
	
	 


		public HashMap getmap(String id,String table)
		{
			List<HashMap> list = new ArrayList();
			try {
				Statement st =  this.getConn().createStatement();
				System.out.println("select * from "+table+" where id="+id);
			    ResultSet rs = st.executeQuery("select * from "+table+" where id="+id);
			    ResultSetMetaData rsmd = rs.getMetaData();
	            while(rs.next())
			    {
			    	HashMap map = new HashMap();
			    	int i = rsmd.getColumnCount();
			    	for(int j=1;j<=i;j++)
			    	{
			    		if(!rsmd.getColumnName(j).equals("ID"))
			    		{
			    			String str = rs.getString(j)==null?"": rs.getString(j);
			    			if(str.equals("null"))str = "";
			    			map.put(rsmd.getColumnName(j).toLowerCase(), str);
			    		}
			    		else
			    			map.put("id", rs.getString(j));
			    	}
			    	list.add(map);
			    }
			    rs.close();
			    st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list.get(0);
		}
		
		


		 
		public String insert(HttpServletRequest request,HttpServletResponse response, String tablename,HashMap extmap,boolean alert,boolean reflush)
		{
			extmap.put("savetime", Info.getDateStr());
			if(request.getParameter("f")!=null){
			HashMap typemap = new HashMap();
			ArrayList<String> collist = new ArrayList();
			String sql = "insert into "+tablename+"(";
			
		 
			Connection conn = this.getConn();
			try {
				Statement st = conn.createStatement();
			    ResultSet rs = st.executeQuery("select * from "+tablename);
			    ResultSetMetaData rsmd = rs.getMetaData();
	            int i = rsmd.getColumnCount();
			    	 
			    	for(int j=1;j<=i;j++)
			    	{
			    	if(rsmd.getColumnName(j).equals("id"))continue;
			    	typemap.put(rsmd.getColumnName(j).toLowerCase()+"---", rsmd.getColumnTypeName(j));
			    	collist.add(rsmd.getColumnName(j).toLowerCase());
	    	    	sql+=rsmd.getColumnName(j).toLowerCase()+",";
			    	}
			    	sql = sql.substring(0,sql.length()-1);
			    
			    sql+=") values(";
			    rs.close();
			    st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Enumeration enumeration = request.getParameterNames();
			String names = ",";
			while(enumeration.hasMoreElements())
			{
			names += enumeration.nextElement().toString()+",";
			}
			try {
				Statement st = conn.createStatement();
			    for(String str:collist)
			    {
			    	
			    	
			    	if(str.equalsIgnoreCase("id"))
			    	{
			    		sql+=""+StrUtil.generalSrid()+",";	
			    	}else{
			    	
			    	
			    	if(names.indexOf(","+str+",")>-1)
			    	{
			    		String[] values = request.getParameterValues(str);
			    		String value="";
			    		for(String vstr:values)
			    		{
			    			if(vstr==null)vstr="";
			    			if(vstr.equals("null"))vstr="";
			    			if(vstr.trim().equals(""))continue;
			    			
			    			if(request.getParameter(vstr)!=null&&!"".equals(request.getParameter(vstr))&&request.getParameter("dk-"+str+"-value")!=null)
			    			{
			    				String dkv = request.getParameter(vstr);
			    				String dknamevalue = request.getParameter("dk-"+str+"-value");
			    				vstr+=" - "+dknamevalue+":"+dkv;
			    			}
			    			
			    			value+=vstr+" ~ ";
			    			
			    			
			    			
			    		}
			    		if(value==null)value="";
		    			if(value.equals("null"))value="";
			    		if(value.length()>0)value=value.substring(0,value.length()-3);
			    		 
			    	    if(typemap.get(str+"---").equals("int"))
			    	    {
			    		sql+=(value.equals("")?-10:value)+",";
			    	    }else{
			    	    sql+="'"+(value.equals("null")?"":value)+"',";
			    	    }
			    	}else{
			    		if(typemap.get(str+"---").equals("int"))
			    	    {
			    		sql+=(extmap.get(str)==null?"":extmap.get(str))+",";
			    	    }else{
			    	    sql+="'"+(extmap.get(str)==null?"":extmap.get(str))+"',";
			    	    }
			    	}
			    	
			    	
			    	
			    	}
			    	
			    	
			    	
			    }
			    
			    sql=sql.substring(0,sql.length()-1)+")";
			    System.out.println(sql);
			    this.commOper(sql);
			  
			    st.close();
			     
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String str = "";
			if(!reflush)
			 str += "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> \n";
			 str +=	"<script language=javascript>\n";
			if(alert){
			request.getSession().setAttribute("alertsuc", "");
			}
			if(reflush){
				str+="parent.location=parent.location;\n";
			}
			str+="</script>";
			
			
			PrintWriter wrt = null;
			try {
			wrt = response.getWriter();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			wrt.write(str);
			} 
			return "";
		}
		

		public String dyTformTR(String frgroupno,String cols,String dytable)
		{ 
			String tr = "";
			List<HashMap> list = select("select * from "+dytable+" where frgroupno='"+frgroupno+"'");
			for(int i=0;i<list.size();i++)
			{ 
				HashMap m = list.get(i);
				tr+="<tr>\n";
				for(String str:cols.split(";"))
				{
					String colname = str.split("-")[0];
					tr+="<td height=\"30\" align=\"center\">"+m.get(colname)+"</td>\n";
				}
				tr+="</tr>\n";
			} 
			return tr;
		}
		
		
		public void delete(HttpServletRequest request,String tablename)
		{
			int i = 0;
			try {
				String did = request.getParameter("did");
				if(did==null)did = request.getParameter("scid");
				if(did!=null){
					if(did.length()>0){
				Statement st = conn.createStatement();
				
				String cols = getCols(tablename);
				if(cols.contains("frgroupno")) 
				{
					HashMap<String, String> map = getmap(did, tablename);
					for(Entry<String, String> entry : map.entrySet())
					{
						if(entry.getValue()!=null&&entry.getValue().startsWith("dytab-"))
						{
							String rtab = entry.getValue().replaceAll("dytab-", "");
							String frgroupno = map.get("frgroupno");
							commOper("delete from "+rtab+" where frgroupno='"+frgroupno+"'");
						}
					}
				}
				
				 st.execute("delete from "+tablename+" where id="+did);
				 st.close();
					}
				} 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

		
		
		public String getCols(String table)
		{  
			String str = "";
			Connection conn = this.getConn();
			try {
				Statement st = conn.createStatement();
			    ResultSet rs = st.executeQuery("select * from "+table);
			    ResultSetMetaData rsmd = rs.getMetaData();
			    
			    int i = rsmd.getColumnCount(); 
		    	for(int j=2;j<=i;j++)
		    	{
		    	str+=rsmd.getColumnName(j)+",";
		    	}
			    
			}catch (Exception e) {
				e.printStackTrace();
			}
			str = str.substring(0,str.length()-1);
			 
			return str;
		}
		
		
		public String update(HttpServletRequest request,HttpServletResponse response, String tablename,HashMap extmap,boolean alert,boolean reflush )
		{
			if(request.getParameter("f")!=null){
			Enumeration enumeration = request.getParameterNames();
			String names = ",";
			while(enumeration.hasMoreElements())
			{
			names += enumeration.nextElement().toString()+",";
			}
			HashMap typemap = new HashMap();
			ArrayList<String> collist = new ArrayList();
			String sql = "update "+tablename+" set ";
			Connection conn = this.getConn();
			try {
				Statement st = conn.createStatement();
			    ResultSet rs = st.executeQuery("select * from "+tablename);
			    ResultSetMetaData rsmd = rs.getMetaData();
	            int i = rsmd.getColumnCount();
			    	System.out.println(i);
			    	for(int j=1;j<=i;j++)
			    	{
			    		
			    	if(rsmd.getColumnName(j).toLowerCase().equals("id"))continue;
			    	
			    	typemap.put(rsmd.getColumnName(j).toLowerCase()+"---", rsmd.getColumnTypeName(j));
			    	
			    	collist.add(rsmd.getColumnName(j).toLowerCase());
			    	
	    	    	if(names.indexOf(","+rsmd.getColumnName(j).toLowerCase()+",")>-1)
	    	    	{
	    	    		
	    	    		String[] values = request.getParameterValues(rsmd.getColumnName(j).toLowerCase());
			    		String value="";
			    		for(String vstr:values)
			    		{
			    			if(vstr==null)vstr="";
			    			if(vstr.equals("null"))vstr="";
			    			if(vstr.trim().equals(""))continue;
			    			
			    			if(request.getParameter(vstr)!=null&&!"".equals(request.getParameter(vstr))&&request.getParameter("dk-"+rsmd.getColumnName(j).toLowerCase()+"-value")!=null)
			    			{
			    				String dkv = request.getParameter(vstr);
			    				String dknamevalue = request.getParameter("dk-"+rsmd.getColumnName(j).toLowerCase()+"-value");
			    				vstr+=" - "+dknamevalue+":"+dkv;
			    			}
			    			
			    			value+=vstr+" ~ ";
			    		}
			    		if(value==null)value="";
		    			if(value.equals("null"))value="";
			    		if(value.length()>0)value=value.substring(0,value.length()-3);
	    	    		
	    	    		if(rsmd.getColumnTypeName(j).equals("int"))
	    	    		{
	    	    		 sql+=rsmd.getColumnName(j).toLowerCase()+"="+value+",";
	    	    		}else{
	    	    			 sql+=rsmd.getColumnName(j).toLowerCase()+"='"+value+"',";
	    	    		}
	    	    	}else{
	    	    		if(extmap.get(rsmd.getColumnName(j).toLowerCase())!=null)
	    	    		{
	    	    			if(rsmd.getColumnTypeName(j).toLowerCase().equals("int"))
	        	    		{
	        	    		 sql+=rsmd.getColumnName(j).toLowerCase()+"="+extmap.get(rsmd.getColumnName(j).toLowerCase())+",";
	        	    		}else{
	        	    			 sql+=rsmd.getColumnName(j).toLowerCase()+"='"+extmap.get(rsmd.getColumnName(j).toLowerCase())+"',";
	        	    		}
	    	    		}
	    	    	}
			    	}
			    	sql = sql.substring(0,sql.length()-1);
			    	sql+=" where id="+request.getParameter("id");
			       System.out.println(sql);
			    	Statement st1 = conn.createStatement();
			    	st1.execute(sql);
			    	st1.close();
			    rs.close();
			    st.close(); 
			     
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String str = "";
			if(!reflush)
			// str += "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" ;
			 str +=	"<script language=javascript>\n";
			if(alert){
				request.getSession().setAttribute("alertsuc", "");
			}
			if(reflush){
				str+="parent.location=parent.location;\n";
			}
			 
			str+="</script>\n";
			
			PrintWriter wrt = null;
			try {
				//request.get
			wrt = response.getWriter();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			wrt.write(str);
			}
	        return "";
		}
		
		
		
	
		public Connection getConn()
		{
		        try
		        {  
		        	if(conn==null||conn.isClosed()){
		        		System.out.println("获取conn");
		        		DriverManagerDataSource datasource = (DriverManagerDataSource)ContextLoader.getCurrentWebApplicationContext().getBean("dataSource");
		                conn = datasource.getConnection();
		        		//这段代码不用改
//		        		Class.forName("com.mysql.jdbc.Driver");
//			        	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/itivtmgr","root","123");
			       
		        		
		        	}}
		        catch(Exception e)
		        {
		            e.printStackTrace();
		        }
		        return conn;
		}
	
	
	public int getInt(String sql)
	{
		int i = 0;
		try {
			Statement st = this.getConn().createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
			{
				i = rs.getInt(1);
			}
			    st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	
	public double getDouble(String sql)
	{
		System.out.println(sql);
		double i = 0;
		try {
			Statement st =  this.getConn().createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
			{
				i = rs.getDouble(1);
				
			}
			    st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public void commOper(String sql)
	{  
		System.out.println(sql);
		try {
			Statement st =  this.getConn().createStatement();
		    st.execute(sql);
		    st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void commOperSqls(ArrayList<String> sql)
	{
		
		try {
			conn.setAutoCommit(false);
			for(int i=0;i<sql.size();i++)
			{
			Statement st =  this.getConn().createStatement();
			System.out.println(sql.get(i));
		    st.execute(sql.get(i));
		    st.close();
			}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	
	public List<HashMap> select(String sql)
	{ 
		System.out.println(sql);
		List<HashMap> list = new ArrayList();
		try {
			Connection conn = this.getConn();
			PreparedStatement st = conn.prepareStatement(sql);
		    ResultSet rs = st.executeQuery();
		    ResultSetMetaData rsmd = rs.getMetaData();
        
		    while(rs.next())
		    {
		    	HashMap map = new HashMap();
		    	int i = rsmd.getColumnCount();
		    	for(int j=1;j<=i;j++)
		    	{
		    		if(!rsmd.getColumnName(j).equals("ID"))
		    		{
		    			String str = rs.getString(j)==null?"": rs.getString(j);
		    			if(str.equals("null"))str = "";
		    			map.put(rsmd.getColumnName(j).toLowerCase(), str);
		    		}
		    		else
		    			map.put("id", rs.getString(j));
		    	}
		    	list.add(map);
		    }
		    rs.close();
		    st.close();
		     
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			if(sql.equals("show tables"))
			list = select("select table_name from   INFORMATION_SCHEMA.tables");
			else
				e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	public List<List> selectforlist(String sql)
	{
		List<List> list = new ArrayList();
		try {
			Statement st =  this.getConn().createStatement();
		    ResultSet rs = st.executeQuery(sql);
		    ResultSetMetaData rsmd = rs.getMetaData();
           
		    while(rs.next())
		    {
		    	List<String> list2 = new ArrayList();
		    	int i = rsmd.getColumnCount();
		    	for(int j=1;j<=i;j++)
		    	{
		    		if(!rsmd.getColumnName(j).equals("ID"))
		    		{
		    			String str = rs.getString(j)==null?"": rs.getString(j);
		    			if(str.equals("null"))str = "";
		    			list2.add( str);
		    		}
		    		else
		    			list2.add(rs.getString(j));
		    	}
		    	list.add(list2);
		    }
		    rs.close();
		    st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public void close()
	{
		 
	}
	
	 
	
	
	/**
	 * 执行一条查询sql,以 List<hashmap> 的形式返回查询的记录，记录条数，和从第几条开始，由参数决定，主要用于翻页
	 * pageno 页码  rowsize 每页的条数
	 */
	public List<HashMap> select(String sql, int pageno, int rowsize) {
		List<HashMap> list=new ArrayList<HashMap>();
		List<HashMap> mlist=new ArrayList<HashMap>();
		try{
			list=this.select(sql);
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
	 
	 
	public List<HashMap> selectByParam(HttpServletRequest request, HashMap<String, String> param)
	{ 
		initSqlSession(request);
		HashMap<String, String> param2 = new HashMap<String, String>();
		if(param.get("orderby")==null)param.put("orderby", "id desc");
		if(param.get("cols")==null)param.put("cols", "*");
		String mapname = param.get("tablename").split(" ")[0].trim();
		mapname = mapname.substring(0,1).toUpperCase()+mapname.substring(1).toLowerCase();
		for(Entry<String,String> entry : param.entrySet())
		{
			String key = entry.getKey().toLowerCase();
			String value = ""+entry.getValue()+"";
			if(key.equals("id")||key.equals("tablename")||key.equals("orderby")||key.equals("param")||key.equals("cols"))
			{
				param2.put(key, entry.getValue());
			}else{
				param2.put(key, value);
			}
			
		}
		List<HashMap> list = sqlsession.selectList("entity.mapper."+mapname+"Mapper.findByParam",param2);  
		List<HashMap> returnlist = new ArrayList<HashMap>();
		for(HashMap<String,String> map:list)
		{ 
			HashMap rmap = new HashMap();
			for(Entry entry : map.entrySet())
			{
				String key = entry.getKey().toString().toLowerCase();
				if(key.equals("id"))
				rmap.put(key, entry.getValue()+"");
				else 
				rmap.put(key, entry.getValue()==null?"":(String)entry.getValue());
			}
			returnlist.add(rmap);
		} 
		return list;
	}
	
	
	/**
	 * 执行一条查询sql,以 List<hashmap> 的形式返回查询的记录，记录条数，和从第几条开始，由参数决定，主要用于翻页
	 * pageno 页码  rowsize 每页的条数
	 */
	public List<HashMap> selectByParam(HttpServletRequest request, HashMap<String, String> param, int pageno, int rowsize) {
		List<HashMap> list=new ArrayList<HashMap>();
		List<HashMap> mlist=new ArrayList<HashMap>();
		try{
			list=this.selectByParam(request,param);
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
	
	public HashMap dyTform(HashMap map)
	{  
		String rtab = "";
		String frgroupno = "";
		for(Object obj : map.entrySet())
		{
			Entry entry = (Entry)obj; 
			if(entry.getValue()!=null&&entry.getValue().toString().startsWith("dytab-"))
			{
			    rtab = entry.getValue().toString().replaceAll("dytab-", "");
			    frgroupno = map.get("frgroupno").toString();
				break;
			}
		}
		if(!"".equals(rtab)&&!"".equals(frgroupno)){
		String ts = rtab.substring(0,1).toUpperCase()+rtab.substring(1);
		String cols = getCols(rtab);
		HashMap<String, String> dmap = new HashMap<String, String>();
		dmap.put("frgroupno", ""+frgroupno+"");
		dmap.put("cols", "*");
		dmap.put("tablename", rtab);
		dmap.put("orderby", "id");
		List<HashMap> list = sqlsession.selectList("entity.mapper."+ts+"Mapper.findByParam",dmap); 
		for(int i=0;i<list.size();i++)
		{ 
			int x = i+1;
			HashMap m = list.get(i);
			for(String str:cols.split(","))
			{ 
				if(str.equals("id"))continue;
				map.put(str+x, m.get(str));
			}
		} 
		map.put("dytabrows", list.size());
		map.put("dytabtab", rtab);
		} 
		return map;
	}
	

 	//根据uname取tname start
	public List<HashMap> getTname(List<HashMap> list){
		List<HashMap> list2 = list;
		list = new ArrayList<HashMap>();
		for(HashMap map:list2){
			HashMap map2 = new HashMap();
			java.util.Set<Map.Entry<String,Object>> set = map.entrySet();
			for(Map.Entry<String,Object> entry:set){
				if(entry.getKey().endsWith("uname")) {
                   List<HashMap> userlist = select("select tname from sysuser where uname='"+entry.getValue()+"'");
                   if(userlist.size()>0){
					   map2.put(entry.getKey()+"_tname",userlist.get(0).get("tname"));
				   }else{
					   map2.put(entry.getKey()+"_tname","");
				   }
				}
				map2.put(entry.getKey(),entry.getValue());
			}
			list.add(map2);
		}
		return list;
	}
	//根据uname取tname end
	public void insertDYTable(String tablename,String frgroupno,String dytabdelrows,HttpServletRequest request)
	{  
		initSqlSession(request);
		int grtable_crows = Integer.parseInt(request.getParameter("grtable_crows")) ;
		String table_rela_cols = request.getParameter("table_rela_cols")==null?"":request.getParameter("table_rela_cols");
		HashMap<String, String> table_rela_cols_map = new HashMap<String, String>();
		if(table_rela_cols.contains(",")){
		for(String str:table_rela_cols.split(","))
		{
			table_rela_cols_map.put(str.split("-")[0], str.split("-")[1]+"-"+str.split("-")[2]);
		}}
		String cols = getCols(tablename);
		HashMap<String, String> dmap = new HashMap();
		dmap.put("frgroupno", frgroupno); 
		String ts = tablename.substring(0,1).toUpperCase()+tablename.substring(1); 
		sqlsession.delete("entity.mapper."+ts+"Mapper.deleteByParam",dmap);
		for(int i=1;i<=grtable_crows;i++)
		{
			boolean hasvalue = false;
			for(String str:cols.split(","))
			{
				str = str.toLowerCase();
				if(str.equals("id"))continue;
				if(dytabdelrows.contains("_"+i+"_"))continue; 
				String colvalue = request.getParameter(str+i)==null?"":request.getParameter(str+i);
				if(!"".equals(colvalue))
				{
					hasvalue = true;
					break;
				}  
			} 
			if(hasvalue){
			HashMap<String, String> insertmap = new HashMap();
			insertmap.put("frgroupno", frgroupno); 
			
			for(String str:cols.split(","))
			{
				str = str.toLowerCase();
				if(str.equals("id"))continue;
				if(str.equals("frgroupno"))continue;
				if(!str.equals("savetime")){ 
				if(table_rela_cols_map.containsKey(str.replaceAll("key", ""))&&str.endsWith("key"))
				{
					String gl = table_rela_cols_map.get(str.replaceAll("key", ""));
					String values = request.getParameter(str.replaceAll("key", "")+i)==null?"":request.getParameter(str.replaceAll("key", "")+i);
					String param = str.replaceAll("key", "")+i;
					if(request.getParameterValues(param)!=null&&request.getParameterValues(param).length>1)
					{
						values = getCheckBoxValues(request, param);
					}
					String id = getID( tablename, str, gl.split("-")[0], gl.split("-")[1], values);
					insertmap.put(str, id);   
				}else{ 
				String values = request.getParameter(str+i)==null?"":request.getParameter(str+i);
				String param = str+i;
				if(request.getParameterValues(param)!=null&&request.getParameterValues(param).length>1)
				{
					values = getCheckBoxValues(request, param);
				}
				insertmap.put(str, values); 
				}
				}
				else
				insertmap.put("savetime", Info.getDateStr()); 
			} 
			System.out.println("----------"+insertmap);
			sqlsession.insert("entity.mapper."+ts+"Mapper.insert",insertmap);
			}
		}
	}
	
	
	public static void main(String[] args) { 
		String a = "sno~hnoname;sname~hnoname;tuname~负责人";
		System.out.println(a.split(";")[0]);
	}
}
