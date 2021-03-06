$(function(){
	var orderState=$(".refundStatus").val();
	$(".tapDiv").find(".tap").each(function(){
		if($(this).attr("mag")==orderState){
			$(".tapDiv").find("td").css("border-bottom","1px solid #EBECEE");
			$(this).parents("td").css("border-bottom","2px solid #D13600");
		}
	})
})

//物流信息 提交按钮
function submitButtonClick(){
	var wuliuNo= $(".wuliuNo").val();
	if(wuliuNo ==""){
		Info.view('物流单号不能为空');
		return ;
	}
	$("#sendLogisticsForm").submit();
}

function closetrackingNo(){
	$(".trackingNo").css("display","none");
	$(".zhezhao").css("display","none");
}





//-----------------------------------------------------------------
mui.init({
	pullRefresh: {
		container: '#pullrefresh',
		down: {
			callback: pulldownRefresh
		},
		up: {
			  contentrefresh : "正在加载...",//可选，正在加载状态时，上拉加载控件上显示的标题内容
		      contentnomore:'没有更多数据了',//可选，请求完毕若没有更多数据时显示的提醒内容；
		      callback :pullupRefresh //必选，刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据；
		}
	}
});
/**
* 下拉刷新具体业务实现
*/
function pulldownRefresh() {
setTimeout(function() {
	mui('#pullrefresh').pullRefresh().endPulldownToRefresh(); //refresh completed
}, 500);
}

/**
 * 上拉加载具体业务实现
 */
var pageIndex=1;
function pullupRefresh() {
	setTimeout(function() {
		mui('#pullrefresh').pullRefresh().endPullupToRefresh();
		var table = document.body.querySelector('.mui-table-view');
		var response = getResponseData(pageIndex);
		for(var e in response){
			var html="<li style='padding: 0px;background:#fff;width:100%;position: relative;' class='mui-table-view-cell'>"+
					"<div class='oneOrder' id="+response[e].orderRefundRequestPO.id+">"+
					"<div class='orderInfoDiv'>"+
					"<div class='orderNum'><span>售后单号："+response[e].orderRefundRequestPO.refundNumber+"</span></div>"+
					"<div class='orderTime'><span><fmt:formatDate value='"+response[e].orderRefundRequestPO.createTime+"' pattern='yyyy-MM-dd HH:mm:ss'/></span></div>"+
					"<div class='clear'></div></div>"+
					
					"<div class='oneGoods'>"+
					"<div class='goodsImgDiv'>"+
					"<img src="+response[e].orderRefundRequestPO.productPicUrl+" class='goodsImg'/>"+
					"</div>"+
					
					"<div class='goodsInfo'>"+
					"<div class='goodsName'>"+response[e].orderRefundRequestPO.productName+"</div>"+
					"<div class='goodsInfoFloor'>"+
					"<div class='inventory'>"+
					"<span>单价："+response[e].orderRefundRequestPO.productPrice+"</span>";
					if(response[e].orderRefundRequestPO.isTuihuo=="1"){
						html+="<span>退货数量："+response[e].orderRefundRequestPO.productQuant+"</span>";
					}
					html+="<span>规格：¥"+response[e].orderRefundRequestPO.productSpec+"</span>"+
					"</div>"+
					"<div><span>总价：</span><span class='price'>¥"+response[e].orderRefundRequestPO.productTotalAmount+"</span></div>"+
					"</div></div><div class='clear'></div></div>"+
					
					
					"<div class='goodsOperation'>";
					if(response[e].orderRefundRequestPO.refundStatus=="1"){
						html+="<div class='goodsStatus'>申请中</div>";
						if(response[e].productType=="2" && response[e].orderRefundRequestPO.fcgRefundFlag=="0"){
							html+="<button class='goodsBtn'>移交非常购</button>";
						}
						if(response[e].productType=="1"){
							html+="<button class='goodsBtn'>通过</button><button class='goodsBtn'>拒绝</button>";
						}
					}
					
					if(response[e].orderRefundRequestPO.refundStatus=="2"){
						html+="<div class='goodsStatus'>已通过</div>";
					}
					if(response[e].orderRefundRequestPO.refundStatus=="3"){
						html+="<div class='goodsStatus'>已完成</div>";
					}
					if(response[e].orderRefundRequestPO.refundStatus=="4"){
						html+="<div class='goodsStatus'>已拒绝</div>";
					}
					html+="<div class='clear'></div></div></div></li>"
			
			$("#goodsListInfo").append(html);
			/*this.endPullupToRefresh(true|false);*/
		}
	}, 500);
}

