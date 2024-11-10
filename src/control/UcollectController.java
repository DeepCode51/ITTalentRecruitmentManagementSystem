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
@RequestMapping("/ucollect")
public class UcollectController {

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

      //删除记录功能开始 
      @RequestMapping("/delete") 
      public String delete(@RequestParam String[] plids,@RequestParam HashMap<String, String> deletemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{ 
      if(StringUtils.isNotEmpty(deletemap.get("plids"))) { 
          ucollectService.delete(deletemap.get("plids").toString()); 
      }
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
           ucollectService.delete(id); 
          }
       }
      if(StringUtils.isEmpty(deletemap.get("tablename"))) { 
         return "/ucollect/"+deletemap.get("surl")+".do"; 
      }else { 
         return "/"+deletemap.get("tablename")+"/"+deletemap.get("surl")+".do"; 
      }
      }
      //删除记录功能结束 

      //单字段修改功能开始 
      @RequestMapping("/customUpdate") 
      public String customUpdate(@RequestParam String[] plids,@RequestParam HashMap<String, String> customUpdateMap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{ 
      if(StringUtils.isNotEmpty(customUpdateMap.get("id"))) { 
          ucollectService.update(customUpdateMap); 
      } 
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
             HashMap<String, String> map = new HashMap<>();
             map.put(customUpdateMap.get("col"),customUpdateMap.get("zdycol"));
             map.put("id",id);
            ucollectService.update(map); 
          }
       }
      session.setAttribute("updatesuc", "updatesuc"); 
      return "/ucollect/"+customUpdateMap.get("surl")+".do"; 
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
