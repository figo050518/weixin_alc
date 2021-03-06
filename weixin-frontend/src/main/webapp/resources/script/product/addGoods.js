
(function($, doc) {
	$.init();
	$.ready(function() {
		//普通示例
		var userPicker = new $.PopPicker();
		var jsonListVal=jQuery("#fcgCates").val();
		var jsonList=eval("("+jsonListVal+")");
		userPicker.setData(jsonList);
		var showUserPickerButton = doc.getElementById('pinLeiCount');
		var userResult = doc.getElementById('userResult');
		showUserPickerButton.addEventListener('tap', function(event) {
			userPicker.show(function(items) {
				console.log(items[0]);
				document.getElementById("pinLeiCount").value = (items[0].text);
				document.getElementById("pinLei").value = (items[0].id);
				if(jQuery("#pinLeiCount").val()=="其他"){
					var pinLeiHtml="<input type='text'  class='lineRightInput' name='' id='pinLeiInput' placeholder='请输入品类'/>";
					jQuery("#pinleiDiv").append(pinLeiHtml);
				}else{
					jQuery("#pinLeiInput").remove();
				}
			});
		}, false);
		
	});
})(mui, document);

var imgboxHtml=$("#img").html();
function changePhoto(obj){
	var thisId=$(obj).attr("id");
	var f = document.getElementById(thisId).files;
	var img1 = $(obj).parents(".imgbox").find(".img1"); //this指的是input
	var img2 = $(obj).parents(".imgbox").find(".img2");
	var canclePhoto = $(obj).parents(".imgbox").find(".canclePhoto");
	var imageUrl=$(obj).parents(".imgbox").find(".imageUrl");
	var nextImgbox=$(obj).parents(".imgbox").next().find(".imageUrl").val();
	//alert(f[0].size);
	if(f[0].size<0.5*1024*1000){
		    var $f = $("<form method='post'/>");
			var real = $(obj);
			var cloned = real.clone(true);
			cloned.insertAfter(real);
	        $f.append(real);
	        $f.attr({
	            method: "post",
	            action: "/uc/interfaces/image/uploadImage",
	            enctype: "multipart/form-data"
	        }).insertAfter($("#hide")).ajaxForm({
	            complete: function(xhr) {
            	    if(xhr.responseText=='invalidImage'){
 	                     alert("图片文件无效！");  
	 	        	}
	 	        	if(xhr.responseText=='invalidPreFix'){
	 	                 alert("不是正确的图片类型！");  
	 	        	}
	 	        	if(xhr.responseText=='error'){
	 	                 alert("上传错误,网络异常！");  
	 	        	}else{
	 	        		img1.css("display","none");
		            	img2.css("display","block");
		            	img2.attr("src",xhr.responseText);//显示Temp图片
		            	canclePhoto.css("display","block");
		            	imageUrl.val(xhr.responseText);
		            	var photoLen=$("#img").find(".imgbox").length;
	            	   if(photoLen<5){
	            	   		if(nextImgbox==null){
	            	   			$("#img").append(imgboxHtml);
	            	   		}
	            	   }
	 	        	}
	            }
	    }).submit();
	}else{
		alert('请选取小于0.5M大小的图片！');
		return false;
	}
}

function canclePoto(obj){
   $(obj).css("display","none");
   $(obj).parents(".imgbox").find(".img2").css("display","none");
   $(obj).parents(".imgbox").find(".img1").css("display","block");
   $(obj).parents(".imgbox").find(".imageUrl").val("");
   var imgbox=$(obj).parents(".imgbox");
   var blankLen=0;
   $("#img").find(".imgbox").each(function(){
	   if($(this).find(".imageUrl").val()==""){
		   blankLen=blankLen+1;
	   }
   });
   if(blankLen==1){
	   imgbox.remove();
	   $("#img").append(imgboxHtml);
   }
   if(blankLen==2){
	   imgbox.remove();
   }
}
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
	var productName= $("#productName").val();
	var specList=[];
	var nodes = $("#specList").find(".addOneTypeDiv");
	for(var i=0;i<nodes.length;i++){
		var specName= $(nodes[i]).find("input[name='specName']").val();
		var specStocks =$(nodes[i]).find("input[name='stocks']").val();
		var specPrice = $(nodes[i]).find("input[name='specPrice']").val();
		specList.push({'specName':specName,'stock':specStocks,'originalPrice':specPrice,'salesPrice':specPrice});
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
	var imgUrlList =[];
	var nodes = $("#img").find(".imgbox .imageUrl");
	for(var i=0;i<nodes.length;i++){
		var url= $(nodes[i]).attr("value");
		imgUrlList.push(url);
	}
	var data={'productName':productName,'sepcList':specList,'freight':freight,'productDesc':productDesc,'group':{'id':groupId},'imgUrlList':imgUrlList,'fcgCateId':$("#pinLei").val(),'fcgCateName':$("#pinLeiName").val()};
	
	$.ajax({
			contentType :"application/json",
	        type:'post',
	        url:"/uc/product/addSelfProduct",
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