<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>支付成功</title>
    <link rel="stylesheet" href="/resources/style/paySuccess.css" />
</head>
<body>
	<div class="head">
		<div class="title">
			<span>支付成功</span>
		</div> 
		<div class="return" onclick="history.go(-1)">
			<span class="returnImg"></span><span>返回</span>
		</div>
		<div class="addCard"></div>
	</div>
	<div class="content">
		<div class="contentTop">
			<img src="/resources/images/jinbitu.png"/>
			<div class="text">支付成功 !<button>查看订单</button></div>
		</div>
		<div style="width: 100%;height: 2px;background:#eee"></div>
		<div class="contentBottom">
			<p><span>订单编号：</span> <span>123456789</span></p>
			<p><span>收货地址：</span> <span>江苏省南京市雨花台区宁双路28号汇智大厦 A座908</span></p>
			
		</div>
	</div>
	
</body>
</html>