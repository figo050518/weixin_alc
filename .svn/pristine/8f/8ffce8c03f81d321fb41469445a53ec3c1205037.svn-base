<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@include file="/WEB-INF/view/include.jsp"%>
<%@include file="/WEB-INF/view/header.jsp"%>
<title>订单列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 订单管理 <span class="c-gray en">&gt;</span> 订单列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<form id="searchForm" action="${pageContext.request.contextPath}/order/orderList.do" method="post" >
    <div class="text-c">
	    <span class="select-box inline">
			<select name="orderState" class="select">
				<option value="" >全部状态</option>
				<c:forEach items="${orderStates}" var="orderState">
				  <option value="${orderState.key}" <c:if test="${seachInfo.orderState == orderState.key}"> selected</c:if> >${orderState.value}</option>
				</c:forEach>
			</select>
		</span>
		<span class="select-box inline">
			<select name="orderType" class="select">
				<option value="" >全部类型</option>
				<option value="1" <c:if test="${seachInfo.orderType == '1'}"> selected</c:if> >自营订单</option>
				<option value="2" <c:if test="${seachInfo.orderType == '2'}"> selected</c:if> >非常购订单</option>
			</select>
		</span>
		
		<input type="text" name="logisticsNum" id="logisticsNum" placeholder="物流单号" style="width:250px" class="input-text" value="${seachInfo.logisticsNum}">
		<input type="text" name="orderNum" id="orderNum" placeholder="订单单号" style="width:250px" class="input-text" value="${seachInfo.orderNum}">
		<button name="" id="" class="btn btn-success" type="submit"onclick="search()" ><i class="Hui-iconfont">&#xe665;</i> 搜订单</button>
	</div>
</form>
<!-- 	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" data-title="添加资讯" data-href="article-add.html" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加资讯</a></span> <span class="r">共有数据：<strong>54</strong> 条</span> </div> -->
	<div class="mt-20">
	  <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper no-footer">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="80">操作</th>
					<th width="80">订单ID</th>
					<th width="80">创建时间</th>
					<th width="90">订单状态</th>
					<th width="80">支付方式</th>
					<th width="80">支付时间</th>
					<th width="80">发货时间</th>
					<th width="80">买家ID</th>
					<th width="80">卖家ID</th>
					<th width="80">实际支付金额</th>
					<th width="80">订单运费</th>
					<th width="80">商品总额</th>
					<th width="80">折扣</th>
					<th width="80">物流单号</th>
					<th width="80">物流公司</th>
					<th width="80">订单类型</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="data" items="${paginationContext.datas}">
					<tr class="text-c">
						<td>
							<a style="text-decoration:none" class="btn btn-link" onClick="orderDetail('${data.id}')" href="javascript:;" title="查看">查看</a>
						</td>
						<td>${data.orderNum}</td>
						<td>
						   <fmt:formatDate value="${data.createTime}" type="date"  pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<c:forEach items="${orderStates}" var="orderState">
							 <c:if test="${data.orderState == orderState.key}"> ${orderState.value}</c:if>
							</c:forEach>
						</td>
						<td>
						   ${data.payWay}
						</td>
						<td>
						   <fmt:formatDate value="${data.payTime}" type="date"  pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
						   <fmt:formatDate value="${data.sendTime}" type="date"  pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
						   ${data.userId}
						</td>
						<td>
						   ${data.sellerId}
						</td>
						<td>
						   ${data.actPayAmount}
						</td>
						<td>
						   ${data.freightAmount}
						</td>
						<td>
						   ${data.proAmount}
						</td>
						<td>
						   ${data.ordDiscount}
						</td>
						<td>
						   ${data.logisticsNum}
						</td>
						<td>
						   ${data.logisticsComp}
						</td>
						<td>
							<c:if test="${data.orderType =='1'}" >
						                自营订单
						   </c:if>
						    <c:if test="${data.orderType =='2'}" >
						   	    非常购订单
						   </c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:import url="/WEB-INF/view/pagination.jsp">
		   <c:param name="formId" value="searchForm"/>
		</c:import>
	  </div>
	</div>
</div>
<%@include file="/WEB-INF/view/footer.jsp"%>

<script type="text/javascript">

function orderDetail(id){
	var index = layer.open({
		type: 2,
		title: "订单详情",
		content: "${pageContext.request.contextPath}/order/orderDetail.do?id="+id
	});
	layer.full(index);
}
function search(){
	$("#searchForm").submit();
}
</script> 
</body>
</html>
