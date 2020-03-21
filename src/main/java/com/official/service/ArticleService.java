package com.official.service;

import java.util.List;

import com.official.bean.dto.ArticleDto;
import com.github.pagehelper.PageInfo;
import com.official.bean.Article;
import com.official.bean.dto.PageDto;

public interface ArticleService {
	   int deleteByPrimaryKey(Integer id);


	    int insert(Article record);

	    
	    Article selectByPrimaryKey(Integer id);

	    
	   PageInfo<ArticleDto> selectAll(PageDto pageDto);

	   PageInfo<ArticleDto> getArticleListScience(PageDto pageDto); 
	   
	    int updateByPrimaryKey(Article record);
	    
	    
	    PageInfo<ArticleDto> fuzzySearch(String startTime,String endTime,String keyword,PageDto pageDto);
	    
	    
	    PageInfo<ArticleDto> selectByParentId( Integer parentId,Integer  id,PageDto pageDto);

	    PageInfo<ArticleDto> selectByParentIdScience( Integer parentId,Integer  id,PageDto pageDto);

	    List<ArticleDto> selectArticleSlide(Integer id);
	    
	    //文章置顶
	    Integer articleStick(Article article);
	    
	    //预览发布
	    Integer previewPubnish(Article article);

		PageInfo<ArticleDto> getByColumn(Integer columnId,PageDto pageDto);
}
