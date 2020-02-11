$(document).ready(function() {
    	$("#inputForm").validate({
            submitHandler: function(form){
            	checkfile();
            	if(!fileFlage){
            		return;
            	}
            	  var shopName = document.getElementById("shopName");  
                  if(shopName==""|| shopName.length>0){  
                      alert("店铺名称不能为空");  
                      return;  
                  }  
                  var shopDesc = document.getElementById("shopDesc");  
                  if(shopDesc==""|| shopDesc.length>0){  
                      alert("店铺简介不能为空");  
                      return;  
                  }  
              if (confirm("确认创建店铺吗?")) {
              	form.submit();
              }
            },
            errorContainer: "#messageBox",
            errorPlacement: function(error, element) {
            }
          });
	
    })