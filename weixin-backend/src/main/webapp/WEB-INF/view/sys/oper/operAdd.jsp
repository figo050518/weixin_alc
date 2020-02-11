<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<script></script>
<%@include file="/WEB-INF/view/include.jsp"%>
<%@include file="/WEB-INF/view/header.jsp"%>
<title>增加</title>
<meta name="keywords" content="">
<meta name="description" content="">
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-operInfo-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户ID：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="text" class="input-text"  placeholder=""  id="operId" name="operId" value="">
			</div>
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户姓名：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="text" class="input-text"  placeholder="" id="operName" name="operName" value="">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>密码：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="password" class="input-text" autocomplete="off" placeholder="密码" name="password" id="password">
			</div>
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="password" class="input-text" autocomplete="off" placeholder="确认密码" name="confirmPassword" id="confirmPassword">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机号：</label>
			<div class="formControls col-xs-8 col-sm-3">
				 <input type="text" class="input-text"  placeholder="" id="telNum" name="telNum" value="${operInfo.telNum}">
			</div>
			<label class="form-label col-xs-4 col-sm-3">邮箱：</label>
			<div class="formControls col-xs-8 col-sm-3">
				<input type="text" class="input-text"  placeholder="" id="operEmail" name="operEmail" value="${operInfo.operEmail}">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">性别：</label>
			<div class="formControls col-xs-8 col-sm-3 skin-minimal">
				<div class="radio-box">
					<input name="operGender" type="radio" id="sex-1" value="1" <c:if test="${operInfo.operGender =='1'}" >checked </c:if>>
					<label for="sex-1">男</label>
				</div>
				<div class="radio-box">
					<input type="radio" id="sex-0" name="operGender" value="0" <c:if test="${operInfo.operGender =='0'}" >checked </c:if>>
					<label for="sex-0">女</label>
				</div>
			</div>
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
			<div class="formControls col-xs-8 col-sm-3 skin-minimal"> 
			   <div class="radio-box">
					<input name="isDelete" type="radio" id="isDelete-1"  value="1" <c:if test="${operInfo.isDelete =='1'}" >checked </c:if>>
					<label for="isDelete-1">禁用</label>
				</div>
				<div class="radio-box">
					<input type="radio" id="isDelete-0" name="isDelete" value="0" <c:if test="${operInfo.isDelete =='0'}" >checked </c:if>>
					<label for="isDelete-0"> 启用</label>
				</div>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-12 col-xs-offset-4 col-sm-offset-6">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;保存&nbsp;&nbsp;">
				<button onClick="layerClose();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</article>
<%@include file="/WEB-INF/view/footer.jsp"%>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	//表单验证
	$("#form-operInfo-add").validate({
		rules:{
			operId:{
			    required:true,
			},
			operName:{
			    required:true,
			},
			password:{
				minlength:6,
				maxlength:16,
			    required:true,
			},
			confirmPassword:{
				minlength:6,
				maxlength:16,
			    required:true,
			    equalTo: "#password"
			},
			telNum:{
				required:true,
			},
			isDelete:{
				required:true,
			}
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			var formData = $("#form-operInfo-add").serializeArray(); 
			$.ajax({
		        type:'post',
		        url:"/oper/saveOperInfo.do",
		        data:formData,
		        async: false,
		        success: function(result) {
		    	 	var json = eval("("+ result +")");
		    	 	if(json.resultCode != "SUCCESS"){
		    	 		layer.msg(json.resultMsg,{icon:2,time:1000});
		    	 		return;
					}
		    	 	parent.$('.btn-success').click();
		    	 	var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
		        }
			});
		}
	});
	
});
function layerClose(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
