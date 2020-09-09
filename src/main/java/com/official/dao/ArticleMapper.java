package com.official.dao;

import com.official.bean.dto.ArticleDto;
import com.official.bean.Article;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ArticleMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Article record);
    
    Article selectByPrimaryKey(Integer id);
    
    List<ArticleDto> selectAll();

    List<ArticleDto> getArticleListScience();
    
    List<ArticleDto> selectByParentId(@Param("parentId") Integer parentId,@Param("id")Integer  id);
    
    List<ArticleDto> selectByParentIdScience(@Param("parentId") Integer parentId,@Param("id")Integer  id);

    int updateByPrimaryKey(Article record);

    List<ArticleDto> fuzzySearch(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("keyword")String keyword,@Param("status")Integer status);

    List<ArticleDto> selectArticleSlide(Integer id);

    Integer articleStick(Article article);
    
    Integer previewPubnish(Article article);

    List<ArticleDto> selectByColumnId(@Param("columnId")Integer columnId);
}