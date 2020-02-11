<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>市场选货</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<link rel="stylesheet" href="/resources/style/mui.min.css">
		<link rel="stylesheet" href="/resources/style/theyPick.css">
		<style>
			.mui-bar~.mui-content .mui-fullscreen {
				top: 44px;
				height: auto;
			}
			.mui-pull-top-tips {
				position: absolute;
				top: -20px;
				left: 50%;
				margin-left: -25px;
				width: 40px;
				height: 40px;
				border-radius: 100%;
				z-index: 1;
			}
			.mui-bar~.mui-pull-top-tips {
				top: 24px;
			}
			.mui-pull-top-wrapper {
				width: 42px;
				height: 42px;
				display: block;
				text-align: center;
				background-color: #efeff4;
				border: 1px solid #ddd;
				border-radius: 25px;
				background-clip: padding-box;
				box-shadow: 0 4px 10px #bbb;
				overflow: hidden;
			}
			.mui-pull-top-tips.mui-transitioning {
				-webkit-transition-duration: 200ms;
				transition-duration: 200ms;
			}
			.mui-pull-top-tips .mui-pull-loading {
				margin: 0;
			}
			.mui-pull-top-wrapper .mui-icon,
			.mui-pull-top-wrapper .mui-spinner {
				margin-top: 7px;
			}
			.mui-pull-bottom-tips {
				text-align: center;
				background-color: #efeff4;
				font-size: 15px;
				line-height: 40px;
				color: #777;
			}
			.mui-pull-top-canvas {
				overflow: hidden;
				background-color: #fafafa;
				border-radius: 40px;
				box-shadow: 0 4px 10px #bbb;
				width: 40px;
				height: 40px;
				margin: 0 auto;
			}
			.mui-pull-top-canvas canvas {
				width: 40px;
			}
			.mui-slider-indicator.mui-segmented-control {
				background-color: #efeff4;
			}
		</style>
	</head>

	<body>
	<input id="fcgCatId" type="hidden" value=""/>
	<input id="fcgPro" type="hidden" value=""/>
		<div class="head">
			<input type="search" placeholder="请输入宝贝关键字" id="productName" class="searchInputType">
			<div class="return" onclick="history.go(-1)">
				<span class="returnImg"></span>
			</div>
		</div>
		<div class="mui-content">
			<div id="slider" class="mui-slider mui-fullscreen" style="margin-top:50px;">
				<div id="sliderSegmentedControl" class="mui-scroll-wrapper mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
					<div class="mui-scroll" id="pinleiList">
						<c:forEach items="${fcgCates}" var="fcgCate"  varStatus="status">
							<a class="mui-control-item " upindex="2" msgtype="${status.index}" href="#item${status.index+1}mobile" id="${fcgCate.id}">
								${fcgCate.name}
							</a>
						</c:forEach>
					</div>
				</div>
				<div class="mui-slider-group">
					<c:forEach items="${fcgCates}" var="fcgCate"  varStatus="status">
							<div id="item${status.index+1}mobile" class="mui-slider-item mui-control-content">
								<div id="scroll1" class="mui-scroll-wrapper">
									<div class="mui-scroll">
										<ul class="mui-table-view" style="background: #efeff4;" id="resultList${status.index}">
											
										</ul>
									</div>
								</div>
							</div>
					 </c:forEach>
				</div>
			</div>
		</div>
		<!-- 点击管理的弹窗 -->
		<div class="popup" id="submitPopup" style="display:none;"> 
	  		<div class="closeImgDiv" onclick="closeClick()">
				<img src="/resources/images/cancel.png" class="closePopup" />
			</div>
			<div class="changePriceDiv">
				<div class="changeText">设置涨幅</div>
				<div style="margin-top:3vw;">
					<input type="hidden" id="orderId">
					<input placeholder="设置涨幅" class="changeInput" type="number" id="increase"/>
					<div class="priceIcon">%</div>
					<div class="clear"></div>
				</div>
			</div>
			<input class="changeBtn" onclick="changeClick()" value="上架" type="button"/>
	  	</div>
	  	<!--弹窗的遮罩层-->
	  	<div id="cover" class="overlay" style="display: none;" onclick="closeClick()"></div>
	  	<script src="/resources/script/jquery-1.11.1.min.js"></script>
		<script src="/resources/script/mui.min.js"></script>
		<script src="/resources/script/mui.pullToRefresh.js"></script>
		<script src="/resources/script/mui.pullToRefresh.material.js"></script>
		<script src="/resources/script/product/theyPick.js"></script>
		<script>
		</script>
	</body>

</html>