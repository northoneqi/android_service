<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>首页</title>
	<script type="text/javascript" src="${basePath}/ext/extTabCloseMenu.js"></script>
	<script type="text/javascript">
		var resourceList = ${resourceList};
		Ext.onReady(function(){
			
		/*	new Ext.ux.TipWindow({
				title : '<span class=commoncss>提示</span>',
				html : '欢迎登录系统，祝工作愉快.',
				iconCls : 'commentsIcon'
			}).show(Ext.getBody());*/
		
			var edituserWin;
			function editUserInfo() {
				editUserForm = new Ext.form.FormPanel({
					layout: "form",
					border : true,
					frame : true, 
					bodyPadding : 5,
					defaultType : 'textfield',
					triggerAction:'all',
					defaults : {
						// autoFitErros:true, //展示错误信息时是否自动调整组件宽度
						lableSeparator : ':', // 标签与field已“:”分开
						lableWidth : 50,
						width: 300,
						allowBlank : true, // 是否允许为空
						blankText : '不允许为空', // 字段为空是的提示消息
					    lableAlign:'right', //标签对齐方式
						msgTarget : 'side' // 显示提示消息样式
					},
					items: [{
						name: 'id',
						hidden: true,
						value: "${loginUser.id}"
					},{
						 fieldLabel: "姓名",
						 value: "${loginUser.name}",
		               	 fieldStyle:'background-color: #F6F6F6; background-image: none;',
						 readOnly: true
					},{
						 fieldLabel: "账号",
		                 name: 'userName',
		                 value: "${loginUser.userName}",
		               	 fieldStyle:'background-color: #F6F6F6; background-image: none;',
						 readOnly: true
					},{
						 xtype: 'textfield',
						 inputType: 'password',
						 fieldLabel: "旧密码",
		                 name: 'oldPassword',
		                 focus: true,
		                 allowBlank : false // 是否允许为空
					},{
						 xtype: 'textfield',
						 inputType: 'password',
						 fieldLabel: "新密码",
		                 name: 'newPassword',
		                 allowBlank : false // 是否允许为空
					},{
						 xtype: 'textfield',
						 inputType: 'password',
						 fieldLabel: "确认密码",
		                 name: 'confirmPassword',
		                 allowBlank : false // 是否允许为空
					},{
						 fieldLabel: "所属部门",
		                 value: "${loginUser.departmentName}",
		                 fieldStyle:'background-color: #F6F6F6; background-image: none;',
						 readOnly: true
					}],
					buttonAlign: 'center',
					buttons:[{
						text: "修改",
						handler: function(){
							var values = editUserForm.getForm().getValues();
							if(editUserForm.getForm().isValid()){
								Ext.ux.Ajax.request("${basePath}/sys/user/updatePassword.do", values,
									function(response,action){
										Ext.Msg.alert("消息", response.responseText);
								});
								editUserForm.getForm().reset();
							}else{
								if(values.oldPassword == ""){
									Ext.Msg.alert("警告", "旧密码不能为空！");
								}else if(values.newPassword == ""){
									Ext.Msg.alert("警告", "新密码不能为空！");
								}else if(values.confirmPassword != values.newPassword){
									Ext.Msg.alert("警告", "新密码和确认密码不一致！");
								}
								
							}
						}
					},{
						text: "取消",
						handler: function(){
							editUserWin.hide();
						}
					}, "->"]
				});
				
				editUserWin = createWindow("修改密码", editUserForm, 500, 240);
				editUserWin.show();
			}
			
            var topPanel = new Ext.Panel({
            	contentEl: 'topPanel',
            	region: 'north',
            	deferredRender : true,
            	frame: true,
            	border : false,
            	height: 70
            });
            
            mainTabs = new Ext.TabPanel({
                region:'center', 
                activeTab:0,
                id:'mainTabs',
                minHeight: 400,
                enableTabScroll: true,
                border:false,
                plugins: new Ext.ux.TabCloseMenu(),
                items:[{
                      title:"<img align='top' class='IEPNG' src='${basePath}/images/ext/user.png'/>我的工作台",
                      html:'<iframe frameborder="0" width="100%" height="100%" src="${basePath}/admin/main.do"></iframe>',
                      listeners: {
                    	  activate: function(){
                    	  	Ext.getCmp('centerPanel').setTitle('我的工作台');
                    	  	
                    	  	//重新加载主页
                    	  	var tabInitialConfig = mainTabs.getActiveTab().initialConfig;
                    	  	mainTabs.getActiveTab().update(tabInitialConfig, true);
                    	  }
                      }
                }]
            });
            
            var centerPanel = new Ext.Panel({
            	 id: 'centerPanel',
            	 layout: 'border',
            	 region:'center',
                 margins:'5 5 5 0',
                 title: '我的工作台',
                 items: [mainTabs]
            });
            
            var accordion = new Ext.Panel({
                region:'west',
                margins:'5 0 5 5',
                split:true,
                width: 210,
                layout:'accordion'
                //items: items
            });
          	
            var items = [];
            var trees = {};
            var nodes = {};
            
            for(var i = 0 ; i < resourceList.length; i++){
            	var res = resourceList[i];

            	if(res.type == "accordion"){
            		var root = new Ext.tree.TreeNode({
						id:"root_"+res.code,	
						text:"菜单树"
					});
            		
            		var tree = new Ext.tree.TreePanel({
						rootVisible: false,
						border: false,
						autoScroll:true,  
						lines : false,
						root: root
					});
            		
            		trees['tree_'+res.code] = tree;
            		nodes['node_'+res.code] = root;
            		var item = new Ext.Panel({
		                title: res.name,
		                items: [tree]
		            });
            		
            		var length = items.length;
            		items[length] = item;
            		//accordion.add(item);
            	} else {
            		var parentCode = res.parentCode;
            		var parentNode = nodes['node_'+parentCode];
            		var  node = new Ext.tree.TreeNode({
						id: 'node_'+res.code,
						text: res.name,
						url: res.url,
						code: res.code,
						listeners : {
							'click' : function(node){
            					if(!Ext.isEmpty(node.attributes.url)){
            						addTab("${basePath}/"+node.attributes.url, node.attributes.text, node.attributes.code, node.attributes.text);
            					}
							}
						}
					});
            		
            		nodes['node_'+res.code] = node;
            		
            		parentNode.appendChild(node);
            	}
            }
            
            for(var i = items.length-1; i >= 0; i--){
            	accordion.add(items[i]);
            }
            
            var viewport = new Ext.Viewport({
                layout:'border',
                items:[
                    accordion,
                    topPanel,
                    centerPanel
                ]
            });
            
            var themeMenu = new Ext.menu.Menu({
				id : 'themeMenu',
				items : [{
					text : '经典蓝色',
					iconCls : 'bugIcon',
					handler : function() {
						changeTheame("blue");
						setCookie("bluefat.theme", "blue", 240);
						window.location.reload();
					}
				}, {
					text : '灰色',
					iconCls : 'app_rightIcon',
					handler : function() {
						changeTheame("gray");
						setCookie("bluefat.theme", "gray", 240);
						window.location.reload();
					}
				}, {
					text : '对比',
					iconCls : 'app_rightIcon',
					handler : function() {
						changeTheame("access");
						setCookie("bluefat.theme", "access", 240);
						window.location.reload();
					}
				}]
			});
			
			var themeButton = new Ext.Button({
				text : '主题',
				iconCls : 'themeIcon',
				iconAlign : 'left',
				scale : 'medium',
				width : 50,
				tooltip : '<span style="font-size:12px">切换系统主题样式</span>',
				//pressed : true,
				arrowAlign : 'right',
				renderTo : 'themeDiv',
				menu: themeMenu
			});
			
			var mainMenu = new Ext.menu.Menu({
				id : 'mainMenu',
				items : [{
					text : '密码修改',
					iconCls : 'keyIcon',
					handler : function() {
						editUserInfo();
					}
				}/*, {
					text : '系统锁定',
					iconCls : 'lockIcon',
					handler : function() {
						editUserInfo();
					}
				}*/]
			});
			
			var mainMenu_sys = new Ext.menu.Menu({
				id : 'mainMenu',
				items : [{
					text : '返回首页',
					iconCls : 'keyIcon',
					handler : function() {
						window.location.href = "${basePath}/admin/adminMain.do";
					}
				}, {
					text : '退出系统',
					iconCls : 'cancel_48Icon',
					handler : function() {
						window.location.href = "${basePath}/admin/logout.do";
					}
				}]
			});
			var configButton = new Ext.Button({
				text : '首选项',
				iconCls : 'config2Icon',
				iconAlign : 'left',
				scale : 'medium',
				width : 50,
				tooltip : '<span style="font-size:12px">首选项设置</span>',
				pressed : true,
				renderTo : 'configDiv',
				menu : mainMenu
			});

			var closeButton = new Ext.Button({
				iconCls : 'cancel_48Icon',
				iconAlign : 'left',
				scale : 'medium',
				width : 30,
				tooltip : '<span style="font-size:12px">切换用户,安全退出系统</span>',
				pressed : true,
				arrowAlign : 'right',
				renderTo : 'closeDiv',
				menu:mainMenu_sys
			});
		});
		
		
		  /**
		 * 响应树节点单击事件
		 */
		function addTab(url,name,menuid,pathCh,icon){
		  	if(Ext.isEmpty(icon)){
		    	icon = 's.gif';
		  	}
		  	var id = "tab_id_" + menuid;
		  	if(url == '#' || url == ''){
		    	//Ext.Msg.alert('提示', '此菜单还没有指定请求地址,无法为您打开页面.');
		    	return;
		  	}else{
		 		var index = url.indexOf('.do');
		  		if(index != -1)
		    		url = url;
		  		var n = mainTabs.getComponent(id);
		  		if (Ext.isEmpty(n)) {
			    	n = mainTabs.add({
			       		id:id,
			       		title:"<img align='top' class='IEPNG' src='${basePath}/images/ext/" + icon + "'/>" + name,
			       		closable:true,
			       		layout:'fit',
			       		autoScroll : true,
			       		listeners: {activate: function(){Ext.getCmp('centerPanel').setTitle(pathCh)}},
			       		html:'<iframe frameborder="0" width="100%" height="100%" src='+url+'></iframe>',
			       		listeners: {
                    	  activate: function(){
                    	  	Ext.getCmp('centerPanel').setTitle(name);
                    	  	
                    	  	//重新加载主页
                    	  	var tabInitialConfig = mainTabs.getActiveTab().initialConfig;
                    	  	//mainTabs.getActiveTab().update(tabInitialConfig, true);
                    	  }
                      }
		        	});
			     	mainTabs.setActiveTab(n);
		   		}else{
		   			mainTabs.setActiveTab(n);
		   		}
		  		
		 	}
		  	
		  	return false;
  		}
	</script>
  </head>
  
  <body>
  	<div id="topPanel" class="x-hidden">
  		<table border="0" cellpadding="0" cellspacing="0" width="100%"  style="font-size: 12px; margin-top: -20px;"
			height="70" >
			<tr >
				<td style="padding-left:0px;">
					<img alt="" src="images/program_budget.png">
				</td>
				<td style="padding-right:5px">
				  <table width="100%"   border="0" cellpadding="0" cellspacing="3" class="banner">
				  	<tr align="right">
				      <td>欢迎您：${user.name} <span id="rTime"><span></td>
				    </tr>
				    <tr align="right">
				    <td>
				      <table border="0" cellpadding="0" cellspacing="1">
				        <tr>
				          <td><div id = "themeDiv"></td> 
				          <td>&nbsp;</td>
				          <td><div id = "configDiv"></td> 
				          <td>&nbsp;</td>
				          <td><div id = "closeDiv"></td>
				        </tr>
				      </table>
				    </td>
				    </tr>
				  </table>
				</td>
			</tr>
		</table>
	</div>
	<a href="#" onclick="javascript:endIeStatus();" id="endIeStatus"
		style="display: none;" />
  </body>
</html>
