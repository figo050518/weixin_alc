<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>用户详细</title>
		<link rel="stylesheet" type="text/css" href="/resources/style/yonghuziliao.css"/>
	</head>
	<body style="background: #F5F5F5;padding: 0;margin: 0;">
		<div class="head">
			<div class="nickNameDiv">
				<span class="nickName">用户详细</span>
			</div> 
			<div class="return" onclick="history.go(-1)">
				<span class="returnImg"></span><span>返回</span>
			</div>
		</div>
		<div class="selfInfo" style="margin-top:50px;">
			<div class="selfInfo-top">
				<img src="/resources/images/default-Avatar.png"/>
				<div class="selfInfo-topInfo">
					<input type="hidden" id="userId" value="${sellerCustomerDTO.userInfoPO.id }">
					<c:if test="${not empty sellerCustomerDTO.userInfoPO.nikeName }">
						<span>${sellerCustomerDTO.userInfoPO.nikeName }</span>
					</c:if>
					<c:if test="${empty sellerCustomerDTO.userInfoPO.nikeName }">
						<span>${sellerCustomerDTO.userInfoPO.telNum }</span>
					</c:if>
				    <span id="">手机：<span>${sellerCustomerDTO.userInfoPO.telNum }</span></span>
				</div>
			</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="td1">
						<div class="totalMoney">¥${sellerCustomerDTO.sumMoey}</div>
						<div class="total">累计消费</div>
					</td>
					<td class="td2"><div id=""></div></td>
					<td class="td3">
						<div class="totalNum">${count }</div>
						<div class="total">累计订单</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="content">
			<div class="content_item" onclick="toOrder(1)">
				<img src="/resources/images/daifahuo.png"/>
				<div class="item_tit">等待发货订单</div>
				<div class="item_waitNum num">${waitShipCount }</div>
				<img class="nextIcon" src="/resources/images/next.png"/>
			</div>
			<div class="content_item" onclick="toOrder(2)">
				<img src="/resources/images/yifahuo.png"/>
				<div class="item_tit">已发货订单</div>
				<div class="item_waitNum num">${waitConfirmCount }</div>
				<img class="nextIcon" src="/resources/images/next.png"/>
			</div>
			<div class="content_item" onclick="toOrder(3)">
				<img src="/resources/images/yiwancheng.png"/>
				<div class="item_tit">已完成订单</div>
				<div class="item_waitNum num">${finishedCount }</div>
				<img class="nextIcon" src="/resources/images/next.png"/>
			</div>
			<div class="content_item" onclick="toOrder(4)">
				<img src="/resources/images/yiguanbi.png"/>
				<div class="item_tit">已关闭订单</div>
				<div class="item_waitNum num">${canceledCount }</div>
				<img class="nextIcon" src="/resources/images/next.png"/>
			</div>
		</div>
		<div class="footer">
			
		</div>
	</body>
	<script src="/resources/script/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
	   function toOrder(state){
		   var buyerId=$("#userId").val();
			window.location.href="/uc/orderInfo/getOrderList?orderState="+state+"&buyerId="+buyerId;
	   }
	
	</script>
</html>
