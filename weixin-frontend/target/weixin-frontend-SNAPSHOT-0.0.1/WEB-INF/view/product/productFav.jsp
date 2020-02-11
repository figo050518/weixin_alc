<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>收藏夹</title>
		<link rel="stylesheet" type="text/css" href="/resources/style/favorite.css" />
		<link rel="stylesheet" type="text/css" href="/resources/style/mui.min.css" />
		<script src="/resources/script/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/resources/script/mui.min.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" href="/resources/style/info.css">
	</head>

	<body>
		<div class="head">
			<div class="title">
				<span>收藏夹</span>
			</div> 
			<div class="return">
				<span class="returnImg"></span><span>返回</span>
			</div>
		</div>
		<div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="margin: 50px 0;">
			<div class="mui-scroll">
				<!--数据列表-->
				<ul class="mui-table-view mui-table-view-chevron" style="background:#FFFFFF;border:0px;" id="goodsList">
					
				</ul>
			</div>
		</div>
		<div class="footerFixed">
			<label class="checkboxAll">
			  	<input type="checkbox" class="opaction checkedAll"/>
			</label>
			<div class="text">全选</div>
			<div class="delete" onclick="deleteClick()">
				删除
			</div>
		</div>
		<script src="/resources/script/info.js"></script>
		<script src="/resources/script/product/productFav.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>