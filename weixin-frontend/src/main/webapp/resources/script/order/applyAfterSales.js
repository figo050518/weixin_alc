/*点击选取默认地址*/
$(".GoodsCheck").click(function() {
     /*$(this).parent().parent().parent().parent().attr("id")*/   //最外层的id,把最外层的id社会为地址的id
     if($(this).is(':checked')){
     	$(".GoodsCheck").prop('checked', false);
     	$(this).prop('checked', true);
     }
  });
  
function submitGrade(){
	alert($(".popup").find('input[type=checkbox]:checked').val());
	if($(".popup").find('input[type=checkbox]:checked').val()==null){
		alert("请选择拒绝理由！");
	}
}
