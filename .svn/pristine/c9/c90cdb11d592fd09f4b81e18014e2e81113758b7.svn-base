<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
		<title>售后详情</title>
		<link rel="stylesheet" href="/resources/style/info.css">
		<link rel="stylesheet" href="/resources/style/applyAfterSales.css">
	</head>
	<body>
		<div class="head">
			<div class="title">
				<span>售后详情</span>
			</div> 
			<div class="return" onclick="history.go(-1)">
				<span class="returnImg"></span><span>返回</span>
			</div>
		</div>
		<div class="blank" style="width: 100%;height: 50px;"></div>
		
		<table cellpadding="0" cellspacing="0" id="addressTable">
			<tr>
				<td style="width:15vw;text-align: center;" rowspan="2">
					<img src="/resources/images/icon-dizhi.png" style="width:20px"/>
				</td>
				<td style="text-align: left;">
					收货人：<span>${orderReceiverAddressPO.receiverName }</span>
				</td>
				<td style="text-align: right;">
					<span style="">${orderReceiverAddressPO.contactNum }</span>
				</td>
				<td style="width:5vw"></td>
			</tr>
			<tr>
				<td colspan="2">
					<div style="margin-top:10px;">
						<span>收货地址：</span>
						<span>${orderReceiverAddressPO.province}${orderReceiverAddressPO.city}
							${orderReceiverAddressPO.area}${orderReceiverAddressPO.detailAddr}</span>
					</div>
				</td>
				<td style="width:5vw"></td>
			</tr>
		</table>
		
		
		<div class="oneOrder">
			<div class="orderNumberDiv">
				<div class="orderNumber">
					售后编号:${orderRefundRequestPO.refundNumber }
				</div>
				<div class="orderState">
					<!-- 买家显示 -->
					<c:if test="${userType=='1'}">
						<!-- 售后状态 1 申请中2已通过3已完成 4：拒绝 -->
						<c:if test="${orderRefundRequestPO.refundStatus=='1'}">
							<c:if test="${orderRefundRequestPO.isTuihuo=='0'}">
								<span>等待卖家同意退款</span>
							</c:if>
							<c:if test="${orderRefundRequestPO.isTuihuo=='1'}">
								<span>等待卖家同意退货</span>
							</c:if>
						</c:if>
						<c:if test="${orderRefundRequestPO.refundStatus=='2'}">
							<c:if test="${not empty orderRefundRequestPO.logisticsNumber}">
								<span>等待卖家确认收货</span>
							</c:if>
							<c:if test="${empty orderRefundRequestPO.logisticsNumber}">
								<span>卖家同意退货</span>
							</c:if>
						</c:if>
						<c:if test="${orderRefundRequestPO.refundStatus=='3'}">
							<c:if test="${orderRefundRequestPO.isTuihuo=='0'}">
								<span>退款成功</span>
							</c:if>
							<c:if test="${orderRefundRequestPO.isTuihuo=='1'}">
								<span>退货成功</span>
							</c:if>
						</c:if>
						<c:if test="${orderRefundRequestPO.refundStatus=='4'}">
							<span>售后已拒绝</span>
						</c:if>
					</c:if>
					
					<!-- 卖家 -->
					<c:if test="${userType=='2'}">
						<!-- 售后状态 1 申请中2已通过3已完成 4：拒绝 -->
						<c:if test="${orderRefundRequestPO.refundStatus=='1'}">
							<c:if test="${orderRefundRequestPO.isTuihuo=='0'}">
								<span>买家申请退款</span>
							</c:if>
							<c:if test="${orderRefundRequestPO.isTuihuo=='1'}">
								<span>买家申请退货</span>
							</c:if>
						</c:if>
						<c:if test="${orderRefundRequestPO.refundStatus=='2'}">
							<c:if test="${not empty orderRefundRequestPO.logisticsNumber}">
								<span>买家已退回货物</span>
							</c:if>
							<c:if test="${empty orderRefundRequestPO.logisticsNumber}">
								<span>等待买家退货物品</span>
							</c:if>
						</c:if>
						<c:if test="${orderRefundRequestPO.refundStatus=='3'}">
							<c:if test="${orderRefundRequestPO.isTuihuo=='0'}">
								<span>退款成功</span>
							</c:if>
							<c:if test="${orderRefundRequestPO.isTuihuo=='1'}">
								<span>退货成功</span>
							</c:if>
						</c:if>
						<c:if test="${orderRefundRequestPO.refundStatus=='4'}">
							<span>售后已拒绝</span>
						</c:if>
					</c:if>
				</div>
				<div class="clear"></div>
			</div>
			<div class="oneGoods">
				<!-- <div class="sellerInfo">
					<div class="goodsSize" style="border-bottom: 1px solid #EBECEE;">
						<span class="nickImg"></span>
						<span>卖家昵称</span>
					</div>
				</div> -->
				<div class="goodsDiv" >
					<div class="goodsImgDiv">
						<img src="${orderRefundRequestPO.productPicUrl }" class="goodsImg"/>
					</div>
					<div class="goodsInfo">
						<div class="goodsInfoMiddle">
							<div class="goodsName">
								${orderRefundRequestPO.productName }
							</div>
							<div class="goodsDescribeDiv">
								<div class="goodsDescribe">
									规格：${orderRefundRequestPO.productSpec }
								</div>
							</div>
						</div>
						<div class="goodsInfoRight">
							<div class="goodsPriceDiv">¥<span class="goodsPrice">${orderRefundRequestPO.productPrice }</span></div>
							<div class="goodsCount">X${orderRefundRequestPO.productQuant }</div>
						</div>
						<div class="clear"></div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		
		
		<div class="afterStatusDiv">
			<div class="afterSalesTypeText">售后类型:</div>
				<c:if test="${orderRefundRequestPO.isTuihuo=='0' }">
					<div class="afterSalesType">退款</div>
				</c:if>
				<c:if test="${orderRefundRequestPO.isTuihuo=='1' }">
					<div class="afterSalesType">退货</div>
				</c:if>
			<div class="line"></div>
			
			<div class="warmPrompt">温馨提示:非质量问题的退货需要您承担退回的运费</div>
			<div class="line"></div>
			<c:if test="${orderRefundRequestPO.isTuihuo=='1' }">
			<div class="afterSalesTypeText">退货说明</div>
				<div style="width: 90%;margin-left:5%;border:1px solid #ADADAD;height: 100px;margin-top:15px;color:#999999;font-size:1.4em;">
					<div style="margin: 5px;">${orderRefundRequestPO.refundReason}</div>
				</div>
			</c:if>
			<c:if test="${not empty orderRefundRequestPO.refusedReason }">
			<div class="afterSalesTypeText">拒绝退货原因</div>
				<div style="width: 90%;margin-left:5%;border:1px solid #ADADAD;height: 100px;margin-top:15px;color:#999999;font-size:1.4em;">
					<div style="margin: 5px;">${orderRefundRequestPO.refusedReason }</div>
				</div>
			</c:if>
		</div>
		
		<%-- <div style="margin-top:10px;width: 100%;height: 60px;background: #fff;">
			<c:if test="${userType=='2'}">
			<!-- 	<button class="cancelBtn">取消</button> -->
				<c:if test="${orderRefundRequestPO.refundStatus=='2' && not empty orderRefundRequestPO.logisticsNumber }">
					
				</c:if>
			</c:if>
			<c:if test="${userType=='1'}">
			<!-- 	<button class="cancelBtn">取消</button> -->
				<c:if test="${orderRefundRequestPO.refundStatus=='2' && orderRefundRequestPO.isTuihuo=='1' }">
					
				</c:if>
			</c:if>
		</div>  --%>
		
		
		<!--弹窗(拒绝取货的理由)-->
	  	<div class="popup" id="submitPopup" style="display:none;"> 
			<div style="margin-top:20px;">
				<div style="font-size:1.6em;margin-left:8%;">
					<img src="/resources/images/feedbackImg.png" style="height:20px"/>
					请选择拒绝取货的理由
				</div>
				
				<!--注意遍历checkbox时，要加下标 ${status.index+1}     varStatus="status"-->
				<div style="height: 35px;">
					<label class="checkboxDiv">
					  	<input type="checkbox" class="opaction goodsCheck"/>
					</label>
				  	<div class="jujueText">已经安装</div>
				  	<div class="clear"></div>
				</div>
				<div style="height: 35px;">
					<label class="checkboxDiv">
					  	<input type="checkbox" class="opaction goodsCheck"/>
					</label>
				  	<div class="jujueText">已经安装</div>
				  	<div class="clear"></div>
				</div>
				<div style="height: 35px;">
					<label class="checkboxDiv">
					  	<input type="checkbox" class="opaction goodsCheck"/>
					</label>
				  	<div class="jujueText">其他</div>
				  	<div class="clear"></div>
				</div>
				
				<textarea class="textareaType" placeholder="请输入输入拒绝取货理由"></textarea>
			</div>
			<div style="margin-top:10px;width: 100%;padding-bottom: 20px;">
				<button class="btn1" onclick="cancelClick()" id="canceBtn">取消</button>
				<button class="btn2" onclick="submitGrade()" id="sureBtn">确定</button>
			</div>
	  	</div>
	  	
	  	<!--弹窗2-->
	  	<div class="popup" id="submitPopup" style="display:none;"> 
			<div style="margin-top:50px;height: 30px;width: 100%;">
				<div class="aa">
					<div style="float: left;margin-left:20%;">
						<img src="/resources/images/warning.png" style="width:30px;"/>
					</div>
					<div style="float:left;line-height:30px;margin-left:5%;font-size:1.6em;">
						确定取走货物吗？
					</div>
				</div>
			</div>
			<div style="margin-top:50px;width: 100%;padding-bottom: 30px;">
				<button class="btn1" onclick="cancelClick()" id="canceBtn">取消</button>
				<button class="btn2" onclick="submitGrade()" id="sureBtn">确定</button>
			</div>
	  	</div>
	  	
	  	<!--弹窗的遮罩层-->
	  	<div id="cover" class="overlay" style="display: none;"></div>
	  	
	  	
	</body>
	<script src="/resources/script/jquery-1.11.1.min.js"></script>
	<script src="/resources/script/info.js"></script>
	<script type="text/javascript" src="/resources/script/order/applyAfterSales.js"></script>
	<script type="text/javascript" src="/resources/script/order/afterSalesDetails.js"></script>
</html>
