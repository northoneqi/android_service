<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/views/common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
    
    <title>专项活动收支情况</title>
    
	<style type="text/css">
	</style>
	<script type="text/javascript" >
		Ext.onReady(function(){
			
		    var Activity = [
		    	{header: '项目名称',name: 'projectName',type: 'string', width: 80},
		    	{header: '负责科室',name: 'dName',type: 'string', width: 100}, 
		    	{header: '单位名称', name: 'code',type: 'string', width: 100},
	    		{header: '日期', name: 'name',type: 'string', width: 80},
	    		{header: '人数', name: 'state',type: 'string', width: 60},
	    		{header: '应收金额', name: 'remark',type: 'string', width: 80},
	    		{header: '实收金额', name: 'remark',type: 'string', width: 80},
	    		{header: '应收金额', name: 'remark',type: 'string', width: 80},
	    		{header: '预算金额', name: 'remark',type: 'string', width: 80},
	    		{header: '已用金额', name: 'remark',type: 'string', width: 80},
	    		{header: '剩余金额', name: 'remark',type: 'string', width: 80},
	    		{header: '剩余金额', name: 'remark',type: 'string', width: 80}
		    ];
		    
		    var activityGrid = createExtGrid("专项活动收支情况", Activity, true, {}, "${basePath}/sys/dict/queryList.do", "multi");
		    activityGrid.getStore().on({
		    	"beforeload": function(store){
		    		//var baseParams = {dCode: dCode};
		    		//store.baseParams = baseParams;
		    	}
		    });
		    
		    var gridTopbar = activityGrid.getTopToolbar();
		    
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
					columnWidth : 0.25,
					defaults : {xtype: 'textfield', anchor: anchor_w, allowBlank: true},
           			labelAlign : 'right',
					items:[
						{fieldLabel:'项目名称', name: 'projectName'},
					    {fieldLabel:'实收金额', name: 'idCard'}
					]
				},{
					layout:'form',
					columnWidth : 0.25,
					defaults : {xtype : 'textfield', anchor : anchor_w},
           			labelAlign : 'right',
					items:[
						createComboTree({
							url: 'sys/department/queryTree.do',
							fieldLabel: '负责科室',
							hiddenName: 'departmentCode',
							name: 'departmentName'
						}),
						{fieldLabel:'至', name: 'projectName'}
					]
				},{
					layout:'form',
					columnWidth : 0.25,
					defaults : {xtype : 'textfield', anchor : anchor_w},
           			labelAlign : 'right',
					items:[
						{fieldLabel:'应收金额', name: 'projectName'},
						{fieldLabel:'预算金额', name: 'projectName'}
					]
				},{
					layout:'form',
					columnWidth : 0.25,
					defaults : {xtype : 'textfield', anchor : anchor_w},
           			labelAlign : 'right',
					items:[
						{fieldLabel:'至', name: 'projectName'},
						{fieldLabel:'至', name: 'projectName'}
					]
				}],
				buttonAlign:'center',
				buttons:[{
					text:'查询',
					handler: function(){
						if(!Ext.isEmpty(activityGrid)){
							activityGrid.getStore().load({params: {start: 0, limit: pageSize}});
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
				items : [searchForm, activityGrid]
    		});
		});
		
	</script>

  </head>
  
  <body>
  </body>
</html>
