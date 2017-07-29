Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
goodCategoryUi=Ext.extend(Ext.Panel,{
	id:'goodCategoryUi',
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
				title:'商品分类',
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
							items:[{fieldLabel:'商品分类代码',name:'goodCategoryCode',xtype:'textfield'}]
						}
,{
							columnWidth:.30,
							defaults : {anchor : '-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
							labelAlign : 'right',
							layout:'form',
							items:[{fieldLabel:'商品分类名称',name:'goodCategoryName',xtype:'textfield'}]
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
		goodCategoryUi.superclass.initComponent.call(this);
	}
});
