var sessionStorage = window.sessionStorage;
var columnId = getColumnId();
var firstColumnList = JSON.parse(sessionStorage.getItem("columnTree"));
function gerTransformPolicySonColumns(){
	var firstResult = [];
	var currentColumn = recursion(firstColumnList,columnId,firstResult).pop();
	console.log(currentColumn);
	var items = "";
	$("#article-section").empty();
	for (var i = 0; i < currentColumn.nodes.length; i++) {
		items += '<div class="policy clearfix">'
				+	'<div class="col-md-4">'
				+		'<i class="label">'+currentColumn.nodes[i].text+'</i>'
				+	'</div>'
				+	'<div class="col-md-8 policy-articles">'
				+		'<div class="list-group">'
				+			getPolicies(1,10,currentColumn.nodes[i].id)
				+		'</div>'
				+	'</div>'
				+ '</div>'
	}
	
	$("#article-section").html(items)
}

gerTransformPolicySonColumns()

function getPolicies(pageNum,pageSize,columnId){
			var articleItems = "";
			var articleList = getArticleListByColumn(columnId,pageNum,pageSize)
			for (var i = 0; i < articleList.length; i++) {
				articleItems += '<a href="/transform-article-template.html?columnId='+articleList[i].columnid+'&articleId='+articleList[i].id+'" class="list-group-item no-border">'+articleList[i].title+'</a>'
			}
	return articleItems;
}

