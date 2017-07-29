mcirowaterUi=Ext.extend(Ext.Panel,{
	id:'mcirowaterUi',
	border:false,
	margins:'1 1 1 1',
	layout:'fit',
	initComponent: function() {
		 pageSize=20;
		 mcirowaterGrid=new mcirowaterGrid();
		 searchForm=new searchForm();
		 Ext.applyIf(this,{
			items:[{
				layout:'border',
				border:false,
				items:[{
					region:'north',
					margins:'1 1 1 1',
					border:false,
					height:100,
					layout:'fit',
					items:searchForm
				},{
					region:'center',
					margins:'1 1 1 1',
					layout:'fit',
					tbar:{height:34,items:[{text:'审批通过',iconCls:'acceptIcon',id:'acceptIcon'},
										   {text:'驳回申请',iconCls:'returnIcon',id:'returnIcon'},
										   '-']},
					border:false,
					items:mcirowaterGrid,
					bbar: new Ext.PagingToolbar({
			            pageSize: pageSize,
			            store: mcirowaterGrid.getStore(),
			            displayInfo: true
			    	})
				}]
			}]
		 });
		 mcirowaterUi.superclass.initComponent.call(this);
	}
});