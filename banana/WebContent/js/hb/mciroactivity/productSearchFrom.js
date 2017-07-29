ISShow = [
	['', '全部'],
    ['1', '是'],
    ['0', '否']
];

var ISShowStore = new Ext.data.ArrayStore({
    fields: ['value', 'state'],
    data : ISShow // from states.js
});

ProductSearchFrom = Ext.extend(Ext.form.FormPanel,{
	id:'productSearchFrom',
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
					   {fieldLabel:'商品名称', name: 'productName'} 
					]
				},{
					layout:'form',
					columnWidth: 0.33,
					defaults: {xtype : 'textfield', anchor : anchor_w},
           			labelAlign: 'right',
					items:[
				       new Ext.form.ComboBox({
							fieldLabel: '是否上架',
						    store: ISShowStore,
						    displayField:'state',
						    valueField: 'value',
						    typeAhead: true,
						    mode: 'local',
						    forceSelection: true,
						    triggerAction: 'all',
						    hiddenName: 'isShow',
						    name: 'activityStatusw',
						    selectOnFocus:true
						})
					]
				}],
				buttonAlign:'center',
				buttons:[{
					text:'查询',
					handler: function(){
						if(!Ext.isEmpty(Ext.getCmp("productGrid"))){
							Ext.getCmp("productGrid").getStore().load({params: {start: 0, limit: pageSize}});
						}
						
					}
				},{ text:'重置',
					handler: function(){
						Ext.getCmp("productSearchFrom").getForm().reset();
					}
				}]
			}]
		 });
		 ProductSearchFrom.superclass.initComponent.call(this);
	}
});