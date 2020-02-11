<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>店铺管理</title>
		<link rel="stylesheet" type="text/css" href="/resources/style/dianpuguanli.css" />
		<link rel="stylesheet" type="text/css" href="/resources/style/info.css" />
		<link rel="stylesheet" type="text/css" href="/resources/style/jquery.validate.css" />
		<script src="/resources/script/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/resources/script/jquery.validate.js" type="text/javascript" charset="utf-8"></script>
		<script src="/resources/script/jquery.validate.method.js" type="text/javascript" charset="utf-8"></script>
		<script src="/resources/script/shop/openShop.js" type="text/javascript" charset="utf-8"></script>

	</head>

	<body>
		<table width="100%" class="titleTable"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="width: 20%;text-align: left;"><img src="/resources/images/prev1.png" style="margin-right:5px;"/>返回</td>
				<title>店铺详情</title>
				<td style="width: 20%;text-align: left;"></td>
			</tr>
		</table>
			<div class="content">
			<div class="contentItem">
				<div id="">店铺名称</div>
				<input type="text" path="shopName" id="shopName" class="shopName" value="${sellerShop.shopName}" placeholder="请填写店铺名称" readOnly/>	
			</div>
			<div class="contentItemIntro">
				<div id="">店铺简介</div>
				<textarea type="text" path="shopDesc" class="jianjieText" id="shopDesc" placeholder="请填写店铺简介" readOnly>${sellerShop.shopDesc}</textarea>
			</div>
			<div class="contentItemImg">
				<div id="">店铺头像</div>
				<div class="imgdiv"><img src="${sellerShop.logoUrl}"/></div>
			</div>
			<div class="contentItemImg2">
				<div id="">背景头像</div>
				<div class="imgdiv2"><img src="${sellerShop.bgUrl}"/></div>
			</div>
		</div>
		<button class="createShop" type="submit" id="creatBtn" value="立即创建">编辑</button>
		<script src="/resources/script/shop/detail.js" type="text/javascript" charset="utf-8"></script>
	</body>

</html>