Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
carRecordUi=Ext.extend(Ext.Panel,{
	id:'carRecordUi',
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
				title:'车辆档案表',
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
							items:[{fieldLabel:'车辆编号',name:'carCode',xtype:'textfield'}]
						}
,{
							columnWidth:.30,
							defaults : {anchor : '-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
							labelAlign : 'right',
							layout:'form',
							items:[{fieldLabel:'车牌号',name:'carMark',xtype:'textfield'}]
						}
,{
							columnWidth:.30,
							defaults : {anchor : '-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
							labelAlign : 'right',
							layout:'form',
							items:[{fieldLabel:'品牌型号',name:'carVersion',xtype:'textfield'}]
						}
,{
							columnWidth:.30,
							defaults : {anchor : '-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
							labelAlign : 'right',
							layout:'form',
							items:[{fieldLabel:'使用性质',name:'useState',xtype:'textfield'}]
						}
,{
							columnWidth:.30,
							defaults : {anchor : '-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
							labelAlign : 'right',
							layout:'form',
							items:[{fieldLabel:'注册日期',name:'enrollDate',xtype:'datefield',format:'Y-m-d'}]
						}
,{
							columnWidth:.30,
							defaults : {anchor : '-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
							labelAlign : 'right',
							layout:'form',
							items:[{fieldLabel:'保险日期',name:'safeDate',xtype:'datefield',format:'Y-m-d'}]
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
		  				{text:'删除',iconCls:'deleteIcon',id:'delete_button'}
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
		carRecordUi.superclass.initComponent.call(this);
	}
});