//获取实时分页数据
function getResponseData(obj){
	var response=new Array();
	var refundStatus=$(".refundStatus").val();
	//查询被选中的,初次
	if(obj==0){
		pageIndex=1;//初次加载，从第一页开始
	}else{
		pageIndex=obj+1;
	}
	$.ajax({
		url:'/uc/ordRefundRequest/findOrdRufundBypageAjax?refundStatus='+refundStatus,
		data:{"pageIndex":pageIndex},
		async:false,
		success:function(obj){
			response = obj.rows;
		}
	});
	return response;	
}

//tab选择
function findOrderRefundType(type){
	if(type=='-1'){
		window.location.href="/uc/ordRefundRequest/findOrdRufundBypage?refundStatus="+type;
	}
	if(type=='0'){
		window.location.href="/uc/ordRefundRequest/findOrdRufundBypage?refundStatus="+type;
	}
	if(type=='1'){
		window.location.href="/uc/ordRefundRequest/findOrdRufundBypage?refundStatus="+type;
	}
	if(type=='2'){
		window.location.href="/uc/ordRefundRequest/findOrdRufundBypage?refundStatus="+type;
	}
} 


//详细
mui(".mui-table-view").on('tap','.oneGoods',function(){
	  //获取id
	var id = this.getAttribute("id");
	window.location.href="/uc/ordRefundRequest/findBydetail?ordRefundId="+id;
});


//拒绝
function updateJuJue(ordRefundId){
	$(".zhezhao").css("display","block");
	$(".popup").css("display","block");
	$("#refuseRefundId").val(ordRefundId);
}

function cancelRefund(ordRefundId){
	$.ajax({
        type:'post',
        url:"/uc/ordRefundRequest/updateAuditBySeller",
        data:{ordRefundId:ordRefundId,refundStatus:4},
        async: false,
        error: function(request) {
          
       	},
        success: function(result) {
        	if(result=='1'){
                 alert("已撤销本次申请！");  
                 window.location.reload();
        	}
        }
	});
}
 function submitGrade(){
	 var textareaTypeVal=$(".textareaType").val();
		var textareaTypeKg = textareaTypeVal.replace(/\s/g,'');
		if(textareaTypeKg==""){
			Info.view("请输入拒绝理由！");
		}else{
			$("#ordRefundForm").submit();
		}
 }
function cancelClick(){
	$(".zhezhao").css("display","none");
	$(".popup").css("display","none");
}
//同意售后
function updateTongyi(ordRefundId){
	if(confirm("确定同意本次申请？")){
		$.ajax({
	        type:'post',
	        url:"/uc/ordRefundRequest/updateAuditBySeller",
	        data:{ordRefundId:ordRefundId,refundStatus:2},
	        async: false,
	        error: function(request) {
	          
	       	},
	        success: function(result) {
	        	if(result=='1'){
	                 alert("已同意本次申请！");  
	                 window.location.reload();
	        	}
	        }
		});
	}
}

//退款
function updateTuiKuan(ordRefundId){
	if(confirm("确定同意本次申请？")){
		$.ajax({
	        type:'post',
	        url:"/uc/ordRefundRequest/updateAuditBySeller",
	        data:{ordRefundId:ordRefundId,refundStatus:3},
	        async: false,
	        error: function(request) {
	          
	       	},
	        success: function(result) {
	        	if(result=='1'){
	                 alert("已同意该笔本次申请！");  
	                 window.location.reload();
	        	}
	        }
		});
	}
}
//平台订单移交与fcg
function updateTransferFcg(ordRefundId){
	if(confirm("确定将该笔订单售后移交FCG？")){
		$.ajax({
	        type:'post',
	        url:"/uc/ordRefundRequest/updateTransferFcg",
	        data:{ordRefundId:ordRefundId},
	        async: false,
	        error: function(request) {
	          
	       	},
	        success: function(result) {
	        	if(result=='1'){
	                 alert("已该笔订单售后移交FCG！");  
	                 window.location.reload();
	        	}
	        }
		});
	}
}


//最顶部Tab跳转
function findOrdType(type){
	//1:售后订单列表
	if(type=='1'){
		window.location.href="/uc/ordRefundRequest/findOrdRufundBypage";
	}
	//正常订单
	if(type=='0'){
		window.location.href="/uc/orderInfo/getOrderList";
	}
}
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
//买家退回货物
function updateTuihuiPro(ordRefundId){
	$("#sendLogisticsRefundId").val(ordRefundId);
	$(".trackingNo").css("display","block");
	$(".zhezhao").css("display","block");
}