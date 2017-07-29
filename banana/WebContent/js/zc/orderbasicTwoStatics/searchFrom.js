searchFrom = Ext.extend(Ext.form.FormPanel,{
	id:'searchFrom',
	border:false,
	margins:'2 2 2 2 ',
	height:140,
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
					   {fieldLabel:'众筹活动Id', name: 'activityId', xtype: 'numberfield'}
					]
				},{
					layout:'form',
					columnWidth: 0.33,
					defaults: {xtype : 'textfield', anchor : anchor_w},
           			labelAlign: 'right',
					items:[ 
						{fieldLabel:'众筹期数', name: 'activityNum'}
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
						Ext.getCmp("searchFrom").getForm().reset();
					}
				}]
			}]
		 });
		 searchFrom.superclass.initComponent.call(this);
	}
});