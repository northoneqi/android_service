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
		var parentId = 0;
		var parentText = "";
		Ext.onReady(function(){
			Ext.QuickTips.init();
		    var Department = [
				{header: 'ID',width:30,name: 'goodCategoryId' ,hide: true},
		    	{header: '商品分类编号',width:200,name: 'goodCategoryCode'}, 
	    		{header: '商品分类名称',width:200, name: 'goodCategoryName'}, 
	    		{header: '备注',width:300, name: 'goodCategoryRemark'}
		    ];
		    
		    var departmentGrid = createExtGrid("商品分类列表", Department, true, {}, "${basePath}/supplyChain/goodCategory/queryList.do", "multi");
		    departmentGrid.getStore().on({
		    	"beforeload": function(store){
		    		var baseParams = {isUnit: false};
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
    		
    		var root = new Ext.tree.AsyncTreeNode({ id: "0",code:"0",level:"0", text: "商品分类树" }); //创建AsyncTreeNode 必须和loader一起使用
    		var departmentTree = new Ext.tree.TreePanel({
                root: root,
                region : 'west',
                width : 210,
        		minSize : 160,
        		maxSize : 280,
        		split : true,
        		iconCls : 'chart_organisationIcon',
                id:'departmentTree',
                border:false,
                title:"商品分类树形结构",
                loader: new Ext.tree.TreeLoader({ dataUrl: "${basePath}/supplyChain/goodCategory/queryTree.do" })//
            });
    		departmentTree.root.expand();
    		departmentTree.on('beforeload',function(node){
				var parentId = node.attributes.id;
				departmentTree.loader.dataUrl= '${basePath}/supplyChain/goodCategory/queryTree.do?parentId='+parentId;//通过这个传递参数，这样就可以点一个节点出来它的子节点来实现异步加载
		    });
    		departmentTree.on({
    			"click": function(node){
    				parentId = node.attributes.id;
    				parentText = node.attributes.text;
    				departmentGrid.setTitle("商品分类列表 【<font color=red>"+parentText+"</font>】");
    				departmentGrid.getStore().load({params:{start: 0, limit: pageSize, parentId: parentId, united: true}});
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
		            title: '商品分类信息',
		            autoHeight:true,
		            labelAlign: 'right',
		            defaults: {width: 210},
		            defaultType: 'textfield',
		            animCollapse: true,
		            collapsed: false,
		            items :[{
			            	name: 'goodCategoryId',
			            	hidden:true
		            	},{
		                    fieldLabel: '商品分类编号',
		                    name: 'goodCategoryCode',
		                    allowBlank:false
		                }, {
		                    fieldLabel: '商品分类名称',
		                    name: 'goodCategoryName',
		                    allowBlank: false
		                }, {
		                    fieldLabel: '上级分类',
		                    name:'goodCategoryParentShow',
		                    style:'background:#E3E0D5',
		                    readOnly: true
		                }, {
		                	name: 'goodCategoryParent',
		                	xtype:'hidden'
		                }, {
		                	name: 'goodCategoryRemark',
		                	xtype:'textarea'
		                }
		            ]
		        }],
		        buttons: [{
	    			id: 'btn_form_save',
		            iconCls: 'saveIcon',
		            text: '保存',
		            handler: function(){
		        		console.log(departmentForm.getForm().getValues());
		            	Ext.ux.Form.submitForm(departmentForm, "${basePath}/supplyChain/goodCategory/save.do?united=false", 
		            		function(form, action){
		            			Ext.Msg.alert('消息', action.result.data.instruction);
		            			Ext.MessageBox.confirm('确认', '您确定要继续添加吗?', function(btn){
		        					if(btn == 'yes'){
		        						departmentForm.getForm().reset();
		        	    				departmentForm.getForm().findField("goodCategoryParentShow").setValue(parentText);
		        	    				departmentForm.getForm().findField("goodCategoryParent").setValue(parentId);
		        					}else{
		        						departmentWin.hide();
		        					}
		            			});
		            			if(parseInt(parentId)<=0){
	   								departmentTree.root.reload();
	   							}else{
	   								var node = departmentTree.getNodeById(parentId);
	   								node.parentNode.reload();
	   							}
		            			departmentGrid.getStore().load({params:{limit: pageSize,parentId:parentId}});
		            		}		
		            	);
		            }
		        }, {
		        	id: 'btn_form_update',
		            iconCls: 'edit1Icon',
		            text: '更新',
		            handler: function(){
		            	Ext.ux.Form.submitForm(departmentForm, "${basePath}/supplyChain/goodCategory/update.do?united=false", 
			            		function(form, action){
			            			Ext.Msg.alert('消息', action.result.data.instruction);
			            			departmentWin.hide();
			            			if(parseInt(parentId)<=0){
		   								departmentTree.root.reload();
		   							}else{
		   								var node = departmentTree.getNodeById(parentId);
		   								node.parentNode.reload();
		   							}
			            			departmentGrid.getStore().load({params:{limit: pageSize,parentId:parentId}});
			            			
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
    		
    		departmentWin = createWindow("商品分类信息", departmentForm, 400, 268);
    		
    		function showAdd(){
    			departmentWin.setTitle("添加商品分类信息");
    			Ext.getCmp("btn_form_save").show();
    			Ext.getCmp("btn_form_update").hide();
    			departmentWin.show();
    			departmentForm.getForm().reset();
    			var showText = "无";
    			if(parentId > 0){
    				showText = parentText ;
    				departmentForm.getForm().findField("goodCategoryParentShow").setValue(showText);
    				departmentForm.getForm().findField("goodCategoryParent").setValue(parentId);
    			}
    			
    		}
    		
    		function showUpdate(){
    			departmentWin.setTitle("更新商品分类信息");
    			
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
    			Ext.ux.Form.loadForm(departmentForm, "${basePath}/supplyChain/goodCategory/find.do", {id: records[0].get("goodCategoryId")});
    			Ext.getCmp("btn_form_save").hide();
    			Ext.getCmp("btn_form_update").show();
    			departmentWin.show();
    			departmentForm.getForm().findField("goodCategoryParentShow").setValue("系统默认");
    			departmentForm.getForm().findField("id").setValue(records[0].get("goodCategoryId"));
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
        					ids.push(records[i].get("goodCategoryId"));
        				}
        				Ext.ux.Ajax.request("${basePath}/supplyChain/goodCategory/delete.do", {ids: ids}, function(action, form){
   							var response =  Ext.util.JSON.decode(action.responseText);
   							if(Ext.isEmpty(response)){
								Ext.ux.Msg.info("删除成功");
							}else{
								Ext.ux.Msg.info(response.data.instruction);
							}
   							if(parseInt(parentId)<=0){
   								departmentTree.root.reload();
   							}else{
   								var node = departmentTree.getNodeById(parentId);
   								node.parentNode.reload();
   							}
			            	departmentGrid.getStore().load({params:{limit: pageSize,parentId:parentId}});
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
