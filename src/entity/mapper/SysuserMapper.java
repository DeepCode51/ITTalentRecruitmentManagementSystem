package entity.mapper;

import java.util.HashMap;
import java.util.List;
import entity.bean.*;
import org.apache.ibatis.annotations.Param;
import javax.servlet.http.HttpServletRequest;

public interface SysuserMapper {
	
	//查询全部记录
	List<HashMap<String, String>> findAll();

	//异步查询
	List<HashMap<String, String>> ajaxSelect(HashMap<String, String> map);

    //根据条件查询记录
	List<HashMap<String, String>> findByParam(HashMap<String, String> map);
	
	//新增一条记录
	void insert(HashMap<String, String> map);

    //删除一条记录 
	void delete(String id);

    //根据条件删除记录 
	void deleteByParam(HashMap<String, String> map);
	 
	//根据ID查找记录
	public HashMap<String, String> findByID(String id);

    //修改记录
	void update(HashMap<String, String> map);

    //通用新增方法
	void commInsert(@Param("tableName") String tablename,@Param("map") HashMap<String, String> ma);
	
	//通用修改方法
	void commUpdate(@Param("tableName") String tablename,@Param("map") HashMap<String, String> ma);
 

   // 查询功能 : 菜单选择查询  
   List<HashMap<String, String>> findexamsxgunametouname(HashMap<String, String> map);

   // 查询功能 : 菜单选择查询  
   List<HashMap<String, String>> findexamstjunametouname(HashMap<String, String> map);

   // 查询功能 : 菜单选择查询  
   List<HashMap<String, String>> findoffersxgunametouname(HashMap<String, String> map);

   // 查询功能 : 菜单选择查询  
   List<HashMap<String, String>> findofferstjunametouname(HashMap<String, String> map);

   // 查询功能 : 菜单选择查询  
   List<HashMap<String, String>> findzchatstjunametouname(HashMap<String, String> map);

   // 查询功能 : 用户信息管理  
   List<HashMap<String, String>> yhsysusercx(HashMap<String, String> map);

   // 查询功能 : 管理员信息管理  
   List<HashMap<String, String>> glysysusercx(HashMap<String, String> map);

   // 查询功能 : 企业信息管理  
   List<HashMap<String, String>> qysysusercx(HashMap<String, String> map);

   // 查询功能 : 会员信息管理  
   List<HashMap<String, String>> hysysusercx(HashMap<String, String> map);

   // 查询功能 : 站内资讯管理  
   List<HashMap<String, String>> sysusercx(HashMap<String, String> map);

   // 查询功能 : 站内资讯  
   List<HashMap<String, String>> znzxs(HashMap<String, String> map);

   // 查询功能 : 企业信息  
   List<HashMap<String, String>> qyxxs(HashMap<String, String> map);

   // 查询功能 : 人才信息  
   List<HashMap<String, String>> rcxxs(HashMap<String, String> map);

   // 查询功能 : 人才推荐  
   List<HashMap<String, String>> rctjsysusercx(HashMap<String, String> map);

   // 查询功能 : 专业领域可视化  
   List<HashMap<String, String>> kssysusercx(HashMap<String, String> map);

}