<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    <title>调价单主表</title>
    <script type="text/javascript" >
		ctx="${basePath}";
	</script>
	
	<script type="text/javascript" src="${basePath}/js/commodityChangePriceTwo/commodityChangePriceTwoGrid.js"></script>
    <script type="text/javascript" src="${basePath}/js/commodityChangePriceTwo/commodityChangePriceTwoEditWindow.js"></script>
	
	<script type="text/javascript" src="${basePath}/js/commodityChangePrice/commodityChangePriceGrid.js"></script>
    <script type="text/javascript" src="${basePath}/js/commodityChangePrice/commodityChangePriceEditWindow.js"></script>
	<script type="text/javascript" src="${basePath}/js/commodityChangePrice/commodityChangePriceUi.js"></script>
	<script type="text/javascript" src="${basePath}/js/commodityChangePrice/commodityChangePrice.js"></script>
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
	    		items:new commodityChangePrice()
	    	});
	    });
	</script>
  </head>
<body>
</body>
</html>
