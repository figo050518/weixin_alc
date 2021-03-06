function TixianShow() {
	window.location.href="/uc/finance/withdrwaApplyInit";
}

function shouzhidetailShow() {
	window.location.href="/uc/finance//transaction/init";
}


function PresentationRecordShow() {
	window.location.href="/uc/finance//withDrawApply/init"
}


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
		//loadData(0);//初次加载
		//document.getElementById('businessSummary').innerHTML=html;
		mui('#pullrefresh').pullRefresh().endPulldownToRefresh(); //refresh completed
		//mui('#pullrefresh').pullRefresh().refresh(true);
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
			htm+="<div class='oneDrawApply'>"+
				"<div class='time'><span>"+response[e].addTime+"</span></div>"+
				"<div class='price'><span>¥"+response[e].withdrawAmount+"</span></div>";
				if(response[e].auditState=="1"){
					htm+="<div class='status'><span>审核中</span></div>";
				}
				if(response[e].auditState=="2"){
					htm+="<div class='status'><span>提现成功</span></div>";
				}
				if(response[e].auditState=="3"){
					htm+="<div class='status'><span>提现失败</span></div>";
				}
				htm+="<div class='clear'></div></div>"
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
						"<div class='blankText'>您还没有相关数据哦！</div>"+
						"<div class='blankBtnDiv'>"+
							"<button class='blankBtn'>这是按钮</button>"+
						"</div></div>";
		$("#pullrefresh").remove();
		$(".head").after(blankHtml);
	}else{
		for(var e in response){
			html+="<li class='mui-table-view-cell' style='padding: 0px;background: #FFFFFF;width:100vw;position: relative;' id='"+response[e].id+"'>"+
						"<div class='oneDrawApply'>"+
						"<div class='time'><span>"+response[e].addTime+"</span></div>"+
						"<div class='price'><span>¥"+response[e].withdrawAmount+"</span></div>";
						if(response[e].auditState=="1"){
							html+="<div class='status'><span>审核中</span></div>";
						}
						if(response[e].auditState=="2"){
							html+="<div class='status'><span>提现成功</span></div>";
						}
						if(response[e].auditState=="3"){
							html+="<div class='status'><span>提现失败</span></div>";
						}
						html+="<div class='clear'></div></div>"
				 "</li>";
		}
		$("#goodsList").html(html);
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
	console.log(pageIndex);
	$.ajax({
		type:'get',
		url:'/uc/finance/withdrawApply/list',
		data:{"pageIndex":pageIndex},
		async:false,
		success:function(obj){
			response = obj.withDrawApplyList;
		}
	});
	return response;	
}

