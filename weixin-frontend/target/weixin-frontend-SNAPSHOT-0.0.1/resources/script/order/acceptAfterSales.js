$(".min").click(function() {
    var t = $(this).parent().find('.text_box');
    t.val(parseInt(t.val()) - 1);
    if (t.val() <= 1) {
      t.val(1);
    }
    var goondsNum=$("#goodsCount").val();
    $(".refundAmount").val(parseInt(t.val())*$(".productPrice").val());
    $(this).parent().parent().next().find(".goodsCounts").text(t.val());
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
    $(".refundAmount").val(parseInt(t.val())*$(".productPrice").val());
});
$(function() {
	$(".filepath").on("change",function() {
	   var srcs = getObjectURL(this.files[0]); //获取路径
	   $(this).nextAll(".img1").hide(); //this指的是input
	   $(this).nextAll(".img2").show(); //fireBUg查看第二次换图片不起做用
	   $(this).nextAll(".img2").attr("src",srcs); //this指的是input
	})
})
function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) {
   		url = window.createObjectURL(file)
    } else if (window.URL != undefined) {
   		url = window.URL.createObjectURL(file)
    } else if (window.webkitURL != undefined) {
   		url = window.webkitURL.createObjectURL(file)
    }
    return url
};

//退货维修
function TuihuoType(type){
	//样式修改自己加
	if(type=='0'){
		$(".tuihuoCountDiv").hide();
		$(".tuihuoMoneyDiv").hide();
		$("#returnInstructions").hide();
		$('.isTuihuo').val(0);
	}
	if(type=='1'){
		if($('.isTuihuo').val()=='1'){
			$(".tuihuoCountDiv").show();
			$(".tuihuoMoneyDiv").show();
			$("#returnInstructions").show();
		}
		$('.isTuihuo').val(1);
	}
}

//提交
function submitInfo(){
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