package util;
 
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.management.relation.RoleList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.displaytag.util.CollectionUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import dao.CommDAO;
import entity.bean.Sysuser;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

 
 
public class Info {
	 
	//public static String popheight = "alliframe.height=document.body.clientHeight+";
	public static String popheight = "alliframe.style.height=document.body.scrollHeight+"; 
	  
    public static HashMap getUser(HttpServletRequest request)
    {
    	
    	HashMap map = (HashMap)(request.getSession().getAttribute("admin")==null?request.getSession().getAttribute("user"):request.getSession().getAttribute("admin"));
        return map;
    }
    
    

	//给一个list<hashmap>，然后根据map里的某个字段将list分组为 map<list<hashmap>>
	public static Map<String,List<HashMap>> matchListToMap(List<HashMap> list,String key) { 
		Map<String, List<HashMap>> glist = list.stream().collect(Collectors.groupingBy(e -> e.get(key).toString()));
		return glist;
	}


	//以&隔开  饼
	public static String getBCharts(String title , ArrayList<String> list,String id) {
		String values = "";
		int i = 0;
		String num = "";
		String data = "";
		String datas = "";
		for(String str:list)
		{
			data+="'"+str.split("&")[0]+"，"+str.split("&")[1]+"',";
			values+=""+str.split("&")[1]+",";
			num+="oa["+i+"].value+";
			// datas+=" {value:"+str.split("&")[1]+", name:'"+str.split("&")[0]+","+str.split("&")[1]+"'}, \r\n";
			datas+=" {value:"+str.split("&")[1]+", name:'"+str.split("&")[0]+"，"+str.split("&")[1]+"'}, \r\n";
			i++;
		}
		if(num.length()>0)num=num.substring(0,num.length()-1);
		data = data.substring(0,data.length()-1);
		values = values.substring(0,values.length()-1);
		datas = datas.substring(0,datas.length()-3);
		String str = "";
		String colors = "'#AFD8F8','#F6BD0F','#8BBA00','#FF8E46','#008E8E','#6bc0fb','#7fec9d','#fedd8b','#ffa597','#84e4dd','#AFD8F8','#F6BD0F','#8BBA00','#FF8E46','#008E8E','#6bc0fb'";
		str = "<link rel=\"stylesheet\" href=\"/itivtmgr/js/echarts/css/echarts.css\"> \r\n "+
				"<style type=\"text/css\">#courserate"+id+"{ height: 400px; }</style> \r\n "+
				" <div id=\"courseratetitle\" style='display:inline'>"+title+"</div> \r\n" +
				" <div id=\"courserate"+id+"\"></div> \r\n" +
				" <script src=\"/itivtmgr/js/echarts/js/jquery.min.js\"></script> \r\n"+
				" <script src=\"/itivtmgr/js/echarts/js/echarts.min.js\"></script> \r\n"+
				"<script language=javascript>  \r\n" +
				" $(function(){                                                                                                    \r\n"+
				" var courserate"+id+" = echarts.init(document.getElementById('courserate"+id+"'));                                            \r\n"+
				" option = {                                                                                                       \r\n"+
				" tooltip : {                                                                                                      \r\n"+
				" trigger: 'item',    textStyle:{   color:\"white\"  }   ,                                            \r\n"+
				" formatter: \"\"                                                                                                    \r\n"+
				" },                                                                                                               \r\n"+
				" legend: {                                                                                                        \r\n"+
				" orient: 'vertical',                                                                                              \r\n"+
				" right: '0',                                                                                                      \r\n"+
				" y:'middle',                                                                                                      \r\n"+
				" textStyle:{                                                                                                      \r\n"+
				" color:\"#000\"  , fontSize:'15'                                                                                                    \r\n"+
				" },                                                                                                               \r\n"+
				" formatter:function(name){                                                                                        \r\n"+
				" var oa = option.series[0].data;                                                                                  \r\n"+
				" var num = "+num+";         \r\n"+
				" for(var i = 0; i < option.series[0].data.length; i++){                                                           \r\n"+
				" if(name==oa[i].name){                                                                                            \r\n"+
				" return name.split('，')[0] +  ' '+oa[i].value;                                                                                  \r\n"+
				" }                                                                                                                \r\n"+
				" }                                                                                                                \r\n"+
				" },                                                                                                               \r\n"+
				" data: ["+data+"]                                                          \r\n"+
				" },                                                                                                               \r\n"+
				" series : [                                                                                                       \r\n"+
				" {                                                                                                                \r\n"+
				" name: 'FK',   textStyle:{fontSize:'15' ,color:\"#fff\"  },                       \r\n"+
				" type: 'pie',                                                                                                     \r\n"+
				" radius : '45%',                                                                                                  \r\n"+
				" color:["+colors+"],                                             \r\n"+
				" center: ['38%', '50%'],                                                                                          \r\n"+
				" data:[                                                                                                           \r\n"+
				" "+datas+"                                                                                     \r\n"+
				" ],                                                                                                               \r\n"+
				" itemStyle: {                                                                                                     \r\n"+
				" emphasis: {                                                                                                      \r\n"+
				" shadowBlur: 10,                                                                                                  \r\n"+
				" shadowOffsetX: 0,                                                                                                \r\n"+
				" shadowColor: 'rgba(0, 0, 0, 0.5)'                                                                                \r\n"+
				" }                                                                                                                \r\n"+
				" },                                                                                                               \r\n"+
				" itemStyle: {                                                                                                     \r\n"+
				" normal: {                                                                                                        \r\n"+
				" label:{      textStyle:{fontSize:'15' },       \r\n"+
				" show: true,                                                                                                      \r\n"+
				" position:'outside',                                                                                              \r\n"+
				" formatter: '{b}'                                                                                                 \r\n"+
				" }                                                                                                                \r\n"+
				" },                                                                                                               \r\n"+
				" labelLine :{show:true}                                                                                           \r\n"+
				" }                                                                                                                \r\n"+
				" }                                                                                                                \r\n"+
				" ]                                                                                                                \r\n"+
				" };                                                                                                               \r\n"+
				" courserate"+id+".setOption(option);                                                                                    \r\n"+
				" })     </script>                                                   \r\n";
		return str;
	}




