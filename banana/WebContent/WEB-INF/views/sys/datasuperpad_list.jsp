<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>数据同步</title>
    
	<style type="text/css">
	</style>
	<script type="text/javascript" >
		Ext.onReady(function(){
			/**check表单**/
			var checkInForm=new Ext.form.CheckboxGroup({
				id:'inGroup',
				fieldLabel: '导入表',
				columns: 3,
				items:[
					{boxLabel: '部门', name: 'sys_department', checked: true}, 
					{boxLabel: '供应商档案', name: 'budget_supplier_record', checked: true},
					{boxLabel: '供应商分类'},
					{boxLabel: '会计科目', name: 'budget_accounting_subject', checked: true},
					{boxLabel: '客户档案', name: 'budget_client_record', checked: true},
					{boxLabel: '客户分类'},
					{boxLabel: '人员档案', name: 'sys_user', checked: true},
					{boxLabel: '现金流量项目档案', name: 'budget_flows', checked: true},
					{boxLabel: '项目档案', name: 'budget_item_archives', checked: true},
					{boxLabel: '项目分类', name: 'budget_project_subject', checked: true}
				]
			});
			var checkOutForm=new Ext.form.CheckboxGroup({
				id:'outGroup',
				fieldLabel: '导出表',
				columns: 3,
				items:[
					{boxLabel: '凭证', name: 'GL_accvouch', checked: true}, 
					{boxLabel: '现金流', name: 'GL_CashTable', checked: true}
				]
			});
			var combox=createCombo("数据库类型", "dataBaseType", [["SQLServer", "SQLServer"]])
			combox.setValue('${sysDataSuperpad.dataBaseType}');
			var formPanel=new Ext.form.FormPanel({
				layout:'column',
				margins:'1,1,1,1',
				id:'connectFormPanel',
				border:false,
				items:[{
					columnWidth:0.33,
					layout:'form',
					defaults : {xtype: 'textfield', anchor: '-10', allowBlank: false},
           			labelAlign : 'right',
					border:false,
					items:[combox,
						{fieldLabel:'用户名',name:'dataBaseUser',value:'${sysDataSuperpad.dataBaseUser}'}	
					//{fieldLabel:'同步',xtype:'checkbox'}
					]
				},{
					columnWidth:0.33,
					border:false,
					layout:'form',
					defaults : {xtype: 'textfield', anchor: '-10', allowBlank: false},
           			labelAlign : 'right',
           			items:[
           				{fieldLabel:'数据库',name:'dataBaseName',value:'${sysDataSuperpad.dataBaseName}'},
           				{fieldLabel:'密码',name:'dataBasePassword',value:'${sysDataSuperpad.dataBasePassword}'}
           			]
				},{
					columnWidth:0.34,
					border:false,
					layout:'form',
					defaults : {xtype: 'textfield', anchor: '-10', allowBlank: false},
           			labelAlign : 'right',
           			items:[{fieldLabel:'IP地址',name:'dataBaseAddress',value:'${sysDataSuperpad.dataBaseAddress}'},
           				{xtype:'hidden',allowBlank:true,name:'tables'}
           			]
				}]
			});
			new Ext.Viewport({
				layout:'border',
				border:false,
				items:[{
					layout:'fit',
					region:'north',
					margins:'1,1,1,1',
					height:130,
					title:'配置信息',
					buttonAlign:'center',
					buttons:[{text:'测试连接',handler:textConnect},{text:'保存连接',handler:saveConnect}],
					items:formPanel
				},{
					layout:'border',
					border:false,
					region:'center',
					items:[{
						region:'west',
						margins:'1,1,1,1',
						width:'50%',
						tbar:[{text:'同步导入表数据', iconCls:'tbar_synchronizeIcon', handler:leadingIn}, {xtype:'tbseparator'}],
						layout:'fit',
						items:checkInForm
					},{
						tbar:[{text:'同步导出表数据', handler: exportData}, {xtype:'tbseparator'}],
						margins:'1,1,1,1',
						region:'center',
						items:checkOutForm
					}]
				}]
			});
		});
	/**检测连接是否成功**/
	textConnect=function(){
		var formPanel=Ext.getCmp("connectFormPanel").getForm();
		if(formPanel.isValid()){
			formPanel.submit({
				method:'POST',
				waitMsg : '正在连接数据库,请稍等...',
				data:'JSON',
				waitTitle : '系统提示',// 标题
				url:'sys/datasuperpad/testConnection.do',
				params:formPanel.getValue,
				success:function(form,action){
					Ext.Msg.alert("系统提示",action.result.msg);
				},
				failure:function(form,action){
					Ext.Msg.alert("系统提示",action.result.msg);
				}
			});
		}
	}
	/**保存连接**/
	saveConnect=function(){
		var formPanel=Ext.getCmp("connectFormPanel").getForm();
		if(formPanel.isValid()){
			formPanel.submit({
				method:'POST',
				waitMsg : '正在保存数据库,请稍等...',
				data:'JSON',
				waitTitle : '系统提示',// 标题
				url:'sys/datasuperpad/saveConnection.do',
				params:formPanel.getValue,
				success:function(form,action){
					Ext.Msg.alert("系统提示",action.result.msg);
				},
				failure:function(form,action){
					Ext.Msg.alert("系统提示",action.result.msg);
				}
			});
		}
	}
	/**导入数据**/
	leadingIn=function(){
		var formPanel=Ext.getCmp("connectFormPanel").getForm();
		var groupItems=Ext.getCmp("inGroup");
		var tableName="";
		for(var i=0;i<groupItems.items.length;i++){
			if(groupItems.items.itemAt(i).checked){
				tableName+=groupItems.items.itemAt(i).name+",";
			}
		}
		if(tableName.length>0){
			tableName=tableName.substring(0,tableName.length-1);
			if(formPanel.isValid()){
				formPanel.findField("tables").setValue(tableName);
				formPanel.submit({
					method:'POST',
					waitMsg : '正在传输数据,请稍等...',
					data:'JSON',
					waitTitle : '系统提示',// 标题
					url:'sys/datasuperpad/leadingInConnection.do',
					params:formPanel.getValue,
					success:function(form,action){
						Ext.Msg.alert("系统提示",action.result.msg);
					},
					failure:function(form,action){
						Ext.Msg.alert("系统提示",action.result.msg);
					}
				});
			}	
		}else{
			Ext.Msg.alert("系统提示","请选择需要导入的表");
		}
	}
	
	exportData = function(){
			var formPanel=Ext.getCmp("connectFormPanel").getForm();
			var groupItems=Ext.getCmp("outGroup");
			var tableName="";
			for(var i=0;i<groupItems.items.length;i++){
				if(groupItems.items.itemAt(i).checked){
					tableName+=groupItems.items.itemAt(i).name+",";
				}
			}
			
			if(tableName.length>0){
				tableName=tableName.substring(0,tableName.length-1);
				if(formPanel.isValid()){
					formPanel.findField("tables").setValue(tableName);
					formPanel.submit({
						method:'POST',
						waitMsg : '正在导出数据,请稍等...',
						data:'JSON',
						waitTitle : '系统提示',// 标题
						url:'sys/datasuperpad/exportData.do',
						params:formPanel.getValue,
						success:function(form,action){
							Ext.Msg.alert("系统提示",action.result.data.instruction);
						},
						failure:function(form,action){
							Ext.Msg.alert("系统提示",action.result.data.instruction);
						}
					});
				}	
			}else{
				Ext.Msg.alert("系统提示","请选择需要导出的表");
			}
		}
	</script>
  </head>
  <body>
  </body>
</html>
