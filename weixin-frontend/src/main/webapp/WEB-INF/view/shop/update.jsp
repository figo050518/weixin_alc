<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>编辑店铺</title>
		<link rel="stylesheet" type="text/css" href="/resources/style/dianpuguanli.css" />
		<link rel="stylesheet" type="text/css" href="/resources/style/info.css" />
		<link rel="stylesheet" type="text/css" href="/resources/style/jquery.validate.css" />
		<script src="/resources/script/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/resources/script/jquery.validate.js" type="text/javascript" charset="utf-8"></script>
		<script src="/resources/script/jquery.validate.method.js" type="text/javascript" charset="utf-8"></script>
		<script src="/resources/script/shop/update.js" type="text/javascript" charset="utf-8"></script>

	</head>

	<body>
		<table width="100%" class="titleTable"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="width: 20%;text-align: left;"><img src="/resources/images/prev1.png" style="margin-right:5px;"/>返回</td>
				<title>编辑店铺</title>
				<td style="width: 20%;text-align: left;"></td>
			</tr>
		</table>
		<form:form id="inputForm" modelAttribute="SellerShop" action="/uc/shop/update" method="post" class="form-horizontal" enctype="multipart/form-data">
		 <form:input path="id" id="id" class="" value="${sellerShop.id}" type="hidden" />
			<div class="content">
			<div class="contentItem">
				<div id="">店铺名称</div>
				<form:input type="text" path="shopName" id="shopName" class="shopName"  placeholder="请填写店铺名称" />	
			</div>
			<div class="contentItemIntro">
				<div id="">店铺简介</div>
				<form:textarea type="text" path="shopDesc" class="jianjieText" id="shopDesc"/>
			</div>
			<div class="contentItemImg">
				<input type="hidden" class="imageUrl" name="logoUrl" value="${sellerShop.logoUrl}"/>
				<input type="file" name="imageFileName" id="file" class="filepath1"/>
				<div id="">店铺头像</div>
				<div class="imgdiv">
				<img id="logoUrlTemp" src="${sellerShop.logoUrl}"/>
				</div>
			</div>
			<div class="contentItemImg2">
				<input type="hidden" class="imageUrl" name="bgUrl" value="${sellerShop.bgUrl}"/>
				<input type="file" name="imageFileName" id="file2" class="filepath2" />
				<div id="">背景头像</div>
				<div class="imgdiv2">
					<img id="bgUrlTemp" src="${sellerShop.bgUrl}"/>
				</div>
			</div>
		</div>
		<button class="createShop" type="submit" id="creatBtn" value="提交修改">提交修改</button>
		</form:form>
		<div style="display:none;"><input id="hide"></div>
		<script src="/resources/script/info.js" type="text/javascript" charset="utf-8"></script>
		<script src="/resources/script/jquery.form.js"></script>
		<script src="/resources/script/ajaxfileupload.js" type="text/javascript"></script>
		<script src="/resources/script/dianpuguanli.js" type="text/javascript" charset="utf-8"></script>
	</body>

</html>