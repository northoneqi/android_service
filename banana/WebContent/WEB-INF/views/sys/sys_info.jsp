<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>数据库备份与还原模块</title>
    
    <script type="text/javascript">
    	Ext.onReady(function(){
    		var resourceForm = new Ext.form.FormPanel({
		        frame:true,
		        buttonAlign: 'center',
	            labelAlign: 'right',
	            region: "center",
	            width:300,
	            layout:'fit',
	            height:600,
	            defaults: {width: 400},
	            layout: 'column',
	            defaultType: 'textfield',
		        items: [{
		        	xtype:'fieldset',
			        collapsible: true,
			        height:300,
			        title: '使用单位',
			        layout: 'form',
			        defaultType : 'textfield',
			        columnWidth: 1.0,
			        items: [{
			            	name: 'id',
			            	hidden: true,
			            	value:'${department.id}'
		            	},{
							name:'state',
							hidden:true,
							value:'${department.state}'
			            },{
							name:'united',
							hidden:true,
							value:'${department.united}'
			            },{
							name:'unitCode',
							hidden:true,
							value:'${department.unitCode}'
			            },{
							name:'unitName',
							hidden:true,
							value:'${department.unitName}'
			            },{
		                    fieldLabel: '单位编号',
		                    name: 'code',
		                    allowBlank:false,
		                    readOnly:true,
		                    width: 400,
		                    value:'${department.code}'
		                }, {
		                    fieldLabel: '单位名称',
		                    name: 'name',
		                    allowBlank: false,
		                    width: 400,
		                    value:'${department.name}'
		                }, {
		                	fieldLabel:'备注',
		                    xtype:'textarea',
		                    name: 'remark',
		                    height: 50,
		                    width: 500,
		                    value: "${sysInfo.remark}"
		                }
	            	]
	            }],
		        buttons: [{
		        	id: 'btn_form_update',
		            iconCls: 'edit1Icon',
		            text: '更新',
		            handler: function(){
		            	Ext.ux.Form.submitForm(resourceForm, "${basePath}/sys/department/update.do", 
			            		function(form, action){
			            			Ext.Msg.alert('消息', action.result.data.instruction);
		            			}		
			            	);
		            }
		        }, {
		        	id: 'btn_form_cancle',
		            iconCls: 'deleteIcon',
		            text: '取消',
		            handler: function(){
		            }
		        }]
    		});
    		
    		new Ext.Viewport({
    			layout: 'fit',
    			frame:true,
    			items: [resourceForm]
    		});
    	});
    </script>
  </head>
  
  <body>
  </body>
</html>
