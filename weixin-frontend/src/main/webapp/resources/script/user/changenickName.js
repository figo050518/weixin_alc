window.alert = function(name){
			 var iframe = document.createElement("IFRAME");
			iframe.style.display="none";
			iframe.setAttribute("src", 'data:text/plain,');
			document.documentElement.appendChild(iframe);
			window.frames[0].window.alert(name);
			iframe.parentNode.removeChild(iframe);
		}

/*验证昵称 (先只验证非空)*/
function nickNameVel() {
	var nickNameVal = $("#nickName").val();
	if (nickNameVal == "") {
		Info.view('请输入新的昵称');
		return false;
	}
	else {
			return true;
		}
}
$("#nickName").blur(function() {
	nickNameVel();
});
			
			
/*点击确定*/
function sureClick(){
	var flag=nickNameVel();
	if(flag==true){
		$(".surenBtn").attr("disabled","disabled");
		$.ajax({
            type:'post',
            url:"/uc/buyer/manage/changeNickName",
            data:$('#submitForm').serialize(),
            async: false,
            error: function(request) {
            	Info.view('系统繁忙，请稍后重试');
            	$(".surenBtn").removeAttr("disabled","disabled");
           	},
            success: function(result) {
                 location.href="/uc/buyer/manage/index";   
            }
    	});
	} 
}