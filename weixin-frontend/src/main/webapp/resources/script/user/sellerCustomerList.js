//详细  
/*function findDetail(userId){
	window.location.href="/uc/SellerCustomer/findByDetail?userId="+userId;
}*/

//绑定单击事件  详细
mui(".mui-table-view").on('tap','.content-item',function(){
	  //获取id
	var userId = this.getAttribute("id");
	window.location.href="/uc/SellerCustomer/findByDetail?userId="+userId;
});

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
			html+="<li style='padding:3vw 0;background:#fff;width:100%;position: relative;' class='mui-table-view-cell'>"+
				"<div class='content-item'>"+
				"<img src='/resources/images/default-Avatar.png'/>"+
				"<div class='info' onclick='findDetail("+response[e].userInfoPO.id+")'>"+
				"<span>"+response[e].userInfoPO.telNum+"</span>"+
				"<input type='hidden' class='userId' value='"+response[e].userInfoPO.id+"'>"+
				"<span>累计消费：¥<span>"+response[e].sumMoey+"</span></span>"+
				"</div></div></li>";
			$("#goodsListInfo").append(html);
		}
	}, 500);
}

//获取实时分页数据
function getResponseData(obj){
	var response=new Array();
	//查询被选中的,初次
	if(obj==1){
		pageIndex=2;//初次加载，从第一页开始
	}else{
		pageIndex=obj+1;
	}
	
	$.ajax({
		type:'get',
		url:"/uc/SellerCustomer/findByPageAjax",
		data:{"pageIndex":pageIndex},
		async:false,
		success:function(obj){
			response = obj.page.rows;
			console.log(response);
		}
	});
	return response;	
}