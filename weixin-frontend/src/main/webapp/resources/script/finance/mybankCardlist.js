$(".oneCard").each(function(){
	var oneCardName =$.trim($(this).find(".cardName1").text());
	console.log(oneCardName);
	var imgSrc = "/resources/images/bankLogo/"+oneCardName+".png";
	$(this).find(".cardLogodiv img").attr('src',imgSrc);
});
