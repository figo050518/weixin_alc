<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<script></script>
<%@include file="/WEB-INF/view/include.jsp"%>
<%@include file="/WEB-INF/view/header.jsp"%>
<title>商品详情</title>
<meta name="keywords" content="">
<meta name="description" content="">
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-article-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>商品名称：</label>
			<div class="formControls col-xs-8 col-sm-2">
			${productPO.proName }
			</div>
			<label class="form-label col-xs-4 col-sm-2">上下架：</label>
			<div class="formControls col-xs-8 col-sm-2"> 
			  <c:if test="${productPO.upState =='0'}" >
						               下架
			  </c:if>
			  <c:if test="${productPO.upState =='1'}" >
			   	    上架
			  </c:if>
			</div>
			<label class="form-label col-xs-4 col-sm-2">上/下架时间：</label>
			<div class="formControls col-xs-8 col-sm-2"> 
				<fmt:formatDate value="${productPO.upTime}" type="date"  pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-2">添加人：</label>
			<div class="formControls col-xs-8 col-sm-2">
				${productPO.createName}
			</div>
			<label class="form-label col-xs-4 col-sm-2">创建时间：</label>
			<div class="formControls col-xs-8 col-sm-2">
				<fmt:formatDate value="${productPO.createTime}" type="date"  pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
			<label class="form-label col-xs-4 col-sm-2">分组：</label>
			<div class="formControls col-xs-8 col-sm-2">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">来源：</label>
			<div class="formControls col-xs-8 col-sm-2">
				   <c:if test="${productPO.fromType =='1'}" >
						   自营
				   </c:if>
				    <c:if test="${productPO.fromType =='2'}" >
				   		 平台上架
				   </c:if>
			</div>
			<label class="form-label col-xs-4 col-sm-2">非常购商品的类型：</label>
			<div class="formControls col-xs-8 col-sm-2">
				  <c:if test="${productPO.fcgProductType =='1'}" >
						                  一般贸易
				   </c:if>
				    <c:if test="${productPO.fcgProductType =='2'}" >
				                 跨境保税 
				   </c:if>
				   <c:if test="${productPO.fcgProductType =='3'}" >
				                 海外直邮
				   </c:if>
			</div>
			<label class="form-label col-xs-4 col-sm-2">商品的运费：</label>
			<div class="formControls col-xs-8 col-sm-2">
				  ${productPO.freight}
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">商品详细描述：</label>
			<div class="formControls col-xs-8 col-sm-10">
				${productPO.productDesc}
			</div>
		</div>
		<div class="row cl">
			
			
			<label class="form-label col-xs-4 col-sm-2">最高价：</label>
			<div class="formControls col-xs-8 col-sm-2 skin-minimal">
				${productPO.maxPrice}
			</div>
			<label class="form-label col-xs-4 col-sm-2">最低价：</label>
			<div class="formControls col-xs-8 col-sm-2">
				${productPO.minPrice}
			</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">售卖数量：</label>
			<div class="formControls col-xs-8 col-sm-2">
				${productPO.salesCount}
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">商品规格：</label>
			<div class="formControls col-xs-8 col-sm-10 skin-minimal">
				<c:forEach var="productSpecPO" items="${productSpecPOs}" varStatus="status" >
					${status.index}.规格名称:${productSpecPO.specName} 库存:${productSpecPO.stock} 库存:${productSpecPO.originalPrice} 涨幅:${productSpecPO.amountIncrease}<br/>
				</c:forEach>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">商品图片：</label>
			<div class="formControls col-xs-8 col-sm-10">
				<c:forEach var="productImagePO" items="${productImagePOs}" varStatus="status" >
					<img src="${productImagePO.imgUrl}" onerror="${pageContext.request.contextPath}/resources/images/none.png" width="60px" height="40px"/>
				</c:forEach>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-12 col-xs-offset-4 col-sm-offset-6">
				<button onClick="layerClose();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</article>
<%@include file="/WEB-INF/view/footer.jsp"%>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
});
function layerClose(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
