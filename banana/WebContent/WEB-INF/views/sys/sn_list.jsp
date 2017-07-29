<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>单据编号维护</title>
    
	<style type="text/css">
	</style>
	<script type="text/javascript" >
		Ext.onReady(function(){
			
			Ext.QuickTips.init();
			
		    var FieldValue = [
	    		{header: '流水号类型', name: 'snType',type: 'string', width: 200},
	    		//{header: '业务含义', name: 'chineseMean',type: 'string', width: 200},
	    		{header: '当前日期', name: 'date',type: 'string'},
	    		{header: '当前值', name: 'maxValue',type: 'string'},
	    		{header: '前缀', name: 'prefix',type: 'string'},
	    		{header: '后缀', name: 'suffix',type: 'string'},
	    		{header: '流水号长度', name: 'length',type: 'string'},
	    		{header: '流水号时间规则', name: 'rule',type: 'string'}
		    ];
		    
		    var fieldValueGrid = createExtGrid("单据编号列表", FieldValue, true, {}, "${basePath}/sys/sn/query.do", "multi");
		    
		    var gridTopbar = fieldValueGrid.getTopToolbar();
		    
		    var gridTbar = [{
	            iconCls: 'edit1Icon',
	            text: '更新',
	            handler: function(){
	            	showUpdate();
	            }
	        },
	        	{xtype: 'tbseparator'}
	        ];
    		
    		gridTopbar.add(gridTbar);
    		
    		new Ext.Viewport({
    			layout : 'border',
				items : [fieldValueGrid]
    		});
    		
    		var fieldValueForm = new Ext.form.FormPanel({
		        frame:true,
		        border: true,
		        buttonAlign: 'center',
		        items: [{
		            xtype:'fieldset',
		            autoShow: true,
		            collapsible: true,
		            title: '流水号信息',
		            autoHeight:true,
		            labelAlign: 'right',
		            defaults: {width: 210},
		            defaultType: 'textfield',
		            animCollapse: true,
		            collapsed: false,
		            items :[{
			            	name: 'id',
			            	hidden: true
		            	},{
		                    fieldLabel: '流水号类型',
		                    name: 'snType',
		                    style: 'background: #F6F6F6;',
		                    allowBlank:false,
		                    readOnly: true
		                }/*,{
		                    fieldLabel: '业务含义',
		                    name: 'chineseMean',
		                    style: 'background: #F6F6F6;',
		                    allowBlank:false,
		                    readOnly: true
		                }*/,{
		                    fieldLabel: '当前日期',
		                    name: 'date',
		                    style: 'background: #F6F6F6;',
		                    allowBlank: false
		                }, {
		                    fieldLabel: '当前值',
		                    name: 'maxValue',
		                    style: 'background: #F6F6F6;',
		                    readOnly: true
		                }, {
		                    fieldLabel: '前缀',
		                    name: 'prefix'
		                }, {
		                    fieldLabel: '后缀',
		                    name: 'suffix'
		                }, {
		                	xtype: 'numberfield',
		                    fieldLabel: '当流水号长度',
		                    minValue: 2,
		                    maxValue: 10,
		                    name: 'length'
		                }, Ext.ux.form.ComboBox({
					    	 id: 'ruleCombo',
 							 hiddenName: 'rule',
 							 fieldLabel: '<span style="color:red">*</span>流水号时间规则',
 				        	 fields: [{name: 'code'}, {name: 'name'}],
 				        	 url: 'sys/sn/getRule.do'
 				        }),
		            ]
		        }],
		        buttons: [{
		        	id: 'btn_form_update',
		            iconCls: 'edit1Icon',
		            text: '更新',
		            handler: function(){
		            	Ext.ux.Form.submitForm(fieldValueForm, "${basePath}/sys/sn/update.do", 
			            		function(form, action){
			            			Ext.Msg.alert('消息', action.result.data.instruction);
			            			
			            			fieldValueGrid.getStore().load({params:{limit: pageSize}});
			            			fieldValueWin.hide();
		            			}		
			            	);
		            }
		        }, {
		        	id: 'btn_form_cancle',
		            iconCls: 'deleteIcon',
		            text: '取消',
		            handler: function(){
		            	fieldValueForm.getForm().reset();
		            	fieldValueWin.hide();
		            }
		        }]
    		});
    		
    		fieldValueWin = createWindow("添加单据编号", fieldValueForm, 500, 310);
    		
    		function showUpdate(){
    			fieldValueWin.setTitle("更新单据编号信息");
    			
    			//准备加载数据
    			var records = fieldValueGrid.getSelectionModel().getSelections();
    			if(records.length == 0){
    				Ext.ux.Msg.warning("请选择一条记录"); 
    				return;
    			}else if(records.length > 1){
    				Ext.ux.Msg.warning("您选择的记录过多，本操作不支持批量修改，您只能选择一条记录！");
    				return;
    			}
    			
    			Ext.ux.Form.loadForm(fieldValueForm, "${basePath}/sys/sn/find.do", {id: records[0].id});
    			fieldValueWin.show();
    		}
		});
		
	</script>

  </head>
  
  <body>
  </body>
</html>
