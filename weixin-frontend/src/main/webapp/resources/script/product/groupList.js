$(function(){

})


$(function(){
	loadData(0);
});
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
			contentrefresh: '正在加载...',
			callback: pullupRefresh
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
var pageIndex=0;
function pullupRefresh() {
	setTimeout(function() {
		mui('#pullrefresh').pullRefresh().endPullupToRefresh(); //参数为true代表没有更多数据了。
		var table = document.body.querySelector('.mui-table-view');
		var response = getResponseData(pageIndex);
		for(var e in response){
			li = document.createElement('li');
			li.setAttribute("style","padding: 0px;background: #FFFFFF;width:100vw;position: relative;");
			li.setAttribute("id",response[e].id);
			li.className = 'mui-table-view-cell';
			var htm="";
			htm+="<div class='contentItem' id="+response[e].id+"><div class='itemText'>"+response[e].groupName+"</div><div class='itemImg'><img src='/resources/images/next1.png'/></div><div class='itemNum'>"+response[e].productCount+"</div></div>";
			li.innerHTML = htm;
			table.appendChild(li);
		}
	}, 500);
}

function loadData(obj){     //初始化页面
	var response=getResponseData(obj);
	var html="";
	var blankHtml="";
	if(response==0 && obj=="0"){
		var blankHtml="<div class='blankState'>"+
						"<div class='blankImgDiv'>"+
							"<img src='/resources/images/orderBlank.png' class='blankImg'/>"+
						"</div>"+
						"<div class='blankText'>您还没有相关订单哦！</div>"+
						"<div class='blankBtnDiv'>"+
							"<button class='blankBtn'>这是按钮</button>"+
						"</div></div>";
		$("#pullrefresh").remove();
		$(".head").after(blankHtml);
	}else{
		console.log(response);
		for(var e in response){
			html+="<li class='mui-table-view-cell' style='padding: 0px;background: #FFFFFF;width:100vw;position: relative;'>"+
				"<div class='contentItem' id="+response[e].id+">" +
					"<div class='itemText'>"+response[e].groupName+"</div>" +
					"<div class='itemImg'><img src='/resources/images/next1.png'/></div>" +
					"<div class='itemNum'>"+response[e].productCount+"</div>" +
				  "</div></li>";
		}
		$("#content").html(html);
	}
}

//获取实时分页数据
function getResponseData(obj){
	var response=new Array();
	//查询被选中的,初次
	if(obj==0){
		pageIndex=1;//初次加载，从第一页开始
	}else{
		pageIndex=obj+1;
	}
	$.ajax({
		type:'post',
		url:'/uc/productGroup/list',
		data:{"pageIndex":pageIndex,'needCount':true},
		async:false,
		success:function(obj){
			response = obj.groups;
		}
	});
	return response;
}



function view(id){
	window.location.href="/uc/product/initList?groupId="+id;
}

function viewProduct(){
	window.location.href="/uc/product/initList";
}

//绑定单击事件
mui("body").on('tap','.contentItem',function(){
	var id=$(this).attr("id");
	window.location.href="/uc/product/initList?groupId="+id;
});