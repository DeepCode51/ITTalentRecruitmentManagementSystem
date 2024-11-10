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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.lang.ArrayUtils;
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
import util.*;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

@Controller
@RequestMapping("/itivtmgr")
public class SysController {

	String date = Info.getDateStr();
	CommDAO cdao = new CommDAO();
	CommDAO dao = new CommDAO();
	public static String uname = ""; // 写在前面

	//ssmservice
     @Autowired 
     public ChargesService chargesService; 
     @Autowired 
     public ExamdtlService examdtlService; 
     @Autowired 
     public ExamsService examsService; 
     @Autowired 
     public JobapplyService jobapplyService; 
     @Autowired 
     public MessagesService messagesService; 
     @Autowired 
     public MixinfoService mixinfoService; 
     @Autowired 
     public OffersService offersService; 
     @Autowired 
     public PinlunService pinlunService; 
     @Autowired 
     public PostypeService postypeService; 
     @Autowired 
     public RecruitingService recruitingService; 
     @Autowired 
     public RepbussService repbussService; 
     @Autowired 
     public SmailService smailService; 
     @Autowired 
     public SystemlogService systemlogService; 
     @Autowired 
     public SysuserService sysuserService; 
     @Autowired 
     public UcollectService ucollectService; 
     @Autowired 
     public ViewhistoryService viewhistoryService; 
     @Autowired 
     public ZchatsService zchatsService; 
     @Autowired 
     public ZresourcesService zresourcesService; 
     @Autowired 
     public ZrtypesService zrtypesService; 
     @Autowired 
     public ZtypeService ztypeService; 
//ssmservice

