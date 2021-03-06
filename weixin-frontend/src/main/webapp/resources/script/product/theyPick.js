var fcgCatId;
$(function(){
	$("#sliderSegmentedControl").find(".mui-control-item:first").addClass("mui-active");
	$(".mui-slider-group").find(".mui-slider-item:first").addClass("mui-active");
	fcgCatId=$("#pinleiList").find(".mui-active").attr("id");
})
function closeClick(){
	$(".popup").css("display","none");
	$(".overlay").css("display","none");
}


mui.init();
(function($) {
	//阻尼系数
	var deceleration = mui.os.ios?0.003:0.0009;
	$('.mui-scroll-wrapper').scroll({
		bounce: false,
		indicators: true, //是否显示滚动条
		deceleration:deceleration
	});
	$.ready(function() {
		//循环初始化所有下拉刷新，上拉加载。
		$.each(document.querySelectorAll('.mui-slider-group .mui-scroll'), function(index, pullRefreshEl) {
			$(pullRefreshEl).pullToRefresh({
				down: {
					callback: function() {
						var self = this;
						setTimeout(function() {
							var ul = self.element.querySelector('.mui-table-view');
							//ul.insertBefore(createFragment(ul, index, 10, true), ul.firstChild);
							loadData("0");
							self.endPullDownToRefresh();
						}, 1000);
					}
				},
				up: {
					callback: function() {
						var self = this;
						setTimeout(function() {
							var ul = self.element.querySelector('.mui-table-view');
							ul.appendChild(createFragment(ul, index));
							self.endPullUpToRefresh();
						}, 1000);
					}
				}
			});
		});
		var createFragment = function(ul, index , count , reverse) {
			var length = ul.querySelectorAll('li').length;
			var fragment = document.createDocumentFragment();
			var li;
			var response=getResponseData("1");
			for(var e in response){
				li = document.createElement('li');
				li.setAttribute("style","padding: 0px;background: #FFFFFF;width:100vw;position: relative;");
				li.setAttribute("id",response[e].id);
				li.className = 'mui-table-view-cell';	
				var html="";
				html+="<div class='oneGoods'><input calss='goodsId' type='hidden' value="+response[e].id+"/>"+
				"<div class='goodsImgDiv'><img src="+response[e].f_picurl_logo+" class='goodsImg'></div>"+
				"<div class='goodsInfo'>" +
				"<div class='goodsName'>"+response[e].f_goods_name+"</div>" +
				"<div class='goodsInfoFloor'>" +
					"<div><span>售价：</span><span class='price'>¥"+response[e].f_price+"</span></div>" +
				"</div></div>" +
				"<div class='managAndShare'>" +
				"<div class='managementDiv'>" +
				"<img src='/resources/images/addAddressImg.png' class='managementImg'>"+
				"<div class='managementText'>管理</div></div></div><div class='clear'></div></div>";
				li.innerHTML = html;
			    fragment.appendChild(li);
			}
			return fragment;
		};
	});
})(mui);
//当前tab
var msgtype=0;
var managerCode='${managerCode}';
$(function(){
	loadData(0);	//初始化页面展示的是全部订单
});
function loadData(obj){     //初始化页面
	var response=getResponseData(obj);
	var html="";
	for(var e in response){
		html+="<li class='mui-table-view-cell' style='padding: 0px;margin-top:5px;background: #FFFFFF;width:100%;position: relative;' id='"+response[e].id+"' obj='"+JSON.stringify(response[e])+"'>"+
		"<div class='oneGoods'><input calss='goodsId' type='hidden' value="+response[e].id+"/>"+
		"<div class='goodsImgDiv'><img src="+response[e].f_picurl_logo+" class='goodsImg'></div>"+
		"<div class='goodsInfo'>" +
		"<div class='goodsName'>"+response[e].f_goods_name+"</div>" +
		"<div class='goodsInfoFloor'>" +
			"<div><span>售价：</span><span class='price'>¥"+response[e].f_price+"</span></div>" +
		"</div></div>" +
		"<div class='managAndShare'>" +
		"<div class='managementDiv'>" +
		"<img src='/resources/images/addAddressImg.png' class='managementImg'>"+
		"<div class='managementText'>管理</div></div></div><div class='clear'></div></div></li>";
	}
	$("#resultList"+msgtype).html(html);
}	


//获取实时分页数据
function getResponseData(obj){
	var response=new Array();
	var pageIndex="0";
	//查询被选中的,初次
	if(obj==0){
		pageIndex="1";//初次加载，从第一页开始
		$(".mui-scroll a")[msgtype].setAttribute("upindex",2);
	}else{
		pageIndex=$(".mui-scroll a")[msgtype].getAttribute("upindex");
		$(".mui-scroll a")[msgtype].setAttribute("upindex",parseInt(pageIndex)+1); 
	}
	fcgCatId=$("#pinleiList").find(".mui-active").attr("id");
	console.log(msgtype+"和"+pageIndex+"和"+fcgCatId);
	$.ajax({
		type:'post',
		url:'/uc/product/fcgProList',
		data:{"msgtype":msgtype,"pageIndex":pageIndex,"fcgCategoryId":fcgCatId},
		async:false,
		success:function(obj){
			response = obj.products;
			console.log(response);
		}
	});
	return response;	
}
//tab页点击切换
mui(".mui-scroll").on('tap','.mui-control-item',function(){
	/*jQuery(".mui-control-item").removeClass("mui-active");
	jQuery(this).addClass("mui-active");*/
	msgtype = $(this).attr("msgtype");
	loadData(0);  //点击已上线-未上线-下线标签初始化
})

//li绑定单击事件
mui(".mui-table-view").on('tap','.managAndShare',function(){
  var id =  jQuery(this).parents("li").attr("id");
  var product =  jQuery(this).parents("li").attr("obj");
  $("#fcgPro").val(product);
  jQuery(".popup").css("display","block");
  jQuery(".overlay").css("display","block");
  jQuery('html,body').addClass('overHiden');
})

function closeClick(){
	$(".popup").css("display","none");
	$(".overlay").css("display","none");
	$('html,body').removeClass('overHiden');
}

function changeClick(){
	var increase = $("#increase").val();
	var pro =$("#fcgPro").val();
	pro['increase'] = increase;
	console.log(pro);
	$.ajax({
        type:'post',
        url:"/uc/product/addPlatProduct",
        data:pro,
        async: false,
        success: function(result) {
	 		if(result=='success'){
        		window.location.href="/uc/product/initList";
        	}
	}
	})
	}

