<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>资源维护</title>
    
	<style type="text/css">
	</style>
	<script type="text/javascript" >
		Ext.onReady(function(){
			Ext.QuickTips.init();
			var parentCode = '888';
		  /*  var Resource = [
		    	{header: '编号',name: 'code',type: 'string', width: 80}, 
	    		{header: '资源名称', name: 'name',type: 'string', width: 120}, 
	    		{header: '资源类型', name: 'type',type: 'string', width: 120},
	    		{header: '状态', name: 'state',type: 'string', width: 50},
	    		{header: '备注', name: 'remark',type: 'String', width: 120}
		    ];
		    
		    var resourceGrid = createExtGrid("资源列表", Resource, true, {}, "${basePath}/sys/resource/queryList.do", "multi");
		    resourceGrid.getStore().on({
		    	"beforeload": function(store){
		    		var baseParams = {};
		    		store.baseParams = baseParams;
		    	}
		    });
		    
		    var gridTopbar = resourceGrid.getTopToolbar();
		    */
		    var gridTbar = [{
	            iconCls: 'addIcon',
	            text: '初始化资源',
	            handler: function(){
		    		//showAdd();
		    		Ext.MessageBox.confirm('确认', '您确定要执行初始化资源操作吗?', function(btn){
						if(btn == 'yes'){
							Ext.ux.Ajax.request("${basePath}/sys/resource/init.do", {}, function(action, form){
								var response =  Ext.util.JSON.decode(action.responseText);
								if(Ext.isEmpty(response)){
									Ext.ux.Msg.info("初始化成功");
									treeloader.load();
								}else{
									Ext.ux.Msg.info(response.data.instruction);
								}
								gridTreePanel.root.reload();
		    					gridTreePanel.expand();
							});
						}
		    		});
	            }
	        },  
	        	{xtype: 'tbseparator'}
	        , {
	            iconCls: 'addIcon',
	            text: '增加资源',
	            handler: function(){
	            	showAddResource();
	            }
	        },
	        	{xtype: 'tbseparator'}
	        , {
	            iconCls: 'edit1Icon',
	            text: '修改资源',
	            handler: function(){
	            	showUpdate();
	            }
	        },
	        	{xtype: 'tbseparator'}
	        , {
	            iconCls: 'deleteIcon',
	            text: '删除资源',
	            handler: function(){
	            	showDelete();
	            }
	        }
	        ];
    		
    		//gridTopbar.add(gridTbar);
    		
    		var treeloader = new Ext.tree.TreeLoader({
    			dataUrl: "${basePath}/sys/resource/queryList.do",
    			baseParams: {}
    		});
    		
    		gridTreePanel = new Ext.ux.tree.TreeGrid({
    	        title: '资源列表',
    	        region: 'center',
    	        tbar: gridTbar,
    	        loadMask : {msg:'正在加载数据，请稍侯...'},
    	        columns:[{
    	            header: '编号',
    	            dataIndex: 'code',
    	            width: 230
    	        },{
    	            header: '资源名称',
    	            width: 200,
    	            dataIndex: 'name',
    	            align: 'left'
    	        },{
    	            header: '资源URL',
    	            width: 300,
    	            dataIndex: 'url',
    	            align: 'left'
    	        },{
    	            header: '类型',
    	            width: 150,
    	            dataIndex: 'typeName',
    	            align: 'center',
    	            convert: function(v){
    	            	if(v == 'button'){
    	            		return "按钮";
    	            	}else if(v == 'menu'){
    	            		return '菜单';
    	            	}
    	            	
    	            	return '布局';
    	            }
    	        },{
    	            header: '状态',
    	            width: 60,
    	            dataIndex: 'state',
    	            align: 'center'
    	        },{
    	            header: '备注',
    	            width: 150,
    	            dataIndex: 'remark',
    	            align: 'center'
    	        }],
    	        tools : [ {
    				id : 'refresh',
    				handler : function() {
    					gridTreePanel.root.reload();
    					gridTreePanel.expand();
    				}
    			} ],
				loader: treeloader
    	       // dataUrl: '${basePath}/sys/resource/queryList.do'
    	    });
    		
    		gridTreePanel.on({
    			'beforeload': function(node){
    				treeloader.baseParams = {parentCode: node.attributes.code};
    			}
    		});
    		gridTreePanel.on({
    			'click': function(node){//dbl
    				//showAddResource(node.id);
    				parentCode = node.id;
    			}
    		});
    		new Ext.Viewport({
    			layout : 'border',
				items : [gridTreePanel]
    		});
    		
    		var resourceForm = new Ext.form.FormPanel({
		        frame:true,
		        border: true,
		        buttonAlign: 'center',
		        autoHeight:true,
	            labelAlign: 'right',
	            //layout: 'column',
	            defaults: {width: 210},
	            defaultType: 'textfield',
		        items: [{
		            	name: 'id',
		            	hidden: true
	            	},{
		            	name: 'parentCode',
		            	id:'parentCode',
		            	hidden: true
	            	},{
		            	name: 'type',
		            	hidden: true
	            	},{
	                    fieldLabel: '资源编号',
	                    name: 'code',
	                    allowBlank:false
	                }, {
	                    fieldLabel: '资源名称',
	                    name: 'name',
	                    allowBlank: false
	                }, {
	                    fieldLabel: '资源URL',
	                    name: 'url',
	                    id:'url',
	                    allowBlank: false
	                }, 
	                	createCombo("状态", "state", [["启用", "启用"], ["禁用", "禁用"]])
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
		            	Ext.ux.Form.submitForm(resourceForm, "${basePath}/sys/resource/save.do", 
		            		function(form, action){
		            			Ext.Msg.alert('消息', action.result.msg);
		            			resourceWin.hide();
		            			//gridTreePanel.load();
		            		}		
		            	);
		            }
		        }, {
		        	id: 'btn_form_update',
		            iconCls: 'edit1Icon',
		            text: '更新',
		            handler: function(){
		            	Ext.ux.Form.submitForm(resourceForm, "${basePath}/sys/resource/update.do", 
			            		function(form, action){
			            			Ext.Msg.alert('消息', action.result.msg);
			            			//resourceGrid.getStore().load();
			            			resourceWin.hide();
		            			}		
			            	);
		            }
		        }, {
		        	id: 'btn_form_cancle',
		            iconCls: 'deleteIcon',
		            text: '取消',
		            handler: function(){
		            	resourceForm.getForm().reset();
		            	resourceWin.hide();
		            }
		        }]
    		});
    		
    		resourceWin = createWindow("添加资源", resourceForm, 560, 240);
    		
    		function showAddResource(){
    			resourceForm.getForm().reset();
    			resourceWin.setTitle("添加资源信息");
    			Ext.getCmp("btn_form_save").show();
    			Ext.getCmp("btn_form_update").hide();
    			if(parentCode=='888'){
    				Ext.MessageBox.confirm('确认', '您将要添加根节点菜单?', function(btn){
    					if(btn == 'yes'){
    						Ext.getCmp("url").setValue("子系统/表名称/listPage.do");
    		    			resourceWin.show();
    					}
    				});
    			}else{
        			Ext.getCmp("parentCode").setValue(parentCode);
        			Ext.getCmp("url").setValue("子系统/表名称/listPage.do");
        			resourceWin.show();
    			}
    		}
    		
    		function showAdd(){
    			resourceForm.getForm().reset();
    			resourceWin.setTitle("添加资源信息");
    			Ext.getCmp("btn_form_save").show();
    			Ext.getCmp("btn_form_update").hide();
    			resourceWin.show();
    		}
    		
    		function showUpdate(){
    			resourceWin.setTitle("更新资源信息");
    			if(parentCode!='888'){
    				Ext.getCmp("btn_form_save").hide();
        			Ext.getCmp("btn_form_update").show();
        			resourceWin.show();
        			//Ext.ux.Form.loadForm(resourceForm, "${basePath}/sys/resource/load.do", {id: parentCode});
        			loadForm(parentCode);
    			}
    		}
    		
    		function showDelete(){
    			if(parentCode == '888'){
    				Ext.ux.Msg.warning("请选择一条记录"); 
    				return;
    			}
    			Ext.MessageBox.confirm('确认', '您确定要删除所选择的记录吗?', function(btn){
					if(btn == 'yes'){
        				Ext.ux.Ajax.request("${basePath}/sys/resource/delete.do", {ids: parentCode}, function(action, form){
   							var response =  Ext.util.JSON.decode(action.responseText.success);
   							if(Ext.isEmpty(response)){
								Ext.ux.Msg.info("删除成功");
							}else{
								Ext.ux.Msg.info("删除失败");
							}
   						});
					}
    			});
    		}
    		//加载表单数据
    		function loadForm(id) {
    			var formPanel = resourceForm.getForm();
    			formPanel.load( {
    				waitMsg : '正在加载数据请稍后',//提示信息
    				waitTitle : '提示',//标题
    				url:"${basePath}/sys/resource/load.do",
    				params:{id:id},
    				method : 'POST',//请求方式
    				failure : function(form, action) {//加载失败的处理函数
    					Ext.Msg.alert('系统提示', '数据加载失败');
    				}
    			});
    		}
		});
	</script>

  </head>
  
  <body>
  </body>
</html>
