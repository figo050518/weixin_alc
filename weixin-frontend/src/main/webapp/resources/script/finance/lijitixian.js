
(function($, doc) {
	$.init();
	$.ready(function() {
		//普通示例
		var userPicker = new $.PopPicker();
		userPicker.setData([{
			value: '中国工商银行',
			text: '中国工商银行'
		}, {
			value: '中国农业银行',
			text: '中国农业银行'
		}, {
			value: '中国银行',
			text: '中国银行'
		}, {
			value: '中国建设银行',
			text: '中国建设银行'
		}, {
			value: '国家开发银行',
			text: '国家开发银行'
		}, {
			value: '交通银行',
			text: '交通银行'
		}, {
			value: '中信银行',
			text: '中信银行'
		}, {
			value: '中国光大银行',
			text: '中国光大银行'
		}, {
			value: '华夏银行',
			text: '华夏银行'
		}, {
			value: '中国民生银行', 
			text: '中国民生银行'
		}, {
			value: '广发银行', 
			text: '广发银行'
		}, {
			value: '平安银行', 
			text: '平安银行'
		}, {
			value: '招商银行', 
			text: '招商银行'
		}, {
			value: '兴业银行', 
			text: '兴业银行'
		}, {
			value: '上海浦东发展银行', 
			text: '上海浦东发展银行'
		}, {
			value: '中国邮政储蓄银行', 
			text: '中国邮政储蓄银行'
		}]);
		var showUserPickerButton = doc.getElementById('cardName');
		var userResult = doc.getElementById('userResult');
		showUserPickerButton.addEventListener('tap', function(event) {
			userPicker.show(function(items) {
				document.getElementById("cardName").value = (items[0].text);
				document.getElementById("cardVal").value = (items[0].value);
			});
		}, false);
		
	});
})(mui, document);


//验证提现
/*^(?:0|[1-9]\d*)(\.\d{2})?$*/
function validateTianMoney(){
	var regex = /^(?:0|[1-9]\d*)(\.\d{2})?$/;
	var balance=$("#tixianInput").val();
	var tianMoney=$(".yue").text();
	if(parseInt(balance)>parseInt(tianMoney)){
		$(".p_view").css("display","none");
		Info.view('提现金额不能大于余额!');
		return false;
	}
	if(parseInt(balance)>parseInt("50000.00")){
		$(".p_view").css("display","none");
		Info.view('提现金额不能大于2万!');
		return false;
	}
	if(balance==""){
		$(".p_view").css("display","none");
		Info.view('请输入提现金额!');
		return false;
	}
	if(balance=="0"||balance=="0.0"||balance=="0.00"){
		$(".p_view").css("display","none");
		Info.view('提现金额不能为0!');
		return false;
	}
	if (regex.test(balance) == false) {
		$(".p_view").css("display","none");
		Info.view('请正确输入提现金额！');
		return false;
	}
	else{
		return true;
	}
}

function btnNow(){
	var flag = validateTianMoney()&&tixianCard();
	if(flag==true){
		$.ajax({
	        type:'post',
	        url:"/uc/finance/withdrwaApply",
	        data:$('#withdrawForm').serialize(),
	        async: false,
	        success: function(result) {
	        	if(result=='errorMsgCode'){
	        		Info.view('验证码错误！');
	        	}else{
	        		window.location.href="/uc/finance/withDrawApply/init";
	        	}
	        }
		});
	}
}


function bankCardShow(){
	$(".bankCard").show();
	$(".overlay").show();
}
function bankCardHide(){
	$(".bankCard").hide();
	$(".overlay").hide();
}
			
// 验证银行卡卡号（只验证非空）
  function cardNumVal() {
	var cardNumVal = $(".cardNum").val();
	if (cardNumVal == "") {
		$(".p_view").css("display","none");
		Info.view('银行卡卡号不能为空！');
		return false;
	}else {
		return true;
	}
}
// 验证姓名（只验证非空）
  function nameVal() {
	var nameVal = $(".name").val();
	var regX= /^[\u4e00-\u9fa5]{2,}$/;
	if (nameVal == "") {
		$(".p_view").css("display","none");
		Info.view('您的姓名不能为空！');
		return false;
	}else if(regX.test(nameVal) == false){
		$(".p_view").css("display","none");
		Info.view('您的姓名不正确！');
		return false;
	}
	else {
		return true;
	}
}
  
// 验证验证码（只验证非空）
  function messageVel() {
	var messageVal = $(".codeNum").val();
	if (messageVal == "") {
		$(".p_view").css("display","none");
		Info.view('验证码不能为空！');
		return false;
	}
	else {
		return true;
	}
}
function tixianCard(){
	var selectCard = $(".selectCard");
	var selectCardOption = $(".selectCard option");
	if(selectCardOption.length==1){
		$(".p_view").css("display","none");
			Info.view('请添加账户！');
			return false;
	}else{
		var optionST = selectCard.find("option:selected").text();
		if(optionST=="请选择提现账户"){
			$(".p_view").css("display","none");
			Info.view('请选择提现账户！');
			return false;
			
		}else{
			return true;
		}
	}
}


function wanchengBtn(){
	var flag = cardNumVal()&&nameVal();
	if(flag ==true){
		$.ajax({
	        type:'get',
	        url:"/uc/finance/addCard",
	        data:$('#submitForm').serialize(),
	        async: false,
	        success: function(result) {
	        	if(result=='error'){
	        		Info.view('绑定银行卡失败失败！');
	        	}else{
	        		console.log(result);
	        		console.log(result.id);
	        		$(".overlay").css("display","none");
	        		$(".bankCard").css("display","none");
	        		$("#selectCardNone").remove();
	        		/*var addHtml="<option value="+result.id+"><div>"+result.bankName+"</div><div class='Mleft'>尾号（"+result.cardNum+"</span>）</div></option>";*/
	        		var addHtml="<option value='ceshi'><div>测试银行</div><div class='Mleft'>尾号（111）</div></option>";
	        		$(".selectCard").prepend(addHtml);
	        		var aa="ceshi";
	        		/*$(".selectCard option[value='"+result.id+"']").attr("selected","selected");*/
	        		$(".selectCard option[value='"+aa+"']").attr("selected","selected");
	        	}
	        }
		});
	}
}
$(document).ready(function(){
	var localHeight = $("section").height();//获取可视宽度
	$("input").focus(function() {
	var keyboardHeight = localHeight - $("section").height();//获取键盘的高度
	var keyboardY = localHeight - keyboardHeight;
	var addBottom = (parseInt($(this).position().top) + parseInt($(this).height()));//文本域的底部
	var offset = addBottom - keyboardY;//计算上滑的距离
	$("section").scrollTop(offset);
	});
	
})



//点击获取验证码
var clock= '';
var nums= 60;
function codeClick(){
	var  telephone =$(".telephone").val();
	var userId=$("#userId").val();
		$(".getCode").attr("disabled","disabled");
		$(".getCode").css({"background":"#A9B7B7","color":"#333","border":"1px solid #A9B7B7"});
		$.ajax({
	        type:'post',
	        url:"/sendMsgForBindCard?telephone="+telephone+"&userId="+userId,
	        async: false,
	        success: function(result) {
	        	if(result=='success'){
	        		$(".getCode").attr("disabled","disabled");
	        	    $(".getCode").val(nums+'秒重新发送');
	        	    clock = setInterval(doLoop, 1000); //一秒执行一次*
	        	}
	        	if(result=='errorTelephone'){
	        		Info.view('手机号不是当前商户！');
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

