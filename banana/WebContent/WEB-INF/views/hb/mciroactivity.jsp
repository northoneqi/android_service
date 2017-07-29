<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>红包活动计划管理</title>
    
    <script type="text/javascript" src="${basePath}/js/hb/mciroactivity/manageUi.js"></script>
	<script type="text/javascript" src="${basePath}/js/hb/mciroactivity/manage.js"></script>
	<script type="text/javascript" src="${basePath}/js/hb/mciroactivity/mainGrid.js"></script>
	<script type="text/javascript" src="${basePath}/js/hb/mciroactivity/searchFrom.js"></script>
	<script type="text/javascript" src="${basePath}/js/hb/mciroactivity/billForm.js"></script>
	<script type="text/javascript" src="${basePath}/js/hb/mciroactivity/addWindow.js"></script>
	<script type="text/javascript" src="${basePath}/js/hb/mciroactivity/billDetail.js"></script>
	<script type="text/javascript" src="${basePath}/js/hb/mciroactivity/productWindow.js"></script>
	<script type="text/javascript" src="${basePath}/js/hb/mciroactivity/productGrid.js"></script>
	<script type="text/javascript" src="${basePath}/js/hb/mciroactivity/productSearchFrom.js"></script>
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
