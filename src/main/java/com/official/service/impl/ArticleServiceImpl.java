package com.official.service.impl;

import java.util.List;

import com.official.bean.Article;
import com.official.bean.dto.ArticleDto;
import com.official.bean.dto.PageDto;
import com.official.dao.ArticleMapper;
import com.official.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	
	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		int n = articleMapper.deleteByPrimaryKey(id);
		return n;
	}

	@Transactional
	@Override
	public int insert(Article record) {
		int n = articleMapper.insert(record);
		return n;
	}

	@Override
	public Article selectByPrimaryKey(Integer id) {
		
		return articleMapper.selectByPrimaryKey(id);
	}


	@Transactional
	@Override
	public int updateByPrimaryKey(Article record) {
		int n = articleMapper.updateByPrimaryKey(record);
		return n;
	}

	@Override
	public PageInfo<ArticleDto> selectAll(PageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
		List<ArticleDto> articleDtos = articleMapper.selectAll();
		PageInfo<ArticleDto> pagelist = new PageInfo<ArticleDto>(articleDtos);
		return pagelist;
	}


	@Override
	public PageInfo<ArticleDto> selectByParentId(Integer parentId, Integer id,PageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
		List<ArticleDto> articleDtos = articleMapper.selectByParentId(parentId, id);
		PageInfo<ArticleDto> pagelist = new PageInfo<ArticleDto>(articleDtos);
		return pagelist;
	}

	@Override
	public PageInfo<ArticleDto> fuzzySearch(String startTime, String endTime, String keyword, PageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
		List<ArticleDto> articleDtos = articleMapper.fuzzySearch(startTime, endTime, keyword);
		PageInfo<ArticleDto> pagelist = new PageInfo<ArticleDto>(articleDtos);
		return pagelist;
	}

	@Override
	public List<ArticleDto> selectArticleSlide(Integer id) {
		
		return articleMapper.selectArticleSlide(id);
	}

	@Transactional
	@Override
	public Integer articleStick(Article article) {
		
		return articleMapper.articleStick(article);
	}

	@Override
	public PageInfo<ArticleDto> selectByParentIdScience(Integer parentId, Integer id, PageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
		List<ArticleDto> articleDtos = articleMapper.selectByParentIdScience(parentId, id);
		PageInfo<ArticleDto> pagelist = new PageInfo<ArticleDto>(articleDtos);
		return pagelist;
	}

	@Override
	public PageInfo<ArticleDto> getArticleListScience(PageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
		List<ArticleDto> articleDtos = articleMapper.getArticleListScience();
		PageInfo<ArticleDto> pagelist = new PageInfo<ArticleDto>(articleDtos);
		return pagelist;
	}

	@Transactional
	@Override
	public Integer previewPubnish(Article article) {
		
		return articleMapper.previewPubnish(article);
	}

	@Override
	public PageInfo<ArticleDto> getByColumn(Integer columnId, PageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
		List<ArticleDto> articleDtos = articleMapper.selectByColumnId(columnId);
		PageInfo<ArticleDto> pagelist = new PageInfo<ArticleDto>(articleDtos);
		return pagelist;
	}
}
