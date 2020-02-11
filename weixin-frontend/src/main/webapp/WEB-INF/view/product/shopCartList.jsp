<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>购物车</title>
		<link rel="stylesheet" type="text/css" href="/resources/style/shoppingcart.css" />
		<script src="/resources/script/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" href="/resources/style/info.css">
		<script src="/resources/script/info.js"></script>
	</head>

	<body>
		<div class="head">
			<div class="title">
				<span>购物车</span>
			</div> 
		</div>
		
		<div class="shangjiali" style="margin:50px 0">
			
		</div>

		<div class="footerFixed">
			<div class="footerFixedLeft">
				<label for=""><input type="checkbox"  class="check checkall" name="" id="" class="check" value="" /></label>

			</div>
			<div class="text">全选</div>
			<div class="delete" onclick="delelteClick()">
				删除
			</div>
			<button class="jiesuanBtn" onclick="jiesuanClick()">结算</button>
			<div class="jine">
				<div class="">合计：<span style="color:#FF9900" class="totalPrice">0.00</span>
				</div>
				<div id="">不含运费</div>
			</div>
			<div class="">

			</div>
		</div>
		<script src="/resources/script/product/shoppingcart.js" type="text/javascript" charset="utf-8"></script>
	</body>

</html>