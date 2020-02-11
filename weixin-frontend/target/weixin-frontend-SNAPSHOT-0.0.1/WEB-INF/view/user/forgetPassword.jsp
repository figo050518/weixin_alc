<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>忘记密码</title>
		<link rel="stylesheet" type="text/css" href="/resources/style/forgetPsd.css" />
		<link rel="stylesheet" type="text/css" href="/resources/style/info.css"/>
		 <script src="/resources/script/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/resources/script/info.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<table class="titleTable" cellspacing="0" cellpadding="0">
			<tr>
				<td style="width: 60%;text-align: center;" class="td">重置密码</td>
				<td style="width: 20%;text-align: left;" class="td"></td>
			</tr>
		</table>
		<div class="setNewPsd">
			设置新密码
		</div>
		<div class="setNewPsdphone">
			<div>手机号</div>
			<input class="setNewPsdphoneNum" placeholder="请输入您的手机号" />
		</div>
		<div class="setNewPsdCode">
			<div>验证码</div>
			<input class="CodeNum" placeholder="请输入验证码" />
			<button class="getCode" onclick="codeClick()">获取验证码</button>
		</div>
		<div class="setPsd">
			<div class="">新密码</div>
			<input class="setNewPsdNum" placeholder="请输入新密码" type="password" />
		</div>
		<button class="setNewPsdBTN" onclick="setNewPsdBTN()">确认提交</button>
		<script src="/resources/script/forgetPsd.js" type="text/javascript" charset="utf-8"></script>
	</body>

</html>