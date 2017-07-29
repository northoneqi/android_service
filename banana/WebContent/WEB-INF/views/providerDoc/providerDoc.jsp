<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    <title>供应商档案</title>
    <script type="text/javascript" >
		ctx="${basePath}";
	</script>
	
	<script type="text/javascript" src="${basePath}/js/providerLinkMan/providerLinkManGrid.js"></script>
    <script type="text/javascript" src="${basePath}/js/providerLinkMan/providerLinkManEditWindow.js"></script>
	
	<script type="text/javascript" src="${basePath}/js/providerDoc/providerDocGrid.js"></script>
    <script type="text/javascript" src="${basePath}/js/providerDoc/providerDocEditWindow.js"></script>
	<script type="text/javascript" src="${basePath}/js/providerDoc/providerDocUi.js"></script>
	<script type="text/javascript" src="${basePath}/js/providerDoc/providerDoc.js"></script>
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
	    		items:new providerDoc()
	    	});
	    });
	</script>
  </head>
<body>
</body>
</html>
