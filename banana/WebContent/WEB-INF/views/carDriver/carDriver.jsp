<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    <title>驾驶员档案表</title>
    <script type="text/javascript" >
		ctx="${basePath}";
	</script>
    <script type="text/javascript" src="${basePath}/js/carDriver/carDriverGrid.js"></script>
    <script type="text/javascript" src="${basePath}/js/carDriver/carDriverEditWindow.js"></script>
	<script type="text/javascript" src="${basePath}/js/carDriver/carDriverUi.js"></script>
	<script type="text/javascript" src="${basePath}/js/carDriver/carDriver.js"></script>
	<style type="text/css">
		.tbarContext{
			font-weight:bold
		}
	</style>
	
	<script type="text/javascript" >
		Ext.onReady(function(){
	    	Ext.QuickTips.init();
	    	new Ext.Viewport({
	    		layout:'fit',
	    		items:new carDriver()
	    	});
	    });
	</script>
  </head>
<body>
</body>
</html>
