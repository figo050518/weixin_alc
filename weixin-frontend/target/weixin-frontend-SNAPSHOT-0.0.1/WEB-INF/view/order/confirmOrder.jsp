<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>确认订单</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="/resources/style/confirmOrder.css">
		<link rel="stylesheet" href="/resources/style/info.css">
		<link href="/resources/style/mui.picker.css" rel="stylesheet" />
		<link href="/resources/style/mui.poppicker.css" rel="stylesheet" />
		<link rel="stylesheet" href="/resources/style/mui.min.css">
	</head>
	<body>
		
		<input value="" class="adddressVal" type="hidden"/>
		<div class="head">
			<div class="title">
				<span>确认订单</span>
			</div> 
			<div class="return" onclick="history.go(-1)">
				<span class="returnImg"></span><span>返回</span>
			</div>
		</div>
		<div class="blank" style="width: 100%;height: 50px;"></div>
		<div class="addressArea">
			<!--如果收货地址为空时-->
			<table cellpadding="0" cellspacing="0" class="addressBlank" onclick="addAddress(1)" style="display: none;">
				<tr>
					<td class="addImgTd">
						<img src="/resources/images/addAddressIMg.png" class="addAddressImg"/>
					</td>
					<td>
						<span class="addAddressText">新增收获地址</span>
					</td>
					<td class="toNextTd">
						<img src="/resources/images/toNext.png" class="toNextImg"/>
					</td>
				</tr>
			</table>
			<!--收货地址不为空时-->
			<table cellpadding="0" cellspacing="0" id="addressDiv" style="display: block;" onclick="chooseAddress()">
				<tr>
					<td style="width:calc(6vw + 16px);text-align: center;" rowspan="2">
						<img src="/resources/images/icon-dizhi.png" style="width:16px"/>
					</td>
					<td style="text-align: left;">
						收货人：<span class="checkedName">夏涵</span>
					</td>
					<td style="text-align: right;">
						<span class="checkedPhone">13813961410</span>
					</td>
					<td class="toNextTd" rowspan="2">
						<img src="/resources/images/toNext.png" class="toNextImg"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div style="margin-top:5px;">
							<span>收货地址：</span>
							<span class="checkedAddress">
								江苏省南京市雨花台区铁心桥街道
							</span>
						</div>
					</td>
				</tr>
			</table>
			<img src="/resources/images/addressLine.png" class="addressLine"/>
		</div>
		
		<div class="oneOrder">
			<div class="oneGoods">
				<div class="sellerInfo">
					<div class="goodsSize" style="border-bottom: 1px solid #EBECEE;">
						<span class="nickImg"></span>
						<span>卖家昵称</span>
					</div>
				</div>
				<div class="goodsDiv" >
					<div class="goodsImgDiv">
						<img src="/resources/images/goodsImg.png" class="goodsImg"/>
					</div>
					<div class="goodsInfo">
						<div class="goodsInfoMiddle">
							<div class="goodsName">
								商品名称
							</div>
							<div class="goodsDescribeDiv">
								<div class="goodsDescribe">
									商品描述
								</div>
							</div>
						</div>
						<div class="goodsInfoRight">
							<div class="goodsPriceDiv">¥<span class="goodsPrice">111</span></div>
							<div class="goodsCount">X10</div>
						</div>
						<div class="clear"></div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<div class="oneGoods">
				<div class="sellerInfo">
					<div class="goodsSize" style="border-bottom: 1px solid #EBECEE;">
						<span class="nickImg"></span>
						<span>卖家昵称</span>
					</div>
				</div>
				<div class="goodsDiv" >
					<div class="goodsImgDiv">
						<img src="/resources/images/goodsImg.png" class="goodsImg"/>
					</div>
					<div class="goodsInfo">
						<div class="goodsInfoMiddle">
							<div class="goodsName">
								商品名称
							</div>
							<div class="goodsDescribeDiv">
								<div class="goodsDescribe">
									商品描述
								</div>
							</div>
						</div>
						<div class="goodsInfoRight">
							<div class="goodsPriceDiv">¥<span class="goodsPrice">111</span></div>
							<div class="goodsCount">X10</div>
						</div>
						<div class="clear"></div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<div class="peisongType">
			<table cellspacing="0" cellpadding="0" class="peisongDiv">
				<tr>
					<td>
						<div style="margin-top: 3vw;">
							<div class="peisongTypeText">配送方式</div>
							<div class="peisongYunfei">
								<span>￥7.00</span>
							</div>
							<div class="clear"></div>
						</div>
						<div style="text-align: right;padding-bottom: 3vw;">
							<span>快递发货</span>
						</div>
					</td>
					<td class="toNextTd">
						<img src="/resources/images/toNext.png" class="toNextImg"/>
					</td>
				</tr>
			</table>
		</div>
		
		<div class="peisongType" style="margin-top: 0px;">
			<div class="buyerMsgDiv" style="border-bottom:1px solid #EBECEE;">
				<div class="msgText">卖家留言：</div>
				<textarea placeholder="点击给商家留言" class="msgContent"></textarea>
				<div class="clear"></div>
			</div>
		</div>
		<div class="peisongType" style="margin-top: 0px;border-bottom:1px solid #EBECEE;">
			<div class="buyerMsgDiv">
				<div class="msgText">合计</div>
				<div class="goodsTotle">¥118.00</div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="calculateDiv">
			<div class="goodsTotleDiv">
				<div class="goodsTotleText" >商品金额</div>
				<div class="goodsTotle" id="" style="color: #333;margin-right: 3vw;">¥118.00</div>
				<div class="clear"></div>
			</div>
			<div class="yunfeiDiv">
				<div class="goodsTotleText">运费</div>
				<div class="goodsTotle" style="color: #333;margin-right: 3vw;">¥118.00</div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="orderTotleDiv">
			<input type="button" value="提交订单" class="submitOrder" onclick="submitOrder()"/>
			<div class="orderTotle">
				<span>合计：</span><span style="color: #D13600;">¥118.00</span>
			</div>
			<div class="clear"></div>
		</div>
		
		
	<!--弹窗遮罩层-->
		<div class="cover" id="popupCover" style="display: none;" onclick="closePopup()"></div>
	<!--新增收货地址的弹窗-->
		<div class="bottomPopup" id="addressPopup" style="display: none;">
			<input type="hidden"  id="province" name="province" value=""/>
			<input type="hidden"  id="city" name="city" value=""/>
			<input type="hidden"  id="addressDetail" name="addressDetail" value=""/>
			<input type="hidden" value="" class="addType"/>
			<div class="popupTitle">
				<span>新增收货地址</span>
				<img src="/resources/images/cancel.png" class="closeImg" onclick="closePopup()"/>
			</div>
			<form class="addAddressForm">
				<div class="addInfo">
					<div class="addInfoLeft">收货人</div>
					<input type="text" class="addInput" placeholder="姓名" id="name" name="name"/>
				</div>
				<div class="addInfo">
					<div class="addInfoLeft">联系电话</div>
					<input type="text" class="addInput" placeholder="手机号码" id="telephone" name="telephone"/>
				</div>
				<div class="addInfo">
					<div class="addInfoLeft">所在地区</div>
					<button id='showCityPicker'  type='button'>所在地址</button>
				</div>
				<div class="addInfo">
					<div class="addInfoLeft">详细地址</div>
					<input type="text" class="addInput" placeholder="详细地址" id="address" name="addree"/>
				</div>
			</form>
			<div class="sureBtnDiv">
				<button class="sureBtn" onclick="submitForm()">保存</button>
			</div>
		</div>
	<!--选择收货地址的弹窗-->
		<div class="bottomPopup" id="chooseAddressPopup" style="display: none;">
			<div class="popupTitle">
				<span>选择收货地址</span>
				<img src="/resources/images/cancel.png" class="closeImg"/>
			</div>
			<table cellpadding="0" cellspacing="0" class="chooseAddressTb">
				<tbody>
					<tr class="oneAddress">
						<td rowspan="2" class="checkBOxTd" onclick="chooseThis(this)">
							<label class="checkboxDiv">
							  	<input type="checkbox" class="opaction"/>
							</label>
						</td>
						<td style="text-align: left;padding-top: 3vw;" onclick="chooseThis(this)">
							<span class="addressName">夏涵1</span><span class="addressPhone">13813961410</span>
						</td>
						<td class="toNextTd editTd" rowspan="2" onclick="editeAddress(this)">
							<img src="/resources/images/editImg.png" class="editAddress"/>
						</td>
					</tr>
					<tr onclick="chooseThis(this)">
						<td class="addAddressTd">
							<div style="margin-top:5px;">
								<span>收货地址：</span>
								<span class="addressText">
									江苏省南京市雨花台区铁心桥街道1
								</span>
							</div>
						</td>
					</tr>
				</tbody>
				<tbody>
					<tr class="oneAddress">
						<td rowspan="2" class="checkBOxTd" onclick="chooseThis(this)">
							<label class="checkboxDiv">
							  	<input type="checkbox" class="opaction"/>
							</label>
						</td>
						<td style="text-align: left;padding-top: 3vw;" onclick="chooseThis(this)">
							<span class="addressName">夏涵2</span><span class="addressPhone">13813961410</span>
						</td>
						<td class="toNextTd editTd" rowspan="2">
							<img src="/resources/images/editImg.png" class="editAddress"/>
						</td>
					</tr>
					<tr onclick="chooseThis(this)">
						<td class="addAddressTd">
							<div style="margin-top:5px;">
								<span>收货地址：</span>
								<span class="addressText">
									江苏省南京市雨花台区铁心桥街道2
								</span>
							</div>
						</td>
					</tr>
				</tbody>
				<tr class="oneAddress" onclick="addAddress(2)">
					<td class="checkBOxTd" style="padding: 3vw 0;">
						<img src="/resources/images/addIcong.png" style="width: 24px;height: 24px;"/>
					</td>
					<td style="text-align: left;font-size: 14px;">
						<span>新增收货地址</span>
					</td>
					<td class="toNextTd editTd">
						<img src="/resources/images/toNext.png" class="toNextImg"/>
					</td>
				</tr>
			</table>
		</div>
	<!--选择支付方式的弹窗-->
		<div class="bottomPopup" id="choosePayType" style="display: none;font-size: 16px;">
			<div class="popupTitle">
				<span style="color:#7BD339;">微信支付</span>
			</div>
			<div class="popupTitle">
				<span style="color:#049AE3;">支付宝支付</span>
			</div>
			<div class="popupTitle">
				<span>银行卡支付</span>
			</div>
			<div class="popupTitle">
				<span>储蓄卡支付</span>
			</div>
		</div>
	</body>
	<script src="/resources/script/jquery-1.11.1.min.js"></script>
	<script src="/resources/script/info.js"></script>
	<script src="/resources/script/mui.min.js"></script>
	<script src="/resources/script/mui.picker.js"></script>
	<script src="/resources/script/mui.poppicker.js"></script>
	<script src="/resources/script/city.data-3.js" type="text/javascript" charset="utf-8"></script>
	<script src="/resources/script/order/confirmOrder.js"></script>
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
						var input1 = document.getElementById("name");
						input1.blur();
						var input1 = document.getElementById("name");
						input1.blur();
						var input1 = document.getElementById("name");
						input1.blur();
						var input1 = document.getElementById("name");
						input1.blur();
						
						cityPicker.show(function(items) {
							cityResult.innerText =(items[0] || {}).text + " " + (items[1] || {}).text + " " + (items[2] || {}).text;
							document.getElementById("province").value = (items[0] || {}).text;
							document.getElementById("city").value = (items[1] || {}).text;
							document.getElementById("addressDetail").value = (items[2] || {}).text;
							cityResult.style.color="#333333";
						});
					}, false);
				});
			})(mui, document);
	</script>
</html>