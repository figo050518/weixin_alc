<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
		<title>404错误</title>
		<link rel="stylesheet" href="/resources/css/404error.css">
	</head>
	<body>
		<img src="/resources/images/404errorImg2.png" class="img1"/>
		<img src="/resources/images/404errorImg1.png" class="img2"/>
		<img src="/resources/images/404-2.png" class="img3"/>
		<img src="/resources/images/404-1.png" class="img4"/>
		<div class="text">亲来晚了，页面找不到了...</div>
		<div class="btnDiv">
			<button class="returnMainPage" onclick="backIndex()">返回首页</button>
		</div>
	</body>
	<script>
		function backIndex(){
			window.location.href="/"
		}
	</script>
</html>
