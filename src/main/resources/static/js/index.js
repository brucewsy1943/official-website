var gap = 20; //竖向的块之间的间隔
//研发中心动态高度
function setTechCenterCarsouralButtonHeight() {
	var height = $("#carousel-imgs").height() - gap * 2; //以这张图为基准
	$("#project1").children().each(function(index, element) {
		$(this).css("height", height / 3);
		//最后一个,不加margin-bottom
		if (index == 2) {
			return;
		}
		$(this).css("margin-bottom", gap);
	})
}
//setTechCenterCarsouralButtonHeight();

//(最后一个模板)动态高度
function setHeight() {
	var height = $("#banner2").height() - gap;
	$(".newscolumn").each(function() {
		$(this).css("height", height / 2);
		$(this).css("margin-bottom", gap);
	})
}
//setHeight();

//轮播添加鼠标上移事件
$(".carousel-indicators").children().each(function() {
	$(this).mouseover(function() {
		$(this).click();
	})
})

//添加鼠标移入事件
$("#project1").children().each(function(index, element) {
	var index = index;
	var imags = $("#carousel-imgs").children();
	//图片和按钮的索引一一对应
	$(this).mouseover(function() {
		for (var i = 0; i < imags.length; i++) {
			if (i == index) {
				$(imags[i]).show();
				continue;
			}
			$(imags[i]).hide();
		}
	})
})

$("#banner2").mouseover(function() {
	$(this).addClass("add-background");
})
$("#banner2").mouseleave(function() {
	$(this).removeClass("add-background");
});

//动画
AOS.init({
	easing: 'ease',
	duration: 700,
	disable: 'mobile'
});
var firstColumnList = JSON.parse(sessionStorage.getItem("columnTree"));
console.log(firstColumnList);
//滚动新闻---分两部分：一部分是新闻动态的内容。另一部分是首页滚动新闻栏目的内容。
getRollingNews();
function getRollingNews() {
	var li = ""
	/* var newsColumnId = 38; //新闻动态的columnId---暂时写死!
	var articleList = getArticleListByColumn(newsColumnId, 1, 100);
	var result = [];
	var newsColum = recursion(firstColumnList,newsColumnId,result).pop();
	for (var i = 0; i < articleList.length; i++) {
		//if(articleList[i].linkurl == null || articleList[i].linkurl == ""){
			li += '<li>'+
					'<span style="font-size:14px">【'+newsColum.text+'】</span>'+
					'<a href="article-template.html?articleId='+articleList[i].id+'&columnId='+newsColumnId+'" >' + articleList[i].title + '</a>' +
				  '</li>'
		//}
	} */
	
	var rollingColumnId = 58;//滚动新闻的columnId---暂时写死!
	var rollingArticleList = getArticleListByColumn(rollingColumnId, 1, 100);
	for (var i = 0; i < rollingArticleList.length; i++) {
		if(rollingArticleList[i].linkurl == "" || rollingArticleList[i].linkurl == null){
			var result = [];
			var column = recursionByColumnName(firstColumnList,rollingArticleList[i].subTitle,result).pop();
			li += '<li>'+
					'<span style="font-size:14px">【'+rollingArticleList[i].subTitle+'】</span>'+
					'<a href="article-template.html?columnId='+column.id+'&articleId='+rollingArticleList[i].id+'" >' + rollingArticleList[i].title + '</a>' +
				  '</li>'
		}else{
			
			li += '<li>' +
					'<span style="font-size:14px">【'+rollingArticleList[i].subTitle+'】</span>'+
					'<a target="_blank" href="'+rollingArticleList[i].linkurl+'">' + rollingArticleList[i].title + '</a>' +
				  '</li>'
		}
	}
	/* var notificationColumnId = 61;//通知公告的columnId---暂时写死!
	var notifyArticleList = getArticleListByColumn(notificationColumnId, 1, 100);
	var result = [];
	var notifyColum = recursion(firstColumnList,notificationColumnId,result).pop();
	for (var i = 0; i < notifyArticleList.length; i++) {
			li += '<li>'+
					'<span style="font-size:14px">【'+notifyColum.text+'】</span>'+
					'<a href="article-template.html?articleId='+notifyArticleList[i].id+'&columnId='+notificationColumnId+'" >' + notifyArticleList[i].title + '</a>' +
				  '</li>'
	} */
	
	$(".donate_bar").append(li);
}

//获取首页文章
var indexColumnId = 59;//路径中获取首页idwindow.location.href.split("?")[1].split("=")[1]
function generateIndexArticles(){
	var articleList =  getArticleListByColumn(indexColumnId,1,1);//columnId,pageNum,pageSize
	if(articleList!=null && articleList.length > 0){
		//研究院简介
		var introArticle = articleList[0];
		console.log(introArticle)
		if(introArticle.title!=null && introArticle.title!=""){
			$(".article-head").children(":first").html(introArticle.title);
		}
		if (introArticle.subTitle!=null && introArticle.subTitle!="") {
			$(".article-subhead").children(":first").html(introArticle.subTitle);
		}
		
		if(introArticle.content!=null && introArticle.content!=""){
			$(".article-content").html(introArticle.content);
		}else{
			$(".article-content").html("<h3 style='text-align:center'>暂未发布文章！</h3>");
		}
	}
	
	//添加简介链接
	var introColumnId = 60;//苏研院详细介绍的id
	var articleList2 = getArticleListByColumn(introColumnId,1,1);
	sessionStorage.setItem("columnName","苏研院介绍");
	var href = "article-template.html?columnId="+introColumnId+"&articleId="+articleList2[0].id;
	$("#more").attr("href",href);
	//$("#more").attr("onclick",'saveArticleAndColumn(' + articleList2[0].id + ',' + introColumnId + ')');
}
generateIndexArticles();

$("#hd").bind("DOMNodeInserted",function(){
	//这数组里面的个数要和index页面个数和顺序一致！！！！
	var achievements = ["细胞库", "质粒库", "仪器共享平台", "中心介绍", "细胞质检","基因编辑","细胞研发","重大科技项目"];
	var columnList = JSON.parse(sessionStorage.getItem("columnTree"));
	$("#achievements").find("a").each(function(index, elem) {
		if (columnList != null && columnList.length > 0) {
			var result = [];
			if (achievements[index] == null || achievements[index] == undefined || achievements[index] == "") {
				$(this).attr("href", "#");
				return true;
			}
			var column = getTagByContent(achievements[index]);

			if (column == null || column == undefined || column == "" || column.attr("href") == null || column.attr("href") == "") {
				$(this).attr("href", "#");
				return true;
			}
			$(this).attr("href", column.attr("href"));
			if (achievements[index].indexOf("质粒库")!=-1 || achievements[index].indexOf("仪器") != -1) {
				$(this).attr("target", "_blank");
			}
		}
	})
})
//根据标签内容查找标签
function getTagByContent(columnName) {
	var tag = "";
	$("#navs").find("a").each(function(index,element){
		if ($(this).html() == columnName) {
			tag = $(this);
			return false;
		}
	})
	return tag;
}