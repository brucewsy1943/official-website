package com.official.controller;

import com.github.pagehelper.PageInfo;
import com.official.bean.Article;
import com.official.bean.dto.ArticleDto;
import com.official.bean.dto.PageDto;
import com.official.exception.CustomException;
import com.official.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2020/8/4.
 */
@Controller
@RequestMapping("/articleFront")
public class ArticleFrontController {

    @Autowired
    private ArticleService articleService;

    //通用详情
    //两种情况，有的时候，column下来直接就是文章却只有一个columnId而没有articleId
    @RequestMapping("/detail")
    public String getArticleInformation(Integer articleId, Integer columnId, Model model, PageDto pageDto){
        Article article =  articleService.getFrontArticle(columnId,articleId,pageDto);
        model.addAttribute("article",article);
        if (article==null) {
            throw new CustomException("获取文章信息失败!");
        }
        return "article-template";
    }

    //孵化中心详情


    //技术研发第一级菜单
    //通用详情
    //两种情况，有的时候，column下来直接就是文章却只有一个columnId而没有articleId
    @RequestMapping("/techArticle")
    public String getArticleInformationForTech(Integer articleId, Integer columnId, Model model, PageDto pageDto){
        Article article =  articleService.getFrontArticle(columnId,articleId,pageDto);
        model.addAttribute("article",article);
        if (article==null) {
            throw new CustomException("获取文章信息失败!");
        }
        return "technology-article";
    }


}
