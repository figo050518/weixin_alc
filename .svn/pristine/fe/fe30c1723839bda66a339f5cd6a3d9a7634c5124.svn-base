<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>所有客户</title>
    <link rel="stylesheet" type="text/css" href="/resources/style/mui.min.css" />
	<link rel="stylesheet" type="text/css" href="/resources/style/yonghuxixin.css"/>
</head>
<body >
	<div class="head">
			<div class="nickNameDiv">
				<span class="nickName">所有客户</span>
			</div> 
			<div class="return" onclick="history.go(-1)">
				<span class="returnImg"></span><span>返回</span>
			</div>
		</div>
	<div class="search">
		<div class="search_Icon"style=";"></div>
		<input type="" name="" id="inputSearch" placeholder="通过昵称、手机号、收货人姓名搜索" />
	</div>
	
<c:if test="${not empty page.rows}">
	<div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="background:#fff;margin-top:calc(50px + 12vw);">
		<div class="mui-scroll">
			<!--数据列表-->
			<ul class="mui-table-view mui-table-view-chevron" style="background: #EFEFF4;border:0px;" id="goodsListInfo">
				<c:forEach items="${page.rows}" var="ordInfo">
				<li style="padding: 3vw 0;background:#fff;width:100%;position: relative;" class="mui-table-view-cell">
					<div class="content-item" id="${ordInfo.userInfoPO.id}">
						<img src="/resources/images/default-Avatar.png"/>
						<div class="info">
							<span id="telName">${ordInfo.userInfoPO.telNum }</span>
							<input type="hidden" class="userId" value="${ordInfo.userInfoPO.id}">
							<span id="sumMoey">累计消费：¥<span>${ordInfo.sumMoey}</span></span>
						</div>
					</div>
				</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</c:if>
	<!--订单为空时-->
	<c:if test="${empty page.rows}">
		 <div class="blankState">
			<div class="blankImgDiv">
				<img src="/resources/images/orderBlank.png" class="blankImg"/>
			</div>
			<div class="blankText">您还没有相关订单哦！</div>
			<div class="blankBtnDiv">
				<button class="blankBtn">这是按钮</button>
			</div>
		</div> 
	</c:if>
	<script src="/resources/script/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/resources/script/mui.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/resources/script/info.js"></script>
	<script src="/resources/script/user/sellerCustomerList.js"></script>
</body>
</html>