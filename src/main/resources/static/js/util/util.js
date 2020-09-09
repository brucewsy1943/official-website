
//提取数字 例如：价格4500元
function getNumberInStr(str){
	return str.replace(/[^0-9]/ig,"");
}