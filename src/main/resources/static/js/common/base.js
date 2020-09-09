//var baseUrl = "http://47.105.212.155";
//var baseUrl = "http://192.168.1.44:8080";
//var baseUrl = "http://localhost:8080";
//var baseUrl = "http://www.brics.ac.cn:8888";
//var baseUrl = "http://10.42.1.117:8080";
var baseUrl = "http://www.brics.ac.cn";
var sessionStorage = window.sessionStorage;
//window.location.href= "http://192.168.1.122:8080/owfp/template/article-template.html";
/* function jumpToIndex(){
	alert(1)
	var columnId = sessionStorage.getItem("columnId");
	if (columnId == null || columnId ==undefined || columnId == "") {  
		window.location.href= "index.html";
	}
} */
/* $.ajaxSetup({
	async:false,
	complete: function(XMLHttpRequest,status) {    
		var columnId = sessionStorage.getItem("columnId");//若HEADER中含有REDIRECT说明后端想重定向    
		if (columnId == null || columnId ==undefined || columnId == "") {  
			var win = window;      
			while (win != win.top){    
				win = win.top;    
			}  
			win.location.href= "index.html";
		}
	}
}); */

/* //排除列表们
//相关业务排除的文章清单（这些文章后面）
var articleTitles = ["细胞质量检测介绍","细胞研发介绍","病毒定制服务介绍","细胞库介绍"];

//排除的栏目清单（这些后面不加推荐的栏目）
var columnNames = ["联盟简介","会员单位","质粒库","新闻动态","行业资讯","人才招聘","组织架构","规章制度","党员活动","学习园地"];

//var columnId = sessionStorage.getItem("columnId");
var findParentColumnAray = ["政策一览","成果展示","学习园地"];//这些column的子级在显示路径的时候要显示他们父级的column

//是否添加二级导航栏
var secondColumnExceptionArray = ["中心介绍","成果展示","政策一览",'人才激励政策','财政引导政策','产业扶持政策','转移转化项目','孵化项目','滚动新闻']

//二级菜单继续添加下拉
var exceptionArray = ["新闻动态","业务动态","通知公告","人才招聘","组织架构","党建制度","党员活动","学习园地"];//这些菜单排除,不要下拉。需要下拉的主要集中在技术研发中心的菜单 */
