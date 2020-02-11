<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>商品管理</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="/resources/style/sellerGoodsList.css">
		<link rel="stylesheet" href="/resources/style/info.css">
	</head>
	<body>
		<div class="head">
			<div class="headNav">
				<div class="headNavL">小店商品</div>
				<div class="headNavR">商品分类</div>
				<div class="headNavCover"></div>
			</div> 
			<div class="return" onclick="history.go(-1)">
				<span class="returnImg"></span><span>返回</span>
			</div>
			<div class="searchDiv">
				<img src="/resources/images/search.png" class="headIcon"/>
				<span class="complete" onclick="complete()">完成</span>
			</div>
		</div>
		<div class="listNavDiv">
			<div class="listNav">
				<div class="tap inSale">出售中(2)</div>
				<div class="tap haveShelves">已下架(0)</div>
			</div>
		</div>
		<div class="goodsList">
			<div class="oneGoods">
				<div class="checkGoods">
					<label class="checkboxDiv">
					  	<input type="checkbox" class="opaction goodsCheck"/>
					</label>
				</div>
				<div class="goodsImgDiv">
					<img src="/resources/images/goodsImg.png" class="goodsImg"/>
				</div>
				<div class="goodsInfo">
					<div class="goodsName">欧立夫 橄榄油 30ml</div>
					<div class="goodsInfoFloor">
						<div class="inventory">
							<span>库存：</span><span>93件&nbsp;&nbsp;</span><span>利润：¥</span><span>12.0</span><span>/件</span>
						</div>
						<div>
							<span>售价：</span><span class="price">¥108.0</span>
						</div>
					</div>
				</div>
				<div class="clear"></div>
				<div class="operationDiv" onclick="operationList(this)">
					<img src="/resources/images/operationImg.png" class="operationIcon"/>
				</div>
				<div class="operationList" style="display: none;">
					<div class="navigation">
						<table cellpadding="0" cellspacing="0" class="operationTab">
							<tr>
								<td>
									<div class="floorNav">
										<img src="/resources/images/edit.png" class="operationImg"/>
									</div>
									<div class="operationName">编辑</div>
								</td>
								<td>
									<div class="floorNav">
										<img src="/resources/images/xiajia.png" class="operationImg"/>
									</div>
									<div class="operationName">下架</div>
								</td>
								<td>
									<div class="floorNav">
										<img src="/resources/images/fenlei.png" class="operationImg"/>
									</div>
									<div class="operationName">分类</div>
								</td>
								<td>
									<div class="floorNav">
										<img src="/resources/images/edit.png" class="operationImg"/>
									</div>
									<div class="operationName">删除</div>
								</td>
								<td>
									<div class="floorNav">
										<img src="/resources/images/tuiguang.png" class="operationImg"/>
									</div>
									<div class="operationName">推广</div>
								</td>
								<td>
									<div class="floorNav">
										<img src="/resources/images/zhiding.png" class="operationImg"/>
									</div>
									<div class="operationName">置顶</div>
								</td>
							</tr>
						</table>
					</div>
					<div class="sanjiao"></div>
				</div>
			</div>
		</div>
		<div class="floor">
			<div class="floorBtnDiv">
				<button class="addGoods floorBtn" onclick="addGoods()">添加商品</button><button class="management floorBtn" onclick="managementGoods()">批量管理</button>
			</div>
			<table class="flooroPeration">
				<tr>
					<td>
						<div class="floorPerationImg">
							<label class="checkboxAll">
							  	<input type="checkbox" class="opaction goodsCheckAll"/>
							</label>
						</div>
						<div class="floorPerationName">全选</div>
					</td>
					<td style="width:15%;height:14vw;"></td>
					<td>
						<div class="floorPerationImg">
							<img src="/resources/images/addImg.png" class="floorImg"/>
						</div>
						<div class="floorPerationName">移到顶部</div>
					</td>
					<td>
						<div class="floorPerationImg">
							<img src="/resources/images/addImg.png" class="floorImg"/>
						</div>
						<div class="floorPerationName">下架</div>
					</td>
					<td>
						<div class="floorPerationImg">
							<img src="/resources/images/deleteRed.png" class="floorImg"/>
						</div>
						<div class="floorPerationName">删除</div>
					</td>
				</tr>
			</table>
		</div>
	</body>
	<script src="/resources/script/jquery-1.11.1.min.js"></script>
	<script src="/resources/script/info.js"></script>
	<script src="/resources/script/order/sellerGoodsList.js"></script>
	<script>
		
	</script>
</html>