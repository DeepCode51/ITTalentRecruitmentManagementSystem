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
@RequestMapping("/jobapply")
public class JobapplyController {

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

      

      

     

     

      //删除记录功能开始 
      @RequestMapping("/delete") 
      public String delete(@RequestParam String[] plids,@RequestParam HashMap<String, String> deletemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{ 
      if(StringUtils.isNotEmpty(deletemap.get("plids"))) { 
          jobapplyService.delete(deletemap.get("plids").toString()); 
      }
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
           jobapplyService.delete(id); 
          }
       }
      if(StringUtils.isEmpty(deletemap.get("tablename"))) { 
         return "/jobapply/"+deletemap.get("surl")+".do"; 
      }else { 
         return "/"+deletemap.get("tablename")+"/"+deletemap.get("surl")+".do"; 
      }
      }
      //删除记录功能结束 

      //单字段修改功能开始 
      @RequestMapping("/customUpdate") 
      public String customUpdate(@RequestParam String[] plids,@RequestParam HashMap<String, String> customUpdateMap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{ 
      if(StringUtils.isNotEmpty(customUpdateMap.get("id"))) { 
          jobapplyService.update(customUpdateMap); 
      } 
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
             HashMap<String, String> map = new HashMap<>();
             map.put(customUpdateMap.get("col"),customUpdateMap.get("zdycol"));
             map.put("id",id);
            jobapplyService.update(map); 
          }
       }
      session.setAttribute("updatesuc", "updatesuc"); 
      return "/jobapply/"+customUpdateMap.get("surl")+".do"; 
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
