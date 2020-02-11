<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
		<title>账户设置</title>
		<meta name="format-detection" content="telephone=no" />
		<script type="text/javascript" src="/resources/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="/resources/script/info.js"></script>
		<link rel="stylesheet" href="/resources/style/accountSettings.css">
		<link rel="stylesheet" href="/resources/style/info.css">
  </head> 
  <body>
		<div class="head">
			<div class="title">
				<span>账户设置</span>
			</div> 
			<div class="return" onclick="history.go(-1)">
				<span class="returnImg"></span><span>返回</span>
			</div>
		</div>
        <div style="width: 100%;height: 10px;background:#F5F5F5;"></div>
		<form id="imageHead" method="post" action="" enctype="multipart/form-data" name="RegisterUsers">
		    <div id="fileInputContainer">
		     	<input type="hidden" name="tempPicIds" id="tempPicIds">
				<input accept="image/*" id="file" type="file" class="file" name="tempFile"/>
				<div class="portrait" style="border-bottom:1px solid #F1F1F1;z-index: 10;position: relative;">
					<div style="float:left;font-size: 1.6em;margin-left: 10%;line-height:80px;">头像</div>
					<div style="float: right;margin-right: 10%;">
						<div style="float:left;">
							<img src="/resources/images/next1.png" style="height: 20px;margin-top:30px;"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="clear"></div>
				</div>
				<div style="z-index:10;position: absolute;top:10px;width: 60px;height:60px;right:15%" class="imgDiv">
				
					<img src="/resources/images/morentouxiang.png" id="preImage" style="height: 60px;width: 60px;z-index:1001;right:11px;position:absolute;display:block;left:0;top:0;" onclick="magnifyingImage(this)" id="photo"/>
					
				</div>
			</div>
		</form>	
		<a href="/uc/buyer/manage/changeNickName">
			<div class="payTypeDiv" >
					<div style="float:left;font-size: 1.6em;margin-left: 10%;line-height: 50px;">更改昵称</div>
					<div style="float:right;margin-right:10%;">
						<img src="/resources/images/next1.png" style="height: 20px;margin-top:15px;"/>
					</div>
						<div style="float:right;font-size: 1.6em;line-height: 50px;margin-right:5%;">${userName}</div>
				
				<div class="clear"></div>
			</div>
		</a>
		<div style="width: 100%;height: 10px;background:#F5F5F5;"></div>
		<div class="payTypeDiv">
			<a href="/uc/buyer/manage/changerPassword">
				<div style="float:left;font-size: 1.6em;margin-left: 10%;line-height: 50px;">更换密码</div>
				<div style="float:right;margin-right: 10%;" >
					<img src="/resources/images/next1.png" style="height: 20px;margin-top:15px;"/>
				</div>
			</a>
			<div class="clear"></div>
		</div>
		
		<div style="width: 100%;height: 0px;border-top: 1px solid #F1F1F1;"></div>
		<a href="/uc/buyer/manage/changeTelephone">
		<div class="payTypeDiv">
				<div style="float:left;font-size: 1.6em;margin-left: 10%;line-height: 50px;">换绑手机</div>
				<div style="float:right;margin-right: 10%;" >
					<img src="/resources/images/next1.png" style="height: 20px;margin-top:15px;"/>
				</div>
			<div class="clear"></div>
		</div>
		</a>
		
	</body>
</html>
