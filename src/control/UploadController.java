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
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
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
import util.Info;
import util.PageManager;
import util.StrUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

@Controller
@RequestMapping("/upload")
public class UploadController {
    
	@Autowired 
    public SysuserService sysuserService; 
	
	@RequestMapping("/uploaddoc")
	public void uploaddoc(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
	 try {
		String filename="";
		request.setCharacterEncoding("utf-8");
		RequestContext  requestContext = new ServletRequestContext(request);
		if(FileUpload.isMultipartContent(requestContext)){
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(request.getRealPath("/upfile/")+"/"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(100*1024*1024);
		List items = new ArrayList();
		
		items = upload.parseRequest(request);
		
		FileItem fileItem = (FileItem) items.get(0);
		if(fileItem.getName()!=null && fileItem.getSize()!=0)
		{
		if(fileItem.getName()!=null && fileItem.getSize()!=0){
		File fullFile = new File(fileItem.getName());
		filename = Info.generalFileName(fullFile.getName());
		File newFile = new File(request.getRealPath("/upfile/")+"/" + filename);
		try {
		fileItem.write(newFile);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}else{
		}
		}
		}
		
		gor("/itivtmgr/js/uploaddoc.jsp?docname="+filename, request, response);
		} catch (Exception e1) {
		e1.printStackTrace();
		}
	}
	
	@RequestMapping("/uploaddocfsh")
	public void uploaddocfsh(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
	 try {
		String filename="";
		request.setCharacterEncoding("utf-8");
		RequestContext  requestContext = new ServletRequestContext(request);
		if(FileUpload.isMultipartContent(requestContext)){
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(request.getRealPath("/upfile/")+"/"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(100*1024*1024);
		List items = new ArrayList();
		
		items = upload.parseRequest(request);
		
		FileItem fileItem = (FileItem) items.get(0);
		if(fileItem.getName()!=null && fileItem.getSize()!=0)
		{
		if(fileItem.getName()!=null && fileItem.getSize()!=0){
		File fullFile = new File(fileItem.getName());
		filename = Info.generalFileName(fullFile.getName());
		File newFile = new File(request.getRealPath("/upfile/")+"/" + filename);
		try {
		fileItem.write(newFile);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}else{
		}
		}
		}
		
		gor("/itivtmgr/js/uploaddocfsh.jsp?docname="+filename, request, response);
		} catch (Exception e1) {
		e1.printStackTrace();
		}
	}
	
	@RequestMapping("/uploaddoc2")
	public void uploaddoc2(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
	    try {
		String filename="";
		request.setCharacterEncoding("utf-8");
		RequestContext  requestContext = new ServletRequestContext(request);
		if(FileUpload.isMultipartContent(requestContext)){
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(request.getRealPath("/upfile/")+"/"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(100*1024*1024);
		List items = new ArrayList();
		
		items = upload.parseRequest(request);
		
		FileItem fileItem = (FileItem) items.get(0);
		if(fileItem.getName()!=null && fileItem.getSize()!=0)
		{
		if(fileItem.getName()!=null && fileItem.getSize()!=0){
		File fullFile = new File(fileItem.getName());
		filename = Info.generalFileName(fullFile.getName());
		File newFile = new File(request.getRealPath("/upfile/")+"/" + filename);
		try {
		fileItem.write(newFile);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}else{
		}
		}
		}
		
		gor("/itivtmgr/js/uploaddoc2.jsp?docname="+filename, request, response);
		} catch (Exception e1) {
		e1.printStackTrace();
		}
	}
	
	@RequestMapping("/uploaddoc3")
	public void uploaddoc3(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
	    try {
		String filename="";
		request.setCharacterEncoding("utf-8");
		RequestContext  requestContext = new ServletRequestContext(request);
		if(FileUpload.isMultipartContent(requestContext)){
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(request.getRealPath("/upfile/")+"/"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(100*1024*1024);
		List items = new ArrayList();
		
		items = upload.parseRequest(request);
		
		FileItem fileItem = (FileItem) items.get(0);
		if(fileItem.getName()!=null && fileItem.getSize()!=0)
		{
		if(fileItem.getName()!=null && fileItem.getSize()!=0){
		File fullFile = new File(fileItem.getName());
		filename = Info.generalFileName(fullFile.getName());
		File newFile = new File(request.getRealPath("/upfile/")+"/" + filename);
		try {
		fileItem.write(newFile);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}else{
		}
		}
		}
		
		gor("/itivtmgr/js/uploaddoc3.jsp?docname="+filename, request, response);
		} catch (Exception e1) {
		e1.printStackTrace();
		}
	}
	
	
	@RequestMapping("/importexcel")
	public void importexcel(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		String page = request.getParameter("page");
		String whzdstr = request.getParameter("whzdstr");
		String tablename = request.getParameter("tablename");
		
		try {
		String filename="";
		request.setCharacterEncoding("utf-8");
		RequestContext  requestContext = new ServletRequestContext(request);
		if(FileUpload.isMultipartContent(requestContext)){
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(request.getRealPath("/upfile/")+"/"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(100*1024*1024);
		List items = new ArrayList();
		
		items = upload.parseRequest(request);
		
		FileItem fileItem = (FileItem) items.get(0);
		if(fileItem.getName()!=null && fileItem.getSize()!=0)
		{
		if(fileItem.getName()!=null && fileItem.getSize()!=0){
		File fullFile = new File(fileItem.getName());
		filename = Info.generalFileName(fullFile.getName());
		File newFile = new File(request.getRealPath("/upfile/")+"/" + filename);
		try {
		fileItem.write(newFile);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}else{
		}
		}
		
		if(filename.indexOf(".xls")>-1)
		{
		Workbook workbook;
		try {
			System.out.println(request.getRealPath("/upfile/")+"/"+filename);
		workbook = Workbook.getWorkbook(new File(request.getRealPath("/upfile/")+"/"+filename));
		//通过Workbook的getSheet方法选择第一个工作簿（从0开始）
		Sheet sheet = workbook.getSheet(0); 
		//通过Sheet方法的getCell方法选择位置为C2的单元格（两个参数都从0开始）
		//int empty = 0;
		for(int i=1;i<1000;i++)
		{
		Cell cell = null;
		HashMap<String, String> insertmap = new HashMap<String, String>();
		try{
		String isql = "insert into "+tablename+"(";
		String[] cols = new String[100];
		int x = 0;
		for(String str:whzdstr.split("-"))
		{ 
		isql+=str+",";
		cols[x]=str;
		x++;
		}
		isql = isql.substring(0,isql.length()-1);
		isql+=")values(";
		
		int j=0;
		int empty = 1;
		for(String str:whzdstr.split("-"))
		{
		cell = sheet.getCell(j,i); 
		isql+="'"+cell.getContents()+"',";
		String content = cell.getContents()==null?"":cell.getContents();
		if(!"".equals(content.trim()))
		{
		empty = 0;
		}
		insertmap.put(cols[j], cell.getContents());
		j++;
		}
		if(empty==1)continue;
		isql = isql.substring(0,isql.length()-1);
		isql+=")";
		sysuserService.commInsert(tablename, insertmap);
		}catch (Exception e) {
		continue;
		}
		
		} 
		workbook.close(); 
		} catch (Exception e) {
		e.printStackTrace();
		} 
		}
		}
		go("/admin/"+page+"?docname="+filename, request, response);
		} catch (Exception e1) {
		e1.printStackTrace();
		}
	}
	
	@RequestMapping("/uploadimg")
	public void uploadimg(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		try {
		String filename="";
		request.setCharacterEncoding("utf-8");
		RequestContext  requestContext = new ServletRequestContext(request);
		if(FileUpload.isMultipartContent(requestContext)){
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(request.getRealPath("/upfile/")+"/"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(100*1024*1024);
		List items = new ArrayList();
		
		items = upload.parseRequest(request);
		
		FileItem fileItem = (FileItem) items.get(0);
		if(fileItem.getName()!=null && fileItem.getSize()!=0)
		{
		if(fileItem.getName()!=null && fileItem.getSize()!=0){
		File fullFile = new File(fileItem.getName());
		filename = Info.generalFileName(fullFile.getName());
		File newFile = new File(request.getRealPath("/upfile/")+"/" + filename);
		try {
		fileItem.write(newFile);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}else{
		}
		}
		}
		
		go("/js/uploadimg.jsp?filename="+filename, request, response);
		} catch (Exception e1) {
		e1.printStackTrace();
		}
	}
	
	@RequestMapping("/uploadimg2")
	public void uploadimg2(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		try {
		String filename="";
		request.setCharacterEncoding("utf-8");
		RequestContext  requestContext = new ServletRequestContext(request);
		if(FileUpload.isMultipartContent(requestContext)){
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(request.getRealPath("/upfile/")+"/"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(100*1024*1024);
		List items = new ArrayList();
		
		items = upload.parseRequest(request);
		
		FileItem fileItem = (FileItem) items.get(0);
		if(fileItem.getName()!=null && fileItem.getSize()!=0)
		{
		if(fileItem.getName()!=null && fileItem.getSize()!=0){
		File fullFile = new File(fileItem.getName());
		filename = Info.generalFileName(fullFile.getName());
		File newFile = new File(request.getRealPath("/upfile/")+"/" + filename);
		try {
		fileItem.write(newFile);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}else{
		}
		}
		}
		
		gor("/itivtmgr/js/uploadimg2.jsp?filename="+filename, request, response);
		} catch (Exception e1) {
		e1.printStackTrace();
		}
	}
	
	@RequestMapping("/uploadimg3")
	public void uploadimg3(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		try {
		String filename="";
		request.setCharacterEncoding("utf-8");
		RequestContext  requestContext = new ServletRequestContext(request);
		if(FileUpload.isMultipartContent(requestContext)){
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(request.getRealPath("/upfile/")+"/"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(100*1024*1024);
		List items = new ArrayList();
		
		items = upload.parseRequest(request);
		
		FileItem fileItem = (FileItem) items.get(0);
		if(fileItem.getName()!=null && fileItem.getSize()!=0)
		{
		if(fileItem.getName()!=null && fileItem.getSize()!=0){
		File fullFile = new File(fileItem.getName());
		filename = Info.generalFileName(fullFile.getName());
		File newFile = new File(request.getRealPath("/upfile/")+"/" + filename);
		try {
		fileItem.write(newFile);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}else{
		}
		}
		}
		
		go("/itivtmgr/js/uploadimg3.jsp?filename="+filename, request, response);
		} catch (Exception e1) {
		e1.printStackTrace();
		}
	}
	
	@RequestMapping("/uploadimg4")
	public void uploadimg4(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		try {
		String filename="";
		request.setCharacterEncoding("utf-8");
		RequestContext  requestContext = new ServletRequestContext(request);
		if(FileUpload.isMultipartContent(requestContext)){
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(request.getRealPath("/upfile/")+"/"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(100*1024*1024);
		List items = new ArrayList();
		
		items = upload.parseRequest(request);
		
		FileItem fileItem = (FileItem) items.get(0);
		if(fileItem.getName()!=null && fileItem.getSize()!=0)
		{
		if(fileItem.getName()!=null && fileItem.getSize()!=0){
		File fullFile = new File(fileItem.getName());
		filename = Info.generalFileName(fullFile.getName());
		File newFile = new File(request.getRealPath("/upfile/")+"/" + filename);
		try {
		fileItem.write(newFile);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}else{
		}
		}
		}
		
		go("/itivtmgr/js/uploadimg4.jsp?filename="+filename, request, response);
		} catch (Exception e1) {
		e1.printStackTrace();
		}
	}
	
	@RequestMapping("/uploadimg5")
	public void uploadimg5(@RequestParam HashMap<String, String> selectmap,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		try {
		String filename="";
		request.setCharacterEncoding("utf-8");
		RequestContext  requestContext = new ServletRequestContext(request);
		if(FileUpload.isMultipartContent(requestContext)){
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(request.getRealPath("/upfile/")+"/"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(100*1024*1024);
		List items = new ArrayList();
		
		items = upload.parseRequest(request);
		
		FileItem fileItem = (FileItem) items.get(0);
		if(fileItem.getName()!=null && fileItem.getSize()!=0)
		{
		if(fileItem.getName()!=null && fileItem.getSize()!=0){
		File fullFile = new File(fileItem.getName());
		filename = Info.generalFileName(fullFile.getName());
		File newFile = new File(request.getRealPath("/upfile/")+"/" + filename);
		try {
		fileItem.write(newFile);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}else{
		}
		}
		}
		
		go("/itivtmgr/js/uploadimg5.jsp?filename="+filename, request, response);
		} catch (Exception e1) {
		e1.printStackTrace();
		}
	} 


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
