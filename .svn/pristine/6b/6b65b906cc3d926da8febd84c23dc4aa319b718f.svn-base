<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
		<title>更换密码</title>
		<meta name="format-detection" content="telephone=no" />
		<link rel="stylesheet" href="/resources/style/changePassword.css">
		<link rel="stylesheet" href="/resources/style/info.css">
	</head>
	<body>
		<form id="submitForm" method="post">
			<input type="hidden" id="telephone" value="${telephone}">
			<div class="oldPassword" ><span style="margin-left:10%;">${telephone}</span></div>
			<div class="codeDiv">
				<input type="text" name="validateCode" placeholder="请输入验证码" id="code"/>
				<input type="button" class="code" value="获取验证码" onclick="codeClick()" >
			</div>
			<div id="codeError"></div>
			<div style="width: 100%;height: 10px;background: #F5F5F5;"></div>
			
			
			<div class="oneDiv">
				<input type="password" placeholder="请输入密码" class="newPassword" id="newPassword" name="password"/>
			</div>
			<div class="oneDiv">
				<input type="password" placeholder="请再输入一次" class="newPassword" id="onceMore"/>
			</div>
			<div id="codeError"></div>
		</form>
		<div class="promptInfo">
			温馨提示:如该手机无法接受验证码短信，请点击联系
			<a href="" style="color:#288a80;">客服</a>
			也可拨打客服电话<a href="tel:400-117-5858" style="color:#288a80;">400-117-5858</a>
		</div>
		<button class="surenBtn" onclick="changePassword()">确定</button>
	</body>
	<script src="/resources/script/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="/resources/script/info.js"></script>
	<script type="text/javascript" src="/resources/script/user/changePassword.js"></script>
</html>
