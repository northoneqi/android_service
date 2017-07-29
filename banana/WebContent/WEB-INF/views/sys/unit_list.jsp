<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>单位部门维护</title>
    
	<style type="text/css">
	</style>
	<script type="text/javascript" >
		Ext.onReady(function(){
			
			Ext.QuickTips.init();
			
			var curUnit;
			
		    var Unit = [
		    	{header: '编号',name: 'code',type: 'string'}, 
	    		{header: '单位名称', name: 'name',type: 'string'}, 
	    		{header: '上级单位编号', name: 'unitCode',type: 'string'},
	    		{header: '上级单位名称', name: 'unitName',type: 'string'},
	    		{header: '状态', name: 'state',type: 'string'},
	    		{header: '是否为单位', name: 'united',type: 'boolean', hide: true}
		    ];
		    
		    var unitGrid = createExtGrid("单位列表", Unit, true, {isUnit: true}, "${basePath}/sys/department/queryList.do", "multi");
		    unitGrid.getStore().on({
		    	"beforeload": function(store){
		    		if(Ext.isEmpty(curUnit)){
	    				curUnit = unitTree.getRootNode().firstChild.attributes;
	    			}
		    		var baseParams = {unitCode: curUnit.code, isUnit: true};
		    		store.baseParams = baseParams;
		    	}
		    });
		    
		    var gridTopbar = unitGrid.getTopToolbar();
		    
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
	        	/*{xtype: 'tbseparator'}
	        , {
	            iconCls: 'deleteIcon',
	            text: '删除',
	            handler: function(){
	            }
	        },*/
	        	{xtype: 'tbseparator'}
	        ];
    		
    		gridTopbar.add(gridTbar);
    		
    		var unitTree = createTreePanel("组织机构", "${basePath}/sys/department/queryTree.do?isUnit=1",{}, true);

    		unitTree.on({
    			"click": function(node){
    				//加载该单位的子单位
    				curUnit = node.attributes;
    				unitGrid.setTitle("当前单位->"+curUnit.text);
    				unitGrid.getStore().load({params:{start: 0, limit: pageSize, unitCode: curUnit.code, united: true}});
    			}
    		});
    		
    		new Ext.Viewport({
    			layout : 'border',
				items : [unitTree, unitGrid]
    		});
    		
    		var unitForm = new Ext.form.FormPanel({
    			//labelWidth: 75, // label settings here cascade unless overridden
		        frame:true,
		        border: true,
		        //bodyStyle:'padding:5px 5px 0',
		        buttonAlign: 'center',
		        items: [{
		            xtype:'fieldset',
		            autoShow: true,
		            collapsible: true,
		            title: '单位信息',
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
		                    fieldLabel: '单位编号',
		                    name: 'code',
		                    allowBlank:false
		                }, {
		                    fieldLabel: '单位名称',
		                    name: 'name',
		                    allowBlank: false
		                }, 
		                	createCombo("状态", "state", [["启用", "启用"], ["禁用", "禁用"]])
		                ,{
		                	name: 'united',
		                	value: true,
		                	hidden: true
		                }, {
		                	name: 'unitCode',
		                	hidden: true
		                }, {
		                    fieldLabel: '上级单位',
		                    name: 'unitName',
		                    readOnly: true
		                }
		            ]
		        }],
		        buttons: [{
	    			id: 'btn_form_save',
		            iconCls: 'saveIcon',
		            text: '保存',
		            handler: function(){
		            	Ext.ux.Form.submitForm(unitForm, "${basePath}/sys/department/save.do", 
		            		function(form, action){
		            			Ext.Msg.alert('消息', action.result.data.instruction);
		            			Ext.MessageBox.confirm('确认', '您确定要继续添加吗?', function(btn){
		        					if(btn == 'yes'){
		        						unitForm.getForm().reset();
		        					}else{
		        						unitWin.hide();
		        					}
		            			})
		            			
		            			unitTree.root.reload();
		            			unitTree.expandAll();
		            			unitGrid.getStore().load({params:{limit: pageSize, unitCode: curUnit.code, united: true}});
		            		}		
		            	);
		            }
		        }, {
		        	id: 'btn_form_update',
		            iconCls: 'edit1Icon',
		            text: '更新',
		            handler: function(){
		            	Ext.ux.Form.submitForm(unitForm, "${basePath}/sys/department/update.do", 
			            		function(form, action){
			            			Ext.Msg.alert('消息', action.result.data.instruction);
			            			
			            			unitTree.root.reload();
			            			unitTree.expandAll();
			            			unitGrid.getStore().load({params:{limit: pageSize, unitCode: curUnit.code, united: true}});
			            			unitWin.hide();
		            			}		
			            	);
		            }
		        }, {
		        	id: 'btn_form_cancle',
		            iconCls: 'deleteIcon',
		            text: '取消',
		            handler: function(){
		            	unitForm.getForm().reset();
		            	unitWin.hide();
		            }
		        }]
    		});
    		
    		unitWin = createWindow("添加单位", unitForm, 400, 228);
    		
    		function showAdd(){
    			unitWin.setTitle("添加单位信息");
    			Ext.getCmp("btn_form_save").show();
    			Ext.getCmp("btn_form_update").hide();
    			if(Ext.isEmpty(curUnit)){
    				curUnit = unitTree.getRootNode().firstChild.attributes;
    			}
    			
    			unitForm.getForm().findField("unitCode").setValue(curUnit.code);
    			unitForm.getForm().findField("unitName").setValue(curUnit.text);
    			unitWin.show();
    		}
    		
    		function showUpdate(){
    			unitWin.setTitle("更新单位信息");
    			
    			//准备加载数据
    			var records = unitGrid.getSelectionModel().getSelections();
    			if(records.length == 0){
    				Ext.ux.Msg.warning("请选择一条记录"); 
    				return;
    			}else if(records.length > 1){
    				Ext.ux.Msg.warning("您选择的记录过多，本操作不支持批量修改，您只能选择一条记录！");
    				return;
    			}
    			
    			Ext.ux.Form.loadForm(unitForm, "${basePath}/sys/department/find.do", {id: records[0].id});
    			Ext.getCmp("btn_form_save").hide();
    			Ext.getCmp("btn_form_update").show();
    			unitWin.show();
    		}
		});
		
	</script>

  </head>
  
  <body>
  </body>
</html>
