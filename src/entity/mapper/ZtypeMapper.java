package entity.mapper;

import java.util.HashMap;
import java.util.List;
import entity.bean.*;
import org.apache.ibatis.annotations.Param;
import javax.servlet.http.HttpServletRequest;

public interface ZtypeMapper {
	
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
 

   // 查询功能 : 专业领域  
   List<HashMap<String, String>> ztypecx(HashMap<String, String> map);

}