<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>用户维护</title>
    
	<style type="text/css">
	</style>
	<script type="text/javascript" >
		Ext.onReady(function(){
			Ext.QuickTips.init();
			
			var curDepartment;
			
		    var User = [
		    	{header: '编号',name: 'code',type: 'string', width: 80}, 
	    		{header: '用户名', name: 'name',type: 'string', width: 70}, 
	    		{header: '登录账号', name: 'userName',type: 'string', width: 70},
	    		{header: '角色编号', name: 'roleCode',type: 'string', width: 70, hide: true},
	    		{header: '所属角色', name: 'roleName',type: 'string', width: 120},
	    		{header: '数据权限', name: 'dataAuth',type: 'String', width: 100},
	    		{header: '所属部门编号', name: 'departmentCode',type: 'string', hide: true},
	    		{header: '所属部门', name: 'departmentName',type: 'string', width: 120},
	    		{header: '所属单位编号', name: 'unitCode',type: 'string', hide: true},
	    		{header: '所属单位', name: 'unitName',type: 'string', hide: true},
	    		{header: '状态', name: 'state',type: 'string', width: 50},
	    		{header: '身份证号', name: 'idCard',type: 'String', width: 140,hide: true},
	    		{header: '出生日期', name: 'birthDay',type: 'String', width: 80,hide: true},
	    		{header: '联系电话', name: 'telPhone',type: 'String',hide: true},
	    		{header: '电子邮箱', name: 'email',type: 'String', width: 150,hide: true},
	    		{header: '备注', name: 'remark',type: 'String', width: 200}
		    ];
		    
		    var userGrid = createExtGrid("用户列表", User, true, {}, "${basePath}/sys/user/query.do", "multi");
		    userGrid.getStore().on({
		    	"beforeload": function(store){
		    		if(Ext.isEmpty(curDepartment)){
	    				curDepartment = departmentTree.getRootNode().firstChild.attributes;
	    			}
		    		var baseParams = store.baseParams;
		    		baseParams.departmentCode = curDepartment.code;
		    		store.baseParams = baseParams;
		    	}
		    });
		    
		    var gridTopbar = userGrid.getTopToolbar();
		    
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
    				userGrid.setTitle("当前单位->"+curDepartment.text);
    				userGrid.getStore().load({params:{start: 0, limit: pageSize, departmentCode: curDepartment.code, united: true}});
    			}
    		});
    		
    		new Ext.Viewport({
    			layout : 'border',
				items : [departmentTree, userGrid]
    		});
    		
    		var deptComboTree =  createComboTree({
           		url: '${basePath}/sys/department/queryTree.do',
           		fieldLabel: '所属部门',
           		hiddenName: 'departmentCode',
           		name: 'departmentName'
            });
            
    		var staffCombo =  Ext.ux.form.ComboBox({
				 id: 'projectCombo',
 				 url: 'sys/staff/getCombo.do',
 				 fields: [{code: 'code'}, {name: 'name'}],
 				 name: 'staffName',
		    	 hiddenName: 'staffCode',
				 fieldLabel: '<span style="color:red">*</span>职工'
 		    })
            
            userForm = new Ext.form.FormPanel({
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
	                    fieldLabel: '用户编号',
	                    name: 'code',
	                    allowBlank:false
	                }, {
	                    fieldLabel: '用户名称',
	                    name: 'name',
	                    allowBlank: false
	                }, {
	                    fieldLabel: '登录账号',
	                    name: 'userName',
	                    allowBlank: false
	                }, 
	                	Ext.ux.form.ComboBox({
	                		fieldLabel: '所属角色',
	                		url: '${basePath}/sys/role/getAllRole.do',
	                		fields: [{name: 'code'}, {name: 'name'}],
	                		name: 'roleName',
	                		hiddenName: 'roleCode',
	                		editable: false
	                	})
	                ,
	                	createCombo("状态", "state", [["启用", "启用"], ["禁用", "禁用"]])
	                , 
		                deptComboTree
		            /*,
		                Ext.ux.form.ComboBox({
				    	 	id: 'documentTypeCombo',
				    	 	name: 'dataAuth',
							hiddenName: 'dataAuthCode',
							fieldLabel: '<span style="color:red">*</span>数据权限',
				        	fields: [{name: 'code'}, {name: 'name'}],
				        	url: 'sys/dict/getDictByCode.do?code=1'
				        })*/
	             	, 
		            	staffCombo
		            ,
		               {
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
		            	Ext.ux.Form.submitForm(userForm, "${basePath}/sys/user/save.do", 
		            		function(form, action){
		            			Ext.Msg.alert('消息', action.result.data.instruction);
		            			Ext.MessageBox.confirm('确认', '用户初始密码为：0000；\n您确定要继续添加吗?', function(btn){
		        					if(btn == 'yes'){
		        						userForm.getForm().reset();
		        					}else{
		        						userWin.hide();
		        					}
		            			})
		            			
		            			userGrid.getStore().load();
		            		}		
		            	);
		            }
		        }, {
		        	id: 'btn_form_update',
		            iconCls: 'edit1Icon',
		            text: '更新',
		            handler: function(){
		        		
		            	Ext.ux.Form.submitForm(userForm, "${basePath}/sys/user/update.do", 
		            		function(form, action){
		            			Ext.Msg.alert('消息', action.result.data.instruction);
		            			userGrid.getStore().load();
		            			userWin.hide();
		            			userForm.getForm().reset();
	            			}		
		            	);
		            }
		        }, {
		        	id: 'btn_form_cancle',
		            iconCls: 'deleteIcon',
		            text: '取消',
		            handler: function(){
		            	userForm.getForm().reset();
		            	userWin.hide();
		            }
		        }]
    		});
            
	 		 console.log(deptComboTree);
	 		 deptComboTree.tree.on({
	 			 click: function(node){
	 				console.log(node);
	 				var staffStore = staffCombo.getStore();
    				staffStore.baseParams = {departmentCode: node.attributes.code};
    				staffStore.load({});
	 			 }
	 		 });
            
    	 
    		
    		userWin = createWindow("添加用户", userForm, 570, 350);
    		
    		function showAdd(){
    			userWin.setTitle("添加用户信息");
    			Ext.getCmp("btn_form_save").show();
    			Ext.getCmp("btn_form_update").hide();
    			if(Ext.isEmpty(curDepartment)){
    				//console.log(departmentTree.getRootNode().firstChild);
    				curDepartment = departmentTree.getRootNode().firstChild.attributes;
    			}
    			userForm.getForm().findField("departmentCode").setValue(curDepartment.code);
    			var staffStore = staffCombo.getStore();
    			staffStore.baseParams = {departmentCode: curDepartment.code};
    			staffStore.load({});
    			userWin.show();
    			userForm.getForm().reset();
    		}
    		
    		function showUpdate(){
    			userWin.setTitle("更新用户信息");
    			//准备加载数据
    			var records = userGrid.getSelectionModel().getSelections();
    			if(records.length == 0){
    				Ext.ux.Msg.warning("请选择一条记录"); 
    				return;
    			}else if(records.length > 1){
    				Ext.ux.Msg.warning("您选择的记录过多，本操作不支持批量修改，您只能选择一条记录！");
    				return;
    			}
    			
    			Ext.getCmp("btn_form_save").hide();
    			Ext.getCmp("btn_form_update").show();
    			userWin.show();
    			
    			Ext.ux.Form.loadForm(userForm, "${basePath}/sys/user/find.do", {id: records[0].id});
    			
    		}
    		
    		function showDelete(){
    			var records = userGrid.getSelectionModel().getSelections();
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
        				Ext.ux.Ajax.request("${basePath}/sys/user/delete.do", {ids: ids}, function(action, form){
   							var response =  Ext.util.JSON.decode(action.responseText);
   							if(Ext.isEmpty(response)){
								Ext.ux.Msg.info("删除成功");
							}else{
								Ext.ux.Msg.info(response.data.instruction);
							}
   							userGrid.getStore().reload();
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
