function minClick(obj){
	 var t = $(obj).parent().find('.text_box');
	    t.val(parseInt(t.val()) - 1);
	    if (t.val() <= 1) {
	      t.val(1);
	    }
	    $(obj).parents(".shangjiaPdtItem").find(".goodsCount").text(t.val());
	 
	 TotalPrice();  
}
	//点击加
function addClick(obj){
	 var addGoodsId=$(obj).parents(".shangjiaPdtItem").find(".productIdClass").val();
	 var goodsDescribe=$(obj).parents(".shangjiaPdtItem").find(".goodsDescribe").text();
	 var t = $(obj).parent().find('.text_box');
	    t.val(parseInt(t.val()) + 1);
	    if (t.val() <= 1) {
	      t.val(1);
	    }
	   var proDescribe=$.trim(goodsDescribe);
	  TotalPrice();
	   		
}
 function TotalPrice() {
      var oprice = 0; //店铺总价
      $(".shangjiaPdtItem").find(".checkone").each(function() { //循环店铺里面的商品
        if ($(this).is(":checked")) { //如果该商品被选中
          var num = parseInt($(this).parents(".shangjiaPdtItem").find(".text_box").val()); //得到商品的数量
          var price = parseFloat($(this).parents(".shangjiaPdtItem").find(".pdtPrice span").text()); //得到商品的单价
          var total = price * num; //计算单个商品的总价
          oprice += total; //计算该店铺的总价
          
        }
        $(".totalPrice").text(oprice.toFixed(2));//显示被选中商品的店铺总价
      });
};


$(function(){
	$.ajax({
		type:'get',
		url:'/uc/shopCart/list',
		data:"",
		async:false,
		success:function(result){
			response = result.shopcartitem;
			len=result.shopcartitem.length;
			var blankHtml="";
			var html="";
			if(length=0){
				var blankHtml="<div class='blankState'>"+
									"<div class='blankImgDiv'>"+
										"<img src='/resources/images/orderBlank.png' class='blankImg'/>"+
									"</div>"+
									"<div class='blankText'>您还没有相关订单哦！</div>"+
									"<div class='blankBtnDiv'>"+
										"<button class='blankBtn'>这是按钮</button>"+
									"</div></div>";
				$(".shangjiali").html(blankHtml);
			}else{
				for(var e in response){
					html+="<div class='shangjiaPdtItem' id="+response[e].productId+">"+
								"<input type='hidden' class='shopName' value="+response[e].shopName+">" +
								"<input type='hidden' class='sellerId' value="+response[e].sellerId+">"+
								"<input type='hidden' class='specId' value="+response[e].specId+">"+
								"<input type='hidden' class='fcgSpecId' value="+response[e].fcgSpecId+">"+
								"<div class='itemLeft'>"+
									"<label for=''><input type='checkbox'  name='' id='' class='check checkone shopId' value="+response[e].shopId+" /></label>"+
								"</div>"+
								"<div class='itemimg'>"+
									"<img src='/resources/images/0140_foodsense.jpg'/>"+
								"</div>"+
								"<div class='pdtInfo'>"+
									"<span class='pdtName'>"+response[e].productName+"</span>"+
									"<span class='pdtPrice'>￥<span style='display: inline;'>99.00</span></span>"+
									"<div class='countDiv'>"+
										"<div class='min' onclick='minClick(this)'>-</div>"+
										"<div class='count' style='height: 20px;'>"+
											"<input class='text_box' name='goodsCount' type='text' value="+response[e].productCount+" readonly='readonly'/>"+
										"</div>"+
										"<div class='add' onclick='addClick(this)'>+</div>"+
							"</div></div></div>";
				}
				$(".shangjiali").html(html);
				
				$(".shangjiaPdtItem").each(function(){
					if($(this).find(".shopId").val()==null){
						$(this).remove();
						$(this).appendTo(".shangjiali");
					}
				});
				
			 	var asc = function(a, b) {
			        return $(a).find('.shopId').val() > $(b).find('.shopId').val() ? 1 : -1;
			    };
			    var desc = function(a, b) {
			        return $(a).find('.shopId').val() > $(b).find('.shopId').val() ? -1 : 1;
			    };
			    var sortByInput = function(sortBy) {
			        var sortEle = $('.shangjiali>.shangjiaPdtItem').sort(sortBy);
			        $('.shangjiali').empty().append(sortEle);
			    };
			    sortByInput(asc);
			    
			    $(".shangjiali").find(".shangjiaPdtItem").each(function(){
			   		if($(this).find(".shopId").val()!=$(this).prev().find(".shopId").val()&&$(this).find(".shopId").val()!=null){
			   			var htm="";
			   			var shopName=$(this).find(".shopName").val();
			   			var shopId=$(this).find(".shopId").val();
			   			var sellerId=$(this).find(".sellerId").val();
			   			htm+="<div class='shangjiaName'><input type='hidden' class='sellerId' value="+sellerId+">"+
								"<div class='shangjiaNameLeft'>"+
									"<label for=''><input type='checkbox' class='check checkoneall' value="+shopId+"></label>"+
								"</div>"+
								"<div class='shangjiaNameRight'>"+
									"<span id=''>"+shopName+"</span>"+
								"</div>"+
							"</div>";
			   			$(this).before(htm);
			   		}
			   });
			   
			   $(".checkall").click(function() {
					if($(this).is(":checked")) {
						$(".checkone").prop('checked', true);
						$(".checkoneall").prop('checked', true);
					} else {
						$('.checkone').attr('checked', false);
						$('.checkoneall').attr('checked', false);
					}
					TotalPrice();
				});
			   
				$(".checkoneall").click(function() {
					var checkedShopId=$(this).val();
					if($(this).is(":checked")) {
						$(".checkone").each(function(){
							if($(this).val()==checkedShopId){
								$(this).prop('checked', true);
							}
						});
					} else {
					    $(".checkone").each(function(){
							if($(this).val()==checkedShopId){
								$(this).prop('checked', false);
							}
						});
					}
					
					var checkoneallLen=$(".checkoneall").length;
					var checkoneallChecked=$(".checkoneall[type='checkbox']:checked").length;
					if(checkoneallLen==checkoneallChecked){
						$(".checkall").prop("checked", true);
					}else{
						$(".checkall").prop("checked", false);
					}
					TotalPrice();
				});
				$(".checkone").click(function() {
					var shopId=$(this).val();
					//获取次商家下的商品个数
					var length=$(".checkone[value='"+shopId+"']").length;
					var len=0;
					$(".shopId").each(function(){
						if($(this).val()==shopId&&$(this).is(":checked")){
							len=len+1;
						}
					})
					if(length==len){
						$(".checkoneall[value='"+shopId+"']").prop("checked", true);
					}else{
						$(".checkoneall[value='"+shopId+"']").prop("checked", false);
					}
					
					var checkoneallLen=$(".checkoneall").length;
					var checkoneallChecked=$(".checkoneall[type='checkbox']:checked").length;
					if(checkoneallLen==checkoneallChecked){
						$(".checkall").prop("checked", true);
					}else{
						$(".checkall").prop("checked", false);
					}
					TotalPrice();
				})
			}
			
		}
	});
})

