<iframe name="signIn_loading_iframe" style="display:none;border:none;" id="signIn_loading_iframe" src="" width="0" height="0"></iframe>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="/resources/style/info.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/style/zhuce.css"/>
    <script src="/resources/script/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/resources/script/info.js" type="text/javascript" charset="utf-8"></script>
   	
</head>
<body>
	<form id="submitForm" method="post">
	 	<input type="hidden" name="backUrl" value="${backUrl}">
		<div class="registeruser">
			<div >手机号</div>
			<input  class="registerphone" placeholder="注册手机号"/>
		</div>
		<div class="registerCode">
			<div>验证码</div>
			<input  class="CodeNum" placeholder="验证码"/>
			<input class="getCode" onclick="codeClick()" value="获取验证码">
		</div>
		<div class="psWord">
			<div class="">设置密码</div>
			<input class="psWordNum" placeholder="请输入密码" type="password"/>
		</div>
	</form>
	<button class="registerBTN" onclick="register()">注册</button>
	<script src="/resources/script/user/register.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="/resources/script/user/sign-in-common.js"></script>
</body>
</html>