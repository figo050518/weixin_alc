/*$("#proManage").click(function(){
	
})*/

function addClick(){
	$(".cover").css("display","block");
	$(".bottomPopup").css("display","block");
}

function closePopup(){
	$(".cover").css("display","none");
	$(".bottomPopup").css("display","none");
}


function addMyProduct(){
	window.location.href="/uc/product/preAddProduct?source=1";
}