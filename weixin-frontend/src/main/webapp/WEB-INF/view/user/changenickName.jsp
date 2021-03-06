<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
		<title>更换昵称</title>
		<meta name="format-detection" content="telephone=no" />
		<script type="text/javascript" src="/resources/script/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="/resources/script/info.js"></script>
		<link rel="stylesheet" href="/resources/style/info.css">
		<link rel="stylesheet" href="/resources/style/changenickName.css">
	</head>
	<body>
		<div style="width: 100%;height: 10px;background:#F5F5F5;"></div>
		<form id="submitForm" method="post" action="/uc/buyer/manage/changeNickName">
			<div class="oldNickName"><span style="margin-left:10%;">${userName}</span></div>
		
			<div class="newNickName">
				<input type="text"  name="userName" placeholder="请输入新的昵称" id="nickName"/>
			</div>
			<div id="nickError"></div>
		</form>
		
		<button class="surenBtn" onclick="sureClick()">确定</button>
	</body>
    <script type="text/javascript" src="/resources/script/user/changenickName.js"></script>
</html>
