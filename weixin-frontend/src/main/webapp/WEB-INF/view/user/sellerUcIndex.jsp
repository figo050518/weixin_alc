<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>卖家管理后台首页</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="/resources/style/sellerUcindex.css">
	</head>
	<body>
		<div class="pageTitle">
			<span>微店</span>
			<div class="msgDiv">
				<img src="/resources/images/msgImg.png" class="msgImg"/>
			</div>
		</div>
		<div class="head">
			<div class="portraitDiv">
				<a href=""><img src="/resources/images/defaultTX.png" class="portrait"/></a>
			</div>
			<div class="nickNameDiv">
				<span class="nickName">${shopName}</span>
			</div>
			<div class="tuiguangDiv">
				<div class="tuiguang">
					<div style="width: 100%;height: 8vw;text-align: center;font-size: 0;">
						<img src="/resources/images/promoteImg.png" class="tuiguangImg"/>
					</div>
					<div style="font-size: 12px;color: #999999;text-align: center;">推广</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="blank"></div>
		<div class="recommendedArea">
			<div class="leftArea">
				<img src="/resources/images/recommendedImg1.png" class="recommendedImg"/>
			</div>
			<div class="rightArea">
				<img src="/resources/images/recommendedImg2.png" class="recommendedImg"/>
				<span class="corner">
					<em>new</em>
				</span>
			</div>
			<div class="clear"></div>
		</div>
		<div class="addGoodsArea">
			<div class="addGoodsDiv" onclick="addClick()">
				<img src="/resources/images/addImg.png" class="addImg"/>
				<div class="addText">添加商品</div>
				<div class="clear"></div>
			</div>
		</div>
		<table cellpadding="0" cellspacing="0" class="navigationArea">
			<tr>
				<td>
				 	<a href="/uc/product/initList">
					<div class="navImgDiv">
						<img src="/resources/images/shangpindingdan1.png" class="navImg"/>
					</div>
					<div class="navText" >商品管理</div>
					</a>
				</td>
				<td>
				    <a href="/uc/orderInfo/getOrderList">
						<div class="navImgDiv">
							<img src="/resources/images/dingdanguanli1.png" class="navImg"/>
						</div>
						<div class="navText">订单管理</div>
					</a>
				</td>
				<td>
					<a href="/uc/finance/index">
						<div class="navImgDiv">
							<img src="/resources/images/weixinshoufei.png" class="navImg"/>
						</div>
						<div class="navText">财务管理</div>
					</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="/uc/SellerCustomer/findByPage">
						<div class="navImgDiv">
							<img src="/resources/images/kehuguanli1.png" class="navImg"/>
						</div>
						<div class="navText">客户管理</div>
					</a>
				</td>
				<td>
					<a href="/uc/shop/view">
						<div class="navImgDiv">
							<img src="/resources/images/dianpuguanli1.png" class="navImg"/>
						</div>
						<div class="navText">店铺管理</div>
					</a>
				</td>
				<td>
					<a href="/uc/seller/manage/index">
						<div class="navImgDiv">
							<img src="/resources/images/wodeweidian1.png" class="navImg"/>
						</div>
						<div class="navText">我的微店</div>
					</a>
				</td>
			</tr>
		</table>
		<div class="blank"></div>
		<div class="advertising">
			<img src="/resources/images/recommendedImg3.png" class="advertisingImg"/>
		</div>
		
		
		<!--遮罩层-->
		<div class="cover" id="popupCover" style="display: none;" onclick="closePopup()"></div>
		
		<!--底部弹窗-->
		<div class="bottomPopup" id="addressPopup" style="display: none;">
			<table class="popupTab">
				<tr>
					<td onclick="addPlatFormProduct()">
						<img src="/resources/images/operationImg.png" class="popupTabImg"/>
						<div class="popupTabText">市场选货</div>
					</td>
					<td onclick="addMyProduct()">
						<img src="/resources/images/operationImg.png" class="popupTabImg"/>
						<div class="popupTabText">自营商品</div>
					</td>
				</tr>
			</table>
			<button class="popupCancle" onclick="closePopup()">取消</button>
		</div>
	</body>
	<script src="/resources/script/jquery-1.11.1.min.js"></script>
	<script src="/resources/script/info.js" type="text/javascript" charset="utf-8"></script>
	<script src="/resources/script/user/sellerUcIndex.js"></script>
</html>