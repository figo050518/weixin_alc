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
<title>用户列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户管理 <span class="c-gray en">&gt;</span> 用户列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<form id="searchForm" action="${pageContext.request.contextPath}/user/listUsers.do" method="post" >
    <div class="text-c">
		 <span class="select-box inline">
			<select name="userType" class="select">
				<option value="" >用户角色</option>
				<option value="1" <c:if test="${seachInfo.userType == '1'}"> selected</c:if> >买家</option>
				<option value="2" <c:if test="${seachInfo.userType == '2'}"> selected</c:if> >卖家</option>
			</select>
		</span>
		<input type="text" name="nikeName" id="nikeName" placeholder="用户昵称" style="width:250px" class="input-text" value="${seachInfo.nikeName}">
		<input type="text" name="telNum" id="telNum" placeholder="手机号" style="width:250px" class="input-text" value="${seachInfo.telNum}">
		<button name="" id="" class="btn btn-success" type="submit"onclick="search()" ><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
	</div>
</form>
<!-- 	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" data-title="添加资讯" data-href="article-add.html" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加资讯</a></span> <span class="r">共有数据：<strong>54</strong> 条</span> </div> -->
	<div class="mt-20">
	  <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper no-footer">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="80">操作</th>
					<th width="80">微信ID</th>
					<th width="80">用户昵称</th>
					<th width="80">手机号</th>
					<th width="80">用户角色</th>
					<th width="80">非常购商户门店用户ID</th>
					<th width="80">状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${userInfos}">
					<tr class="text-c">
						<td>
						   <c:choose>
							    <c:when test="${user.isDelete == '1'}">
							    	<a style="text-decoration:none" class="btn btn-link" onClick="userActive('${user.id}','0')" href="javascript:;" title="禁用">禁用</a> 
							    </c:when>
							    <c:otherwise>
							    	<a style="text-decoration:none" class="btn btn-link" onClick="userActive('${user.id}','1')" href="javascript:;" title="启用">启用</a>
							    </c:otherwise>
						   </c:choose>
						</td>
						<td>${user.wxId}</td>
						<td>${user.nikeName}</td>
						<td>${user.telNum}</td>
						<td>
						   <c:if test="${user.userType =='1'}" >
						   买家
						   </c:if>
						    <c:if test="${user.userType =='2'}" >
						    卖家
						   </c:if>
						</td>
						<td>${user.fcgSellerId}</td>
						<td>
						   <c:if test="${user.isDelete =='1'}" >
						 			  启用
						   </c:if>
						    <c:if test="${user.isDelete =='0'}" >
						                                    禁用
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

<!--请在下方写此页面业务相关的脚本-->

<script type="text/javascript">


/*资讯-添加*/
function userActive(id,type){
	$.ajax({
	        type:'post',
	        url:"/oper/userActive.do",
	        data:{id:id,isDelete:type},
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
function search(){
	$("#searchForm").submit();
}
</script> 
</body>
</html>
