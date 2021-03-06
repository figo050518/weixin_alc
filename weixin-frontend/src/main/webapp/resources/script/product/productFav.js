$(".checkedAll").click(function() {
	if($(this).is(":checked")) {
		$(".goodsCheck").prop('checked', true);
		$(".checkedAll").prop('checked', true);
	} else {
		$('.goodsCheck').attr('checked', false);
		$('.checkedAll').attr('checked', false);

	}
});
$(".goodsCheck").click(function() {
	var goodsN = $(".oneGoods").find(".goodsCheck");

	var goodsC = $(".oneGoods").find(".goodsCheck:checked");
	if(goodsN.length == goodsC.length) {
		$(".checkedAll").prop('checked', true);
	} else {
		$(".checkedAll").prop('checked', false);
	}
})

//点击删除
function deleteClick(){
	var result=new Array(),
	goodsList=new Array(),
	str='',
	goodsStr='';
	$("#pullrefresh").find('input[type=checkbox]:checked').each(function(){
		//提取出选中的checkbox的外层div用push组合起来
	   result.push($(this).parents(".oneGoods").attr('id'));
	});
	var str=result.join(',');
	if(str==''){
		Info.view('请最少选择一项！');
	   return false;
	}
	else{
		$.ajax({
  	        type:'post',
  	        url:"/uc/product/fav/delete/"+str,
  	        async: false,
  	        success: function(result) {
  	        	if(result=='1'){
  	        		Info.view("删除成功！");
  	        		for(var i=0;i<result.length;i++){
  	        			$("#"+result[i]).remove();
  	        		}
  	        		window.location.reload();
  	        	}else if(result =='0'){
  	        		Info.view("删除失败");
  	        	}
  	        }
  		});
	}
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
			htm+="<div class='oneGoods' id="+response[e].id+">"+
					"<div class='checkGoods'>"+
						"<label class='checkboxDiv'><input type='checkbox' class='opaction goodsCheck'/></label>"+
					"</div>"+
					"<div class='goodsImgDiv'><img src='/resources/images/goodsImg.jpg' class='goodsImg'/></div>"+
					/*"<div class='goodsImgDiv'><img src='/images/products/orgin/"+response[e].productPicId+"' class='goodsImg'/></div>"+*/
					"<div class='goodsInfo'>"+
						"<div class='goodsName'>"+response[e].productName+"</div>"+
						"<div class='goodsInfoFloor'><span>售价：</span><span class='price'>¥108.00</span></div>"+
						/*"<span class='pdtPrice'>￥<span style='display: inline;'>"+response[e].price+"</span></span>"*/
					"</div><div class='clear'></div></div>";
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
		$(".footerFixed").remove();
		$(".head").after(blankHtml);
	}else{
		for(var e in response){
			html+="<li class='mui-table-view-cell' style='padding: 0px;background: #FFFFFF;width:100vw;position: relative;' id='"+response[e].id+"'>"+
					"<div class='oneGoods' id="+response[e].id+">"+
					"<div class='checkGoods'>"+
						"<label class='checkboxDiv'><input type='checkbox' class='opaction goodsCheck'/></label>"+
					"</div>"+
					"<div class='goodsImgDiv'><img src='/resources/images/goodsImg.jpg' class='goodsImg'/></div>"+
					"<div class='goodsInfo'>"+
						"<div class='goodsName'>"+response[e].productName+"</div>"+
						"<div class='goodsInfoFloor'><span>售价：</span><span class='price'>¥108.00</span></div>"+
					"</div><div class='clear'></div></div>";
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
	$.ajax({
		type:'post',
		url:'/uc/product/fav/list',
		data:{"pageIndex":pageIndex},
		async:false,
		success:function(obj){
			response = obj.userProductFavPOs;
		}
	});
	return response;	
}