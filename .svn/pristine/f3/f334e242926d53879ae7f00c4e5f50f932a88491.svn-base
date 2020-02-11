<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>物流详情</title>
		<link rel="stylesheet" type="text/css" href="/resources/style/wuliuDetail.css" />
	</head>

	<body>
		<header class="head">
			<div class="title">
				<span>物流详情</span>
			</div>
			<div class="return" onclick="history.go(-1)">
				<span class="returnImg"></span><span>返回</span>
			</div>
		</header>
		<div class="wuliuContent">
			<div class="ContentItem">
				<span style="font-size: 14px;color:#999">物流公司</span>
				<span class="wuliuCpy">${logisticsCom}</span>
			</div>
			<div class="ContentItem">
				<span style="font-size: 14px;color:#999">物流单号</span>
				<span class="wuliuNo">${logisticsNum}</span>
			</div>
		</div>
		<div style="width:100%;height:5px;border-top:1px solid #eee;border-bottom:1px solid #eee"></div>
		<div class="wuliuList">
			<ul class="ulInfo">
				<c:forEach items="${logiticsTrackList}" var="object">
					<li>
						<div class="conDiv">
							<span id="">${object.context}</span>
							<span id="time">${object.time}</span>
						</div>
						<div class="clear">
							
						</div>
					</li>
				</c:forEach>
			</ul>
			<div class="infoNone">
				暂无物流信息
			</div>
		</div>
	</body>

</html>