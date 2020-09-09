var sessionStorage = window.sessionStorage;
//获取路径中的columnId
var columnId = getColumnId();

//点击事件
$("#submit-message").click(function(){
	addMessage();
})

//添加留言
function addMessage(){
	if($("#message").val() == null || $("#message").val() == ""){
		alert("请输入留言内容！");
		return;
	}
	
	 $.ajax({
		type: "post",
		url: baseUrl + "/leavemessage/add",
		async: true,
		data:$('#message-form').serialize(),
		success: function(res) {
			console.log(res);
			if (res.code == 200) {
				alert('留言成功!感谢留言！');
			}
		}
	}) 
}	

//两边同高
function sameHeight(){
	var height = $("#right-part").children(":last").height();
	$("#right-part").children(":first").css("height",height);
	
}
sameHeight();











