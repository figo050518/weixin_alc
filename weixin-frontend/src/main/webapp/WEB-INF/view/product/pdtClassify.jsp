<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>商品分类</title>
    <link rel="stylesheet" type="text/css" href="/resources/style/mui.min.css" />
    <link rel="stylesheet" type="text/css" href="/resources/style/pdtClassify.css"/>
</head>
<body>
	<div class="head">
		<div class="title">
			<span class="pdt" onClick="viewProduct()">小店商品</span>
			<span class="pdtClassify">商品分类</span>
		</div> 
		<div class="return" onclick="history.go(-1)">
			<span class="returnImg"></span><span>返回</span>
		</div>
	</div>
	<div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="margin: 50px 0;">
		<div class="mui-scroll">
			<!--数据列表-->
			<ul class="mui-table-view mui-table-view-chevron" style="background:#FFFFFF;border:0px;" id="content">
				
			</ul>
		</div>
	</div>
<!-- 	<div class="footerbtn" >
		<button class="addClassify">添加分类</button>
		<button class="batchClassify">批量管理</button>
	</div> -->
	<script src="/resources/script/jquery-1.11.1.min.js"></script>
	<script src="/resources/script/info.js"></script>
	<script src="/resources/script/mui.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/resources/script/product/groupList.js"></script>
</body>
</html>