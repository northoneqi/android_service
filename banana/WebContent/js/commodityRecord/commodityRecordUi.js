Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
commodityRecordUi=Ext.extend(Ext.Panel,{
	id:'commodityRecordUi',
	border:false,
	layout:'border',
	initComponent:function(){
		 var page_size=20;
		 grid.getStore().load({params:{start: 0, limit: pageSize}});
		 Ext.applyIf(this,{
			layout:'border',
			//frame:true,
			border:false,
			items:[{
				region:'north',
				autoHeight:true,
				title:'商品登记表',
				iconCls:'gridlist',
				layout:'form',
				xtype:'form',
				frame:true,
				id:'queryForm',
				items:[{
					layout:'column',
					items:[{
							columnWidth:.30,
							defaults : {anchor : '-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
							labelAlign : 'right',
							layout:'form',
							items:[{fieldLabel:'商品类别',name:'goodsClassify',xtype:'textfield'}]
						}
,{
							columnWidth:.30,
							defaults : {anchor : '-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
							labelAlign : 'right',
							layout:'form',
							items:[{fieldLabel:'厂商货号',name:'companySKU',xtype:'textfield'}]
						}
,{
							columnWidth:.30,
							defaults : {anchor : '-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
							labelAlign : 'right',
							layout:'form',
							items:[{fieldLabel:'产地',name:'productionPlace',xtype:'textfield'}]
						}
,{
							columnWidth:.30,
							defaults : {anchor : '-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
							labelAlign : 'right',
							layout:'form',
							items:[{fieldLabel:'商品名称',name:'commodityName',xtype:'textfield'}]
						}
,{
							columnWidth:.30,
							defaults : {anchor : '-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
							labelAlign : 'right',
							layout:'form',
							items:[{fieldLabel:'录入人',name:'entryPerson',xtype:'textfield'}]
						}
,{
							columnWidth:.30,
							defaults : {anchor : '-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
							labelAlign : 'right',
							layout:'form',
							items:[{fieldLabel:'录入日期',name:'entryDate',xtype:'datefield',format:'Y-m-d'}]
						}

					],
					buttonAlign:'center',
					buttons:[{text:'查   询',id:'queryButton',iconCls:'query2_button'},
						{text:'重   置',id:'resetButton',iconCls:'icon-qk'}]
				}]
				},{ 
					tbar:[
				        {text:'增加',iconCls:'addIcon',id:'add_button'},'-',
		  	 		    {text:'修改',iconCls:'edit1Icon',id:'edit_button'},'-',
		  				{text:'删除',iconCls:'deleteIcon',id:'delete_button'},'-',
		  	 		    {text:'Excel导出',iconCls:'ExcelIcon',id:'Excel_button'}
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
		});
		commodityRecordUi.superclass.initComponent.call(this);
	}
});
