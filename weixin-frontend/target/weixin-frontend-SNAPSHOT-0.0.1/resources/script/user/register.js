
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
	var mobilePhoneVal = $(".registerphone").val();
	var passwordVal = $(".psWordNum").val();
	var messageVal = $(".CodeNum").val();
	if(flag==true){
		$(".registerBTN").attr("disabled","disabled");
		$(".btn").css({"background":"#A9B7B7","color":"#333","border":"1px solid #A9B7B7"});
		$.ajax({
           type:'post',
           url:"/user/register?userType=1&telephone="+mobilePhoneVal+"&password="+passwordVal+"&code="+messageVal,
           dataType:'JSON',
           async: false,
           error: function(request) {
	           	$(".p_view").css("display","none");
	           	Info.view('注册失败！');
	           	$(".btn").removeAttr("disabled");
      		 	$(".btn").css("background", "#FFD900");
           },
           success: function(result) {
           	if(result=='existTelephone'){
           		$(".p_view").css("display","none");
           		Info.view('该手机已被注册！');
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
           		$.ajax({
          	        type:'post',
          	        url:"/user/login?telephone="+mobilePhoneVal+"&password="+passwordVal,
          	        async: false,
          	        success: function(result) {
          	        	 	var json = eval("("+ result +")");
          	        		 var tokenId=json.tokenId;
          	        		 var telephone=json.telephone;
          	        		 var userName=json.userName;
          	        		 var userType=json.userType;
          	        		 var userId=json.userId;
          	        		 var signInTime=json.signInTime;
          	        		 var signInLoadingIframeUrl="/user/sign-in-loading?tokenId="+tokenId+"&telNum="+telephone+"&nikeName="+userName+"&userType="+userType+"&userId="+userId+"&signInTime="+ signInTime;
          	        		 $("#signIn_loading_iframe")[0].src = signInLoadingIframeUrl;
          	        		 //location.href="/userLogin//sign-in-loading?tokenId="+tokenId+"&telephone="+telephone+"&userName="+userName+"&userType="+userType;
          	        	
          	        }
          		});
           	}
		}
	});
}
}

//点击获取验证码
var clock= '';
var nums= 60;
function codeClick(){
	var  telephone =$(".registerphone").val();
	if(mobilePhoneVel() == false){
		Info.view('请正确填写手机号！');
	}else{
		$(".getCode").attr("disabled","disabled");
		$(".getCode").css({"background":"#A9B7B7","color":"#333","border":"1px solid #A9B7B7"});
		$.ajax({
	        type:'post',
	        url:"/sendMsgForRegister?telephone="+telephone,
	        async: false,
	        success: function(result) {
	        	if(result=='success'){
	        		$(".getCode").attr("disabled","disabled");
	        	    $(".getCode").val(nums+'秒重新发送');
	        	    clock = setInterval(doLoop, 1000); //一秒执行一次*
	        	}
	        	if(result=='existTelephone'){
	        		Info.view('手机号已被注册过！');
	        		$(".getCode").removeAttr("disabled");
	        		$(".getCode").css({"background":"#FFFFFF","color":"#D13600","border":"1px solid #D13600"});
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

