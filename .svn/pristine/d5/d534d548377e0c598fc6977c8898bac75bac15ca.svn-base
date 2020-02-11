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
<title>订单详情</title>
<meta name="keywords" content="">
<meta name="description" content="">
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-article-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>订单编号：</label>
			<div class="formControls col-xs-8 col-sm-2">
			${orderInfoPO.orderNum }
			</div>
			<label class="form-label col-xs-4 col-sm-2">创建时间：</label>
			<div class="formControls col-xs-8 col-sm-2">
				<fmt:formatDate value="${orderInfoPO.createTime}" type="date"  pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
			<label class="form-label col-xs-4 col-sm-2">订单状态：</label>
			<div class="formControls col-xs-8 col-sm-2"> 
				<c:forEach items="${orderStates}" var="orderState">
				 <c:if test="${orderInfoPO.orderState == orderState.key}"> ${orderState.value}</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">支付方式：</label>
			<div class="formControls col-xs-8 col-sm-2"> 
				 ${orderInfoPO.payWay}
			</div>
			<label class="form-label col-xs-4 col-sm-2">支付时间：</label>
			<div class="formControls col-xs-8 col-sm-2">
				 <fmt:formatDate value="${orderInfoPO.payTime}" type="date"  pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
			<label class="form-label col-xs-4 col-sm-2">发货时间：</label>
			<div class="formControls col-xs-8 col-sm-2">
				 <fmt:formatDate value="${orderInfoPO.sendTime}" type="date"  pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">买家ID：</label>
			<div class="formControls col-xs-8 col-sm-2">
				 ${orderInfoPO.userId}
			</div>
			<label class="form-label col-xs-4 col-sm-2">卖家ID：</label>
			<div class="formControls col-xs-8 col-sm-2">
				  ${orderInfoPO.sellerId}
			</div>
			<label class="form-label col-xs-4 col-sm-2">店铺ID：</label>
			<div class="formControls col-xs-8 col-sm-2 skin-minimal">
				${orderInfoPO.shopId}
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">实际支付金额：</label>
			<div class="formControls col-xs-8 col-sm-2">
				${orderInfoPO.actPayAmount}
			</div>
			<label class="form-label col-xs-4 col-sm-2">订单运费：</label>
			<div class="formControls col-xs-8 col-sm-2">
				${orderInfoPO.freightAmount}
			</div>
			<label class="form-label col-xs-4 col-sm-2">商品总额：</label>
			<div class="formControls col-xs-8 col-sm-2 skin-minimal">
				${orderInfoPO.proAmount}
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">商品折扣：</label>
			<div class="formControls col-xs-8 col-sm-2">
				${orderInfoPO.ordDiscount}
			</div>
			<label class="form-label col-xs-4 col-sm-2">物流单号：</label>
			<div class="formControls col-xs-8 col-sm-2">
				${orderInfoPO.logisticsNum}
			</div>
			<label class="form-label col-xs-4 col-sm-2">物流公司：</label>
			<div class="formControls col-xs-8 col-sm-2">
				${orderInfoPO.logisticsComp}
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">买家备注：</label>
			<div class="formControls col-xs-8 col-sm-9">
				${orderInfoPO.sellerRemarks}
			</div>
		</div>	
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">评价与否：</label>
			<div class="formControls col-xs-8 col-sm-2">
				<c:if test="${orderInfoPO.isEaluate =='0'}" >
			                未评价
			    </c:if>
			    <c:if test="${orderInfoPO.isEaluate =='1'}" >
			   	    已评价
			   </c:if>
			</div>
			<label class="form-label col-xs-4 col-sm-2">订单完成时间：</label>
			<div class="formControls col-xs-8 col-sm-2">
				<fmt:formatDate value="${orderInfoPO.finishTime}" type="date"  pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
			<label class="form-label col-xs-4 col-sm-2">订单取消时间：</label>
			<div class="formControls col-xs-8 col-sm-2">
				<fmt:formatDate value="${orderInfoPO.cancelTime}" type="date"  pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">添加人员：</label>
			<div class="formControls col-xs-8 col-sm-2">
				${orderInfoPO.createName}
			</div>
			<label class="form-label col-xs-4 col-sm-2">删除人员：</label>
			<div class="formControls col-xs-8 col-sm-2">
				${orderInfoPO.delName}
			</div>
			<label class="form-label col-xs-4 col-sm-2">删除时间：</label>
			<div class="formControls col-xs-8 col-sm-2">
				${orderInfoPO.delTime}
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">订单类型：</label>
			<div class="formControls col-xs-8 col-sm-2">
				<c:if test="${orderInfoPO.orderType =='1'}" >
					 自营订单
			    </c:if>
			    <c:if test="${orderInfoPO.orderType =='2'}" >
			   	             非常购订单
			   </c:if>
			</div>
			<label class="form-label col-xs-4 col-sm-2">取消类型：</label>
			<div class="formControls col-xs-8 col-sm-2">
				<c:if test="${orderInfoPO.cancelType =='1'}" >
					用户自己取消
			    </c:if>
			    <c:if test="${data.orderType =='2'}" >
			   	           卖家取消
			    </c:if>
			    <c:if test="${data.orderType =='3'}" >
			   	          系统取消
			    </c:if>
			</div>
			<label class="form-label col-xs-4 col-sm-2"></label>
			<div class="formControls col-xs-8 col-sm-2">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">订单关闭原因：</label>
			<div class="formControls col-xs-8 col-sm-9">
				${orderInfoPO.ordDiscount}
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">买家昵称：</label>
			<div class="formControls col-xs-8 col-sm-3">
				${buyerInfo.nikeName}
			</div>
			<label class="form-label col-xs-4 col-sm-3">买家手机：</label>
			<div class="formControls col-xs-8 col-sm-3">
				${buyerInfo.telNum}
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">卖家昵称：</label>
			<div class="formControls col-xs-8 col-sm-3">
				${sellerInfo.nikeName}
			</div>
			<label class="form-label col-xs-4 col-sm-3">卖家手机：</label>
			<div class="formControls col-xs-8 col-sm-3">
				${sellerInfo.telNum}
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">收货人姓名：</label>
			<div class="formControls col-xs-8 col-sm-3">
				${orderReceiverAddressPO.receiverName}
			</div>
			<label class="form-label col-xs-4 col-sm-3"> 收货人手机：</label>
			<div class="formControls col-xs-8 col-sm-3">
				${orderReceiverAddressPO.contactNum}
			</div>
		</div>
			<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">收货人地址：</label>
			<div class="formControls col-xs-8 col-sm-9">
				${orderReceiverAddressPO.province} ${orderReceiverAddressPO.city} ${orderReceiverAddressPO.area} ${orderReceiverAddressPO.detailAddr} 
			</div>
		</div>
		<div class="row_cl">
			<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper no-footer">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="80">商品名称</th>
							<th width="80">图片</th>
							<th width="80">商品数量</th>
							<th width="80">规格名称</th>
							<th width="90">商品单价</th>
							<th width="80">实际售卖价格</th>
							<th width="80">商品总金额</th>
							<th width="80">退货状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${orderItemsDTOs}">
							<tr class="text-c">
								<td>${item.orderItemPO.productName}</td>
								<td> ${item.orderItemPO.productQuant}
									<img width="60px" height="40px" src="${item.imageUrl}" onerror="${pageContext.request.contextPath}/resources/images/none.png">
								</td>
								<td> ${item.orderItemPO.productQuant}</td>
								<td> ${item.orderItemPO.productSpec}</td>
								<td> ${item.orderItemPO.productPrice}</td>
								<td> ${item.orderItemPO.actualSalePrice}</td>
								<td> ${item.orderItemPO.productTotalAmount}</td>
								<td> 
									 <c:if test="${item.orderItemPO.returnState=='0'}">未退货</c:if>
								     <c:if test="${item.orderItemPO.returnState=='1'}">退货</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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
