$(document).ready(function() {
	$("#file").change(function() {
		var fil = this.files;
		for(var i = 0; i < fil.length; i++) {
			reads(fil[i]);
		}
	});
	$("#file2").change(function() {
		var fil = this.files;
		for(var i = 0; i < fil.length; i++) {
			reads2(fil[i]);
		}
	});
});

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