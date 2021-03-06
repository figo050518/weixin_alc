<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>添加商品</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="/resources/style/mui.min.css">
		<link rel="stylesheet" href="/resources/style/mui.picker.css" />
		<link rel="stylesheet" href="/resources/style/mui.poppicker.css"/>
		<link rel="stylesheet" href="/resources/style/addGoods.css">
		<link rel="stylesheet" href="/resources/style/info.css">
	</head>
	<body>
		<input type="hidden"  value='${fcgCates}' id="fcgCates"/>
		<div class="head">
			<div class="nickNameDiv">
				<span class="nickName">编辑商品</span>
			</div> 
			<div class="return" onclick="history.go(-1)">
				<span class="returnImg"></span><span>返回</span>
			</div>
			<div class="releaseDiv" onclick="release()">
				<div>发布</div>
			</div>
		</div>
		<div class="headeBlank" style="width: 100%;height: 50px;"></div>
		
		<form id="submitForm" method="post">
		<input type="hidden" name="id" value="${product.id}" id="id"/>
		<input type="hidden" name="upState" value="${product.upState}" id="upState"/>
		<div class="goodsShuxing">
			<div class="goodsTypeDiv">
				<div class="oneLine goodsTitle" id="goodsTitle">
					<div class="lineCount">
						<div class="oneLineLeft">
							标题
						</div>
						<div class="oneLineRight">
							<input type="text" class="lineRightInput typeTitle" name="productName" value="${product.productName}" id="productName"/>
						</div>
						<div class="clear"></div>
					</div>
				</div>
			<div class="addList" id="specList">
			<c:forEach items="${product.sepcList}" var="spec">
			<div class="addOneTypeDiv" style="display:block;" id="${spec.id}">
				<div class="oneTypeDiv">
					<div class="addTypeDiv">
						<div class="addText">
							规格
						</div>
						<div class="addInputDiv">
							<input type="text" class="addInput typeGuige" name="specName" value="${spec.specName}"/>
						</div>
						<div class="clear"></div>
					</div>
				</div>
				<div class="oneTypeDiv">
					<div class="addTypeDiv">
						<div class="addText">
							价格
						</div>
						<div class="addInputDiv">
							<input type="text" class="addInput typePrice" class="" name="specPrice" value="${spec.salesPrice}"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="deleteTypeDiv" onclick="deleteType(this)">
						<img src="/resources/images/deleteType.png" class="deleteTypeImg"/>
					</div>
				</div>
				<div class="oneTypeDiv">
					<div class="addTypeDiv">
						<div class="addText">
							库存
						</div>
						<div class="addInputDiv">
							<input type="text" class="addInput typeCount" class="" name="stocks" value="${spec.stock}"/>
						</div>
						<div class="clear"></div>
					</div>
				</div>
		</div>
		</c:forEach>
			</div>
		</div>
		<div class="addBtnDiv">
			<div class="addBtn" onclick="addOneType()">
				<span style="color:#3083FB;">+添加商品规格</span><span style="font-size: 12px;">(最多添加200个)</span>
			</div>
		</div>
		
		<div class="oneLine yunfei" id="yunfei">
			<div class="lineCount">
				<div class="oneLineLeft">
					运费
				</div>
				<div class="oneLineRight">
					<input type="text" class="lineRightInput"  name="freight" id="freight" value="${product.freight}"/>
				</div>
				<div class="clear"></div>
			</div>
		</div> 
		<div class="tuwenDiv">
			<div class="tuwenText">
				图文描述
			</div>
			<div class="tuwenStatusDiv">
				<span class="tuwenStatus" onclick="addProduceDesc()" id="proFlag">已添加</span><span class="tuwenImg"></span>
			</div>
		</div> 
		<div class="oneLine yunfei" id="fenlei">
			<div class="lineCount">
				<div class="oneLineLeft">
					分类
				</div>
				<div class="oneLineRight">
					<input type="text" class="lineRightInput" id="fenLeiCount" value="${product.group.groupName}" readonly="readonly" onclick="setFenLei();"/>
					<input type="hidden"  id="groupId" value="${product.group.id}" />
				</div>
				<div class="clear"></div>
			</div>
		</div>
		
		<div class="oneLine yunfei" id="pinleiDiv">
			<div class="lineCount">
				<div class="oneLineLeft">
					品类
				</div>
				<div class="oneLineRight">
					<input type="text" readonly="readonly" class="lineRightInput" id="pinLeiCount" value="${product.fcgCateName}"/>
					<input type="hidden"  id="pinLei"  value="${product.fcgCateId}"/>
				</div>
				<div class="clear"></div>
			</div>
			<!-- <input type="text"  class="lineRightInput" id="pinLeiInput" placeholder="请输入品类"/> -->
		</div>
		
		<div class="uploadImgText" id="">
			<div style="margin-left: 5vw;">商品图片</div>
		</div>
		
		<div id="img">
			<!-- 遍历 -->
			<!-- 如果有5个就全部遍历出来，如果小于5个要在最后留一个添加的 -->
			<%-- <input value="${fn:length(product.imageList)}" id="aa" type="hidden"/> --%>
			<c:forEach items="${product.imageList}" var="img">
				<div class="imgbox">
					 <div class="imgnum">
						 <input type="hidden" class="imageId" name="imageId" value="${img.id}"/>
					 	 <input type="hidden" class="imageUrl" name="imageUrl" value="${img.imgUrl}" />
						  <input type="file" class="filepath" name="imageFileName" id="file1" onchange="changePhoto(this)";/>
						  <img src="${img.imgUrl}" class="img1" />
						  <img src="" class="img2" />
					 </div>
					 <img src="/resources/images/cancel.png" class="canclePhoto" onclick="canclePoto(this)"/>
				 </div>
			</c:forEach>
			<c:if test="${fn:length(product.imageList)<5}">
				<div class="imgbox">
					 <div class="imgnum">
						 <input type="hidden" class="imageId" name="imageId" value=""/>
					 	 <input type="hidden" class="imageUrl" name="imageUrl" />
						  <input type="file" class="filepath" name="imageFileName" id="file1" onchange="changePhoto(this)";/>
						  <img src="/resources/images/icon_kuang2.png" class="img1" />
						  <img src="" class="img2" />
					 </div>
					 <img src="/resources/images/cancel.png" class="canclePhoto" onclick="canclePoto(this)"/>
				</div>
			</c:if>
		</div>
		<!---->
		<div id="oneAddPhoto" style="display:none;">
			<div class="imgbox" id="imgbox1">
				 <div class="imgnum">
					  <input type="hidden" class="imageId" name="imageId" value=""/>
				 	<input type="hidden" class="imageUrl" name="imageUrl" />
					  <input type="file" class="filepath" name="imageFileName" id="file1" onchange="changePhoto(this)";/>
					  <img src="/resources/images/icon_kuang2.png" class="img1" />
					  <img src="" class="img2" />
				 </div>
				 <img src="/resources/images/cancel.png" class="canclePhoto" onclick="canclePoto(this)"/>
			</div>
		</div>
		<!--这是一个商品规格-->
		<div class="addHtml">
			<div class="addOneTypeDiv">
				<div class="oneTypeDiv">
					<div class="addTypeDiv">
						<div class="addText">
							规格
						</div>
						<div class="addInputDiv">
							<input type="text" class="addInput typeGuige" name="specName"/>
						</div>
						<div class="clear"></div>
					</div>
				</div>
				<div class="oneTypeDiv">
					<div class="addTypeDiv">
						<div class="addText">
							价格
						</div>
						<div class="addInputDiv">
							<input type="text" class="addInput typePrice" class="" name="specPrice"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="deleteTypeDiv" onclick="deleteType(this)">
						<img src="/resources/images/deleteType.png" class="deleteTypeImg"/>
					</div>
				</div>
				<div class="oneTypeDiv">
					<div class="addTypeDiv">
						<div class="addText">
							库存
						</div>
						<div class="addInputDiv">
							<input type="text" class="addInput typeCount" class="" name="stocks"/>
						</div>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
		<!-- 添加商品详细描述 -->
		<div id="productDesc" class="popup" style="z-index:200;">
			<div class="head" id="fenLeiHead" >
				<div class="nickNameDiv">
					<span class="nickName">添加详细描述</span>
				</div> 
				<div class="return" onclick="closePopup1()">
					<span style="margin-left: 3vw;">取消</span>
				</div>
				<div class="releaseDiv" onclick="modifyProductDesc()">
					<div>确认</div>
				</div>
			</div>
			<div class="fenLeiBlank" style="width: 100%;height: 50px;"></div>
			<div class="addBtnDiv" style="width: 100%;height:calc(100vh - 62px);">
			 <script id="container" name="content" style="" type="text/plain">
					${product.productDesc}
    		</script>
			</div>
			
		</div>
	<!--点击分类的弹窗-->
		<div id="fenLeiListPopup" class="popup">
			<div class="head" id="fenLeiHead">
				<div class="nickNameDiv">
					<span class="nickName">修改分类</span>
				</div> 
				<div class="return" onclick="closePopup1()">
					<span style="margin-left: 3vw;">取消</span>
				</div>
				<div class="releaseDiv" onclick="modifyFenLei()">
					<div>确认</div>
				</div>
			</div>
			<div class="fenLeiBlank" style="width: 100%;height: 50px;"></div>
			<div class="addBtnDiv">
				<div class="addBtn" onclick="addFenLei()" style="margin-left:3vw;">
					<span style="color:#3083FB;">+添加分类</span>
				</div>
			</div>
			<table class="fenLeiList" id="groupList">
			<c:forEach items="${groupList}" var="group">
			<tr>
					<td class="checkBoxTd">
						<label class="checkboxDiv">
						  	<input type="checkbox" class="opaction goodsCheck"/>
						</label>
					</td>
					<td class="fenLeiNameTd" id="${group.id}">
						<span>${group.groupName}</span>
					</td>
				</tr>
			</c:forEach>
			</table>
		</div>
		
	<!--点击添加分类的弹窗-->
		<div class="popup" id="addFenLeiPopup">
			<div class="head" id="fenLeiHead">
				<div class="nickNameDiv">
					<span class="nickName">添加分类</span>
				</div> 
				<div class="return" onclick="closePopup2()">
					<span style="margin-left: 3vw;">取消</span>
				</div>
				<div class="releaseDiv" onclick="addOne()">
					<div>添加</div>
				</div>
			</div>
			<div class="addFenLeiBlank" style="height: 50px;width: 100%;"></div>
			
			<div class="addFenLeiInputDiv">
				<input type="text" id="addFenLeiInput" placeholder="请输入分类名称"/>
			</div>
		</div>
		</form>
		<div style="display:none;"><input id="hide"></div>
	</body>
	<script src="/resources/script/jquery-1.11.1.min.js"></script>
	<script src="/resources/script/mui.min.js"></script>
	<script src="/resources/script/mui.picker.js"></script>
	<script src="/resources/script/mui.poppicker.js"></script>
	<script src="/resources/script/jquery.form.js"></script>
	<script src="/resources/script/ajaxfileupload.js" type="text/javascript"></script>
	<script src="/resources/script/info.js"></script>
	<script src="/resources/script/product/edit.js"></script>
	<script type="text/javascript" charset="utf-8" src="/resources/script/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/resources/script/ueditor/ueditor.all.min.js"> </script>
    	    <!-- 实例化编辑器 -->
	    <script type="text/javascript">
	    var ue = UE.getEditor('container', {
	    	  toolbars: [[
				'bold', //加粗
				'forecolor', //字体颜色
				'simpleupload',
				]],
					initialFrameWidth: '100%',
					initialFrameHeight: '100%',
	    	         elementPathEnabled:false,
	    	         wordCount: false  
	    });
	    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;  
	    UE.Editor.prototype.getActionUrl = function(action) {
	        if (action == 'uploadimage') {//跳转到后来的上传action  
	            return '${pageContext.request.contextPath}/uc/interfaces/image/ueUpload';  
	        } else {  
	            return this._bkGetActionUrl.call(this, action);  
	        }  
	    } 
	    </script>
</html>