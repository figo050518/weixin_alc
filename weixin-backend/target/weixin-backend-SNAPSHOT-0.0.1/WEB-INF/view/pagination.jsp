<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/WEB-INF/view/include.jsp"%>
    
<div class="dataTables_info" id="DataTables_Table_0_info" role="status" aria-live="polite">显示 ${paginationContext.startIndex} 到 ${paginationContext.endIndex} ，共  ${paginationContext.records} 条</div>
<div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
	<c:if test="${paginationContext.showPre}">
		<a class="paginate_button previous disabled" aria-controls="DataTables_Table_0" data-dt-idx="${paginationContext.startPage-1}" tabindex="0" id="DataTables_Table_0_previous" onClick= turnPage('${paginationContext.startPage-1}','${param.formId}')>上一页</a>
	</c:if>
		<c:forEach begin="${paginationContext.startPage}" end="${paginationContext.endPage}" var="pge" step="1">
		<span><a class="paginate_button <c:if test='${paginationContext.pageIndex == pge}'>current</c:if>" aria-controls="DataTables_Table_0" data-dt-idx="${pge}" tabindex="0" onClick= turnPage('${pge}','${param.formId}')>${pge}</a></span>
		</c:forEach>
	<c:if test="${paginationContext.showNext}">
		<a class="paginate_button next disabled" aria-controls="DataTables_Table_0" data-dt-idx="${paginationContext.endPage+1}" tabindex="0" id="DataTables_Table_0_next" onClick= turnPage('${paginationContext.endPage+1}','${param.formId}')>下一页</a>
	</c:if>
</div>

<script >
function turnPage(pindex,fid){
	var f= $("#"+fid);
	f.attr("target","_self");
	var iput = $("#pageIndex");
	if(iput.length  ==0){ 
		iput = $("<input type='hidden' name='pageIndex'>");
		f.append(iput);
	}
	iput.val(pindex);
	f.submit();
}
</script>