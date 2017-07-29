<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    <title>要货单</title>
    <script type="text/javascript" >
		ctx="${basePath}";
	</script>
	
	<script type="text/javascript" src="${basePath}/js/yhdOrderItems/yhdOrderItemsGrid.js"></script>
    <script type="text/javascript" src="${basePath}/js/yhdOrderItems/yhdOrderItemsEditWindow.js"></script>
	
	<script type="text/javascript" src="${basePath}/js/yhdOrder/yhdOrderGrid.js"></script>
    <script type="text/javascript" src="${basePath}/js/yhdOrder/yhdOrderEditWindow.js"></script>
	<script type="text/javascript" src="${basePath}/js/yhdOrder/yhdOrderUi.js"></script>
	<script type="text/javascript" src="${basePath}/js/yhdOrder/yhdOrder.js"></script>
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
	    		items:new yhdOrder()
	    	});
	    });
	</script>
  </head>
<body>
</body>
</html>
