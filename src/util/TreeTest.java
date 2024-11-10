package util;

import java.util.ArrayList;
import java.util.List;

public class TreeTest {
	public static List<SidebarTree> formatTree(List<SidebarTree> list ){
		SidebarTree root=new SidebarTree();
		SidebarTree node=new SidebarTree();
		List<SidebarTree> treelist=new ArrayList<SidebarTree>();; //拼凑好的Json数据
		List<SidebarTree> parentNodes=new ArrayList<SidebarTree>();; // 存放所有父节点
		
	     if(list!=null && list.size()>0){
	             root=list.get(0); //第一个一定是根节点 0
	             
	           for(int i=1; i<list.size(); i++){
	        	   node=list.get(i);
	        	   if(node.getParent().equals(root.getId())){ //从跟节点开始遍历是不是子节点
	 
	        		   parentNodes.add(node);
	        		   root.getChildren().add(node);
	        	   
	        	   }else{ //获取root子节点的孩子节点
	        		    getChildrenNodes(parentNodes, node); 
	 
	        		    parentNodes.add(node);
	        	   }
	           }  
	     }
	     treelist.add(root);
 
	     return treelist;
	     
	     }
	
	private static void getChildrenNodes(List<SidebarTree> parentNodes , SidebarTree node){
		 for(int i=parentNodes.size()-1; i>=0; i--){
			  SidebarTree pnode=parentNodes.get(i);
			  
			  if(pnode.getId().equals(node.getParent())){
				  pnode.getChildren().add(node);
				  return;
			  } 
		 }
	}
	
	
	public static void main(String[] args) {
		SidebarTree s1 = new SidebarTree();
		s1.setId("1");
		s1.setParent("0");
		s1.setUrl("#");
		s1.setUrlname("总目录"); 
		SidebarTree s2 = new SidebarTree();
		s2.setId("10");
		s2.setParent("1");
		s2.setUrl("#");
		s2.setUrlname("一级目录1");
		SidebarTree s3 = new SidebarTree();
		s3.setId("20");
		s3.setParent("1");
		s3.setUrl("#");
		s3.setUrlname("一级目录2");
		
		SidebarTree s4 = new SidebarTree();
		s4.setId("1010");
		s4.setParent("10");
		s4.setUrl("#");
		s4.setUrlname("二级目录1");
		
		List<SidebarTree> l1 = new ArrayList();
		l1.add(s1);
		l1.add(s2); 
		l1.add(s3);
		l1.add(s4);
		
		s4.setUrlname("哈哈哈哈");
		
		TreeTest.formatTree(l1);
		
		
		
	}
	
}
