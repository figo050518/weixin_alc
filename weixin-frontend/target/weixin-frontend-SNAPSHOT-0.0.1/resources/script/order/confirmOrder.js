$(function(){
	$(".msgContent").focus(function(){
		$(".msgContent").height("40px");
	});
	$(".msgContent").blur(function(){
		$(".msgContent").height("20px");
	});
})


//点击提交顶点
function submitOrder(){
	var adddressVal=$(".adddressVal").val();
	if(adddressVal==""){
		alert("收货地址不能为空！")
	}
}

//点击新增收货地址
function addAddress(num){
	$("#chooseAddressPopup").css("display","none");
	$(".cover").css("display","block");
	$("#addressPopup").css("display","block");
	$('html,body').addClass('overHiden');
}

//验证收货人姓名 2-5个中文
function nameVel() {
	var regex = /^[\u4e00-\u9fa5]{2,5}$/;
	var nameVal = $("#name").val();
	if (nameVal == "") {
		$(".p_view").css("display","none");
		Info.view("请输入您的姓名！");
		return false;
	}
	if (regex.test(nameVal) == false) {
		$(".p_view").css("display","none");
		Info.view("您输入的姓名不正确！");
		return false;
	}
	else {
		return true;
	}
}

//验证手机号‘
function telephoneVel() {
	var regex = /^1[3|4|5|7|8]\d{9}$/;
	var telephoneVal = $("#telephone").val();
	if (telephoneVal == "") {
		Info.view("请输入您的手机号！");
		return false;
	}
	if (regex.test(telephoneVal) == false) {
		Info.view("您输入的手机号不正确！");
		return false;
	}
	else {
		return true;
	}
}
//验证详细地址
function addressVel(){
	var addressVal = $("#address").val();
	if (addressVal == "") {
		$(".p_view").css("display","none");
		Info.view("请输入详细地址！");
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
		$(".p_view").css("display","none");
		Info.view("所在地区不能为空！");
		return false;
	}
	else {
		return true;
	}
}

//点击新增收货地址的保存
function submitForm(){
	var flag=nameVel()&&telephoneVel()&&addressVel();
	if(flag==true){
		$.ajax({
            type:'post',
            url:"",
            data:$('#addAddressForm').serialize(),
            async: false,
            error: function(request) {
              
           	},
            success: function(result) {
            	if(result=='1'){
                       
            	}
            	if(result=='0'){
            		
            	}
            }
    	});
	}
}
//关闭弹窗
function closePopup(){
	$(".cover").css("display","none");
	$(".bottomPopup").css("display","none");
	$('html,body').removeClass('overHiden');
}

//选择收货地址
function chooseAddress(){
	$(".cover").css("display","block");
	$("#chooseAddressPopup").css("display","block");
	$('html,body').addClass('overHiden');
}
//选取收货地址错
function chooseThis(obj){
	$(".chooseAddressTb").find(".opaction").prop("checked", false);
	$(obj).parents("tbody").find(".opaction").prop("checked", true);
	$("#chooseAddressPopup").css("display","none");
	$(".cover").css("display","none");
	$('html,body').removeClass('overHiden');
	
	var telephone=$(obj).parents("tbody").find(".addressPhone").text();
	var addressName=$(obj).parents("tbody").find(".addressName").text();
	var addressText=$(obj).parents("tbody").find(".addressText").text();
	$(".checkedName").text(addressName);
	$(".checkedPhone").text(telephone);
	$(".checkedAddress").text(addressText);
}

//编辑收货地址
function editeAddress(obj){
	$("#chooseAddressPopup").css("display","none");
	$(".cover").css("display","block");
	$("#addressPopup").css("display","block");
	$('html,body').addClass('overHiden');
}
