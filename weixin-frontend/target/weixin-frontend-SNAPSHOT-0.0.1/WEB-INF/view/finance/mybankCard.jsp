<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
		<title>我的银行卡</title>
		<link rel="stylesheet" type="text/css" href="/resources/style/mybankCard.css"/>
	</head>
	<body>
		<div class="head">
			<div class="title">
				<span>我的银行卡</span>
			</div> 
			<div class="return" onclick="history.go(-1)">
				<span class="returnImg"></span><span>返回</span>
			</div>
			<div class="addCard">+</div>
		</div>
		<div class="cardList">
			<c:forEach items="${cardList}" var="object">
			<table class="oneCard" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="tdLeft">
					<div class="cardName">
						<div class="cardName1">
							${object.bankName}
						</div>
						<div class="cardName2">储蓄卡</div>
					</div>
					
				</td>
				<!-- <td class="tdRight">
					<div class="kjzfdiv">
						<img src="img/kuaijiezhifu.png"/>
					</div>
					
				</td> -->
			</tr>
			<tr>
				<td colspan="2" class="cardNum">**** **** **** ${object.cardNum }</td>
			</tr>
		</table>
		</c:forEach>
		</div>
		
	</body>
</html>
