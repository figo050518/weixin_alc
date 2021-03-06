<iframe name="signIn_loading_iframe" style="display:none;border:none;" id="signIn_loading_iframe" src="" width="0" height="0"></iframe>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="/resources/style/denglu.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/style/info.css"/>
    <script src="/resources/script/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/resources/script/info.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div class="head">
		<div class="title">
			<span>登录</span>
		</div> 
		<div class="return" onclick="history.go(-1)">
			<span class="returnImg"></span><span>返回</span>
		</div>
	</div>
	<form id="submitForm" method="post">
		<input type="hidden" name="backUrl" value="${backUrl}">
		<div class="user">
			<div ><img src="/resources/images/icon-phone.png"></div>
			<input  class="userName" placeholder="您的手机号" name="telephone"/>
			<div class="clear"></div>
		</div>
		<div class="psWord">
			<div><img src="/resources/images/icon-password.png"></div>
			<input class="psWordNum" placeholder="请输入密码" name="password" type="password"/>
		</div>
	</form>
	<button class="signBTN" onclick="signBtn()">登录</button>
	<div class="otherBtn">
		<a href="/user/forgetPassword" class="forgetPsd">忘记密码？</a>
		<a href="/user/register" class="registerBtn">立即注册</a>
	</div>
	<script src="/resources/script/user/denglu.js" type="text/javascript" charset="utf-8"></script>
	<script src="/resources/script/user/sign-in-common.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>