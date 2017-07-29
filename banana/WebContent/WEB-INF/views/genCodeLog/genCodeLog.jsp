<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    <title>自动代码生成</title>
    <script type="text/javascript" >
		ctx="${basePath}";
	</script>
    <script type="text/javascript" src="${basePath}/js/genCodeLog/genCodeLogGrid.js"></script>
    <script type="text/javascript" src="${basePath}/js/genCodeLog/genCodeLogEditWindow.js"></script>
	<script type="text/javascript" src="${basePath}/js/genCodeLog/genCodeLogUi.js"></script>
	<script type="text/javascript" src="${basePath}/js/genCodeLog/genCodeLog.js"></script>
	<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
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
	    		items:new genCodeLog()
	    	});
	    });
	</script>
  </head>
<body>
</body>
</html>
