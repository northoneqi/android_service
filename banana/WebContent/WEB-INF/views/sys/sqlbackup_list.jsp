<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>数据库备份与还原模块</title>
    
    <script type="text/javascript">
    	Ext.onReady(function(){
    		var sqlbackupModel = [
		        {header: '编号',name: 'code',type: 'string', width: 80}, 
	    		{header: '备份类型', name: 'name',type: 'string'}, 
	    		{header: '备份时间', name: 'createTime',type: 'string', width:200},
	    		{header: '备注', name: 'remark',type: 'string', width:350}
		    ];
    		
    		var sqlbackupGrid = createExtGrid("备份列表", sqlbackupModel, true, {id: 1}, "${basePath}/sys/sqlbackup/query.do", 'multi');
    		
    		var gridTopbar = sqlbackupGrid.getTopToolbar();
    		var gridTbar = [{
	            iconCls: 'addIcon',
	            text: '手动备份',
	            handler: function(){
		    		Ext.ux.Ajax.request("${basePath}/sys/sqlbackup/backup.do", {}, function(action, form){
						var response =  Ext.util.JSON.decode(action.responseText);
						if(Ext.isEmpty(response)){
							Ext.ux.Msg.info("备份成功");
						}else{
							Ext.ux.Msg.info(response.data.instruction);
						}
						sqlbackupGrid.getStore().reload();
					});
	            }
	        },  
	        	{xtype: 'tbseparator'}
	        , {
	            iconCls: 'edit1Icon',
	            text: '手动恢复',
	            handler: function(){
	            	//准备加载数据
	    			var records = sqlbackupGrid.getSelectionModel().getSelections();
	    			if(records.length == 0){
	    				Ext.ux.Msg.warning("请选择一条记录"); 
	    				return;
	    			}else if(records.length > 1){
	    				Ext.ux.Msg.warning("您选择的记录过多，本操作不支持批量还原，您只能选择一条记录！");
	    				return;
	    			}
	    			
	            	Ext.MessageBox.confirm('确认', '您确定要还原数据库吗?', function(btn){
    					if(btn == 'yes'){
    						Ext.ux.Ajax.request("${basePath}/sys/sqlbackup/load.do",  {id: records[0].id}, function(action, form){
    							var response =  Ext.util.JSON.decode(action.responseText);
    							if(Ext.isEmpty(response)){
									Ext.ux.Msg.info("还原成功");
								}else{
									Ext.ux.Msg.info(response.data.instruction);
								}
    						});
    					}
    				});
	            }
	        },
	        	{xtype: 'tbseparator'}
	        , {
	            iconCls: 'deleteIcon',
	            text: '下载备份数据库',
	            handler: function(){
	            	//准备加载数据
	    			var records = sqlbackupGrid.getSelectionModel().getSelections();
	    			if(records.length == 0){
	    				Ext.ux.Msg.warning("请选择一条记录"); 
	    				return;
	    			}else if(records.length > 1){
	    				Ext.ux.Msg.warning("您选择的记录过多，本操作不支持批量下载，您只能选择一条记录！");
	    				return;
	    			}
	    			
	    			window.open("${basePath}/sys/sqlbackup/downloadSql.do?id="+records[0].id);
	            }
	        },
	        	{xtype: 'tbseparator'}
	        ];
    		
    		gridTopbar.add(gridTbar);
    		
    		
    		new Ext.Viewport({
    			layout: 'border',
    			items: [sqlbackupGrid]
    		});
    	});
    </script>
  </head>
  
  <body>
  </body>
</html>
