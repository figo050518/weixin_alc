function submitUpdate(){
	$("#inputForm").submit();
}

function reads(fil) {
	var reader = new FileReader();
	reader.readAsDataURL(fil);
	reader.onload = function() {
		$(".imgdiv").html("<img src='" + reader.result + "'>");
	};
}
function reads2(fil) {
	var reader = new FileReader();
	reader.readAsDataURL(fil);
	reader.onload = function() {
		$(".imgdiv2").html("<img src='" + reader.result + "'>");
	};
}

function shopName() {
	var shopNameVal = $(".shopName").val();
	if(shopNameVal == "") {
		Info.view('店铺名称不能为空！');
		return false;
	} else {
		return true;
	}
}

function shopIntro() {
	var jianjieText = $(".jianjieText").val();
	if(jianjieText == "") {
		Info.view('店铺简介不能为空！');
		return false;
	} else {
		return true;
	}
}

function shopImg() {
	var fileVal = $("#file").val();
	if(fileVal == "") {
		Info.view('请上传店铺头像！');
		return false;
	} else {
		return true;
	}
}

function createBtn() {
	var flag = shopName() && shopIntro() && shopImg();
	if(flag == true) {
		alert("ok")
	} else {}
}


$(function () { 
	$(".filepath1").change(function() { //上传照片
		var thisId=$(this).attr("id");
		var f = document.getElementById(thisId).files;
		if(f[0].size<0.5*1024*1000){
			var container=$(this).parents(".contentItemImg").find("img");
			var picContainer=$(this).parent().find("input[name='logoUrl']");
			   var $f = $("<form method='post'/>");
				var real = $(this);
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
		 	        		container.attr("src",xhr.responseText);//显示Temp图片
			            	picContainer.val(xhr.responseText);
		 	        	  }
		               }
		    }).submit();
		}else{
			alert('请选取小于0.5M大小的图片！');
			return false;
		}
    });
	
	$(".filepath2").change(function() { //上传照片
		var thisId=$(this).attr("id");
		var f = document.getElementById(thisId).files;
		if(f[0].size<0.5*1024*1000){
			var container=$(this).parents(".contentItemImg2").find("img");
			var picContainer=$(this).parent().find("input[name='bgUrl']");
			   var $f = $("<form method='post'/>");
				var real = $(this);
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
		 	        		container.attr("src",xhr.responseText);//显示Temp图片
			            	picContainer.val(xhr.responseText);
		 	        	  }
		            }
		    }).submit();
		}else{
			alert('请选取小于0.5M大小的图片！');
			return false;
		}
    });
	

})