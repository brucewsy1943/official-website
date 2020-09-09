//假如文章的高度不够，设置最小高度
function resize(idSelector){
	//获取屏幕高度
	var screenHeight = $(window).height();
	//文档总高度
	var bodyHeight = $(document.body).height();
	/* alert(screenHeight);
	alert(bodyHeight) */
	
	/* var innerNavHeight = 31; */
	var secondNavHeight = $("#secondnavs").get(0).offsetHeight;
	var pathNavHeight = $("#path-nav").get(0).offsetHeight;
	var navHeight = $("#hd").get(0).offsetHeight;
	var footHeight = $("#ft").get(0).offsetHeight;
	var bannerHeight = $("#banner").get(0).offsetHeight;
	//文档高度假如小于屏幕高度，则把文章最小高度设为指定高度
	var minHeight = screenHeight - secondNavHeight - navHeight - footHeight - bannerHeight - pathNavHeight;
	//var idSelector = "#main-container";
	//设置文章的最小高度第一种情况
	if(bodyHeight <= screenHeight){
		$(idSelector).css("min-height",minHeight);
		return;
	}
}

/* resize(); */
//递归查找
function recursion(columnList,id,result){
	for (var i = 0; i < columnList.length; i++) {
		//出口
		if (columnList[i].id == id) {
			result.push(columnList[i]);
		}else if(columnList[i].nodes != null && columnList[i].nodes.length > 0){
			recursion(columnList[i].nodes,id,result)
		}
	}
	return result;
}

//根据columnname查找column
function recursionByColumnName(columnList,columnName,result){
	for (var i = 0; i < columnList.length; i++) {
		//出口
		if (columnList[i].text == columnName) {
			result.push(columnList[i]);
		}else if(columnList[i].nodes != null && columnList[i].nodes.length > 0){
			recursionByColumnName(columnList[i].nodes,columnName,result)
		}
	}
	return result;
}

//将columnId存入到sessionStorage
/* function saveColumn(columnId){
	sessionStorage.setItem("columnId",columnId);
} */

//将articleId存入到sessionStorage
/* function saveArticle(articleId){
	sessionStorage.setItem("articleId",articleId);
} */

/* function saveArticleAndColumn(articleId,columnId){
	sessionStorage.setItem("articleId",articleId);
	sessionStorage.setItem("columnId",columnId);
} */

//缓存文章id，栏目id，banner图
/* function saveInfo(articleId,columnId,bannerPic,columnName){
	sessionStorage.setItem("columnId",columnId);
	sessionStorage.setItem("articleId",articleId);
	sessionStorage.setItem("bannerPic",bannerPic);
	sessionStorage.setItem("columnName",columnName);
} */


//var bannerSrc = sessionStorage.getItem("bannerPic")


//给每个页面添加banner，index除外 
//banner图相对固定，为顶级栏目的导航图---研发中心除外
/* function addBanner(){
	//var columnId = sessionStorage.getItem("columnId");
	
	var columnId = getColumnId();
	var result = [];
	if(columnId == "" || columnId == null){
		return;
	}
	var firstColumnList =JSON.parse(sessionStorage.getItem("columnTree"));
	var column = recursion(firstColumnList,columnId,result).pop();
	var prev = "";
	while(column.parentId!=0){
		var resultTemp = [];
		prev = column;
		column = recursion(firstColumnList,column.parentId,resultTemp).pop();
		//研发中心不是用顶级菜单的导航图作为banner图
		//目前这个目录结构下可以这么写，但是假如技术研发也可以点击，那就不行了。
		if(column.text.indexOf("技术研发") != -1){
			column = prev;
			break;
		}
	}
	var href = window.location.href
	//首页不用添加banner图
	if(href.indexOf("index")==-1){
		if(column.bannerPic != null && column.bannerPic != ""){
			$("#banner").children(":first").attr("src",column.bannerPic);
			$("#banner").children(":first").css("width","100%");
		}
	}
} */



