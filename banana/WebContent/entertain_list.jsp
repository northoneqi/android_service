<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/views/common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>业务招待费情况</title>
    
	<style type="text/css">
	</style>
	<script type="text/javascript" >
		Ext.onReady(function(){
			
		    var Entertain = [
		    	{header: '项目名称',name: 'projectName',type: 'string', width: 80},
		    	{header: '指标数',name: 'dName',type: 'string', width: 100}, 
		    	{header: '已使用指标', name: 'code',type: 'string', width: 100},
	    		{header: '剩余指标', name: 'name',type: 'string', width: 80},
	    		{header: '备注', name: 'state',type: 'string', width: 200}
		    ];
		    
		    var entertainGrid = createExtGrid("业务招待费情况", Entertain, true, {}, "${basePath}/sys/dict/queryList.do", "multi");
		    entertainGrid.getStore().on({
		    	"beforeload": function(store){
		    		//var baseParams = {dCode: dCode};
		    		//store.baseParams = baseParams;
		    	}
		    });
		    
		    var gridTopbar = entertainGrid.getTopToolbar();
		    
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
						{fieldLabel:'项目名称', name: 'projectName'},
					    {fieldLabel:'已使用指标', name: 'idCard'}
					]
				},{
					layout:'form',
					columnWidth : 0.33,
					defaults : {xtype : 'textfield', anchor : anchor_w},
           			labelAlign : 'right',
					items:[
						{fieldLabel:'指标数', name: 'projectName'},
						{fieldLabel:'至', name: 'projectName'}
					]
				},{
					layout:'form',
					columnWidth : 0.34,
					defaults : {xtype : 'textfield', anchor : anchor_w},
           			labelAlign : 'right',
					items:[
						{fieldLabel:'至', name: 'projectName'}
					]
				}],
				buttonAlign:'center',
				buttons:[{
					text:'查询',
					handler: function(){
						if(!Ext.isEmpty(entertainGrid)){
							entertainGrid.getStore().load({params: {start: 0, limit: pageSize}});
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
				items : [searchForm, entertainGrid]
    		});
		});
		
	</script>

  </head>
  
  <body>
  </body>
</html>
