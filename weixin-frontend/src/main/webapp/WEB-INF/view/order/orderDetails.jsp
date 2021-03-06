<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>订单详情</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="/resources/style/orderDetails.css">
		<link rel="stylesheet" href="/resources/style/info.css">
	</head>
	<body>
		<div class="head">
			<div class="title">
				<span>订单详情</span>
			</div> 
			<div class="return" onclick="history.go(-1)">
				<span class="returnImg"></span><span>返回</span>
			</div>
		</div>
		<div class="blank" style="width: 100%;height: 50px;"></div>
		<table cellpadding="0" cellspacing="0" class="orderStatus">
			<tr>
				<td style="width: 19vw;text-align: center;font-size: 0;">
					<img src="/resources/images/orderStatus.png" class="orderStatusImg"/>
				</td>
				<td class="orderStatusTd">
					<span>订单状态：</span>
					<!-- 订单状态：0=待付款,1=待发货,2=待收货,3=已完成,4=已关闭 -->
					<c:choose>
						<c:when test="${orderInfoPO.orderState=='0'}">
							<span>待付款</span>
						</c:when>
						<c:when test="${orderInfoPO.orderState=='1'}">
							<span>待发货</span>
						</c:when>
						<c:when test="${orderInfoPO.orderState=='2'}">
							<span>待收货</span>
						</c:when>
						<c:when test="${orderInfoPO.orderState=='3'}">
							<span>已完成</span>
						</c:when>
						<c:when test="${orderInfoPO.orderState=='4'}">
							<span>交易关闭</span>
						</c:when>
					</c:choose>
					
					<br/>
					<span>订单编号：</span><span>${orderInfoPO.orderNum}</span>
				</td>
			</tr>
		</table>
		<table cellpadding="0" cellspacing="0" id="addressDiv">
			<tr>
				<td style="width:calc(6vw + 16px);text-align: center;" rowspan="2">
					<img src="/resources/images/icon-dizhi.png" style="width:16px"/>
				</td>
				<td style="text-align: left;">
					收货人：<span>${orderReceiverAddressPO.receiverName }</span>
				</td>
				<td style="text-align: right;">
					<span>${orderReceiverAddressPO.contactNum }</span>
				</td>
				<td style="width:3%;text-align: center;" rowspan="2">
					<!--<img src="/resources/images/toNext.png" style="width: 10px;"/>-->
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div style="margin-top:5px;">
						<span>收货地址：</span>
						<span>
							${orderReceiverAddressPO.province}${orderReceiverAddressPO.city}
							${orderReceiverAddressPO.area}${orderReceiverAddressPO.detailAddr}
						</span>
						<%-- <span>卖家昵称：}</span> --%>
					</div>
				</td>
			</tr>
		</table>
		<img src="/resources/images/addressLine.png" style="display: block;width: 100%;"/>
		
		
		<div style="width:100%;height:10px;background:#ffffff;"></div>
			<c:if test="${isTuiHuo>0 and userType=='2'}">
				<table cellpadding="0" cellspacing="0" id="addressDiv">
					<tr>
						<td style="width:calc(6vw + 16px);text-align: center;" rowspan="2">
							<img src="/resources/images/icon-dizhi.png" style="width:16px"/>
						</td>
						<td style="text-align: left;">
							退货人：<span>${orderRefundAddressPO.receiverName }</span>
						</td>
						<td style="text-align: right;">
							<span>${orderRefundAddressPO.contactNum }</span>
						</td>
						<td style="width:3%;text-align: center;" rowspan="2">
							<!--<img src="/resources/images/toNext.png" style="width: 10px;"/>-->
						</td>
					</tr>
				
						<tr>
							<td colspan="2">
								<div style="margin-top:5px;">
									<span>退货地址：</span>
									<span>
										${orderRefundAddressPO.province}${orderRefundAddressPO.city}
										${orderRefundAddressPO.area}${orderRefundAddressPO.detailAddr}
									</span>
									<%-- <span>卖家昵称：}</span> --%>
								</div>
							</td>
						</tr>
				</table>
		</c:if>
		<img src="/resources/images/addressLine.png" style="display: block;width: 100%;"/>
		<div class="sellerInfo">
			<div class="goodsSize" style="border-bottom: 1px solid #EBECEE;">
				<span class="nickImg"></span>
				<c:if test="${userType=='2'}">
					<span>买家的昵称：${buyerInfo.nikeName}</span>
				</c:if>
				<c:if test="${userType=='1'}">
					<span>卖家的昵称：${sellerInfo.nikeName }</span>
				</c:if>
			</div>
			<c:if test="${orderInfoPO.orderState=='2'||orderInfoPO.orderState=='3'}">
				<div class="goodsSize" style="border-bottom: 1px solid #EBECEE;">
					<div>
						<span class="nickImg"></span>
						<span>运货单号：${ orderInfoPO.logisticsNum}</span>
					</div>
				</div>
				<div class="goodsSize" style="">
					<div style="float:left;">
						<span class="nickImg"></span>
						<span>物流公司：${ orderInfoPO.logisticsComp}</span>
					</div>
					<div class="clear"></div>
				</div>
			</c:if>
		</div> 
		<div class="oneOrder">
			<c:forEach items="${orderItemsDTOs}" var="orderItemsDTO">
			 <div class="oneGoods">
				<div class="goodsDiv" >
					 <div class="goodsImgDiv">
						<img src="${orderItemsDTO.imageUrl}" class="goodsImg"/>
					</div>
					<div class="goodsInfo">
						<div class="goodsInfoMiddle">
							<div class="goodsName">
								${orderItemsDTO.orderItemPO.productName }
							</div>
							<div class="goodsDescribeDiv">
								<div class="goodsDescribe">
									${orderItemsDTO.orderItemPO.productSpec }
								</div>
							</div>
						</div>
						<div class="goodsInfoRight">
							<div style="text-align: right;font-size:12px;">¥<span class="goodsPrice">${orderItemsDTO.orderItemPO.productPrice }</span></div>
							<div class="goodsCount">X${orderItemsDTO.orderItemPO.productQuant }</div>
						</div>
						<div class="clear"></div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="goodsOperation">
					<!-- 买家 -->
					<c:if test="${userType=='1' }">
						<c:if test="${orderInfoPO.orderState=='1' ||orderInfoPO.orderState=='2'||orderInfoPO.orderState=='3' }">
							<!-- 3:已申请  4：为申请 -->
							<c:if test="${orderItemsDTO.isRefund=='4'}">
								<button class="goodsBtn"   onclick="inserOrdRefund('${orderInfoPO.id}','${orderItemsDTO.orderItemPO.id }')">申请售后</button>
							</c:if>
							<c:if test="${orderItemsDTO.isRefund=='3'}">
								<button class="goodsBtn" onclick="findOrdRefund('${orderItemsDTO.ordRefundId}')">查看售后</button>
							</c:if>
						</c:if>
					</c:if>
					<!-- 卖家 -->
					<c:if test="${userType=='2' }">
						<c:if test="${orderInfoPO.orderState=='1' ||orderInfoPO.orderState=='2'||orderInfoPO.orderState=='3' }">
							<!-- 3:已申请  4：未申请 -->
							<c:if test="${orderItemsDTO.isRefund=='3'}">
								<button class="goodsBtn" onclick="findOrdRefund('${orderItemsDTO.ordRefundId}')">查看售后</button>
							</c:if>
						</c:if>
					</c:if>
					<div class="clear"></div>
				</div>
			</div>
			</c:forEach>
		</div>
		<div class="distributionInfoDIv" style="background: #FFFFFF;border-bottom: 1px solid #EBECEE;">
			<div class="orderMoneyDiv">
				<div class="freightDiv">
					<div class="freightText">运费</div>
					<div class="freight">¥${orderInfoPO.freightAmount}</div>
					<div class="clear"></div>
				</div>
			</div>
			<div class="buyerMessageDiv">
				<c:if test="${userType=='1'}">
					<div class="msgText">买家留言：</div>
					<textarea placeholder="点击给商家留言" class="msgContent" autocomplete="off">${orderInfoPO.sellerRemarks}</textarea>
					<div class="clear"></div>
				</c:if>
				<c:if test="${userType=='2'}">
					<div class="buyerMessage">
						买家留言：${orderInfoPO.sellerRemarks}
					</div>
				</c:if>
			</div>
			<div class="totleMoneyDiv">
				<span>需付款：</span><span class="totleMoney">¥${orderInfoPO.proAmount}</span>
			</div>
		</div>
		<div class="totleCalculateDiv">
			<div class="totleCalculate">
				<span>¥${orderInfoPO.proAmount}</span>
				<span>+</span>
				<span>¥${orderInfoPO.freightAmount}运费</span>
			</div>
			<div class="totleResult">
				<span>实际支付：</span><span>¥${orderInfoPO.actPayAmount}</span>
			</div>
		</div>
		<div class="blank" style="width: 100%;height: calc(15vw + 1px);"></div>
		<div class="floor">
			<button class="floorBtn">复制订单链接</button>
		</div>
	</body>
	<script src="/resources/script/jquery-1.11.1.min.js"></script>
	<script src="/resources/script/info.js"></script>
	<script src="/resources/script/order/orderDetails.js"></script>
	<script>
		
	</script>
</html>