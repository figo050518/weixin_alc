<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>我的售后</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="/resources/style/afterSales.css">
		<link rel="stylesheet" href="/resources/style/info.css">
		<link rel="stylesheet" type="text/css" href="/resources/style/mui.min.css" />
	</head>
	<body>
		<input value="${refundStatus}" class="refundStatus" type="hidden">
		<div class="head">
			<div class="headNav">
				<div class="headNavL" onclick="findOrdType(0)">所有订单</div>
				<div class="headNavR" onclick="findOrdType(1)">维权订单</div>
			</div> 
			<div class="return">
				<span class="returnImg"></span><span>返回</span>
			</div>
		</div>
		<table class="tapDiv">
			<tr>
				<td style="border-bottom:2px solid #D13600;">
					<div class="tap" mag="-1" id="tap1" onclick="findOrderRefundType('-1')">全部</div>
				</td>
				<td style="border-bottom: 1px solid #EBECEE;">
					<div class="tap" mag="0" id="tap2" onclick="findOrderRefundType('0')">进行中</div>
				</td>
				<td style="border-bottom: 1px solid #EBECEE;">
					<div class="tap" mag="1" id="tap3" onclick="findOrderRefundType('1')">已完成</div>
				</td>
				<td style="border-bottom: 1px solid #EBECEE;">
					<div class="tap" mag="2" id="tap4" onclick="findOrderRefundType('2')">已拒绝</div>
				</td>
			</tr>
		</table>
		<c:if test="${not empty page.rows}">
