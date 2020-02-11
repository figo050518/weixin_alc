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
<title>运营人员列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 运营人员列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<form id="searchForm" action="${pageContext.request.contextPath}/oper/operList.do" method="post" >
    <div class="text-c">
		<input type="text" name="operName" id="operName" placeholder="姓名、手机号、邮箱" style="width:250px" class="input-text" value="${seachInfo.operName}">
		<button name="" id="" class="btn btn-success" type="submit"onclick="search()" ><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
	</div>
</form>
<div class="cl pd-5 bg-1 bk-gray mt-20">
 <span class="l">
 <a class="btn btn-primary radius" data-title="添加人员" data-href="" onclick="toOperInfoAdd()" href="javascript:;">
 <i class="Hui-iconfont">&#xe600;</i> 添加人员</a>
 </span> 
 </div>
 	<div class="mt-20">
	  <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper no-footer">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="170">操作</th>
					<th width="80">ID</th>
					<th width="80">姓名</th>
					<th width="80">状态</th>
					<th width="80">创建人</th>
					<th width="80">创建时间</th>
					<th width="80">邮箱</th>
					<th width="80">手机</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="data" items="${paginationContext.datas}">
					<tr class="text-c">
						<td>
						   <c:choose>
							    <c:when test="${data.isDelete == '1'}">
							    	<a style="text-decoration:none" class="btn btn-link" onClick="operInfoActive('${data.operId}','0')" href="javascript:;" title="禁用">启用</a> 
							    </c:when>
							    <c:otherwise>
							    	<a style="text-decoration:none" class="btn btn-link" onClick="operInfoActive('${data.operId}','1')" href="javascript:;" title="启用">禁用</a>
							    </c:otherwise>
						   </c:choose>
						   	<a style="text-decoration:none" class="btn btn-link" onClick="operInfoDetail('${data.operId}')" href="javascript:;" title="查看">查看</a>
						   	<a style="text-decoration:none" class="btn btn-link" onClick="resetPassword('${data.operId}')" href="javascript:;" title="重置密码">重置密码</a>
						</td>
						<td>${data.operId}</td>
						<td>${data.operName}</td>
						<td>
						   <c:if test="${data.isDelete =='1'}" >
						   		 禁用
						   </c:if>
						    <c:if test="${data.isDelete =='0'}" >
						  		  启用
						   </c:if>
						</td>
						<td>${data.createName}</td>
						<td>
							<fmt:formatDate value="${data.createTime}" type="date"  pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>${data.operEmail}</td>
						<td>${data.telNum}</td>
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

<!--请在下方写此页面业务相关的脚本-->

<script type="text/javascript">


/*资讯-添加*/
function operInfoActive(id,type){
	$.ajax({
	        type:'post',
	        url:"/oper/operInfoActive.do",
	        data:{operId:id,isDelete:type},
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
function resetPassword(id){
	layer_show("修改密码","${pageContext.request.contextPath}/oper/toResetPassword.do?operId="+id,"600","300");
}

function search(){
	$("#searchForm").submit();
}
function operInfoDetail(id){
	var index = layer.open({
		type: 2,
		title: "人员详情",
		content: "${pageContext.request.contextPath}/oper/operDetail.do?operId="+id
	});
	layer.full(index);
}
function toOperInfoAdd(){
	var index = layer.open({
		type: 2,
		title: "增加人员",
		content: "${pageContext.request.contextPath}/oper/toOperInfoAdd.do?t="+Date.parse(new Date())

	});
	layer.full(index);
	
}
</script> 
</body>
</html>
