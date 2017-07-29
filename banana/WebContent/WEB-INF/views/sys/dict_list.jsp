<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>数据字典维护</title>
    
	<style type="text/css">
	</style>
	<script type="text/javascript" >
		Ext.onReady(function(){
			var dCode = "";
			
		    var Dict = [
		    	{header: '分组编号',name: 'dCode',type: 'string', width: 80},
		    	{header: '分组名称',name: 'dName',type: 'string', width: 150}, 
		    	{header: '字典编号', name: 'code',type: 'string', width: 80},
	    		{header: '字典名称', name: 'name',type: 'string', width: 150},
	    		{header: '状态', name: 'state',type: 'string', width: 60},
	    		{header: '备注', name: 'remark',type: 'string', width: 300}
		    ];
		    
		    var dictGrid = createExtGrid("数据字典列表", Dict, true, {}, "${basePath}/sys/dict/queryList.do", "multi");
		    dictGrid.getStore().on({
		    	"beforeload": function(store){
		    		var baseParams = {dCode: dCode};
		    		store.baseParams = baseParams;
		    	}
		    });
		    
		    var gridTopbar = dictGrid.getTopToolbar();
		    
		    var dictCombo = Ext.ux.form.ComboBox({
	        	fields: [{name: 'code'}, {name: 'name'}],
	        	url: '${basePath}/sys/dict/getDictGroup.do'
	        });
		    dictCombo.on({
		    	'select': function(index){
		    		dCode = index.value;
		    		dictGrid.getStore().load({params:{start: 0, limit: pageSize}});
		    	}
		    });
		    
		    
		    var gridTbar = [{
	            iconCls: 'addIcon',
	            text: '添加',
	            handler: function(){
		    		showAdd();
	            }
	        },  
	        	{xtype: 'tbseparator'}
	        , {
	            iconCls: 'edit1Icon',
	            text: '更新',
	            handler: function(){
	            	showUpdate();
	            }
	        },
	        	{xtype: 'tbseparator'}
	        , {
	            iconCls: 'deleteIcon',
	            text: '删除',
	            handler: function(){
	            	showDelete();
	            }
	        },
	        	{xtype: 'tbseparator'}, '->'
        	, {
	        	xtype: 'displayfield',
	        	value: '字典分组：'
	        }, dictCombo];
    		
    		gridTopbar.add(gridTbar);
    		
    		new Ext.Viewport({
    			layout : 'border',
				items : [dictGrid]
    		});
    		
    		Ext.ux.form.ComboBox2 = function(config){
    			var store = new Ext.data.JsonStore({ 
    			     url: config.url, 
    			     fields: config.fields
    			    // data: [{code:'1001',name:'性别'},{code:'1002', name:'状态'}]
    			}); 
    			store.load(); 
    			
    			var combo = new Ext.form.ComboBox({
    			    typeAhead: true,
    			    submitValue : true,
    			    allowBlank: Ext.isEmpty(config.allowBlank) ? true : config.allowBlank,
    			    fieldLabel:  Ext.isEmpty(config.fieldLabel) ? "" : config.fieldLabel,
    			    name: Ext.isEmpty(config.name) ? "name" :  config.name,
    			    hiddenName: Ext.isEmpty(config.hiddenName) ? 'code': config.hiddenName,
    			    emptyText: Ext.isEmpty(config.emptyText)? "请选择" : config.emptyText,
    			    editable: Ext.isEmpty(config.editable)? false : config.editable,
    			    triggerAction: 'all',
    			    lazyRender:true,
    			    selectOnFocus:true, 
    			    mode: 'local',
    			    store: store,
    			    valueField: config.fields[0].name,
    			    displayField: config.fields[1].name
    			});
    			
    			return combo;
    		}
 		    
 		    var dictComboField = Ext.ux.form.ComboBox2({
  			 	 name: 'dName',
		    	 hiddenName: 'dCode',
				 fieldLabel: '<span style="color:red">*</span>字典分组',
	        	 fields: [{name: 'code'}, {name: 'name'}],
	        	 url: '${basePath}/sys/dict/getDictGroup.do'
	        });
 		    
    		var dictForm = new Ext.form.FormPanel({
		        frame:true,
		        border: true,
		        buttonAlign: 'center',
		        autoHeight:true,
	            labelAlign: 'right',
	            defaults: {width: 210},
	            defaultType: 'textfield',
		        items: [{
		            	name: 'id',
		            	hidden: true
	            	}, {
	                    fieldLabel: '字典编号',
	                    name: 'code',
	                    allowBlank:false
	                }, {
	                    fieldLabel: '字典名称',
	                    name: 'name',
	                    allowBlank: false
	                }, 
	                	createCombo("状态", "state", [["启用", "启用"], ["禁用", "禁用"]])
					, 
	                	dictComboField
	                , {
	                	fieldLabel:'备注',
	                    xtype:'textarea',
	                    name: 'remark',
	                    height: 50,
	                    width: 400
	                }
	            ],
		        buttons: [{
	    			id: 'btn_form_save',
		            iconCls: 'saveIcon',
		            text: '保存',
		            handler: function(){
		            	Ext.ux.Form.submitForm(dictForm, "${basePath}/sys/dict/save.do", 
		            		function(form, action){
		            			Ext.Msg.alert('消息', action.result.data.instruction);
		            			Ext.MessageBox.confirm('确认', '您确定要继续添加吗?', function(btn){
		        					if(btn == 'yes'){
		        						dictForm.getForm().reset();
		        					}else{
		        						dictWin.hide();
		        					}
		            			})
		            			
		            			dictGrid.getStore().load();
		            		}		
		            	);
		            }
		        }, {
		        	id: 'btn_form_update',
		            iconCls: 'edit1Icon',
		            text: '更新',
		            handler: function(){
		            	Ext.ux.Form.submitForm(dictForm, "${basePath}/sys/dict/update.do", 
			            		function(form, action){
			            			Ext.Msg.alert('消息', action.result.data.instruction);
			            			
			            			dictGrid.getStore().load();
			            			dictWin.hide();
		            			}		
			            	);
		            }
		        }, {
		        	id: 'btn_form_cancle',
		            iconCls: 'deleteIcon',
		            text: '取消',
		            handler: function(){
		            	dictForm.getForm().reset();
		            	dictWin.hide();
		            }
		        }]
    		});
    		
    		dictWin = createWindow("添加数据字典", dictForm, 600, 262);
    		
    		function showAdd(){
    			dictWin.setTitle("添加数据字典信息");
    			Ext.getCmp("btn_form_save").show();
    			Ext.getCmp("btn_form_update").hide();
    			dictWin.show();
    			dictForm.getForm().reset();
    		}
    		
    		function showUpdate(){
    			dictWin.setTitle("更新数据字典信息");
    			
    			//准备加载数据
    			var records = dictGrid.getSelectionModel().getSelections();
    			if(records.length == 0){
    				Ext.ux.Msg.warning("请选择一条记录"); 
    				return;
    			}else if(records.length > 1){
    				Ext.ux.Msg.warning("您选择的记录过多，本操作不支持批量修改，您只能选择一条记录！");
    				return;
    			}
    			
    			Ext.getCmp("btn_form_save").hide();
    			Ext.getCmp("btn_form_update").show();
    			dictWin.show();
    			
    			Ext.ux.Form.loadForm(dictForm, "${basePath}/sys/dict/find.do", {id: records[0].id});
    			
    		}
    		
    		function showDelete(){
    			var records = dictGrid.getSelectionModel().getSelections();
    			if(records.length == 0){
    				Ext.ux.Msg.warning("请选择一条记录"); 
    				return;
    			}
    			
    			Ext.MessageBox.confirm('确认', '您确定要删除所选择的记录吗?', function(btn){
					if(btn == 'yes'){
						var ids = [];
        				for(var i = 0; i < records.length; i++){
        					ids.push(records[i].id);
        				}
        				Ext.ux.Ajax.request("${basePath}/sys/dict/delete.do", {ids: ids}, function(action, form){
   							var response =  Ext.util.JSON.decode(action.responseText);
   							if(Ext.isEmpty(response)){
								Ext.ux.Msg.info("删除成功");
							}else{
								Ext.ux.Msg.info(response.data.instruction);
							}
   							dictGrid.getStore().reload();
   						});
					}
    			})
    		}
		});
		
	</script>

  </head>
  
  <body>
  </body>
</html>
