//根据栏目id查找文章列表
function getArticleListByColumn(columnId,pageNum,pageSize){
	var articleList = "";
	$.ajax({
		type:"post",
		url:baseUrl+"/article/getByColumn",
		async:false,
		data:{
			columnId:columnId,
			pageNum:pageNum,
			pageSize:pageSize
		},
		success:function(res){
			articleList = res.data.list;
			totalItems = res.data.total;
			totalPages = res.data.pages;
		}
	});
	return articleList;
}

function getArticleInfo(articleId){
	var article = "";
	$.ajax({
		type:"post",
		url:baseUrl+"/article/getInformation",
		async:false,
		data:{
			id:articleId
		},
		success:function(res){
			console.log(res);
			article = res.data;
		}
	})
	return article;
}


//根据url获取文章id
function getArticleId(){
	var href = window.location.href
	var articleId = null;
	/* if(href.indexOf("&")==-1 && href.indexOf("?")==-1){//假如啥也没有，则直接返回
		return articleId;
	}
	if(href.indexOf("&")!=-1){
		articleId = href.split("?")[1].split("&")[1].split("=")[1];
	}else{
		articleId = href.split("?")[1].split("=")[1];
	} */
	if(href.indexOf("articleId") != -1){
		articleId = href.split("&")[1].split("=")[1];
	}
	
	return articleId;
}


//生成推荐的标签
function getRecommendArticles(articleList,columnId,exceptions,article,template){
	//var columnList = JSON.parse(sessionStorage.getItem("columnTree"));
		var items = ""
		
		
		for (var i = 0; i < articleList.length; i++) {
			//排除自己
			if(articleList[i].title == article.title){
				continue;
			}
			
			if (exceptions.indexOf(articleList[i].title) != -1) {
				continue;
			}
			
			var bannerImgSrc = articleList[i].navpicturepath;
			//假如文章没有导航图，则用栏目对应的导航图
			if (bannerImgSrc == null || bannerImgSrc == "") {
				var result = [];
				var column = recursion(columnList,articleList[i].columnid,result).pop();
				bannerImgSrc = column.bannerPic;
			}
			//为了IE的兼容性。没有src的时候，不加banner类
			if(bannerImgSrc == null || bannerImgSrc == ""){
				$("#banner").children("first").addClass("banner");
			}
				
			items += '<div class="col-xs-2 clearfix">'
					+	'<a href="'+template+'?columnId='+columnId+'&articleId='+articleList[i].id+'" class="recommend">'
					+			'<img class="img-responsive" src="'+bannerImgSrc+'" >'
					+			'<h5>'+articleList[i].title+'</h5>'
					+	'</a>'
					+'</div>'
		}
		return (items);
}

//获取推荐---由于首页滚动新闻的问题
function getRecommendedContents(){
	var articleList = "";
	var columnId = article.columnid
	var result = [];
	var column = recursion(columnList,columnId,result).pop();
	if(column.text.indexOf("滚动新闻") != -1){//假如是首页的滚动新闻，则columnId用url里面的columnId
		columnId = getColumnId();
	}
	var articleList = getArticleListByColumn(columnId,1,10);//article.columnid
	//columnId是article对应的columnId。不一定是存在session中的columnId
	if(articleList!=null && articleList.length>1){//需要有除了自己以外的文章
		getRecommendArticles(articleList);
	}
}
//推荐的文章的标签
function getRecommendTitle(title){
	var recommendTitle = '<div class="col-xs-12">'
						+	'<h3 class="recommendTitle">'
						+		'<s></s>' + title
						+	'</h3>'
						+ '</div>'
	return recommendTitle;
}