	// login.jsp请求处理开始
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		String pagerandom = request.getParameter("pagerandom") == null ? "" : request.getParameter("pagerandom");
		String random = (String) request.getSession().getAttribute("random");
		if (!pagerandom.equals(random) && request.getParameter("a") != null) {
			request.setAttribute("random", "");
			go("/admin/login.jsp", request, response);
		} else {
			String username = request.getParameter("uname");
			String password = request.getParameter("upass");
			String utype = request.getParameter("utype");
			request.getSession().setAttribute("utype", utype);
			HashMap<String, String> pmap = new HashMap<String, String>();
			pmap.put("tablename", "sysuser  ");
			pmap.put("uname", username);
			pmap.put("upass", password);
			pmap.put("utype", utype);
			pmap.put("status", "正常");
			pmap.put("orderby", " id desc  ");
			List<HashMap> list = cdao.selectByParam(request, pmap);
			if (list.size() == 1) {
				HashMap map = list.get(0);
				List<HashMap> ulist = cdao.selectByParam(request, pmap);
				if (ulist.size() == 1 && password.equals(map.get("upass").toString())) {
					request.getSession().setAttribute("admin", map);
					this.uname = username;// 登录成功后
					gor("/itivtmgr/itivtmgr/index.do", request, response);
				} else {
					request.setAttribute("error", "");
					go("/admin/login.jsp", request, response);
				}
			} else {
				request.setAttribute("error", "");
				go("/admin/login.jsp", request, response);
			}
		}
		return null;
	}
	// login.jsp请求处理结束

	//sfotpass sysuser 请求初始化开始
	@RequestMapping("/sfotpass")
	public String sfotpass(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		String uname = selectmap.get("uname"); //取数据ID
		//根据uname取出用户的hashmap
		HashMap usermap = new HashMap();
		HashMap<String,String> parammap = new HashMap<String,String>();
		parammap.put("uname",uname);
		List userlist = sysuserService.findByParam(parammap);
		if(CollectionUtils.isNotEmpty(userlist)){
		    usermap = (HashMap) userlist.get(0);
		}
		String userid = usermap.get("id").toString();//用户ID
		modelMap.put("usermap", usermap); //将数据实体类发送到前端
		return "/fotpass.jsp";
	}
	//sfotpass sysuser 请求初始化结束

      //szxjlcx.jsp szxjlcx 请求处理开始
      @RequestMapping("/szxjlcx")
      public String szxjlcx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
        HashMap smap = null;
        if(selectmap.get("id").startsWith("20000")){
			smap = chargesService.findByID(selectmap.get("id").replaceAll("20000","")); //取数据实体类
			smap.put("uname",smap.get("uname"));
			smap.put("touname","admin");
		}else {
			smap = zchatsService.findByID(selectmap.get("id")); //取数据实体类
		}
    	modelMap.put("id",selectmap.get("id"));
    	String uname = Info.getUser(request).get("uname").toString();
    	modelMap.put("uname",uname);
    	if(smap.get("uname").equals(uname))
    	    modelMap.put("touname",smap.get("touname"));
    	else
    		modelMap.put("touname",uname);
      return "/admin/szxjlcx.jsp";
      }
      //szxjlcx.jsp szxjlcx 请求处理结束

	//uppass.jsp 请求处理开始
	@RequestMapping("/uppass")
	public String uppass(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
	    String upass = request.getParameter("upass");
		HashMap user = dao.getmap(Info.getUser(request).get("id").toString(), "sysuser");
		String id = (String)user.get("id");
		HashMap<String, String> usermap = sysuserService.findByID(id);
		usermap.put("upass", upass);
		sysuserService.update(usermap);
		request.setAttribute("suc", "");
		return "/admin/uppass.jsp";
	}
	//uppass.jsp 请求处理结束

 @RequestMapping("/jcxx")
  public String jcxx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
   String id = selectmap.get("id").toString(); //取数据ID
  HashMap smap = mixinfoService.findByID(id); //取数据实体类
  modelMap.put("sdata", smap); //将数据实体类发送到前端
  modelMap.put("id", id); //将数据ID发送到前端
  return "/jcxx.jsp";
  }
  //jcxx mixinfo 请求初始化结束

	// sipapply 申请请求处理开始
    @RequestMapping("/sipapply")
    public String sipapply(@RequestParam HashMap<String, String> insertmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
    String table = insertmap.get("applytable");//申请表的表名
    String[] cols = insertmap.get("applytablecols").split(",");//申请表的字段
    List colslist = Arrays.asList(cols);//将字段数组转为list，不然做不了判断
    String fshstatus = insertmap.get("fshstatus");//申请表的表名
    String url = insertmap.get("url");//申请界面URL，申请完了要返回云
    if(StringUtils.isNotEmpty(fshstatus)&&fshstatus.contains("-")){
    	fshstatus = fshstatus.split("-")[0];
  	  insertmap.put("fshstatus", fshstatus);//设置初始状态
    }
    insertmap.put("uname", Info.getUser(request).get("uname").toString());
    HashMap<String, String> imap = new HashMap<String, String>();//insert用的hashmap，因为insertmap有多余数据，mybatis会报错
    for(Entry<String, String> entry:insertmap.entrySet()){
  	  if(colslist.contains(entry.getKey())) {
  		  imap.put(entry.getKey(), entry.getValue());
  	  }
    }
    //取session中的信息，存入申请 表
    for(Entry<String, String> entry:((HashMap<String, String>)Info.getUser(request)).entrySet()){
    	if(entry.getKey().equals("id"))continue;
    	if(entry.getKey().equals("status"))continue;
    	if(colslist.contains(entry.getKey()))
		imap.put(entry.getKey(), entry.getValue());
    }
    request.getSession().setAttribute("suc", "suc");
		imap.put("savetime", Info.getDateStr());
    sysuserService.commInsert(table, imap);
    return "forward:"+url;
    }
    // sipapply 申请请求处理结束

	      // index.jsp请求处理开始
		@RequestMapping("/index")
		public String index(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
			 if(StringUtils.equals("zx", selectmap.get("zx"))) { //用户点击右上角的注销按钮，注销session
				 session.invalidate();
			 }

			 //取资讯类别
			 HashMap<String, String> smap = new HashMap<String, String>();
			 List<HashMap<String, String>> list1 = zrtypesService.findByParam(smap);
			 modelMap.put("typelist1", list1);

			 //取资讯
			 int x = 1;
			 for(HashMap m:list1) {
				 smap = new HashMap<String, String>();
				 smap.put("btype", m.get("tname").toString());
				 smap.put("orderby", "id desc");
				 List list2 = zresourcesService.findByParam(smap);
				 modelMap.put("list"+x+"1", Info.subList(list2,1,3));
				 modelMap.put("list"+x+"2", Info.subList(list2,4,6));
				 x++;
			 }

			//取最新企业
			smap = new HashMap<String, String>();
			smap.put("orderby", "id desc");
			smap.put("utype", "企业");
			List listqy = sysuserService.findByParam(smap);
			modelMap.put("listqy1", Info.subList(listqy,1,2));
			modelMap.put("listqy2", Info.subList(listqy,3,4));
			modelMap.put("listqy3", Info.subList(listqy,5,6));

			//取热门企业
			smap = new HashMap<String, String>();
			smap.put("orderby", "clicknums+0 desc");
			List listrmqy = sysuserService.findByParam(smap);
			modelMap.put("listrmqy", Info.subList(listrmqy,1,3));

			return "/index.jsp";
		}
		// index.jsp请求处理结束

		 // top.jsp请求处理开始
		@RequestMapping("/top")
		public String top(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

			 //取基础信息
			 HashMap<String, String> smap = new HashMap<String, String>();
			 smap.put("remo", "单");
			 List<HashMap<String, String>> jclist = mixinfoService.findByParam(smap);
			 for(HashMap<String, String> m:jclist) {
				 if("16".equals(String.valueOf(m.get("id")))) {
			        modelMap.put("gzgg", m); //滚动公告数据发送到前端
				 }
			 }

			 //取当前位置
			 String cur = selectmap.get("cur");
			 if(cur==null) {
				 cur = session.getAttribute("cur")==null?null:session.getAttribute("cur").toString();
			 }
			 if(cur==null) {
				 cur = "1";
			 }
			 session.setAttribute("cur", cur);

			 //取滚动图片
			 smap = new HashMap<String, String>();
			 smap.put("infotype", "滚动图片");
			 smap.put("orderby", "id");
			 List<HashMap<String, String>> gdlist = mixinfoService.findByParam(smap);
			 modelMap.put("gdlist", gdlist); //滚动图片数据发送到前端

//			 //取心理评测
//			 smap = new HashMap<String, String>();
//			 smap.put("orderby", "id desc");
//			 List<HashMap<String, String>> pclist = examsService.findByParam(smap);
//			 pclist = pclist.subList(0, 6);
//			 modelMap.put("pclist", pclist); //滚动图片数据发送到前端

			return "/top.jsp";
		}
		// top.jsp请求处理结束

		//excel导出请求处理开始
	    @RequestMapping("/excel")
	    public String excel(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		HashMap  cuser = Info.getUser(request);
		for(java.util.Map.Entry<String, String> entry:((HashMap<String, String>)Info.getUser(request)).entrySet()){
			if(entry.getKey().equals("id"))continue;
			if(entry.getKey().equals("status"))continue;
			selectmap.put("sysuser_"+entry.getKey(), entry.getValue());
		}
	    String filename = new PageManager("excel",0,request).getDataList(selectmap.get("table"), selectmap.get("method"), selectmap,request);
	    StringBuffer sb = new StringBuffer(50);
		response.setContentType("application/x-msdownload;charset=GB2312");
        response.setHeader("Content-Disposition", new String(sb.toString().getBytes(), "ISO8859-1"));
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
		   filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
		}
		else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
	        filename = URLEncoder.encode(filename, "UTF-8");
	      }
		 response.setContentType("text/plain");
		 response.setHeader("Location",filename);
		 response.reset();
		 response.setHeader("Cache-Control", "max-age=0" );
		 response.setHeader("Content-Disposition", "attachment; filename=" + filename);
		    try {
		        BufferedInputStream bis = null;
			    BufferedOutputStream bos = null;
			    OutputStream fos = null;
			    bis = new BufferedInputStream((InputStream)new FileInputStream(request.getRealPath("/upfile/")+"/"+filename));
			    fos = response.getOutputStream();
			    bos = new BufferedOutputStream(fos);
			    int bytesRead = 0;
			    byte[] buffer = new byte[5 * 1024];
			    while ((bytesRead = bis.read(buffer)) != -1) {
			     bos.write(buffer, 0, bytesRead);
			    }
			    bos.close();
			    bis.close();
			    fos.close();
		     } catch (Exception e) {
			}
	    return null;
	    }
	    //excel导出请求处理结束

	   //download导出请求处理开始
	    @RequestMapping("/download")
	    public String download(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
	    	 String filename = selectmap.get("filename");
	 	    StringBuffer sb = new StringBuffer(50);
	 		response.setContentType("application/x-msdownload;charset=GB2312");
	         response.setHeader("Content-Disposition", new String(sb.toString().getBytes(), "ISO8859-1"));
	 		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
	 		   filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
	 		}
	 		else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
	 	        filename = URLEncoder.encode(filename, "UTF-8");
	 	      }
	 		 response.setContentType("text/plain");
	 		 response.setHeader("Location",filename);
	 		 response.reset();
	 		 response.setHeader("Cache-Control", "max-age=0" );
	 		 response.setHeader("Content-Disposition", "attachment; filename=" + filename);
	 		    try {
	 		        BufferedInputStream bis = null;
	 			    BufferedOutputStream bos = null;
	 			    OutputStream fos = null;
	 			    bis = new BufferedInputStream((InputStream)new FileInputStream(request.getRealPath("/upfile/")+"/"+filename));
	 			    fos = response.getOutputStream();
	 			    bos = new BufferedOutputStream(fos);
	 			    int bytesRead = 0;
	 			    byte[] buffer = new byte[5 * 1024];
	 			    while ((bytesRead = bis.read(buffer)) != -1) {
	 			     bos.write(buffer, 0, bytesRead);
	 			    }
	 			    bos.close();
	 			    bis.close();
	 			    fos.close();
	 		     } catch (Exception e) {
	 			}
	 	    return null;
	    }
	    //download导出请求处理结束

   //处理查询条件开始
     public void clearSelectMap(HashMap<String, String> selectmap,HttpSession session)throws Exception{
     if(session.getAttribute("suc")!=null)selectmap.clear() ;  //如果是从新增或修改界面过来，要清空查询条件
     if(session.getAttribute("updatesuc")!=null){ //如果是从单字段修改功能过来，要清空查询条件
     selectmap.clear() ;
     session.removeAttribute("updatesuc") ;
     }
     }
     //处理查询条件结束

	// guestbook.jsp请求处理开始
	@RequestMapping("/guestbook")
	public String guestbook(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		selectmap.clear();
		String url = Info.getPageUrl("/hipbhop/guestbook",selectmap) ;  //得取分页地址
		List<HashMap> list = messagesService.findByParamWithPages("messagescx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
		modelMap.put("slist", list); //列表发送前端
		return "/guestbook.jsp";
	}
	// guestbook.jsp请求处理结束

	// guestbookopr.jsp请求处理开始
	@RequestMapping("/guestbookopr")
	public String guestbookopr(@RequestParam HashMap<String, String> insertmap,ModelMap modelMap,HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		insertmap.put("filename", Info.getUser(request).get("filename").toString());
		insertmap.put("uname", Info.getUser(request).get("uname").toString());
		insertmap.put("savetime", Info.getDateStr());
		messagesService.insert(insertmap);
		return "/itivtmgr/guestbook.do";
	}
	// guestbookopr.jsp请求处理结束

	//toadminindex sysuser 请求初始化开始
	@RequestMapping("/toadminindex")
	public String toadminindex(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		return "/admin/index.jsp";
	}
	//toadminindex sysuser 请求初始化结束

	//toadmintop sysuser 请求初始化开始
	@RequestMapping("/toadmintop")
	public String toadmintop(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		return "/admin/top.jsp";
	}
	//toadmintop sysuser 请求初始化结束

	//toadminfoot sysuser 请求初始化开始
	@RequestMapping("/toadminfoot")
	public String toadminfoot(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		return "/admin/foot.jsp";
	}
	//toadminfoot sysuser 请求初始化结束

	//siteinfo mixinfo 请求初始化开始
	@RequestMapping("/siteinfo")
	public String siteinfo(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		String url = Info.getPageUrl("",selectmap) ;  //得取分页地址
		List<HashMap> list = mixinfoService.findByParamWithPages("zmixinfocx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
		for(HashMap m:list) {
			modelMap.put("siteinfo", m); //列表发送前端
		}
		return "/siteinfo.jsp";
	}
	//siteinfo mixinfo 请求初始化结束

	// pinlun 评论请求处理开始
	@RequestMapping("/pinluns")
	public String pinlun(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		String table = selectmap.get("table");//被评论的表名
		modelMap.put("table", table);
		String id = selectmap.get("id");//被评论表的ID
		modelMap.put("id", id);
		String title = selectmap.get("title");//被评论表的标题
		modelMap.put("title", title);
		String furl = selectmap.get("url");//分页URL
		modelMap.put("url", furl);
		String pf = selectmap.get("pf");//是否评分
		modelMap.put("pf", pf);

		HashMap<String, String> smap = new HashMap<String, String>();//查询评论列表条件
		smap.put("pid", selectmap.get("id"));//设置被评论ID
		smap.put("id", selectmap.get("id"));//设置被评论ID
		smap.put("table", selectmap.get("table"));//设置被评论表名
		String url = Info.getPageUrl(selectmap.get("url"),smap) ;  //得取分页地址
		List<HashMap> list = pinlunService.findByParamWithPages("pinlun", smap, 10, url, request); //分页查询数据
		modelMap.put("slist", list); //列表发送前端

		if(Info.getUser(request)!=null) {//如果用户登录了就要查询本人是否已评论
			HashMap<String,String> pinlunMap = new HashMap<String,String>() ;//查询本人评论，设置查询条件
			pinlunMap.put("pid",id) ;//设置信息ID
			pinlunMap.put("tablename",table) ;//设置信息表名
			pinlunMap.put("orderby","id desc") ;//设置排序方式
			pinlunMap.put("saver",Info.getUser(request).get("uname").toString()) ;//设置本人用户名
			pinlunMap.put("cols","id") ;//设置查询字段
			int pinlunNum = pinlunService.findByParam(pinlunMap).size() ;//调用评论服务，查询本人评论数量
			modelMap.put("pinlunNum", pinlunNum); //本人评论数量发送前端
		}

		modelMap.put("user", Info.getUser(request)); //用户信息发送到前端
		return "/pinlun.jsp";
	}
	// pinlun 评论请求处理结束

	// pinlunsub 评论请求处理开始
	@RequestMapping("/pinlunsub")
	public String pinlunsub(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		String table = selectmap.get("table");//被评论的表名
		modelMap.put("table", table);
		String uname = Info.getUser(request).get("uname").toString();//评论人
		modelMap.put("uname", uname);
		String id = selectmap.get("id");//被评论表的ID
		modelMap.put("id", id);
		String title = selectmap.get("title");//被评论表的标题
		modelMap.put("title", title);
		String furl = selectmap.get("url");//分页URL
		modelMap.put("url", furl);

		if(Info.getUser(request)!=null) {//如果用户登录了就要查询本人是否已评论
			HashMap<String,String> pinlunMap = new HashMap<String,String>() ;//查询本人评论，设置查询条件
			pinlunMap.put("pid",id) ;//设置信息ID
			pinlunMap.put("tablename",table) ;//设置信息表名
			pinlunMap.put("orderby","id desc") ;//设置排序方式
			pinlunMap.put("saver",Info.getUser(request).get("uname").toString()) ;//设置本人用户名
			pinlunMap.put("cols","id") ;//设置查询字段
			int pinlunNum = pinlunService.findByParam(pinlunMap).size() ;//调用评论服务，查询本人评论数量
			modelMap.put("pinlunNum", pinlunNum); //本人评论数量发送前端
			if(StringUtils.isNotEmpty(selectmap.get("content"))) { //如果提交了评论就添加评论记录
				HashMap<String, String> imap = new HashMap<String, String>();
				imap.put("pid", id);
				imap.put("saver", Info.getUser(request).get("uname").toString());
				imap.put("infotitle", title);
				imap.put("content", selectmap.get("content"));
				imap.put("tablename", table);
				imap.put("savetime", Info.getDateStr());
				if(pinlunNum==0) {
					imap.put("pf", selectmap.get("pf"));
				}
				pinlunService.insert(imap);
			}
		}
		modelMap.put("user", Info.getUser(request)); //用户信息发送到前端
		return furl;
	}
	// pinlunsub 评论请求处理结束

	//tofrtop sysuser 请求初始化开始
	@RequestMapping("/tofrtop")
	public String tofrtop(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		 //取滚动图片
		 HashMap<String, String> smap = new HashMap<String, String>();
		 smap.put("infotype", "滚动图片");
		 smap.put("orderby", "id");
		 List list1 = mixinfoService.findByParam(smap);
		 list1 = Info.subList(list1,1,4);
		 modelMap.put("list1", list1); //法律法规数据发送到前端

		//取滚动图片
		smap = new HashMap<String, String>();
		smap.put("tglparentid", "-1");
		smap.put("orderby", "id");
		List listtype = postypeService.findByParam(smap);
		modelMap.put("listtype", listtype); //法律法规数据发送到前端

		 return "/top.jsp";
	}
	//tofrtop sysuser 请求初始化结束

	//tofrfoot sysuser 请求初始化开始
	@RequestMapping("/tofrfoot")
	public String tofrfoot(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		return "/foot.jsp";
	}
	//tofrfoot sysuser 请求初始化结束

	//touppass sysuser 请求初始化开始
	@RequestMapping("/touppass")
	public String touppass(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		return "/admin/uppass.jsp";
	}
	//touppass sysuser 请求初始化结束

	//tologin.jsp sysuser 请求初始化开始
	@RequestMapping("/tologin")
	public String tologin(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		return "/admin/login.jsp";
	}
	//tologin sysuser 请求初始化结束

	//tomain.jsp sysuser 请求初始化开始
	@RequestMapping("/tomain")
	public String tomain(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		return "/admin/main.jsp";
	}
	//tomain sysuser 请求初始化结束

	//tomain2.jsp sysuser 请求初始化开始
	@RequestMapping("/tomain2")
	public String tomain2(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		return "/admin/main2.jsp";
	}
	//tomain2 sysuser 请求初始化结束

	//tomain3.jsp sysuser 请求初始化开始
	@RequestMapping("/tomain3")
	public String tomain3(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		return "/admin/main3.jsp";
	}
	//tomain3 sysuser 请求初始化结束

      //toyhsysuserregedit sysuser 请求初始化开始
      @RequestMapping("/toyhsysuserregedit")
      public String toyhsysuserregedit(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      return "/admin/yhsysuserregedit.jsp";
      }
      //toyhsysuserregedit sysuser 请求初始化结束

      //yhsysuserregedit.jsp sysuser 请求处理开始
      @RequestMapping("/yhsysuserregedit")
      public String yhsysuserregedit(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("管理员信息管理，用户注册",Info.getUser(request)); //记操作日志
      String utype = "用户";
      String status = "正常";
      insertmap.put("savetime",Info.getDateStr());
      insertmap.put("utype",utype);
      insertmap.put("status",status);
      sysuserService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/sysuser/toyhsysuserregedit.do";
      }
      //yhsysuserregedit.jsp sysuser 请求处理结束

     //toperyhsysuserxg sysuser 请求初始化开始
     @RequestMapping("/toperyhsysuserxg")
     public String toperyhsysuserxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = Info.getUser(request).get("id").toString(); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/peryhsysuserxg.jsp";
     }
     //toperyhsysuserxg sysuser 请求初始化结束

     //peryhsysuserxg.jsp sysuser 请求处理开始
     @RequestMapping("/peryhsysuserxg")
     public String peryhsysuserxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("管理员信息管理，修改个人信息",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     //String status = updatemap.get("status").toString(); //取修改后的状态
     sysuserService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/sysuser/toperyhsysuserxg.do";
     }
     //peryhsysuserxg.jsp sysuser 请求处理结束

      //toyhsysusertj sysuser 请求初始化开始
      @RequestMapping("/toyhsysusertj")
      public String toyhsysusertj(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      return "/admin/yhsysusertj.jsp";
      }
      //toyhsysusertj sysuser 请求初始化结束

      //yhsysusertj.jsp sysuser 请求处理开始
      @RequestMapping("/yhsysusertj")
      public String yhsysusertj(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("用户信息管理，信息添加",Info.getUser(request)); //记操作日志
      String utype = "用户";
      String status = "正常";
      insertmap.put("savetime",Info.getDateStr());
      insertmap.put("utype",utype);
      insertmap.put("status",status);
      sysuserService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/sysuser/yhsysusercx.do";
      }
      //yhsysusertj.jsp sysuser 请求处理结束

     //toyhsysuserxg sysuser 请求初始化开始
     @RequestMapping("/toyhsysuserxg")
     public String toyhsysuserxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/yhsysuserxg.jsp";
     }
     //toyhsysuserxg sysuser 请求初始化结束

     //yhsysuserxg.jsp sysuser 请求处理开始
     @RequestMapping("/yhsysuserxg")
     public String yhsysuserxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("用户信息管理，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     //String status = updatemap.get("status").toString(); //取修改后的状态
     sysuserService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/sysuser/yhsysusercx.do";
     }
     //yhsysuserxg.jsp sysuser 请求处理结束

      //yhsysusercx.jsp sysuser 请求处理开始
      @RequestMapping("/yhsysusercx")
      public String yhsysusercx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/sysuser/yhsysusercx",selectmap) ;  //得取分页地址
      List<HashMap> list = sysuserService.findByParamWithPages("yhsysusercx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/yhsysusercx.jsp";
      }
      //yhsysusercx.jsp sysuser 请求处理结束

      //tosysusertj sysuser 请求初始化开始
      @RequestMapping("/tosysusertj")
      public String tosysusertj(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      return "/admin/sysusertj.jsp";
      }
      //tosysusertj sysuser 请求初始化结束

      //sysusertj.jsp sysuser 请求处理开始
      @RequestMapping("/sysusertj")
      public String sysusertj(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("，信息添加",Info.getUser(request)); //记操作日志
      String utype = "用户";
      String status = "正常";
      insertmap.put("savetime",Info.getDateStr());
      insertmap.put("utype",utype);
      insertmap.put("status",status);
      sysuserService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/sysuser/sysusercx.do";
      }
      //sysusertj.jsp sysuser 请求处理结束

     //tosysuserxg sysuser 请求初始化开始
     @RequestMapping("/tosysuserxg")
     public String tosysuserxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/sysuserxg.jsp";
     }
     //tosysuserxg sysuser 请求初始化结束

     //sysuserxg.jsp sysuser 请求处理开始
     @RequestMapping("/sysuserxg")
     public String sysuserxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     //String status = updatemap.get("status").toString(); //取修改后的状态
     sysuserService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/sysuser/sysusercx.do";
     }
     //sysuserxg.jsp sysuser 请求处理结束

      //sysusercx.jsp sysuser 请求处理开始
      @RequestMapping("/sysusercx")
      public String sysusercx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/sysuser/sysusercx",selectmap) ;  //得取分页地址
      List<HashMap> list = sysuserService.findByParamWithPages("sysusercx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/sysusercx.jsp";
      }
      //sysusercx.jsp sysuser 请求处理结束

     //toperglysysuserxg sysuser 请求初始化开始
     @RequestMapping("/toperglysysuserxg")
     public String toperglysysuserxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = Info.getUser(request).get("id").toString(); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/perglysysuserxg.jsp";
     }
     //toperglysysuserxg sysuser 请求初始化结束

     //perglysysuserxg.jsp sysuser 请求处理开始
     @RequestMapping("/perglysysuserxg")
     public String perglysysuserxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("会员信息管理，修改个人信息",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     //String status = updatemap.get("status").toString(); //取修改后的状态
     sysuserService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/sysuser/toperglysysuserxg.do";
     }
     //perglysysuserxg.jsp sysuser 请求处理结束

      //toglysysusertj sysuser 请求初始化开始
      @RequestMapping("/toglysysusertj")
      public String toglysysusertj(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      return "/admin/glysysusertj.jsp";
      }
      //toglysysusertj sysuser 请求初始化结束

      //glysysusertj.jsp sysuser 请求处理开始
      @RequestMapping("/glysysusertj")
      public String glysysusertj(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("管理员信息管理，信息添加",Info.getUser(request)); //记操作日志
      String utype = "管理员";
      String status = "正常";
      insertmap.put("savetime",Info.getDateStr());
      insertmap.put("utype",utype);
      insertmap.put("status",status);
      sysuserService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/sysuser/glysysusercx.do";
      }
      //glysysusertj.jsp sysuser 请求处理结束

     //toglysysuserxg sysuser 请求初始化开始
     @RequestMapping("/toglysysuserxg")
     public String toglysysuserxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/glysysuserxg.jsp";
     }
     //toglysysuserxg sysuser 请求初始化结束

     //glysysuserxg.jsp sysuser 请求处理开始
     @RequestMapping("/glysysuserxg")
     public String glysysuserxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("管理员信息管理，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     //String status = updatemap.get("status").toString(); //取修改后的状态
     sysuserService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/sysuser/glysysusercx.do";
     }
     //glysysuserxg.jsp sysuser 请求处理结束

      //glysysusercx.jsp sysuser 请求处理开始
      @RequestMapping("/glysysusercx")
      public String glysysusercx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/sysuser/glysysusercx",selectmap) ;  //得取分页地址
      List<HashMap> list = sysuserService.findByParamWithPages("glysysusercx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/glysysusercx.jsp";
      }
      //glysysusercx.jsp sysuser 请求处理结束

      //toqysysuserregedit sysuser 请求初始化开始
      @RequestMapping("/toqysysuserregedit")
      public String toqysysuserregedit(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      return "/qysysuserregedit.jsp";
      }
      //toqysysuserregedit sysuser 请求初始化结束

      //qysysuserregedit.jsp sysuser 请求处理开始
      @RequestMapping("/qysysuserregedit")
      public String qysysuserregedit(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("管理员信息管理，企业注册",Info.getUser(request)); //记操作日志
      String utype = "企业";
      String status = "正常";
      insertmap.put("savetime",Info.getDateStr());
      insertmap.put("utype",utype);
      insertmap.put("status",status);
      sysuserService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/sysuser/toqysysuserregedit.do";
      }
      //qysysuserregedit.jsp sysuser 请求处理结束

     //toperqysysuserxg sysuser 请求初始化开始
     @RequestMapping("/toperqysysuserxg")
     public String toperqysysuserxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = Info.getUser(request).get("id").toString(); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/perqysysuserxg.jsp";
     }
     //toperqysysuserxg sysuser 请求初始化结束

     //perqysysuserxg.jsp sysuser 请求处理开始
     @RequestMapping("/perqysysuserxg")
     public String perqysysuserxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("管理员信息管理，修改个人信息",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     //String status = updatemap.get("status").toString(); //取修改后的状态
     sysuserService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/sysuser/toperqysysuserxg.do";
     }
     //perqysysuserxg.jsp sysuser 请求处理结束

      //toqysysusertj sysuser 请求初始化开始
      @RequestMapping("/toqysysusertj")
      public String toqysysusertj(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      return "/admin/qysysusertj.jsp";
      }
      //toqysysusertj sysuser 请求初始化结束

      //qysysusertj.jsp sysuser 请求处理开始
      @RequestMapping("/qysysusertj")
      public String qysysusertj(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("企业信息管理，信息添加",Info.getUser(request)); //记操作日志
      String utype = "企业";
      String status = "正常";
      insertmap.put("savetime",Info.getDateStr());
      insertmap.put("utype",utype);
      insertmap.put("status",status);
      sysuserService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/sysuser/qysysusercx.do";
      }
      //qysysusertj.jsp sysuser 请求处理结束

     //toqysysuserxg sysuser 请求初始化开始
     @RequestMapping("/toqysysuserxg")
     public String toqysysuserxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/qysysuserxg.jsp";
     }
     //toqysysuserxg sysuser 请求初始化结束

     //qysysuserxg.jsp sysuser 请求处理开始
     @RequestMapping("/qysysuserxg")
     public String qysysuserxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("企业信息管理，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     //String status = updatemap.get("status").toString(); //取修改后的状态
     sysuserService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/sysuser/qysysusercx.do";
     }
     //qysysuserxg.jsp sysuser 请求处理结束

      //qysysusercx.jsp sysuser 请求处理开始
      @RequestMapping("/qysysusercx")
      public String qysysusercx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/sysuser/qysysusercx",selectmap) ;  //得取分页地址
      List<HashMap> list = sysuserService.findByParamWithPages("qysysusercx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/qysysusercx.jsp";
      }
      //qysysusercx.jsp sysuser 请求处理结束

      //tohysysuserregedit sysuser 请求初始化开始
      @RequestMapping("/tohysysuserregedit")
      public String tohysysuserregedit(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      return "/hysysuserregedit.jsp";
      }
      //tohysysuserregedit sysuser 请求初始化结束

      //hysysuserregedit.jsp sysuser 请求处理开始
      @RequestMapping("/hysysuserregedit")
      public String hysysuserregedit(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("企业信息管理，会员注册",Info.getUser(request)); //记操作日志
      String utype = "会员";
      String status = "正常";
      insertmap.put("savetime",Info.getDateStr());
      insertmap.put("utype",utype);
      insertmap.put("status",status);
      sysuserService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/sysuser/tohysysuserregedit.do";
      }
      //hysysuserregedit.jsp sysuser 请求处理结束

     //perhysysuserxg.jsp sysuser 请求处理开始
     @RequestMapping("/perhysysuserxg")
     public String perhysysuserxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("企业信息管理，修改个人信息",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     //String status = updatemap.get("status").toString(); //取修改后的状态
     sysuserService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/sysuser/toperhysysuserxg.do";
     }
     //perhysysuserxg.jsp sysuser 请求处理结束

      //hysysusercx.jsp sysuser 请求处理开始
      @RequestMapping("/hysysusercx")
      public String hysysusercx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/sysuser/hysysusercx",selectmap) ;  //得取分页地址
      List<HashMap> list = sysuserService.findByParamWithPages("hysysusercx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/hysysusercx.jsp";
      }
      //hysysusercx.jsp sysuser 请求处理结束

      //tozresourcestj zresources 请求初始化开始
      @RequestMapping("/tozresourcestj")
      public String tozresourcestj(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      List btypeselectlist = zrtypesService.findByParamWithPages("findAll",selectmap,PageManager.DEFAULTPAGESIZE,"",request); //类别下拉框取数
      String btypeselect = Info.getselect("btype","zrtypes","tname~hnoname",btypeselectlist); //类别组装下拉框代码
      modelMap.put("btypeselect", btypeselect); //类别下拉框代码发送前端
      return "/admin/zresourcestj.jsp";
      }
      //tozresourcestj zresources 请求初始化结束

      //zresourcestj.jsp zresources 请求处理开始
      @RequestMapping("/zresourcestj")
      public String zresourcestj(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("站内资讯，信息添加",Info.getUser(request)); //记操作日志
      String content = request.getParameter("content")==null?"":request.getParameter("content");
      content = content.replaceAll("'"," ");
      String uname = " sysuser-uname ";
      insertmap.put("savetime",Info.getDateStr());
      insertmap.put("content",content);
      insertmap.put("uname",uname);
      zresourcesService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/zresources/zresourcescx.do";
      }
      //zresourcestj.jsp zresources 请求处理结束

     //tozresourcesxg zresources 请求初始化开始
     @RequestMapping("/tozresourcesxg")
     public String tozresourcesxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     List btypeselectlist = zrtypesService.findByParamWithPages("findAll",updatemap,PageManager.DEFAULTPAGESIZE,"",request); //取类别下拉框数据
     String btypeselect = Info.getselect("btype","zrtypes","tname~hnoname",btypeselectlist);//生成下拉框代码
     modelMap.put("btypeselect", btypeselect);//下拉框代码发往前端
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = zresourcesService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/zresourcesxg.jsp";
     }
     //tozresourcesxg zresources 请求初始化结束

     //zresourcesxg.jsp zresources 请求处理开始
     @RequestMapping("/zresourcesxg")
     public String zresourcesxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("站内资讯，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = zresourcesService.findByID(id); //取数据实体类
     String content = updatemap.get("content")==null?"":updatemap.get("content"); //取文本输入框数据
     content = content.replaceAll("'"," "); //文本输入框数据移除单引号，不然入库时会报错
     updatemap.put("content",content);//文本输入框数据设值
     zresourcesService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/zresources/zresourcescx.do";
     }
     //zresourcesxg.jsp zresources 请求处理结束

      //zresourcescx.jsp zresources 请求处理开始
      @RequestMapping("/zresourcescx")
      public String zresourcescx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      List btypeselectlist = zrtypesService.findByParamWithPages("findAll",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询类别下拉框的数据
      String btypeselect = Info.getselect("btype","zrtypes","tname",btypeselectlist);//组装类别下拉框select代码
      modelMap.put("btypeselect", btypeselect);//类别下拉框select代码发往前端
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/zresources/zresourcescx",selectmap) ;  //得取分页地址
      List<HashMap> list = zresourcesService.findByParamWithPages("zresourcescx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/zresourcescx.jsp";
      }
      //zresourcescx.jsp zresources 请求处理结束

      //tozrtypestj zrtypes 请求初始化开始
      @RequestMapping("/tozrtypestj")
      public String tozrtypestj(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      return "/admin/zrtypestj.jsp";
      }
      //tozrtypestj zrtypes 请求初始化结束

      //zrtypestj.jsp zrtypes 请求处理开始
      @RequestMapping("/zrtypestj")
      public String zrtypestj(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("信息类别管理，信息添加",Info.getUser(request)); //记操作日志
      zrtypesService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/zrtypes/zrtypescx.do";
      }
      //zrtypestj.jsp zrtypes 请求处理结束

     //tozrtypesxg zrtypes 请求初始化开始
     @RequestMapping("/tozrtypesxg")
     public String tozrtypesxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = zrtypesService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/zrtypesxg.jsp";
     }
     //tozrtypesxg zrtypes 请求初始化结束

     //zrtypesxg.jsp zrtypes 请求处理开始
     @RequestMapping("/zrtypesxg")
     public String zrtypesxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("信息类别管理，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = zrtypesService.findByID(id); //取数据实体类
     zrtypesService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/zrtypes/zrtypescx.do";
     }
     //zrtypesxg.jsp zrtypes 请求处理结束

      //zrtypescx.jsp zrtypes 请求处理开始
      @RequestMapping("/zrtypescx")
      public String zrtypescx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/zrtypes/zrtypescx",selectmap) ;  //得取分页地址
      List<HashMap> list = zrtypesService.findByParamWithPages("zrtypescx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/zrtypescx.jsp";
      }
      //zrtypescx.jsp zrtypes 请求处理结束

     //toznzxx zresources 请求初始化开始
     @RequestMapping("/toznzxx")
     public String toznzxx(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = zresourcesService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/znzxx.jsp";
     }
     //toznzxx zresources 请求初始化结束

     //znzxx.jsp zresources 请求处理开始
     @RequestMapping("/znzxx")
     public String znzxx(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("站内资讯，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = zresourcesService.findByID(id); //取数据实体类
     zresourcesService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/zresources/znzxs.do";
     }
     //znzxx.jsp zresources 请求处理结束

      //znzxs.jsp zresources 请求处理开始
      @RequestMapping("/znzxs")
      public String znzxs(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      List btypeselectlist = zrtypesService.findByParamWithPages("findAll",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询类别下拉框的数据
      String btypeselect = Info.getselect("btype","zrtypes","tname",btypeselectlist);//组装类别下拉框select代码
      modelMap.put("btypeselect", btypeselect);//类别下拉框select代码发往前端
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/zresources/znzxs",selectmap) ;  //得取分页地址
      List<HashMap> list = zresourcesService.findByParamWithPages("znzxs", selectmap, 6, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/znzxs.jsp";
      }
      //znzxs.jsp zresources 请求处理结束

      //gmixinfocx.jsp mixinfo 请求处理开始
      @RequestMapping("/gmixinfocx")
      public String gmixinfocx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/mixinfo/gmixinfocx",selectmap) ;  //得取分页地址
      List<HashMap> list = mixinfoService.findByParamWithPages("gmixinfocx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/gmixinfocx.jsp";
      }
      //gmixinfocx.jsp mixinfo 请求处理结束

     //togmixinfoxg mixinfo 请求初始化开始
     @RequestMapping("/togmixinfoxg")
     public String togmixinfoxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = mixinfoService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/gmixinfoxg.jsp";
     }
     //togmixinfoxg mixinfo 请求初始化结束

     //gmixinfoxg.jsp mixinfo 请求处理开始
     @RequestMapping("/gmixinfoxg")
     public String gmixinfoxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("滚动图片，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = mixinfoService.findByID(id); //取数据实体类
     mixinfoService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/mixinfo/gmixinfocx.do";
     }
     //gmixinfoxg.jsp mixinfo 请求处理结束

     //toqyxxx sysuser 请求初始化开始
     @RequestMapping("/toqyxxx")
     public String toqyxxx(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     if(Info.getUser(request)!=null){
     HashMap<String,String> ucollectMap = new HashMap<String,String>() ;//查询本人是否收藏，设置查询条件
     ucollectMap.put("pid",updatemap.get("id")) ;//设置信息ID
     ucollectMap.put("tablename","sysuser") ;//设置信息表名
     ucollectMap.put("orderby","id desc") ;//设置排序方式
     ucollectMap.put("uname",Info.getUser(request).get("uname").toString()) ;//设置本人用户名
     ucollectMap.put("cols","id") ;//设置查询字段
     int ucollectNum = ucollectService.findByParam(ucollectMap).size() ;//调用收藏服务，查询本人是否收藏
     modelMap.put("ucollectNum", ucollectNum); //将收藏数量发送到前端
     }
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/qyxxx.jsp";
     }
     //toqyxxx sysuser 请求初始化结束

     //qyxxx.jsp sysuser 请求处理开始
     @RequestMapping("/qyxxx")
     public String qyxxx(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("企业信息，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     //String status = updatemap.get("status").toString(); //取修改后的状态
     sysuserService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/sysuser/qyxxs.do";
     }
     //qyxxx.jsp sysuser 请求处理结束

      //qyxxs.jsp sysuser 请求处理开始
      @RequestMapping("/qyxxs")
      public String qyxxs(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/sysuser/qyxxs",selectmap) ;  //得取分页地址
      List<HashMap> list = sysuserService.findByParamWithPages("qyxxs", selectmap, 6, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/qyxxs.jsp";
      }
      //qyxxs.jsp sysuser 请求处理结束

     //tozmixinfoxg mixinfo 请求初始化开始
     @RequestMapping("/tozmixinfoxg")
     public String tozmixinfoxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = mixinfoService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/zmixinfoxg.jsp";
     }
     //tozmixinfoxg mixinfo 请求初始化结束

     //zmixinfoxg.jsp mixinfo 请求处理开始
     @RequestMapping("/zmixinfoxg")
     public String zmixinfoxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("基础信息，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = mixinfoService.findByID(id); //取数据实体类
     String content = updatemap.get("content")==null?"":updatemap.get("content"); //取文本输入框数据
     content = content.replaceAll("'"," "); //文本输入框数据移除单引号，不然入库时会报错
     updatemap.put("content",content);//文本输入框数据设值
     mixinfoService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/mixinfo/zmixinfocx.do";
     }
     //zmixinfoxg.jsp mixinfo 请求处理结束

      //zmixinfocx.jsp mixinfo 请求处理开始
      @RequestMapping("/zmixinfocx")
      public String zmixinfocx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/mixinfo/zmixinfocx",selectmap) ;  //得取分页地址
      List<HashMap> list = mixinfoService.findByParamWithPages("zmixinfocx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/zmixinfocx.jsp";
      }
      //zmixinfocx.jsp mixinfo 请求处理结束

      //topostypetj postype 请求初始化开始
      @RequestMapping("/topostypetj")
      public String topostypetj(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      modelMap.put("tglparentid", selectmap.get("tglparentid").toString()); //上级类别ID
      return "/admin/postypetj.jsp";
      }
      //topostypetj postype 请求初始化结束

      //postypetj.jsp postype 请求处理开始
      @RequestMapping("/postypetj")
      public String postypetj(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("职位类别管理，信息添加",Info.getUser(request)); //记操作日志
      postypeService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/postype/postypecx.do";
      }
      //postypetj.jsp postype 请求处理结束

     //topostypexg postype 请求初始化开始
     @RequestMapping("/topostypexg")
     public String topostypexg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = postypeService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/postypexg.jsp";
     }
     //topostypexg postype 请求初始化结束

     //postypexg.jsp postype 请求处理开始
     @RequestMapping("/postypexg")
     public String postypexg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("职位类别管理，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = postypeService.findByID(id); //取数据实体类
     postypeService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/postype/postypecx.do";
     }
     //postypexg.jsp postype 请求处理结束

      //postypecx.jsp postype 请求处理开始
      @RequestMapping("/postypecx")
      public String postypecx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      selectmap.put("tglparentid","-1"); //一级分类类别为-1
      List<HashMap> list = postypeService.findByParamWithPages("postypecx", selectmap, PageManager.DEFAULTPAGESIZE, "", request); //查询一级分类
      HashMap<String,List<HashMap>> ermap = new HashMap();//二级分类数据MAP
      for(HashMap map:list){  //循环一级类别，取二级类别
      selectmap.put("tglparentid",map.get("id").toString());  //设置查询条件为一级类别ID
      List<HashMap> erlist = postypeService.findByParamWithPages("postypecx", selectmap, PageManager.DEFAULTPAGESIZE, "", request);  //调用服务查询二级分类
      ermap.put("erlist_"+map.get("id"), erlist);
      }
      modelMap.put("ermap", ermap); //二级分类数据发送前端
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/postypecx.jsp";
      }
      //postypecx.jsp postype 请求处理结束

      //torecruitingtj recruiting 请求初始化开始
      @RequestMapping("/torecruitingtj")
      public String torecruitingtj(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      List btypeselectlist = postypeService.findByParamWithPages("findrecruitingtjdatashownamebtype",selectmap,PageManager.DEFAULTPAGESIZE,"",request); //职位大类下拉框取数
      String btypeselect = Info.getselect("btype","postype","datashowname~hnoname",btypeselectlist); //职位大类组装下拉框代码
      modelMap.put("btypeselect", btypeselect); //职位大类下拉框代码发送前端
      List stypeselectlist = postypeService.findByParamWithPages("findAll",selectmap,PageManager.DEFAULTPAGESIZE,"",request); //职位细类下拉框取数
      String stypeselect = Info.getselect("stype","postype","datashowname~hnoname",stypeselectlist); //职位细类组装下拉框代码
      modelMap.put("stypeselect", stypeselect); //职位细类下拉框代码发送前端
      return "/admin/recruitingtj.jsp";
      }
      //torecruitingtj recruiting 请求初始化结束

      //recruitingtj.jsp recruiting 请求处理开始
      @RequestMapping("/recruitingtj")
      public String recruitingtj(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("职位类别管理，信息添加",Info.getUser(request)); //记操作日志
      String qyuname = Info.getUser(request).get("uname").toString();
      String qytname = Info.getUser(request).get("tname").toString();
      String clicknums = "0";
      insertmap.put("savetime",Info.getDateStr());
      insertmap.put("qyuname",qyuname);
      insertmap.put("qytname",qytname);
      insertmap.put("clicknums",clicknums);
      recruitingService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/recruiting/recruitingcx.do";
      }
      //recruitingtj.jsp recruiting 请求处理结束

     //torecruitingxg recruiting 请求初始化开始
     @RequestMapping("/torecruitingxg")
     public String torecruitingxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     List btypeselectlist = postypeService.findByParamWithPages("findrecruitingxgdatashownamebtype",updatemap,PageManager.DEFAULTPAGESIZE,"",request); //取职位大类下拉框数据
     String btypeselect = Info.getselect("btype","postype","datashowname~hnoname",btypeselectlist);//生成下拉框代码
     modelMap.put("btypeselect", btypeselect);//下拉框代码发往前端
     List stypeselectlist = postypeService.findByParamWithPages("findAll",updatemap,PageManager.DEFAULTPAGESIZE,"",request); //取职位细类下拉框数据
     String stypeselect = Info.getselect("stype","postype","datashowname~hnoname",stypeselectlist);//生成下拉框代码
     modelMap.put("stypeselect", stypeselect);//下拉框代码发往前端
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = recruitingService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/recruitingxg.jsp";
     }
     //torecruitingxg recruiting 请求初始化结束

     //recruitingxg.jsp recruiting 请求处理开始
     @RequestMapping("/recruitingxg")
     public String recruitingxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("职位类别管理，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = recruitingService.findByID(id); //取数据实体类
     recruitingService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/recruiting/recruitingcx.do";
     }
     //recruitingxg.jsp recruiting 请求处理结束

     //topvirecruitingxg recruiting 请求初始化开始
     @RequestMapping("/topvirecruitingxg")
     public String topvirecruitingxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = recruitingService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     modelMap.put("spage", updatemap.get("spage")); //将数据ID发送到前端
     return "/admin/pvirecruitingxg.jsp";
     }
     //topvirecruitingxg recruiting 请求初始化结束

     //pvirecruitingxg.jsp recruiting 请求处理开始
     @RequestMapping("/pvirecruitingxg")
     public String pvirecruitingxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("职位类别管理，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = recruitingService.findByID(id); //取数据实体类
     recruitingService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/recruiting/pvirecruitingcx.do";
     }
     //pvirecruitingxg.jsp recruiting 请求处理结束

      //recruitingcx.jsp recruiting 请求处理开始
      @RequestMapping("/recruitingcx")
      public String recruitingcx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      List btypeselectlist = postypeService.findByParamWithPages("findrecruitingcxdatashownamebtype",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询职位大类下拉框的数据
      String btypeselect = Info.getselect("btype","postype","datashowname",btypeselectlist);//组装职位大类下拉框select代码
      modelMap.put("btypeselect", btypeselect);//职位大类下拉框select代码发往前端
      List stypeselectlist = postypeService.findByParamWithPages("findAll",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询职位细类下拉框的数据
      String stypeselect = Info.getselect("stype","postype","datashowname",stypeselectlist);//组装职位细类下拉框select代码
      modelMap.put("stypeselect", stypeselect);//职位细类下拉框select代码发往前端
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/recruiting/recruitingcx",selectmap) ;  //得取分页地址
      List<HashMap> list = recruitingService.findByParamWithPages("recruitingcx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/recruitingcx.jsp";
      }
      //recruitingcx.jsp recruiting 请求处理结束

      //fshrecruitingcx.jsp recruiting 请求处理开始
      @RequestMapping("/fshrecruitingcx")
      public String fshrecruitingcx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      List btypeselectlist = postypeService.findByParamWithPages("findfshrecruitingcxdatashownamebtype",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询职位大类下拉框的数据
      String btypeselect = Info.getselect("btype","postype","datashowname",btypeselectlist);//组装职位大类下拉框select代码
      modelMap.put("btypeselect", btypeselect);//职位大类下拉框select代码发往前端
      List stypeselectlist = postypeService.findByParamWithPages("findAll",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询职位细类下拉框的数据
      String stypeselect = Info.getselect("stype","postype","datashowname",stypeselectlist);//组装职位细类下拉框select代码
      modelMap.put("stypeselect", stypeselect);//职位细类下拉框select代码发往前端
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/recruiting/fshrecruitingcx",selectmap) ;  //得取分页地址
      List<HashMap> list = recruitingService.findByParamWithPages("fshrecruitingcx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/fshrecruitingcx.jsp";
      }
      //fshrecruitingcx.jsp recruiting 请求处理结束

     //tozpxxx recruiting 请求初始化开始
     @RequestMapping("/tozpxxx")
     public String tozpxxx(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     if(Info.getUser(request)!=null){
     HashMap<String,String> ucollectMap = new HashMap<String,String>() ;//查询本人是否收藏，设置查询条件
     ucollectMap.put("pid",updatemap.get("id")) ;//设置信息ID
     ucollectMap.put("tablename","recruiting") ;//设置信息表名
     ucollectMap.put("orderby","id desc") ;//设置排序方式
     ucollectMap.put("uname",Info.getUser(request).get("uname").toString()) ;//设置本人用户名
     ucollectMap.put("cols","id") ;//设置查询字段
     int ucollectNum = ucollectService.findByParam(ucollectMap).size() ;//调用收藏服务，查询本人是否收藏
     modelMap.put("ucollectNum", ucollectNum); //将收藏数量发送到前端
     }
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = recruitingService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/zpxxx.jsp";
     }
     //tozpxxx recruiting 请求初始化结束

     //zpxxx.jsp recruiting 请求处理开始
     @RequestMapping("/zpxxx")
     public String zpxxx(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("招聘信息，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = recruitingService.findByID(id); //取数据实体类
     recruitingService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/recruiting/zpxxs.do";
     }
     //zpxxx.jsp recruiting 请求处理结束

     //tottx recruiting 请求初始化开始
     @RequestMapping("/tottx")
     public String tottx(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     if(Info.getUser(request)!=null){
     HashMap<String,String> ucollectMap = new HashMap<String,String>() ;//查询本人是否收藏，设置查询条件
     ucollectMap.put("pid",updatemap.get("id")) ;//设置信息ID
     ucollectMap.put("tablename","recruiting") ;//设置信息表名
     ucollectMap.put("orderby","id desc") ;//设置排序方式
     ucollectMap.put("uname",Info.getUser(request).get("uname").toString()) ;//设置本人用户名
     ucollectMap.put("cols","id") ;//设置查询字段
     int ucollectNum = ucollectService.findByParam(ucollectMap).size() ;//调用收藏服务，查询本人是否收藏
     modelMap.put("ucollectNum", ucollectNum); //将收藏数量发送到前端
     }
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = recruitingService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/ttx.jsp";
     }
     //tottx recruiting 请求初始化结束

     //ttx.jsp recruiting 请求处理开始
     @RequestMapping("/ttx")
     public String ttx(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("TT，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = recruitingService.findByID(id); //取数据实体类
     recruitingService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/recruiting/tts.do";
     }
     //ttx.jsp recruiting 请求处理结束

      //tts.jsp recruiting 请求处理开始
      @RequestMapping("/tts")
      public String tts(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      List btypeselectlist = postypeService.findByParamWithPages("findttsdatashownamebtype",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询职位大类下拉框的数据
      String btypeselect = Info.getselect("btype","postype","datashowname",btypeselectlist);//组装职位大类下拉框select代码
      modelMap.put("btypeselect", btypeselect);//职位大类下拉框select代码发往前端
      List stypeselectlist = postypeService.findByParamWithPages("findAll",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询职位细类下拉框的数据
      String stypeselect = Info.getselect("stype","postype","datashowname",stypeselectlist);//组装职位细类下拉框select代码
      modelMap.put("stypeselect", stypeselect);//职位细类下拉框select代码发往前端
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/recruiting/tts",selectmap) ;  //得取分页地址
      List<HashMap> list = recruitingService.findByParamWithPages("tts", selectmap, 12, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/tts.jsp";
      }
      //tts.jsp recruiting 请求处理结束

     //toqzxxglx jobapply 请求初始化开始
     @RequestMapping("/toqzxxglx")
     public String toqzxxglx(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = jobapplyService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/qzxxglx.jsp";
     }
     //toqzxxglx jobapply 请求初始化结束

     //qzxxglx.jsp jobapply 请求处理开始
     @RequestMapping("/qzxxglx")
     public String qzxxglx(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("求职信息管理，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = jobapplyService.findByID(id); //取数据实体类
     //String fshstatus = updatemap.get("fshstatus").toString(); //取修改后的状态
     jobapplyService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/jobapply/qzxxgls.do";
     }
     //qzxxglx.jsp jobapply 请求处理结束

      //qzxxgls.jsp jobapply 请求处理开始
      @RequestMapping("/qzxxgls")
      public String qzxxgls(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/jobapply/qzxxgls",selectmap) ;  //得取分页地址
      List<HashMap> list = jobapplyService.findByParamWithPages("qzxxgls", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/qzxxgls.jsp";
      }
      //qzxxgls.jsp jobapply 请求处理结束

      //zpxxs.jsp recruiting 请求处理开始
      @RequestMapping("/zpxxs")
      public String zpxxs(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      if(StringUtils.isNotEmpty(selectmap.get("rtitle_top"))) selectmap.put("rtitle",selectmap.get("rtitle_top"));
      if(StringUtils.isNotEmpty(selectmap.get("btype_top"))) selectmap.put("btype",selectmap.get("btype_top"));
      List btypeselectlist = postypeService.findByParamWithPages("findzpxxsdatashownamebtype",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询职位大类下拉框的数据
      String btypeselect = Info.getselect("btype","postype","datashowname",btypeselectlist);//组装职位大类下拉框select代码
      modelMap.put("btypeselect", btypeselect);//职位大类下拉框select代码发往前端
      List stypeselectlist = postypeService.findByParamWithPages("findAll",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询职位细类下拉框的数据
      String stypeselect = Info.getselect("stype","postype","datashowname",stypeselectlist);//组装职位细类下拉框select代码
      modelMap.put("stypeselect", stypeselect);//职位细类下拉框select代码发往前端
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/recruiting/zpxxs",selectmap) ;  //得取分页地址
      List<HashMap> list = recruitingService.findByParamWithPages("zpxxs", selectmap, 12, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/zpxxs.jsp";
      }
      //zpxxs.jsp recruiting 请求处理结束

     //topvisysuserxg sysuser 请求初始化开始
     @RequestMapping("/topvisysuserxg")
     public String topvisysuserxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     modelMap.put("spage", updatemap.get("spage")); //将数据ID发送到前端
     return "/admin/pvisysuserxg.jsp";
     }
     //topvisysuserxg sysuser 请求初始化结束

     //pvisysuserxg.jsp sysuser 请求处理开始
     @RequestMapping("/pvisysuserxg")
     public String pvisysuserxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("我申请的职位，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     //String status = updatemap.get("status").toString(); //取修改后的状态
     sysuserService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/sysuser/pvisysusercx.do";
     }
     //pvisysuserxg.jsp sysuser 请求处理结束

      //hysysusertj.jsp sysuser 请求处理开始
      @RequestMapping("/hysysusertj")
      public String hysysusertj(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("我申请的职位，信息添加",Info.getUser(request)); //记操作日志
      String utype = "会员";
      String status = "正常";
      String clicknums = "0";
      insertmap.put("savetime",Info.getDateStr());
      insertmap.put("utype",utype);
      insertmap.put("status",status);
      insertmap.put("clicknums",clicknums);
      sysuserService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/sysuser/sysusercx.do";
      }
      //hysysusertj.jsp sysuser 请求处理结束

     //hysysuserxg.jsp sysuser 请求处理开始
     @RequestMapping("/hysysuserxg")
     public String hysysuserxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("我申请的职位，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     //String status = updatemap.get("status").toString(); //取修改后的状态
     sysuserService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/sysuser/sysusercx.do";
     }
     //hysysuserxg.jsp sysuser 请求处理结束

      //qyucollectcx.jsp ucollect 请求处理开始
      @RequestMapping("/qyucollectcx")
      public String qyucollectcx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/ucollect/qyucollectcx",selectmap) ;  //得取分页地址
      List<HashMap> list = ucollectService.findByParamWithPages("qyucollectcx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/qyucollectcx.jsp";
      }
      //qyucollectcx.jsp ucollect 请求处理结束

      //zwucollectcx.jsp ucollect 请求处理开始
      @RequestMapping("/zwucollectcx")
      public String zwucollectcx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/ucollect/zwucollectcx",selectmap) ;  //得取分页地址
      List<HashMap> list = ucollectService.findByParamWithPages("zwucollectcx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/zwucollectcx.jsp";
      }
      //zwucollectcx.jsp ucollect 请求处理结束

      //toucollecttj ucollect 请求初始化开始
      @RequestMapping("/toucollecttj")
      public String toucollecttj(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      return "/admin/ucollecttj.jsp";
      }
      //toucollecttj ucollect 请求初始化结束

      //ucollecttj.jsp ucollect 请求处理开始
      @RequestMapping("/ucollecttj")
      public String ucollecttj(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("在线交流，发起会话",Info.getUser(request)); //记操作日志
      String pid = "0";
      String uname = Info.getUser(request).get("uname").toString();
      insertmap.put("pid",pid);
      insertmap.put("uname",uname);
      ucollectService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/ucollect/ucollectcx.do";
      }
      //ucollecttj.jsp ucollect 请求处理结束

      //ucollectcx.jsp ucollect 请求处理开始
      @RequestMapping("/ucollectcx")
      public String ucollectcx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/ucollect/ucollectcx",selectmap) ;  //得取分页地址
      List<HashMap> list = ucollectService.findByParamWithPages("ucollectcx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/ucollectcx.jsp";
      }
      //ucollectcx.jsp ucollect 请求处理结束

      //tozchatstj zchats 请求初始化开始
      @RequestMapping("/tozchatstj")
      public String tozchatstj(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      List tounameautocomplist = sysuserService.findByParamWithPages("findzchatstjunametouname",selectmap,PageManager.DEFAULTPAGESIZE,"",request); //接收人自动补全框取数
      String tounameautocomp = Info.getAutoComplate("touname","sysuser","uname~hnoname;tname~hnoname",tounameautocomplist); //接收人自动补全框代码
      modelMap.put("tounameautocomp", tounameautocomp); //接收人自动补全代码发送前端
      return "/admin/zchatstj.jsp";
      }
      //tozchatstj zchats 请求初始化结束

      //zchatstj.jsp zchats 请求处理开始
      @RequestMapping("/zchatstj")
      public String zchatstj(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("在线交流，发起会话",Info.getUser(request)); //记操作日志
      String uname = Info.getUser(request).get("uname").toString();
      String pid = "0";
      insertmap.put("savetime",Info.getDateStr());
      insertmap.put("uname",uname);
      insertmap.put("pid",pid);
      zchatsService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/zchats/zchatscx.do";
      }
      //zchatstj.jsp zchats 请求处理结束

      //zchatscx.jsp zchats 请求处理开始
      @RequestMapping("/zchatscx")
      public String zchatscx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/zchats/zchatscx",selectmap) ;  //得取分页地址
      List<HashMap> list = zchatsService.findByParamWithPages("zchatscx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/zchatscx.jsp";
      }
      //zchatscx.jsp zchats 请求处理结束

      //toofferstj offers 请求初始化开始
      @RequestMapping("/toofferstj")
      public String toofferstj(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      List tounameautocomplist = sysuserService.findByParamWithPages("findofferstjunametouname",selectmap,PageManager.DEFAULTPAGESIZE,"",request); //会员自动补全框取数
      String tounameautocomp = Info.getAutoComplate("touname","sysuser","uname~hnoname;tname~hnoname",tounameautocomplist); //会员自动补全框代码
      modelMap.put("tounameautocomp", tounameautocomp); //会员自动补全代码发送前端
      return "/admin/offerstj.jsp";
      }
      //toofferstj offers 请求初始化结束

      //offerstj.jsp offers 请求处理开始
      @RequestMapping("/offerstj")
      public String offerstj(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("OFFER管理，信息添加",Info.getUser(request)); //记操作日志
      String content = request.getParameter("content")==null?"":request.getParameter("content");
      content = content.replaceAll("'"," ");
      String qyuname = Info.getUser(request).get("uname").toString();
      String qytname = Info.getUser(request).get("tname").toString();
      insertmap.put("savetime",Info.getDateStr());
      insertmap.put("content",content);
      insertmap.put("qyuname",qyuname);
      insertmap.put("qytname",qytname);
      offersService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/offers/offerscx.do";
      }
      //offerstj.jsp offers 请求处理结束

     //tooffersxg offers 请求初始化开始
     @RequestMapping("/tooffersxg")
     public String tooffersxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     List tounameautocomplist = sysuserService.findByParamWithPages("findoffersxgunametouname",updatemap,PageManager.DEFAULTPAGESIZE,"",request); //取会员自动补全下拉框数据
     String tounameautocomp = Info.getAutoComplate("touname","sysuser","uname~hnoname;tname~hnoname",tounameautocomplist);//生成自动补全代码
     modelMap.put("tounameautocomp", tounameautocomp); //自动补全代码发往前端
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = offersService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/offersxg.jsp";
     }
     //tooffersxg offers 请求初始化结束

     //offersxg.jsp offers 请求处理开始
     @RequestMapping("/offersxg")
     public String offersxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("OFFER管理，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = offersService.findByID(id); //取数据实体类
     String content = updatemap.get("content")==null?"":updatemap.get("content"); //取文本输入框数据
     content = content.replaceAll("'"," "); //文本输入框数据移除单引号，不然入库时会报错
     updatemap.put("content",content);//文本输入框数据设值
     offersService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/offers/offerscx.do";
     }
     //offersxg.jsp offers 请求处理结束

     //topvioffersxg offers 请求初始化开始
     @RequestMapping("/topvioffersxg")
     public String topvioffersxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = offersService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     modelMap.put("spage", updatemap.get("spage")); //将数据ID发送到前端
     return "/admin/pvioffersxg.jsp";
     }
     //topvioffersxg offers 请求初始化结束

     //pvioffersxg.jsp offers 请求处理开始
     @RequestMapping("/pvioffersxg")
     public String pvioffersxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("OFFER管理，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = offersService.findByID(id); //取数据实体类
     String content = updatemap.get("content")==null?"":updatemap.get("content"); //取文本输入框数据
     content = content.replaceAll("'"," "); //文本输入框数据移除单引号，不然入库时会报错
     updatemap.put("content",content);//文本输入框数据设值
     offersService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/offers/pviofferscx.do";
     }
     //pvioffersxg.jsp offers 请求处理结束

      //offerscx.jsp offers 请求处理开始
      @RequestMapping("/offerscx")
      public String offerscx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/offers/offerscx",selectmap) ;  //得取分页地址
      List<HashMap> list = offersService.findByParamWithPages("offerscx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/offerscx.jsp";
      }
      //offerscx.jsp offers 请求处理结束

      //fshofferscx.jsp offers 请求处理开始
      @RequestMapping("/fshofferscx")
      public String fshofferscx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/offers/fshofferscx",selectmap) ;  //得取分页地址
      List<HashMap> list = offersService.findByParamWithPages("fshofferscx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/fshofferscx.jsp";
      }
      //fshofferscx.jsp offers 请求处理结束

     //tomessagesxg messages 请求初始化开始
     @RequestMapping("/tomessagesxg")
     public String tomessagesxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = messagesService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/messagesxg.jsp";
     }
     //tomessagesxg messages 请求初始化结束

     //messagesxg.jsp messages 请求处理开始
     @RequestMapping("/messagesxg")
     public String messagesxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("留言管理，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = messagesService.findByID(id); //取数据实体类
     messagesService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/messages/messagescx.do";
     }
     //messagesxg.jsp messages 请求处理结束

      //messagescx.jsp messages 请求处理开始
      @RequestMapping("/messagescx")
      public String messagescx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/messages/messagescx",selectmap) ;  //得取分页地址
      List<HashMap> list = messagesService.findByParamWithPages("messagescx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/messagescx.jsp";
      }
      //messagescx.jsp messages 请求处理结束

      //tosmailtj smail 请求初始化开始
      @RequestMapping("/tosmailtj")
      public String tosmailtj(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      List tounameautocomplist = sysuserService.findByParamWithPages("findAll",selectmap,PageManager.DEFAULTPAGESIZE,"",request); //接收人自动补全框取数
      String tounameautocomp = Info.getAutoComplate("touname","sysuser","uname~hnoname;utype~hnoname",tounameautocomplist); //接收人自动补全框代码
      modelMap.put("tounameautocomp", tounameautocomp); //接收人自动补全代码发送前端
      return "/admin/smailtj.jsp";
      }
      //tosmailtj smail 请求初始化结束

      //smailtj.jsp smail 请求处理开始
      @RequestMapping("/smailtj")
      public String smailtj(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("发送邮件，信息添加",Info.getUser(request)); //记操作日志
      String content = request.getParameter("content")==null?"":request.getParameter("content");
      content = content.replaceAll("'"," ");
      String uname = Info.getUser(request).get("uname").toString();
      insertmap.put("savetime",Info.getDateStr());
      insertmap.put("content",content);
      insertmap.put("uname",uname);
      smailService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/smail/smailcx.do";
      }
      //smailtj.jsp smail 请求处理结束

     //tosmailxg smail 请求初始化开始
     @RequestMapping("/tosmailxg")
     public String tosmailxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     List tounameautocomplist = sysuserService.findByParamWithPages("findAll",updatemap,PageManager.DEFAULTPAGESIZE,"",request); //取接收人自动补全下拉框数据
     String tounameautocomp = Info.getAutoComplate("touname","sysuser","uname~hnoname;utype~hnoname",tounameautocomplist);//生成自动补全代码
     modelMap.put("tounameautocomp", tounameautocomp); //自动补全代码发往前端
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = smailService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/smailxg.jsp";
     }
     //tosmailxg smail 请求初始化结束

     //smailxg.jsp smail 请求处理开始
     @RequestMapping("/smailxg")
     public String smailxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("发送邮件，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = smailService.findByID(id); //取数据实体类
     String content = updatemap.get("content")==null?"":updatemap.get("content"); //取文本输入框数据
     content = content.replaceAll("'"," "); //文本输入框数据移除单引号，不然入库时会报错
     updatemap.put("content",content);//文本输入框数据设值
     smailService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/smail/smailcx.do";
     }
     //smailxg.jsp smail 请求处理结束

     //tofshsmailxg smail 请求初始化开始
     @RequestMapping("/tofshsmailxg")
     public String tofshsmailxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = smailService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/fshsmailxg.jsp";
     }
     //tofshsmailxg smail 请求初始化结束

     //fshsmailxg.jsp smail 请求处理开始
     @RequestMapping("/fshsmailxg")
     public String fshsmailxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("发送邮件，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = smailService.findByID(id); //取数据实体类
     smailService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/smail/fshsmailcx.do";
     }
     //fshsmailxg.jsp smail 请求处理结束

     //topvismailxg smail 请求初始化开始
     @RequestMapping("/topvismailxg")
     public String topvismailxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = smailService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     modelMap.put("spage", updatemap.get("spage")); //将数据ID发送到前端
     return "/admin/pvismailxg.jsp";
     }
     //topvismailxg smail 请求初始化结束

     //pvismailxg.jsp smail 请求处理开始
     @RequestMapping("/pvismailxg")
     public String pvismailxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("发送邮件，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = smailService.findByID(id); //取数据实体类
     String content = updatemap.get("content")==null?"":updatemap.get("content"); //取文本输入框数据
     content = content.replaceAll("'"," "); //文本输入框数据移除单引号，不然入库时会报错
     updatemap.put("content",content);//文本输入框数据设值
     smailService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/smail/pvismailcx.do";
     }
     //pvismailxg.jsp smail 请求处理结束

      //smailcx.jsp smail 请求处理开始
      @RequestMapping("/smailcx")
      public String smailcx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/smail/smailcx",selectmap) ;  //得取分页地址
      List<HashMap> list = smailService.findByParamWithPages("smailcx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/smailcx.jsp";
      }
      //smailcx.jsp smail 请求处理结束

      //fshsmailcx.jsp smail 请求处理开始
      @RequestMapping("/fshsmailcx")
      public String fshsmailcx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/smail/fshsmailcx",selectmap) ;  //得取分页地址
      List<HashMap> list = smailService.findByParamWithPages("fshsmailcx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/fshsmailcx.jsp";
      }
      //fshsmailcx.jsp smail 请求处理结束

      //tjpvirecruitingcx.jsp recruiting 请求处理开始
      @RequestMapping("/tjpvirecruitingcx")
      public String tjpvirecruitingcx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
          String ids  = "";
          if(Info.getUser(request)!=null)
          {
              List<HashMap> list2 = new CommDAO().select("select uname tj,group_concat(distinct(ftitle),'',' ') s from viewhistory where uname='"+Info.getUser(request).get("uname")+"'  and ftable='recruiting'  group by uname union select '"+Info.getUser(request).get("uname")+"',ftitle from viewhistory where ftable='recruiting' ");
              ArrayList<String> l = new ArrayList<String>();
              int x = 0;
              for(HashMap<String,String> m:list2)
              {

                  String a = m.get("tj").toString().replaceAll(" ", "").replaceAll(",", "");
                  String s = m.get("s").toString().replaceAll(" ", "").replaceAll(",", "");
                  a=s;
                  if(StringUtils.isNotEmpty(s))
                  l.add(a);
                  x++;
              }
              String templates = CollaborativeDistillUtil.getTempage(l);
              l.remove(0);
              l.add(0,Info.getUser(request).get("uname")+" "+templates);

              ArrayList<String> l2 = new ArrayList<String>();
              l2.add(Info.getUser(request).get("uname")+" "+templates);
              for(HashMap m:new CommDAO().select("select * from recruiting"))
              {
                  String mstr = "";
                  for(String str:templates.split(" "))
                  {
                      str=str.trim();
                      if(str.length()==0)continue;
                      if(m.get("rtitle").toString().contains(str)&&!m.get("rtitle").toString().equals(" ")&&!m.get("rtitle").toString().equals(""))
                          mstr+=str+" ";
                  }
                  if(mstr.length()>0)
                      l2.add(m.get("id")+" "+mstr);
              }
              System.out.println(l2);
              ids = CollaborativeFiltering.filter(l2,Info.getUser(request).get("uname").toString());
              selectmap.put("ids", ids);
          }

          List btypeselectlist = postypeService.findByParamWithPages("findtjpvirecruitingcxdatashownamebtype",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询职位大类下拉框的数据
      String btypeselect = Info.getselect("btype","postype","datashowname",btypeselectlist);//组装职位大类下拉框select代码
      modelMap.put("btypeselect", btypeselect);//职位大类下拉框select代码发往前端
      List stypeselectlist = postypeService.findByParamWithPages("findAll",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询职位细类下拉框的数据
      String stypeselect = Info.getselect("stype","postype","datashowname",stypeselectlist);//组装职位细类下拉框select代码
      modelMap.put("stypeselect", stypeselect);//职位细类下拉框select代码发往前端
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/recruiting/tjpvirecruitingcx",selectmap) ;  //得取分页地址
      List<HashMap> list = recruitingService.findByParamWithPages("tjpvirecruitingcx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/tjpvirecruitingcx.jsp";
      }
      //tjpvirecruitingcx.jsp recruiting 请求处理结束

     //torcxxx sysuser 请求初始化开始
     @RequestMapping("/torcxxx")
     public String torcxxx(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     if(Info.getUser(request)!=null){
     HashMap<String,String> ucollectMap = new HashMap<String,String>() ;//查询本人是否收藏，设置查询条件
     ucollectMap.put("pid",updatemap.get("id")) ;//设置信息ID
     ucollectMap.put("tablename","sysuser") ;//设置信息表名
     ucollectMap.put("orderby","id desc") ;//设置排序方式
     ucollectMap.put("uname",Info.getUser(request).get("uname").toString()) ;//设置本人用户名
     ucollectMap.put("cols","id") ;//设置查询字段
     int ucollectNum = ucollectService.findByParam(ucollectMap).size() ;//调用收藏服务，查询本人是否收藏
     modelMap.put("ucollectNum", ucollectNum); //将收藏数量发送到前端
     }
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/rcxxx.jsp";
     }
     //torcxxx sysuser 请求初始化结束

     //rcxxx.jsp sysuser 请求处理开始
     @RequestMapping("/rcxxx")
     public String rcxxx(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("人才信息，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     //String status = updatemap.get("status").toString(); //取修改后的状态
     sysuserService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/sysuser/rcxxs.do";
     }
     //rcxxx.jsp sysuser 请求处理结束

      //rcxxs.jsp sysuser 请求处理开始
      @RequestMapping("/rcxxs")
      public String rcxxs(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/sysuser/rcxxs",selectmap) ;  //得取分页地址
      List<HashMap> list = sysuserService.findByParamWithPages("rcxxs", selectmap, 6, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/rcxxs.jsp";
      }
      //rcxxs.jsp sysuser 请求处理结束

      //ucollect 收藏请求处理开始
	@RequestMapping("/ucollectopr")
	public String ucollectopr(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
        String table = selectmap.get("table");//收藏的表名
        String uname = Info.getUser(request).get("uname").toString();//收藏人
        String id = selectmap.get("id");//收藏表的ID
        String title = selectmap.get("title");//收藏表的标题
        String userid = "";
        if(table.equals("sysuser")){
            //根据uname取出用户的hashmap
            HashMap usermap = new HashMap();
            HashMap<String,String> parammap = new HashMap<String,String>();
            parammap.put("uname",title);
            List userlist = sysuserService.findByParam(parammap);
            if(CollectionUtils.isNotEmpty(userlist)){
                usermap = (HashMap) userlist.get(0);
            }
            userid = usermap.get("id")+"";//用户ID
            if(userid!=null&&!userid.equals("null"))
                id = userid;
        }
        HashMap<String,String> ucollectMap = new HashMap<String,String>() ;//查询本人收藏，设置查询条件
        ucollectMap.put("pid",id) ;//设置信息ID
        ucollectMap.put("tablename",table) ;//设置信息表名
        ucollectMap.put("orderby","id desc") ;//设置排序方式
        ucollectMap.put("uname",Info.getUser(request).get("uname").toString()) ;//设置本人用户名
        ucollectMap.put("cols","id") ;//设置查询字段
        int ucollectNum = ucollectService.findByParam(ucollectMap).size() ;//调用收藏服务，查询本人收藏数量
        if(ucollectNum==0) { //如果没有收藏过就添加收藏记录
            HashMap<String, String> imap = new HashMap<String, String>();
            imap.put("pid", id);
            if(table.equals("sysuser")&&userid!=null&&!userid.equals("null")) imap.put("pid", userid);
            imap.put("uname", uname);
            imap.put("ptitle", title);
            imap.put("tablename", table);
            ucollectService.insert(imap);
        }
        String table2 = selectmap.get("table2");//收藏的表名
        if(StringUtils.isNotEmpty(table2))table = table2;
        return "/"+table+"/"+selectmap.get("surl")+".do";
	}
	//ucollect 收藏请求处理结束

      //rcucollectcx.jsp ucollect 请求处理开始
      @RequestMapping("/rcucollectcx")
      public String rcucollectcx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/ucollect/rcucollectcx",selectmap) ;  //得取分页地址
      List<HashMap> list = ucollectService.findByParamWithPages("rcucollectcx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/rcucollectcx.jsp";
      }
      //rcucollectcx.jsp ucollect 请求处理结束

      //rctjsysusercx.jsp sysuser 请求处理开始
      @RequestMapping("/rctjsysusercx")
      public String rctjsysusercx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
		  String ids  = "";
		  if(Info.getUser(request)!=null)
		  {
		  	HashMap  cuser = Info.getUser(request);
		  	String uname = "";
		  	String uid = "";
		  	String tname = "";
		  	if(cuser!=null){
		  	    uname = cuser.get("uname").toString();
		  	    uid = cuser.get("id")+"";
		  	    tname = cuser.get("tname").toString();
		  	}
			  List<HashMap> list2 = new CommDAO().select("select a.uname tj,group_concat(distinct(b.skill),'',' ') s from viewhistory a,sysuser b where a.fid=b.id and a.uname='"+uname+"'  and a.ftable='sysuser'  group by a.uname union all select '"+uname+"',b.skill from viewhistory a,sysuser b where a.fid=b.id and a.ftable='sysuser' and a.uname='"+uname+"' and b.utype='会员' ");
			  ArrayList<String> l = new ArrayList<String>();
			  int x = 0;
			  for(HashMap<String,String> m:list2)
			  {

//				  String a = m.get("tj").toString().replaceAll(" ", "").replaceAll(",", "");
//				  String s = m.get("s").toString().replaceAll(" ", "").replaceAll(",", "");
				  String a = m.get("tj").toString() ;
				  String s = m.get("s").toString() ;
				  a=s;
				  if(StringUtils.isNotEmpty(s))
					  l.add(a);
				  x++;
			  }
			  String templates = CollaborativeDistillUtil.getTempage(l);
			  l.remove(0);
			  l.add(0,Info.getUser(request).get("uname")+" "+templates);

			  ArrayList<String> l2 = new ArrayList<String>();
			  l2.add(Info.getUser(request).get("uname")+" "+templates);
			  for(HashMap m:new CommDAO().select("select * from sysuser"))
			  {
				  String mstr = "";
				  for(String str:templates.split(" "))
				  {
					  str=str.trim();
					  if(str.length()==0)continue;
					  if(m.get("skill").toString().contains(str)&&!m.get("skill").toString().equals(" ")&&!m.get("skill").toString().equals(""))
						  mstr+=str+" ";
				  }
				  if(mstr.length()>0)
					  l2.add(m.get("id")+" "+mstr);
			  }
			  System.out.println(l2);
			  ids = CollaborativeFiltering.filter(l2,Info.getUser(request).get("uname").toString());
			  selectmap.put("ids", ids);
		  }

		  String url = Info.getPageUrl("/sysuser/rctjsysusercx",selectmap) ;  //得取分页地址
      List<HashMap> list = sysuserService.findByParamWithPages("rctjsysusercx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/rctjsysusercx.jsp";
      }
      //rctjsysusercx.jsp sysuser 请求处理结束

     //tox recruiting 请求初始化开始
     @RequestMapping("/tox")
     public String tox(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = recruitingService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/x.jsp";
     }
     //tox recruiting 请求初始化结束

     //x.jsp recruiting 请求处理开始
     @RequestMapping("/x")
     public String x(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = recruitingService.findByID(id); //取数据实体类
     recruitingService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/recruiting/s.do";
     }
     //x.jsp recruiting 请求处理结束

      //s.jsp recruiting 请求处理开始
      @RequestMapping("/s")
      public String s(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      List btypeselectlist = postypeService.findByParamWithPages("findsdatashownamebtype",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询职位大类下拉框的数据
      String btypeselect = Info.getselect("btype","postype","datashowname",btypeselectlist);//组装职位大类下拉框select代码
      modelMap.put("btypeselect", btypeselect);//职位大类下拉框select代码发往前端
      List stypeselectlist = postypeService.findByParamWithPages("findAll",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询职位细类下拉框的数据
      String stypeselect = Info.getselect("stype","postype","datashowname",stypeselectlist);//组装职位细类下拉框select代码
      modelMap.put("stypeselect", stypeselect);//职位细类下拉框select代码发往前端
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/recruiting/s",selectmap) ;  //得取分页地址
      List<HashMap> list = recruitingService.findByParamWithPages("s", selectmap, 12, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/s.jsp";
      }
      //s.jsp recruiting 请求处理结束

     //tosqcsx recruiting 请求初始化开始
     @RequestMapping("/tosqcsx")
     public String tosqcsx(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = recruitingService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/sqcsx.jsp";
     }
     //tosqcsx recruiting 请求初始化结束

     //sqcsx.jsp recruiting 请求处理开始
     @RequestMapping("/sqcsx")
     public String sqcsx(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("申请测试，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = recruitingService.findByID(id); //取数据实体类
     recruitingService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/recruiting/sqcss.do";
     }
     //sqcsx.jsp recruiting 请求处理结束

      //sqcss.jsp recruiting 请求处理开始
      @RequestMapping("/sqcss")
      public String sqcss(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      List btypeselectlist = postypeService.findByParamWithPages("findsqcssdatashownamebtype",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询职位大类下拉框的数据
      String btypeselect = Info.getselect("btype","postype","datashowname",btypeselectlist);//组装职位大类下拉框select代码
      modelMap.put("btypeselect", btypeselect);//职位大类下拉框select代码发往前端
      List stypeselectlist = postypeService.findByParamWithPages("findAll",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询职位细类下拉框的数据
      String stypeselect = Info.getselect("stype","postype","datashowname",stypeselectlist);//组装职位细类下拉框select代码
      modelMap.put("stypeselect", stypeselect);//职位细类下拉框select代码发往前端
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/recruiting/sqcss",selectmap) ;  //得取分页地址
      List<HashMap> list = recruitingService.findByParamWithPages("sqcss", selectmap, 12, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/sqcss.jsp";
      }
      //sqcss.jsp recruiting 请求处理结束

      //tojobapplytj jobapply 请求初始化开始
      @RequestMapping("/tojobapplytj")
      public String tojobapplytj(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      return "/admin/jobapplytj.jsp";
      }
      //tojobapplytj jobapply 请求初始化结束

      //jobapplytj.jsp jobapply 请求处理开始
      @RequestMapping("/jobapplytj")
      public String jobapplytj(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("申请测试，信息添加",Info.getUser(request)); //记操作日志
      String uname = Info.getUser(request).get("uname").toString();
      String tname = Info.getUser(request).get("tname").toString();
      insertmap.put("savetime",Info.getDateStr());
      insertmap.put("uname",uname);
      insertmap.put("tname",tname);
      jobapplyService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/jobapply/jobapplycx.do";
      }
      //jobapplytj.jsp jobapply 请求处理结束

     //tojobapplyxg jobapply 请求初始化开始
     @RequestMapping("/tojobapplyxg")
     public String tojobapplyxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = jobapplyService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/jobapplyxg.jsp";
     }
     //tojobapplyxg jobapply 请求初始化结束

     //jobapplyxg.jsp jobapply 请求处理开始
     @RequestMapping("/jobapplyxg")
     public String jobapplyxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("申请测试，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = jobapplyService.findByID(id); //取数据实体类
     //String fshstatus = updatemap.get("fshstatus").toString(); //取修改后的状态
     jobapplyService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/jobapply/jobapplycx.do";
     }
     //jobapplyxg.jsp jobapply 请求处理结束

     //tospvivotingxg jobapply 请求初始化开始
     @RequestMapping("/tospvivotingxg")
     public String tospvivotingxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = jobapplyService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/spvivotingxg.jsp";
     }
     //tospvivotingxg jobapply 请求初始化结束

     //spvivotingxg.jsp jobapply 请求处理开始
     @RequestMapping("/spvivotingxg")
     public String spvivotingxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("申请测试，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = jobapplyService.findByID(id); //取数据实体类
     //String fshstatus = updatemap.get("fshstatus").toString(); //取修改后的状态
     jobapplyService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/jobapply/spvivotingcx.do";
     }
     //spvivotingxg.jsp jobapply 请求处理结束

     //tocssqx recruiting 请求初始化开始
     @RequestMapping("/tocssqx")
     public String tocssqx(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = recruitingService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/cssqx.jsp";
     }
     //tocssqx recruiting 请求初始化结束

     //cssqx.jsp recruiting 请求处理开始
     @RequestMapping("/cssqx")
     public String cssqx(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("测试申请，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = recruitingService.findByID(id); //取数据实体类
     recruitingService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/recruiting/cssqs.do";
     }
     //cssqx.jsp recruiting 请求处理结束

      //cssqs.jsp recruiting 请求处理开始
      @RequestMapping("/cssqs")
      public String cssqs(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      List btypeselectlist = postypeService.findByParamWithPages("findcssqsdatashownamebtype",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询职位大类下拉框的数据
      String btypeselect = Info.getselect("btype","postype","datashowname",btypeselectlist);//组装职位大类下拉框select代码
      modelMap.put("btypeselect", btypeselect);//职位大类下拉框select代码发往前端
      List stypeselectlist = postypeService.findByParamWithPages("findAll",selectmap,PageManager.DEFAULTPAGESIZE,"",request);//查询职位细类下拉框的数据
      String stypeselect = Info.getselect("stype","postype","datashowname",stypeselectlist);//组装职位细类下拉框select代码
      modelMap.put("stypeselect", stypeselect);//职位细类下拉框select代码发往前端
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/recruiting/cssqs",selectmap) ;  //得取分页地址
      List<HashMap> list = recruitingService.findByParamWithPages("cssqs", selectmap, 12, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/cssqs.jsp";
      }
      //cssqs.jsp recruiting 请求处理结束

     //tofshjobapplyxg jobapply 请求初始化开始
     @RequestMapping("/tofshjobapplyxg")
     public String tofshjobapplyxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = jobapplyService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/fshjobapplyxg.jsp";
     }
     //tofshjobapplyxg jobapply 请求初始化结束

     //fshjobapplyxg.jsp jobapply 请求处理开始
     @RequestMapping("/fshjobapplyxg")
     public String fshjobapplyxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("oooooooooo，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = jobapplyService.findByID(id); //取数据实体类
     //String fshstatus = updatemap.get("fshstatus").toString(); //取修改后的状态
     jobapplyService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/jobapply/fshjobapplycx.do";
     }
     //fshjobapplyxg.jsp jobapply 请求处理结束

     //topvijobapplyxg jobapply 请求初始化开始
     @RequestMapping("/topvijobapplyxg")
     public String topvijobapplyxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = jobapplyService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     modelMap.put("spage", updatemap.get("spage")); //将数据ID发送到前端
     return "/admin/pvijobapplyxg.jsp";
     }
     //topvijobapplyxg jobapply 请求初始化结束

     //pvijobapplyxg.jsp jobapply 请求处理开始
     @RequestMapping("/pvijobapplyxg")
     public String pvijobapplyxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("oooooooooo，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = jobapplyService.findByID(id); //取数据实体类
     //String fshstatus = updatemap.get("fshstatus").toString(); //取修改后的状态
     jobapplyService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/jobapply/pvijobapplycx.do";
     }
     //pvijobapplyxg.jsp jobapply 请求处理结束

      //jobapplycx.jsp jobapply 请求处理开始
      @RequestMapping("/jobapplycx")
      public String jobapplycx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/jobapply/jobapplycx",selectmap) ;  //得取分页地址
      List<HashMap> list = jobapplyService.findByParamWithPages("jobapplycx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/jobapplycx.jsp";
      }
      //jobapplycx.jsp jobapply 请求处理结束

      //fshjobapplycx.jsp jobapply 请求处理开始
      @RequestMapping("/fshjobapplycx")
      public String fshjobapplycx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/jobapply/fshjobapplycx",selectmap) ;  //得取分页地址
      List<HashMap> list = jobapplyService.findByParamWithPages("fshjobapplycx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/admin/fshjobapplycx.jsp";
      }
      //fshjobapplycx.jsp jobapply 请求处理结束

     //tooooooooooox jobapply 请求初始化开始
     @RequestMapping("/tooooooooooox")
     public String tooooooooooox(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = jobapplyService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/oooooooooox.jsp";
     }
     //tooooooooooox jobapply 请求初始化结束

     //oooooooooox.jsp jobapply 请求处理开始
     @RequestMapping("/oooooooooox")
     public String oooooooooox(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("oooooooooo，信息查看",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = jobapplyService.findByID(id); //取数据实体类
     //String fshstatus = updatemap.get("fshstatus").toString(); //取修改后的状态
     jobapplyService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/jobapply/oooooooooos.do";
     }
     //oooooooooox.jsp jobapply 请求处理结束

      //oooooooooos.jsp jobapply 请求处理开始
      @RequestMapping("/oooooooooos")
      public String oooooooooos(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/jobapply/oooooooooos",selectmap) ;  //得取分页地址
      List<HashMap> list = jobapplyService.findByParamWithPages("oooooooooos", selectmap, 12, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      return "/oooooooooos.jsp";
      }
      //oooooooooos.jsp jobapply 请求处理结束

      

      

     

     

      //tohysysusertj sysuser 请求初始化开始
      @RequestMapping("/tohysysusertj")
      public String tohysysusertj(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
          List stypeselectlist = ztypeService.findByParamWithPages("findAll",selectmap,PageManager.DEFAULTPAGESIZE,"",request); //职位细类下拉框取数
          String stypeselect = Info.getradio("specialty","ztype","zname~hnoname",stypeselectlist); //职位细类组装下拉框代码
          modelMap.put("stypeselect", stypeselect); //职位细类下拉框代码发送前端
	    return "/admin/hysysusertj.jsp";
      }
      //tohysysusertj sysuser 请求初始化结束

      //tohysysuserxg sysuser 请求初始化开始
     @RequestMapping("/tohysysuserxg")
     public String tohysysuserxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
         List stypeselectlist = ztypeService.findByParamWithPages("findAll",updatemap,PageManager.DEFAULTPAGESIZE,"",request); //职位细类下拉框取数
         String stypeselect = Info.getradio("specialty","ztype","zname~hnoname",stypeselectlist); //职位细类组装下拉框代码
         modelMap.put("stypeselect", stypeselect); //职位细类下拉框代码发送前端
     return "/admin/hysysuserxg.jsp";
     }
     //tohysysuserxg sysuser 请求初始化结束

     //tokssysuserxg sysuser 请求初始化开始
     @RequestMapping("/tokssysuserxg")
     public String tokssysuserxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/kssysuserxg.jsp";
     }
     //tokssysuserxg sysuser 请求初始化结束

     //kssysuserxg.jsp sysuser 请求处理开始
     @RequestMapping("/kssysuserxg")
     public String kssysuserxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("专业领域可视化，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     //String status = updatemap.get("status").toString(); //取修改后的状态
     sysuserService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/sysuser/kssysusercx.do";
     }
     //kssysuserxg.jsp sysuser 请求处理结束

     //tosystemlogxg systemlog 请求初始化开始
     @RequestMapping("/tosystemlogxg")
     public String tosystemlogxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = systemlogService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/systemlogxg.jsp";
     }
     //tosystemlogxg systemlog 请求初始化结束

     //systemlogxg.jsp systemlog 请求处理开始
     @RequestMapping("/systemlogxg")
     public String systemlogxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("系统日志，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = systemlogService.findByID(id); //取数据实体类
     systemlogService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/systemlog/systemlogcx.do";
     }
     //systemlogxg.jsp systemlog 请求处理结束

      //toperhysysuserxg sysuser 请求初始化开始
     @RequestMapping("/toperhysysuserxg")
     public String toperhysysuserxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = Info.getUser(request).get("id").toString(); //取数据ID
     HashMap smap = sysuserService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
         List stypeselectlist = ztypeService.findByParamWithPages("findAll",updatemap,PageManager.DEFAULTPAGESIZE,"",request); //职位细类下拉框取数
         String stypeselect = Info.getradio("specialty","ztype","zname~hnoname",stypeselectlist); //职位细类组装下拉框代码
         modelMap.put("stypeselect", stypeselect); //职位细类下拉框代码发送前端
     return "/admin/perhysysuserxg.jsp";
     }
     //toperhysysuserxg sysuser 请求初始化结束

      //kssysusercx.jsp sysuser 请求处理开始
      @RequestMapping("/kssysusercx")
      public String kssysusercx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/sysuser/kssysusercx",selectmap) ;  //得取分页地址
      List<HashMap> list = sysuserService.findByParamWithPages("kssysusercx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      session.setAttribute("tobackurl", "/itivtmgr/sysuser/kssysusercx.do"); //列表路径存到session，返回时要用到

          HashMap<String,String> parammap = new HashMap<String,String>();
          HashMap<String,String> parammap2 = list.get(0);
          ArrayList<String> alist = new ArrayList();
          for (HashMap hashMap : list) {
              parammap2.put(hashMap.get("items").toString(),hashMap.get("ivalue").toString());
              alist.add(hashMap.get("items").toString()+"&"+hashMap.get("ivalue").toString());
          }
          modelMap.put("parammap2", parammap2); //列表发送前端
          modelMap.put("tub",Info.getBCharts("专业领域统计图表",alist,"1"));

      return "/admin/kssysusercx.jsp";
      }
      //kssysusercx.jsp sysuser 请求处理结束

      //dytab_examdtl请求初始化开始
      @RequestMapping("/dytab_examdtl")
      public String dytab_examdtl(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      return "/admin/dytab_examdtl.jsp";
      }
      //dytab_examdtl请求初始化结束

      //toexamstj exams 请求初始化开始
      @RequestMapping("/toexamstj")
      public String toexamstj(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      List tounameautocomplist = sysuserService.findByParamWithPages("findexamstjunametouname",selectmap,PageManager.DEFAULTPAGESIZE,"",request); //应聘人自动补全框取数
      String tounameautocomp = Info.getAutoComplate("touname","sysuser","uname~hnoname;tname~hnoname",tounameautocomplist); //应聘人自动补全框代码
      modelMap.put("tounameautocomp", tounameautocomp); //应聘人自动补全代码发送前端
      //List dyselectlist = 下拉框表名Service.findByParamWithPages("findAll",selectmap,PageManager.DEFAULTPAGESIZE,"",request); //下拉框取数
      //modelMap.put("dyinitjs", Info.dytabInitInsert("ititle","下拉框表的字段名","examdtl", dyselectlist,"i")); //下拉框初始化
      return "/admin/examstj.jsp";
      }
      //toexamstj exams 请求初始化结束

      //examstj.jsp exams 请求处理开始
      @RequestMapping("/examstj")
      public String examstj(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("笔试管理，信息添加",Info.getUser(request)); //记操作日志
      String frgroupno = request.getParameter("frgroupno");//获取主子表关联值
      String dytablename = request.getParameter("dytablename");//获取明细表表名
      String dytabdelrows = request.getParameter("dytab_del_rows");//获取明细数据条数
      dao.insertDYTable(dytablename, frgroupno,dytabdelrows,request);//添加明细数据
      //开始更新明细表examdtl关联ID start -
      HashMap<String,String> parammap = new HashMap<String,String>();
      parammap.put("zdycol","frgroupno='"+frgroupno+"'");
      List<HashMap<String, String>> dlist = examdtlService.findByParam(parammap);
      for (HashMap<String, String> dmap : dlist) {
          examdtlService.update(dmap);
      }
      //开始更新明细表examdtl关联ID end -
      String qyuname = Info.getUser(request).get("uname").toString();
      insertmap.put("frgroupno",frgroupno);
      insertmap.put("examdtl","dytab-"+dytablename);
      insertmap.put("savetime",Info.getDateStr());
      insertmap.put("qyuname",qyuname);
      examsService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/exams/examscx.do";
      }
      //examstj.jsp exams 请求处理结束

     //toexamsxg exams 请求初始化开始
     @RequestMapping("/toexamsxg")
     public String toexamsxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     List tounameautocomplist = sysuserService.findByParamWithPages("findexamsxgunametouname",updatemap,PageManager.DEFAULTPAGESIZE,"",request); //取应聘人自动补全下拉框数据
     String tounameautocomp = Info.getAutoComplate("touname","sysuser","uname~hnoname;tname~hnoname",tounameautocomplist);//生成自动补全代码
     modelMap.put("tounameautocomp", tounameautocomp); //自动补全代码发往前端
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = examsService.findByID(id); //取数据实体类
     smap = dao.dyTform(smap); //本修改功能有动态表格，需要加载动态表格的数据
     modelMap.put("frgroupno", smap.get("frgroupno")); //将数据动态表格关联索引发送到前端
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     //List dyselectlist = 下拉框表名Service.findByParamWithPages("findAll",updatemap,PageManager.DEFAULTPAGESIZE,"",request); //下拉框取数
     //modelMap.put("dyinitjs", Info.dytabInitInsert("ititle","下拉框表的字段名","examdtl", dyselectlist,"u")); //下拉框初始化
     return "/admin/examsxg.jsp";
     }
     //toexamsxg exams 请求初始化结束

     //examsxg.jsp exams 请求处理开始
     @RequestMapping("/examsxg")
     public String examsxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("笔试管理，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = examsService.findByID(id); //取数据实体类
     String frgroupno = updatemap.get("frgroupno"); //取题目动态表格关联字段值
     String dytablename = updatemap.get("dytablename"); //取题目动态表名
     String dytabdelrows = updatemap.get("dytab_del_rows"); //取题目需删除的数据ID
     dao.insertDYTable(dytablename, frgroupno,dytabdelrows,request); //题目动态表格数据入库
     //开始更新明细表examdtl关联ID start -
     HashMap<String,String> parammap = new HashMap<String,String>();
     parammap.put("zdycol","frgroupno='"+frgroupno+"'");
     List<HashMap<String, String>> dlist = examdtlService.findByParam(parammap);
     for (HashMap<String, String> dmap : dlist) {
         examdtlService.update(dmap);
     }
     //开始更新明细表examdtl关联ID end -
     updatemap.put("frgroupno",frgroupno); //取题目动态表格关联字段值存入exams表
     updatemap.put("examdtl","dytab-"+dytablename);//取题目动态表格关联表表名存入exams表
     examsService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/exams/examscx.do";
     }
     //examsxg.jsp exams 请求处理结束

     //topviexamsxg exams 请求初始化开始
     @RequestMapping("/topviexamsxg")
     public String topviexamsxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = examsService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     modelMap.put("spage", updatemap.get("spage")); //将数据ID发送到前端
     return "/admin/pviexamsxg.jsp";
     }
     //topviexamsxg exams 请求初始化结束

     //pviexamsxg.jsp exams 请求处理开始
     @RequestMapping("/pviexamsxg")
     public String pviexamsxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("笔试管理，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = examsService.findByID(id); //取数据实体类
     examsService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/exams/pviexamscx.do";
     }
     //pviexamsxg.jsp exams 请求处理结束

      //examscx.jsp exams 请求处理开始
      @RequestMapping("/examscx")
      public String examscx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/exams/examscx",selectmap) ;  //得取分页地址
      List<HashMap> list = examsService.findByParamWithPages("examscx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("selectmap", selectmap); //查询条件发到前端
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      session.setAttribute("tobackurl", "/itivtmgr/exams/examscx.do"); //列表路径存到session，返回时要用到
      return "/admin/examscx.jsp";
      }
      //examscx.jsp exams 请求处理结束

      //fshexamscx.jsp exams 请求处理开始
      @RequestMapping("/fshexamscx")
      public String fshexamscx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/exams/fshexamscx",selectmap) ;  //得取分页地址
      List<HashMap> list = examsService.findByParamWithPages("fshexamscx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("selectmap", selectmap); //查询条件发到前端
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      session.setAttribute("tobackurl", "/itivtmgr/exams/fshexamscx.do"); //列表路径存到session，返回时要用到
      return "/admin/fshexamscx.jsp";
      }
      //fshexamscx.jsp exams 请求处理结束

      //tofshexamsxg exams 请求初始化开始
     @RequestMapping("/tofshexamsxg")
     public String tofshexamsxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = examsService.findByID(id); //取数据实体类
         String frgroupno = smap.get("frgroupno").toString();
         HashMap paramap = new HashMap<String, String>();
         paramap.put("frgroupno",frgroupno);
         List<HashMap<String, String>> hlist = examdtlService.findByParam(paramap);
         modelMap.put("hlist", hlist); //将数据实体类发送到前端
         modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/fshexamsxg.jsp";
     }
     //tofshexamsxg exams 请求初始化结束

      //fshexamsxg.jsp exams 请求处理开始
     @RequestMapping("/fshexamsxg")
     public String fshexamsxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("笔试管理，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = examsService.findByID(id); //取数据实体类
         String frgroupno = smap.get("frgroupno").toString();
         HashMap paramap = new HashMap<String, String>();
         paramap.put("frgroupno",frgroupno);
         List<HashMap<String, String>> hlist = examdtlService.findByParam(paramap);
         for (HashMap<String, String> m : hlist) {
             String ititle = m.get("ititle")+"";
             String answer = request.getParameter("answer"+ititle)==null?"":request.getParameter("answer"+ititle);
             new CommDAO().commOper("update examdtl set canswer='"+answer+"' where ititle='"+ititle+"' and frgroupno='"+frgroupno+"'");
         }
//     examsService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/exams/fshexamscx.do";
     }
     //fshexamsxg.jsp exams 请求处理结束

      //tochargestj charges 请求初始化开始
      @RequestMapping("/tochargestj")
      public String tochargestj(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      return "/admin/chargestj.jsp";
      }
      //tochargestj charges 请求初始化结束

      //chargestj.jsp charges 请求处理开始
      @RequestMapping("/chargestj")
      public String chargestj(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("付费服务，信息添加",Info.getUser(request)); //记操作日志
      String uname = Info.getUser(request).get("uname").toString();
      insertmap.put("savetime",Info.getDateStr());
      insertmap.put("uname",uname);
      chargesService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/charges/chargescx.do";
      }
      //chargestj.jsp charges 请求处理结束

     //tochargesxg charges 请求初始化开始
     @RequestMapping("/tochargesxg")
     public String tochargesxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = chargesService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/chargesxg.jsp";
     }
     //tochargesxg charges 请求初始化结束

     //chargesxg.jsp charges 请求处理开始
     @RequestMapping("/chargesxg")
     public String chargesxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("付费服务，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = chargesService.findByID(id); //取数据实体类
     chargesService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/charges/chargescx.do";
     }
     //chargesxg.jsp charges 请求处理结束

     //tofshchargesxg charges 请求初始化开始
     @RequestMapping("/tofshchargesxg")
     public String tofshchargesxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = chargesService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     return "/admin/fshchargesxg.jsp";
     }
     //tofshchargesxg charges 请求初始化结束

     //fshchargesxg.jsp charges 请求处理开始
     @RequestMapping("/fshchargesxg")
     public String fshchargesxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("付费服务，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = chargesService.findByID(id); //取数据实体类
     chargesService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/charges/fshchargescx.do";
     }
     //fshchargesxg.jsp charges 请求处理结束

     //topvichargesxg charges 请求初始化开始
     @RequestMapping("/topvichargesxg")
     public String topvichargesxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID
     HashMap smap = chargesService.findByID(id); //取数据实体类
     modelMap.put("updateentity", smap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码
     modelMap.put("id", id); //将数据ID发送到前端
     modelMap.put("spage", updatemap.get("spage")); //将数据ID发送到前端
     return "/admin/pvichargesxg.jsp";
     }
     //topvichargesxg charges 请求初始化结束

     //pvichargesxg.jsp charges 请求处理开始
     @RequestMapping("/pvichargesxg")
     public String pvichargesxg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息
     if(user!=null)systemlogService.log("付费服务，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID
     HashMap smap = chargesService.findByID(id); //取数据实体类
     chargesService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/charges/pvichargescx.do";
     }
     //pvichargesxg.jsp charges 请求处理结束

      //chargescx.jsp charges 请求处理开始
      @RequestMapping("/chargescx")
      public String chargescx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/charges/chargescx",selectmap) ;  //得取分页地址
      List<HashMap> list = chargesService.findByParamWithPages("chargescx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("selectmap", selectmap); //查询条件发到前端
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      session.setAttribute("tobackurl", "/itivtmgr/charges/chargescx.do"); //列表路径存到session，返回时要用到
      return "/admin/chargescx.jsp";
      }
      //chargescx.jsp charges 请求处理结束

      //fshchargescx.jsp charges 请求处理开始
      @RequestMapping("/fshchargescx")
      public String fshchargescx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉
      String url = Info.getPageUrl("/charges/fshchargescx",selectmap) ;  //得取分页地址
      List<HashMap> list = chargesService.findByParamWithPages("fshchargescx", selectmap, PageManager.defPageSize, url, request); //分页查询数据
      modelMap.put("selectmap", selectmap); //查询条件发到前端
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码
      modelMap.put("slist", list); //列表发送前端
      session.setAttribute("tobackurl", "/itivtmgr/charges/fshchargescx.do"); //列表路径存到session，返回时要用到
      return "/admin/fshchargescx.jsp";
      }
      //fshchargescx.jsp charges 请求处理结束

      //toztypetj ztype 请求初始化开始
      @RequestMapping("/toztypetj")
      public String toztypetj(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      return "/admin/ztypetj.jsp";
      }
      //toztypetj ztype 请求初始化结束

      //ztypetj.jsp ztype 请求处理开始
      @RequestMapping("/ztypetj")
      public String ztypetj(@RequestParam HashMap<String, String> insertmap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      HashMap<String,String> user = (HashMap<String,String>)session.getAttribute("admin");
      if(user!=null)systemlogService.log("专业领域，信息添加",Info.getUser(request)); //记操作日志
      ztypeService.insert(insertmap);
      session.setAttribute("suc", "suc");
      return "/ztype/ztypecx.do";
      }
      //ztypetj.jsp ztype 请求处理结束

     //toztypexg ztype 请求初始化开始
     @RequestMapping("/toztypexg")
     public String toztypexg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String id = updatemap.get("id"); //取数据ID 
     HashMap smap = ztypeService.findByID(id); //取数据实体类 
     modelMap.put("updateentity", smap); //将数据实体类发送到前端 
     modelMap.put("fillForm", Info.tform(smap)); //生成填充表单的JS代码 
     modelMap.put("id", id); //将数据ID发送到前端 
     return "/admin/ztypexg.jsp";
     }
     //toztypexg ztype 请求初始化结束

     //ztypexg.jsp ztype 请求处理开始
     @RequestMapping("/ztypexg")
     public String ztypexg(@RequestParam HashMap<String, String> updatemap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     HashMap<String,String> user = (HashMap<String,String>)Info.getUser(request); //取当前登录人信息 
     if(user!=null)systemlogService.log("专业领域，信息修改",Info.getUser(request)); //记操作日志
     String id =  updatemap.get("id"); //取数据ID 
     HashMap smap = ztypeService.findByID(id); //取数据实体类 
     ztypeService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/ztype/ztypecx.do";
     }
     //ztypexg.jsp ztype 请求处理结束

      //ztypecx.jsp ztype 请求处理开始
      @RequestMapping("/ztypecx")
      public String ztypecx(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      clearSelectMap(selectmap,session); //如果不是从查询界面跳转过来的，要把查询条件去掉 
      String url = Info.getPageUrl("/ztype/ztypecx",selectmap) ;  //得取分页地址 
      List<HashMap> list = ztypeService.findByParamWithPages("ztypecx", selectmap, PageManager.defPageSize, url, request); //分页查询数据 
      modelMap.put("selectmap", selectmap); //查询条件发到前端 
      modelMap.put("fillForm", Info.tform(selectmap)); //填充前台表单JS代码 
      modelMap.put("slist", list); //列表发送前端 
      session.setAttribute("tobackurl", "/itivtmgr/ztype/ztypecx.do"); //列表路径存到session，返回时要用到 
      return "/admin/ztypecx.jsp";
      }
      //ztypecx.jsp ztype 请求处理结束

//--业务层代码块结束--

	public void go(String url, HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gor(String url, HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
