function chooseType(){
	$(".btnDiv1").find(".buyNowGoods").css("display","none");
	$("#popupBtn1").css("display","block");
	$(".popupCover").css("display","block");
	$(".popup").css("display","block");
	
	goodsType();
}
//选择商品规格
function chooseColor(obj){
 	$(obj).siblings().css({"color":"#333","background":"#F5F5F5","border":"1px solid #C9C9C9"});
 	$(obj).css({"color":"#fff","background":"#D13600","border":"1px solid #fff"});
 	$(".goodsType").val($(obj).text());
 	$(".checkedGoodsType").text($(obj).text());
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
 	$(".checkedGoodsType").text(goodsType);
}

//商品数量
$(".min").click(function() {
    var t = $(this).parent().find('.text_box');
    t.val(parseInt(t.val()) - 1);
    if (t.val() <= 1) {
      t.val(1);
    }
    /*$(this).parent().parent().next().find(".goodsCounts").text(t.val());
    TotalPrice();*/
});
  // 数量加
$(".add").click(function() {
    var t = $(this).parent().find('.text_box');
    t.val(parseInt(t.val()) + 1);
    if (t.val() <= 1) {
      t.val(1);
    }
    /*$(this).parent().parent().next().find(".goodsCounts").text(t.val());
    TotalPrice();*/
});

//点击加入购物车
function toAddShoppingCart(){
	$(".btnDiv1").find(".buyNowGoods").css("display","none");
	$("#popupBtn2").css("display","block");
	$(".popupCover").css("display","block");
	$(".popup").css("display","block");
	
	goodsType();
}

//立即购买
function buyNowGoods(){
	$(".btnDiv1").find(".buyNowGoods").css("display","none");
	$("#popupBtn3").css("display","block");
	$(".popupCover").css("display","block");
	$(".popup").css("display","block");
	goodsType()
}

//点击弹窗里面的加入购物车
function sureAdd(){
	$.ajax({
		type:'post',
		url:"",
		data:{"pageIndex":pageIndex+""},
		async:false,
		success:function(result){
			if(result=="1"){
				$(".p_view").css("display","none");
				Info.view("添加成功！！");
			}
		}
	});
}

//下一步
function toNext(){
	window.location.href="";
}
//
function goodsType(){
	//获取选择的商品规格，如果没选择，就是默认选择第一个
	var goodsType=$(".goodsType").val();
	if(goodsType==""){
		$(".colorTypeDiv").find(".colorType:first-child").css({"background":"#D13600","color":"#FFFFFF"});
		$(".goodsType").val($(".colorTypeDiv").find(".colorType:first-child").text());
	}
	else{
		$(".colorTypeDiv").find(".colorType").each(function(){
			if($(this).text()==goodsType){
				$(this).css({"background":"#D13600","color":"#FFFFFF"});
			}
		});
	}
}