<div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="background:#fff;margin-top:calc(50px + 10vw);">
	<div class="mui-scroll">
		<!--数据列表-->
		<ul class="mui-table-view mui-table-view-chevron" style="background: #EFEFF4;border:0px;" id="goodsListInfo">
		<c:forEach items="${page.rows}" var="orderRefundRequestDTOs">
		<li style="padding: 0px;background:#fff;width:100%;position: relative;" class="mui-table-view-cell">
			<div class="oneOrder">
				<div class="orderInfoDiv">
					<div class="orderNum">
						<span>售后单号：${orderRefundRequestDTOs.orderRefundRequestPO.refundNumber}</span>
					</div>
					<div class="orderTime">
						<span><fmt:formatDate value="${orderRefundRequestDTOs.orderRefundRequestPO.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
					</div>
					<div class="clear"></div>
				</div>
				<div class="oneGoods" id="${orderRefundRequestDTOs.orderRefundRequestPO.id}">
					<div class="goodsImgDiv">
						<img src="${orderRefundRequestDTOs.orderRefundRequestPO.productPicUrl}" class="goodsImg"/>
					</div>
					<div class="goodsInfo">
						<div class="goodsName">${orderRefundRequestDTOs.orderRefundRequestPO.productName}</div>
						<div class="goodsInfoFloor">
							<div class="inventory">
								<span>单价：${orderRefundRequestDTOs.orderRefundRequestPO.productPrice}</span>
								<c:if test="${orderRefundRequestDTOs.orderRefundRequestPO.isTuihuo=='1'}">
									<span>退货数量：${orderRefundRequestDTOs.orderRefundRequestPO.productQuant}</span>
								</c:if>
								<span>规格：¥${orderRefundRequestDTOs.orderRefundRequestPO.productSpec}</span>
							</div>
							<div>
								<span>总价：</span><span class="price">¥${orderRefundRequestDTOs.orderRefundRequestPO.productTotalAmount}</span>
							</div>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="goodsOperation">
				<!-- 售后状态 1 申请中2已通过3已完成 4：拒绝 -->
					<c:choose>
						<c:when test="${orderRefundRequestDTOs.orderRefundRequestPO.refundStatus=='1'}">
							<!-- 不同身份显示按钮 -->
							<!-- 卖家 -->	
							<c:if test="${userType=='2'}">
							<!-- 判断是否是自营商品 -->
							  <!-- 1=自营，2=平台上架 商品来源  是否已经交给非常购审核的标识,1:已经，0:没有-->
								<c:if test="${orderRefundRequestDTOs.productType=='2' && orderRefundRequestDTOs.orderRefundRequestPO.fcgRefundFlag=='0'}">
									<c:if test="${orderRefundRequestDTOs.orderRefundRequestPO.isTuihuo=='0'}">
										<div class="goodsStatus">买家申请退款</div>
									</c:if>
									<c:if test="${orderRefundRequestDTOs.orderRefundRequestPO.isTuihuo=='1'}">
										<div class="goodsStatus">买家申请退或</div>
									</c:if>
									<button class="goodsBtn" onclick="updateTransferFcg('${orderRefundRequestDTOs.orderRefundRequestPO.id}')">移交非常购</button>
								</c:if>
								<c:if test="${orderRefundRequestDTOs.productType=='2' && orderRefundRequestDTOs.orderRefundRequestPO.fcgRefundFlag=='1'}">
									<div class="goodsStatus">已移交非常购</div>
								</c:if>
								<c:if test="${orderRefundRequestDTOs.productType=='1' && orderRefundRequestDTOs.orderRefundRequestPO.isTuihuo=='1'}">
									<div class="goodsStatus">买家申请退货</div>
									<button class="goodsBtn"  onclick="updateTongyi('${orderRefundRequestDTOs.orderRefundRequestPO.id}')">同意退货</button>
									<button class="goodsBtn"  onclick="updateJuJue('${orderRefundRequestDTOs.orderRefundRequestPO.id}')">拒绝退货</button>
								</c:if>
								<c:if test="${orderRefundRequestDTOs.productType=='1' && orderRefundRequestDTOs.orderRefundRequestPO.isTuihuo=='0'}">
									<div class="goodsStatus">买家申请退款</div>
									<button class="goodsBtn"  onclick="updateTuiKuan('${orderRefundRequestDTOs.orderRefundRequestPO.id}')">同意退款</button>
									<button class="goodsBtn"  onclick="updateJuJue('${orderRefundRequestDTOs.orderRefundRequestPO.id}')">拒绝退款</button>
								</c:if>
							</c:if>
							<!-- 买家 -->
							<c:if test="${userType=='1'}">
								<c:if test="${orderRefundRequestDTOs.orderRefundRequestPO.isTuihuo=='1' }">
									<button class="goodsBtn" onclick="updateJuJue('${orderRefundRequestDTOs.orderRefundRequestPO.id}')">取消退货</button>
									<div class="goodsStatus">等待卖家同意退货</div>
								</c:if>
								<c:if test="${orderRefundRequestDTOs.orderRefundRequestPO.isTuihuo=='0' }">
									<button class="goodsBtn" onclick="updateJuJue('${orderRefundRequestDTOs.orderRefundRequestPO.id}')">取消退款</button>
									<div class="goodsStatus">等待卖家同意退款</div>
								</c:if>
							</c:if>
						</c:when>
						<c:when test="${orderRefundRequestDTOs.orderRefundRequestPO.refundStatus=='2'&& orderRefundRequestDTOs.orderRefundRequestPO.isTuihuo=='1'}">
							<c:if test="${userType=='1' }">
								<c:if test="${not empty orderRefundRequestDTOs.orderRefundRequestPO.logisticsNumber}">
									<div class="goodsStatus">等待卖家确认收货</div>
								</c:if>
								<c:if test="${empty orderRefundRequestDTOs.orderRefundRequestPO.logisticsNumber}">
									<div class="goodsStatus">卖家同意退货</div>
								</c:if>
							</c:if>
							<c:if test="${userType=='2' }">
								<c:if test="${not empty orderRefundRequestDTOs.orderRefundRequestPO.logisticsNumber}">
									<div class="goodsStatus">买家已退回货物</div>
								</c:if>
								<c:if test="${empty orderRefundRequestDTOs.orderRefundRequestPO.logisticsNumber}">
									<div class="goodsStatus">等待买家退回物品</div>
								</c:if>
							</c:if>
						</c:when>
						<c:when test="${orderRefundRequestDTOs.orderRefundRequestPO.refundStatus=='3'}">
							<c:if test="${orderRefundRequestDTOs.orderRefundRequestPO.isTuihuo=='0' }">
								<div class="goodsStatus">退款成功</div>
							</c:if>
							<c:if test="${orderRefundRequestDTOs.orderRefundRequestPO.isTuihuo=='1' }">
								<div class="goodsStatus">退货成功</div>
							</c:if>
						</c:when>
						<c:when test="${orderRefundRequestDTOs.orderRefundRequestPO.refundStatus=='4'}">
							<div class="goodsStatus">售后已拒绝</div>
						</c:when>
					</c:choose>
					
					<div class="clear"></div>
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
	
	<!-- 提示操作的弹窗 -->
		<div class="promptPopup" id="promptPopup" style="display:none;">
			<input class="caozuoType" type="hidden"/>
			<input class="tanchuangInfo" type="hidden"/>
			<input class="popupOrderId" type="hidden"/>
			<div class="promptText" style="position:relative;">
				<span style="margin-left:20px;">温馨提示</span>
				<div class="closeDiv" onclick="popupCencle()">
					<img src="/resources/images/cancel.png" alt="关闭图标" class="closeImg"/>
				</div>
			</div> 
			<div class="popupMsgDiv">
				<span class="popupMsg">确定要取消该订单吗？</span><br/>
			</div>
			<div style="width:100%;text-align:center;">
				<button class=popupSure onclick="popupSure()" id="popupSureBtn">确定</button>
				<button class="popupCencle" onclick="popupCencle()" id="popupCencleBtn" style="margin-left:20px;">取消</button>
			</div>
	  	</div>
	  	<div id="popupCover" class="popupOverlay" style="display:none;"></div>
	  	
  	<!-- 淡入淡出的提示框 -->
  		<div class="promptPopup" id="fadePopup" style="display:none;">
			<div class="promptText" style="position:relative;">
				<span style="margin-left:20px;">温馨提示</span>
				<div class="closeDiv" onclick="popupCencle()">
					<img src="/resources/images/closeImg.png" alt="关闭图标" class="closeImg"/>
				</div>
			</div> 
			<div class="popupMsgDiv">
				<span class="fadeMsg">确定要取消该订单吗？</span><br/>
			</div>
			<div style="width:100%;text-align:center;">
				<button class=popupSure onclick="fadeSure()" id="fadeBtn">确定</button>
			</div>
	  	</div>
	</body>
	<script src="/resources/script/jquery-1.11.1.min.js"></script>
	<script src="/resources/script/info.js"></script>
	<script src="/resources/script/mui.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/resources/script/order/aterSales.js"></script>
</html>