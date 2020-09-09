var columnId = getColumnId();
/* var firstColumnList =JSON.parse(sessionStorage.getItem("columnTree"));
var result = [];
var column = recursion(firstColumnList,columnId,result).pop(); */
/* $("#column-title").html(column.text); */

//根据column查找文章
function getArticleListTags(pageNum, pageSize) {
		var articleList = getArticleListByColumn(columnId,pageNum,pageSize)
		var articleItems = "";
		$("#content-list").children(":first").empty();
		if(articleList.length>0){
			for (var i = 0; i < articleList.length; i++) {
				if(articleList[i].linkurl!=null && articleList[i].linkurl!=""){
					articleItems += '<div class="list-group-item">' +
						'<div class="title">' +
						'<a target="_blank" href="'+articleList[i].linkurl+'">' + articleList[i].title +
						'</a>' +
						'</div>' +
						'<div class="date">' +
						'发布时间:' + articleList[i].pubtime +
						'</div>' +
						'</div>'
				}else{
					articleItems += '<div class="list-group-item">' +
						'<div class="title">' +
						'<a href="articleFront/detail?columnId='+columnId+'&articleId='+articleList[i].id+'">' + articleList[i].title +
						'</a>' +
						'</div>' +
						'<div class="date">' +
						'发布时间:' + articleList[i].pubtime +
						'</div>' +
						'</div>'
				}
				
				
			}
			$("#content-list").children(":first").append(articleItems);
		}else{
			$("#content-list").children(":first").html("<h3 style='text-align:center'>正在建设中！</h3>");
			$("#page").hide();
		}

}
//初始化page参数
var pageNum = 1;
var pageSize = 8;

layui.use('laypage', function() {
	getArticleListTags(pageNum, pageSize);
	var laypage = layui.laypage;
	//执行一个laypage实例
	laypage.render({
		elem: 'page', //注意，这里的 page 是 ID，不用加 # 号
		limit: pageSize,//每页显示几条
		count: totalItems, //数据总数，从服务端得到
		groups:1,
		jump: function(obj, first) {
			//obj包含了当前分页的所有参数，比如：
			console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
			console.log(obj.limit); //得到每页显示的条数
			//首次不执行
			/* if (!first) {
				return;
			} */
			getArticleListTags(obj.curr, pageSize);
		}
	});
});

/* resize("#content-list"); */
