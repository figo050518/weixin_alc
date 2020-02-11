<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>商品详情</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="/resources/style/goodsDetails.css">
		<link rel="stylesheet" href="/resources/style/swiper.min.css">
		<link rel="stylesheet" href="/resources/style/info.css">
	</head>
	<style>
		.swiper-pagination-bullet-active{
			background:#F7E05F !important;
		}
		.swiper-pagination-bullet{
			background:#F5F5F5;
			opacity:1;
		}
		.detailsImgList img{
			max-width: 100vw;
		}
	</style>
	<body>
		<!--选择的商品规格-->
		<input type="hidden" class="goodsType" value=""/>
		<div class="head">
			<div class="nickNameDiv">
				<span class="nickName">商品详情</span>
			</div> 
			<div class="return" onclick="history.go(-1)">
				<span class="returnImg"></span><span>返回</span>
			</div>
			<div class="toOtherPageDiv" onclick="toOtherPage()">
				<img src="/resources/images/toOther.png" class="toOthwePage"/>
			</div>
			<div class="headNav" style="display: none;">
				<div id="sanjiao"></div>
				<div class="tableDiv">
					<table cellpadding="0" cellspacing="0" style="">
						<tr style="height: 40px;">
							<td style="border-bottom: 1px solid #fff;">
								<img src="/resources/images/button_shouye_foucxs-1.png" style="height:16px;"/>
							</td>
							<td style="border-bottom: 1px solid #fff;">
								<span style="font-size: 14px;color: #fff;margin-left: 10px;">商城首页</span>
							</td>
						</tr>
						<tr style="height: 40px;">
							<td style="border-bottom: 1px solid #fff;">
								<img src="/resources/images/button-me.png" style="height:16px;"/>
							</td>
							<td style="border-bottom: 1px solid #fff;">
								<span style="font-size: 14px;color: #fff;margin-left: 10px;">个人中心</span>
							</td>
						</tr>
						<tr style="height: 40px;">
							<td style="">
								<img src="/resources/images/button-gouwuche.png" style="height:16px;"/>
							</td>
							<td style="">
								<span style="font-size: 14px;color: #fff;margin-left: 10px;">购物车</span>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div style="width: 100%;height: 50px;"></div>
		<div class="swiper-container">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<img src="/resources/images/goodsImg.png" class="goodsImg"/>
				</div>
				<div class="swiper-slide">
					<img src="/resources/images/goodsImg.png" class="goodsImg"/>
				</div>
				<div class="swiper-slide">
					<img src="/resources/images/goodsImg.png" class="goodsImg"/>
				</div>
				<div class="swiper-slide">
					<img src="/resources/images/goodsImg.png" class="goodsImg"/>
				</div>
			</div>
			<div class="swiper-pagination"></div>
		</div>
		<div class="goodsInfoDiv">
			<div class="goodsInfo1">
				<div class="goodsName">这是商品名称</div>
				<div class="goodsPrice">¥111.00</div>
			</div>
			<div class="goodsInfo2">
				<div class="freight">
					<span>运费：¥</span><span>7.00</span>
				</div>
				<div class="inventory">
					<span>剩余：</span><span>14</span>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="goodsSizeDiv">
			<div class="goodsSize" onclick="chooseType()">
				<span>选择：</span><span class="checkedGoodsType">商品规格</span>
			</div>
		</div>
		<div class="sellerInfo">
			<div class="goodsSize" style="border-bottom: 1px solid #EBECEE;">
				<span class="nickImg"></span>
				<span>我的昵称</span>
			</div>
			<div class="guarantee">
				<span class="guaranteeImg"></span>
				<span>担保交易</span>
			</div>
		</div>
		<div class="shortDescriptionDiv">
			<div class="shortDescription">
				<span>这里是商品的描述</span>
			</div>
		</div>
		<div class="detailsDiv">
			<div class="detailsImgList">
				<p>
					<img src="/resources/images/goodsdescribe1.jpg"/>
				</p>
				<p>
					<img src="/resources/images/goodsdescribe2.jpg"/>
				</p>
				<p>
					<img src="/resources/images/goodsdescribe3.jpg"/>
				</p>
			</div>
		</div>
		<!--<div class="support">
			<img src="/resources/images/goodsImg.png" class="supportImg"/>
		</div>-->
		<div style="width: 100%;height: 60px;background:#F5F5F5;"></div>
	<!--这是买家的底部-->
		<div class="floor" style="height: 50px;display: block;">
			<table cellpadding="0" cellspacing="0" class="floorTab">
				<tr>
					<td style="border:1px solid #BBBBBB;width: 50px;text-align: center;border-radius:6px;" onclick="kefu()">
						<div style="width: 16px;height: 16px;margin-left: 17px;">
							<img src="/resources/images/icon-shouhou.png" style="height: 14px;margin-top: 5px;display: block;"/>
						</div>
						<div style="font-size: 10px;">客服</div>
					</td>
					<td style="width:5px;height: 100%;"></td>
					<td style="border:1px solid #BBBBBB;width: 50px;text-align: center;border-radius:6px;position:relative;" onclick="toShopCat()">
						<div style="width: 16px;height: 16px;margin-left: 17px;">
							<img src="/resources/images/gouwucheImg.png" style="height: 14px;margin-top: 5px;display: block;"/>
						</div>
						<div style="font-size: 10px;">购物车</div>
						<div class="shoppCartNum">10</div>
					</td>
					<td style="width:5px;height: 100%;"></td>
					<td style="text-align: center;" onclick="toAddShoppingCart(this)" msg="1">
						<button class="floorBtn">加入购物车</button>
					</td>
					<td style="width:5px;height: 100%;"></td>
					<td style="text-align: center;" onclick="buyNowGoods(this)" msg="2">
						<button class="floorBtn">立即购买</button>
					</td>
				</tr>
			</table>
		</div>
	<!--这是卖家后台的底部-->
		<div class="floor" style="height: 50px;display: none;">
			<table cellpadding="0" cellspacing="0" class="floorTab">
				<tr>
					<td style="text-align: center;" onclick="toAddShoppingCart(this)" msg="1">
						<button class="edit operationGoods">编辑</button>
					</td>
					<td style="text-align: center;" onclick="toAddShoppingCart(this)" msg="1">
						<button class="shelves operationGoods">下架</button>
					</td>
					<td style="text-align: center;" onclick="toAddShoppingCart(this)" msg="1">
						<button class="promote operationGoods">推广</button>
					</td>
				</tr>
			</table>
			<div class="navigationDiv" style="display: none;">
				<div class="navigation">
					<table cellpadding="0" cellspacing="0" style="">
						<tr style="height: 40px;" onclick="home()">
							<td style="border-bottom: 1px solid #fff;">
								<span style="font-size: 14px;color: #fff;margin-left: 10px;">编辑商品</span>
							</td>
						</tr>
						<tr style="height: 40px;" onclick="myIndex()">
							<td style="border-bottom: 1px solid #fff;">
								<span style="font-size: 14px;color: #fff;margin-left: 10px;">商品分类</span>
							</td>
						</tr>
						<tr style="height: 40px;" onclick="myCart()">
							<td style="">
								<span style="font-size: 14px;color: #fff;margin-left: 10px;">删除商品</span>
							</td>
						</tr>
					</table>
				</div>
				<div id="test1"></div>
			</div>
		</div>
		<!--点击颜色分类的弹窗-->
		<div id="popupCover" class="popupCover" onclick="closePopup()" style="display:none;"></div>
	  	<div class="popup" id="submitPopup"> 
			<div class="popupGoodsDiv" style="position: relative;width: 100%;">
				<div style="width:30%;margin-left:3%;margin-top: -15px;float: left;height:30vw;" class="goodsImgDiv">
					<img src="/resources/images/goodsImg.png" id="prviewImage" style="width: 100%;border-radius:5px;border:1px solid #ECECEC;height:100%;"/>
				</div>
				<div class="goodsInfo" style="width: 65%;">
					<div style="height: 25px;line-height:25px;">
						<input type="hidden" class="imgId">
						<div style="float: left;" class="goodsPrice"></div>
						<div style="float: right;margin-right: 5%;">
							<img src="/resources/images/cancel.png" style="height: 21px;" onclick="closePopup()" />
						</div>
						<div class="clear"></div>
					</div>
					<div style="height: 25px;line-height:25px;">库存<span class="goodsCount">17</span>件</div>
					<div style="height: 25px;line-height:25px;" class="chooseResulte">请选择颜色分类</div>
				</div>
				<div class="clear"></div>
			</div>
			
			<div class="goodsattribute" style="overflow: auto;width: 100%;">
				<div style="margin-left:3%;font-size: 1.6em;margin-top: 20px;">颜色分类</div>
				<div style="width: 94%;margin-left: 3%;padding-bottom:15px;border-bottom:1px solid #F1F1F1;overflow: auto;max-height:50vw;" class="colorTypeDiv">
					<i class="colorType"  style="color:#000000;" onclick="chooseColor(this)">红色18寸</i>
					<i class="colorType"  style="color:#000000;" onclick="chooseColor(this)">黑色17寸</i>
					<i class="colorType"  style="color:#000000;" onclick="chooseColor(this)">黄色20寸</i>
				</div>
				
				<div style="width:96%;height:50px;margin-left:3%;border-bottom:1px solid #F1F1F1;">
					<div style="line-height:50px;float: left;">
						<span style="font-size:1.6em;">购买数量</span>
					</div>
					<div class="countDiv">
						<div class="min">-</div>
						<div class="count" style="height: 25px;">
							<input class="text_box" id="goodsCount" name="goodsCount" type="text" value="1"  style="border: none;border: none;width: 40px;height: 25px;padding: 0px;margin: 0px;text-align: center;font-size: 0.9em;" readonly="readonly"/> 
						</div>
						<div class="add">+</div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
			
			<div style="height:50px;width:100%;" class="btnDiv1">
				<button class="buyNowGoods" id="popupBtn1"  onclick="buyNowGoodsDetail()" style="width:100%;display: none;">立即购买</button>
				<button class="buyNowGoods" id="popupBtn2" onclick="sureAdd()" style="width:100%;display: none;">加入购物车</button>
				<button class="buyNowGoods" id="popupBtn3"  onclick="toNext()" style="width:100%;display: none;">下一步</button>
			</div>
			<input type="hidden" id="guigeName">
			<div style="height:50px;width:100%;display: none;" class="btnDiv2">
				<div class="sureBtn" id="surebtn" onclick="sureClick()">确定</div>
			</div>
	  	</div>
	</body>
	<script src="/resources/script/jquery-1.11.1.min.js"></script>
	<script src="/resources/script/info.js"></script>
	<script type="text/javascript" src="/resources/script/swiper.min.js"></script>
	<script type="text/javascript" src="/resources/script/product/goodsDetails.js"></script>
	<script>
		var swiper = new Swiper('.swiper-container', {
			pagination: '.swiper-pagination',
	        nextButton: '.swiper-button-next',
	        prevButton: '.swiper-button-prev',
	        paginationClickable: true,
	        spaceBetween: 30,
	        centeredSlides: true,
	        autoplay: 2500,
	        autoplayDisableOnInteraction: false
	    });
	</script>
</html>