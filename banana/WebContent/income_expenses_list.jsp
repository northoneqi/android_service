<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/views/common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>收支情况总表</title>
    
	<style type="text/css">
	</style>
	<script type="text/javascript" >
		Ext.onReady(function(){
			
		    var IncomeExpence = [
		    	{header: '项目分类',name: 'projectName',type: 'string', width: 80},
		    	{header: '收入或上级补入',name: 'projectName',type: 'string', width: 80},
		    	{header: '实际支出',name: 'dName',type: 'string', width: 100}, 
		    	{header: '利润', name: 'code',type: 'string', width: 80},
	    		{header: '已收金额', name: 'name',type: 'string', width: 80},
	    		{header: '未收金额', name: 'state',type: 'string', width: 80}
		    ];
		    
		    var incomeExpenceGrid = createExtGrid("收支情况总表", IncomeExpence, true, {}, "${basePath}/sys/dict/queryList.do", "multi");
		    incomeExpenceGrid.getStore().on({
		    	"beforeload": function(store){
		    		//var baseParams = {dCode: dCode};
		    		//store.baseParams = baseParams;
		    	}
		    });
		    
		    var gridTopbar = incomeExpenceGrid.getTopToolbar();
		    
		    var gridTbar = [{
	            iconCls: 'printIcon',
	            text: '打印预览',
	            handler: function(){
	            }
	        },  
	        	{xtype: 'tbseparator'}
			];
		    
    		gridTopbar.add(gridTbar);
    		
    		searchForm = new Ext.form.FormPanel({
    			title: '查询条件',
    			border: false,
				margins:'2 2 2 2 ',
				height:125,
				layout:'column',
				xtype: 'form',
				frame: true,
				region: 'north',
				items:[{
					layout:'form',
					columnWidth : 0.33,
					defaults : {xtype: 'textfield', anchor: anchor_w, allowBlank: true},
           			labelAlign : 'right',
					items:[
					    {fieldLabel:'日期', name: 'idCard'}
					]
				},{
					layout:'form',
					columnWidth : 0.33,
					defaults : {xtype : 'textfield', anchor : anchor_w},
           			labelAlign : 'right',
					items:[
						{fieldLabel:'至', name: 'idCard'}
					]
				},{
					layout:'form',
					columnWidth : 0.34,
					defaults : {xtype : 'textfield', anchor : anchor_w},
           			labelAlign : 'right',
					items:[
					]
				}],
				buttonAlign:'center',
				buttons:[{
					text:'查询',
					handler: function(){
						if(!Ext.isEmpty(incomeExpenceGrid)){
							incomeExpenceGrid.getStore().load({params: {start: 0, limit: pageSize}});
						}
					}
				},{ text:'重置',
					handler: function(){
						Ext.getCmp("searchFrom").getForm().reset();
					}
				}]
    		});
    		new Ext.Viewport({
    			layout : 'border',
				items : [searchForm, incomeExpenceGrid]
    		});
		});
		
	</script>

  </head>
  
  <body>
  </body>
</html>
