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
@RequestMapping("/recruiting")
public class RecruitingController {

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

      //删除记录功能开始 
      @RequestMapping("/delete") 
      public String delete(@RequestParam String[] plids,@RequestParam HashMap<String, String> deletemap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{ 
      if(StringUtils.isNotEmpty(deletemap.get("plids"))) { 
          recruitingService.delete(deletemap.get("plids").toString()); 
      }
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
           recruitingService.delete(id); 
          }
       }
      if(StringUtils.isEmpty(deletemap.get("tablename"))) { 
         return "/recruiting/"+deletemap.get("surl")+".do"; 
      }else { 
         return "/"+deletemap.get("tablename")+"/"+deletemap.get("surl")+".do"; 
      }
      }
      //删除记录功能结束 

      //单字段修改功能开始 
      @RequestMapping("/customUpdate") 
      public String customUpdate(@RequestParam String[] plids,@RequestParam HashMap<String, String> customUpdateMap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{ 
      if(StringUtils.isNotEmpty(customUpdateMap.get("id"))) { 
          recruitingService.update(customUpdateMap); 
      } 
       if(!ArrayUtils.isEmpty(plids)){
          for(String id:plids){
             HashMap<String, String> map = new HashMap<>();
             map.put(customUpdateMap.get("col"),customUpdateMap.get("zdycol"));
             map.put("id",id);
            recruitingService.update(map); 
          }
       }
      session.setAttribute("updatesuc", "updatesuc"); 
      return "/recruiting/"+customUpdateMap.get("surl")+".do"; 
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
