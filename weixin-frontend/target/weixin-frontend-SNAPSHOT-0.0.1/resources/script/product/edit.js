$(function() {
	$(".filepath").on("change",function() {
	   var srcs = getObjectURL(this.files[0]); //获取路径
	   $(this).nextAll(".img1").hide(); //this指的是input
	   $(this).nextAll(".img2").show(); //fireBUg查看第二次换图片不起做用
	   $(this).nextAll(".img2").attr("src",srcs); //this指的是input
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
			$(obj).parents(".addOneTypeDiv").addProduceDescremove();
	}else{
		  $(obj).parents(".addOneTypeDiv").remove();
	}
}

//点击设置分类
function setFenLei(){
	$("html,body").addClass("overHiden");   
	$("#fenLeiListPopup").css("display","block");
}

//点击添加图文描述
function addProduceDesc(){
	$("html,body").addClass("overHiden");   
	$("#productDesc").css("display","block");
}

function modifyProductDesc(){
	$("#proFlag").html("已添加");
	$("html,body").removeClass("overHiden");   
	$("#productDesc").css("display","none");
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
//点击取消
function closePopup2(){
	$("#addFenLeiPopup").css("display","none");
}
//点击添加分类的添加
function addFenLei(){
	$("#addFenLeiPopup").css("display","block");
}
//点击取消
function closePopup1(){
	$("#fenLeiListPopup").css("display","none");
}
function addOne(){
	var fenLeiAdd = $("#addFenLeiInput").val();
	if(fenLeiAdd==""){
		alert("请输入分类名称！");
	}else{
		$.ajax({
	        type:'post',
	        url:"/uc/productGroup/add",
	        data:{'name':fenLeiAdd},
	        async: false,
	        success: function(result) {
    	 	if(result!=null){
    	 		var t="";
    	 		var t=  '<tr>\
    	 			<td class="checkBoxTd">\
    	 			<label class="checkboxDiv">\
    	 			<input type="checkbox" class="opaction goodsCheck"/>\
    	 			</label>\
    	 			</td>\
    	 			<td class="fenLeiNameTd" id=\
    	 			'+result.id+'\>'+
    	 			'<span>'+result.groupName+'</span>\
    	 			</td>\
    	 			</tr>';
    	 		$("#groupList").append(t);
    	 		$("#addFenLeiPopup").css("display","none");
    	 	}
	        }
		}
);
	
	}
}


//验证标题 2-50任意字符，但不能全为空格
function typeTitleVel() {
	var regex = /^[\s\S]{2,50}$/;
	var regexKg = "\\s+";
	var typeTitleVal = $(".typeTitle").val();
	var typeTitleKg = typeTitleVal.replace(/\s/g,'');
	if (typeTitleKg == "") {
		return false;
	}
	if (regex.test(typeTitleVal) == false) {
		return false;
	}
	else {
		return true;
	}
}

//验证规格
function typeGuigeVel(){
	var length=$(".addList").find(".typeGuige").length;
	var lengthNotBlank=0;
	if(length>0){
		$(".addList").find(".typeGuige").each(function(){
			if($(this).val()==""){
				lengthNotBlank=lengthNotBlank+1;
			}
		});
		if(lengthNotBlank != 0){
			$(".p_view").css("display","none");
			Info.view("请填写商品规格！");
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}

//验证价格
function typePriceVel(){
	var lengthNotBlank=0;
	var regex = /(^[-+]?[1-9]\d*(\.\d{1,2})?$)|(^[-+]?[0]{1}(\.\d{1,2})?$)/; 
	if($("#goodsPrice").css("display")=="none"){
		$(".addList").find(".typePrice").each(function(){
			if($(this).val()==""){
				lengthNotBlank=lengthNotBlank+1;
			}else{
				if (regex.test($(this).val()) == false) {
					$(".p_view").css("display","none");
					Info.view("请正确填写商品价格！");
					return false;
				}
			}
		});
		if(lengthNotBlank != 0){
			$(".p_view").css("display","none");
			Info.view("请填写商品价格！");
			return false;
		}else{
			return true;
		}
	}else{
		var goodsPrice=$("#goodsPrice").find(".typePrice").val();
		if (goodsPrice == "") {
			$(".p_view").css("display","none");
			Info.view("请填写商品价格！");
			return false;
		}
		if (regex.test(goodsPrice) == false) {
			$(".p_view").css("display","none");
			Info.view("请正确填写商品价格！");
			return false;
		}
		else {
			return true;
		}
	}
}



//验证库存数量
function typeCountVel(){
	var lengthNotBlank=0;
	var regex = /^[0-9]*[1-9][0-9]*$/; 
	if($("#goodsPrice").css("display")=="none"){
		$(".addList").find(".typeCount").each(function(){
			if($(this).val()==""){
				lengthNotBlank=lengthNotBlank+1;
			}else{
				if (regex.test($(this).val()) == false) {
					$(".p_view").css("display","none");
					Info.view("请正确填写库存数量！");
					return false;
				}
			}
			
		});
		if(lengthNotBlank != 0){
			$(".p_view").css("display","none");
			Info.view("请填写库存数量！");
			return false;
		}else{
			return true;
		}
	}else{
		var typeCount=$("#goodsCount").find(".typeCount").val();
		if (typeCount == "") {
			$(".p_view").css("display","none");
			Info.view("请填写库存数量！");
			return false;
		}
		if (regex.test(typeCount) == false) {
			$(".p_view").css("display","none");
			Info.view("请正确填写库存数量！");
			return false;
		}
		else {
			return true;
		}
	}
}



//验证分类设置
function fenLeiCountVel(){
	var fenLeiCount=$("#fenLeiCount").val();
	if(fenLeiCount=="设置分类"){
		$(".p_view").css("display","none");
		Info.view("请设置分类！");
	}
}
//点击发布
function release(){
	var productId =$("#id").val();
	var productName= $("#productName").val();
	var upState =$("#upState").val();
	var specList=[];
	var nodes = $("#specList").find(".addOneTypeDiv");
	for(var i=0;i<nodes.length;i++){
		var specName= $(nodes[i]).find("input[name='specName']").val();
		var specStocks =$(nodes[i]).find("input[name='stocks']").val();
		var specPrice = $(nodes[i]).find("input[name='specPrice']").val();
		var id =$(nodes[i]).attr("id");
		specList.push({'specName':specName,'stock':specStocks,'originalPrice':specPrice,'salesPrice':specPrice,'id':id});
	}
	var freight =$("#freight").val();
	var productDesc = UE.getEditor('container').getContent();	
	var groupTrs = $("#groupList").find("tr");
	var groupId="";
	for(var i=0;i<groupTrs.length;i++){
		if($(groupTrs[i]).find("input").is(':checked')){
			groupId=$(groupTrs[i]).find(".fenLeiNameTd").attr("id");
		}
	}
	if(groupId==""){
		groupId=$("#groupId").val();
	}
	var data={'id':productId,'upState':upState,'productName':productName,'sepcList':specList,'freight':freight,'productDesc':productDesc,'group':{'id':groupId}};
	
	$.ajax({
			contentType :"application/json",
	        type:'post',
	        url:"/uc/product/updateSelfProduct",
	        data:JSON.stringify(data),
	        async: false,
	        success: function(result) {
	        	if(result=='success'){
	        		window.location.href="/uc/product/initList";
	        	}
	        }
		}
);
	
}