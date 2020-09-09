var sessionStorage = window.sessionStorage;
var columnId = getColumnId();

//var firstColumnList = JSON.parse(sessionStorage.getItem("columnTree"));

//获取孵化转化下面的子栏目----孵化转化项目、孵化项目、其他项目的栏目id，
//然后再分别调用不同的方法进行html的拼接
function getTranformSonColumns(){
	var firstResult = [];
	var currentColumn = recursion(getAllColumns(),columnId,firstResult).pop();
	console.log(currentColumn) 
	for (var i = 0; i < currentColumn.nodes.length; i++) {
		//用栏目标识来进行区分，因为不同的栏目，布局不同。其他项目和
		if(currentColumn.nodes[i].refno == "brooding-project"){
			getTransformProjects(1,4,currentColumn.nodes[i].id,"project1");
			
		}else if(currentColumn.nodes[i].refno == "brooding-projects2"){
			getTransformProjects(1,4,currentColumn.nodes[i].id,"project2");
			
		}else{
			getOtherProjects(1,4,currentColumn.nodes[i].id,"other-project");
			
		}
	}
}
getTranformSonColumns();

//获取其他项目
function getOtherProjects(pageNum,pageSize,columnId,tagId){
			var articleList = getArticleListByColumn(columnId,pageNum,pageSize);
			$("#"+tagId).empty();
			$("#"+tagId).append(articleList[0].content);
}
//孵化转化项目/孵化项目
function getTransformProjects(pageNum,pageSize,columnId,tagId){
			var articleList = getArticleListByColumn(columnId,pageNum,pageSize);
			var articleItems = "";
			$("#"+tagId).empty();
			for (var i = 0; i < articleList.length; i++) {
				articleItems += '<li class="list-group-item col-md-3">'
							+		'<a href="/transform-article-template.html?columnId='+columnId+'&articleId='+articleList[i].id+'" >'
							+			'<img class="img-responsive" src="'+articleList[i].navpicturepath+'">'
							+			 '<label>'
							+				articleList[i].subTitle
							+			'</label>' 
							+			'<label>'
							+				articleList[i].title
							+			'</label>'
							+		'</a>'
							+	'</li>'
			}
			$("#"+tagId).append(articleItems);
}