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
			
		    var Department = [
		    	{header: '编号',name: 'code',type: 'string'}, 
	    		{header: '部门名称', name: 'name',type: 'string'}, 
	    		{header: '所属单位编号', name: 'unitCode',type: 'string', hide: true},
	    		{header: '所属单位名称', name: 'unitName',type: 'string'},
	    		{header: '状态', name: 'state',type: 'string'},
	    		{header: '是否为单位', name: 'united',type: 'boolean', hide: true}
		    ];
		    
		    var departmentGrid = createExtGrid("部门列表", Department, true, {}, "${basePath}/sys/department/queryList.do", "multi");
		    departmentGrid.getStore().on({
		    	"beforeload": function(store){
		    		if(Ext.isEmpty(curUnit)){
	    				curUnit = departmentTree.getRootNode().firstChild.attributes;
	    			}
		    		var baseParams = {unitCode: curUnit.code, isUnit: false};
		    		store.baseParams = baseParams;
		    	}
		    });
		    
		    var gridTopbar = departmentGrid.getTopToolbar();
		    
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
	        	{xtype: 'tbseparator'}
	        ];
    		
    		gridTopbar.add(gridTbar);
    		
    		var departmentTree = createTreePanel("组织机构", "${basePath}/sys/department/queryTree.do",{}, true);

    		departmentTree.on({
    			"click": function(node){
    				//加载该单位的子单位
    				curUnit = node.attributes;
    				departmentGrid.setTitle("当前单位->"+curUnit.text);
    				departmentGrid.getStore().load({params:{start: 0, limit: pageSize, unitCode: curUnit.code, united: true}});
    			}
    		});
    		
    		new Ext.Viewport({
    			layout : 'border',
				items : [departmentTree, departmentGrid]
    		});
    		
    		var departmentForm = new Ext.form.FormPanel({
    			//labelWidth: 75, // label settings here cascade unless overridden
		        frame:true,
		        border: true,
		        //bodyStyle:'padding:5px 5px 0',
		        buttonAlign: 'center',
		        items: [{
		            xtype:'fieldset',
		            autoShow: true,
		            collapsible: true,
		            title: '部门信息',
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
		                    fieldLabel: '部门编号',
		                    name: 'code',
		                    allowBlank:false
		                }, {
		                    fieldLabel: '部门名称',
		                    name: 'name',
		                    allowBlank: false
		                }, 
		                	createCombo("状态", "state", [["启用", "启用"], ["禁用", "禁用"]])
		                ,{
		                	name: 'unitCode',
		                	hidden: true
		                }, {
		                    fieldLabel: '所属单位',
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
		        		console.log(departmentForm.getForm().getValues());
		            	Ext.ux.Form.submitForm(departmentForm, "${basePath}/sys/department/save.do?united=false", 
		            		function(form, action){
		            			Ext.Msg.alert('消息', action.result.data.instruction);
		            			Ext.MessageBox.confirm('确认', '您确定要继续添加吗?', function(btn){
		        					if(btn == 'yes'){
		        						departmentForm.getForm().reset();
		        					}else{
		        						departmentWin.hide();
		        					}
		            			})
		            			
		            			departmentTree.root.reload();
		            			departmentTree.expandAll();
		            			departmentGrid.getStore().load({params:{limit: pageSize}});
		            		}		
		            	);
		            }
		        }, {
		        	id: 'btn_form_update',
		            iconCls: 'edit1Icon',
		            text: '更新',
		            handler: function(){
		            	Ext.ux.Form.submitForm(departmentForm, "${basePath}/sys/department/update.do?united=false", 
			            		function(form, action){
			            			Ext.Msg.alert('消息', action.result.data.instruction);
			            			
			            			departmentWin.hide();
			            			departmentTree.root.reload();
			            			departmentTree.expandAll();
			            			departmentGrid.getStore().load({params:{limit: pageSize}});
			            			
		            			}		
			            	);
		            }
		        }, {
		        	id: 'btn_form_cancle',
		            iconCls: 'deleteIcon',
		            text: '取消',
		            handler: function(){
		            	departmentForm.getForm().reset();
		            	departmentWin.hide();
		            }
		        }]
    		});
    		
    		departmentWin = createWindow("添加部门", departmentForm, 400, 228);
    		
    		function showAdd(){
    			departmentWin.setTitle("添加部门信息");
    			Ext.getCmp("btn_form_save").show();
    			Ext.getCmp("btn_form_update").hide();
    			if(Ext.isEmpty(curUnit)){
    				curUnit = departmentTree.getRootNode().firstChild.attributes;
    			}
    			
    			departmentWin.show();
    			departmentForm.getForm().reset();
    			departmentForm.getForm().findField("unitCode").setValue(curUnit.code);
    			departmentForm.getForm().findField("unitName").setValue(curUnit.text);
    		}
    		
    		function showUpdate(){
    			departmentWin.setTitle("更新部门信息");
    			
    			//准备加载数据
    			var records = departmentGrid.getSelectionModel().getSelections();
    			if(records.length == 0){
    				Ext.ux.Msg.warning("请选择一条记录"); 
    				return;
    			}else if(records.length > 1){
    				Ext.ux.Msg.warning("您选择的记录过多，本操作不支持批量修改，您只能选择一条记录！");
    				return;
    			}
    			departmentForm.getForm().reset();
    			Ext.ux.Form.loadForm(departmentForm, "${basePath}/sys/department/find.do", {id: records[0].id});
    			Ext.getCmp("btn_form_save").hide();
    			Ext.getCmp("btn_form_update").show();
    			departmentWin.show();
    		}
    		
    		function showDelete(){
    			var records = departmentGrid.getSelectionModel().getSelections();
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
        				Ext.ux.Ajax.request("${basePath}/sys/department/delete.do", {ids: ids}, function(action, form){
   							var response =  Ext.util.JSON.decode(action.responseText);
   							if(Ext.isEmpty(response)){
								Ext.ux.Msg.info("删除成功");
							}else{
								Ext.ux.Msg.info(response.data.instruction);
							}
   							departmentTree.root.reload();
			            	departmentTree.expandAll();
			            	departmentGrid.getStore().load({params:{limit: pageSize}});
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
