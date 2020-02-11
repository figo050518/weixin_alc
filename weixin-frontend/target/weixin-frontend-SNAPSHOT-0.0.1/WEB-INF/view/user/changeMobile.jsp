<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
		<title>换绑手机</title>
		<meta name="format-detection" content="telephone=no" />
		<script type="text/javascript" src="/resources/script/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="/resources/script/info.js"></script>
		<link rel="stylesheet" href="/resources/style/info.css">
		<link rel="stylesheet" href="/resources/style/changeMobile.css">
	</head>
	<body>
		<form id="submitForm" method="post">
			<div class="oldPassword" ><span style="margin-left:10%;">15996863128</span></div>
			<div class="codeDiv">
				<input type="text" name="validateCodeBefor" placeholder="输入验证码" id="code"/>
				<input type="button" class="code" value="获取验证码" onclick="codeClickold1()" id="codeBtn1">
			</div>
			<div id="codeError"></div>
			<div style="width: 100%;height: 10px;background: #F5F5F5;"></div>
			
			
			<div class="oneDiv">
				<input type="text" placeholder="请输入新手机号" class="newPassword" id="telePhone" name="telePhone" onblur="validateTeleEist()"/>
			</div>
			<div class="codeDiv">
				<input type="text" name="validateCodeNow"  placeholder="输入验证码" id="code2"/>
				<input type="button" class="code" value="获取验证码" onclick="codeClick2()" id="codeBtn2">
			</div>
			<div id="codeError"></div>
		</form>
		<div class="promptInfo">
			温馨提示:如该手机无法接受验证码短信，请点击联系
			<a href="https://www.sobot.com/chat/oldh5/index.html?sysNum=919a6f5e5d734df388fe89191380fa20" style="color:#288a80;">客服</a>
			也可拨打客服电话<a href="tel:400-117-5858" style="color:#288a80;">400-117-5858</a>
		</div>
		<button class="surenBtn" onclick="sureClick()">确定</button>
	</body>
	<script type="text/javascript" src="/resources/script/user/changeMobile.js"></script>
	
</html>
