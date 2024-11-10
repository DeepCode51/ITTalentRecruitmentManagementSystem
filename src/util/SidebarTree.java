package util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class SidebarTree {
	 private String url;
     private String urlname;
     private String id;  //节点
     
	 private String parent; //父节点
     private JSONObject attributes = new JSONObject();   //net.sf.json
     private List<SidebarTree> children= new ArrayList<SidebarTree>(); //存放子节点
 
     public String getUrl() {
 		return url;
 	}
 	public void setUrl(String url) {
 		this.url = url;
 	}
 	public String getUrlname() {
 		return urlname;
 	}
 	public void setUrlname(String urlname) {
 		this.urlname = urlname;
 	}
 	public String getId() {
 		return id;
 	}
 	public void setId(String id) {
 		this.id = id;
 	}
 	public String getParent() {
 		return parent;
 	}
 	public void setParent(String parent) {
 		this.parent = parent;
 	}
 	public JSONObject getAttributes() {
 		return attributes;
 	}
 	public void setAttributes(JSONObject attributes) {
 		this.attributes = attributes;
 	}
 	public List<SidebarTree> getChildren() {
 		return children;
 	}
 	public void setChildren(List<SidebarTree> children) {
 		this.children = children;
 	}
     
}