	//以&隔开  柱
    public static String getZCharts(String title , ArrayList<String> list,String id) { 
   	 String keys = "";
   	 String values = ""; 
   	 int i = 0;
   	 for(String str:list)
   	 {
   		 keys+="'"+str.split("&")[0]+"',";
   		 values+=""+str.split("&")[1]+","; 
   		 i++;
   	 }  
   	 keys = keys.substring(0,keys.length()-1);
   	 values = values.substring(0,values.length()-1);
   	 String str = "";
   	 String gid = StrUtil.generalSrid();
   	 String colors = "'#AFD8F8','#F6BD0F','#8BBA00','#FF8E46','#008E8E','#6bc0fb','#7fec9d','#fedd8b','#ffa597','#84e4dd','#AFD8F8','#F6BD0F','#8BBA00','#FF8E46','#008E8E','#6bc0fb'";
   	 str = "<link rel=\"stylesheet\" href=\"/itivtmgr/js/echarts/css/echarts.css\"> \n "+
   	 "<style type=\"text/css\">#changedetail"+id+"{ height: 329px; }</style> \n "+
   	 " <div id=\"changedetail"+id+"\" ></div> \n" +
   	 " <script src=\"/itivtmgr/js/echarts/js/jquery.min.js\"></script> \n"+
   	 " <script src=\"/itivtmgr/js/echarts/js/echarts.min.js\"></script> \n"+
   	 "<script language=javascript>  \n" +
   	 " $(function(){                                                                          \n"+    
   	 " /* 柱状图*/                                                                                   \n"+   
   	 " var changedetail"+id+" = echarts.init(document.getElementById('changedetail"+id+"'));                    \n"+   
   	 " option = {                                                                                   \n"+   
   	 " tooltip: {                                                                                   \n"+   
   	 " trigger: 'axis',                                                                             \n"+   
   	 " formatter: '',                                                \n"+   
   	 " textStyle:{                                                                                  \n"+   
   	 " color:\"white\"                                                                                \n"+   
   	 " },                                                                                           \n"+   
   	 " },                                                                                           \n"+   
   	 " toolbox: {                                                                                   \n"+   
   	 " show:false,                                                                                  \n"+   
   	 " feature: {                                                                                   \n"+   
   	 " dataView: {show: true, readOnly: false},                                                     \n"+   
   	 " magicType: {show: true, type: ['line', 'bar']},                                              \n"+   
   	 " restore: {show: true},                                                                       \n"+   
   	 " saveAsImage: {show: true}                                                                    \n"+   
   	 " }                                                                                            \n"+   
   	 " },                                                                                           \n"+   
   	 " legend: {                                                                                    \n"+   
   	 " data:['',''],                                                                                \n"+   
   	 " show:false                                                                                   \n"+   
   	 " },                                                                                           \n"+   
   	 " grid:{                                                                                       \n"+   
   	 " top:'18%',                                                                                   \n"+   
   	 " right:'5%',                                                                                  \n"+   
   	 " bottom:'19%',                                                                                \n"+   
   	 " left:'5%',                                                                                   \n"+   
   	 " containLabel: true                                                                           \n"+   
   	 " },                                                                                           \n"+   
   	 " xAxis: [                                                                                     \n"+   
   	 " {                                                                                            \n"+   
   	 " type: 'category',                                                                            \n"+   
   	 " fontSize:'15px',                                                                             \n"+   
   	 " data: ["+keys+"],                                             \n"+   
   	 " splitLine:{                                                                                  \n"+   
   	 " show:false,                                                                                  \n"+   
   	 " lineStyle:{                                                                                  \n"+   
   	 " color: ['white'],                                                                            \n"+   
   	 " width: 1,                                                                                    \n"+   
   	 " type: 'solid'                                                                                \n"+   
   	 " }                                                                                            \n"+   
   	 " },                                                                                           \n"+   
   	 " axisTick: {                                                                                  \n"+   
   	 " show: false                                                                                  \n"+   
   	 " },                                                                                           \n"+   
   	 " axisLabel:{                                                                                  \n"+   
   	 " fontSize:'15',                                                                               \n"+   
   	 " textStyle:{                                                                                  \n"+   
   	 " color:\"black\"                                                                                \n"+   
   	 " },                                                                                           \n"+   
   	 " lineStyle:{                                                                                  \n"+   
   	 " color: 'gray'                                                                                \n"+   
   	 " },                                                                                           \n"+   
   	 " alignWithLabel: true,                                                                        \n"+   
   	 " interval:0                                                                                   \n"+   
   	 " }                                                                                            \n"+   
   	 " }                                                                                            \n"+   
   	 " ],                                                                                           \n"+   
   	 " yAxis: [                                                                                     \n"+   
   	 " {                                                                                            \n"+   
   	 " type: 'value',                                                                               \n"+   
   	 " name: '"+title+"',                                                                                  \n"+   
   	 " fontSize:'15',                                                                               \n"+   
   	 " nameTextStyle:{                                                                              \n"+   
   	 " fontSize:'17',                                                                               \n"+   
   	 " color:'#000'                                                                                 \n"+   
   	 " },                                                                                           \n"+    
   	 " min: 0,                                                                                      \n"+   
   	 " splitLine:{                                                                                  \n"+   
   	 " show:true,                                                                                   \n"+   
   	 " lineStyle:{                                                                                  \n"+   
   	 " color: ['#E5F1FB'],                                                                          \n"+   
   	 " width: 0.5,                                                                                  \n"+   
   	 " type: 'solid'                                                                                \n"+   
   	 " }                                                                                            \n"+   
   	 " },                                                                                           \n"+   
   	 " axisLine: {                                                                                  \n"+   
   	 " show:true,                                                                                   \n"+   
   	 " lineStyle: {                                                                                 \n"+   
   	 " color: '#115372'                                                                             \n"+   
   	 " }                                                                                            \n"+   
   	 " },                                                                                           \n"+   
   	 " axisTick: {                                                                                  \n"+   
   	 " show: true                                                                                   \n"+   
   	 " },                                                                                           \n"+   
   	 " axisLabel:{                                                                                  \n"+   
   	 " fontSize:'15',                                                                               \n"+   
   	 " textStyle:{                                                                                  \n"+   
   	 " color:\"#000\"                                                                                 \n"+   
   	 " },                                                                                           \n"+   
   	 " alignWithLabel: true,                                                                        \n"+   
   	 " interval:0                                                                                   \n"+   
   	 " }                                                                                            \n"+   
   	 " },                                                                                           \n"+   
   	 " {                                                                                            \n"+   
   	 " }                                                                                            \n"+   
   	 " ],                                                                                           \n"+   
   	 " color:\"yellow\",                                                                              \n"+   
   	 " series: [                                                                                    \n"+   
   	 " {                                                                                            \n"+   
   	 " name:'',                                                                                \n"+   
   	 " type:'bar',                                                                                  \n"+   
   	 " data:["+values+"],                                                                      \n"+   
   	 " boundaryGap: '45%',                                                                          \n"+   
   	 " barWidth:'40%',                                                                              \n"+   
   	 " itemStyle: {                                                                                 \n"+   
   	 " normal: {                                                                                    \n"+   
   	 " color: function(params) {                                                                    \n"+   
   	 " var colorList = [                                                                            \n"+   
   	 " "+colors+"                                            \n"+   
   	 " ];                                                                                           \n"+   
   	 " return colorList[params.dataIndex]                                                           \n"+   
   	 " },label: {                                                                                   \n"+   
   	 " show: true, fontSize:'16',                                                                   \n"+   
   	 " position: 'top',                                                                             \n"+   
   	 " formatter: '{c}'                                                                             \n"+   
   	 " }                                                                                            \n"+   
   	 " }                                                                                            \n"+   
   	 " }                                                                                            \n"+   
   	 " },                                                                                           \n"+   
   	 " {                                                                                            \n"+   
   	 " name:'test2',                                                                                \n"+   
   	 " type:'line',                                                                                 \n"+   
   	 " yAxisIndex: 1,                                                                               \n"+   
   	 " lineStyle: {                                                                                 \n"+   
   	 " normal: {                                                                                    \n"+   
   	 " color:'gray'                                                                                 \n"+   
   	 " }                                                                                            \n"+   
   	 " },                                                                                           \n"+   
   	 " data:[]                                                                                      \n"+   
   	 " }                                                                                            \n"+   
   	 " ]                                                                                            \n"+   
   	 " };                                                                                           \n"+   
   	 " changedetail"+id+".setOption(option);                                                              \n"+   
   	 " }) </script>                                                                                 \n" ;  
   	 return str;
    }
    
     
	//给一个主list<hashmap>，再给一个从 map<list<hashmap>>，再根据主list里的map里的key，去查从map里的list，合到
	//主list，这是用来组装二级列表的，key是关联字段，sublistname是二级列表的name
	public static List<HashMap> combineSubList(List<HashMap> list,List<HashMap> list2,String key,String sublistname){
		System.out.println();
		Map<String,List<HashMap>> map = matchListToMap(list2,key);
		List<HashMap> listn = new ArrayList<HashMap>();
		for(HashMap m:list)
		{
		   String keyvalue = m.get(key).toString();
		   List<HashMap> sublist = map.get(keyvalue);
		   m.put(sublistname, sublist);
		   listn.add(m);
		}
		return listn;
	}
	