/*点击删除*/
function delelteClick(){
	var result=new Array(),
	str='';
	$(".shangjiali").find('.checkone[type=checkbox]:checked').each(function(){
		//提取出选中的checkbox的val用push组合起来
	   result.push($(this).parents(".shangjiaPdtItem").attr("id"));
	});
	var str=result.join(',');
	if(str==''){
		Info.view('请最少选择一项！');
	   return false;
	}else{
		$.ajax({
  	        type:'get',
  	        url:"/shopCart/delete/"+str,
  	        async: false,
  	        success: function(result) {
  	        	if(result=='success'){
  	        		Info.view("删除成功！");
  	        		location.href="/shopCart/list";
  	        	}else if(result =='0'){
  	        		Info.view("删除失败");
  	        		location.href="/shopCart/list";
  	        	}
  	        }
  		});
	}
}


function jiesuanClick(){
	var result=new Array(),
	str='';
	var jsonArray=new Array();
	$(".shangjiali").find('.checkone[type=checkbox]:checked').each(function(){
	   result.push($(this).parents(".shangjiaPdtItem").attr("id"));
	});
	var str=result.join(',');
	if(str==''){
		Info.view('请最少选择一项！');
	   return false;
	}else{
		$(".shangjiali").find('.checkone[type=checkbox]:checked').each(function(){
			var goodsCount=$(this).parents(".shangjiaPdtItem").find(".text_box").val();
			var sellerId=$(this).parents(".shangjiaPdtItem").find(".sellerId").val();
			var productId=$(this).parents(".shangjiaPdtItem").attr("id");
			var specId=$(this).parents(".shangjiaPdtItem").find(".specId").val();
			if(specId==""){
				specId=$(this).parents(".shangjiaPdtItem").find(".fcgSpecId").val();
			}
			var oneGoodsJson={
				sellerId:sellerId,
				productId: productId,
				productCount: goodsCount,
				specId:specId
			}
			jsonArray.push(oneGoodsJson);
		});
		jsonArrayString=JsonArrayToStringCfz(jsonArray);
		//console.log(jsonArrayString);
		//alert(jsonArrayString);
		window.location.href="/uc/placeOrder/confirmOrder?productInfoJson="+jsonArrayString;
	}
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