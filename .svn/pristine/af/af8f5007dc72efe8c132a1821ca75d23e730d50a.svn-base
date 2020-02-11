var w=window.innerWidth;
window.onload=function(){
	/*$(".addressLeft").height($(".addressMiddle").height());
	$(".addressRight").height($(".addressMiddle").height());*/
	$(".addressUp").each(function(){
		$(this).find(".addressLeft").height($(this).find(".addressMiddle").height());
		$(this).find(".addressRight").height($(this).find(".addressMiddle").height());
	});
};


/*点击选取默认地址*/
//$(".GoodsCheck").click(function() {
//    if($(this).is(':checked')){
//    	var id=$(this).parents(".oneAddressDiv").find(".addressId").val(); 
//		$.ajax({
//	        type:'get',
//	        url:"/uc/user/address/setDefault?id="+id,
//		    dataType:'JSON',
//	        async: false,
//	        success: function(result) {
//	        	if(result=='1'){
//	        		$(".GoodsCheck").prop('checked', false);
//     				$(this).prop('checked', true);
//	        		Info.view('修改成功！');
//	        	}
//	        	if(result=='0'){
//	        		Info.view('修改失败');
//	        	}
//	        }
//		});
//    }
//    
//});
  
/*删除地址*/
function deleteAddress(obj){
	//var id=$(this).parents(".oneAddressDiv").find(".addressId").val();
	var id =$("#addressId").val();
	if(confirm("确定要删除吗？"))
	 {
			$.ajax({
		        type:'get',
		        url:"/uc/user/address/delete?userAddrId="+id,
			    dataType:'JSON',
		        async: false,
		        success: function(result) {
		        	if(result=='success'){
		        		Info.view('删除成功！');
		        		//$(this).parents(".oneAddressDiv").remove();
		        		window.location.href="/uc/user/address/list";
		        	}else{
		        		Info.view('删除失败');
		        	}
		        }
			});
	}
}

//编辑
function editAddress(obj){
	//var id=$(this).parents(".oneAddressDiv").find(".addressId").val();
	var id =$("#addressId").val();
	window.location.href="/uc/user/address/preEdit?userAddrId="+id;
}

//新增收货地址
function addAddressClick(){
	window.location.href="/uc/user/address/preAdd";
}


