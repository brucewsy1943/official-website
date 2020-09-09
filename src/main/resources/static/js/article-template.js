//recursion(columnList,columnData.parentId,[]).pop().nodes;
var articleId = getArticleId();
var article = "";
var articleList = "";
getArticleTag(); 
//根据栏目id查找文章列表---这里主要是查出文章
function getArticleTag(){
	articleList = getArticleListByColumn(columnId,1,100);//必查 推荐文章的时候要用的
	if(articleId != null){
		article = getArticleInfo(articleId);
	}else{
		if(articleList.length > 0){
			article = articleList[0];
		}
	}
	if(article.title!=null && article.title!=""){
		$(".article-head").children(":first").html(article.title);
	}
	if(article.content!=null && article.content!=""){
		$(".article-content").html(article.content);
	}else{
		$(".article-content").html("<h3 style='text-align:center'>暂未发布文章！</h3>");
	}
}
//排除的栏目清单
var excludeColumnNames = ["联盟简介","会员单位","质粒库","新闻动态","行业资讯","人才招聘","组织架构","规章制度","党员活动","学习园地","人才激励政策","财政引导政策","产业扶持政策"];
//排除的栏文章清单
var excludeArticles = ["细胞质量检测介绍","细胞研发介绍","病毒定制服务介绍","细胞库介绍","会员单位","苏州工业园区细胞产业创新技术协会"];
function getRecommendLinks(){
	var template = judgeTemplateType();//根据不同的template进行推荐column的拼接
	if(articleList.length>1){//放的是文章
		//如果路径中带了articleId
		var recommendColumnContent = ""
		if(articleId!=null && articleId!=""){
			recommendColumnContent = getRecommendArticles(articleList,columnId,excludeArticles,article,template)
		}else{
			recommendColumnContent = getRecommendArticles(articleList,columnId,excludeArticles,article,template)
		}
		if(recommendColumnContent!="" && recommendColumnContent!=null){
			$("#recommend").html(getRecommendTitle("相关链接")+recommendColumnContent);
			$("#recommend").addClass("article-border")
		}
	}else{//放的是栏目
		var recommendColumnContent = getRecommendColumns(columnList,columnId,excludeColumnNames)
		if(recommendColumnContent!="" && recommendColumnContent!=null){
			$("#recommend").html(getRecommendTitle("相关链接")+recommendColumnContent);
			$("#recommend").addClass("article-border")
		}
	}
}
getRecommendLinks();


//判断模板类型
function judgeTemplateType(){
	var transformCenter = "中心介绍,成果展示,政策一览,人才激励政策,财政引导政策,产业扶持政策,转移转化项目,孵化项目,滚动新闻"
	console.log(columnData.text)
	if(transformCenter.indexOf(columnData.text)!=-1){
		return "/transform-article-template.html";
	}else{
		return "/articleFront/detail";
	}
}

//是否添加二级导航栏
/* var secondColumnExceptionArray = ["中心介绍","成果展示","政策一览",'人才激励政策','财政引导政策','产业扶持政策','转移转化项目','孵化项目','滚动新闻']
function judgeSecondNav(){
	//var columnList = JSON.parse(sessionStorage.getItem("columnTree"));
	var result = [];
	var column = recursion(columnList,columnId,result).pop();
	var columnName = column.text;
	 if(secondColumnExceptionArray.indexOf(columnName)!=-1){
		$("#secondnavs").hide();
	} 
} 

judgeSecondNav(); */

//是否添加二级导航栏
/* 	var secondColumnExceptionArray = ["中心介绍","成果展示","政策一览",'人才激励政策','财政引导政策','产业扶持政策','转移转化项目','孵化项目','滚动新闻']
	function judgeSecondNav(){
		//var columnList = JSON.parse(sessionStorage.getItem("columnTree"));
		var result = [];
		var column = recursion(columnList,columnId,result).pop();
		var columnName = column.text;
		 if(secondColumnExceptionArray.indexOf(columnName)!=-1){
			$("#secondnavs").hide();
		} 
	} 
	
	judgeSecondNav(); */






