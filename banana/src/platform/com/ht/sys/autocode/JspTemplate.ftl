<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${r"${basePath}"}/">
    
    <title>${description}管理</title>
    
	<style type="text/css">
	</style>
	<script type="text/javascript" >
		Ext.onReady(function(){
			
		    var ${entity} = [
		    <#list fields as field>
		    	{header: '${field.fieldLabel}', name:'${field.name}', type:'${field.type}'<#if field.hidden==true>, hide:true</#if>,width: ${field.width}}<#if field_has_next>,</#if>
		    </#list>
		    ];
		    
		    var <@lowerFC>${entity}</@lowerFC>Grid = createExtGrid("${description}列表", ${entity}, true, {}, "${r"${basePath}"}/${app}/<@lowerFC>${entity}</@lowerFC>/query.do", "multi");
		    <@lowerFC>${entity}</@lowerFC>Grid.getStore().on({
		    	"beforeload": function(store){
		    		var baseParams = store.baseParams;
		    		store.baseParams = baseParams;
		    	}
		    });
		    
		    var gridTopbar = <@lowerFC>${entity}</@lowerFC>Grid.getTopToolbar();
		    
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
    		
    		new Ext.Viewport({
    			layout : 'border',
				items : [<@lowerFC>${entity}</@lowerFC>Grid]
    		});
    		
    		var <@lowerFC>${entity}</@lowerFC>Form = new Ext.form.FormPanel({
		        frame:true,
		        border: true,
		        buttonAlign: 'center',
		        autoHeight:true,
	            labelAlign: 'right',
	            //layout: 'column',
	            defaults: {width: 210, allowBlank: false},
	            defaultType: 'textfield',
		        items: [
		        <#list fields as field>
		        	<#if !(field.name=="createTime")>
		    		{fieldLabel: '${field.fieldLabel}', name:'${field.name}'<#if field.type=="date">,xtype: 'datefield',format: 'Y-m-d',maxValue: new Date()</#if><#if field.hidden==true>, hidden:true, allowBlank:true</#if>}<#if field_has_next>,</#if>
		    		</#if>
		    	</#list>
	            ],
		        buttons: [{
	    			id: 'btn_form_save',
		            iconCls: 'saveIcon',
		            text: '保存',
		            handler: function(){
		            	Ext.ux.Form.submitForm(<@lowerFC>${entity}</@lowerFC>Form, "${r"${basePath}"}/${app}/<@lowerFC>${entity}</@lowerFC>/save.do", 
		            		function(form, action){
		            			Ext.Msg.alert('消息', action.result.data.instruction);
		            			Ext.MessageBox.confirm('确认', '您确定要继续添加吗?', function(btn){
		        					if(btn == 'yes'){
		        						<@lowerFC>${entity}</@lowerFC>Form.getForm().reset();
		        					}else{
		        						<@lowerFC>${entity}</@lowerFC>Win.hide();
		        					}
		            			})
		            			
		            			<@lowerFC>${entity}</@lowerFC>Grid.getStore().load();
		            		}		
		            	);
		            }
		        }, {
		        	id: 'btn_form_update',
		            iconCls: 'edit1Icon',
		            text: '更新',
		            handler: function(){
		            	Ext.ux.Form.submitForm(<@lowerFC>${entity}</@lowerFC>Form, "${r"${basePath}"}/${app}/<@lowerFC>${entity}</@lowerFC>/update.do", 
		            		function(form, action){
		            			Ext.Msg.alert('消息', action.result.data.instruction);
		            			
		            			<@lowerFC>${entity}</@lowerFC>Grid.getStore().load();
		            			<@lowerFC>${entity}</@lowerFC>Win.hide();
		            			<@lowerFC>${entity}</@lowerFC>Form.getForm().reset();
	            			}		
		            	);
		            }
		        }, {
		        	id: 'btn_form_cancle',
		            iconCls: 'deleteIcon',
		            text: '取消',
		            handler: function(){
		            	<@lowerFC>${entity}</@lowerFC>Form.getForm().reset();
		            	<@lowerFC>${entity}</@lowerFC>Win.hide();
		            }
		        }]
    		});
    		
    		<@lowerFC>${entity}</@lowerFC>Win = createWindow("添加${description}", <@lowerFC>${entity}</@lowerFC>Form, 570, 290);
    		
    		function showAdd(){
    			<@lowerFC>${entity}</@lowerFC>Form.getForm().reset();
    			<@lowerFC>${entity}</@lowerFC>Win.setTitle("添加${description}信息");
    			Ext.getCmp("btn_form_save").show();
    			Ext.getCmp("btn_form_update").hide();
    			<@lowerFC>${entity}</@lowerFC>Win.show();
    		}
    		
    		function showUpdate(){
    			<@lowerFC>${entity}</@lowerFC>Win.setTitle("更新${description}信息");
    			//准备加载数据
    			var records = <@lowerFC>${entity}</@lowerFC>Grid.getSelectionModel().getSelections();
    			if(records.length == 0){
    				Ext.ux.Msg.warning("请选择一条记录"); 
    				return;
    			}else if(records.length > 1){
    				Ext.ux.Msg.warning("您选择的记录过多，本操作不支持批量修改，您只能选择一条记录！");
    				return;
    			}
    			
    			Ext.getCmp("btn_form_save").hide();
    			Ext.getCmp("btn_form_update").show();
    			<@lowerFC>${entity}</@lowerFC>Win.show();
    			
    			Ext.ux.Form.loadForm(<@lowerFC>${entity}</@lowerFC>Form, "${r"${basePath}"}/${app}/<@lowerFC>${entity}</@lowerFC>/find.do", {id: records[0].id});
    			
    		}
    		
    		function showDelete(){
    			var records = <@lowerFC>${entity}</@lowerFC>Grid.getSelectionModel().getSelections();
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
        				Ext.ux.Ajax.request("${r"${basePath}"}/${app}/<@lowerFC>${entity}</@lowerFC>/delete.do", {ids: ids}, function(action, form){
   							var response =  Ext.util.JSON.decode(action.responseText);
   							if(Ext.isEmpty(response)){
								Ext.ux.Msg.info("删除成功");
							}else{
								Ext.ux.Msg.info(response.data.instruction);
							}
   							<@lowerFC>${entity}</@lowerFC>Grid.getStore().reload();
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
<!--该代码由代码生成器产生，请根据实际修改-->