	public static Map<String,List<HashMap>> matchListToMap2(List<HashMap<String,String>> list,String key) { 
		Map<String, List<HashMap>> glist = list.stream().collect(Collectors.groupingBy(e -> e.get(key).toString()));
		return glist;
	}
	
	public static List<HashMap> combineSubList2(List<HashMap> list,List<HashMap<String,String>> list2,String key,String sublistname){
		Map<String,List<HashMap>> map = matchListToMap2(list2,key);
		List<HashMap> listn = new ArrayList<HashMap>();
		for(HashMap m:list)
		{
		   String keyvalue = m.get(key).toString();
		   List<HashMap> sublist = map.get(keyvalue);
		   m.put(sublistname, sublist);
		   listn.add(m);
		}
		return listn;
	}
	
    
    public static boolean isIndex(HttpServletRequest request){
    	boolean b = false;
    	if(request.getRequestURI().endsWith("index.jsp")||request.getRequestURI().equals("/itivtmgr/")||request.getRequestURI().equals("/itivtmgr")){
    		 b = true;
    	}
    	return b;
    }
  
	 public static String getAutoComplate(String name,String tablename,String zdname,List<HashMap> list)
	 { 
		 String kjstr = "<script language=javascript src=\"/itivtmgr/js/compl.js\"></script> \n";  
		  kjstr += "<script language=javascript>\n"; 
		  kjstr += "function "+name+"autoidchange(){\n"; 
		  String ops = ""; 
		 for(HashMap permap:list){ 
			  String optionstr = "";
			 for(String str:zdname.split(";"))
			 {
				 String zdstr = str.split("~")[0];
				 String zdnamestr = str.split("~")[1].equals("hnoname")?"":(str.split("~")[1]+":");
				 optionstr+=zdnamestr+permap.get(zdstr)+" - ";
			 }
			 if(optionstr.length()>0)optionstr=optionstr.substring(0,optionstr.length()-3);
			 
			 ops+=optionstr+"--"; 
		 } 
		 if(ops.length()>0)ops=ops.substring(0,ops.length()-2); 
		 kjstr += "datastrs=\""+ops+"\"; \n";
		 kjstr += "comdivid=\""+name+"div\";\ncomptextid=\""+name+"\" ; \n"; 
		 kjstr += "} \n";
		 kjstr += "</script>\n"; 
		 return kjstr;
	 }
 
	
	public static int getBetweenDayNumber(String dateA, String dateB) {
		long dayNumber = 0;
		//1小时=60分钟=3600秒=3600000
		long mins = 60L * 1000L;
		//long day= 24L * 60L * 60L * 1000L;计算天数之差
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
		   java.util.Date d1 = df.parse(dateA);
		   java.util.Date d2 = df.parse(dateB);
		   dayNumber = (d2.getTime() - d1.getTime()) / mins;
		} catch (Exception e) {
		   e.printStackTrace();
		}
		return (int) dayNumber;
		}
	 
	
	
	 public static String getselect(String name,String tablename,String zdname)
	 {
		 String select = "<select name=\""+name+"\" id=\""+name+"\" >";
		 for(HashMap permap:new CommDAO().select("select * from "+tablename+" order by id desc")){ 
			 select+="<option value=\""+permap.get(zdname)+"\">"+permap.get(zdname)+"</option>";
		 }
		 select+="</select>";
		 return select;
	 }
	 
	 public static String getselect(String name,String tablename,String zdname,List<HashMap<String, String>> list)
	 {
		  
		 String select = "<select name=\""+name+"\" id=\""+name+"\" >";
		 select+="<option value=\"\">不限</option>";
		 for(HashMap permap:list){ 
			 String optionstr = ""; 
			 if(zdname.split(";").length==1){ 
				 optionstr=permap.get(zdname.split("~")[0]).toString(); 
			 }else{
				 for(String str:zdname.split(";"))
				 {
					 String zdstr = str.split("~")[0];
					 String zdnamestr = str.split("~")[1].equals("hnoname")?"":(str.split("~")[1]+":");
					 optionstr+=zdnamestr+permap.get(zdstr)+" - ";
				 } 
			 } 
			 if(optionstr.indexOf(" - ")>-1)optionstr=optionstr.substring(0,optionstr.length()-3);
			 select+="<option value=\""+optionstr+"\">"+optionstr+"</option>";
		 }
		 select+="</select>";
		 return select;
	 }
	 
	 
	 public static String getselect(String name,String tablename,String zdname,String where,String style)
	 {
		  
		 String select = "<select name=\""+name+"\" id=\""+name+"\" style=\""+style+"\" >";
		 select+="<option value=\"\">不限</option>";
		 for(HashMap permap:new CommDAO().select("select * from "+tablename+" where "+where+" order by id desc")){ 
			 String optionstr = ""; 
			 if(zdname.split(";").length==1){ 
				 optionstr=permap.get(zdname.split("~")[0]).toString(); 
			 }else{
				 for(String str:zdname.split(";"))
				 {
					 String zdstr = str.split("~")[0];
					 String zdnamestr = str.split("~")[1].equals("hnoname")?"":(str.split("~")[1]+":");
					 optionstr+=zdnamestr+permap.get(zdstr)+" - ";
				 } 
			 } 
			 if(optionstr.indexOf(" - ")>-1)optionstr=optionstr.substring(0,optionstr.length()-3);
			 select+="<option value=\""+optionstr+"\">"+optionstr+"</option>";
		 }
		 select+="</select>";
		 return select;
	 }
	 
	 public static String getradio(String name,String tablename,String zdname,List<HashMap> list)
	 { 
		 String radio="";
		 int dxii = 0;
		 for(HashMap permap:list){ 
			 String check="";
			 if(dxii==0)check="checked=checked";
			 
			 String optionstr = "";
			 for(String str:zdname.split(";"))
			 {
				 String zdstr = str.split("~")[0];
				 //String zdnamestr = str.split("~")[1].equals("hnoname")?"":(str.split("~")[1]+":");
				 //optionstr+=zdnamestr+permap.get(zdstr)+" - ";
				 optionstr+=permap.get(zdstr)+" - ";
			 }
			 if(optionstr.length()>0)optionstr=optionstr.substring(0,optionstr.length()-3);
			 
			 radio+="<label><input type='radio' name='"+name+"' "+check+" value=\""+optionstr+"\"> "+optionstr+"</label> &nbsp; \n";
			 dxii++;
		 }
		 return radio;
	 }
	 
	 
	 public static String writeExcel(String fileName,String prosstr,java.util.List<List> plist,HttpServletRequest request){    
	        WritableWorkbook  wwb = null;    
	        String cols = "";
	        for(String str:prosstr.split("@"))
	        {
	        	cols+=str.split("-")[0]+",";
	        }
	        cols = cols.substring(0,cols.length()-1); 
	        List<List> mlist = plist;
	        
	        fileName = request.getRealPath("/")+"/upfile/"+Info.generalFileName("a.xls");
	        String[] pros = prosstr.split("@");
	        try {    
	            //首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象    
	            wwb = Workbook.createWorkbook(new File(fileName));    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }    
	        if(wwb!=null){    
	            //创建一个可写入的工作表    
	            //Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置    
	            WritableSheet ws = wwb.createSheet("sheet1", 0);    
	            ws.setColumnView(0,20);
	            ws.setColumnView(1,20);
	            ws.setColumnView(2,20);
	            ws.setColumnView(3,20);
	            ws.setColumnView(4,20);
	            ws.setColumnView(5,20);

	            try {
	            
	            	
	            for(int i=0;i<pros.length;i++)
	            {
	            Label label1 = new Label(i, 0,"");
	            
	            label1.setString(pros[i]);
	            ws.addCell(label1);
	            }
	            
	            } catch (RowsExceededException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}    
	             //下面开始添加单元格  
				int i=1;
	            for(List t:mlist){  
	            	try {
	            		System.out.println(t);
	            	Iterator it = t.iterator();
	            	int jj=0;
	            		while(it.hasNext())
	            		{
	            	Label label1 = new Label(jj, i,"");
	            	
	            	String a = it.next().toString();
		            label1.setString(a);
		            ws.addCell(label1);
		            jj++;
	            		}
		            
		            i++;
	            } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
	            }    
	   
	            try {    
	                //从内存中写入文件中    
	                wwb.write();    
	                //关闭资源，释放内存    
	                wwb.close();    
	            } catch (IOException e) {    
	                e.printStackTrace();    
	            } catch (Exception e) {    
	                e.printStackTrace();    
	            }    
	        } 
	         
				return fileName.substring(fileName.lastIndexOf("/")+1); 
	        
	    }    
	 
	 
	 public static String getcheckbox(String name,String tablename,String zdname,List<HashMap> list)
	 { 
		 String checkbox="";
		 for(HashMap permap: list){ 
			 
			 String optionstr = "";
			 for(String str:zdname.split(";"))
			 {
				 String zdstr = str.split("~")[0];
//				 String zdnamestr = str.split("~")[1].equals("hnoname")?"":(str.split("~")[1]+":");
//				 optionstr+=zdnamestr+permap.get(zdstr)+" - ";
				 optionstr+=permap.get(zdstr)+" - ";
			 }
			 if(optionstr.length()>0)optionstr=optionstr.substring(0,optionstr.length()-3);
			 
			 checkbox+="<label><input type='checkbox' name='"+name+"' value=\""+optionstr+"\">"+optionstr+"</label>\n";
		 }
		 checkbox+="<input type=hidden name='"+name+"' value='' />";
		 return checkbox;
	 }
	 
	 
	 public static String getCombox(String tablename,String zdname,String where)
	 { 
		 String checkbox="";
		 for(HashMap permap:new CommDAO().select("select * from "+tablename+" where "+where+" order by id desc")){ 
			 
			 String optionstr = "'";
			 for(String str:zdname.split(";"))
			 {
				 if(str.indexOf("~")>-1){
				 String zdstr = str.split("~")[0];
				 String zdnamestr = str.split("~")[1].equals("hnoname")?"":(str.split("~")[1]+":");
				 optionstr+=zdnamestr+permap.get(zdstr)+" - ";
				 }else{ 
					 optionstr+=permap.get(str); 
				 }
			 }
			 if(optionstr.length()>0)optionstr=optionstr.substring(0,optionstr.length()-3);
			 optionstr+="',";
			 checkbox+= optionstr;
		 }
		 if(checkbox.length()>0)checkbox=checkbox.substring(0,checkbox.length()-1);
		 
		 return checkbox;
	 }
	 
	 
	 
	 
	 public static String getcheckboxDk(String name,String tablename,String zdname,String nstr,List<HashMap> list)
	 { 
		 String checkbox="";
		 int i=0;
		 for(HashMap permap:list){ 
			 
			 String optionstr = "";
			 for(String str:zdname.split(";"))
			 {
				 String zdstr = str.split("~")[0];
//				 String zdnamestr = str.split("~")[1].equals("hnoname")?"":(str.split("~")[1]+":");
//				 optionstr+=zdnamestr+permap.get(zdstr)+" - ";
				 optionstr+=permap.get(zdstr)+" - ";
			 }
			 if(optionstr.length()>0)optionstr=optionstr.substring(0,optionstr.length()-3);
			 String nbs = "";
			 if(i>0)nbs="&nbsp;";
			 checkbox+="<label>"+nbs+"<input type='checkbox' name='"+name+"' value=\""+optionstr+"\">"+optionstr+"</label>&nbsp;&nbsp;\n";
			 checkbox+="<label>&nbsp;- "+nstr+" &nbsp;<input type='text' size='5' name='"+optionstr+"' value=\"\"></label><br />\n";
			 i++;
		 }
		 checkbox+="<input type=hidden name='"+name+"' value='' /><input type=hidden name='dk-"+name+"-value' value='"+nstr+"' />";
		 return checkbox;
	 }
	 

	 public static String getFileUpInfo()
		{
			String jscode = "";
			jscode+="<script src=/itivtmgr/js/popup.js></script>\n";
			jscode+="<font onclick=\"uploaddoc()\" src=\"/itivtmgr/admin/adminfiles/js/nopic.jpg\" style='cursor:hand' id=txt >点击此处上传</font>\n";
			jscode+="&nbsp;&nbsp;<input type=text readonly style='border:0px' size=30  name=\"docname\" id=\"docname\" value=\"\" />\n";
			return jscode;
		}
	 
	 public static String getFileUpInfo2()
		{
			String jscode = "";
			jscode+="<script src=/itivtmgr/js/popup.js></script>";
			jscode+="<font onclick=\"uploaddoc2()\" src=\"/itivtmgr/admin/adminfiles/js/nopic.jpg\" style='cursor:hand' id=filetxt2 >点击此处上传</font>";
			jscode+="&nbsp;&nbsp;<input type=text readonly style='border:0px' size=30  name=\"docname2\" id=\"docname2\" value=\"\" />";
			return jscode;
		}
	 
	 public static String getFileUpInfo3()
		{
			String jscode = "";
			jscode+="<script src=/itivtmgr/js/popup.js></script>";
			jscode+="<font onclick=\"uploaddoc3()\" src=\"/itivtmgr/admin/adminfiles/js/nopic.jpg\" style='cursor:hand' id=filetxt3 >点击此处上传</font>";
			jscode+="&nbsp;&nbsp;<input type=text readonly style='border:0px' size=30  name=\"docname3\" id=\"docname3\" value=\"\" />";
			return jscode;
		}

	  public static String tform(HashMap map)
		{
	    	String jscode = "";
	    	try{
	    	jscode+="<script type=\"text/javascript\">\n";
	    	jscode+="function getPvalue()\n";
	    	jscode+="{\n";
	    	
	    	Set set = map.entrySet();
	    	 Iterator it = set.iterator();
	    	 while(it.hasNext())
	    	 {
	    	  String pm=((Object)it.next()).toString();
	    	  String str1 = "";
	    	  String str2 = "";
	    	  String[] strs = pm.split("=");
	    	  str1 = strs[0];
	    	  if(strs.length==1)str2="";
	    	  if(strs.length==2)str2=strs[1];
	    	  str2 = str2.replaceAll("\r\n", "-----");
	    	 
	    	  if(!str1.equals("content")){
	    	  
	    	  jscode+=" if(document.getElementsByName(\""+str1+"\").length>1)\n";
	    	  jscode+=" {\n";
	    	  jscode+=" var radios = document.getElementsByName(\""+str1+"\");\n";
	    	 
	    	  jscode+=" if(radios[0].type=='radio'){\n";
	    	  jscode+=" for(var i=0;i<radios.length;i++)\n";
	    	  jscode+=" {\n";
	    	  jscode+=" if(radios[i].value==\""+str2+"\")\n";
	    	  jscode+=" {\n";
	    	  jscode+=" radios[i].checked=\"checked\";\n";
	    	  jscode+=" }\n";
	    	  jscode+=" }\n";
	    	  jscode+=" }\n";
	    	   
	    	  
	    	  jscode+=" if(radios[0].type=='checkbox'){\n";
	    	  
	    	  
	    	  
	    	  jscode+=" for(var i=0;i<radios.length;i++)\n";
	    	  jscode+=" {\n";
	    	   
	    	  jscode+=" if(\""+str2+"\".indexOf(radios[i].value)>-1)\n";
	    	  jscode+=" {\n";
	    	  
	    	  jscode+=" radios[i].checked=\"checked\";\n";
	    	  
	    	 
	    	  if(str2.indexOf(" - ")>-1){
	    		for(String strch:str2.split(" ~ ")){  
		    	  
	    		  String strchname = strch.substring(0,strch.lastIndexOf(" - "));
	    		  jscode+=" if(document.getElementsByName('"+strchname+"').length>0)\n";
		    	  jscode+=" {\n";
		    	  jscode+=" document.getElementsByName('"+strchname+"')[0].value='"+strch.substring(strch.lastIndexOf(":")+1)+"';\n";
		    	  jscode+=" }\n";
		    	  
		    	  
	    		}
	    	 }
	    	  
	    	  
	    	  jscode+=" }\n";
	    	  jscode+=" }\n";
	    	  jscode+=" }\n";
	    	  
	    	  jscode+=" if(radios.type=='select'){\n";
	    	  jscode+=" f1."+str1+".value=\""+str2+"\";\n";
	    	  jscode+=" }\n";
	    	  
	    	  
	    	  jscode+=" }else{   \n";
	    	 jscode+=" if(f1."+str1+")\n";
	    	  jscode+="{\n";
	    	  jscode+="f1."+str1+".value=\""+str2+"\";\n";
	    	  jscode+="}\n";
	    	  jscode+="}\n";
	    	  
	    	
	    	  jscode+="if(document.getElementById(\"txt\"))\n";
	    	  jscode+="{\n";
	    	  jscode+="document.getElementById(\"txt\").src=\"/itivtmgr/upfile/"+map.get("filename")+"\";\n";
	    	  jscode+="}\n";
	    	  
	    	  jscode+="if(document.getElementById(\"txt2\"))\n";
	    	  jscode+="{\n";
	    	  jscode+="document.getElementById(\"txt2\").src=\"/itivtmgr/upfile/"+map.get("filename2")+"\";\n";
	    	  jscode+="}\n";
	    	  
	    	  jscode+="if(document.getElementById(\"txt3\"))\n";
	    	  jscode+="{\n";
	    	  jscode+="document.getElementById(\"txt3\").src=\"/itivtmgr/upfile/"+map.get("filename3")+"\";\n";
	    	  jscode+="}\n";
	    	  
	    	  jscode+="if(document.getElementById(\"txt4\"))\n";
	    	  jscode+="{\n";
	    	  jscode+="document.getElementById(\"txt4\").src=\"/itivtmgr/upfile/"+map.get("filename4")+"\";\n";
	    	  jscode+="}\n";
	    	  
	    	  jscode+="if(document.getElementById(\"txt5\"))\n";
	    	  jscode+="{\n";
	    	  jscode+="document.getElementById(\"txt5\").src=\"/itivtmgr/upfile/"+map.get("filename5")+"\";\n";
	    	  jscode+="}\n";
	    	  
	    	  } 
	    	 }
	    	  
	    	  jscode+="}\n"; 
	    jscode+=" getPvalue();\n";
	    
	   if(map.containsKey("dytabrows"))
  	  {
  		  String dytabrows = map.get("dytabrows").toString();
  		  String dytabtab = map.get("dytabtab").toString();
  		  for(int i=1;i<=Integer.parseInt(dytabrows);i++)
  		  {
  			jscode+="document.getElementById(\""+dytabtab+"_tr_"+i+"\").style.display='';\n";
  		  }
  		  jscode+=""+dytabtab+"_table_crows="+dytabrows+";\n";
  		  jscode+="document.getElementById(\"grtable_crows\").value='"+dytabrows+"';\n";
  	  }
	    
	    jscode+="</script>\n";
	    	}catch (Exception e) {
				e.printStackTrace();
			}
	      return jscode;
		}
	  
	  
	  
	  
	  public static String tformF2(HashMap map)
		{
	    	String jscode = "";
	    	try{
	    	jscode+="<script type=\"text/javascript\">\n";
	    	jscode+="function getPvalueF2()\n";
	    	jscode+="{\n";
	    	
	    	Set set = map.entrySet();
	    	 Iterator it = set.iterator();
	    	 while(it.hasNext())
	    	 {
	    	  String pm=((Object)it.next()).toString();
	    	  String str1 = "";
	    	  String str2 = "";
	    	  String[] strs = pm.split("=");
	    	  str1 = strs[0];
	    	  if(strs.length==1)str2="";
	    	  if(strs.length==2)str2=strs[1];
	    	  str2 = str2.replaceAll("\r\n", "-----");
	    	 
	    	  if(!str1.equals("content")){
	    	  
	    	  jscode+=" if(document.getElementsByName(\""+str1+"\").length>1)\n";
	    	  jscode+=" {\n";
	    	  jscode+=" var radios = document.getElementsByName(\""+str1+"\");\n";
	    	 
	    	  jscode+=" if(radios[0].type=='radio'){\n";
	    	  jscode+=" for(var i=0;i<radios.length;i++)\n";
	    	  jscode+=" {\n";
	    	  jscode+=" if(radios[i].value==\""+str2+"\")\n";
	    	  jscode+=" {\n";
	    	  jscode+=" radios[i].checked=\"checked\";\n";
	    	  jscode+=" }\n";
	    	  jscode+=" }\n";
	    	  jscode+=" }\n";
	    	   
	    	  
	    	  jscode+=" if(radios[0].type=='checkbox'){\n";
	    	  
	    	  
	    	  
	    	  jscode+=" for(var i=0;i<radios.length;i++)\n";
	    	  jscode+=" {\n";
	    	   
	    	  jscode+=" if(\""+str2+"\".indexOf(radios[i].value)>-1)\n";
	    	  jscode+=" {\n";
	    	  
	    	  jscode+=" radios[i].checked=\"checked\";\n";
	    	  
	    	 
	    	  if(str2.indexOf(" - ")>-1){
	    		for(String strch:str2.split(" ~ ")){  
		    	  
	    		  String strchname = strch.substring(0,strch.lastIndexOf(" - "));
	    		  jscode+=" if(document.getElementsByName('"+strchname+"').length>0)\n";
		    	  jscode+=" {\n";
		    	  jscode+=" document.getElementsByName('"+strchname+"')[0].value='"+strch.substring(strch.lastIndexOf(":")+1)+"';\n";
		    	  jscode+=" }\n";
		    	  
		    	  
	    		}
	    	 }
	    	  
	    	  
	    	  jscode+=" }\n";
	    	  jscode+=" }\n";
	    	  jscode+=" }\n";
	    	  
	    	  jscode+=" if(radios.type=='select'){\n";
	    	  jscode+=" f2."+str1+".value=\""+str2+"\";\n";
	    	  jscode+=" }\n";
	    	  
	    	  
	    	  jscode+=" }else{\n";
	    	 jscode+=" if(f2."+str1+")\n";
	    	  jscode+="{\n";
	    	  jscode+="f2."+str1+".value=\""+str2+"\";\n";
	    	  jscode+="}\n";
	    	  jscode+="}\n";
	    	  
	    	
	    	  jscode+="if(document.getElementById(\"txt\"))\n";
	    	  jscode+="{\n";
	    	  jscode+="document.getElementById(\"txt\").src=\"/itivtmgr/upfile/"+map.get("filename")+"\";\n";
	    	  jscode+="}\n";
	    	  
	    	  jscode+="if(document.getElementById(\"txt2\"))\n";
	    	  jscode+="{\n";
	    	  jscode+="document.getElementById(\"txt2\").src=\"/itivtmgr/upfile/"+map.get("filename2")+"\";\n";
	    	  jscode+="}\n";
	    	  
	    	  jscode+="if(document.getElementById(\"txt3\"))\n";
	    	  jscode+="{\n";
	    	  jscode+="document.getElementById(\"txt3\").src=\"/itivtmgr/upfile/"+map.get("filename3")+"\";\n";
	    	  jscode+="}\n";
	    	  
	    	  jscode+="if(document.getElementById(\"txt4\"))\n";
	    	  jscode+="{\n";
	    	  jscode+="document.getElementById(\"txt4\").src=\"/itivtmgr/upfile/"+map.get("filename4")+"\";\n";
	    	  jscode+="}\n";
	    	  
	    	  jscode+="if(document.getElementById(\"txt5\"))\n";
	    	  jscode+="{\n";
	    	  jscode+="document.getElementById(\"txt5\").src=\"/itivtmgr/upfile/"+map.get("filename5")+"\";\n";
	    	  jscode+="}\n";
	    	  
	    	  }
	    	 }
	    	  
	    	  jscode+="}\n";
	    	
	    	  
	    	 
	    	 
	    jscode+=" getPvalueF2();\n";
	    jscode+="</script>\n";
	    	}catch (Exception e) {
				e.printStackTrace();
			}
	      return jscode;
		}
	    
	
	
	public static String generalFileName(String srcFileName) {
		try{
		   int index=srcFileName.lastIndexOf(".");
		   return StrUtil.generalSrid()+srcFileName.substring(index).toLowerCase();
		}catch(Exception e){
			return StrUtil.generalSrid();
		}
	}

	public synchronized static String getID() {
		Random random = new Random();
		StringBuffer ret = new StringBuffer(20);
		String rand = String.valueOf(Math.abs(random.nextInt()));
		ret.append(getDateStr());
		ret.append(rand.substring(0,6));
		
		return ret.toString();
	} 
	
	


	
	public static String getImgUpInfo(int height)
	{
		String jscode = "";
		jscode+="<img style=\"border-radius:0;cursor: hand;margin:3px;height:85px\" onclick=\"uploadimg()\" src=\"/itivtmgr/js/nopic.jpg\" id=txt height=\""+height+"\"/> \n";
		jscode+="<input type=hidden name=\"filename\" id=\"filename\" value=\"\" /> \n";
		jscode+="<script type=\"text/javascript\"  src=\"/itivtmgr/js/popups.js\"></script> \n";
		return jscode;
	}
	
	
	
	public static String getImgUpInfo2(int height)
	{
		String jscode = "";
		jscode+="<img style=\"cursor: hand\" onclick=\"uploadimg2()\" src=\"/itivtmgr/js/nopic.jpg\" id=txt2 height=\""+height+"\"/>";
		jscode+="<input type=hidden name=\"filename2\" id=\"filename2\" value=\"\" />";
		jscode+="<script type=\"text/javascript\"  src=\"/itivtmgr/js/popup.js\"></script>";
		return jscode;
	}
	
	public static String getImgUpInfo3(int height)
	{
		String jscode = "";
		jscode+="<img style=\"cursor: hand\" onclick=\"uploadimg3()\" src=\"/itivtmgr/js/nopic.jpg\" id=txt3 height=\""+height+"\"/>";
		jscode+="<input type=hidden name=\"filename3\" id=\"filename3\" value=\"\" />";
		jscode+="<script type=\"text/javascript\"  src=\"/itivtmgr/js/popup.js\"></script>";
		return jscode;
	}
	
	public static String getImgUpInfo4(int height)
	{
		String jscode = "";
		jscode+="<img style=\"cursor: hand\" onclick=\"uploadimg4()\" src=\"/itivtmgr/js/nopic.jpg\" id=txt4 height=\""+height+"\"/>";
		jscode+="<input type=hidden name=\"filename4\" id=\"filename4\" value=\"\" />";
		jscode+="<script type=\"text/javascript\"  src=\"/itivtmgr/js/popup.js\"></script>";
		return jscode;
	}
	
	public static String getImgUpInfo5(int height)
	{
		String jscode = "";
		jscode+="<img style=\"cursor: hand\" onclick=\"uploadimg5()\" src=\"/itivtmgr/js/nopic.jpg\" id=txt5 height=\""+height+"\"/>";
		jscode+="<input type=hidden name=\"filename5\" id=\"filename5\" value=\"\" />";
		jscode+="<script type=\"text/javascript\"  src=\"/itivtmgr/js/popup.js\"></script>";
		return jscode;
	}
	
	// 替换字符串里最后出现的元素
	public static String replaceLast( String text, String strToReplace, 
	                                  String replaceWithThis ) {
		return text.replaceFirst( "(?s)" + strToReplace + "(?!.*?" + strToReplace     
	                                       + ")", replaceWithThis );
	}
	
	public static String fck(int height,String content)
	{
		String jscode = "<TEXTAREA   name=\"content\" id=\"content\">"+content+"</TEXTAREA>\n";
		jscode+="<script type=\"text/javascript\"  src=\"/itivtmgr/fckeditor/ckeditor.js\"></script>\n";
		jscode+="<script language=\"javascript\">\n";
		jscode+="function fckinit()\n";
		jscode+="{\n";
		jscode+=" CKEDITOR.replace('content');\n"; 
		jscode+="}\n";
		jscode+="fckinit();\n";
		jscode+="</script>\n"; 
		return jscode;
	}
	
	 //"引用新增"，新增界面接受一个tablename和id，可以将新增界面上的字段，自动赋值
	 //hashmap里存值    源表字段名 - 目标表字段名
	 public static String applyInsert(String tablename,String cols,HashMap<String, String> datamap)
	 {  
		 cols = cols.substring(0,cols.length()-1);
		 //HashMap<String, String> m = getmap(id, tablename);  
		 HashMap<String, String> infomap = new HashMap<String, String>();
		 for(String str:cols.split(";"))
		 {
			 //源表字段名
			 String sourceColName = str.split(",")[0];
			 //目标表字段名(要插入的那个表的字段名)
			 String desColName = str.split(",")[1];
			 //源表字段值
			 String sourceColValue = datamap.get(sourceColName);
			 //目标表字段赋值
			 infomap.put(desColName, sourceColValue);
		 }
		 String infoscript = Info.tform(infomap); 
		 return infoscript;
	 }
	
	public static String fck1(int height,String pduty)
	{
		String jscode = "<TEXTAREA   name=\"pduty\" id=\"pduty\">"+pduty+"</TEXTAREA>";
		jscode+="<script type=\"text/javascript\"  src=\"/itivtmgr/fckeditor/fckeditor.js\"></script>";
		jscode+="<script language=\"javascript\">";
		jscode+="function fckinit()";
		jscode+="{";
		jscode+=" var of = new FCKeditor(\"pduty\");";
		jscode+="of.BasePath=\"/zxjksite/fckeditor/\";";
		jscode+="of.Height = \""+height+"\";";
		jscode+="of.ToolbarSet=\"Default\";";
		jscode+="of.ReplaceTextarea();";
		jscode+="}";
		jscode+="fckinit();";
		jscode+="</script>"; 
		return jscode;
	}
	
	public static Object getDao(HttpServletRequest request,String name)
    {
    	Object ad= (Object) WebApplicationContextUtils
    	.getRequiredWebApplicationContext(request.getSession().getServletContext()).getBean(name);
    	return ad;
    }

	
	public synchronized static String subStr(String source,int length) {
		if(source.length()>length)
		{
			source=source.substring(0,length)+"...";
		}
		
		return source;
	} 
	
	
	public static String ensubStr(String source,Integer length) {
		String msource = Info.filterStrIgnoreCase(source.toString(), "<", ">");
		if(msource.length()>length)
		{
			msource=msource.substring(0,length)+"...";
		}  
		return msource;
	} 
	
	
