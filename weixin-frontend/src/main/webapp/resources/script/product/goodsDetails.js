var jsonArrayString="";
function chooseType(){
	$(".btnDiv1").find(".buyNowGoods").css("display","none");
	$("#popupBtn1").css("display","block");
	$(".popupCover").css("display","block");
	$(".popup").css("display","block");
	goodsType();
	
	//console.log(jsonArrayString);
}

//选择商品规格
/*function chooseColor(obj){
 	$(obj).siblings().css({"color":"#333","background":"#F5F5F5","border":"1px solid #C9C9C9"});
 	$(obj).css({"color":"#fff","background":"#D13600","border":"1px solid #fff"});
 	$(".goodsType").val($(obj).text());
 	$(".checkedGoodsType").text($(obj).text());
}
*/
//选择属性
function chooseColor(obj){
	var specId=$(obj).attr("id");
	$.ajax({
        url:"/uc/productInfo/getInfoByspecId",
        data:{"specId":specId},
        async: false,
        error: function(request) {
          
       	},
        success: function(result) {
        	if(result.stock>0){
        		$("#salesPrice").html(result.salesPrice.toFixed(2));
            	$("#stock").html(result.stock);
            	$(obj).siblings().css({"color":"#333","background":"#F5F5F5","border":"1px solid #C9C9C9"});
            	$(obj).css({"color":"#fff","background":"#D13600","border":"1px solid #fff"});
            	$(obj).siblings().attr("class", "colorType");
            	$(obj).addClass("active");
            	$(".goodsType").val(specId);
        	}else{
        		Info.view("产品库存不足，请选择其他规格！");
        	}
        	
        }
	});
}
function toOtherPage(){
	if($(".headNav").css("display")=="none"){
		$(".headNav").css("display","block");
	}else{
		$(".headNav").css("display","none");
	}
}

//关闭弹窗
function closePopup(){
	$(".popupCover").css("display","none");
	$(".popup").css("display","none");
	var goodsType=$(".goodsType").val();
	$(".goodsType").val(goodsType);
 	$(".checkedGoodsType").text($(".active").text());
 	
 	var goodsCount=$("#goodsCount").val();
	var sellerId=$(".sellerId").val();
	var productId=$(".productId").val();
	var specId=$(".goodsType").val();
	var jsonArray=new Array();
	jsonArray=[{
			sellerId:sellerId,
			productId: productId,
			goodsCount: goodsCount,
			specId:specId
			}];
	jsonArrayString=JsonArrayToStringCfz(jsonArray);
}

//商品数量
$(".min").click(function() {
    var t = $(this).parent().find('.text_box');
    t.val(parseInt(t.val()) - 1);
    if (t.val() <= 1) {
      t.val(1);
    }
});
  // 数量加
$(".add").click(function() {
	 //获取该属性商品的库存
	var kucun=$("#stock").text();
	console.log(kucun);
	var t = $(this).parent().find('.text_box');
    if(parseInt(t.val())<parseInt(kucun)){
    	t.val(parseInt(t.val()) + 1);
    }
});

//点击加入购物车
function toAddShoppingCart(){
	if(jsonArrayString==""){
		$(".buyNowGoods").css("display","none");
		$("#popupBtn2").css("display","block");
		$(".popupCover").css("display","block");
		$(".popup").css("display","block");
		goodsType();
	}else{
		//商品数量
		var goodsCount=$("#goodsCount").val();
		var sellerId=$(".sellerId").val();
		var productId=$(".productId").val();
		var specId=$(".goodsType").val();
		var jsonArray=new Array();
		jsonArray=[{
				sellerId:sellerId,
				productId: productId,
				productCount: goodsCount,
				specId:specId
				}];
		jsonArrayString=JsonArrayToStringCfz(jsonArray);
		console.log(jsonArrayString);
		$.ajax({
			type:'post',
			url:"/uc/shopCart/add",
			data:{"productInfoJson":jsonArrayString},
			async:false,
			success:function(result){
				if(result=="1"){
					$(".p_view").css("display","none");
					Info.view("添加成功！！");
				}
			}
		});
	}
	
}
//点击弹窗里面的加入购物车
function addShopCart(){
	//商品数量
	var goodsCount=$("#goodsCount").val();
	var sellerId=$(".sellerId").val();
	var productId=$(".productId").val();
	var specId=$(".goodsType").val();
	var jsonArray=new Array();
	jsonArray=[{
			sellerId:sellerId,
			productId: productId,
			productCount: goodsCount,
			specId:specId
			}];
	jsonArrayString=JsonArrayToStringCfz(jsonArray);
	console.log(jsonArrayString);
	$.ajax({
		type:'post',
		url:"/uc/shopCart/add",
		data:{"productInfoJson":jsonArrayString},
		async:false,
		success:function(result){
			if(result=="1"){
				$(".p_view").css("display","none");
				Info.view("添加成功！！");
			}
		}
	});
}
//确定
/*function sureClick(){
	$(".btnDiv1").find(".buyNowGoods").css("display","none");
	$("#popupBtn3").css("display","block");
	$(".popupCover").css("display","block");
	$(".popup").css("display","block");
	goodsType()
	$(".checkedGoodsType").text($(".active").text());
}*/

