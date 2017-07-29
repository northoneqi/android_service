<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>职工维护</title>
    
	<style type="text/css">
	</style>
	<script type="text/javascript" >
		Ext.onReady(function(){
			Ext.QuickTips.init();
			
			var curDepartment;
			
		    var Staff = [ 
		    	{header: '职工编号',name: 'code',type: 'string', width: 80}, 
	    		{header: '职工姓名', name: 'name',type: 'string', width: 70}, 
	    		{header: '所属部门', name: 'departmentName',type: 'string', width: 70},
	    		{header: '所在部门编码', name: 'departmentCode',type: 'string', width: 70, hide: true},
	    		{header: '联系方式', name: 'phone',type: 'string', width: 70},
	    		{header: 'email', name: 'email',type: 'string', width: 120}
		    ];
		    
		    var StaffGrid = createExtGrid("职工列表", Staff, true, {}, "${basePath}/sys/staff/query.do", "multi");
		    StaffGrid.getStore().on({
		    	"beforeload": function(store){
		    		if(Ext.isEmpty(curDepartment)){
	    				curDepartment = departmentTree.getRootNode().firstChild.attributes;
	    			}
		    		var baseParams = store.baseParams;
		    		baseParams.departmentCode = curDepartment.code;
		    		store.baseParams = baseParams;
		    	}
		    });
		    
		    var gridTopbar = StaffGrid.getTopToolbar();
		    
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
    				curDepartment = node.attributes;
    				StaffGrid.setTitle("当前单位->"+curDepartment.text);
    				StaffGrid.getStore().load({params:{start: 0, limit: pageSize, departmentCode: curDepartment.code, united: true}});
    			}
    		});
    		
    		new Ext.Viewport({
    			layout : 'border',
				items : [departmentTree, StaffGrid]
    		});
    		
    		var deptComboTree =  createComboTree({
           		url: '${basePath}/sys/department/queryTree.do',
           		fieldLabel: '所属部门',
           		hiddenName: 'departmentCode',
           		name: 'departmentName'
            });
    		
    		var StaffForm = new Ext.form.FormPanel({
		        frame:true,
		        border: true,
		        buttonAlign: 'center',
		        autoHeight:true,
	            labelAlign: 'right',
	            //layout: 'column',
	            defaults: {width: 150},
	            defaultType: 'textfield',
		        items: [{
		            	name: 'id',
		            	hidden: true
	            	},{
	                    fieldLabel: '职工编号',
	                    name: 'code',
	                    allowBlank:false
	                }, {
	                    fieldLabel: '职工名称',
	                    name: 'name',
	                    allowBlank: false
	                },  deptComboTree , {
	                    fieldLabel: '联系方式',
	                    name: 'phone' 
	                }, {
	                    fieldLabel: 'email',
	                    name: 'email' 
	                    }
	               
	            ],
		        buttons: [{
	    			id: 'btn_form_save',
		            iconCls: 'saveIcon',
		            text: '保存',
		            handler: function(){
		            	Ext.ux.Form.submitForm(StaffForm, "${basePath}/sys/staff/save.do", 
		            		function(form, action){
		            			Ext.Msg.alert('消息', action.result.data.instruction);
		            			Ext.MessageBox.confirm('确认', '添加成功！您确定要继续添加吗?', function(btn){
		        					if(btn == 'yes'){
		        						StaffForm.getForm().reset();
		        					}else{
		        						StaffWin.hide();
		        					}
		            			})
		            			
		            			StaffGrid.getStore().load();
		            		}		
		            	);
		            }
		        }, {
		        	id: 'btn_form_update',
		            iconCls: 'edit1Icon',
		            text: '更新',
		            handler: function(){
		        		
		            	Ext.ux.Form.submitForm(StaffForm, "${basePath}/sys/staff/update.do", 
		            		function(form, action){
		            			Ext.Msg.alert('消息', action.result.data.instruction);
		            			
		            			StaffGrid.getStore().load();
		            			StaffWin.hide();
		            			StaffForm.getForm().reset();
	            			}		
		            	);
		            }
		        }, {
		        	id: 'btn_form_cancle',
		            iconCls: 'deleteIcon',
		            text: '取消',
		            handler: function(){
		            	StaffForm.getForm().reset();
		            	StaffWin.hide();
		            }
		        }]
    		});
    		
    		StaffWin = createWindow("添加职工", StaffForm, 330, 260);
    		
    		function showAdd(){
    			StaffWin.setTitle("添加职工信息");
    			Ext.getCmp("btn_form_save").show();
    			Ext.getCmp("btn_form_update").hide();
    			if(Ext.isEmpty(curDepartment)){
    				curDepartment = departmentTree.getRootNode().firstChild.attributes;
    			}
    			StaffWin.show();
    			StaffForm.getForm().reset();
    		}
    		
    		function showUpdate(){
    			StaffWin.setTitle("更新职工信息");
    			//准备加载数据
    			var records = StaffGrid.getSelectionModel().getSelections();
    			if(records.length == 0){
    				Ext.ux.Msg.warning("请选择一条记录"); 
    				return;
    			}else if(records.length > 1){
    				Ext.ux.Msg.warning("您选择的记录过多，本操作不支持批量修改，您只能选择一条记录！");
    				return;
    			}
    			
    			Ext.getCmp("btn_form_save").hide();
    			Ext.getCmp("btn_form_update").show();
    			StaffWin.show();
    			
    			Ext.ux.Form.loadForm(StaffForm, "${basePath}/sys/staff/find.do", {id: records[0].id});
    			
    		}
    		
    		function showDelete(){
    			var records = StaffGrid.getSelectionModel().getSelections();
    			//alert(records.lengths);
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
        				Ext.ux.Ajax.request("${basePath}/sys/staff/delete.do", {ids: ids}, function(action, form){
   							var response =  Ext.util.JSON.decode(action.responseText);
   							if(Ext.isEmpty(response)){
								Ext.ux.Msg.info("删除成功");
							}else{
								Ext.ux.Msg.info(response.data.instruction);
							}
   							StaffGrid.getStore().reload();
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
