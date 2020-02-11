<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>提交售后</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="/resources/style/acceptAfterSales.css">
		<link rel="stylesheet" href="/resources/style/info.css">
	</head>

	<body>
		<div class="head">
			<div class="title">
				<span>售后申请</span>
			</div> 
			<div class="return" onclick="history.go(-1)">
				<span class="returnImg"></span><span>返回</span>
			</div>
		</div>
		<div class="blank" style="width: 100%;height: 50px;"></div>
		<form id="insertInfo">
		<div class="goodsDiv"  style="padding:0px;font-family: '微软雅黑';padding-top:0px;background: #fff;">
			<div class="orderNumDiv">
				<span>订单号：${orderInfoPO.orderNum}</span>
			</div>
			<div class="goodsDiv" >
				<div class="goodsImgDiv">
					<img src="${orderItemPO.productPicUrl }" class="goodsImg"/>
				</div>
				<div class="goodsInfo">
					<div class="goodsInfoMiddle">
						<div class="goodsName">
							${orderItemPO.productName}
						</div>
						<div class="goodsDescribeDiv">
							<div class="goodsDescribe">
								${orderItemPO.productSpec}
							</div>
						</div>
					</div>
					<div class="goodsInfoRight">
						<div class="goodsPriceDiv">¥<span class="goodsPrice">${orderItemPO.productPrice}</span></div>
						<input type="hidden" id="goodsCount" value="${orderItemPO.productQuant}" >
						<div class="goodsCountDiv">X${orderItemPO.productQuant}</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		 
		<div class="chooseType">
			<div style="width:100%;">
				<div class="afterTextL">售后类型<span style="color:red;">*</span>:</div>
				<div class="afterTextR">非质量问题不给退货<span style="color:red;">*</span></div>
				<div class="clear"></div>
			</div>
			
			<div class="typeBtnDiv">
				<input type="button" class="typeBtn activity" onclick="TuihuoType('1')" value="退货">
				<input type="button" class="typeBtn" onclick="TuihuoType('0')" value="退款">
				<div class="clear"></div>
			</div>
		</div>
		
		<div class="tuihuoCountDiv">
			<div class="countLeft">请选择退货数量:</div>
			<div class="countDiv" style="float: right;margin-right: 5%;">
				<div class="min">-</div>
				<div class="count" style="height: 25px;">
					<input class="text_box" name="productQuant" type="text" value="1" readonly="readonly"/> 
				</div>
				<div class="add">+</div>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>
		<!-- 申请售后必要参数 -->
		<input type="hidden" name="buyerId" value="${orderInfoPO.userId}">
		<input type="hidden" name="sellerId" value="${orderInfoPO.sellerId}">
		<input type="hidden" name="orderId" value="${orderInfoPO.id}">
		<input type="hidden" name="orderNum" value="${orderInfoPO.orderNum}">
		<input type="hidden" name="productId" value="${orderItemPO.productId}">
		<input type="hidden" name="productPicUrl" value="${productImagePO.imgUrl }">
		<input type="hidden" name="productName" value="${orderItemPO.productName}">
		<input type="hidden" name="productPrice" class="productPrice" value="${orderItemPO.productPrice}">
		<input type="hidden" name="productSpec" value="${orderItemPO.productSpec}">
		<input type="hidden" name="productTotalAmount" value="${orderItemPO.productTotalAmount}">
		<input type="hidden" name="productCount" value="${orderItemPO.productQuant}">
		<!-- 选择退货退款 赋值 -->
		<input type="hidden" class="isTuihuo" name="isTuihuo" value="1">
		
		<div class="tuihuoMoneyDiv">
			<div class="countLeft">退货金额: </div>
			<!-- 选择数量时 赋值 -->
			<input type="number" class="refundAmount" readonly="readonly" value="${orderItemPO.productPrice}"/>
			<div class="clear"></div>
		</div>
		
		<div style="background:#fff;width: 100%;" id="returnInstructions">
			<div style="margin-left:5%;font-size:14px;padding-top: 3vw;">退货说明(必填)</div>
			<div style="width: 90vw;margin-left:5vw;margin-top: 15px;position: relative;height: 110px;font-size:1.4em;">
				<textarea  class="returnInstructions" name="refundReason" placeholder="尽可能详细描述您的问题"></textarea>
			</div>
		</div>
		
		<div style="background:#fff;width: 100%;">
			<div style="margin-left:5%;padding-top: 20px;">
				<span style="font-size:1.4em;">上传图片</span>
				<span style="font-size:1.1em;color:#999999;">(最多上传3张，每张不超过5M)</span>
			</div>
			<div id="img">
				<div class="imgbox">
					 <div class="imgnum">
						  <input type="file" class="filepath" />
						  <img src="/resources/images/icon_kuang2.png" class="img1" />
						  <img src="" class="img2" />
					 </div>
				</div>
				<div class="imgbox">
					 <div class="imgnum">
						  <input type="file" class="filepath" />
						  <img src="/resources/images/icon_kuang2.png" class="img1" />
						  <img src="" class="img2" />
					 </div>
				</div>
				<div class="imgbox">
					 <div class="imgnum">
						  <input type="file" class="filepath" />
						  <img src="/resources/images/icon_kuang2.png" class="img1" />
						  <img src="" class="img2" />
					 </div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		</form>
		<div style="height: 50px;position: relative;">
			<button class="btn" onclick="submitInfo()">
				<span class="btnImg"></span><span style="line-height: 40px;">提交申请</span>
			</button>
		</div>
	</body>
	<script src="/resources/script/jquery-1.11.1.min.js"></script>
	<script src="/resources/script/info.js"></script>
	<script src="/resources/script/order/acceptAfterSales.js"></script>
	<script>
	
	</script>
</html>