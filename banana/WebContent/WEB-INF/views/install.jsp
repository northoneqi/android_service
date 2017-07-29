<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.ht.sys.bean.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
request.setAttribute("basePath", basePath);
request.setAttribute("userTheme", "gray");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>/">
    
    <title>系统登陆</title>
	<script type="text/javascript" src="${basePath}/ext/bootstrap.js"></script>
	<script type="text/javascript" src="${basePath}/js/bluefat_util.js"></script>
	<!-- 引入主题 -->
	<link type="text/css" href="${basePath}/ext/resources/css/xtheme-${userTheme}.css"/>
	<script type="text/javascript">
		
		/**
		 * 登陆页面
		 * 
		 * @author qj
		 * @since 2013-08-04
		 */
		Ext.onReady(function() {
			Ext.QuickTips.init();
			var panel = new Ext.Panel({
				el : 'hello-tabs',
				autoTabs : true,
				deferredRender : false,
				border : false,
				items : {
					xtype : 'tabpanel',
					id : 'loginTabs',
					activeTab : 0,
					height : 180,
					border : false,
					items : [{
						title : "初始化数据",
						xtype : 'form',
						id : 'loginForm',
						defaults : {
							width : 260
						},
						bodyStyle : 'padding:20 0 0 50',
						defaultType : 'textfield',
						labelWidth : 40,
						labelSeparator : '：',
						items : [{
							xtype : 'button',
							text: '初始化数据',
							style: 'margin-top: 30px; margin-left: 50px;',
							width: 200,
							height: 40,
							handler: function(){
								Ext.MessageBox.show({
						           title: '请等待',
						           msg: '正在初始化数据...',
						           //progressText: '初始化中...',
						           width:300,
						           //progress:true,
						           closable:false
						       });
								
								Ext.ux.Ajax.request("${basePath}/admin/initData.do", {}, function(action, form){
									Ext.MessageBox.hide();
									Ext.ux.Msg.info(""+action.responseText);
								});
							}
						}]
					}, {
						title : '关于',
						contentEl : 'aboutDiv',
						defaults : {
							width : 230
						}
					}]
				}
			});
		
			var win = new Ext.Window({
				title : "BlueFat演示系统",
				renderTo : Ext.getBody(),
				layout : 'fit',
				width : 460,
				height : 300,
				closeAction : 'hide',
				plain : true,
				modal : true,
				collapsible : true,
				titleCollapse : true,
				maximizable : false,
				draggable : false,
				closable : false,
				resizable : false,
				animateTarget : document.body,
				items : panel
			});
		
			win.show();
		
		});

	</script>
  </head>
  
  <body>
	<div id="hello-win" class="x-hidden">
		<div id="hello-tabs"><img border="0" width="450" height="70"
			src="${basePath}/images/banner_right.png" />
		</div>
	</div>
	<div id="aboutDiv" class="x-hidden"
		style='color: black; padding-left: 10px; padding-top: 10px; font-size: 12px'>
		该功能模块只开放给开发人员，其他人不得擅自使用<br>
		<br>
		<br>
		联系方式:XXX
	</div>
  </body>
</html>
