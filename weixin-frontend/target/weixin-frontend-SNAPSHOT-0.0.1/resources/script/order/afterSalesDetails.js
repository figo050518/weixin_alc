//卖家确认售后完成
function updateFinsh(ordRefundId){
	if(confirm("确定该笔订单售后已完成？")){
		$.ajax({
	        type:'post',
	        url:"/uc/ordRefundRequest/updateFinsh",
	        data:{ordRefundId:ordRefundId},
	        async: false,
	        error: function(request) {
	          
	       	},
	        success: function(result) {
	        	if(result=='1'){
	                 alert("该笔订单售后已完成！");  
	                 window.location.reload();
	        	}
	        }
		});
	}
}

function updateTuihuiPro(ordRefundId){
	alert("超哥请在这个地方 加一个弹框 里面两个参数   退货物流公司名称   logisticsCompany    退回货物物流单号：logisticsNumber！ 谢谢合作");
	if(confirm("确定退回货物已发送？")){
		alert("卖家正在准备退款请耐心等待！");
		//写好之后请释放ajax方法
		/*$.ajax({
	        type:'post',
	        url:"/uc/ordRefundRequest/updateTuihuiPro",
	        data:{ordRefundId:ordRefundId,logisticsCompany:logisticsCompany,logisticsNumber:logisticsNumber},
	        async: false,
	        error: function(request) {
	          
	       	},
	        success: function(result) {
	        	if(result=='1'){
	                 alert("该笔订单售后已完成！");  
	                 window.location.reload();
	        	}
	        }
		});*/
	}
}