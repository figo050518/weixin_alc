//验证姓名
function billingNameVel() {
	var regex = /^[\u4e00-\u9fa5]{2,13}$/;
	var billingNameVal = $("#receiverName").val();
	var errorMsg = $("#receiverError");
	if (billingNameVal == "") {
		alert("请输入收货人姓名！");
		return false;
	}
	if (regex.test(billingNameVal) == false) {
		alert("您输入的收货人姓名不正确！");
		return false;
	}
	else {
		return true;
	}
}

// 验证手机号
function mobilePhoneVel() {
	var regex = /^1[3|4|5|7|8]\d{9}$/;
	var mobilePhoneVal = $("#contactNum").val();
	if (mobilePhoneVal == "") {
		$(".p_view").css("display","none");
		alert('手机号不能为空！');
		return false;
	}
	if (regex.test(mobilePhoneVal) == false) {
		alert('手机号输入不正确！');
		return false;
	}
	else {
		return true;
	}
}

// 验证所在地区
function provinceVel() {
	var provinceVal = $("#province").val();
	if (provinceVal == "") {
		$(".p_view").css("display","none");
		alert('所在地区不能为空！');
		return false;
	}
	else {
		return true;
	}
}



// 验证详细地址
function detailAddressVel() {
	var detailAddressVal = $("#detailAddr").val();
	if (detailAddressVal == "") {
		$(".p_view").css("display","none");
		alert('详细地址不能为空！');
		return false;
	}
	else {
		return true;
	}
}

function surenClick3(){
	var flag=billingNameVel()&&mobilePhoneVel()&&provinceVel()&&detailAddressVel();
	if(flag==true){
		$(".sureBtn").attr("disabled","disabled");
		$(".sureBtn").css("background", "#A9B7B7");
		$.ajax({
	        type:'post',
	        url:"/uc/user/address/edit",
	        data:$('#submitForm').serialize(),
	        async: false,
	        error: function(request) {
	        	$(".sureBtn").removeAttr("disabled");
				$(".sureBtn").css("background", "#F7E05F");
				$(".p_view").css("display","none");
	        	Info.view('系统繁忙请稍后重试！');
	       	},
	        success: function(result) {
	        	if(result=='success'){
	        		Info.view('编辑成功！');
	        		window.location.href="/uc/user/address/list";
	        	}
	     
	        		$(".sureBtn").removeAttr("disabled");
					$(".sureBtn").css("background", "#F7E05F");
					$(".p_view").css("display","none");
	        		Info.view('添加失败！');
	        	
	        }
		});
	}
}