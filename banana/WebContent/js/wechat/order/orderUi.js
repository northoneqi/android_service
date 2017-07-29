Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
orderUi=Ext.extend(Ext.Panel,{
	//title:'职工信息管理',
	//系统操作日志
	iconCls:'gridlist',
	id:'orderUi',
	border:false,
	layout:'border',
	initComponent:function(){
		 var page_size=20;
		 grid.getStore().load();
		 Ext.applyIf(this,{
			layout:'border',
			//frame:true,
			border:false,
			items:[{
				region:'north',
				autoHeight:true,
				title:'职级变动管理',
				iconCls:'gridlist2',
				layout:'form',
				xtype:'form',
				frame:true,
				id:'queryForm',
				items:[{
					layout:'column',
					items:[{
							columnWidth:.30,
				 			defaults : {anchor : "-10", allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
	           	 			labelAlign : 'right',
	           	 			layout:'form',
	           	 			items:[{fieldLabel:'员工名称',name:'customer',xtype:'textfield'}]
						},{
							columnWidth:.30,
					 		defaults : {anchor : "-10", allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
		           	 		labelAlign : 'right',
		           	 		layout:'form',
		           	 		items:[{fieldLabel:'原职级', name:'oldRank',xtype:'textfield'}]
						},{
							columnWidth:.30,
					 		defaults : {anchor : "-10",allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
		           	 		labelAlign : 'right',
		           	 		layout:'form',
		           	 		items:[{fieldLabel:'现职级',name:'newRank'}]
					}],
					buttonAlign:'center',
					buttons:[{text:'查   询',id:'queryButton',iconCls:'query2_button'},
						{text:'重   置',id:'resetButton',iconCls:'icon-qk'}]
				}]
				},{ 
					tbar:[
				        {text:'增加',iconCls:'add_button',id:'add_button'},'-',
		  	 		    {text:'修改',iconCls:'edit_button',id:'edit_button'},'-',
		  				{text:'删除',iconCls:'delete_button',id:'delete_button'},'-',
		  	            {text:'查看',iconCls:'button_see',id:'see_button'}
	  		 	    ],
					region:'center',
					layout:'fit',
					items:grid,
					bbar:new Ext.PagingToolbar({
	    				pageSize : page_size,
	    				store : grid.getStore(),
	    				displayInfo : true,
	    				displayMsg:'每页显示'+page_size+'条记录，当前显示{0}到{1}条，共{2}条。',  
	        			emptyMsg:'没有记录'
	  		 		})
				}]	 
		 })
		orderUi.superclass.initComponent.call(this);
	}
});