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
@RequestMapping("/charges")
public class ChargesController {

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

      //删除记录功能开始 
      @RequestMapping("/delete") 
      public String delete(@RequestParam String[] plids,@RequestParam HashMap<String, String> deletemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{ 
      if(StringUtils.isNotEmpty(deletemap.get("plids"))) { 
          chargesService.delete(deletemap.get("plids").toString()); 
      }
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
           chargesService.delete(id); 
          }
       }
      if(StringUtils.isEmpty(deletemap.get("tablename"))) { 
         return "/charges/"+deletemap.get("surl")+".do"; 
      }else { 
         return "/"+deletemap.get("tablename")+"/"+deletemap.get("surl")+".do"; 
      }
      }
      //删除记录功能结束 

      //单字段修改功能开始 
      @RequestMapping("/customUpdate") 
      public String customUpdate(@RequestParam String[] plids,@RequestParam HashMap<String, String> customUpdateMap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{ 
      if(StringUtils.isNotEmpty(customUpdateMap.get("id"))) { 
          chargesService.update(customUpdateMap); 
      } 
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
             HashMap<String, String> map = new HashMap<>();
             map.put(customUpdateMap.get("col"),customUpdateMap.get("zdycol"));
             map.put("id",id);
            chargesService.update(map); 
          }
       }
      session.setAttribute("updatesuc", "updatesuc"); 
      return "/charges/"+customUpdateMap.get("surl")+".do"; 
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
