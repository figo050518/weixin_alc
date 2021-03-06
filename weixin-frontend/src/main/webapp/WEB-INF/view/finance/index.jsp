<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>小店营收</title>
		<link rel="stylesheet" type="text/css" href="/resources/style/xiaodianyingshou.css" />
		<script src="/resources/script/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/resources/script/finance/index.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<div class="mainPage">
			<table class="titleTable" cellspacing="0" cellpadding="0">
				<tr>
					<td style="width: 20%;text-align: left;" onclick="javascript:history.back(-1);"><img src="/resources/images/prev1.png" style="margin-right:5px;"/><span>返回</span></td>
					<td style="width: 60%;text-align: center;" class="td">小店营收</td>
					<td style="width: 20%;text-align: left;" class="td"></td>
				</tr>
			</table>
			<div class="content">
				<table border="0" cellspacing="0" cellpadding="0"  onclick="TixianShow()">
					<tr>
						<td class="td">
							<span style="display: block;font-size: 12px;line-height: 8vw;">账户余额</span>
							<span style="display: block;line-height: 10vw;">￥<span style="font-size: 18px;">${balance }</span></span>
						</td>
						<td class="td">提现<img src="/resources/images/next.png" alt="" /></td>

					</tr>
				</table>
				<table border="0" cellspacing="0" cellpadding="0" style="margin-top:5vw">
					<tr>
						<td class="td">已提现总额</td>
						<td class="td">${withDrawApplyAmount}</td>
					</tr>
				</table>
				<table border="0" cellspacing="0" cellpadding="0" style="margin-top:5vw" onclick="shouzhidetailShow()">
					<tr>
						<td class="td">收支明细</td>
						<td class="td"><img src="/resources/images/next.png" alt="" /></td>
					</tr>
				</table>
				<table border="0" cellspacing="0" cellpadding="0"  onclick="PresentationRecordShow()">
					<tr>
						<td class="td">提现记录</td>
						<td class="td"><img src="/resources/images/next.png" alt="" /></td>
					</tr>
				</table>
				
				<table border="0" cellspacing="0" cellpadding="0"  onclick="showBankCard()">
					<tr>
						<td class="td">银行卡列表</td>
						<td class="td"><img src="/resources/images/next.png" alt="" /></td>
					</tr>
				</table>
				<p style="font-size: 3.3vw;padding-left: 4vw;color:#666">小店销售收入将显示在这里，已结算收入可以随时申请提现</p>
				<div style="padding-left: 4vw;">
					<a href="">详细规则</a>
					<a href="" style="margin-left: 5vw;;">交易手续费说明</a>
				</div>
			</div>
		</div>
	</body>

</html>