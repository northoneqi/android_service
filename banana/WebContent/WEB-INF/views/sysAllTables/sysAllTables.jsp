<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    <title>系统所有业务表</title>
    <script type="text/javascript" >
		ctx="${basePath}";
	</script>
	<style type="text/css">
		 .my_row_bgcolor{ background-color:#8FBFE7} 
	</style>
    <script type="text/javascript" src="${basePath}/js/sysAllTables/sysAllTablesGrid.js"></script>
    <script type="text/javascript" src="${basePath}/js/sysAllTables/sysAllTablesEditWindow.js"></script>
	<script type="text/javascript" src="${basePath}/js/sysAllTables/sysAllTablesUi.js"></script>
	<script type="text/javascript" src="${basePath}/js/sysAllTables/sysAllTables.js"></script>
	<script type="text/javascript" src="${basePath}/js/sysAllTables/uploadFile.js"></script>
	<script type="text/javascript" src="${basePath}/js/sysAllTables/errorMsgWindow.js"></script>
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
	    		items:new sysAllTables()
	    	});
	    });
	</script>
  </head>
<body>
</body>
</html>
