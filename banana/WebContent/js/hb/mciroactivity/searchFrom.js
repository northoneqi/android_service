searchFrom = Ext.extend(Ext.form.FormPanel,{
	id:'searchFrom',
	border:false,
	margins:'2 2 2 2 ',
	height:145,
	layout:'form',
	xtype: 'form',
	frame: false,
	initComponent: function() {
		 Ext.applyIf(this,{
			items:[{
				xtype:'fieldset',
				collapseFirst:false, 
				margins:'2 2 0 2 ',
				header : true,
				animCollapse:true, 
				border:false,
				collapsible : false, //可折叠
				layout:'column',
				items:[{
					layout:'form',
					columnWidth: 0.33,
					defaults: {xtype: 'textfield', anchor: anchor_w, allowBlank: true},
           			labelAlign: 'right',
					items:[
					   {fieldLabel:'活动名称', name: 'activityName'},    
					{fieldLabel:'奖品数量', name: 'prizeCount'}
					]
				},{
					layout:'form',
					columnWidth: 0.33,
					defaults: {xtype : 'textfield', anchor : anchor_w},
           			labelAlign: 'right',
					items:[
				        {fieldLabel:'活动日期', name: 'startDate',  xtype:'datetimefield',format:'Y-m-d'},
				        {fieldLabel:'活动时间', name: 'startTime', xtype:'timefield',format:'Y-m-d'}
					]
				},{
					layout:'form',
					columnWidth: 0.33,
					defaults: {xtype : 'textfield', anchor : anchor_w},
           			labelAlign: 'right',
					items:[
						{fieldLabel:'至', name: 'endDate',xtype:'datetimefield',format:'Y-m-d'},
						{fieldLabel:'至', name: 'endTime',xtype:'timefield',format:'Y-m-d'}
						]
				}],
				buttonAlign:'center',
				buttons:[{
					text:'查询',
					handler: function(){
						if(!Ext.isEmpty(Ext.getCmp("mainGrid"))){
							Ext.getCmp("mainGrid").getStore().load({params: {start: 0, limit: pageSize}});
						}
						
					}
				},{ text:'重置',
					handler: function(){
					//alert(Ext.getCmp("searchFrom").getForm().getValues().activityStatus);
						// Ext.getCmp("searchFrom").getForm().getValues().activityStatus='';
						Ext.getCmp("searchFrom").getForm().reset();
					}
				}]
			}]
		 });
		 searchFrom.superclass.initComponent.call(this);
	}
});