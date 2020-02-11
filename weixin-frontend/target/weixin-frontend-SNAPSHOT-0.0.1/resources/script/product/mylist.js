function findByOrder(tab){
	//出售中
	if(tab=="1"){
		window.location.href="/uc/product/initList?upState=1";
	}
	//已下架
	if(tab=="0"){
		window.location.href="/uc/product/initList?upState=0";
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
			htm+="<div class='oneGoods'><div class='checkGoods'><label class='checkboxDiv'><input type='checkbox' class='opaction goodsCheck'/></label></div><div class='goodsImgDiv'><img src='/resources/images/goodsImg.png' class='goodsImg'/>"+
			"<input type='hidden' class='goodsId' value='"+response[e].id+"'/>"+
			"</div><div class='goodsInfo'><div class='goodsName'>"+response[e].productName+"<div class='goodsInfoFloor'><div class='inventory'><span>库存：</span><span>"+response[e].stocks+"&nbsp;&nbsp;</span><span>";
			//平台上架
			if(response[e].fromType==2){
				htm+="利润：¥</span><span>"+response[e].maxPrice;//不确定
			}		
			htm+="<div><span>售价：</span><span class='price'>¥"+response[e].minPrice+"~"+response[e].maxPrice+"</span></div></div></div>"+
			"<div class='clear'></div><div class='operationDiv' onclick='operationList(this)'><img src='/resources/images/operationImg.png' class='operationIcon'/></div>"+
			"<div class='operationList' style='display: none;'><div class='navigation'><table cellpadding='0' cellspacing='0' class='operationTab'><tr><td class='editClick'>"+
			"<div class='floorNav'><img src='/resources/images/edit.png' class='operationImg'/></div><div class='operationName' onclick='edit("+response[e].id+")'>编辑</div></td><td class='xiajiaClick'><div class='floorNav'><img src='/resources/images/xiajia.png' class='operationImg'/>"+
			"</div><div class='operationName' onclick='offline("+response[e].id+")'>下架</div></td><td class='fenleiClick'><div class='floorNav'><img src='/resources/images/fenlei.png' class='operationImg'/></div><div class='operationName' onclick='setFenLei()'>分类</div></td><td class='deleteClick'>"+
			"<div class='floorNav'><img src='/resources/images/edit.png' class='operationImg'/></div><div class='operationName'>删除</div></td><td><div class='floorNav'><img src='/resources/images/tuiguang.png' class='operationImg'/></div>"+
			"<div class='operationName'>推广</div></td></tr></table>"+
			"</div><div class='sanjiao'></div></div></div></div><div class='clear'></div></div>";
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
			html+="<li class='mui-table-view-cell' style='padding: 0px;background: #FFFFFF;width:100vw;position: relative;'>"+
			"<input type='hidden' class='goodsId' value='"+response[e].id+"'/>"+
			"<div class='oneGoods'><div class='checkGoods'><label class='checkboxDiv'><input type='checkbox' class='opaction goodsCheck'/></label></div><div class='goodsImgDiv'><img src='/resources/images/goodsImg.png' class='goodsImg'/>"+
			"</div><div class='goodsInfo'><div class='goodsName'>"+response[e].productName+"<div class='goodsInfoFloor'><div class='inventory'><span>库存：</span><span>"+response[e].stocks+"&nbsp;&nbsp;</span><span>";
			//平台上架
			if(response[e].fromType==2){
				html+="利润：¥</span><span>"+response[e].maxPrice;//不确定
			}		
			html+="<div><span>售价：</span><span class='price'>¥"+response[e].minPrice+"~"+response[e].maxPrice+"</span></div></div></div>"+
			"<div class='clear'></div><div class='operationDiv' onclick='operationList(this)'><img src='/resources/images/operationImg.png' class='operationIcon'/></div>"+
			"<div class='operationList' style='display: none;'><div class='navigation'><table cellpadding='0' cellspacing='0' class='operationTab'><tr><td class='editClick'>"+
			"<div class='floorNav'><img src='/resources/images/edit.png' class='operationImg'/></div><div class='operationName' onclick='edit("+response[e].id+")'>编辑</div></td><td class='xiajiaClick'><div class='floorNav'><img src='/resources/images/xiajia.png' class='operationImg'/>"+
			"</div><div class='operationName' onclick='offline("+response[e].id+")'>下架</div></td><td class='fenleiClick'><div class='floorNav'><img src='/resources/images/fenlei.png' class='operationImg'/></div><div class='operationName' onclick='setFenLei()'>分类</div></td><td class='deleteClick'>"+
			"<div class='floorNav'><img src='/resources/images/edit.png' class='operationImg'/></div><div class='operationName'>删除</div></td><td><div class='floorNav'><img src='/resources/images/tuiguang.png' class='operationImg'/></div>"+
			"<div class='operationName'>推广</div></td></tr></table>"+
			"</div><div class='sanjiao'></div></div></div></div><div class='clear'></div></div></li>";
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
	var upState=$(".upState").val();
	if(upState==""){
		upState=1;
	}
	$.ajax({
		type:'post',
		url:'/uc/product/mylist',
		data:{"pageIndex":pageIndex,'upState':upState},
		async:false,
		success:function(obj){
			console.log(obj);
			response = obj.products;
			$(".upState").val(obj.upState);
			if(upState==1){
				$("#onSaleCount").html(obj.totalCount);
				$("#offSaleCount").html(obj.totalCountAll*1-obj.totalCount*1);
			}
			if(upState==0){
				$("#offSaleCount").html(obj.totalCount);
				$("#onSaleCount").html(obj.totalCountAll*1-obj.totalCount*1);
			}
		}
	});
	return response;	
}

/*function operationList(obj){
	var operationList=$(obj).parents(".oneGoods").find(".operationList");
	if(operationList.css("display")=="none"){
		operationList.css("display","block");
	}else{
		operationList.css("display","none");
	}
}*/

//绑定单击事件
mui(".mui-table-view").on('tap','.operationDiv',function(){
	var operationList=$(this).parents(".oneGoods").find(".operationList");
	if(operationList.css("display")=="none"){
		operationList.css("display","block");
	}else{
		operationList.css("display","none");
	}
});


/*function edit(id){
	window.location.href="/uc/product/preEdit?id="+id;
}*/
//编辑
mui(".mui-table-view").on('tap','.editClick',function(){
	var id=$(this).parents("li").find(".goodsId").val();
	window.location.href="/uc/product/preEdit?id="+id;
});



/*function offline(productId){
	$.ajax({
		type:'post',
		url:'/uc/product/upStateUpdate',
		data:{"productId":productId,'state':0},
		async:false,
		success:function(res){
			
			if(res=="success"){
				Info.view("下架成功！");
				window.location.href="/uc/product/initList";
			}
			if(upState==0){
				Info.view("下架失败！");
			}
		}
	});
}*/

//下架
mui(".mui-table-view").on('tap','.xiajiaClick',function(){
	var id=$(this).parents("li").find(".goodsId").val();
	$.ajax({
		type:'post',
		url:'/uc/product/upStateUpdate',
		data:{"productId":id,'state':0},
		async:false,
		success:function(res){
			
			if(res=="success"){
				Info.view("下架成功！");
				window.location.href="/uc/product/initList";
			}
			if(upState==0){
				Info.view("下架失败！");
			}
		}
	});
});

//点击设置分类
function setFenLei(){
	alert(11)
}