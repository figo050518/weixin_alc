function userName() {
	var userNameVal = $("#operId").val();
	if(userNameVal == "") {
		  layer.msg('请输入登录账号!',{icon:2,time:1000});
		return false;
	} else {
		return true;
	}
}

function psWordVal() {
	var psWordVal = $("#password").val();
	if(psWordVal == "") {
		layer.msg('请输入密码!',{icon:2,time:1000});
		return false;
	} else {
		return true;
	}
}
function signBtn() {
	var flag = userName() && psWordVal();
	if(flag == true) {
//		$(".btn-success").attr("disabled","disabled");
  		$.ajax({
  	        type:'post',
  	        url:"/oper/login.do",
  	        data:$('#loginForm').serialize(),
  	        async: false,
  	        success: function(result) {
        	 	var json = eval("("+ result +")");
        	 	if(json.resultCode != "SUCCESS"){
        	 		layer.msg(json.resultMsg,{icon:2,time:1000});
        	 		$(".btn-success").removeAttr("disabled");
        	 		return;
				}
        		 window.location.href="/oper/index.do";
  	        }
  		});
	}
}