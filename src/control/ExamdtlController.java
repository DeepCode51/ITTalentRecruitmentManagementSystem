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
@RequestMapping("/examdtl")
public class ExamdtlController {

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

      //dytab_examdtl请求初始化开始
      @RequestMapping("/dytab_examdtl")
      public String dytab_examdtl(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      return "/admin/dytab_examdtl.jsp";
      }
      //dytab_examdtl请求初始化结束

      //删除记录功能开始 
      @RequestMapping("/delete") 
      public String delete(@RequestParam String[] plids,@RequestParam HashMap<String, String> deletemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{ 
      if(StringUtils.isNotEmpty(deletemap.get("plids"))) { 
          examdtlService.delete(deletemap.get("plids").toString()); 
      }
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
           examdtlService.delete(id); 
          }
       }
      if(StringUtils.isEmpty(deletemap.get("tablename"))) { 
         return "/examdtl/"+deletemap.get("surl")+".do"; 
      }else { 
         return "/"+deletemap.get("tablename")+"/"+deletemap.get("surl")+".do"; 
      }
      }
      //删除记录功能结束 

      //单字段修改功能开始 
      @RequestMapping("/customUpdate") 
      public String customUpdate(@RequestParam String[] plids,@RequestParam HashMap<String, String> customUpdateMap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{ 
      if(StringUtils.isNotEmpty(customUpdateMap.get("id"))) { 
          examdtlService.update(customUpdateMap); 
      } 
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
             HashMap<String, String> map = new HashMap<>();
             map.put(customUpdateMap.get("col"),customUpdateMap.get("zdycol"));
             map.put("id",id);
            examdtlService.update(map); 
          }
       }
      session.setAttribute("updatesuc", "updatesuc"); 
      return "/examdtl/"+customUpdateMap.get("surl")+".do"; 
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
