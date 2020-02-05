package com.official.service;

import java.util.List;
import com.official.bean.Link;
public interface LinkService {
	   int deleteByPrimaryKey(Integer id);

	    
	    int insert(Link record);

	    
	    Link selectByPrimaryKey(Integer id);

	    
	    List<Link> selectAll(Integer status);

	    List<Link> fuzzySearch(Integer status,String keyword);
	    
	    int updateByPrimaryKey(Link record);
}
