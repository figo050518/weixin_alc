<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title></title>
		<link rel="stylesheet" type="text/css" href="/resources/style/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="/resources/style/shopindex.css" />
	</head>

	<body>
		<div class="header">
			<div class="headerbanner">

			</div>
			<div class="shopLogo">
				<img src="${shop.logoUrlId}"  />
			</div>
			<div class="shopname">${shop.shopName}</div>
			<div class="shopInfo">${shop.shopDesc}</div>
		</div>
		<div style="width:100%;height:7px;background:#f5f5f5;border-bottom:1px solid #eee"></div>
		<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
			<div class="mui-scroll">
				<div class="classify">
					<div class="classifyone">
						<c:forEach items="${fcgCates}" var="cate">
						<div class="itemClass" id="${cate.id}" onclick="viewByGroup(${cate.id})" >
							<div id="imgDiv" >
								<img src="/resources/images/Adithi_Dinner_blog.jpg" />
							</div>
							<p >${cate.text}</p>
						</div>
						</c:forEach>
					</div>
				</div>
				<div style="width:100%;height:7px;background:#f5f5f5;border-bottom:1px solid #eee;border-top:1px solid #eee"></div>
				<div class="recommended">
					<!-- <div class="recommendedTitle"><div><img src="/resources/images/dianzan.png"></div><div class="Titletext">本店推荐</div></div>
 -->
					<div class="recommendedCon">
						<ul class="mui-table-view mui-table-view-chevron" style="background: #EFEFF4;border:0px;" id="goodsInfoType1">
						</ul>
					</div>
				</div>
			</div>
		</div>
		
		<div class="footerNav">
			<div class="footerNavOne">
				<div><img src="/resources/images/icon-redshouye.png"></div><div style="color:#EB1212">首页</div>
			</div>
			<div class="footerNavOne"><div><img src="/resources/images/icon-blackgouwuche.png"></div><div>购物车</div></div>
			<div class="footerNavOne"><div><img src="/resources/images/icon-blackwode.png"></div><div>我的</div></div>
		</div>
		<script src="/resources/script/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/resources/script/mui.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="/resources/script/shop/shopshouye.js"></script>
	</body>

</html>