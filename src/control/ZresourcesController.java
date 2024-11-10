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
@RequestMapping("/zresources")
public class ZresourcesController {

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

      //删除记录功能开始 
      @RequestMapping("/delete") 
      public String delete(@RequestParam String[] plids,@RequestParam HashMap<String, String> deletemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{ 
      if(StringUtils.isNotEmpty(deletemap.get("plids"))) { 
          zresourcesService.delete(deletemap.get("plids").toString()); 
      }
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
           zresourcesService.delete(id); 
          }
       }
      if(StringUtils.isEmpty(deletemap.get("tablename"))) { 
         return "/zresources/"+deletemap.get("surl")+".do"; 
      }else { 
         return "/"+deletemap.get("tablename")+"/"+deletemap.get("surl")+".do"; 
      }
      }
      //删除记录功能结束 

      //单字段修改功能开始 
      @RequestMapping("/customUpdate") 
      public String customUpdate(@RequestParam String[] plids,@RequestParam HashMap<String, String> customUpdateMap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{ 
      if(StringUtils.isNotEmpty(customUpdateMap.get("id"))) { 
          zresourcesService.update(customUpdateMap); 
      } 
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
             HashMap<String, String> map = new HashMap<>();
             map.put(customUpdateMap.get("col"),customUpdateMap.get("zdycol"));
             map.put("id",id);
            zresourcesService.update(map); 
          }
       }
      session.setAttribute("updatesuc", "updatesuc"); 
      return "/zresources/"+customUpdateMap.get("surl")+".do"; 
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
