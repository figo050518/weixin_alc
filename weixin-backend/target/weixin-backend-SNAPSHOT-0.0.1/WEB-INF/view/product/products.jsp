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
<title>商品列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品管理 <span class="c-gray en">&gt;</span> 商品列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<form id="searchForm" action="${pageContext.request.contextPath}/product/pdtProductList.do" method="post" >
    <div class="text-c">
		 <span class="select-box inline">
			<select name="upState" class="select">
				<option value="" >上/下架</option>
				<option value="0" <c:if test="${seachInfo.upState == '0'}"> selected</c:if> >下架</option>
				<option value="1" <c:if test="${seachInfo.upState == '1'}"> selected</c:if> >上架</option>
			</select>
		</span>
	    <span class="select-box inline">
			<select name="fromType" class="select">
				<option value="" >全部来源</option>
				<option value="1" <c:if test="${seachInfo.fromType == '1'}"> selected</c:if> >自营</option>
				<option value="2" <c:if test="${seachInfo.fromType == '2'}"> selected</c:if> >平台上架</option>
			</select>
		</span>
		<span class="select-box inline">
			<select name="fromType" class="select">
				<option value="" >全部分组</option>
				<c:forEach var="group" items="${productGroups}">
				   <option value="${group.id}" <c:if test="${seachInfo.groupId == group.id}"> selected</c:if> >${group.groupName}</option>
				</c:forEach>
			</select>
		</span>
		<input type="text" name="proName" id="proName" placeholder="商品名称" style="width:250px" class="input-text" value="${seachInfo.proName}">
		<button name="" id="" class="btn btn-success" type="submit"onclick="search()" ><i class="Hui-iconfont">&#xe665;</i> 搜商品</button>
	</div>
</form>
<!-- 	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" data-title="添加资讯" data-href="article-add.html" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加资讯</a></span> <span class="r">共有数据：<strong>54</strong> 条</span> </div> -->
	<div class="mt-20">
	  <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper no-footer">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="80">操作</th>
					<th width="80">商品名称</th>
					<th width="80">上/下架</th>
					<th width="90">上/下架时间</th>
					<th width="80">来源</th>
					<th width="80">非常购商品的类型</th>
					<th width="80">最高价</th>
					<th width="80">最低价</th>
					<th width="80">售卖数量</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${products}">
					<tr class="text-c">
						<td>
						    <c:choose>
							    <c:when test="${product.upState == '1'}">
							    	<a style="text-decoration:none" class="btn btn-link" onClick="productActive('${product.id}','0')" href="javascript:;" title="下架">下架</a> 
							    </c:when>
							    <c:otherwise>
							    	<a style="text-decoration:none" class="btn btn-link" onClick="productActive('${product.id}','1')" href="javascript:;" title="上架">上架</a>
							    </c:otherwise>
						   </c:choose>
						    <a style="text-decoration:none" class="btn btn-link" onClick="productDetail('${product.id}')" href="javascript:;" title="查看">查看</a>
						</td>
						<td>${product.proName}</td>
						<td>
						   <c:if test="${product.upState =='0'}" >
						               下架
						   </c:if>
						    <c:if test="${product.upState =='1'}" >
						   	    上架
						   </c:if>
						</td>
						<td>
							<fmt:formatDate value="${product.upTime}" type="date"  pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
						   <c:if test="${product.fromType =='1'}" >
						   自营
						   </c:if>
						    <c:if test="${product.fromType =='2'}" >
						    平台上架
						   </c:if>
						</td>
						<td>
						   <c:if test="${product.fcgProductType =='1'}" >
						                  一般贸易
						   </c:if>
						    <c:if test="${product.fcgProductType =='2'}" >
						                 跨境保税 
						   </c:if>
						   <c:if test="${product.fcgProductType =='3'}" >
						                 海外直邮
						   </c:if>
						</td>
						<td>${product.maxPrice}</td>
						<td>${product.minPrice}</td>
						<td>${product.salesCount}</td>
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

function productActive(id,type){
	$.ajax({
	        type:'post',
	        url:"/product/productActive.do",
	        data:{id:id,upState:type},
	        async: false,
	        success: function(result) {
	    	 	var json = eval("("+ result +")");
	    	 	if(json.resultCode != "SUCCESS"){
	    	 		layer.msg(json.resultMsg,{icon:2,time:1000});
	    	 		return;
				}
	    	 	search();
	        }
		});
}
function productDetail(id){
	var index = layer.open({
		type: 2,
		title: "商品详情",
		content: "${pageContext.request.contextPath}/product/pdtProductdeail.do?id="+id
	});
	layer.full(index);
}
function search(){
	$("#searchForm").submit();
}
</script> 
</body>
</html>
