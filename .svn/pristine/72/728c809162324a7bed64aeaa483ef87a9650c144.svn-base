
// 验证手机号
  function mobilePhoneVel() {
	var regex = /^1[3|4|5|7|8]\d{9}$/;
	var mobilePhoneVal = $(".registerphone").val();
	if (mobilePhoneVal == "") {
		$(".p_view").css("display","none");
		Info.view('手机号不能为空！');
		return false;
	}
	if (regex.test(mobilePhoneVal) == false) {
		$(".p_view").css("display","none");
		Info.view('手机号输入不正确！');
		return false;
	}
	else {
		return true;
	}
}
// 验证验证码（只验证非空）
  function messageVel() {
	var messageVal = $(".CodeNum").val();
	if (messageVal == "") {
		$(".p_view").css("display","none");
		Info.view('验证码不能为空！');
		return false;
	}
	else {
		return true;
	}
}
  
// 验证密码（只验证非空）
  function passwordVel() {
	var passwordVal = $(".psWordNum").val();
	if (passwordVal == "") {
		$(".p_view").css("display","none");
		Info.view('密码不能为空！');
		return false;
	}
	else {
		return true;
	}
}


function register(){
	var flag =mobilePhoneVel()&&messageVel()&&passwordVel();
	if(flag==true){
		alert("ok")
	}
}
