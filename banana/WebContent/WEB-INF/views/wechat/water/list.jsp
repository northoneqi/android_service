<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>水活动管理</title>
    <script type="text/javascript" src="${basePath}/js/wechat/water/mcirowaterGrid.js"></script>
    <script type="text/javascript" src="${basePath}/js/wechat/water/searchForm.js"></script>
	<script type="text/javascript" src="${basePath}/js/wechat/water/mcirowaterUi.js"></script>
	<script type="text/javascript" src="${basePath}/js/wechat/water/mcirowater.js"></script>
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
 				items: new mcirowater()
 			});
		});
	</script>
  </head>
<body>
</body>
</html>
