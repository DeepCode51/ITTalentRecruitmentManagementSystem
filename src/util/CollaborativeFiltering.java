package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class CollaborativeFiltering {
	
	public static void main(String[] args) {
		ArrayList<String> l = new ArrayList<String>(); 
		 
		l.add("123 程序员");
		l.add("456 ab 程序 abcd");
 
		
		CollaborativeFiltering.filter(l, "123");
	}
	  
	public static String test()
	{
		System.out.println("123");
		return "胡胡";
	}
	
	public static String filter(ArrayList<String> list,String uname) {
		/**
		 * 协同过滤算法实现
		 * 基于欧式距离，实现相似度计算
		 * 输入用户-->物品条目  一个用户对应多个物品
		 * 用户ID	物品ID集合
		 *   A		a b d
		 *   B		a c
		 *   C		b e
		 *   D		c d e
		 */
		String res = ""; 
		System.out.println("Input the total users number:");
		//输入用户总量
		int N = list.size();
		int[][] sparseMatrix = new int[N][N];//建立用户稀疏矩阵，用于用户相似度计算【相似度矩阵】
		Map<String, Integer> userItemLength = new HashMap();//存储每一个用户对应的不同物品总数  eg: A 3
		Map<String, Set<String>> itemUserCollection = new HashMap();//建立物品到用户的倒排表 eg: a A B
		Set<String> items = new HashSet();//辅助存储物品集合
		Map<String, Integer> userID = new HashMap();//辅助存储每一个用户的用户ID映射
		Map<Integer, String> idUser = new HashMap();//辅助存储每一个ID对应的用户映射
	 
		for(int i = 0; i < N ; i++){//依次处理N个用户 输入数据  以空格间隔
			String[] user_item = list.get(i).split(" ");
			int length = user_item.length;
			userItemLength.put(user_item[0], length-1);//eg: A 3
			userID.put(user_item[0], i);//用户ID与稀疏矩阵建立对应关系
			idUser.put(i, user_item[0]);
			//建立物品--用户倒排表
			for(int j = 1; j < length; j ++){
				if(items.contains(user_item[j])){//如果已经包含对应的物品--用户映射，直接添加对应的用户
					itemUserCollection.get(user_item[j]).add(user_item[0]);
				}else{//否则创建对应物品--用户集合映射
					items.add(user_item[j]);
					itemUserCollection.put(user_item[j], new HashSet<String>());//创建物品--用户倒排关系
					itemUserCollection.get(user_item[j]).add(user_item[0]);
				}
			}
		}
		System.out.println(itemUserCollection.toString());
		//计算相似度矩阵【稀疏】
		Set<Entry<String, Set<String>>> entrySet = itemUserCollection.entrySet();
		Iterator<Entry<String, Set<String>>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Set<String> commonUsers = iterator.next().getValue();
			for (String user_u : commonUsers) {
				for (String user_v : commonUsers) {
					if(user_u.equals(user_v)){
						continue;
					}
					sparseMatrix[userID.get(user_u)][userID.get(user_v)] += 1;//计算用户u与用户v都有正反馈的物品总数
				}
			}
		}
		System.out.println(userItemLength.toString());
		System.out.println("Input the user for recommendation:<eg:A>");
		String recommendUser = uname;
		System.out.println(userID.get(recommendUser));
		//计算用户之间的相似度【余弦相似性】
		int recommendUserId = userID.get(recommendUser);
		double[] ds = new double[sparseMatrix.length];
		HashMap<String, Double> m = new HashMap<String, Double>();
		for (int j = 0;j < sparseMatrix.length; j++) {
				if(j != recommendUserId){
					  
					    double b = sparseMatrix[recommendUserId][j]/Math.sqrt(userItemLength.get(idUser.get(recommendUserId))*userItemLength.get(idUser.get(j)));
					    ds[j] = b;
					    m.put(idUser.get(j), b);
					System.out.println(idUser.get(recommendUserId)+"--"+idUser.get(j)+"相似度:"+sparseMatrix[recommendUserId][j]/Math.sqrt(userItemLength.get(idUser.get(recommendUserId))*userItemLength.get(idUser.get(j))));
				}
		}
		
		Arrays.sort(ds);
		int x=ds.length;
		for(int i=ds.length-1;i>=0;i--)
		{
			ArrayList<String> ids = (ArrayList<String>)getKey(m, ds[i]);
			System.out.println(ids+" ----");
			for(String id:ids)
			{
			if(!res.contains("'"+id+"'"))
			res+="'"+id+"',";
			}
			if(x==ds.length-4)break;
			x--;
		}
		
		//计算指定用户recommendUser的物品推荐度
		for(String item: items){//遍历每一件物品
			Set<String> users = itemUserCollection.get(item);//得到购买当前物品的所有用户集合
			if(!users.contains(recommendUser)){//如果被推荐用户没有购买当前物品，则进行推荐度计算
				double itemRecommendDegree = 0.0;
				for(String user: users){
					itemRecommendDegree += sparseMatrix[userID.get(recommendUser)][userID.get(user)]/Math.sqrt(userItemLength.get(recommendUser)*userItemLength.get(user));//推荐度计算
				}
				System.out.println("The item "+item+" for "+recommendUser +"'s recommended degree:"+itemRecommendDegree);
			}
		} 
		if(res.length()>0)res=res.substring(0,res.length()-1);
		System.out.println(res);
		return res;
	}
 
	public static Object getKey(Map map, Object value){
	    List<Object> keyList = new ArrayList(); 
	    for(Object key: map.keySet()){
	        if(map.get(key).equals(value)&&(Double)value>0){
	            keyList.add(key);
	        }
	    }
	    return keyList;
	}

}
