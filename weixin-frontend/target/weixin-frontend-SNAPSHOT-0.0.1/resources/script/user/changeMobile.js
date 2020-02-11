			
/*点击确定*/
function sureClick(){
	
	var code2=$("#code2").val();
	var code=$("#code").val();
	var telePhone=$("#telePhone").val();
	if(code==""){
		Info.view('请输入旧手机的验证码');
		return;
	}
	
	if(telePhone==""){
		Info.view('请输入新手机号码');
		return;
	}
	if(code2==""){
		Info.view('请输入新手机的验证码');
		return;
	}
	$(".surenBtn").attr("disabled","disabled");
		alert("ok");
}



function validateTeleEist(){
   var telePhone=$("#newPassword").val();
//	   $.ajax({
//	        type:'post',
//	  //      url:"/uc/userInfo/validateTeleEist?telephone="+telePhone,
//	        async: false,
//	        success: function(result) {
//	        	if(result=='1'){
//	        		Info.view('手机号已被注册过！');
//	        		return false;
//	        	}
//	        }
//	   
//	   });
	   return true;
}


/*发送验证码*/
var clock1= '';
var nums1= 60;
function codeClickold1(){
	$("#codeBtn1").attr("disabled","disabled");
	$("#codeBtn1").css("background", "#A9B7B7");
	var  telephone =$("#oldTelePhone").val();
//	$.ajax({
//        type:'post',
//  //      url:"/validateCode/sendMsgAle?telephone="+telephone,
//        async: false,
//        success: function(result) {
//        	if(result=='1'){
//        		$("#codeBtn1").attr("disabled","disabled");
//        	    $("#codeBtn1").val(nums1+'秒重新发送');
//        	    clock1 = setInterval(doLoop1, 1000); //一秒执行一次*
//        	}
//        	if(result=='0'){
//        		Info.view('手机号已被注册过！');
//        		$("#codeBtn1").removeAttr("disabled");
//        		$("#codeBtn1").css("background", "#FFD900");
//        	}
//        	if(result=='2'){
//        		 $("#codeBtn1").attr("disabled","disabled");
// 			     $("#codeBtn1").text(nums1+'秒');
// 			     clock1 = setInterval(doLoop1, 1000); 
//        	}
//        }
//	});
  
}
function doLoop1(){
	 nums1--;
	 if(nums1 > 0){
	  $("#codeBtn1").val(nums1+'秒重新发送');
	 }else{
	  clearInterval(clock1); //清除js定时器
	  $("#codeBtn1").val("重新发送");
	   $("#codeBtn1").removeAttr("disabled");
	   $("#codeBtn1").css("background", "#FFD900");
	  nums1 = 60; //重置时间
	 }
 }




/*发送验证码2*/
var clock2= '';
var nums2= 60;
function codeClick2(){
	var telePhone=$("#telePhone").val();
	if(telePhone==""){
		Info.view('请输入新手机的号码');
		return;
	}
	if(validateTeleEist){
		Info.view('手机号已被注册过！');
		return;
	}
	$("#codeBtn2").attr("disabled","disabled");
	$("#codeBtn2").css("background", "#A9B7B7");
	var  telephone =$("#telePhone").val();
//	$.ajax({
//        type:'post',
//        url:"/sendMsgForgetPassword?telephone="+telephone,
//        async: false,
//        success: function(result) {
//        	if(result=='1'){
//        		$("#codeBtn2").attr("disabled","disabled");
//        	    $("#codeBtn2").val(nums2+'秒重新发送');
//        	    clock2 = setInterval(doLoop2, 1000); //一秒执行一次*
//        	}
//        	if(result=='0'){
//        		Info.view('手机号已被注册过！');
//        		$("#codeBtn2").removeAttr("disabled");
//        		$("#codeBtn2").css("background", "#FFD900");
//        	}
//        	if(result=='2'){
//        		 $("#codeBtn2").attr("disabled","disabled");
// 			     $("#codeBtn2").text(nums2+'秒');
// 			     clock2 = setInterval(doLoop2, 1000); 
//        	}
//        }
//	});
  
}
function doLoop2(){
	 nums2--;
	 if(nums2 > 0){
	  $("#codeBtn2").val(nums2+'秒重新发送');
	 }else{
	  clearInterval(clock2); //清除js定时器
	  $("#codeBtn2").val("重新发送");
	   $("#codeBtn2").removeAttr("disabled");
	   $("#codeBtn2").css("background", "#FFD900");
	  nums1 = 60; //重置时间
	 }
 }

$(document).ready(function(){
	var localHeight = $("section").height();//获取可视宽度
	$("#code2").focus(function() {
		$(".floor").hide();
	var keyboardHeight = localHeight - $("section").height();//获取键盘的高度
	var keyboardY = localHeight - keyboardHeight;
	var addBottom = (parseInt($(this).position().top) + parseInt($(this).height()));//文本域的底部
	var offset = addBottom - keyboardY;//计算上滑的距离
	$("section").scrollTop(offset);
	});
	$("#code2").blur(function(){
        $(".floor").css("display","block");
  });
})