//	public static String ensubStr() { 
//		String msource = Info.filterStrIgnoreCase(source.toString(), "<", ">");
//		return "----";
//	} 
	
	

	public static String getDateStr(){
		String dateString="";
		try{//yyyyMMddHHmmss
		java.text.SimpleDateFormat formatter=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date currentTime_1=new java.util.Date();
		dateString=formatter.format(currentTime_1);
		}catch(Exception e){
		     }
		 return dateString;
		 } 
	
	
	


	
	 public static String getUTFStr(String str) {
		 if(str==null){
			 return "";
		 }
		 
		 try {
			str =  new String(str.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	 }
	 
	 public static String getGBKStr(String str) throws UnsupportedEncodingException{
		 if(str==null){
			 return "";
		 }
		 return  new String(str.getBytes("ISO-8859-1"),"GBK");
	 }
	 
	 public static String getGB2312Str(String str) throws UnsupportedEncodingException{
		 if(str==null){
			 return "";
		 }
		 return  new String(str.getBytes("ISO-8859-1"),"gb2312");
	 }

	 
	 /**
		 *得到多少天之后之前的日期
		 * @return
		 */
		public static String getDay(String date,int day) {
			String b = date.substring(0,10);
			String c = b.substring(0,4);
			String d = b.substring(5,7);
			String f = b.substring(8,10);
			String aa = c+"/"+d+"/"+f;
			String a = "";
			DateFormat dateFormat =  DateFormat.getDateInstance(DateFormat.MEDIUM);
				GregorianCalendar grc=new GregorianCalendar();
				grc.setTime(new Date(aa));
	            grc.add(GregorianCalendar.DAY_OF_MONTH,day);
	            String resu = dateFormat.format(grc.getTime());
	        String t[]= resu.split("-");
	        String sesuu = "";
	        for(int i=0;i<t.length;i++)
	        {
	        	if(t[i].length()==1)
	        	{
	        		t[i]="0"+t[i];
	        	}
	        	sesuu += t[i]+"-";
	        }
	        
			return sesuu.substring(0,10);
		}
		

	    
	    
	    /**
	 	 * 计算两个时期之间的天数
	 	 * 
	 	 */
	    public static int dayToday(String DATE1, String DATE2) {
	       int i = 0;
	       if(DATE1.indexOf(" ")>-1)
	       DATE1 = DATE1.substring(0,DATE1.indexOf(" "));
	       if(DATE2.indexOf(" ")>-1)
	       DATE2 = DATE2.substring(0,DATE2.indexOf(" "));
	       
	       String[] d1 = DATE1.split("-");
	       if(d1[1].length()==1)
	       {
	    	   DATE1 = d1[0]+"-0"+d1[1];
	       }else{
	    	   DATE1 = d1[0]+"-"+d1[1];
	       }
	       
	       if(d1[2].length()==1)
	       {
	    	   DATE1 = DATE1+"-0"+d1[2];
	       }else{
	    	   DATE1 = DATE1+"-"+d1[2];
	       }
	       
	       String[] d2 = DATE2.split("-");
	       if(d2[1].length()==1)
	       {
	    	   DATE2 = d2[0]+"-0"+d2[1];
	       }else{
	    	   DATE2 = d2[0]+"-"+d2[1];
	       }
	       
	       if(d2[2].length()==1)
	       {
	    	   DATE2 = DATE2+"-0"+d2[2];
	       }else{
	    	   DATE2 = DATE2+"-"+d2[2];
	       }
	       
	       
	       for(int j=0;j<10000;j++)
	       {
	    	i=j;
	    	String gday = Info.getDay(DATE1, j);
	    	if(gday.equals(DATE2))
	    	{
	    		break;
	    	}
	       }
	        return i;
	    }
	    
	   
	    

	    /**
	 	 * 比较时间大小
	 	 * 
	 	 */
	    public static String compare_datezq(String DATE1, String DATE2) {
	       DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            Date dt1 = df.parse(DATE1);
	            Date dt2 = df.parse(DATE2);
	            if (dt1.getTime() > dt2.getTime()) {
	               
	                return "big";
	            } else if (dt1.getTime() < dt2.getTime()) {
	              
	                return "small";
	            } else {
	                return "den";
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	        return "den";
	    }
	    
	    /**
	 	 * 过滤html代码
	 	 * 
	 	 */
	    public static String filterStrIgnoreCase(String source, String from, String to){
	        String sourceLowcase=source.toLowerCase();
	        String sub1,sub2,subLowcase1,subLowcase2;
	        sub1=sub2=subLowcase1=subLowcase2="";
	        int start=0,end;
	        boolean done=true;
	        if(source==null) return null;
	        if(from==null||from.equals("")||to==null||to.equals("")) 
	         return source;
	        while(done){
	         start=sourceLowcase.indexOf(from,start);
	         if(start==-1) {
	          break;
	         }
	         subLowcase1=sourceLowcase.substring(0,start);
	         sub1=source.substring(0,start);
	         end=sourceLowcase.indexOf(to,start);
	         if(end==-1){
	          end=sourceLowcase.indexOf("/>",start); 
	          if(end==-1) {
	           done=false;
	          }
	         }
	         else{
	          end=end+to.length();
	          subLowcase2=sourceLowcase.substring(end,source.length());
	          sub2=source.substring(end,source.length());
	          sourceLowcase=subLowcase1+subLowcase2;
	          source=sub1+sub2;
	         }
	         //System.out.println(start+" "+end);
	        }
	        return source;
	   }
	    
	   
	    public static void delPic(String path,String img)
		{
			 if(img!=null)
			 {
				 if(!img.equals(""))
				 {
			  File file1=new File(path + "/" + img); 
		       if(file1.exists() ) {
		    	file1.deleteOnExit();
		         file1.delete(); 
		       }}}
		}
	    
	    
	     public static String getPageUrl(String pagename,HashMap<String,String> selectmap)
	     {
	    	 String pageurl = "/itivtmgr"+pagename+".do?pageurls=1";
	    	 System.out.println("---- "+selectmap); 
//	    	 for(Entry<String, String> entry:selectmap.entrySet())
//	    	 {
//	    		 if(entry.getKey().contains("trcurrentid"))continue;
//	    		 if(entry.getKey().equals("1"))continue;
//	    		 if(entry.getKey().contains("currentPage"))continue;
//	    		 String value = entry.getValue();
//	    		 if(selectmap.containsKey("currentPage")) { 
//						try {
//							value=Info.getGBKStr(value);
//						} catch (UnsupportedEncodingException e) { 
//							e.printStackTrace();
//						}
//				        System.out.println(value+" ------------");   
//	    		 }
//	    		 pageurl+="&"+entry.getKey()+"="+value;
//	    	 }
	    	 return pageurl;
	     }


	     public static String getCol(String type,String trid,String name,String url,HttpServletRequest request)
	     {   
	    	String html = "";
	    	String currentid = "1";
            String liactive = ""; 
            String pageactive = ""; 
            String currentpage = "";
			 String aria_expanded = "false";
			 String iconshow = "";
			 String collapsed = "collapsed";
            
	    	 if(request.getSession().getAttribute("trcurrentid")!=null)
            {
            	currentid = request.getSession().getAttribute("trcurrentid").toString();
            }
	    	 
	    	 if(request.getSession().getAttribute("trcurrentpage")!=null)
            {
	    		 currentpage = request.getSession().getAttribute("trcurrentpage").toString();
            }
	    	 
            if(currentid.equals(trid))
            {
            	liactive = " active";
				aria_expanded = "true";
				iconshow = "show";
				collapsed = "";
            }
	    	String[] uri = request.getRequestURI().split("/");
	    	String page = uri[uri.length-1].split("[.]")[0]; 
	    	String curl = url.split("[.]")[0];
	    	
	    	String[] urls = url.split("/");
	    	String cpage = urls[urls.length-1].split("[.]")[0]; 
	    	  
	    	if(url.contains("/"+currentpage)&&currentpage!=null&&!currentpage.equals(""))
            { 
            	pageactive = " active"; 
            }

			 String dis = "";


	    	if(type.equals("A")) {
             html = "<li class=\"menu-item\">\r\n" +
					 "<a id=\"picon"+trid+"\" href=\"#\" class=\"has-chevron "+collapsed+"\" data-toggle=\"collapse\" data-target=\"#icons"+trid+"\" aria-expanded=\""+aria_expanded+"\" aria-controls=\"icons"+trid+"\">\r\n" +
					 "<span><i class=\"material-icons fs-16\" style=\"margin-top: -15px\">equalizer</i>"+name+"</span>\r\n" +
					 "</a>\r\n" +
					 "<ul id=\"icons"+trid+"\" class=\""+iconshow+" collapse\" aria-labelledby=\"icons"+trid+"\"  data-parent=\"#side-nav-accordion\">\r\n" ;
	    	}

	    	if(type.equals("B")) {
               html =  " <li ><a class=\""+pageactive+"\" href=\""+url+"?trcurrentid="+trid+"&page="+cpage+"\">"+name+"</a></li>" ;
	    	}
            
	    	if(type.equals("C")) {
             html =   "  </ul>  </li> " ;
	    	}
            
	    	 return html;
	     }

	public static List<HashMap> subList(List<HashMap> rlist,int start,int end)
	{
		List<HashMap> list = new ArrayList();
		if(rlist!=null){
			int x = 1;
			for(HashMap m:rlist){
                if(x>=start&&x<=end){
                	list.add(m);
				}
				x++;
			}
		}
		return list;
	}


		public static String getClass(String iorder,HttpServletRequest request)
	     { 
	     	String iclass="";
	     	String iordersession = request.getSession().getAttribute("iorder").toString();
	     	if(iorder.equals(iordersession))
	     	{
	     		iclass="class=\"current\"";
	     	}
	     	return iclass;
	     }
	     
	   public static void testa()
	   {
		   Sysuser u1 = new Sysuser();
		   u1.setUname("aaa");
		   u1.setUpass("bbb");
		   testb(u1);
		   System.out.println(u1.getUname());
	   }
	   
	   public static void testb(Sysuser u1)
	   {
		   Sysuser u2 = new Sysuser();
		   u1.setUname("111");
		   u1.setUpass("222");
		   u1=u2;
	   }
	      
	     
    public static void main(String[] args) { 
		System.out.println("20121212".substring(0,6));
	}
		
}

		

