<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>众筹活动计划管理</title>
    
    <script type="text/javascript" src="${basePath}/js/zc/backinfoTwo/manageUi.js"></script>
	<script type="text/javascript" src="${basePath}/js/zc/backinfoTwo/manage.js"></script>
	<script type="text/javascript" src="${basePath}/js/zc/backinfoTwo/mainGrid.js"></script>
	<script type="text/javascript" src="${basePath}/js/zc/backinfoTwo/searchFrom.js"></script>
	<script type="text/javascript" src="${basePath}/js/zc/backinfoTwo/billForm.js"></script>
	<script type="text/javascript" src="${basePath}/js/zc/backinfoTwo/addWindow.js"></script>
	<script type="text/javascript" src="${basePath}/js/zc/backinfoTwo/billDetail.js"></script>
	<script type="text/javascript" src="${basePath}/js/zc/backinfoTwo/productWindow.js"></script>
	<script type="text/javascript" src="${basePath}/js/zc/backinfoTwo/productGrid.js"></script>
	<script type="text/javascript" src="${basePath}/js/zc/backinfoTwo/productSearchFrom.js"></script>
	<script type="text/javascript" src="${basePath}/js/budget/workflowGrid.js"></script>
	<style type="text/css">
	</style>
	<script type="text/javascript" >
		var buttons = "${buttons}".split(",");
		Ext.onReady(function(){
			initToolBar(buttons);
			
			new Ext.Viewport({
 				layout:'fit',
 				items: new manage()
 			});
			
		});
	</script>

  </head>
  
  <body>
  </body>
</html>
