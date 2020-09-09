var sessionStorage = window.sessionStorage;
var columnId = getColumnId();
var columnList = JSON.parse(sessionStorage.getItem("columnTree"));
//细胞库介绍
function addIntroduction(){
	var article = "";
	var articleList = getArticleListByColumn(columnId,1,100);
	for (var i = 0; i < articleList.length; i++) {
		if(articleList[i].title.indexOf("介绍")!=-1){
			article = articleList[i];
			break;
		}
	}
	if(article!=null && article!=""){
		$("#cell-article-border").addClass("article-border")
		$("#cell-aticle-section").addClass("article-section")
		$(".article-head").children(":first").html(article.title);
	}
	if(article.content!=null && article.content!=""){
		$(".article-content").html(article.content);
	}
}

//addIntroduction();

//相关业务
function getBusinessForTechnology(columnList,columnId,title){
	var articleList = "";
	var result = [];
	var column = recursion(columnList,columnId,result).pop();
	//直接找到当前的column
	var parentColumn = column;
	var columns = parentColumn.nodes;
	
	if(columns!=null && columns.length>1){
		var items = ""
		var recommendTitle = '<div class="col-xs-12">'
							+	'<h3 class="recommendTitle">'
							+		'<s></s>' + title
							+	'</h3>'
							+ '</div>'
		for (var i = 0; i < columns.length; i++) {
			//排除自己
			if(columns[i].id == columnId){
				continue;
			}
			
			var bannerImgSrc = columns[i].bannerPic;
			
			if (bannerImgSrc == null || bannerImgSrc == "") {
				bannerImgSrc = "../images/common/common-recommend.jpg";
			}
			items += '<div class="col-md-3">'
					+	'<a href="'+columns[i].href+'" class="recommend add-border">'
					+			'<img class="img-responsive" src="'+bannerImgSrc+'" >'
					+			'<div class="txt">'
					+				'<div class="tit">'+columns[i].text+'</div>'
					+			'</div>'
					+	'</a>'
					+'</div>'
		}
		if(items != ""){
			$("#relatedbusiness").html(recommendTitle + items);
			$("#relatedbusiness").addClass("article-border");
		}
	}
}
getBusinessForTechnology(columnList,columnId,"相关业务");


