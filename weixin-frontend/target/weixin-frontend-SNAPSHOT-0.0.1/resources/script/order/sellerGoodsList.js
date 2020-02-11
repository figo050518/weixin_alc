//点击批量管理
function managementGoods(){
	$(".checkGoods").css("display","block");
	$(".goodsImgDiv").css("margin-left","0");
	$(".goodsInfo").css("width","60vw");
	$(".floorBtnDiv").css("display","none");
	$(".flooroPeration").css("display","table");
	
	$(".operationDiv").removeAttr("onclick");
	$(".headIcon").css("display","none");
	$(".complete").css("display","block");
	$(".headNavCover").css("display","block");
}
//点击完成
function complete(){
	$(".checkGoods").css("display","none");
	$(".goodsImgDiv").css("margin-left","3vw");
	$(".goodsInfo").css("width","72vw");
	$(".floorBtnDiv").css("display","block");
	$(".flooroPeration").css("display","none");
}
function operationList(obj){
	var operationList=$(obj).parents(".oneGoods").find(".operationList");
	if(operationList.css("display")=="none"){
		operationList.css("display","block");
	}else{
		operationList.css("display","none");
	}
}

 // 点击商品按钮
  $(".goodsCheck").click(function() {
      var goods = $("body").find(".goodsCheck"); //获取本店铺的所有商品
  	  var goodsC = $("body").find(".goodsCheck:checked"); //获取本店铺所有被选中的商品
    if (goods.length == goodsC.length) { //如果选中的商品等于所有商品     q全选按钮被选中
      $(".goodsCheckAll").prop('checked', true); //全选按钮被选中
    } else { //如果选中的商品不等于所有商品
      $(".goodsCheckAll").prop('checked', false); //全选按钮不被选中
    }
  });

  // 点击全选按钮
  $(".goodsCheckAll").click(function() {
  	 if ($(this).is(":checked")) {
	      $(".goodsList").find(".goodsCheck").prop('checked', true);
     }else{
     	$(".goodsList").find(".goodsCheck").prop('checked', false);
     }
  });