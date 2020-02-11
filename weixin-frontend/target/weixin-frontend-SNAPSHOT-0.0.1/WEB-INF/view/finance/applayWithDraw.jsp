<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title></title>
		<link rel="stylesheet" type="text/css" href="/resources/style/lijitixian.css" />
		<link rel="stylesheet" type="text/css" href="/resources/style/info.css" />
		<script src="/resources/script/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/resources/script/info.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<!--提现-->
		<div class="nowDepoit">
			<table class="titleTable" cellspacing="0" cellpadding="0">
				<tr>
					<td style="width: 20%;text-align: left;" onclick="javascript:history.back(-1);"><img src="/resources/images/prev1.png" style="margin-right:5px;"><span>返回</span></td>
					<td style="width: 60%;text-align: center;" class="td">立即提现</td>
					<td style="width: 20%;text-align: left;" onclick="javascript:history.back(-1);"></td>

				</tr>
			</table>
			<div class="nowDepoitCon">
				<form id="withdrawForm" action="/uc/finance/withdrwaApply" method="post" >
				<div class="Conitem">
					<div id="">可提现金额</div>
					<span class="yue">${balance}</span>
				</div>
				<div class="Conitem">
					<div id="">本次提现</div>
					<input type="number" name="withdrawAmount" max="20000.00" id="tixianInput" value="" placeholder="单笔最高2万" />
				</div>
				<div style="width: 100%;background: #f5f5f5;height: 5vw;"></div>
				<div class="Conitem">
					<div id="">提现账户</div>
					<select class="selectCard" name="cardId">
						<option value="">请选择提现账户</option>
						<c:forEach items="${cardList}" var="object">
							<option value="${object.id}">
								<div>${object.bankName}</div><div class="Mleft">尾号（<span>${object.cardNum}</span>）</div>
							</option>
						</c:forEach>
					</select>
				</div>
				 <div class="getCodeDiv" style="margin-top: 5vw;">
							<div>登录手机</div>
							<input type="text" style="width: 30vw;" value="${telephone}" disabled="disabled" name="telephone" class="telephone">
							<input class="getCode" onclick="codeClick()" value="获取验证码" type="button"/>
						</div>
						<div class="bankCardConitem">
							<div>验证码</div>
							<input type="text" class="codeNum" placeholder="请输入验证码" name="code">
						</div> 
				</form>
				<input type="hidden" id="userId" value="${userId}">
				<button class="addzhanghu" onclick="bankCardShow()">添加新银行卡</button>
				<!--选择提现账户弹窗-->
				<div class="overlay">
				</div>
				<div class="bankCard">
					<div class="bankCardTitle">银行卡<img src="/resources/images/icon_close1.png" onclick="bankCardHide()" alt="" />
					</div>
					<div class="bankCardCon">
					<form id="submitForm" method="post">
						<div class="bankCardConitem" style="margin-top: 5vw;">
							<div>银行</div>
							<select name="cardName">
								<option value="">请选择银行</option>
								<option value="中国银行">中国银行</option>
								<option value="中国建设银行">中国建设银行</option>
								<option value="中国农业银行">中国农业银行</option>
								<option value="中国招商银行">中国招商银行</option>
								<option value="中国工商银行">中国工商银行</option>
							</select>
						</div>
						<div class="bankCardConitem">
							<div>银行卡卡号</div>
							<input type="text" class="cardNum" placeholder="填写银行卡卡号" name="cardNumber">
						</div>
						<div class="bankCardConitem">
							<div>姓名</div>
							<input type="text" class="name" placeholder="填写您的姓名" name="ownerName">
						</div>
						<%-- <div class="getCodeDiv" style="margin-top: 5vw;">
							<div>登录手机</div>
							<input type="text" style="width: 30vw;" value="${telephone}" disabled="disabled" name="telephone" class="telephone">
							<input class="getCode" onclick="codeClick()" value="获取验证码" type="button"/>
						</div>
						<div class="bankCardConitem">
							<div>验证码</div>
							<input type="text" class="codeNum" placeholder="请输入验证码" >
						</div> --%>
						</form>
						<button class="wanchengBtn" onclick="wanchengBtn()">完成</button>
					</div>
				</div>
				<button class="btnNow" onclick="btnNow()">立即提现</button>
				<ul class="pointUl">
					<li>提现到账时间为1个工作日</li>
					<li>金额低于1元不可提现，请努力赚钱哟</li>
					<li>提现到账时间规则，
						<a href="">点击查看</a>
					</li>
				</ul>
			</div>
			<div class="bankCardlist">
			</div>
			
			<script src="/resources/script/finance/lijitixian.js" type="text/javascript" charset="utf-8"></script>
	</body>

</html>