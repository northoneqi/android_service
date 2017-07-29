<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>视频活动管理</title>
    <script type="text/javascript" src="${basePath}/js/wechat/videoComment/viewForm.js"></script>
    <script type="text/javascript" src="${basePath}/js/wechat/videoComment/commentGrid.js"></script>
	<script type="text/javascript" src="${basePath}/js/wechat/videoComment/videoCommentUi.js"></script>
	<script type="text/javascript" src="${basePath}/js/wechat/videoComment/videoComment.js"></script>
	<style type="text/css">
		.tbarContext{
			font-weight:bold
		}
	</style>
	<script type="text/javascript" >
		ctx="${basePath}";
		imageURL="${videoDetail.videoUrl}";
		author="${videoDetail.author}";
		title="${videoDetail.title}";
		hostId="${videoDetail.hostId}";
		content="${videoDetail.content}";
		vid="${videoDetail.vid}";
		commentNode="${commentNode}";
		commentTime="${commentTime}";
		
		zan="${zan}";
		shi="${shi}";
		zp="${zp}";
	</script>
	<script type="text/javascript" >
		var buttons = "${buttons}".split(",");
		Ext.onReady(function(){
			initToolBar(buttons);
			
			new Ext.Viewport({
 				layout:'fit',
 				items: new videoCommen()
 			});
		});
	</script>
  </head>
<body>
</body>
</html>
