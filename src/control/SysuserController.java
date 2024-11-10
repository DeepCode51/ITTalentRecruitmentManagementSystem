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
@RequestMapping("/sysuser")
public class SysuserController {

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

   //处理查询条件开始
     public void clearSelectMap(HashMap<String, String> selectmap,HttpSession session)throws Exception{
     if(session.getAttribute("suc")!=null)selectmap.clear() ;  //如果是从新增或修改界面过来，要清空查询条件
     if(session.getAttribute("updatesuc")!=null){ //如果是从单字段修改功能过来，要清空查询条件
     selectmap.clear() ;
     session.removeAttribute("updatesuc") ;
     }
     }
     //处理查询条件结束

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

     //topvisysuserxg sysuser 请求初始化开始 -todo
     @RequestMapping("/topvisysuserxg")
     public String topvisysuserxg(@RequestParam HashMap<String, String> updatemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
     String uname = request.getParameter("uname");
         //根据uname取出用户的hashmap
         HashMap usermap = new HashMap();
         HashMap<String,String> parammap = new HashMap<String,String>();
         parammap.put("uname",uname);
         List userlist = sysuserService.findByParam(parammap);
         if(CollectionUtils.isNotEmpty(userlist)){
             usermap = (HashMap) userlist.get(0);
         }
     modelMap.put("updateentity", usermap); //将数据实体类发送到前端
     modelMap.put("fillForm", Info.tform(usermap)); //生成填充表单的JS代码
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

      //删除记录功能开始 
      @RequestMapping("/delete") 
      public String delete(@RequestParam String[] plids,@RequestParam HashMap<String, String> deletemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{ 
      if(StringUtils.isNotEmpty(deletemap.get("plids"))) { 
          sysuserService.delete(deletemap.get("plids").toString()); 
      }
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
           sysuserService.delete(id); 
          }
       }
      if(StringUtils.isEmpty(deletemap.get("tablename"))) { 
         return "/sysuser/"+deletemap.get("surl")+".do"; 
      }else { 
         return "/"+deletemap.get("tablename")+"/"+deletemap.get("surl")+".do"; 
      }
      }
      //删除记录功能结束 

      //单字段修改功能开始 
      @RequestMapping("/customUpdate") 
      public String customUpdate(@RequestParam String[] plids,@RequestParam HashMap<String, String> customUpdateMap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{ 
      if(StringUtils.isNotEmpty(customUpdateMap.get("id"))) { 
          sysuserService.update(customUpdateMap); 
      } 
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
             HashMap<String, String> map = new HashMap<>();
             map.put(customUpdateMap.get("col"),customUpdateMap.get("zdycol"));
             map.put("id",id);
            sysuserService.update(map); 
          }
       }
      session.setAttribute("updatesuc", "updatesuc"); 
      return "/sysuser/"+customUpdateMap.get("surl")+".do"; 
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
