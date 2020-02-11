<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title></title>
		<link rel="stylesheet" type="text/css" href="/resources/style/shouzhimingxi.css"/>
		<link rel="stylesheet" type="text/css" href="/resources/style/mui.min.css" />
		<link rel="stylesheet" href="/resources/style/info.css">
	</head>
	<body>
		<!--收支明细-->
		<!-- <div class="shouzhidetail">
			<table class="titleTable" cellspacing="0" cellpadding="0">
				<tr>
					<td style="width: 20%;text-align: left;" onclick="javascript:history.back(-1);"><img src="/resources/images/prev1.png" style="margin-right:5px;"><span>返回</span></td>
					<td style="width: 60%;text-align: center;" class="td">收支明细</td>
					<td style="width: 20%;text-align: left;" class="td"></td>
				</tr>
			</table>
			<table class="SZtable" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr><th>时间</th><th>流水</th><th>类型</th></tr>
				<tr><td width="34%">2017-03-30 8:23</td><td width="33%">+￥78.88</td><td width="33%">平台订单</td></tr>
				<tr><td width="34%">2017-03-30 7:55</td><td width="33%">-￥66.66</td><td width="33%">提现</td></tr>
				<tr><td width="34%">2017-03-30 7:53</td><td width="33%">+￥78.88</td><td width="33%">自营订单</td></tr>
				<tr><td width="34%">2017-03-30 7:33</td><td width="33%">-￥66.66</td><td width="33%">提现</td></tr>
				<tr><td width="34%">2017-03-30 7:03</td><td width="33%">+￥78.88</td><td width="33%">收入</td></tr>
				<tr><td width="34%">2017-03-30 7:00</td><td width="33%">-￥66.66</td><td width="33%">提现</td></tr>
			</table>
		</div> -->
		<div class="head">
			<div class="title">
				<span>收支明细</span>
			</div> 
			<div class="return" onclick="javascript:history.back(-1);">
				<span class="returnImg"></span><span>返回</span>
			</div>
		</div>
		<table class="TXtable" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				<td style="width:50vw">时间</td>
				<td style="width:25vw">流水</td>
				<td style="width:25vw">类型</td></tr>
		</table>
		<div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="margin-top: calc(50px + 10vw);">
			<div class="mui-scroll">
				<!--数据列表-->
				<ul class="mui-table-view mui-table-view-chevron" style="background:#FFFFFF;border:0px;" id="goodsList">
				</ul>
			</div>
		</div>
		<script src="/resources/script/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/resources/script/mui.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/resources/script/info.js"></script>
		<script src="/resources/script/finance/transactionList.js" type="text/javascript" charset="utf-8"></script>
		<script>
			$(".tableQH td").click(function(){
				$(this).addClass("on");
				$(this).siblings().removeClass("on");
			})
		</script>
	</body>
</html>
