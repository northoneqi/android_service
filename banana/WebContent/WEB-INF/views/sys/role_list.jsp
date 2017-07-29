<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>角色维护</title>
    
	<style type="text/css"></style>
	<script type="text/javascript" src="${basePath}/ext/ux/treegrid/TreeGridNodeUI.js"></script>
	<script type="text/javascript" >
		var roleId = "";
		var roleCode = "";
		Ext.onReady(function(){
		    var Role = [
		    	{header: '编号',name: 'code',type: 'string', width: 80}, 
	    		{header: '角色名称', name: 'name',type: 'string', width: 120}, 
	    		{header: '状态', name: 'state',type: 'string', width: 50},
	    		{header: '备注', name: 'remark',type: 'String', width: 120}
		    ];
		    
		    var roleGrid = createExtGrid("角色列表", Role, true, {}, "${basePath}/sys/role/queryList.do", "radio");
		    roleGrid.region="west";
		    roleGrid.width="40%";
		    roleGrid.getStore().on({
		    	"beforeload": function(store){
		    		var baseParams = {};
		    		store.baseParams = baseParams;
		    	},
		    	"load": function(){
		    		//roleGrid.getSelectionModel().selectFirstRow();
		    	}
		    });
		    
		    roleGrid.getSelectionModel().on({
		    	"rowselect": function(selModel, rowIndex, record){
		    		roleId = record.id;
		    		roleCode = record.data.code;
		    		gridTreePanel.root.reload();
    				gridTreePanel.expandAll();
    				
    				departmentGridTreePanel.root.reload();
    				departmentGridTreePanel.expandAll();
		    	}
		    });
		    
		    
		    var gridTopbar = roleGrid.getTopToolbar();
		    
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
    		
    		var treeloader = new Ext.tree.TreeLoader({
    			dataUrl: "${basePath}/sys/role/getAllResource.do",
    			autoLoad: false,
    			baseParams: {}
    		});
    		
    		treeloader.on({
    			"beforeload": function(loader){
    				var baseParams = loader.baseParams;
    				baseParams.roleCode = roleCode;
    				loader.baseParams = baseParams;
    			}
    		});
    		
    		
    		var gridTreePanel = new Ext.ux.tree.TreeGrid({
    	        title: '资源列表',
    	        region: 'center',
    	        width: '50%',
    	        collapsible: true,
    	        loadMask : {msg:'正在加载数据，请稍侯...'},
    	        columns:[{
    	            header: '资源名称',
    	            width: 230,
    	            dataIndex: 'name',
    	            align: 'center'
    	        }],
    	        tbar: [{
    	            iconCls: 'addIcon',
    	            text: '分配功能权限',
    	            handler: function(){
    		    		var nodes = gridTreePanel.getChecked();
    	            	var list = [];
    	            	for(var i = 0; i < nodes.length; i++){
    	            		list.push(nodes[i].id);
    	            	};
    	            	
    	            	Ext.ux.Ajax.request("${basePath}/sys/role/addResource.do", {roleId: roleId,resourceIds: list}, function(action, form){
   							var response =  Ext.util.JSON.decode(action.responseText);
   							if(Ext.isEmpty(response)){
								Ext.ux.Msg.info("删除成功");
							}else{
								Ext.ux.Msg.info(response.data.instruction);
							}
   							roleGrid.getStore().reload();
   						});
    	            }
    	        },  
    	        	{xtype: 'tbseparator'}
    	        ],
    	        tools : [ {
    				id : 'refresh',
    				handler : function() {
    					gridTreePanel.root.reload();
    					gridTreePanel.expandAll();
    				}
    			} ],
				loader: treeloader
    	       // dataUrl: '${basePath}/sys/resource/queryList.do'
    	    });
    		
    		var departmentTreeloader = new Ext.tree.TreeLoader({
    			dataUrl: "${basePath}/sys/role/getAllDepartment.do",
    			autoLoad: false,
    			baseParams: {}
    		});
    		
    		departmentTreeloader.on({
    			"beforeload": function(loader){
    				var baseParams = loader.baseParams;
    				baseParams.roleCode = roleCode;
    				loader.baseParams = baseParams;
    			}
    		});
    		
    		
    		var departmentGridTreePanel = new Ext.ux.tree.TreeGrid({
    	        title: '部门列表',
    	        region: 'east',
    	        width: '30%',
    	        collapsible: true,
    	        loadMask : {msg:'正在加载数据，请稍侯...'},
    	        columns:[{
    	            header: '部门名称',
    	            width: 230,
    	            dataIndex: 'name',
    	            align: 'center'
    	        }],
    	        tbar: [{
    	            iconCls: 'addIcon',
    	            text: '分配数据权限',
    	            handler: function(){
    		    		var nodes = departmentGridTreePanel.getChecked();
    	            	var list = [];
    	            	for(var i = 0; i < nodes.length; i++){
    	            		list.push(nodes[i].id);
    	            	};
    	            	
    	            	Ext.ux.Ajax.request("${basePath}/sys/role/addDepartment.do", {roleId: roleId,departmentIds: list}, function(action, form){
   							var response =  Ext.util.JSON.decode(action.responseText);
   							if(Ext.isEmpty(response)){
								Ext.ux.Msg.info("删除成功");
							}else{
								Ext.ux.Msg.info(response.data.instruction);
							}
   							roleGrid.getStore().reload();
   						});
    	            }
    	        },  
    	        	{xtype: 'tbseparator'}
    	        ],
    	        tools : [ {
    				id : 'refresh',
    				handler : function() {
    					gridTreePanel.root.reload();
    					gridTreePanel.expandAll();
    				}
    			} ],
				loader: departmentTreeloader
    	    });
    		
    		
    		//var departmentTree = createTreePanel("查询部门范围", "${basePath}/sys/role/getAllDepartment.do",{}, false);
		    //departmentTree.region = "east";
		    //departmentTree.width = "30%";
		    
    		
    		//gridTreePanel.getRootNode().expandChildNodes(false);
    		//gridTreePanel.expandAll();
    		
    		function selectChildNodes(Node,checked){ 
    	        Node.eachChild(function(node){  
	            node.attributes.checked = checked;  
	            node.getUI().toggleCheck(checked);  
	            if(!node.leaf){  
	                if(!node.loaded){  
	                    node.expand();  
	                }  
	                //this.selectChildNodes(node,checked);  
	                if(node.hasChildNodes()){
    					selectChildNodes(node,checked);//选定子节点  
    				}
	            }  
	        },this);             
    	    }
    		
    		function selectParentNodes(Node, checked){
    			var boo = false;
    			Node.eachChild(function(node){
    	            if(node.attributes.checked){
    	            	boo = true;
    	            	return true;
    	            } 
    	        },this);
    			
    			if(boo) {
    				Node.attributes.checked = true;  
    	            Node.getUI().toggleCheck(true); 
    			}else{
    				Node.attributes.checked = false;  
    	            Node.getUI().toggleCheck(false); 
    			}
    			
    		}
    		
    		gridTreePanel.on({
    			'beforeload': function(node){
    				treeloader.baseParams = {parentCode: node.attributes.code}
    			}, 
    			'checkchange': function(node,checked){
    				//console.log(node);
    				if(!node.loaded){//子节点是否加载  
                        node.expand();  
                    }
    				
    				//selectParentNodes(node,checked);
    				
    				if(node.getDepth() > 1){  
                        //selectParentNodes(node.parentNode);//是否选定父节点  
                    }
    				
    				if(node.hasChildNodes()){
    					selectChildNodes(node,checked);//选定子节点  
    				}
    				/*if(node.parentNode){  
                        selectParentNodes(node.parentNode,checked);//是否选定父节点  
                    } */ 
    			}
    		});
    		
    		
    		departmentGridTreePanel.on({
    			'beforeload': function(node){
    				departmentTreeloader.baseParams = {parentCode: node.attributes.code}
    			}, 
    			'checkchange': function(node,checked){
    				//console.log(node);
    				if(!node.loaded){//子节点是否加载  
                        node.expand();  
                    }
    				
    				//selectParentNodes(node,checked);
    				
    				if(node.getDepth() > 1){  
                        //selectParentNodes(node.parentNode);//是否选定父节点  
                    }
    				
    				if(node.hasChildNodes()){
    					selectChildNodes(node,checked);//选定子节点  
    				}
    				/*if(node.parentNode){  
                        selectParentNodes(node.parentNode,checked);//是否选定父节点  
                    } */ 
    			}
    		});
    		

    		new Ext.Viewport({
    			layout : 'border',
				items : [roleGrid, gridTreePanel, departmentGridTreePanel]
    		});
    		
    		var roleForm = new Ext.form.FormPanel({
		        frame:true,
		        border: true,
		        buttonAlign: 'center',
		        region: 'center',
		        autoHeight:true,
	            labelAlign: 'right',
	            layout: 'form',
		        items: [{
		        	layout:'column',
		        	items:[{
						margins:'1 1 1 1 ',
						width:280,
						layout:'form',
						border:false,
						defaults : {allowBlank : false,xtype : 'textfield', msgTarget : 'side',labelWidth:80},
	         			labelAlign : 'right',
	         			items: [{
				            	name: 'id',
				            	hidden: true,
				            	allowBlank: true
			            	},{
			                    fieldLabel: '角色编号',
			                    name: 'code',
			                    allowBlank:false
			                }, {
			                    fieldLabel: '角色名称',
			                    name: 'name',
			                    allowBlank: false
			                }, 
			                	createCombo("状态", "state", [["启用", "启用"], ["禁用", "禁用"]])
			                , {
			                	fieldLabel:'备注',
			                    xtype:'textarea',
			                    name: 'remark',
			                    allowBlank: true,
			                    height: 50,
			                    width: 400
			                }]
		        	}]
		        }],
		        buttons: [{
	    			id: 'btn_form_save',
		            iconCls: 'saveIcon',
		            text: '保存',
		            handler: function(){
		            	Ext.ux.Form.submitForm(roleForm, "${basePath}/sys/role/save.do", 
		            		function(form, action){
		            			Ext.Msg.alert('消息', action.result.data.instruction);
		            			Ext.MessageBox.confirm('确认', '您确定要继续添加吗?', function(btn){
		        					if(btn == 'yes'){
		        						roleForm.getForm().reset();
		        					}else{
		        						roleWin.hide();
		        					}
		            			})
		            			
		            			roleGrid.getStore().load();
		            		}		
		            	);
		            }
		        }, {
		        	id: 'btn_form_update',
		            iconCls: 'edit1Icon',
		            text: '更新',
		            handler: function(){
		            	Ext.ux.Form.submitForm(roleForm, "${basePath}/sys/role/update.do", 
			            		function(form, action){
			            			Ext.Msg.alert('消息', action.result.data.instruction);
			            			
			            			roleGrid.getStore().load();
			            			roleWin.hide();
		            			}		
			            	);
		            }
		        }, {
		        	id: 'btn_form_cancle',
		            iconCls: 'deleteIcon',
		            text: '取消',
		            handler: function(){
		            	roleForm.getForm().reset();
		            	roleWin.hide();
		            }
		        }]
    		});
    		
    		roleWin = createWindow("添加角色", roleForm, 460, 260);
    		
    		function showAdd(){
    			roleWin.setTitle("添加角色信息");
    			Ext.getCmp("btn_form_save").show();
    			Ext.getCmp("btn_form_update").hide();
    			roleWin.show();
    			roleForm.getForm().reset();
    		}
    		
    		function showUpdate(){
    			roleWin.setTitle("更新角色信息");
    			
    			//准备加载数据
    			var records = roleGrid.getSelectionModel().getSelections();
    			if(records.length == 0){
    				Ext.ux.Msg.warning("请选择一条记录"); 
    				return;
    			}else if(records.length > 1){
    				Ext.ux.Msg.warning("您选择的记录过多，本操作不支持批量修改，您只能选择一条记录！");
    				return;
    			}
    			
    			Ext.getCmp("btn_form_save").hide();
    			Ext.getCmp("btn_form_update").show();
    			roleWin.show();
    			
    			Ext.ux.Form.loadForm(roleForm, "${basePath}/sys/role/find.do", {id: records[0].id});
    			
    		}
    		
    		function showDelete(){
    			var records = roleGrid.getSelectionModel().getSelections();
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
        				Ext.ux.Ajax.request("${basePath}/sys/role/delete.do", {ids: ids}, function(action, form){
   							var response =  Ext.util.JSON.decode(action.responseText);
   							if(Ext.isEmpty(response)){
								Ext.ux.Msg.info("删除成功");
							}else{
								Ext.ux.Msg.info(response.data.instruction);
							}
   							roleGrid.getStore().reload();
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
