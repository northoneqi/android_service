<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.ht.sys.bean.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
request.setAttribute("basePath", basePath);
request.setAttribute("userTheme", "blue");

SysUser user = (SysUser)request.getSession().getAttribute("loginUser");

request.setAttribute("user", user);
if(user == null){
	response.sendRedirect(basePath+"/index.jsp");
}

%>

<link rel="shortcut icon" href="${basePath}/images/logo.png" type="image/x-icon" />
<script type="text/javascript" src="${basePath}/ext/bootstrap.js"></script>
<script type="text/javascript" src="${basePath}/js/fields.js"></script>
<script type="text/javascript" src="${basePath}/js/bluefat_util.js"></script>
<script type="text/javascript" src="${basePath}/js/json.js"></script>
<script type="text/javascript" src="${basePath}/ext/ux/RowEditor.js"></script>
<script type="text/javascript" src="${basePath}/ext/ux/GridSummary.js"></script>
<script type="text/javascript" src="${basePath}/ext/ux/DateTimeField.js"></script>

<!-- 引入主题 -->
<!--  
<link type="text/css" href="${basePath}/ext/resources/css/xtheme-${userTheme}.css"/>
-->
<script>
	userName = "${user.name}";
	userCode = "${user.code}";
	departmentName = "${user.departmentName}";
	departmentCode = "${user.departmentCode}";
	basePath = "${basePath}";
	anchor_w = "-10";
		 
	Ext.onReady(function(){
		 Ext.QuickTips.init();
		 var theme = parent.getCookie("bluefat.theme");
		 
		 if(!Ext.isEmpty(theme)){
			 Ext.util.CSS.swapStyleSheet("theme", "${basePath}/ext/resources/css/xtheme-"+theme+".css"); 
		 }else{
			 Ext.util.CSS.swapStyleSheet("theme", "${basePath}/ext/resources/css/xtheme-${userTheme}.css");
		 }
		 
	     Ext.util.CSS.refreshCache();
	     
	});
	
	function changeTheame(themeName){
		 Ext.util.CSS.swapStyleSheet("theme", "${basePath}/ext/resources/css/xtheme-"+themeName+".css");
	     Ext.util.CSS.refreshCache();
	}
	/*
	Ext.Ajax.on('requestexception', function(conn, response, options) {
		if (response.status == 999) {
			setTimeout(function(){
				//延时避免被failure回调函数中的aler覆盖
				top.Ext.MessageBox.alert('提示', '由于长时间未操作,空闲会话已超时;您将被强制重定向到登录页面!', function() {
					window.parent.location.href = '${basePath}/index.jsp';
				});
			},200);
		}else if (response.status == '998') {
			setTimeout(function(){
				//延时避免被failure回调函数中的aler覆盖
				top.Ext.MessageBox.alert('提示', '您的会话连接由于在其它窗口上被注销而失效,系统将把您强制重定向到登录界面.', function() {
					window.parent.location.href = '${basePath}/index.jsp';
				});
			},200);
		}else if (response.status == -1) {
			setTimeout(function(){
				//延时避免被failure回调函数中的aler覆盖
				top.Ext.MessageBox.alert('提示', '请求失败,超时或服务器无响应.', function() {
				});
			},200);		
		}else{
		     parent.showException(response.responseText);
		}
  });
	
	//异常截获窗口
	function showException(strMsg) {
		var EXCEPTION_CLIENT_WIN_SIZE='400,150';
		var EXCEPTION_CLIENT_MSG='很遗憾的通知您：服务器发生了错误，您提交的请求处理失败。请和我们的客服联系，客服支持电话：911。我们将在第一时间为您处理'
		var arr = EXCEPTION_CLIENT_WIN_SIZE.split(',');
		var shortWindow = new Ext.Window({
			title : '<span class="commoncss">系统发生错误</span>', 
			layout : 'fit', 
			iconCls : 'exclamationIcon',
			width : arr[0], 
			height : arr[1], 
			animateTarget : Ext.getBody(),
			closable : true, 
			closeAction : 'close', 
			collapsible : false, 
			modal : true,
			maximizable : false, 
			border : false, 
			constrain : true, 
			items : [
				new Ext.Panel({
					html : EXCEPTION_CLIENT_MSG,
					style : "font-size: 13px;",
					autoScroll : true
				})],
			buttons : [{
				text : '更多信息',
				iconCls : 'previewIcon',
				handler : function() {
					var detailWindow = new Ext.Window({
						title : '<span class="commoncss">系统运行时异常堆栈详细信息</span>',
						layout : 'fit', 
						iconCls : 'previewIcon',
						width : document.body.clientWidth - 300, 
						height : document.body.clientHeight - 80, 
						animateTarget : Ext.getBody(),
						closable : true, 
						closeAction : 'close', 
						collapsible : false, 
						modal : true,
						maximizable : false, 
						border : false, 
						constrain : true, 
						buttons:[{
							text : '关闭',
							iconCls : 'deleteIcon',
							handler : function() {
								alert("ok");
							    detailWindow.hide();
							}
						}],
						items : [new Ext.Panel({
									html : strMsg,
									style : "font-size: 13px;",
									autoScroll : true
								})]
					}).show();
				}
			},{
				text : '关闭',
				iconCls : 'deleteIcon',
				handler : function() {
				    shortWindow.close();
				}
			}]
		}).show();
	}
**/
</script>