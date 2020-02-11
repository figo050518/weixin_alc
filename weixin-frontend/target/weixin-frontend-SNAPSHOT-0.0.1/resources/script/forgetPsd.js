// 验证手机号
  function mobilePhoneVel() {
	var regex = /^1[3|4|5|7|8]\d{9}$/;
	var mobilePhoneVal = $(".setNewPsdphoneNum").val();
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

function codeVal() {
	var psWordVal = $(".CodeNum").val();
	if(psWordVal == "") {
		Info.view('请输入验证码');
		return false;
	} else {
		return true;
	}
}
function psWordVal() {
	var psWordVal = $(".setNewPsdNum").val();
	if(psWordVal == "") {
		Info.view('请输入新密码');
		return false;
	} else {
		return true;
	}
}
function setNewPsdBTN() {
	var flag = mobilePhoneVel() &&codeVal()&& psWordVal();
	var mobilePhoneVal = $(".setNewPsdphoneNum").val();
	var passwordVal = $(".setNewPsdNum").val();
	var messageVal = $(".CodeNum").val();
	if(flag == true) {
		$.ajax({
	           type:'post',
	           url:"/user/forgetPassword?telephone="+mobilePhoneVal+"&password="+passwordVal+"&validateCode="+messageVal,
	           dataType:'JSON',
	           async: false,
	           error: function(request) {
		           	$(".p_view").css("display","none");
		           	Info.view('注册失败！');
		           	$(".btn").removeAttr("disabled");
	      		 	$(".btn").css("background", "#FFD900");
	           },
	           success: function(result) {
	           	if(result=='errorTelephone'){
	           		$(".p_view").css("display","none");
	           		Info.view('该手机不存在！');
	           		$(".btn").removeAttr("disabled");
	           		$(".btn").css({"background":"#FFFFFF","color":"#D13600","border":"1px solid #D13600"});
	   				return;
	           	}
	           	if(result=='errorMsgCode'){
	           		$(".p_view").css("display","none");
	           		Info.view('验证码输入错误！');
	           		$(".btn").removeAttr("disabled");
	           		$(".btn").css({"background":"#FFFFFF","color":"#D13600","border":"1px solid #D13600"});
	   				return;
	           	}
	           	if(result=='success'){
	           		window.location.href="/user/login";
	           	}
			}
		});
}
}


//点击获取验证码
var clock= '';
var nums= 60;
function codeClick(){
	var  telephone =$(".setNewPsdphoneNum").val();
	if(mobilePhoneVel() == false){
		Info.view('请正确填写手机号！');
	}else{
		$(".getCode").attr("disabled","disabled");
		$(".getCode").css({"background":"#A9B7B7","color":"#333","border":"1px solid #A9B7B7"});
		$.ajax({
	        type:'post',
	        url:"/sendMsgForgetPassword?telephone="+telephone,
	        async: false,
	        success: function(result) {
	        	if(result=='success'){
	        		$(".getCode").attr("disabled","disabled");
	        	    $(".getCode").val(nums+'秒重新发送');
	        	    clock = setInterval(doLoop, 1000); //一秒执行一次*
	        	}
	        	if(result=='senderror'){
	        		Info.view('验证码发送失败！');
	        		$(".getCode").removeAttr("disabled");
	        		$(".getCode").css({"background":"#FFFFFF","color":"#D13600","border":"1px solid #D13600"});
	        	}
	        }
		});
	}
}
function doLoop(){
	 nums--;
	 if(nums > 0){
	  $(".getCode").val(nums+'秒重新发送');
	 }else{
	  clearInterval(clock); //清除js定时器
	  $(".getCode").val("重新发送");
	   $(".getCode").removeAttr("disabled");
	   $(".getCode").css({"background":"#FFFFFF","color":"#D13600","border":"1px solid #D13600"});
	  nums = 60; //重置时间
	 }
 }