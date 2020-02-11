<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title></title>
		<link rel="stylesheet" type="text/css" href="/resources/style/tixianjilu.css"/>
		<link rel="stylesheet" type="text/css" href="/resources/style/mui.min.css" />
		<link rel="stylesheet" href="/resources/style/info.css">
	</head>
	<body>
		<div class="head">
			<div class="title">
				<span>提现记录</span>
			</div> 
			<div class="return" onclick="history.go(-1)">
				<span class="returnImg"></span><span>返回</span>
			</div>
		</div>
		<table class="TXtable" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				<td style="width:50vw">时间</td>
				<td style="width:25vw">金额</td>
				<td style="width:25vw">状态</td></tr>
		</table>
		<div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="margin-top: calc(50px + 10vw);">
			<div class="mui-scroll">
				<!--数据列表-->
				<ul class="mui-table-view mui-table-view-chevron" style="background:#FFFFFF;border:0px;" id="goodsList">
					<!-- <div class="oneDrawApply">
						<div class="time">
							<span>2017-03-30 8:23</span>
						</div>
						<div class="price">
							<span>¥99.00</span>
						</div>
						<div class="status">
							<span>提现中</span>
						</div>
						<div class="clear"></div>
					</div> -->
				</ul>
			</div>
		</div>
		<script src="/resources/script/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/resources/script/mui.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/resources/script/info.js"></script>
		<script src="/resources/script/finance/withDrawApplyList.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
