$(function(){
	var orderState=$(".orderState").val();
	$(".tapDiv").find(".tap").each(function(){
		if($(this).attr("mag")==orderState){
			$(".tapDiv").find(".tap").css("border-bottom","1px solid #EBECEE");
			$(this).css("border-bottom","2px solid #D13600");
		}
	})
})

function findByOrderState(tab){
	//全部订单
	if(tab=="-1"){
		window.location.href="/uc/orderInfo/getOrderList";
	}
	//待付款
	if(tab=="0"){
		window.location.href="/uc/orderInfo/getOrderList?orderState=0";
	}
	//待发货
	if(tab=="1"){
		window.location.href="/uc/orderInfo/getOrderList?orderState=1";
	}
	//待收货
	if(tab=="2"){
		window.location.href="/uc/orderInfo/getOrderList?orderState=2";
	}
	//已完成
	if(tab=="3"){
		window.location.href="/uc/orderInfo/getOrderList?orderState=3";
	}
	//已关闭
	if(tab=="4"){
		window.location.href="/uc/orderInfo/getOrderList?orderState=4";
	}
}



//实现分页的js
(function($, doc) {
	$.init();
	$.ready(function() {
		
	});
})(mui, document);

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
			var html="";
			html+="<li style='padding: 0px;background:#fff;width:100%;position: relative;' class='mui-table-view-cell'>"+
				"<div class='oneOrder' onclick='seeDetail("+response[e].orderInfoPO.id+")'>";
			var productInfo=response[e].orderItemsDTOs;
			for(var i in productInfo){
				html+="<div class='oneGoods'>"+
				"<div class='goodsImgDiv'><img src="+productInfo[i].imageUrl+" class='goodsImg'/></div>"+
				"<div class='goodsInfo'>"+
				"<div class='goodsName'>"+productInfo[i].orderItemPO.productName+"</div>"+
				"<div class='goodsInfoFloor'>"+
				"<div class='inventory'>"+
				"<span>数量："+productInfo[i].orderItemPO.productQuant+"</span><span>件</span>"+
				"</div>"+
				"<div><span>售价：</span><span class='price'>¥"+productInfo[i].orderItemPO.productPrice+"</span></div>"+
				"</div></div><div class='clear'></div></div>";
			}
			html+="<div class='orderInfoDiv'>"+
			"<div class='orderNum'><span>单号："+response[e].orderInfoPO.orderNum+"</span></div>"+
			"<div class='orderTime'><span><fmt:formatDate value='"+response[e].orderInfoPO.createTime+"' pattern='yyyy-MM-dd HH:mm:ss'/></span></div>"+
			"</div><div class='clear'></div></div>"+
			"<div class='orderStateDiv'>"+
			"<div class='orderState'>";
			if(response[e].orderInfoPO.orderState=="0"){
				html+="<span>待付款</span>";
			}
			if(response[e].orderInfoPO.orderState=="1"){
				html+="<span>待发货</span>";
			}
			if(response[e].orderInfoPO.orderState=="2"){
				html+="<span>待收货</span>";
			}
			if(response[e].orderInfoPO.orderState=="3"){
				html+="<span>已完成</span>";
			}
			if(response[e].orderInfoPO.orderState=="4"){
				html+="<span>交易关闭</span>";
			}
			
			var userType=$(".userType").val();
			if(response[e].orderInfoPO.orderState=="0"){
				if(userType=="1"){
					html+="<button class='orderBtn cancleBtn1' onclick='cancleBtn("+response[e].orderInfoPO.id+")'>取消订单</button><button class='orderBtn paymentBtn'>去付款</button>";
				}
				if(userType=="2" && response[e].orderInfoPO.orderType=="1"){
					html+="<button class='orderBtn cancleBtn2' onclick='cancleBtn("+response[e].orderInfoPO.id+")'>取消订单</button><button class='orderBtn cancleBtn' onclick='changeOrderPrice("+response[e].orderInfoPO.id+")'>修改价格</button>	";
				}
			}
			if(response[e].orderInfoPO.orderState=="1"){
				if(userType=="2" && response[e].orderInfoPO.orderType=="1"){
					html+="<button class='orderBtn fahuoBtn' onclick='fahuoBtn("+response[e].orderInfoPO.id+")'>确认发货</button>";
				}
			}
			if(response[e].orderInfoPO.orderState=="2"){
				if(userType=="1"){
					html+="<button class='orderBtn goodsBtn' onclick='goodsBtn("+response[e].orderInfoPO.id+")'>确认收货</button>";
				}
			}
			if(response[e].orderInfoPO.orderState=="3"){
				if(userType=="1" && response[e].orderInfoPO.isEaluate=="0"){
					html+="<button class='orderBtn evaluationBtn' onclick='evaluationBtn("+response[e].orderInfoPO.id+")'>立即评价</button>";
				}
			}
			html+="</div><div class='clear'></div></div></div></li>";
			$("#goodsListInfo").append(html);
			this.endPullupToRefresh(true|false);
		}
	}, 500);
}

//获取实时分页数据
function getResponseData(obj){
	var response=new Array();
	var orderState=$(".orderState").val();
	var url="";
	//查询被选中的,初次
	if(obj==1){
		pageIndex=2;//初次加载，从第一页开始
	}else{
		pageIndex=obj+1;
	}
	if(orderState==""){
		url="/uc/orderInfo/orderInfoAjax";
	}else{
		url='/uc/orderInfo/orderInfoAjax?orderState='+orderState;
	}
	$.ajax({
		type:'get',
		url:url,
		data:{"pageIndex":pageIndex},
		async:false,
		success:function(obj){
			response = obj.rows;
			console.log(obj);
		}
	});
	return response;	
}

/*function seeDetail(id){
	window.location.href="/uc/orderInfo/getByOrderId?orderId="+id;
}*/
//绑定单击事件
mui(".mui-table-view").on('tap','.oneGoods',function(){
	  //获取id
	var id = this.getAttribute("id");
	window.location.href="/uc/orderInfo/getByOrderId?orderId="+id;
});


//取消订单
function cancleBtn(id){
	if(confirm("确定要取消该笔订单吗？")){
		alert("这里有弹框填写取消订单原因！");
		var closeDesc ="取消订单原因";
		$.ajax({
            type:'post',
            url:"/uc/orderInfo/updateCancelPro",
            data:{orderId:id,closeDesc:closeDesc},
            async: false,
            error: function(request) {
              
           	},
            success: function(result) {
            	if(result=='1'){
            		alert("订单取消成功");
            		window.location.reload();
            	}
            }
    	});
	 }
}

//商家待付款中的修改订单总价
function changeOrderPrice(id){
	alert("11");
}
//取消订单
function fahuoBtn(id){
	if(confirm("确定要发货吗？")){
		$.ajax({
            type:'post',
            url:"/uc/orderInfo/updateSendPro",
            data:{orderId:id},
            async: false,
            error: function(request) {
              
           	},
            success: function(result) {
            	if(result=='1'){
            		alert("发货成功");
            		window.location.reload();
            	}
            }
    	});
	 }
}
//取消订单
function goodsBtn(id){
	if(confirm("确定收货吗？")){
		$.ajax({
            type:'post',
            url:"/uc/orderInfo/updateSurePro",
            data:{orderId:id},
            async: false,
            error: function(request) {
              
           	},
            success: function(result) {
            	if(result=='1'){
            		alert("收货成功");
            		window.location.reload();
            	}
            }
    	});
	 }
}
//立即评价
function evaluationBtn(id){
	alert("这里处理评价");
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