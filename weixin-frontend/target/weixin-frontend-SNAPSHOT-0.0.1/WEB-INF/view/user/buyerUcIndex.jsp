<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>我的</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="/resources/style/myPage.css">
	</head>
	<body>
		<div class="banner">
			<div class="bannerText"></div>
			<div class="userText">
				<div class="name">Hello  <span>郁金香</span></div>
				<div class="grace">您拥有本店积分：<span>暂无数据</span></div>
			</div>
		</div>
		<div class="orderStatus">
			<div class="oneStatus">
				<img src="/resources/images/icon-daifukuan.png">
				<p>待付款</p>
			</div>
			<div class="oneStatus">
				<img src="/resources/images/icon-daijiedan.png">
				<p>待接单</p>
			</div>
			<div class="oneStatus">
				<img src="/resources/images/icon-daifahuo.png">
				<p>待发货</p>
			</div>
			<div class="oneStatus">
				<img src="/resources/images/icon-yifahuo.png">
				<p>已发货</p>
			</div>
			<div class="oneStatus">
				<img src="/resources/images/icon-yiwancheng.png">
				<p>已完成</p>
			</div>
		</div>
		<div class="oneArea">
			<a href="/uc/orderInfo/getOrderList">
				<div class="couponsDiv">
					<div class="leftImgDiv">
						<img src="/resources/images/icon-dingdan.png" class="leftImg"/>
					</div>
					<div class="leftText">我的订单</div>
					<div class="toNextDiv">
						<img src="/resources/images/toNext.png" class="toNext"/>
					</div>
					
					<div class="clear"></div>
				</div>
			</a>
		</div>
		
		
		
		
		<div class="oneArea" style="margin-top:0;border-top:0">
			<a href="/shopCart/init">
				<div class="couponsDiv">
					<div class="leftImgDiv">
						<img src="/resources/images/gouwucheBlack.png" class="leftImg"/>
					</div>
					<div class="leftText">我的购物车</div>
					<div class="toNextDiv">
						<img src="/resources/images/toNext.png" class="toNext"/>
					</div>
					<div class="clear"></div>
				</div>
			</a>
		</div>
		
		<div class="oneArea" style="margin-top:0;border-top:0">
			<a href="/uc/product/fav/init">
				<div class="couponsDiv">
					<div class="leftImgDiv">
						<img src="/resources/images/icon-shoucang.png" class="leftImg"/>
					</div>
					<div class="leftText">我的收藏</div>
					<div class="toNextDiv">
						<img src="/resources/images/toNext.png" class="toNext"/>
					</div>
					<div class="clear"></div>
				</div>
			</a>
		</div>
		<div class="oneArea">
		
			<div class="couponsDiv">
			<a href="/uc/buyer/manage/index">
				<div class="leftImgDiv">
					<img src="/resources/images/icon-shezhi.png" class="leftImg"/>
				</div>
				<div class="leftText">账户设置</div>
				<div class="toNextDiv">
					<img src="/resources/images/toNext.png" class="toNext"/>
				</div>
				<div class="clear"></div>
				<div class="mobilePhone">
					<span>${userName}</span>
				</div>
				</a>
			</div>
			
			<div class="couponsDiv">
				<a href="/uc/user/address/list">
					<div class="leftImgDiv">
						<img src="/resources/images/icon-location.png" class="leftImg"/>
					</div>
					<div class="leftText">地址管理</div>
					<div class="toNextDiv">
						<img src="/resources/images/toNext.png" class="toNext"/>
					</div>
				</a>
				<div class="clear"></div>
			</div>
		</div>
		<div class="oneArea">
			<a href="javascript:loginOut()">
				<div class="couponsDiv">
					<div class="leftImgDiv">
						<img src="/resources/images/icon-userTuichu.png"  class="leftImg"/>
					</div>
					<div class="leftText">退出账户</div>
					<div class="toNextDiv">
						<img src="/resources/images/toNext.png" class="toNext"/>
					</div>
					<div class="clear"></div>
				</div>
			</a>
		</div>
	</body>
	<script src="/resources/script/jquery-1.11.1.min.js"></script>
	<script>
		function loginOut(){
			window.location.href="/user/loginOut";
			
		}
	</script>
</html>