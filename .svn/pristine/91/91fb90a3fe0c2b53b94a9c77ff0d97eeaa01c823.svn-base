function changePassword(){
		//$(".surenBtn").attr("disabled","disabled");
//		$(".btn").css({"background":"#A9B7B7","color":"#333","border":"1px solid #A9B7B7"});
		var newPassword = $("#newPassword").val();
		var onceMore = $("#onceMore").val();
		var code=$("#code").val();
		
		if(code==""){
			Info.view('请输入验证码');
			return;
		}
		if(newPassword==""){
			Info.view('请输入新密码');
			return;
		}
		if(onceMore==""){
			Info.view('请输入再次确认密码');
			return;
		}
		if(newPassword !=onceMore){
			Info.view('两次输入密码不一致，请重新输入');
			return;
		}
		$.ajax({
           type:'post',
           url:"/uc/buyer/manage/changerPassword",
           data:$('#submitForm').serialize(),
           async: false,
           error: function(request) {
	           	$(".p_view").css("display","none");
	           	Info.view('修改失败！');
//	           	$(".btn").removeAttr("disabled");
//      		 	$(".btn").css("background", "#FFD900");
           },
           success: function(result) {
           	if(result=='success'){
           		Info.view('修改成功，请重新登录！');
   				window.location.href="/uc/buyer/manage/index";
           	}
		}
	});
}


//点击获取验证码
var clock= '';
var nums= 60;
function codeClick(){
	var  telephone =$("#telephone").val();
		$(".code").attr("disabled","disabled");
		$(".code").css({"background":"#A9B7B7","color":"#333","border":"1px solid #A9B7B7"});
		$.ajax({
	        type:'post',
	        url:"/sendMsgForChangePsd?telephone="+telephone,
	        async: false,
	        success: function(result) {
	        	if(result=='success'){
	        		$(".code").attr("disabled","disabled");
	        	    $(".code").val(nums+'秒重新发送');
	        	    clock = setInterval(doLoop, 1000); //一秒执行一次*
	        	}
	        	if(result=='senderror'){
	        		Info.view('验证码发送失败！');
	        		$(".code").removeAttr("disabled");
	        		$(".code").css({"background":"#FFFFFF","color":"#D13600","border":"1px solid #D13600"});
	        	}
	        }
		});
}
function doLoop(){
	 nums--;
	 if(nums > 0){
	  $(".code").val(nums+'秒重新发送');
	 }else{
	  clearInterval(clock); //清除js定时器
	  $(".code").val("重新发送");
	   $(".code").removeAttr("disabled");
	   $(".code").css({"background":"#FFFFFF","color":"#D13600","border":"1px solid #D13600"});
	  nums = 60; //重置时间
	 }
 }
