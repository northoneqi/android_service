OrderState = [
	['', '全部'],
    ['0', '进行中'],
    ['1', '已完成'],
    ['2', '已取消']
];

var OrderStateStore = new Ext.data.ArrayStore({
    fields: ['value', 'state'],
    data : OrderState 
});

orderStateCombo = new Ext.form.ComboBox({
	fieldLabel: '订单状态',
    store: OrderStateStore,
    displayField:'state',
    valueField: 'value',
    typeAhead: true,
    mode: 'local',
    forceSelection: true,
    triggerAction: 'all',
    hiddenName: 'orderStatus',
    name: 'orderStatusSw',
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
					   {fieldLabel:'众筹订单编号', name: 'crowdfundingCode'},    
					{fieldLabel:'发送日期', name: 'startDate', xtype:'datefield',format:'Y-m-d'}
					]
				},{
					layout:'form',
					columnWidth: 0.33,
					defaults: {xtype : 'textfield', anchor : anchor_w},
           			labelAlign: 'right',
					items:[ 
				        orderStateCombo,
						{fieldLabel:'至', name: 'endDate', xtype:'datefield',format:'Y-m-d'}
					]
				},{
					layout:'form',
					columnWidth: 0.33,
					defaults: {xtype : 'textfield', anchor : anchor_w},
           			labelAlign: 'right',
					items:[
					    {fieldLabel:'区', name: 'area'},   
						{fieldLabel:'发起人电话', name: 'tel'}
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