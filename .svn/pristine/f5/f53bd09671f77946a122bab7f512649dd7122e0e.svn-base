$(document).ready(function() {
    	var maxsize = 500*1024;//500K 
        var errMsg = "上传的附件文件不能超过500K！！！";  
        var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过500K，建议使用IE、FireFox、Chrome浏览器。";  
        var  browserCfg = {};  
        var ua = window.navigator.userAgent;  
        if (ua.indexOf("MSIE")>=1){  
            browserCfg.ie = true;  
        }else if(ua.indexOf("Firefox")>=1){  
            browserCfg.firefox = true;  
        }else if(ua.indexOf("Chrome")>=1){  
            browserCfg.chrome = true;  
        }  
    	function checkfile(){  
            try{  
                var obj_file = document.getElementById("file");  
                if(obj_file.value==""){  
                    alert("请先选择店铺头像");  
                    return;  
                }  
                var filesize = 0;  
                if(browserCfg.firefox || browserCfg.chrome ){  
                    filesize = obj_file.files[0].size;  
                }else if(browserCfg.ie){  
                    var obj_img = document.getElementById('tempimg');  
                    obj_img.dynsrc=obj_file.value;  
                    filesize = obj_img.fileSize;  
                }else{  
                	fileFlage =false;
                    alert(tipMsg);  
                return;  
                }  
                if(filesize==-1){  
                    alert(tipMsg);  
                    fileFlag =false;
                    return;  
                }else if(filesize>maxsize){  
                    alert(errMsg);  
                    fileFlag =false;
                    return;  
                }else{  
                    fileFlag =true;
                    return;  
                }  
            }catch(e){  
                alert(e);  
            }  
        } 
    	function checkfile2(){  
            try{  
                var obj_file = document.getElementById("file2");  
                if(obj_file.value==""){  
                    alert("请先选择背景头像");  
                    return;  
                }  
                var filesize = 0;  
                if(browserCfg.firefox || browserCfg.chrome ){  
                    filesize = obj_file.files[0].size;  
                }else if(browserCfg.ie){  
                    var obj_img = document.getElementById('tempimg2');  
                    obj_img.dynsrc=obj_file.value;  
                    filesize = obj_img.fileSize;  
                }else{  
                	fileFlage =false;
                return;  
                }  
                if(filesize==-1){  
                    alert(tipMsg);  
                    fileFlag =false;
                    return;  
                }else if(filesize>maxsize){  
                    alert(errMsg);  
                    fileFlag =false;
                    return;  
                }else{  
                    fileFlag =true;
                    return;  
                }  
            }catch(e){  
                alert(e);  
            }  
        } 
    	$("#inputForm").validate({
            submitHandler: function(form){
            	if($("#logoUrl").val()==$("#logoUrlTemp").val()){
            		checkfile();
            		if(!fileFlage){
                		return;
                	}
            	}
            	if($("#bgUrl").val()==$("#bgUrlTemp").val()){
            		checkfile();
            		if(!fileFlage){
                		return;
                	}
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
              if (confirm("确认创建?")) {
              	//loading('正在提交，请稍等...');
              	form.submit();
              }
            },
            errorContainer: "#messageBox",
            errorPlacement: function(error, element) {
          /*     $("#messageBox").text("输入有误，请先更正。");
              if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                error.appendTo(element.parent().parent());
              } else {
                error.insertAfter(element);
              } */
            }
          });
	
    })