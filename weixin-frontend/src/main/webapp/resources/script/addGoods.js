$(function() {
	$(".filepath").on("change",function() {
	   var srcs = getObjectURL(this.files[0]); //获取路径
	   $(this).nextAll(".img1").hide(); //this指的是input
	   $(this).nextAll(".img2").show(); //fireBUg查看第二次换图片不起做用
	   $(this).nextAll('.close').show(); //this指的是input
	   $(this).nextAll(".img2").attr("src",srcs); //this指的是input
	   $(this).val(''); //必须制空
	   $(".close").on("click",function() {
		    $(this).hide();  //this指的是span
		    $(this).nextAll(".img2").hide();
		    $(this).nextAll(".img1").show();
	   })
	})
})
function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) {
   		url = window.createObjectURL(file)
    } else if (window.URL != undefined) {
   		url = window.URL.createObjectURL(file)
    } else if (window.webkitURL != undefined) {
   		url = window.webkitURL.createObjectURL(file)
    }
    return url
};
//添加商品规格
function addOneType(){
	//获取已经添加的数量
	var addCount=$(".addList").find(".addOneTypeDiv").length;
	var oneTypeHtml=$(".addHtml").html();
	if(addCount > 200){
		alert("商品规格最多只能添加100个！")
	}else{
			$("#goodsPrice").css("display","none");
			$("#goodsCount").css("display","none");
			$("#goodsTitle").find(".lineCount").css("border-bottom","0px solid #EBECEE");
			$(".addList").prepend(oneTypeHtml);
			$(".addList").find(".addOneTypeDiv").css("display","block");
	}
}

// 删除商品规格
function deleteType(obj){
	//获取已经添加的数量
	var addCount=$(".addList").find(".addOneTypeDiv").length;
	if(addCount==1){
			$("#goodsPrice").css("display","block");
			$("#goodsCount").css("display","block");
			$("#goodsTitle").find(".lineCount").css("border-bottom","1px solid #EBECEE");
			$(obj).parents(".addOneTypeDiv").remove();
	}else{
		  $(obj).parents(".addOneTypeDiv").remove();
	}
}

//点击设置分类
function setFenLei(){
	$("html,body").addClass("overHiden");   
	$("#fenLeiListPopup").css("display","block");
}

//点击修改分类的确定
function modifyFenLei(){
	//获取修改分类的checkBox数量
	var modifyCount=$(".fenLeiList").find('input[type=checkbox]:checked').length;
	if(modifyCount==0){
		alert("请选择修改的分类!");
	}else{
		$("html,body").removeClass("overHiden");   
		$("#fenLeiListPopup").css("display","none");
		$("#fenLeiCount").val("设置了"+modifyCount+"个分类");
	}
}
//点击修改分类
function addFenLei(){
	$("#addFenLeiPopup").css("display","block");
}

//点击添加分类的添加
function addFenLei(){
	$("#addFenLeiPopup").css("display","block");
}

function addOne(){
	var fenLeiAdd = $("#addFenLeiInput").val();
	if(fenLeiAdd==""){
		alert("请输入分类名称！");
	}else{
		
	}
}
