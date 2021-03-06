<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>订单列表</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="/resources/style/orderList.css">
		<link rel="stylesheet" href="/resources/style/info.css">
		<link rel="stylesheet" type="text/css" href="/resources/style/mui.min.css" />
		<script src="/resources/script/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/resources/script/mui.min.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<input value="${userType}" class="userType" type="hidden">
		<input value="${orderState}" class="orderState" type="hidden">
		<div class="head">
			<div class="headNav">
				<div class="headNavL" onclick="findOrdType(0)">所有订单</div>
				<div class="headNavR" onclick="findOrdType(1)">维权订单</div>
			</div> 
			<div class="return" onclick="history.go(-1)">
				<span class="returnImg"></span><span>返回</span>
			</div>
		</div>
		<table class="tapDiv">
			<tr>
				<td>
					<div class="tap" mag="-1" id="tap1" onclick="findByOrderState(-1)">全部</div>
				</td>
				<td>
					<div class="tap" mag="0" id="tap2" onclick="findByOrderState(0)">待付款</div>
				</td>
				<td>
					<div class="tap" mag="1" id="tap3" onclick="findByOrderState(1)">待发货</div>
				</td>
				<td>
					<div class="tap" mag="2" id="tap4" onclick="findByOrderState(2)">已发货</div>
				</td>
				<td>
					<div class="tap" mag="3" id="tap5" onclick="findByOrderState(3)">已完成</div>
				</td>
				<td>
					<div class="tap" mag="4" id="tap6" onclick="findByOrderState(4)">已关闭</div>
				</td>
			</tr>
		</table>
		<div class="blank"></div>
