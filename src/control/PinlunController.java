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
@RequestMapping("/pinlun")
public class PinlunController {

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

   //处理查询条件开始
     public void clearSelectMap(HashMap<String, String> selectmap,HttpSession session)throws Exception{
     if(session.getAttribute("suc")!=null)selectmap.clear() ;  //如果是从新增或修改界面过来，要清空查询条件
     if(session.getAttribute("updatesuc")!=null){ //如果是从单字段修改功能过来，要清空查询条件
     selectmap.clear() ;
     session.removeAttribute("updatesuc") ;
     }
     }
     //处理查询条件结束

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

      //删除记录功能开始 
      @RequestMapping("/delete") 
      public String delete(@RequestParam String[] plids,@RequestParam HashMap<String, String> deletemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{ 
      if(StringUtils.isNotEmpty(deletemap.get("plids"))) { 
          pinlunService.delete(deletemap.get("plids").toString()); 
      }
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
           pinlunService.delete(id); 
          }
       }
      if(StringUtils.isEmpty(deletemap.get("tablename"))) { 
         return "/pinlun/"+deletemap.get("surl")+".do"; 
      }else { 
         return "/"+deletemap.get("tablename")+"/"+deletemap.get("surl")+".do"; 
      }
      }
      //删除记录功能结束 

      //单字段修改功能开始 
      @RequestMapping("/customUpdate") 
      public String customUpdate(@RequestParam String[] plids,@RequestParam HashMap<String, String> customUpdateMap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{ 
      if(StringUtils.isNotEmpty(customUpdateMap.get("id"))) { 
          pinlunService.update(customUpdateMap); 
      } 
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
             HashMap<String, String> map = new HashMap<>();
             map.put(customUpdateMap.get("col"),customUpdateMap.get("zdycol"));
             map.put("id",id);
            pinlunService.update(map); 
          }
       }
      session.setAttribute("updatesuc", "updatesuc"); 
      return "/pinlun/"+customUpdateMap.get("surl")+".do"; 
      }
      //单字段修改功能结束

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
