<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
		<title>收货地址详情</title>
		<script src="/resources/script/jquery-1.11.1.min.js"></script>
		<script src="/resources/script/info.js"></script>
		<link rel="stylesheet" href="/resources/style/mui.min.css">
		<link rel="stylesheet" href="/resources/style/addressDetails.css">
		<link href="/resources/style/mui.picker.css" rel="stylesheet" />
		<link href="/resources/style/mui.poppicker.css" rel="stylesheet" />
	</head>
	<style>
			
	</style>
	<body>
		<div class="head">
			<div class="title">
				<span>编辑收货地址</span>
			</div> 
			<div class="return">
				<span class="returnImg"></span><span>返回</span>
			</div>
		</div>
		<div class="blank" style="width: 100%;height: 50px;"></div>
		<form id="submitForm" method="post" action="/uc/user/address/add">
			<input type="hidden" value="${userAddress.id}" id="id" name="id"/>
			<input type="hidden" value="${userAddress.province}" id="province" name="province"/>
			<input type="hidden" value="${userAddress.city}" id="city" name="city"/>
			<input type="hidden" value="${userAddress.area}" id="area" name="area"/>
			<!-- <input type="hidden"  id="province" name="province" value=""/> -->
			<div style="background:#fff;">
				<div class="oneLine">
					<div class="left">收货人</div>
					<div class="right">
						<input type="text" name="receiverName" class="inputType" placeholder="请输入姓名" id="receiverName" value="${userAddress.receiverName}"/>
					</div>
					<div class="clear"></div>
				</div>
				
				<div class="oneLine">
					<div class="left">联系电话</div>
					<div class="right">
						<input type="text" name="contactNum" class="inputType" placeholder="请输入联系人号码" id="contactNum"  value="${userAddress.contactNum}"/>
					</div>
					<div class="clear"></div>
				</div>
				
				<div class="oneLine">
					<div class="left">所在地区</div>
					<div class="address" style="position: relative;">
						<button id='showCityPicker'  type='button'>${userAddress.province} ${userAddress.city} ${userAddress.area}</button>
						<div class="clear"></div>
					</div>
					<div class="clear"></div>
				</div>
				
				
				<div style="background:#fff;">
					<div class="textareaLeft" style="margin-top: 15px;">详细地址</div>
					<div class="right">
						<textarea class="textareaType" placeholder="请输入详细地址" id="detailAddr" name="detailAddr">${userAddress.detailAddr}</textarea>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</form>
		
		<button onclick="surenClick3()" class="sureBtn"  id="btnSubmit"><strong>确定</strong></button>
	</body>
	<script type="text/javascript" src="/resources/script/user/editForm.js"></script>
	<script src="/resources/script/mui.min.js"></script>
	<script src="/resources/script/mui.picker.js"></script>
	<script src="/resources/script/mui.poppicker.js"></script>
	<script src="/resources/script/city.data-3.js" type="text/javascript" charset="utf-8"></script>
	<script>
		(function($, doc) {
				$.init();
				$.ready(function() {
					//-----------------------------------------
					//					//级联示例
					var cityPicker = new $.PopPicker({
						layer: 3
					});
					cityPicker.setData(cityData3);
					var showCityPickerButton = doc.getElementById('showCityPicker');
					var cityResult = doc.getElementById('showCityPicker');
					showCityPickerButton.addEventListener('tap', function(event) {
						/*$("input").blur();*/
						var input1 = document.getElementById("receiverName");
						input1.blur();
						var input1 = document.getElementById("receiverName");
						input1.blur();
						var input1 = document.getElementById("receiverName");
						input1.blur();
						var input1 = document.getElementById("receiverName");
						input1.blur();
						
						cityPicker.show(function(items) {
							cityResult.innerText =(items[0] || {}).text + " " + (items[1] || {}).text + " " + (items[2] || {}).text;
							//返回 false 可以阻止选择框的关闭
							//return false;
							/*alert((items[0] || {}).text);*/
							document.getElementById("province").value = (items[0] || {}).text;
							document.getElementById("city").value = (items[1] || {}).text;
							document.getElementById("area").value = (items[2] || {}).text;
							cityResult.style.color="#333333";
							//alert(document.getElementById("aa").value);
						});
					}, false);
				});
			})(mui, document);
			
			function surenClick(){
				alert(provinceVel());
			}
			
	</script>
</html>