<c:if test="${not empty page.rows}">
	<div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="background:#fff;margin-top:calc(50px + 10vw);">
		<div class="mui-scroll">
			<!--数据列表-->
			<ul class="mui-table-view mui-table-view-chevron" style="background: #EFEFF4;border:0px;" id="goodsListInfo">
				<c:forEach items="${page.rows}" var="ordInfo">
				<li style="padding: 0px;background:#fff;width:100%;position: relative;" class="mui-table-view-cell">
					<div class="oneOrder">
						<c:forEach items="${ordInfo.orderItemsDTOs}" var="ordItemDto">
						<div class="oneGoods" id="${ordInfo.orderInfoPO.id}">
							<input type="hidden" value="${ordInfo.orderInfoPO.id}" class="goodsId" id="orderId"/>
							<div class="goodsImgDiv">
								<img src="${ordItemDto.imageUrl}" class="goodsImg"/>
							</div>
							<div class="goodsInfo">
								<div class="goodsName">${ordItemDto.orderItemPO.productName}</div>
								<div class="goodsInfoFloor">
									<div class="inventory">
										<span>数量：${ordItemDto.orderItemPO.productQuant}</span><span>件</span>
									</div>
									<div>
										<span>售价：</span><span class="price">¥${ordItemDto.orderItemPO.productPrice}</span>
									</div>
								</div>
							</div>
							<div class="clear"></div>
						</div>
						</c:forEach>
						<div class="orderInfoDiv">
							<div class="orderNum">
								<span>单号：${ordInfo.orderInfoPO.orderNum}</span>
							</div>
							<div class="orderTime">
							<!-- 格式化时间-->
								<span><fmt:formatDate value="${ordInfo.orderInfoPO.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
							</div>
							<div class="clear"></div>
						</div>
						<!-- 订单状态：0=待付款,1=待发货,2=待收货,3=已完成,4=已关闭 -->
						<div class="orderStateDiv">
							<div class="orderState">
								<c:choose>
									<c:when test="${ordInfo.orderInfoPO.orderState=='0'}">
										<span>待付款</span>
									</c:when>
									<c:when test="${ordInfo.orderInfoPO.orderState=='1'}">
										<span>待发货</span>
									</c:when>
									<c:when test="${ordInfo.orderInfoPO.orderState=='2'}">
										<span>待收货</span>
									</c:when>
									<c:when test="${ordInfo.orderInfoPO.orderState=='3'}">
										<span>已完成</span>
									</c:when>
									<c:when test="${ordInfo.orderInfoPO.orderState=='4'}">
										<span>交易关闭</span>
									</c:when>
								</c:choose>
								<!--不同身份 不同状态 显示不用按钮   1:买家 2：卖家-->	
								<!-- 待付款时 -->
								<c:if test="${ordInfo.orderInfoPO.orderState=='0'}">
									<!-- 买家 -->
									<c:if test="${userType=='1'}">
										<button class="orderBtn cancleBtn" style="margin:" onclick="cancleBtn(${ordInfo.orderInfoPO.id})">取消订单</button>	
										<button class="orderBtn paymentBtn">去付款</button>
									</c:if>
									<!-- 卖家 -->	
									<c:if test="${userType=='2' and ordInfo.orderInfoPO.orderType=='1'}">
										<button class="orderBtn cancleBtn" onclick="cancleBtn(${ordInfo.orderInfoPO.id})">取消订单</button>
										<button class="orderBtn cancleBtn" onclick="changeOrderPrice(${ordInfo.orderInfoPO.id})">修改价格</button>	
									</c:if>
								</c:if>
								<!-- 待发货时 -->
								<c:if test="${ordInfo.orderInfoPO.orderState=='1'}">
									<!-- 卖家  并且是自营订单时-->	
									<c:if test="${userType=='2' and ordInfo.orderInfoPO.orderType=='1'}">
										<button class="orderBtn fahuoBtn" onclick="fahuoBtn(${ordInfo.orderInfoPO.id})">确认发货</button>	
									</c:if>
								</c:if>
								<!-- 待收货时 -->
								<c:if test="${ordInfo.orderInfoPO.orderState=='2'}">
									<!-- 买家 -->	
									<c:if test="${userType=='1'}">
										<button class="orderBtn goodsBtn"  onclick="goodsBtn(${ordInfo.orderInfoPO.id})">确认收货</button>
									</c:if>
									<button class="orderBtn goodsBtn"  onclick="viewLogistics(${ordInfo.orderInfoPO.id})">查看物流</button>
								</c:if>
								<!-- 待收货时 -->
								<c:if test="${ordInfo.orderInfoPO.orderState=='3'}">
									<!-- 买家   并且为评价是显示立即评价按钮-->	
									<c:if test="${userType=='1' and ordInfo.orderInfoPO.isEaluate=='0'}">
										<button class="orderBtn evaluationBtn"  onclick="evaluationBtn(${ordInfo.orderInfoPO.id})">立即评价</button>	
									</c:if>
									<button class="orderBtn goodsBtn"  onclick="viewLogistics(${ordInfo.orderInfoPO.id})">查看物流</button>
								</c:if>
							</div>
							<div class="clear"></div>
						</div>
						<div style="width:100vw;height:10px;background:#F5F5F5;"></div>
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
	
		<!--商家待付款中的修改订单价格弹窗-->
	  	<div class="popup" id="submitPopup" style="display:none;"> 
	  		<div class="closeImgDiv">
				<img src="/resources/images/cancel.png" class="closePopup" onclick="closeClick()"/>
			</div>
			<div class="changePriceDiv">
				<div class="changeText">重新设置订单总价</div>
				<div style="margin-top:3vw;">
					<div class="priceIcon">¥</div>
					<input type="hidden" id="orderId">
					<input placeholder="为空则不修改" class="changeInput" type="number"/>
					<div class="clear"></div>
				</div>
			</div>
			<input class="changeBtn" onclick="changeClick()" value="确定" type="button"/>
	  	</div>
	  	
	  	<!--弹窗的遮罩层-->
	  	<div id="cover" class="overlay" style="display: none;" onclick="closeClick()"></div>
	  	
	  		<!-- 弹窗遮罩 -->
	  	<div class="zhezhao"></div>
	  	
	  	<!-- 卖家发货，填写物流单号弹窗-->
	  	<div class="trackingNo">
	  		<div class="title"><span class="returnImg" style="float:left;margin-top:8px" onclick="closetrackingNo()"></span>请填写物流信息</div>
	  		<div class="content">
	  			<form action="/uc/orderInfo/updateSendPro" method="post" id="sendLogisticsForm">
	  			<input type="hidden" name="orderId" id="logisticsorderId"> 
		  			<div class="contentdiv">
		  				<div>物流公司</div>
		  				<select name="logisticsCompany">
		  					<option value="guotongkuaidi">国通快递</option>
		  					<option value="yuantong">圆通快递</option>
		  					<option value="shentong">申通快递</option>
		  					<option value="zhongtong">中通快递</option>
		  					<option value="huitongkuaidi">汇通快递</option>
		  					<option value="youzhengguonei">中国邮政</option>
		  					<option value="yunda">韵达快递</option>
		  					<option value="shunfeng">顺丰快递</option>
		  					<option value="tiantian">天天快递</option>
		  					<option value="ems">EMS</option>
		  				</select>
		  			</div>
		  			<div class="contentdiv">
		  				<div>物流单号</div>
		  				<input type="text" class="wuliuNo" placeholder="填写物流单号" name="logisticsNumber">
		  			</div>
	  			</form>
	  			<button class="submitButton" onclick="submitButtonClick()">提交</button>
	  		</div>
	  	</div>
	</body>
	<script src="/resources/script/jquery-1.11.1.min.js"></script>
	<script src="/resources/script/info.js"></script>
	<script type="text/javascript" src="/resources/script/order/byuerOrderInfoList.js"></script>
	<script>
	</script>
</html>