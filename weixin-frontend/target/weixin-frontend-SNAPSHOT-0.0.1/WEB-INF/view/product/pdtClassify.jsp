<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>商品分类</title>
    <link rel="stylesheet" type="text/css" href="/resources/style/pdtClassify.css"/>
   		
    </script>
</head>
<body>
	<div class="head">
		<div class="title">
			<span class="pdt">小店商品</span><span class="pdtClassify">商品分类</span>
		</div> 
		<div class="return" onclick="history.go(-1)">
			<span class="returnImg"></span><span>è¿å</span>
		</div>
	</div>
	<div class="content">
		<div class="contentItem">
			<div class="itemText">厨房用品</div>
			<div class="itemImg"><img src="img/next1.png"/></div>
			<div class="itemNum">2</div>
			
		</div>
		<div class="contentItem">
			<div class="itemText">卫浴用品</div>
			<div class="itemImg"><img src="img/next1.png"/></div>
			<div class="itemNum">0</div>
		</div>
		<div class="contentItem">
			<div class="itemText">灯具</div>
			<div class="itemImg"><img src="img/next1.png"/></div>
			<div class="itemNum">0</div>
		</div>
		<div class="contentItem">
			<div class="itemText">墙纸</div>
			<div class="itemImg"><img src="img/next1.png"/></div>
			<div class="itemNum">0</div>
		</div>
	</div>
	<div class="footerbtn">
		<button class="addClassify">添加分类</button>
		<button class="batchClassify">批量管理</button>
	</div>
</body>
</html>