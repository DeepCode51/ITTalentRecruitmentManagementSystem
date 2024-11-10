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
@RequestMapping("/exams")
public class ExamsController {

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
         new CommDAO().commOper("update exams set grade=100/(select count(*) from examdtl where frgroupno='"+frgroupno+"')*(select count(*) from examdtl where banswer=canswer and frgroupno='"+frgroupno+"') where frgroupno='"+frgroupno+"'");
//     examsService.update(updatemap);//调用修改服务，修改数据
     request.getSession().setAttribute("suc", "suc");
     return "/exams/fshexamscx.do";
     }
     //fshexamsxg.jsp exams 请求处理结束

      //删除记录功能开始
      @RequestMapping("/delete")
      public String delete(@RequestParam String[] plids,@RequestParam HashMap<String, String> deletemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      if(StringUtils.isNotEmpty(deletemap.get("plids"))) {
          examsService.delete(deletemap.get("plids").toString());
      }
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
           examsService.delete(id);
          }
       }
      if(StringUtils.isEmpty(deletemap.get("tablename"))) {
         return "/exams/"+deletemap.get("surl")+".do";
      }else {
         return "/"+deletemap.get("tablename")+"/"+deletemap.get("surl")+".do";
      }
      }
      //删除记录功能结束

      //单字段修改功能开始
      @RequestMapping("/customUpdate")
      public String customUpdate(@RequestParam String[] plids,@RequestParam HashMap<String, String> customUpdateMap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
      if(StringUtils.isNotEmpty(customUpdateMap.get("id"))) {
          examsService.update(customUpdateMap);
      }
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
             HashMap<String, String> map = new HashMap<>();
             map.put(customUpdateMap.get("col"),customUpdateMap.get("zdycol"));
             map.put("id",id);
            examsService.update(map);
          }
       }
      session.setAttribute("updatesuc", "updatesuc");
      return "/exams/"+customUpdateMap.get("surl")+".do";
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
