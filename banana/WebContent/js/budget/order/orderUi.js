orderUi=Ext.extend(Ext.Panel,{
	id:'orderUi',
	border:false,
	margins:'1 1 1 1',
	layout:'fit',
	initComponent: function() {
		 Ext.applyIf(this,{
			items:[{
				layout:'border',
				items:[{
					region:'north',
					margins:'1 1 1 1',
					frame:true,
					height:160,
					layout:'fit',
					items:new searchFrom()
					//html:'sss'
				},{
					region:'center',
					margins:'1 1 1 1',
					frame:true,
					layout:'fit',
					//html:''
					items:new mainGrid(),
					tbar:[{
				            iconCls: 'addIcon',
				            id:'addButton',
				            text: '添加'
				        },{
				            iconCls: 'edit1Icon',
				            id:'updateButton',
				            text: '更新'
				        },{
				            iconCls: 'deleteIcon',
				            id:'deleteButton',
				            text: '删除'
				        }]
				}]
			}]
		 });
		orderUi.superclass.initComponent.call(this);
	}
});