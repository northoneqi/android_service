<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    <title>供应商合同</title>
    <script type="text/javascript" >
		ctx="${basePath}";
	</script>
	
	<script type="text/javascript" src="${basePath}/js/contractProductDetail/contractProductDetailGrid.js"></script>
    <script type="text/javascript" src="${basePath}/js/contractProductDetail/contractProductDetailEditWindow.js"></script>
	<script type="text/javascript" src="${basePath}/js/utils/contractCombox.js"></script>
	<script type="text/javascript" src="${basePath}/js/providerContract/providerContractGrid.js"></script>
    <script type="text/javascript" src="${basePath}/js/providerContract/providerContractEditWindow.js"></script>
	<script type="text/javascript" src="${basePath}/js/providerContract/providerContractUi.js"></script>
	<script type="text/javascript" src="${basePath}/js/providerContract/providerContract.js"></script>
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
	    		items:new providerContract()
	    	});
	    });
	</script>
  </head>
<body>
</body>
</html>
