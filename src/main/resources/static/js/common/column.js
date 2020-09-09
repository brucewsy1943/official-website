//统一添加树形结构
//获取路径中的columnId
//var columnId = window.location.href.split("?")[1].split("=")[1];
var columnId = getColumnId();
var columnList = getAllColumns();
//菜单树
var columnData = recursion(columnList,columnId,[]).pop();
var firstColumnData = getFirstColumnByColumnId(columnList,columnId);
var defaultData =firstColumnData.nodes

//返回栏目id
function getColumnId(){
	var columnId = null
	var href = window.location.href
	if(href.indexOf("columnId")!=-1){
		columnId = href.split("?")[1].split("&")[0].split("=")[1];
	}
	return columnId;
}

//返回父级栏目
function getParentColumn(columnId){
	var firstColumnList =JSON.parse(sessionStorage.getItem("columnTree"));
	var result = [];
	var column = recursion(firstColumnList,columnId,result).pop();
	var parentId = column.parentId;
	var parentColumn = recursion(firstColumnList,parentId,result).pop();
	return parentColumn;
}

//查找第一级菜单的columnId
function getFirstColumnByColumnId(columnList,columnId){
	var column = recursion(columnList,columnId,[]).pop();
	var columnResult = "";
	while(true){
		if(column.parentId == 0){
			columnResult = recursion(columnList,column.id,[]).pop();
			break;
		}
		column = recursion(columnList,column.parentId,[]).pop();
		console.log(column)
	}
	return columnResult; 
}

//获取所有的栏目
	function getAllColumns(){
		var columnsData = "";
			$.ajax({
				type:"post",
				url:baseUrl+"/column/getTree",
				async:false,
				success:function(e){
					console.log(e)
					sessionStorage.setItem("columnTree",JSON.stringify(e.data));
					columnsData = e.data;
				}
			});
		/* }else{
			columnsData =JSON.parse(columnsData);
		} */
		
		return columnsData;
	}



//生成推荐的标签
function getRecommendColumns(columnList,columnId,exceptions){
	var resultItems = ""
	var articleList = "";
	var column = recursion(columnList,columnId,[]).pop();
	console.log(column)
	if(column.text.indexOf("滚动新闻") != -1){//假如是首页的滚动新闻，则columnId用url里面的columnId
		columnId = getColumnId();
	}

	//直接找到当前的column
	column = recursion(columnList,columnId,[]).pop();
	console.log(column)
	var parentColumn = recursion(columnList,column.parentId,[]).pop();
	console.log(parentColumn)
	var columns = parentColumn.nodes;
	
	if(columns!=null && columns.length>1){
		var items = ""
		for (var i = 0; i < columns.length; i++) {
			//排除自己
			if(columns[i].id == columnId){
				continue;
			}
			//排除不需要的栏目
			if (exceptions.indexOf(columns[i].text) != -1) {
				continue;
			}
			
			var bannerImgSrc = columns[i].bannerPic;
			
			if (bannerImgSrc == null || bannerImgSrc == "") {
				bannerImgSrc = "../images/common/common-recommend.jpg";
			}
			items += '<div class="col-xs-2 clearfix">'
					+	'<a href="'+columns[i].href+'" class="recommend">'
					+			'<img class="img-responsive" src="'+bannerImgSrc+'" >'
					+			'<h5>'+columns[i].text+'</h5>'
					+	'</a>'
					+'</div>'
		}
		console.log(items)
		
	}
	return items;
}

//默认的推荐的article们数据--在本级栏目下没有文章的情况下，直接访问其他栏目
//var columnNames = ["核心团队","成长历程","单位荣誉","联系我们","中心介绍","成果展示","政策一览"]

//获取推荐的文章的总方法
/* function getRecommendedContents(){
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
	}else{
		if(articleList[0].title.indexOf("设备")==-1){
			getRecommendColumns();
		}
	}
} */