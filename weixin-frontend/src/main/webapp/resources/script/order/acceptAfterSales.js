function toDecimal2(x) {//保留2为有效数字 
    var f = parseFloat(x);    
    if (isNaN(f)) {    
        return false;    
    }    
    var f = Math.round(x*100)/100;    
    var s = f.toString();    
    var rs = s.indexOf('.');    
    if (rs < 0) {    
        rs = s.length;    
        s += '.';    
    }    
    while (s.length <= rs + 2) {
        s += '0';    
    }    
    return s;    
}

$(".min").click(function() {
    var t = $(this).parent().find('.text_box');
    t.val(parseInt(t.val()) - 1);
    if (t.val() <= 1) {
      t.val(1);
    }
    var goondsNum=$("#goodsCount").val();
    var refundAmount=parseInt(t.val())*$(".productPrice").val();
    $(".refundAmount").val(toDecimal2(refundAmount));
});
  // 数量加
$(".add").click(function() {
	var goondsNum=$("#goodsCount").val();
    var t = $(this).parent().find('.text_box');
    
    t.val(parseInt(t.val()) + 1);
    if (t.val() <= 1) {
      t.val(1);
    }
    if(parseInt(t.val()) >parseInt(goondsNum)){
    	alert("退货数量应小于商品数量！");
    	t.val(goondsNum);
    }
    $(this).parent().parent().next().find(".text_box").text(t.val());
    var refundAmount=parseInt(t.val())*$(".productPrice").val();
    $(".refundAmount").val(toDecimal2(refundAmount));
});


//退货维修
function TuihuoType(type){
	//样式修改自己加
	if(type=='0'){  //退款
		$(".tuihuoCountDiv").hide();
		$(".tuihuoMoneyDiv").hide();
		/*$("#returnInstructions").hide();*/
		$('.isTuihuo').val(0);
		$("#tuihuoBtn").removeClass("activity");
		$("#tuikBtn").addClass("activity");
	}
	if(type=='1'){ //退货
		$('.isTuihuo').val(1);
		$(".tuihuoCountDiv").show();
		$(".tuihuoMoneyDiv").show();
    	/*$("#returnInstructions").show();*/
		
		$("#tuikBtn").removeClass("activity");
		$("#tuihuoBtn").addClass("activity");
	}
}


function changePhoto(obj){
	var thisId=$(obj).attr("id");
	var f = document.getElementById(thisId).files;
	var img1 = $(obj).parents(".imgbox").find(".img1"); //this指的是input
	var img2 = $(obj).parents(".imgbox").find(".img2");
	var imageUrl=$(obj).parents(".imgbox").find(".imageUrl");
	console.log(f[0].size);
	if(f[0].size<0.5*1024*1000){
		    var $f = $("<form method='post'/>");
			var real = $(obj);
			var cloned = real.clone(true);
			cloned.insertAfter(real);
	        $f.append(real);
	        $f.attr({
	            method: "post",
	            action: "/uc/interfaces/image/uploadImage",
	            enctype: "multipart/form-data"
	        }).insertAfter($("#hide")).ajaxForm({
	            complete: function(xhr) {
            	    if(xhr.responseText=='invalidImage'){
 	                     alert("图片文件无效！");  
	 	        	}
	 	        	if(xhr.responseText=='invalidPreFix'){
	 	                 alert("不是正确的图片类型！");  
	 	        	}
	 	        	if(xhr.responseText=='error'){
	 	                 alert("上传错误,网络异常！");  
	 	        	}else{
	 	        		img1.css("display","none");
		            	img2.css("display","block");
		            	img2.attr("src",xhr.responseText);//显示Temp图片
		            	imageUrl.val(xhr.responseText);
	 	        	}
	            }
	    }).submit();
	}else{
		alert('请选取小于0.5M大小的图片！');
		return false;
	}
}


//验证退货说明 必填
function tuihuoshuomVel() {
	var regexKg = "\\s+";
	var tuihuoshuomVal = $(".returnInstructions").val();
	var tuihuoshuomValKg = tuihuoshuomVal.replace(/\s/g,'');
	if (tuihuoshuomValKg == "") {
		Info.view("请填写退货说明！");
		return false;
	}
	else {
		return true;
	}
}
//提交
function submitInfo(){
	var flag=tuihuoshuomVel();
	if(flag==true){
		if(confirm("确定要提交吗？")){
			$.ajax({
		        type:'post',
		        url:"/uc/ordRefundRequest/insertInfo",
		        data:$('#insertInfo').serialize(),
		        async: false,
		        error: function(request) {
		          
		       	},
		        success: function(result) {
		        	if(result=='1'){
		                 alert("提交成功");  
		                 window.location.href="/uc/ordRefundRequest/findOrdRufundBypage";
		        	}
		        }
			});
		}
	}
}