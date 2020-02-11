function userName() {
	var userNameVal = $(".userName").val();
	if(userNameVal == "") {
		Info.view('请输入登录账号!');
		return false;
	} else {
		return true;
	}
}

function psWordVal() {
	var psWordVal = $(".psWordNum").val();
	if(psWordVal == "") {
		Info.view('请输入密码!');
		return false;
	} else {
		return true;
	}
}
function signBtn() {
	var flag = userName() && psWordVal();
	if(flag == true) {
		$(".signBTN").attr("disabled","disabled");
  		$.ajax({
  	        type:'post',
  	        url:"/user/login",
  	        data:$('#submitForm').serialize(),
  	        async: false,
  	        success: function(result) {
        	 	var json = eval("("+ result +")");
        	 	if(json.error=="error"){
        	 		Info.view('用户名或者密码错误！');
        	 		$(".signBTN").removeAttr("disabled");
        	 		return;
				}
        		 var tokenId=json.tokenId;
        		 var telephone=json.telephone;
        		 var userName=json.userName;
        		 var userType=json.userType;
        		 var userId=json.userId;
        		 var signInTime=json.signInTime
        		 var signInLoadingIframeUrl="/user/sign-in-loading?tokenId="+tokenId+"&telNum="+telephone+"&nikeName="+userName+"&userType="+userType+"&userId="+userId+"&signInTime="+ signInTime;
        		 $("#signIn_loading_iframe")[0].src = signInLoadingIframeUrl;
  	        }
  		});
	}
}