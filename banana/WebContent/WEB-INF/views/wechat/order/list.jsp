<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>水活动管理</title>
    <script type="text/javascript" >
		ctx="${basePath}";
	</script>
    <script type="text/javascript" src="${basePath}/js/wechat/order/orderGrid.js"></script>
    <script type="text/javascript" src="${basePath}/js/wechat/order/orderEditWindow.js"></script>
	<script type="text/javascript" src="${basePath}/js/wechat/order/orderUi.js"></script>
	<script type="text/javascript" src="${basePath}/js/wechat/order/order.js"></script>
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
	    		items:new order()
	    	});
	    });
	</script>
  </head>
<body>
</body>
</html>
