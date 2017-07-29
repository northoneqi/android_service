ActivityStatus2 = [
    ['0', '未开始'],
    ['1', '进行中'],
    ['2', '暂停'],
    ['3', '结束']
];

var ActivityStatusStore2 = new Ext.data.ArrayStore({
    fields: ['value', 'state'],
    data : ActivityStatus2 // from states.js
});

var activityStatusCombo2 = new Ext.form.ComboBox({
	fieldLabel: '活动状态',
    store: ActivityStatusStore2,
    displayField:'state',
    valueField: 'value',
    typeAhead: true,
    mode: 'local',
    forceSelection: true,
    triggerAction: 'all',
    hiddenName: 'activityStatus',
    name: 'activityStatusw',
    selectOnFocus:true
});

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
					   {fieldLabel:'活动期数', name: 'activityNum'},    
					{fieldLabel:'活动日期', name: 'startDate', maxValue: new Date(), xtype:'datefield',format:'Y-m-d'}
					]
				},{
					layout:'form',
					columnWidth: 0.33,
					defaults: {xtype : 'textfield', anchor : anchor_w},
           			labelAlign: 'right',
					items:[
				        activityStatusCombo2,
						{fieldLabel:'至', name: 'endDate', maxValue: new Date(), xtype:'datefield',format:'Y-m-d'}
					]
				},{
					layout:'form',
					columnWidth: 0.33,
					defaults: {xtype : 'textfield', anchor : anchor_w},
           			labelAlign: 'right',
					items:[
						{fieldLabel:'活动计划经办人', name: 'activityAuto'}
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