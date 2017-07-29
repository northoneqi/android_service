<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>视频活动管理</title>
     <script type="text/javascript" src="${basePath}/js/wechat/video/videoView.js"></script>
    <script type="text/javascript" src="${basePath}/js/wechat/video/videoGrid.js"></script>
    <script type="text/javascript" src="${basePath}/js/wechat/video/searchFrom.js"></script>
	<script type="text/javascript" src="${basePath}/js/wechat/video/videoUi.js"></script>
	<script type="text/javascript" src="${basePath}/js/wechat/video/video.js"></script>
	<style type="text/css">
		.tbarContext{
			font-weight:bold
		}
	</style>
	<script type="text/javascript" >
		ctx="${basePath}";
	</script>
	<script type="text/javascript" >
		var buttons = "${buttons}".split(",");
		Ext.onReady(function(){
			initToolBar(buttons);
			
			new Ext.Viewport({
 				layout:'fit',
 				items: new video()
 			});
		});
	</script>
  </head>
<body>
</body>
</html>
