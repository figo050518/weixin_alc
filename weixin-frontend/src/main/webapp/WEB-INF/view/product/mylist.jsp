<!DOCTYPE html>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>

	<head>
		<meta charset="utf-8">
		<title>卖家商品列表</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="/resources/style/sellerGoodsList.css">
		<link rel="stylesheet" type="text/css" href="/resources/style/mui.min.css" />
		<link rel="stylesheet" href="/resources/style/info.css">
	</head>
	<body>
		<input type="hidden" class="upState" value="${upState}" id="upState"/>
		<input type="hidden"  value="${groupId}" id="groupId"/>
		<div class="head">
			<div class="headNav">
				<div class="headNavL">小店商品</div>
				<div class="headNavR" onClick="viewGroups()">商品分类</div>
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
				<div class="tap inSale" id="" onclick="findByOrder(1)">出售中(<span id="onSaleCount"></span>)</div>
				<div class="tap haveShelves" id="" onclick="findByOrder(0)">已下架(<span id="offSaleCount"></span>)</div>
			</div>
		</div>
		<div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="margin: 92px 0 15vw 0;background:#FFFFFF;">
			<div class="mui-scroll">
				<!--数据列表-->
				<ul class="mui-table-view mui-table-view-chevron" style="background:#FFFFFF;border:0px;" id="goodsList">
					
				</ul>
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
							  	<input type="checkbox" class="opaction checkAll"/>
							</label>
						</div>
						<div class="floorPerationName">全选</div>
					</td>
					<td style="width:15%;height:14vw;"></td>
					<td>
						<div class="floorPerationImg" onClick="offlines();">
							<img src="/resources/images/addImg.png" class="floorImg"/>
						</div>
						<div class="floorPerationName" onClick="offlines();">
						<c:if test="${upState==1}">
							下架 
						</c:if>
						<c:if test="${upState==0}">
							上架
						</c:if>
						</div>
					</td>
					<td>
						<div class="floorPerationImg" onClick="deletes();">
							<img src="/resources/images/deleteRed.png" class="floorImg"/>
						</div>
						<div class="floorPerationName" onClick="deletes();">删除</div>
					</td>
				</tr>
			</table>
		</div>
		
	</body>
	<script src="/resources/script/jquery-1.11.1.min.js"></script>
	<script src="/resources/script/info.js"></script>
	<script src="/resources/script/mui.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/resources/script/product/mylist.js"></script>
	<script>
		$("body").find("*").click(function(){
			$(".operationList").css("display","none");
		});
		//点击批量管理
		function managementGoods(){
			$(".checkGoods").css("display","block");
			$(".goodsImgDiv").css("margin-left","0");
			$(".goodsInfo").css("width","60vw");
			$(".floorBtnDiv").css("display","none");
			$(".flooroPeration").css("display","table");
			
			$(".operationDiv").removeAttr("onclick");
			$(".headIcon").css("display","none");
			$(".complete").css("display","block");
			$(".headNavCover").css("display","block");
			mui('#pullrefresh').pullRefresh().disablePullupToRefresh();
		}
		//点击完成
		function complete(){
			$(".checkGoods").css("display","none");
			$(".goodsImgDiv").css("margin-left","3vw");
			$(".goodsInfo").css("width","72vw");
			$(".floorBtnDiv").css("display","block");
			$(".flooroPeration").css("display","none");
			$(".headNavCover").css("display","none");
			mui('#pullrefresh').pullRefresh().enablePullupToRefresh();
		}
	</script>
</html>