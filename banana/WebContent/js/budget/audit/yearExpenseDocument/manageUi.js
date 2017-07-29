manageUi = Ext.extend(Ext.Panel,{
	title:'年度支出预算单管理',
	id:'manageUi',
	border:false,
	margins:'1 1 1 1',
	layout:'fit',
	initComponent: function() {
		  grid = new mainGrid();
		  Ext.applyIf(this,{
			items:[{
				layout:'border',
				items:[{
					region:'north',
					margins:'1 1 1 1',
					frame:true,
					height:140,
					layout:'fit',
					items:new searchFrom()
				},{
					region:'center',
					margins:'1 1 1 1',
					frame:true,
					layout:'fit',
					tbar: tbar,
					items: grid,
					bbar: new Ext.PagingToolbar({
    					pageSize: pageSize,
    					store: grid.getStore(),
    					displayInfo: true
  		 			})
				}]
			}]
		 });
		 manageUi.superclass.initComponent.call(this);
	}
});
