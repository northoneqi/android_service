Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
commodityChangePriceUi=Ext.extend(Ext.Panel,{
	id:'commodityChangePriceUi',
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
				title:'调价单主表',
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
							items:[{fieldLabel:'调价单号',name:'priceOrder',xtype:'textfield'}]
						}
,{
							columnWidth:.30,
							defaults : {anchor : '-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
							labelAlign : 'right',
							layout:'form',
							items:[{fieldLabel:'部门',name:'department',xtype:'textfield'}]
						}
,{
							columnWidth:.30,
							defaults : {anchor : '-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
							labelAlign : 'right',
							layout:'form',
							items:[{fieldLabel:'调价人',name:'pricePeople',xtype:'textfield'}]
						}
,{
							columnWidth:.30,
							defaults : {anchor : '-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
							labelAlign : 'right',
							layout:'form',
							items:[{fieldLabel:'审核人',name:'audit',xtype:'textfield'}]
						}
,{
							columnWidth:.30,
							defaults : {anchor : '-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
							labelAlign : 'right',
							layout:'form',
							items:[{fieldLabel:'生效时间',name:'effectTime',xtype:'datefield',format:'Y-m-d'}]
						}
,{
							columnWidth:.30,
							defaults : {anchor : '-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
							labelAlign : 'right',
							layout:'form',
							items:[{fieldLabel:'失效时间',name:'failureTime',xtype:'datefield',format:'Y-m-d'}]
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
		commodityChangePriceUi.superclass.initComponent.call(this);
	}
});
