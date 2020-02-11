function userName() {
	var userNameVal = $(".userName").val();
	if(userNameVal == "") {
		Info.view('请输入登录账号');
		return false;
	} else {
		return true;
	}
}

function psWordVal() {
	var psWordVal = $(".psWordNum").val();
	if(psWordVal == "") {
		Info.view('请输入密码');
		return false;
	} else {
		return true;
	}
}
function signBtn() {
	var flag = userName() && psWordVal();
	if(flag == true) {
		alert("ok")
	} else {}
}