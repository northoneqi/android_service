<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>日志模块</title>
    
    <script type="text/javascript">
    	Ext.onReady(function(){
    		var sysLogModel = [
		        {header: '编号',name: 'id',type: 'int', width: 80}, 
	    		{header: '用户', name: 'userName',type: 'string'}, 
	    		{header: '登陆IP地址', name: 'loginIP',type: 'string', width:120},
	    		{header: '操作时间', name: 'operateTime',type: 'string', width:150},
	    		{header: '操作模块', name: 'operateModel',type: 'string', width: 200},
	    		{header: '异常信息', name: 'exceptionMsg',type: 'string', width: 400}
		    ];
    		
    		var logGrid = createExtGrid("日志列表", sysLogModel, true, {id: 1}, "${basePath}/sys/log/query.do", 'multi');
    		
    		logGrid.getStore().on({
		    	"beforeload": function(store){
		    		var baseParams = {
		    			userName: Ext.getCmp("userName").getValue(),
		    			startDate: Ext.util.Format.date(Ext.getCmp("startDate").getValue(), "Y-m-d"),
		    			endDate: Ext.util.Format.date(Ext.getCmp("endDate").getValue(), "Y-m-d")
		    	    };
		    		store.baseParams = baseParams;
		    	}
		    });
    		
    		var gridTopbar = logGrid.getTopToolbar();
    		
    		var tbar = [{
	            iconCls: 'page_delIcon',
	            text: '全部删除',
	            handler: function(){
    				Ext.MessageBox.confirm('确认', '您确定要删除所有的记录吗?', function(btn){
    					if(btn == 'yes'){
    						Ext.ux.Ajax.request("${basePath}/sys/log/delete.do", {}, function(action, form){
    							var response =  Ext.util.JSON.decode(action.responseText);
    							if(Ext.isEmpty(response)){
									Ext.ux.Msg.info("删除成功");
								}else{
									Ext.ux.Msg.info(response.data.instruction);
								}
    							logGrid.getStore().reload();
    						});
    					}
    				});
	            }
	        },
	        	{xtype: 'tbseparator'}
	        ,{
	            iconCls: 'deleteIcon',
	            text: '删除',
				handler: function(){
					var records = logGrid.getSelectionModel().getSelections();
        			if(records.length == 0){
        				Ext.ux.Msg.warning("请至少选择一条记录");
        				return;
        			}
        			
	        		Ext.MessageBox.confirm('确认', '您确定要删除所选择的记录吗?', function(btn){
        				var ids = [];
        				for(var i = 0; i < records.length; i++){
        					ids.push(records[i].id);
        				}
        				Ext.ux.Ajax.request("${basePath}/sys/log/delete.do", {ids: ids}, function(action, form){
   							var response =  Ext.util.JSON.decode(action.responseText);
   							if(Ext.isEmpty(response)){
								Ext.ux.Msg.info("删除成功");
							}else{
								Ext.ux.Msg.info(response.data.instruction);
							}
   							logGrid.getStore().reload();
   						});
    				});
	            }
	        },
	        	{xtype: 'tbseparator'}
	        /*,{
	            iconCls: 'deleteIcon',
	            text: '导出Excel',
				handler: function(){
	        		window.open(basePath+"/sys/log/export.do");
	            }
	        }, 
	        	{xtype: 'tbseparator'}*/,'->'
	        ,"操作用户:",{
	        	text: '操作用户:',
	        	xtype: 'textfield',
	        	name: 'userName',
	        	id: 'userName'
	        },"&nbsp;&nbsp;操作起始日期:",{
	        	xtype: 'datefield',
	        	name: 'startDate',
	        	format: 'Y-m-d',
	        	id: 'startDate'
	        },"&nbsp;&nbsp;操作截止日期:",{
	        	xtype: 'datefield',
	        	name: 'endDate',
	        	format: 'Y-m-d',
	        	id: 'endDate'
	        },{
	            iconCls: 'previewIcon',
	            text: '查询',
				handler: function(){
	        		logGrid.getStore().load({params:{start: 0, limit: pageSize}});
	            }
	        }];
    		
    		gridTopbar.add(tbar);
    		
    		new Ext.Viewport({
    			layout: 'border',
    			items: [logGrid]
    		});
    	});
    </script>
  </head>
  
  <body>
  </body>
</html>
