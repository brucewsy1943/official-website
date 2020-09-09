function jumpToIndex(){
	var columnId = sessionStorage.getItem("columnId");
	var articleId = sessionStorage.getItem("articleId");
	if ((columnId == null || columnId ==undefined || columnId == "") && (articleId == null || articleId ==undefined || articleId == "")) {  
		window.location.href= "index.html";
	}
}
//jumpToIndex();
