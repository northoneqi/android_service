<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.ht.sys.bean.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
request.setAttribute("basePath", basePath);
request.setAttribute("userTheme", "gray");

SysUser user = (SysUser)request.getSession().getAttribute("loginUser");

if(user != null){
	response.sendRedirect(basePath+"/admin/adminMain.do");
}

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
						title : "身份认证",
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
							fieldLabel : '帐&nbsp;号',
							name : 'username',
							id : 'username',
							cls : 'user',
							blankText : '帐号不能为空,请输入!',
							maxLength : 30,
							maxLengthText : '账号的最大长度为30个字符',
							allowBlank : false,
							listeners : {
								specialkey : function(field, e) {
									if (e.getKey() == Ext.EventObject.ENTER) {
										Ext.getCmp('password').focus();
									}
								}
							}
						}, {
							fieldLabel : '密&nbsp;码',
							name : 'password',
							id : 'password',
							cls : 'key',
							inputType : 'password',
							blankText : '密码不能为空,请输入!',
							maxLength : 20,
							maxLengthText : '密码的最大长度为20个字符',
							allowBlank : false,
							listeners : {
								specialkey : function(field, e) {
									if (e.getKey() == Ext.EventObject.ENTER) {
										login();
									}
								}
							}
						}/*, {
							id : 'id_reg_panel',
							xtype : 'panel',
							border : false,
							hidden : true,
							html : '<br><div id="id_reg_div" style="font-size:12px;color:blue; margin-left:105px">点此查看登录账户</div>'
						}*/]
					}/*, {
						title : '信息公告',
						contentEl : 'infoDiv',
						defaults : {
							width : 230
						}
					}, {
						title : '关于',
						contentEl : 'aboutDiv',
						defaults : {
							width : 230
						}
					}*/]
				}
			});
		
			// 清除按钮上下文菜单
			var mainMenu = new Ext.menu.Menu({
				id : 'mainMenu',
				items : [{
					text : '清除记忆',
					iconCls : 'status_awayIcon',
					handler : function() {
						clearCookie('bluefat.login.username');
						var username = Ext.getCmp('loginForm')
								.findById('username');
						Ext.getCmp('loginForm').form.reset();
						username.setValue('');
						username.focus();
					}
				}]
			});
		
			var win = new Ext.Window({
				title : "管理系统",
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
				items : panel,
				buttons : [{
					text : '&nbsp;登录',
					iconCls : 'acceptIcon',
					handler : function() {
						if (Ext.isIE) {
							login();
						} else {
							login();
						}
					}
				}, {
					text : '&nbsp;选项',
					iconCls:'tbar_synchronizeIcon',
					menu : mainMenu
				}]
			});
		
			win.show();
		
			win.on('show', function() {
				setTimeout(function() {
					var username = Ext.getCmp('loginForm').findById('username');
					var password = Ext.getCmp('loginForm').findById('password');
					var c_username = getCookie('bluefat.login.username');
					username.setValue(c_username);
					if (Ext.isEmpty(c_username)) {
						username.focus();
					} else {
						password.focus();
					}
				}, 200);
			}, this);
		
			Ext.get('id_reg_div').on('click', function() {
				Ext.getCmp('loginTabs').setActiveTab(1);
			});
		
		
			/**
			 * 提交登陆请求
			 */
			function login() {
				Ext.ux.Form.submitForm(Ext.getCmp('loginForm'),
					'${basePath}/admin/login.do',
					function(form, action) {
							var username = Ext.getCmp('loginForm').findById('username');
							setCookie("bluefat.login.username", username.getValue(), 240);
							setCookie("bluefat.lockflag", '0', 240);
							window.location.href = '${basePath}/admin/adminMain.do';
					},
					'正在验证身份，请稍后。。。。。。');
			}
		
			Ext.getCmp('id_reg_panel').show();
		
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
		JavaEE行业应用二次快速开发平台 (BlueFat&reg)<br>
		<br>
		博客地址:<a href="http://xinjiang.iteye.com" target="_blank">http://xinjiang.iteye.com</a>
		<br>
		<br>
		联系方式:939474528@qq.com
	</div>
	<div id="infoDiv" class="x-hidden"
		style='color: black; padding-left: 10px; padding-top: 10px; font-size: 12px; line-height:25px'>
		登录帐户[用户名/密码](角色)<br>
		[admin/admin](系统管理员)，[100101/0000](个人用户)<br/>
		[100102/0000](部门经理),[100103/0000](项目经理)<br>
		[100104/0000](总经理),[100201/0000](会计)<br>
	</div>
  </body>
</html>
