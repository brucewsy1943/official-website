var sessionStorage = window.sessionStorage;
var keyword = sessionStorage.getItem("keyword")
function fuzzySearch(pageNum, pageSize) {
	$.ajax({
		type: "post",
		//contentType: 'application/json;charset=UTF-8',
		url: baseUrl + "/article/fuzzySearch",
		async: false,
		data: {
			pageNum: pageNum,
			pageSize: pageSize,
			keyword: keyword,
			status:1
		},
		success: function(res) {
			console.log(res);
			totalItems = res.data.total;
			totalPages = res.data.size;
			var articleList = res.data.list;
			var articleItems = "";
			var intro = "";
			$("#content-list").children(":first").empty();
			if(articleList.length>0){
				for (var i = 0; i < articleList.length; i++) {
					if(articleList[i].interception != null && articleList[i].interception != "" && articleList[i].interception !=undefined){
						intro = '<div class="introduction">简介：'+articleList[i].interception+'...</div>'
					}
				articleItems += '<div class="list-group-item">' 
									+'<div class="title">'
										+'<a href="/articleFront/detail?columnId='+articleList[i].columnid+'&articleId='+articleList[i].id+'">' + articleList[i].title
										+'</a>' 
									+'</div>' 
									+'<div class="date">' 
									+'发布时间:' + articleList[i].pubtime 
									+'</div>'
									+ intro
								+'</div>'
				}
				$("#content-list").children(":first").append(articleItems);
			}else{
				$("#content-list").children(":first").html("<h3 style='text-align:center'>无符合的记录！</h3>");
			}
			
		}
	})
}

//初始化分页参数
var totalItems = 0;
var totalPages = 0;
var pageSize = 5;
var pageNum = 1;
layui.use('laypage', function() {
	fuzzySearch(pageNum, pageSize);
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
			fuzzySearch(obj.curr, pageSize);
		}
	});
});

resize("#content-list");