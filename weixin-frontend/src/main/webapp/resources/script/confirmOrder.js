$(function(){
	$(".msgContent").focus(function(){
		$(".msgContent").height("40px");
	});
	$(".msgContent").blur(function(){
		$(".msgContent").height("20px");
	});
})
