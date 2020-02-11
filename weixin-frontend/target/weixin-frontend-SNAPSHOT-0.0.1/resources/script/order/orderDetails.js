$(function(){
	$(".msgContent").focus(function(){
		$(".msgContent").height("40px");
	});
	$(".msgContent").blur(function(){
		$(".msgContent").height("20px");
	});
})

//申请售后
function inserOrdRefund(orderId,ordItemId){
	window.location.href="/uc/ordRefundRequest/getInsetInfo?orderId="+orderId+"&ordItemId="+ordItemId;
}

//查看售后
function findOrdRefund(ordRefundId){
	window.location.href="/uc/ordRefundRequest/findBydetail?ordRefundId="+ordRefundId;
}