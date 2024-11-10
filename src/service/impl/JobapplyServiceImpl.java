package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List; 
import entity.bean.*; 
import entity.mapper.*; 
import service.*; 
import java.util.HashMap;
import javax.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import dao.CommDAO;
import javax.servlet.http.HttpServletRequest;
import util.PageManager;
 

@Service("JobapplyService")
public class JobapplyServiceImpl implements JobapplyService {
	 
	@Resource
	public  JobapplyMapper mapper;
   	
	@Override
	public List<HashMap<String, String>> findAll()
	{
		return mapper.findAll();
	}

	@Override
	public List<HashMap<String, String>> ajaxSelect(HashMap<String, String> map)
	{ 
		return mapper.ajaxSelect(map);
	}

    @Override
	public List<HashMap<String, String>> findByParam(HashMap<String, String> map)
	{
		if(map.get("orderby")==null)map.put("orderby", "id desc");
		return mapper.findByParam(map);
	}

	@Override
	public HashMap<String, String> findByID(String id)
	{
		return mapper.findByID(id);
	}

	@Override
	public List<HashMap> findByParamWithPages(String selectMethod,HashMap<String, String> selectmap,int pageSize,String pageUrl,HttpServletRequest request)
	{
		List<HashMap> list = PageManager.getPages(pageUrl,pageSize, JobapplyMapper.class.getName()+"."+selectMethod,selectmap, request ); 
		if(!"Jobapply".equals("Sysuser"))
		list = new CommDAO().getTname(list);
		return list;
	}
	
	@Override
	public void insert(HashMap<String, String> map)
	{
        mapper.insert(map);
	}

    @Override
	public void delete(String id)
	{
		mapper.delete(id);
	}

	@Override
	public void deleteByParam(HashMap<String, String> map)
	{
         mapper.deleteByParam(map);
	}


    @Override
	public void update(HashMap<String, String> map)
	{
		mapper.update(map);

	}

    @Override
	public void commInsert(@Param("tableName") String tablename,@Param("map") HashMap<String, String> ma)
	{
		mapper.commInsert(tablename,ma);
	}
	
	@Override
	public void commUpdate(@Param("tableName") String tablename,@Param("map") HashMap<String, String> ma)
	{
		mapper.commUpdate(tablename,ma);
    }
 

}