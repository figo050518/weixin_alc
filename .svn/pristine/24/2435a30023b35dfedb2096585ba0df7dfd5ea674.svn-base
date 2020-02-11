<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
		<title>地址管理</title>
		<script src="/resources/script/jquery-1.11.1.min.js"></script>
		<script src="/resources/script/info.js"></script>
		<link rel="stylesheet" href="/resources/style/addressMange.css">
		<link rel="stylesheet" href="/resources/style/info.css">
	</head>
	<body>
		<div class="head">
			<div class="title">
				<span>地址列表</span>
			</div> 
			<div class="return">
				<span class="returnImg"></span><span>返回</span>
			</div>
		</div>
		<div class="blank" style="width: 100%;height: 50px;"></div>
		<!--收货地址部分-->
		<c:forEach items="${addrList}" var="arr">  
      <div class="oneAddressDiv">
			<input type="hidden" class="addressId" id="addressId" value="${arr.id}"/>
			<div class="addressUp">
				<div class="addressLeft">
					<div style="width: 100%;height: 100%;position: relative;">
						<img src="/resources/images/icon-dizhi.png" class="addressImg1"/>
					</div>
				</div>
				<div class="addressMiddle">
					<div style="padding-top: 3vw;">
						<div class="consignee">
							收货人：<span>${arr.receiverName}</span>
						</div>
						<div class="mobile">${arr.contactNum}</div>
						<div class="clear"></div>
					</div>
					<div class="address">
						收货地址：
						<span>${arr.detailAddr}</span>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			
			<div class="addressDown" style="height:35px;width: 100%;background:#fff;">
			<%-- 	<div class="defaultAddress" >
					<label class="checkboxDiv"> 
						<input type="checkbox" class="option-input GoodsCheck" ${1 eq arr.isDefault?"checked":""}  value="1"> 
					</label>
				  	<div style="float: left;font-size: 1.6em;line-height: 25px;margin-left: 10px;">
						默认地址
					</div>
				  	<div class="clear"></div>
				</div> --%>
				
				<div class="deleteDiv" onclick="deleteAddress(this)">
					<img src="/resources/images/icon_delete.png" class="deleteImg"/>
					<div class="deleteText">删除</div>
				</div>
				<div class="deleteDiv" onclick="editAddress(this)">
					<img src="/resources/images/editImg2.png" class="deleteImg"/>
					<div class="deleteText">编辑</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>

    	</c:forEach> 
		
		
		<div style="width: 100%;height:50px;"></div>
		<c:if test="${ empty addrList}">
		<button class="addAddress" onclick="addAddressClick()">
			<strong>添加新地址</strong>
		</button>
		</c:if>
	</body>
	<script type="text/javascript" src="/resources/script/user/addressmange.js"></script>
	<script>
		
	</script>
</html>
