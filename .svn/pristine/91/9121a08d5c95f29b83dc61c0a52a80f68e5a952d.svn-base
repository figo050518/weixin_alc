<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>创建店铺管理</title>
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
				<title>店铺管理</title>
				<td style="width: 20%;text-align: left;"></td>
			</tr>
		</table>
		<form:form id="inputForm" modelAttribute="SellerShop" action="/uc/shop/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		 <form:input path="fcgUserId" id="fcgUserId" class="" value="${fcgUserId}" type="hidden" />	
			<div class="content">
			<div class="contentItem">
				<div id="">店铺名称</div>
				<form:input type="text" path="shopName" id="shopName" class="shopName" value="" placeholder="请填写店铺名称" />	
			</div>
			<div class="contentItemIntro">
				<div id="">店铺简介</div>
				<form:textarea type="text" path="shopDesc" class="jianjieText" id="shopDesc" placeholder="请填写店铺简介" /></textarea>
			</div>
			<div class="contentItemImg">
				<img id="tempimg" dynsrc="" src="" style="display:none" />
				<input type="file" name="file" id="file" value="" />
				<div id="">店铺头像</div>
				<div class="imgdiv"><img src="/resources/images/icon_kuang2.png"/></div>
			</div>
			<div class="contentItemImg2">
				<img id="tempimg2" dynsrc="" src="" style="display:none" />
				<input type="file" name="file2" id="file2" value="" />
				<div id="">背景头像</div>
				<div class="imgdiv2"><img src="/resources/images/icon_kuang2.png"/></div>
			</div>
		</div>
		<button class="createShop" type="submit" id="creatBtn" value="立即创建">立即创建</button>
		</form:form>
		<script src="/resources/script/dianpuguanli.js" type="text/javascript" charset="utf-8"></script>
		<script src="/resources/script/info.js" type="text/javascript" charset="utf-8"></script>
	</body>

</html>