function sureClick(){
	$(".popupCover").css("display","none");
	$(".popup").css("display","none");
	
	$(".checkedGoodsType").text($(".active").text());
	goodsType();
}

//点击立即购买 如果选了规格就到确认订单页面  else弹窗选择
function buyNowGoods(){
	if(jsonArrayString==""){
		$(".buyNowGoods").css("display","none");
		$("#popupBtn3").css("display","block");
		$(".popupCover").css("display","block");
		$(".popup").css("display","block");
		goodsType();
	}else{
		//到下单页面
		//商品数量
		var goodsCount=$("#goodsCount").val();
		var sellerId=$(".sellerId").val();
		var productId=$(".productId").val();
		var specId=$(".goodsType").val();
		//console.log(specId);
		var jsonArray=new Array();
		jsonArray=[{
				sellerId:sellerId,
				productId: productId,
				productCount: goodsCount,
				specId:specId
				}];
		var jsonArrayString=JsonArrayToStringCfz(jsonArray);
		
		//console.log(jsonArrayString);
		window.location.href="/uc/placeOrder/confirmOrder?productInfoJson="+jsonArrayString;
	}
}

function buyNowGoodsDetail(){
	//商品数量
	var goodsCount=$("#goodsCount").val();
	var sellerId=$(".sellerId").val();
	var productId=$(".productId").val();
	var specId=$(".goodsType").val();
	//console.log(specId);
	var jsonArray=new Array();
	jsonArray=[{
			sellerId:sellerId,
			productId: productId,
			productCount: goodsCount,
			specId:specId
			}];
	var jsonArrayString=JsonArrayToStringCfz(jsonArray);
	
	//console.log(jsonArrayString);
	window.location.href="/uc/placeOrder/confirmOrder?productInfoJson="+jsonArrayString;
}
function goodsType(){
	//获取选择的商品规格，如果没选择，就是默认选择第一个
	var goodsType=$(".goodsType").val();
	if(goodsType==""){
		$(".colorTypeDiv").find(".colorType:first-child").css({"background":"#D13600","color":"#FFFFFF"});
		$(".goodsType").val($(".colorTypeDiv").find(".colorType:first-child").attr("id"));
		$(".colorTypeDiv").find(".colorType:first-child").addClass("active");
	}
	else{
		$(".colorTypeDiv").find(".colorType").each(function(){
			if($(this).text()==goodsType){
				$(this).css({"background":"#D13600","color":"#FFFFFF"});
				$(this).addClass("active");
			}
		});
	}
	
	var goodsCount=$("#goodsCount").val();
	var sellerId=$(".sellerId").val();
	var productId=$(".productId").val();
	var specId=$(".goodsType").val();
	var jsonArray=new Array();
	jsonArray=[{
			sellerId:sellerId,
			productId: productId,
			productCount: goodsCount,
			specId:specId
			}];
	jsonArrayString=JsonArrayToStringCfz(jsonArray);
	//console.log(jsonArrayString);
}




function JsonArrayToStringCfz(jsonArray){
	var JsonArrayString = "["; 
	for(var i=0;i<jsonArray.length;i++){ 
		JsonArrayString=JsonArrayString+json2str(jsonArray[i])+","; 
	} 
	JsonArrayString = JsonArrayString.substring(0,JsonArrayString.length-1)+"]"; 
	return JsonArrayString; 
}

function json2str(o) { 
	var arr = []; 
	var fmt = function(s) { 
	if (typeof s == 'object' && s != null) return json2str(s); 
		return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s; 
	} 
	for (var i in o) arr.push("'" + i + "':" + fmt(o[i])); 
		return '{' + arr.join(',') + '}'; 
}

//下一步
function toNext(){
	window.location.href="";
}
//




function toShopCat(){
	window.location.href="/uc/shopCart/list";
}


function toFavorites(){
	var productId=$(".productId").val();
	$.ajax({
		type:'post',
		url:"/uc/product/fav/add",
		data:{"productId":productId},
		async:false,
		success:function(result){
			if(result=="addSuccess"){
				$(".p_view").css("display","none");
				$(".shoucangImg").attr("src","/resources/images/shoucangHave.png");
				Info.view("收藏成功！！");
			}
			if(result=="cancelSuccess"){
				$(".p_view").css("display","none");
				$(".shoucangImg").attr("src","/resources/images/shoucangNot.png");
				Info.view("取消收藏！！");
			}
		}
	});
}
